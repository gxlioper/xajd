/**
 * @部门:学工产品事业部
 * @日期：2015-7-10 下午01:48:34 
 */  
package com.zfsoft.xgxt.xsztz.tzxmsq;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-7-10 下午01:48:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsXmSqService extends SuperServiceImpl<XsXmSqForm, XsXmSqDao> {
	XsXmSqDao dao = new XsXmSqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @描述:获取申请审核开关
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-10 下午04:29:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getSqShKg() throws Exception{
		return dao.getSqShKg();
	}
	
	/**
	 * 
	 * @描述:重复判断
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-10 下午04:37:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkExistForSave(XsXmSqForm model) {
		return dao.checkExistForSave(model);
	}
	
	//重复判断2     新修改提交或者勾选提交时重复判断，判断依旧是结果表中是否有数据。
	public boolean checkExistForSave2(XsXmSqForm model) {
		return dao.checkExistForSave2(model);
	}
	
	public List<HashMap<String, String>> getXmSelectList(String xh,String flag) throws Exception {
		return dao.getXmlist(xh);
	}
	
	/**
	 * 
	 * 保存增加结果
	 */
	public boolean saveSqjg(XsXmSqForm model, User user) throws Exception {
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
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "sztz_xmsqgl_xmsh.do", "sztz_xmsqgl_xmsq.do");
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:保存修改结果
	 * @作者：张昌路[工号：1206]
	 * @日期：2015-7-15 下午04:09:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSqjgUpdate(XsXmSqForm model, User user) throws Exception {
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
				flag = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "sztz_xmsqgl_xmsh.do", "sztz_xmsqgl_xmsq.do");
			}
		}
		return flag;
	}
	
	//提交
	public boolean submitBusi(XsXmSqForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		String splc = model.getSplc();
		model.setSplc(splc);
		if(flag){
			 shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "sztz_xmsqgl_xmsh.do", "sztz_xmsqgl_xmsq.do");
		}
		return flag;
	}
	
	//撤销
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	//活动信息
	public HashMap<String, String> getHdMap(String xmdm,String xn,String xq){
		return dao.getHdMap(xmdm,xn,xq);
	}
}
