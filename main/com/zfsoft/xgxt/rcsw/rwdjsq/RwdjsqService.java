/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-3 ����02:47:26 
 */  
package com.zfsoft.xgxt.rcsw.rwdjsq;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.rwdjsh.RwdjshForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-3 ����02:47:26 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwdjsqService extends SuperServiceImpl<RwdjsqForm, RwdjsqDao> {
	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * 
	 * @����: ��֤��ѧ���Ѵ�������ǼǼ�¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-4 ����09:01:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExist(String xh){
		return dao.checkIsNotExist(xh);
	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * 
	 * @����: �ύ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-4 ����09:17:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitBusi(RwdjsqForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		String splc = dao.getShlcID();
		model.setSplc(splc);
		if(flag){
			 shlc.runSubmit(model.getRwdjid(), model.getSplc(), model.getXh(), "gygl_xyzssh.do", "gygl_xyzssq.do");
		}
		return flag;
	}
	
	//��ȡ������
	public String getShlcID() {
		return dao.getShlcID();
	}
	
	//��ȡspid
	public String getSqbh(RwdjsqForm model) {
		return dao.getSqbh(model);
	}
	
	//��ȡ���״̬����
	public String getShztMc(RwdjsqForm model) {
		return dao.getShztMc(model);
	}
	
	/**
	 * 
	 * @����: ������������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-4 ����02:42:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJg(RwdjsqForm model, User user) throws Exception {
		String splc = dao.getShlcID();
		model.setSplc(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
//		boolean flag = dao.saveZsjg(model, user);
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("savesubmit")){
			if (flag) {
				String sqbh = dao.getSqbh(model);
				flag = shlc.runSubmit(sqbh, splc, model.getXh(), "rwdjshbase.do", "rwdjsqbase.do");
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @����: �����޸�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-4 ����02:48:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveUpdateJg(RwdjsqForm model, User user) throws Exception {
		String splc = dao.getShlcID();
		model.setSplc(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
//		boolean flag = dao.saveZsjg(model, user);
		boolean flag = dao.runUpdate(model);
		if(model.getType().equals("savesubmit")){
			if (flag) {
				String sqbh = dao.getSqbh(model);
				flag = shlc.runSubmit(sqbh, splc, model.getXh(), "rwdjshbasel.do", "rwdjsqbase.do");
			}
		}
		return flag;
	}
	
	@Override
	public RwdjsqForm getModel(RwdjsqForm t) throws Exception {
		return dao.getModel(t);
	}
}
