/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-18 ����02:35:00 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-18 ����02:35:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkSqService extends SuperServiceImpl<XnwxdkSqModel, XnwxdkSqDao> {
	XnwxdkSqDao dao = new XnwxdkSqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * ������������
	 */
	public boolean saveDksq(XnwxdkSqModel model, User user) throws Exception {
		String splc = dao.getShlcID();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		model.setXn(Base.currXn);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				String sqid = dao.getSqbh(model);
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "zxdk_xnwxdk_sh.do", "zxdk_xnwxdk_sq.do");
			}
		}
		return flag;
	}
	/**
	 * 
	 * �����޸�����
	 */
	public boolean saveDksqUpdate(XnwxdkSqModel model, User user) throws Exception {
		if(model.getType().equals("update")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runUpdate(model);
		if(model.getType().equals("updatesubmit")){
			if(flag){
				String splc = dao.getShlcID();
				model.setSplc(splc);
				flag = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "zxdk_xnwxdk_sh.do", "zxdk_xnwxdk_sq.do");
			}
		}
		return flag;
	}
	
	//�ύ
	public boolean submitBusi(XnwxdkSqModel model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		String splc = dao.getShlcID();
		model.setSplc(splc);
		if(flag){
			 shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "zxdk_xnwxdk_sh.do", "zxdk_xnwxdk_sq.do");
		}
		return flag;
	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/*
	 * ����������ʱ���ȡѧ��������Ϣ�Լ�ס��������Ϣ
	 */
	public HashMap<String, String> getXyzsxxMap(XyzsSqForm model, User user) throws Exception {
		
		HashMap<String, String> xsxxmap = dao.getXyzsxxMap(model, user);
	
		return xsxxmap;
	}
	
	/**
	 *��ȡѧ�������ѧ������map,��������ʹ��
     *
	 */
	public List<HashMap<String, String>> getXl(XyzsSqForm model) {
		return dao.getXl(model);
	}
	
	/**
	 * �鿴ʱ��ʾѧ������
	 */
	public HashMap<String, String> getXlCk(XyzsSqForm model) {
		return dao.getXlCk(model);
	}
	
	/**
	 * �����סԭ��
	 */
	public HashMap<String, String> getXyZsyy(XyzsSqForm model) {
		return dao.getXyZsyy(model);
	}
	
	//��ȡ���״̬����
	public String getShztMc(XyzsSqForm model){
		return dao.getShztMc(model);
    }
	
	//��ȡ�������
	public String getJesx() {
		return dao.getJesx();
	}
}
