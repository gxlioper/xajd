/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-24 ����02:46:56 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdkjm.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqDao;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-24 ����02:46:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkjmsqService extends SuperServiceImpl<XnwxdkjmsqModel, XnwxdkjmsqDao> {
	XnwxdkjmsqDao dao = new XnwxdkjmsqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * ������������
	 */
	public boolean saveDksq(XnwxdkjmsqModel model, User user) throws Exception {
		String splc = dao.getShlcID();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
//		model.setXn(Base.currXn);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				String sqid = dao.getSqbh(model);
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "zxdk_xnwxdkjm_sh.do", "zxdk_xnwxdkjm_sq.do");
			}
			
		}
		return flag;
	}
	/**
	 * 
	 * �����޸�����
	 */
	public boolean saveDksqUpdate(XnwxdkjmsqModel model, User user) throws Exception {
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
				flag = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "zxdk_xnwxdkjm_sh.do", "zxdk_xnwxdkjm_sq.do");
			}
		}
		return flag;
	}
	
	//�ύ
	public boolean submitBusi(XnwxdkjmsqModel model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		String splc = dao.getShlcID();
		model.setSplc(splc);
		if(flag){
			 shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "zxdk_xnwxdkjm_sh.do", "zxdk_xnwxdkjm_sq.do");
		}
		return flag;
	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
}
