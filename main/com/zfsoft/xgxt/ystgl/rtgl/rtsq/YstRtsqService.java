/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-02-16 ����02:21:29 
 */  
package com.zfsoft.xgxt.ystgl.rtgl.rtsq;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ź���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2016-02-16 ����02:21:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YstRtsqService extends SuperServiceImpl<YstRtsqForm,YstRtsqDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	
	public HashMap<String, String> getYstxxMap(YstRtsqForm t){
		return dao.getYstxxMap(t);
	}
	
	//��ȡ���ų�Ա��Ϣ
	public HashMap<String, String> getStxxCyMap(YstRtsqForm YstRtsq,User user){
		return dao.getStxxCyMap(YstRtsq, user);
	}
	
	//�ύ
	public boolean submitBusi(YstRtsqForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getRtid(), model.getSplc(), model.getXh(), "ystgl_rtgl_rtsh.do", "ystgl_rtgl_rtsq.do");
		}
		return flag;
	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	public List<HashMap<String, String>> getStxmList(YstRtsqForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		return dao.getStxmList(model, user);
	}
	
	/**
	 * 
	 * ��������
	 */
	public boolean saveYstRtsq(YstRtsqForm model, User user) throws Exception {
		String splc = model.getSplc();
		String rtid = UniqID.getInstance().getUniqIDHash();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		model.setRtid(rtid);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				flag = shlc.runSubmit(rtid, splc, model.getXh(), "ystgl_rtgl_rtsh.do", "ystgl_rtgl_rtsq.do");
			}
		}
		return flag;
	}
	
	//�ظ��ж�
	public boolean checkExistForSave(YstRtsqForm model) {
		return dao.checkExistForSave(model);
	}
	
	//�����޸Ľ��
	public boolean saveSqjgUpdate(YstRtsqForm model, User user) throws Exception {
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
				flag = shlc.runSubmit(model.getRtid(), model.getSplc(), model.getXh(), "ystgl_rtgl_rtsh.do", "ystgl_rtgl_rtsq.do");
			}
		}
		return flag;
	}
	
	public String getsplc(YstRtsqForm model){
		return dao.getsplc(model);
	}
	
	//ѧ��ҳ���ѯ
	public List<HashMap<String, String>> getStuYstRtsqList(YstRtsqForm model, User user)  throws Exception{
		return dao.getStuYstRtsqList(model, user);
	}
}
