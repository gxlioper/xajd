/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ� 2013-12-18 ����08:52:03 
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbbsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg.HcccqjjgForm;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg.HcccqjjgService;
import com.zfsoft.xgxt.rcsw.xszbb.xszbbjg.XszbbjgDao;
import com.zfsoft.xgxt.rcsw.xszbb.xszbbjg.XszbbjgForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��֤�������ģ��
 * @�๦������: TODO(ѧ��֤����-�������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-18 ����08:52:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XszbbshService extends SuperServiceImpl<XszbbshForm, XszbbshDao> {

	private XszbbshDao dao = new XszbbshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public XszbbshService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:��ѯ��ȡ������Ϣ
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����04:53:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXszbbshInfo(XszbbshForm model) {
		if (StringUtil.isNull(model.getBbsqid())) {
			logger.error("����ID����Ϊ�գ�");
			throw new NullPointerException();
		}
		return dao.getXszbbshInfo(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * ����ѧ��֤������� 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-6 ����06:58:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean saveSh(XszbbshForm form,User user) throws Exception{
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplc());
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
		model.setYwid(form.getBbsqid());
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_xszbb_bbsh.do");
		model.setTzljsq("rcsw_xszbb_bbsq.do");
		boolean reuslt = false;
		
			String zhzt = shlc.runAuditingNotCommit(model);
			XszbbshForm upForm = new XszbbshForm();
			upForm.setBbsqid(form.getBbsqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(upForm, form.getBbsqid());
			//���״̬Ϊͨ�������ճ���Ϊ������б����������
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				XszbbjgForm xszbbjgForm = new XszbbjgForm();
        		BeanUtils.copyProperties(xszbbjgForm, StringUtils.formatData(form));
        		xszbbjgForm.setBbjgid(form.getBbsqid());
        		xszbbjgForm.setSjly("1");
        		xszbbjgForm.setBbsqid(form.getBbsqid());
        		if(Base.xxdm.equals("13011")){        			
        			xszbbjgForm.setShwcsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
        		}
        		XszbbjgDao bbjgDao = new XszbbjgDao();
        		bbjgDao.runInsert(xszbbjgForm);
        		if(Base.xxdm.equals("13011") || Base.xxdm.equals("10695")){//�ൺ�Ƶ�ְҵ����ѧԺ���Ի�
        			hcccqjUpdate(xszbbjgForm);
        		}
			}	
		
		return reuslt;
	}
	
	
	/**
	 * 
	 * @����:TODO(����ѧ��֤�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-18 ����03:46:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean newCancelSh(XszbbshForm model){
		boolean resultRcww = false;
		boolean resultRcxwjg = false;
		try {
			//�����ճ���Ϊ��Ϣά��
			resultRcww = dao.updateXsbbsq(model.getBbsqid(), Constants.YW_SHZ);
			if(resultRcww){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultRcxwjg = true;
				}else{
					//ɾ���ճ���Ϊ����еļ�¼
					resultRcxwjg = dao.deleteXsbbsq(model.getBbsqid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultRcxwjg;
	}

	/**
	 * @throws Exception  
	 * @����:��������֤���������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-25 ����10:23:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	@TransactionControl
	public String savePlsh(XszbbshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			//�õ���ID��Ӧ����������ID
			XszbbshDao xszbbshdao = new XszbbshDao();
			Map<String, String> resultList = xszbbshdao.getOneXsbbsqInfo(ids[i]);
			
			XszbbshForm model = new XszbbshForm();
			model.setSplc(resultList.get("splc"));
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setBbsqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setSqsj(resultList.get("sqsj"));
			model.setXszbblxdm(resultList.get("xszbblxdm"));
			model.setSfbbhcyhk(resultList.get("sfbbhcyhk"));
			model.setDd(resultList.get("dd"));
			model.setSj(resultList.get("sj"));
			model.setSqly(resultList.get("sqly"));
			model.setFilepath(resultList.get("filepath"));

			boolean isSuccess = saveSh(model, user);

			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		

		JSONArray json = JSONArray.fromObject(failXhs);

		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}
	
	/**
	 * @throws Exception  
	 * @����:�𳵳˳��������(�ൺ�Ƶ������Ի�)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-5-23 ����07:06:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws 
	 */
	public void hcccqjUpdate(XszbbjgForm xszbbjgForm) throws Exception{
		XszbbjgDao bbjgDao = new XszbbjgDao();				
		if("y".equalsIgnoreCase(xszbbjgForm.getSfbbhcyhk())){//����𳵳˳����俨			
			bbjgDao.delHcccqj(xszbbjgForm.getXh(), Base.currXn, Base.currXq);//ɾ������ȣ���ѧ�ڸ�ͬѧ�𳵳˳�������
			HcccqjjgForm jgForm = new HcccqjjgForm();
			jgForm.setXh(xszbbjgForm.getXh());
			jgForm.setXn(Base.currXn);
			jgForm.setXq(Base.currXq);
			jgForm.setSjly("0");
			jgForm.setCcqdz(xszbbjgForm.getCcqdz());
			jgForm.setCczdz(xszbbjgForm.getCczdz());
			jgForm.setTxsj(xszbbjgForm.getSqsj());
			HcccqjjgService jgService = new HcccqjjgService();
			jgService.runInsert(jgForm);
		}
		
	}
	

}
