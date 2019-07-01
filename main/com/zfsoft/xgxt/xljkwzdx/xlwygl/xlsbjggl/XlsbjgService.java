/**
 * @部门:学工产品事业部
 * @日期：2014-5-30 下午05:23:21 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xlsbjggl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-30 下午05:23:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlsbjgService extends SuperServiceImpl<XlsbjgForm, XlsbjgDao> {

	public XlsbjgService(){
		setDao(new XlsbjgDao());
	}
	
}
