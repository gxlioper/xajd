/**
 * @部门:学工产品事业部
 * @日期：2015-1-22 上午10:49:29 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.xsyybxxx;


import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 保险管理-学生预约报销信息 
 * @作者： 沈晓波 [工号:1123]
 * @时间： 2015-1-22 上午10:49:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsyybxxxService extends SuperServiceImpl<XsyybxxxForm, XsyybxxxDao> {
	
	public XsyybxxxService(){
		setDao(new XsyybxxxDao());
	}
	
}
