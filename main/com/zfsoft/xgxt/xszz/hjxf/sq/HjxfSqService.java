/**
 * @部门:学工产品事业部
 * @日期：2016-3-15 上午10:53:00 
 */  
package com.zfsoft.xgxt.xszz.hjxf.sq;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqDao;
import com.zfsoft.xgxt.zxdk.xnwxdk.sq.XnwxdkSqModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-3-15 上午10:53:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjxfSqService extends SuperServiceImpl<HjxfSqForm, HjxfSqDao> {
	HjxfSqDao dao = new HjxfSqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * 保存增加申请
	 */
	public boolean saveHjxf(HjxfSqForm model, User user) throws Exception {
		String splc = dao.getShlcID();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplcid(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				String sqid = dao.getSqbh(model);
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "xszz_hjxf_sh.do", "xszz_hjxf_sq.do");
			}
		}
		return flag;
	}
	/**
	 * 
	 * 保存修改申请
	 */
	public boolean saveHjxfUpdate(HjxfSqForm model, User user) throws Exception {
		if(model.getType().equals("update")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runUpdate(model);
		if(model.getType().equals("updatesubmit")){
			if(flag){
				String splc = dao.getShlcID();
				model.setSplcid(splc);
				flag = shlc.runSubmit(model.getHjxfid(), model.getSplcid(), model.getXh(), "xszz_hjxf_sh.do", "xszz_hjxf_sq.do");
			}
		}
		return flag;
	}
	
	//提交
	public boolean submitBusi(HjxfSqForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		String splc = dao.getShlcID();
		model.setSplcid(splc);
		if(flag){
			 shlc.runSubmit(model.getHjxfid(), model.getSplcid(), model.getXh(), "xszz_hjxf_sh.do", "xszz_hjxf_sq.do");
		}
		return flag;
	}
	
	//撤销
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
 
	public String getKnsdj(HjxfSqForm model){
		return dao.getKnsdj(model);
		
	}
}
