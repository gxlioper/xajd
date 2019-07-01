/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-25 ����01:31:46 
 */  
package xsgzgl.gygl.xyzsgl.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-5-25 ����01:31:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyzsSqService extends SuperServiceImpl<XyzsSqForm,XyzsSqDao> {
	XyzsSqDao dao = new XyzsSqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	public List<HashMap<String,String>> getZyjzxx(XyzsSqForm model){
		return dao.getZyjzxx(model);
	}
	
	/**
	 * �жϵ�ǰѧ���Ƿ���У��ס�޽��
	 */
	public boolean checkExistForSave(XyzsSqForm model){
		return dao.checkExistForSave(model);
	}

	/**
	 * 
	 * �������ӽ��
	 */
	public boolean saveZsjg(XyzsSqForm model, User user) throws Exception {
		String splc = dao.getShlcID();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		model.setXn(Base.currXn);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
//		boolean flag = dao.saveZsjg(model, user);
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				String sqbh = dao.getSqbh(model);
				flag = shlc.runSubmit(sqbh, splc, model.getXh(), "gygl_xyzssh.do", "gygl_xyzssq.do");
			}
		}
		return flag;
	}
	/**
	 * 
	 * �����޸Ľ��
	 */
	public boolean saveZsjgUpdate(XyzsSqForm model, User user) throws Exception {
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
				flag = shlc.runSubmit(model.getSqbh(), model.getSplc(), model.getXh(), "gygl_xyzssh.do", "gygl_xyzssq.do");
			}
		}
		return flag;
	}
	
	//�ύ
	public boolean submitBusi(XyzsSqForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		String splc = dao.getShlcID();
		model.setSplc(splc);
		if(flag){
			 shlc.runSubmit(model.getSqbh(), model.getSplc(), model.getXh(), "gygl_xyzssh.do", "gygl_xyzssq.do");
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
}
