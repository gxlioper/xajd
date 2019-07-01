package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsh;

import java.util.ArrayList;
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
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybjg.YbjgDao;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybjg.YbjgForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybjg.YbjgService;

public class YbshService extends SuperServiceImpl<YbshForm, YbshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 *������˱���
	 */
	public boolean saveSh(YbshForm form, User user) throws Exception{
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
		model.setYwid(form.getSbid());
		model.setSqrid(form.getTxr());
		model.setTzlj("xg_xlzxnew_ybsh.do");
		model.setTzljsq("xg_xlzxnew_ybsb.do");
		boolean reuslt = false;
		String zhzt = shlc.runAuditing(model);
		YbshForm shForm = new YbshForm();
		shForm.setSbid(form.getSbid());
		shForm.setShzt(zhzt);
		reuslt = dao.runUpdate(shForm, form.getSbid());
		// ���浽�����
		if (zhzt.equalsIgnoreCase(Constants.YW_TG) && reuslt) {
			YbjgForm jgForm = new YbjgForm();
			shForm = this.getModel(shForm);
			//�ȶԽ�����б�ѧ��ѧ����ͬ�༶��ͬ�ܴε��������ݽ���ɾ��
			dao.delJgWtxxData(shForm);
			dao.delJgbRepeaData(shForm);
			BeanUtils.copyProperties(jgForm, StringUtils.formatData(shForm));
			jgForm.setJgid(shForm.getSbid());
			jgForm.setSjly("1");
			reuslt = new YbjgDao().runInsert(jgForm);
			if(reuslt){
				dao.shUpdateWtxxb(shForm.getSbid());
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
	public String savePlsh(YbshForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		YbshForm model = new YbshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] txrs = t.getTxrss();
		String[] splcids = t.getSplcids();
		List<String> failZghs = new ArrayList<String>();
		//Ҫ��Ҫ����֤�д��о�
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplcid(splcids[i]);
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSbid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setTxr(txrs[i]);
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failZghs.add(txrs[i]);
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
	public boolean cancel(YbshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSbid());
		if (result) {
			YbjgService jgService = new YbjgService();
		
			// ɾ��������е�������
			jgService.runDelete(new String[]{myForm.getSbid()});
			dao.cxUpdateWtxxb(myForm.getSbid());
		
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
	public String cxshnew(String ywid, YbshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		model.setShzt(shzt);
		dao.runUpdate(model, ywid);
		return cancelFlag;
	}
}
