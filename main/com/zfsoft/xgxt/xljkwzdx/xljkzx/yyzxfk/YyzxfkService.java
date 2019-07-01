/**
 * @部门:学工产品事业部
 * @日期：2014-4-29 下午03:36:49 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.yyzxfk;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询 -预约咨询反馈
 * @类功能描述: 
 * @作者：王志刚[工号:1060]
 * @时间： 2014-4-29 下午03:36:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YyzxfkService extends SuperServiceImpl<YyzxfkForm, YyzxfkDao>{

	public YyzxfkService() {
		super.setDao(new YyzxfkDao());
	}
}
