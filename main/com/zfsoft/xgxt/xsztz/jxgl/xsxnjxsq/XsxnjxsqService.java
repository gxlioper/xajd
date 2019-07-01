/**
 * @部门:学工产品事业部
 * @日期：2016-2-1 上午09:03:04 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xsxnjxsq;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsztz.jxgl.xnjxsq.XnjxsqDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-2-1 上午09:03:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxnjxsqService extends SuperServiceImpl<XsxnjxsqForm, XsxnjxsqDao> {
	XsxnjxsqDao dao = new XsxnjxsqDao();
	XnjxsqDao xnjxsqDao = new XnjxsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	
	/** 
	 * @描述:学生校内奖项申请保存
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-1 上午09:14:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSqjg(XsxnjxsqForm model, User user) throws Exception {
		String splc = xnjxsqDao.getShlc();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setShlc(splc);
		model.setSfqq("0");
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				String sqid = dao.getSqid(model);
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "sztz_jxgl_xnjxsh.do", "sztz_jxgl_xsxnjxsq.do");
			}
		}
		return flag;
	} 
	
	
	/**
	 * 
	 * 保存修改结果
	 */
	public boolean saveSqjgUpdate(XsxnjxsqForm model, User user) throws Exception {
		if(model.getType().equals("update")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		model.setSfqq("0");
		boolean flag = dao.runUpdate(model);
		if(model.getType().equals("updatesubmit")){
			if(flag){
				String splc = model.getShlc();
				model.setShlc(splc);
				flag = shlc.runSubmit(model.getId(), model.getShlc(), model.getXh(), "sztz_jxgl_xnjxsh.do", "sztz_jxgl_xsxnjxsq.do");
			}
		}
		return flag;
	}
}
