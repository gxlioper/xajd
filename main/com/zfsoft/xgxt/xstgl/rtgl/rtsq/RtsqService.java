/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-6 ����02:21:29 
 */  
package com.zfsoft.xgxt.xstgl.rtgl.rtsq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgForm;
import com.zfsoft.xgxt.xsztz.tzxmsq.XsXmSqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ź���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-6 ����02:21:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RtsqService extends SuperServiceImpl<RtsqForm,RtsqDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	
	//��ȡ������Ϣ�������ų�Ա��Ϣά���Լ��鿴
	public HashMap<String, String> getStxxMap(RtsqForm rtsq){
		return dao.getStxxMap(rtsq);
	}
	
	//��ȡ���ų�Ա��Ϣ
	public HashMap<String, String> getStxxCyMap(RtsqForm rtsq,User user){
		return dao.getStxxCyMap(rtsq, user);
	}
	
	//�ύ
	public boolean submitBusi(RtsqForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getRtid(), model.getSplc(), model.getXh(), "stgl_rtgl_rtsh.do", "stgl_rtgl_rtsq.do");
		}
		return flag;
	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	public List<HashMap<String, String>> getStxmList(RtsqForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		return dao.getStxmList(model, user);
	}
	
	/**
	 * 
	 * �������ӽ��
	 */
	public boolean saveRtsq(RtsqForm model, User user) throws Exception {
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
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "stgl_rtgl_rtsh.do", "stgl_rtgl_rtsq.do");
			}
		}
		return flag;
	}
	
	//�ظ��ж�
	public boolean checkExistForSave(RtsqForm model) {
		return dao.checkExistForSave(model);
	}
	
	//�����޸Ľ��
	public boolean saveSqjgUpdate(RtsqForm model, User user) throws Exception {
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
				flag = shlc.runSubmit(model.getRtid(), model.getSplc(), model.getXh(), "stgl_rtgl_rtsh.do", "stgl_rtgl_rtsq.do");
			}
		}
		return flag;
	}
	
	public String getsplc(RtsqForm model){
		return dao.getsplc(model);
	}
	
	//ѧ��ҳ���ѯ
	public List<HashMap<String, String>> getStuRtsqList(RtsqForm model, User user)  throws Exception{
		return dao.getStuRtsqList(model, user);
	}

    public List<HashMap<String,String>> getZdlsInfo(RtsqForm model) {
		return dao.getZdlsInfo(model);
    }
}
