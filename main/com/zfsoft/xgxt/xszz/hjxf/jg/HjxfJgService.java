/**
 * @部门:学工产品事业部
 * @日期：2016-3-15 上午11:51:44 
 */  
package com.zfsoft.xgxt.xszz.hjxf.jg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xszz.hjxf.sq.HjxfSqDao;
import com.zfsoft.xgxt.xszz.hjxf.sq.HjxfSqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-3-15 上午11:51:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjxfJgService extends SuperServiceImpl<HjxfJgForm, HjxfJgDao> {
	HjxfJgDao dao = new HjxfJgDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	/**
	 * 
	 * 保存增加申请
	 */
	public boolean saveHjxf(HjxfJgForm model, User user) throws Exception {
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setCzy(user.getUserName());
		boolean flag = dao.runInsert(model);
		return flag;
	}
	/**
	 * 
	 * 保存修改申请
	 */
	public boolean saveHjxfUpdate(HjxfJgForm model, User user) throws Exception {
		model.setCzy(user.getUserName());
		boolean flag = dao.runUpdate(model);
		return flag;
	}
	
	public String getKnsdj(HjxfJgForm model){
		return dao.getKnsdj(model);
	}
	
	public boolean delDkjg(String xh,String xn,String xq)throws Exception{
		return dao.delDkjg(xh, xn, xq);
	}
	
	public List<HashMap<String,String>> getJtknXshjList(String xn ,User user ){
		return dao.getJtknXshjList(xn, user);
	}
	
	//数据汇总
	public HashMap<String, String> getSumHz(String xn,User user){
		return dao.getSumHz(xn, user);
	}
	
	public HashMap<String, String> getJqNdYf(String jqjzsj){
		return dao.getJqNdYf(jqjzsj);
	}
	
	public boolean isNotExists(HjxfJgForm utilform){
		return dao.isNotExists(utilform);
	}
}
