/**
 * @部门:学工产品事业部
 * @日期：2016-02-16 下午02:21:29 
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
 * @系统名称: 学生工作管理系统
 * @模块名称: 入团管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2016-02-16 下午02:21:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YstRtsqService extends SuperServiceImpl<YstRtsqForm,YstRtsqDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	
	public HashMap<String, String> getYstxxMap(YstRtsqForm t){
		return dao.getYstxxMap(t);
	}
	
	//获取社团成员信息
	public HashMap<String, String> getStxxCyMap(YstRtsqForm YstRtsq,User user){
		return dao.getStxxCyMap(YstRtsq, user);
	}
	
	//提交
	public boolean submitBusi(YstRtsqForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getRtid(), model.getSplc(), model.getXh(), "ystgl_rtgl_rtsh.do", "ystgl_rtgl_rtsq.do");
		}
		return flag;
	}
	
	//撤销
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	public List<HashMap<String, String>> getStxmList(YstRtsqForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		return dao.getStxmList(model, user);
	}
	
	/**
	 * 
	 * 保存申请
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
	
	//重复判断
	public boolean checkExistForSave(YstRtsqForm model) {
		return dao.checkExistForSave(model);
	}
	
	//保存修改结果
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
	
	//学生页面查询
	public List<HashMap<String, String>> getStuYstRtsqList(YstRtsqForm model, User user)  throws Exception{
		return dao.getStuYstRtsqList(model, user);
	}
}
