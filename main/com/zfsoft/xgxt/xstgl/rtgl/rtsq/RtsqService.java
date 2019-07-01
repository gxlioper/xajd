/**
 * @部门:学工产品事业部
 * @日期：2015-8-6 下午02:21:29 
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
 * @系统名称: 学生工作管理系统
 * @模块名称: 入团管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-6 下午02:21:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RtsqService extends SuperServiceImpl<RtsqForm,RtsqDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	
	//获取社团信息用于社团成员信息维护以及查看
	public HashMap<String, String> getStxxMap(RtsqForm rtsq){
		return dao.getStxxMap(rtsq);
	}
	
	//获取社团成员信息
	public HashMap<String, String> getStxxCyMap(RtsqForm rtsq,User user){
		return dao.getStxxCyMap(rtsq, user);
	}
	
	//提交
	public boolean submitBusi(RtsqForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getRtid(), model.getSplc(), model.getXh(), "stgl_rtgl_rtsh.do", "stgl_rtgl_rtsq.do");
		}
		return flag;
	}
	
	//撤销
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	public List<HashMap<String, String>> getStxmList(RtsqForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		return dao.getStxmList(model, user);
	}
	
	/**
	 * 
	 * 保存增加结果
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
	
	//重复判断
	public boolean checkExistForSave(RtsqForm model) {
		return dao.checkExistForSave(model);
	}
	
	//保存修改结果
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
	
	//学生页面查询
	public List<HashMap<String, String>> getStuRtsqList(RtsqForm model, User user)  throws Exception{
		return dao.getStuRtsqList(model, user);
	}

    public List<HashMap<String,String>> getZdlsInfo(RtsqForm model) {
		return dao.getZdlsInfo(model);
    }
}
