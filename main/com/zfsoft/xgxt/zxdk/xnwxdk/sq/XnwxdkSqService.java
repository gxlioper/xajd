/**
 * @部门:学工产品事业部
 * @日期：2016-2-18 下午02:35:00 
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
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-2-18 下午02:35:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnwxdkSqService extends SuperServiceImpl<XnwxdkSqModel, XnwxdkSqDao> {
	XnwxdkSqDao dao = new XnwxdkSqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * 保存增加申请
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
	 * 保存修改申请
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
	
	//提交
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
	
	//撤销
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/*
	 * 导出申请表的时候获取学生基本信息以及住宿申请信息
	 */
	public HashMap<String, String> getXyzsxxMap(XyzsSqForm model, User user) throws Exception {
		
		HashMap<String, String> xsxxmap = dao.getXyzsxxMap(model, user);
	
		return xsxxmap;
	}
	
	/**
	 *获取学历代码和学历名称map,给下拉框使用
     *
	 */
	public List<HashMap<String, String>> getXl(XyzsSqForm model) {
		return dao.getXl(model);
	}
	
	/**
	 * 查看时显示学历名称
	 */
	public HashMap<String, String> getXlCk(XyzsSqForm model) {
		return dao.getXlCk(model);
	}
	
	/**
	 * 在外居住原因
	 */
	public HashMap<String, String> getXyZsyy(XyzsSqForm model) {
		return dao.getXyZsyy(model);
	}
	
	//获取审核状态名称
	public String getShztMc(XyzsSqForm model){
		return dao.getShztMc(model);
    }
	
	//获取金额上限
	public String getJesx() {
		return dao.getJesx();
	}
}
