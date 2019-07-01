/**
 * @部门:学工产品事业部
 * @日期：2013-7-5 下午02:38:29 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:辅导员培训项目维护
 * @作者： zhangjw
 * @时间： 2013-7-5 下午02:35:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class FdypxXmwhService extends SuperServiceImpl<FdypxXmwhForm,FdypxXmwhDAO>{

	private FdypxXmwhDAO dao = new FdypxXmwhDAO();


	public FdypxXmwhService() {
		// TODO 自动生成方法存根
		super.setDao(dao);
	}
}
