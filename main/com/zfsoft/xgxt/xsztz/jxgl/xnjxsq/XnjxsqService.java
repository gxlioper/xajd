/**
 * @部门:学工产品事业部
 * @日期：2016-1-25 下午06:09:25 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xnjxsq;

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
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-1-25 下午06:09:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnjxsqService extends SuperServiceImpl<XnjxsqForm, XnjxsqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private XnjxsqDao dao = new XnjxsqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	public XnjxsqService() {
		super.setDao(dao);	
	}
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-26 上午11:08:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYiShen(String xh){
		return dao.getYiShen(xh);
	}
	
	/**
	 * 
	 * 保存增加结果
	 */
	public boolean saveSqjg(XnjxsqForm model, User user) throws Exception {
		String splc = dao.getShlc();
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
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "sztz_jxgl_xnjxsh.do", "sztz_jxgl_xnjxsq.do");
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * 保存修改结果
	 */
	public boolean saveSqjgUpdate(XnjxsqForm model, User user) throws Exception {
		if(model.getType().equals("update")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		model.setSfqq("0");
		boolean flag = dao.runUpdate(model);
		if(model.getType().equals("updatesubmit")){
			if(flag){
				String splc = dao.getShlc();
				model.setShlc(splc);
				flag = shlc.runSubmit(model.getId(), model.getShlc(), model.getXh(), "sztz_jxgl_xnjxsh.do", "sztz_jxgl_xnjxsq.do");
			}
		}
		return flag;
	}
	
	//得到审核流程
	public String getShlc(){
		return dao.getShlc();
	}
	
	//撤销
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	//提交
	public boolean submitBusi(XnjxsqForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		String splc = dao.getShlc();
		model.setShlc(splc);
		boolean flag = dao.runUpdate(model);		
		if(flag){
			 shlc.runSubmit(model.getId(), model.getShlc(), model.getXh(), "sztz_jxgl_xnjxsh.do", "sztz_jxgl_xnjxsq.do");
		}
		return flag;
	}
}
