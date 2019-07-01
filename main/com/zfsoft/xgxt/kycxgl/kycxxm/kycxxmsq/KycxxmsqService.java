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
	 * 是否已存在
	 */
	public boolean checkExistSave(KycxxmsqForm model, User user) throws Exception {
		return dao.checkExistSave(model, user);
	}
	/**
	 * 增加科研创新项目申请
	 */
	public boolean insertKycxxmsq(KycxxmsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			model.setShzt(Constants.YW_WTJ);//未提交
		}
		// 获取审批流程
		String splc = dao.getShlcID(model.getLbdm());
		model.setSplc(splc);
		boolean result = super.runInsert(model);
		//保存项目成员
		if(result){
			result = new KycxxmjgDao().insertKycxxmcy(model.getSqid(),model.getXhArr());
		}
		if (result && SUBMIT.equals(model.getType())) {
			String sqURL = "kycxgl_kycxxm_kycxxmsq.do";
			String shURL = "kycxgl_kycxxm_kycxxmsh.do";
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(),splc,model.getCzr(),shURL,sqURL);
		}
		return result;
	}
	/**
	 * 修改科研创新项目申请
	 */
	public boolean updateKycxxmsq(KycxxmsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equals(model.getShzt())){
			// 获取新审批流程
			model.setSplc(dao.getShlcID(model.getLbdm()));
		}
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}
		boolean result = super.runUpdate(model);
		//保存项目成员
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
	 * 提交科研创新项目申请
	 */
	public boolean submitKycxxmsq(KycxxmsqForm model) throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取新审批流程
			model.setSplc(dao.getShlcID(model.getLbdm()));
		}
		model.setShzt(Constants.YW_SHZ);
		boolean result = dao.updateKycxxmsqShzt(model);
		if(result){
			String sqURL = "kycxgl_kycxxm_kycxxmsq.do";
			String shURL = "kycxgl_kycxxm_kycxxmsh.do";
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getCzr(),shURL,sqURL);
		}
		return result;
	}
	/**
	 * 撤销科研创新项目申请
	 */
	public boolean cancelKycxxmsq(KycxxmsqForm model) throws Exception {
		return dao.updateKycxxmsqShzt(model);
	}
	/**
	 * 只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
}
