/**
 * @部门:学工产品事业部
 * @日期：2016-2-24 下午02:53:05 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdkjm.jg;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.sq.XnwxdkjmsqDao;
import com.zfsoft.xgxt.zxdk.xnwxdkjm.sq.XnwxdkjmsqModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-2-24 下午02:53:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnwxdkjmJgService extends SuperServiceImpl<XnwxdkjmJgModel, XnwxdkjmJgDao> {
	XnwxdkjmJgDao dao = new XnwxdkjmJgDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * 保存增加申请
	 */
	public boolean saveDksq(XnwxdkjmJgModel model, User user) throws Exception {
		String splc = dao.getShlcID();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		boolean flag = dao.runInsert(model);
		return flag;
	}
	/**
	 * 
	 * 保存修改申请
	 */
	public boolean saveDksqUpdate(XnwxdkjmJgModel model, User user) throws Exception {
		boolean flag = dao.runUpdate(model);
		return flag;
	}
}
