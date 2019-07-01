package com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmsq;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmjg.KycxxmjgDao;

public class KycxxmsqService extends SuperServiceImpl<KycxxmsqForm, KycxxmsqDao> {

	private KycxxmsqDao dao = new KycxxmsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";

	public KycxxmsqService() {
		super.setDao(dao);
	}
	/**
	 * �Ƿ��Ѵ���
	 */
	public boolean checkExistSave(KycxxmsqForm model, User user) throws Exception {
		return dao.checkExistSave(model, user);
	}
	/**
	 * ���ӿ��д�����Ŀ����
	 */
	public boolean insertKycxxmsq(KycxxmsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		// ��ȡ��������
		String splc = dao.getShlcID(model.getLbdm());
		model.setSplc(splc);
		boolean result = super.runInsert(model);
		//������Ŀ��Ա
		if(result){
			result = new KycxxmjgDao().insertKycxxmcy(model.getSqid(),model.getXhArr());
		}
		if (result && SUBMIT.equals(model.getType())) {
			String sqURL = "kycxgl_kycxxm_kycxxmsq.do";
			String shURL = "kycxgl_kycxxm_kycxxmsh.do";
			//�����������
			result = shlc.runSubmit(model.getSqid(),splc,model.getCzr(),shURL,sqURL);
		}
		return result;
	}
	/**
	 * �޸Ŀ��д�����Ŀ����
	 */
	public boolean updateKycxxmsq(KycxxmsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equals(model.getShzt())){
			// ��ȡ����������
			model.setSplc(dao.getShlcID(model.getLbdm()));
		}
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}
		boolean result = super.runUpdate(model);
		//������Ŀ��Ա
		if(result){
			result = new KycxxmjgDao().insertKycxxmcy(model.getSqid(),model.getXhArr());
		}
		
		if (result && SUBMIT.equals(model.getType())) {
			String sqURL = "kycxgl_kycxxm_kycxxmsq.do";
			String shURL = "kycxgl_kycxxm_kycxxmsh.do";
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getCzr(),shURL,sqURL);
		}
		return result;
	}
	/**
	 * �ύ���д�����Ŀ����
	 */
	public boolean submitKycxxmsq(KycxxmsqForm model) throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// ��ȡ����������
			model.setSplc(dao.getShlcID(model.getLbdm()));
		}
		model.setShzt(Constants.YW_SHZ);
		boolean result = dao.updateKycxxmsqShzt(model);
		if(result){
			String sqURL = "kycxgl_kycxxm_kycxxmsq.do";
			String shURL = "kycxgl_kycxxm_kycxxmsh.do";
			//�����������
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getCzr(),shURL,sqURL);
		}
		return result;
	}
	/**
	 * �������д�����Ŀ����
	 */
	public boolean cancelKycxxmsq(KycxxmsqForm model) throws Exception {
		return dao.updateKycxxmsqShzt(model);
	}
	/**
	 * ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
}
