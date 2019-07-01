/**
 * @部门:学工产品事业部
 * @日期：2014年6月11日 下午3:18:32 
 */  
package com.zfsoft.xgxt.base.service;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 申请审核
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014年6月11日 下午3:18:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public abstract class SuperAuditService<T extends SuperAuditModel, D extends SuperDAOImpl<T>> extends SuperServiceImpl<T,D>{
	/**
	 * 
	 * @描述: 最后一级审核通过后操作（一般用于插入结果库）
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月10日 下午2:13:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public abstract boolean afterLastAudit(T model);
	
	
	
	/**
	 * 
	 * @描述: 取消审核--删除结果库操作
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月10日 下午2:32:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public abstract boolean deleteResult(T model);
	
	
	
	
}
