/**
 * @部门:学工产品事业部
 * @日期：2016-10-26 上午09:33:58 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务_考情管理_管理模块
 * @类功能描述:  参数设置Dao
 * @作者： cq [工号:785]
 * @时间： 2016-10-26 上午09:33:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqCsszService extends SuperServiceImpl<KqCsszForm, KqCsszDao> {
	
	public KqCsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	
	/**
	 * 
	 * @描述:获取申请审核开关状态
	 * @作者：cq [工号：785]
	 * @日期：2016-10-26 上午09:41:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getSqShKg() throws Exception{
		return dao.getSqShKg();
	}

}
