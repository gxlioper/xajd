/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-10 ����01:48:34 
 */  
package com.zfsoft.xgxt.xsztz.tzxmsq;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-7-10 ����01:48:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsXmSqService extends SuperServiceImpl<XsXmSqForm, XsXmSqDao> {
	XsXmSqDao dao = new XsXmSqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @����:��ȡ������˿���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-10 ����04:29:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] getSqShKg() throws Exception{
		return dao.getSqShKg();
	}
	
	/**
	 * 
	 * @����:�ظ��ж�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-10 ����04:37:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkExistForSave(XsXmSqForm model) {
		return dao.checkExistForSave(model);
	}
	
	//�ظ��ж�2     ���޸��ύ���߹�ѡ�ύʱ�ظ��жϣ��ж������ǽ�������Ƿ������ݡ�
	public boolean checkExistForSave2(XsXmSqForm model) {
		return dao.checkExistForSave2(model);
	}
	
	public List<HashMap<String, String>> getXmSelectList(String xh,String flag) throws Exception {
		return dao.getXmlist(xh);
	}
	
	/**
	 * 
	 * �������ӽ��
	 */
	public boolean saveSqjg(XsXmSqForm model, User user) throws Exception {
		String splc = model.getSplc();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				String sqid = dao.getSqid(model);
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "sztz_xmsqgl_xmsh.do", "sztz_xmsqgl_xmsq.do");
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:�����޸Ľ��
	 * @���ߣ��Ų�·[���ţ�1206]
	 * @���ڣ�2015-7-15 ����04:09:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSqjgUpdate(XsXmSqForm model, User user) throws Exception {
		if(model.getType().equals("update")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runUpdate(model);
		if(model.getType().equals("updatesubmit")){
			if(flag){
				String splc = model.getSplc();
				model.setSplc(splc);
				flag = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "sztz_xmsqgl_xmsh.do", "sztz_xmsqgl_xmsq.do");
			}
		}
		return flag;
	}
	
	//�ύ
	public boolean submitBusi(XsXmSqForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		String splc = model.getSplc();
		model.setSplc(splc);
		if(flag){
			 shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "sztz_xmsqgl_xmsh.do", "sztz_xmsqgl_xmsq.do");
		}
		return flag;
	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	//���Ϣ
	public HashMap<String, String> getHdMap(String xmdm,String xn,String xq){
		return dao.getHdMap(xmdm,xn,xq);
	}
}
