/**
 * @部门:学工产品事业部
 * @日期：2016-2-18 下午02:40:57 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.jg;

import java.util.HashMap;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

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
 * @时间： 2016-2-18 下午02:40:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnwxdkJgService extends SuperServiceImpl<XnwxdkJgModel, XnwxdkJgDao> {
	XnwxdkJgDao dao = new XnwxdkJgDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * 保存增加申请
	 */
	public boolean saveDkjg(XnwxdkJgModel model, User user) throws Exception {
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		boolean flag = dao.runInsert(model);
		return flag;
	}
	/**
	 * 
	 * 保存修改申请
	 */
	public boolean saveDkjgUpdate(XnwxdkJgModel model, User user) throws Exception {
		boolean flag = dao.runUpdate(model);
		return flag;
	}
	
	
	/*
	 * 导出申请表的时候获取学生基本信息以及住宿申请信息
	 */
	public HashMap<String, String> getXyzsxxMap(XyzsSqForm model, User user) throws Exception {
		
		HashMap<String, String> xsxxmap = dao.getXyzsxxMap(model, user);
	
		return xsxxmap;
	}
	
	
	//获取金额上限
	public String getJesx() {
		return dao.getJesx();
	}
}
