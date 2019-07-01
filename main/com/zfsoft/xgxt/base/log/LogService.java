/**
 * @部门:学工产品事业部
 * @日期：2014-9-9 下午02:49:03 
 */  
package com.zfsoft.xgxt.base.log;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-9-9 下午02:49:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LogService extends SuperServiceImpl<LogInfo, LogDao> {
	
	/**
	 * 
	 * @描述:统计登录次数单独拷贝登录日志
	 * @作者：taogj[工号：1075]
	 * @日期：2017-9-12 下午03:22:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertDllog() throws Exception{
		return dao.insertDllog();
	}
}
