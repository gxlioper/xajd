package com.zfsoft.xgxt.jskp.lxsh;

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
import com.zfsoft.xgxt.jskp.lxsq.LxsqService;
import com.zfsoft.xgxt.jskp.xmjg.XmjgDao;
import com.zfsoft.xgxt.jskp.xmjg.XmjgForm;
import com.zfsoft.xgxt.jskp.xmjg.XmjgService;

public class LxshService extends SuperServiceImpl<LxshForm, LxshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 *������˱���
	 */
	@TransactionControl
	public boolean saveSh(LxshForm form, User user) throws Exception{
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
		model.setYwid(form.getSqid());
		model.setSqrid(form.getFzr());
		model.setTzlj("pjpy_jskp_lxsh.do");
		model.setTzljsq("pjpy_jskp_lxsq.do");
		model.setZd1("��������");
		model.setZd3(form.getZxf()+"-"+form.getZdf());
		boolean reuslt = false;
		String zhzt = shlc.runAuditingNotCommit(model);
		LxshForm shForm = new LxshForm();
		shForm.setSqid(form.getSqid());
		shForm.setShzt(zhzt);
		reuslt = dao.runUpdateNotCommit(shForm, form.getSqid());
		// ���浽�����
		if (zhzt.equalsIgnoreCase(Constants.YW_TG) && reuslt) {
			XmjgForm jgForm = new XmjgForm();
			shForm = this.getModel(shForm);
			BeanUtils.copyProperties(jgForm, StringUtils.formatData(shForm));
			jgForm.setXmid(shForm.getSqid());
			jgForm.setSjly("1");
			jgForm.setXmsqid(shForm.getSqid());
			jgForm.setXmdl("zlx");
			jgForm.setZxf(form.getZxf());
			jgForm.setZdf(form.getZdf());
			reuslt = new XmjgDao().runInsertNotCommit(jgForm);
			if(reuslt){
				reuslt = dao.updateXmsbLxzt(jgForm.getXmid());
				String splc = new CsszService().getSplc("sb").get("splc");
				if(reuslt){
					reuslt = dao.updateSbSplc(jgForm.getXmid(), splc);
					List<HashMap<String, String>> submitList = new LxsqService().getXmcyryXhs(jgForm.getXmid());
					List<String> xhList = new ArrayList<String>();
					List<String> sqidList = new ArrayList<String>();
					for (int i = 0; i < submitList.size(); i++) {
						xhList.add(submitList.get(i).get("xh"));
						sqidList.add(submitList.get(i).get("sqid"));
					}
					if(sqidList.size() > 0){
						reuslt =  shlc.runSubmitBatchNotCommit(sqidList.toArray(new String[]{}),splc, xhList.toArray(new String[]{}), "jskp_xmsh.do", "jskp_xmsb.do");
					}
					dao.submitSqjl(shForm.getSqid());
				}
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
	public String savePlsh(LxshForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		LxshForm model = new LxshForm();
		String[] sqids = t.getSqids();
		String[] gwid = t.getGwids();
		String[] fzrs = t.getFzrs();
		String[] splcids = t.getSplcids();
		List<String> failZghs = new ArrayList<String>();
		//Ҫ��Ҫ����֤�д��о�
	
		for (int i = 0, n = sqids.length; i < n; i++) {
			model.setSplcid(splcids[i]);
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(sqids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setFzr(fzrs[i]);
			model.setZxf(t.getZxf());
			model.setZdf(t.getZdf());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failZghs.add(fzrs[i]);
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
	public boolean cancel(LxshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if (result) {
			XmjgService jgService = new XmjgService();
		
			// ɾ��������е�������
			jgService.runDelete(new String[]{myForm.getSqid()});
			dao.updateXmsbZt(myForm.getSqid(),"0");
		
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
	public String cxshnew(String ywid, LxshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		model.setShzt(shzt);
		dao.runUpdate(model, ywid);
		return cancelFlag;
	}
	
	/**
	 * 
	 * @����:��֤�Ƿ���δ�ύ�ļ�¼
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-21 ����02:06:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isStuSbTj(String sqid) throws Exception{
		return dao.isStuSbTj(sqid);
	}
	
	/**
	 * 
	 * @����: ��ȡ��һ������ֶ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-18 ����04:51:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getLastshzd(String ywid){
		return dao.getLastshzd(ywid);
	}

}
