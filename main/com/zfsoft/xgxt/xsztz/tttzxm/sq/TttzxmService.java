/**
 * @部门:学工产品事业部
 * @日期：2016-7-22 上午10:43:02 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.sq;

import org.apache.poi.sl.draw.geom.Guide;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsztz.tttzxm.comm.CommTtxmService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-22 上午10:43:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TttzxmService extends SuperServiceImpl<TttzxmForm, TttzxmDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	private CommTtxmService commService = new CommTtxmService();

	/**
	 * 
	 * @描述:保存团体申请（增加）
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午03:59:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveTtsq(TttzxmForm model, User user) throws Exception {
		String splc = dao.getShlcID(model.getXmdm());
		model.setSplc(splc);
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		String ttsqid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setTtsqid(ttsqid);
		boolean flag = commService.saveTtcy(ttsqid, model.getXmdm(), model.getXhArr());
		if(flag){
			flag = dao.runInsert(model);
		}
		
		if(model.getType().equals("submit")){
			if (flag) {
				flag = shlc.runSubmit(ttsqid, splc, model.getSqr(), "sztz_ttxm_sh.do", "sztz_ttxm_sq.do");
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:保存团体申请（修改）
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午04:00:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveTtsqUpdate(TttzxmForm model, User user) throws Exception {
		String splc = dao.getShlcID(model.getXmdm());
		model.setSplc(splc);
		if(model.getType().equals("update")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = commService.saveTtcy(model.getTtsqid(), model.getXmdm(), model.getXhArr());
		if(flag){
			flag = dao.runUpdate(model);
		}
		if(model.getType().equals("updatesubmit")){
			if(flag){
				flag = shlc.runSubmit(model.getTtsqid(), model.getSplc(),model.getSqr(), "sztz_ttxm_sh.do", "sztz_ttxm_sq.do");
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:提交
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午04:32:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitBusi(TttzxmForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		String splc =  dao.getShlcID(model.getXmdm());
		model.setSplc(splc);
		if(flag){
			 shlc.runSubmit(model.getTtsqid(), model.getSplc(), model.getSqr(), "sztz_ttxm_sh.do", "sztz_ttxm_sq.do");
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午04:35:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
}
