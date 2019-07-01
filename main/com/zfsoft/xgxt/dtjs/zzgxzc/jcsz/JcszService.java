/**
 * @部门:学工产品事业部
 * @日期：2017年1月25日 上午9:04:18 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.jcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设组织关系转出管理模块
 * @类功能描述: 基础设置Service
 * @作者： xuwen[工号:1426]
 * @时间： 2017年1月25日 上午9:04:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcszService extends SuperServiceImpl<JcszForm, JcszDao>{

	/**
	 * @throws Exception  
	 * @描述:获取基础设置信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月25日 上午10:37:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * JcszForm 返回类型 
	 * @throws 
	 */
	public JcszForm getModel() throws Exception {
		return dao.getModel();
	}

}
