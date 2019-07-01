package com.zfsoft.xgxt.rcsw.txhd.xmxxsq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

public class XmxxsqService extends SuperServiceImpl<XmxxsqForm, XmxxsqDao> {

	private XmxxsqDao dao = new XmxxsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";

	public XmxxsqService() {
		super.setDao(dao);
	}
	
	/**
	 * @描述:增加项目申请
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveXmxxsq(XmxxsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			model.setShzt(Constants.YW_WTJ);//未提交
		}
		// 获取审批流程
		String splc = dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		if(SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(),splc,model.getSqr(),"rcsw_txhd_xmxxsh.do","rcsw_txhd_xmxxsq.do");
		}
		return result;
	}
	
	/**
	 * @描述:修改项目申请
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXmxxsq(XmxxsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取新审批流程
			model.setSplc(dao.getShlcID());
		}
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}
		
		boolean insertResult = super.runUpdate(model);
		boolean result = true;
		if (insertResult && SUBMIT.equals(model.getType())) {
			shlc.deleteShjl(model.getSqid());
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"rcsw_txhd_xmxxsh.do","rcsw_txhd_xmxxsq.do");
		}
		return insertResult && result;
	}
	
	/**
	 * @描述:提交项目申请
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitXmxxsq(XmxxsqForm model) throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取新审批流程
			model.setSplc(dao.getShlcID());
		}
		
		model.setShzt(Constants.YW_SHZ);
		boolean resultXmxxsq = dao.updateXmxxsq(model);
		boolean result = false;
		if(resultXmxxsq){
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"rcsw_txhd_xmxxsh.do","rcsw_txhd_xmxxsq.do");
		}
		return result;
	}
	
	/**
	 * @描述:更新项目申请
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelXmxxsq(XmxxsqForm model) throws Exception {
		boolean resultXmxxsq = dao.updateXmxxsq(model);
		return resultXmxxsq;
	}
	
	/**
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	/**
	 * @描述:是否已经存在
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean isExist(XmxxsqForm model) throws Exception {
		boolean flag = false;
		String num = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
	
}
