package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.jskp.lxsh.LxshForm;
import com.zfsoft.xgxt.jskp.lxsq.LxsqService;
import com.zfsoft.xgxt.jskp.xmjg.XmjgDao;
import com.zfsoft.xgxt.jskp.xmjg.XmjgForm;
import com.zfsoft.xgxt.jskp.xmjg.XmjgService;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbjg.ZbjgDao;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbjg.ZbjgForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbjg.ZbjgService;

public class ZbshService extends SuperServiceImpl<ZbshForm,ZbshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 *������˱���
	 */
	@TransactionControl
	public boolean saveSh(ZbshForm form, User user) throws Exception{
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplcid());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getSbsqid());
		model.setSqrid(form.getXh());
		model.setTzlj("xg_xlzxnew_zbsh.do");
		model.setTzljsq("xg_xlzxnew_zbsb.do");
		boolean reuslt = false;
		String zhzt = shlc.runAuditingNotCommit(model);
		ZbshForm shForm = new ZbshForm();
		shForm.setSbsqid(form.getSbsqid());
		shForm.setShzt(zhzt);
		reuslt = dao.runUpdateNotCommit(shForm, form.getSbsqid());
		// ���浽�����
		if (zhzt.equalsIgnoreCase(Constants.YW_TG) && reuslt) {
			ZbjgForm jgForm = new ZbjgForm();
			shForm = this.getModel(shForm);
			//�ȶԽ�����б�ѧ��ѧ����ͬ�༶��ͬ�ܴε��������ݽ���ɾ��
			dao.delJgWtxxData(shForm);
			dao.delJgWtxxData(shForm);
			BeanUtils.copyProperties(jgForm, StringUtils.formatData(shForm));
			jgForm.setSbjgid(shForm.getSbsqid());
			jgForm.setSjly("1");
			reuslt = new ZbjgDao().runInsertNotCommit(jgForm);
			if(reuslt){
				dao.shUpdateWtxxb(shForm.getSbsqid());
			}
		}
		return reuslt;
	}
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-19 ����09:39:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	@TransactionControl
	public String savePlsh(ZbshForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		ZbshForm model = new ZbshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcids = t.getSplcids();
		List<String> failZghs = new ArrayList<String>();
		//Ҫ��Ҫ����֤�д��о�
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplcid(splcids[i]);
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSbsqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failZghs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failZghs);
		if (failZghs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-19 ����11:03:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(ZbshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSbsqid());
		if (result) {
			ZbjgService jgService = new ZbjgService();
		
			// ɾ��������е�������
			jgService.runDelete(new String[]{myForm.getSbsqid()});
			dao.cxUpdateWtxxb(myForm.getSbsqid());
		
		}
		return result;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-19 ����11:03:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String cxshnew(String ywid, ZbshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		model.setShzt(shzt);
		dao.runUpdate(model, ywid);
		return cancelFlag;
	}
}
