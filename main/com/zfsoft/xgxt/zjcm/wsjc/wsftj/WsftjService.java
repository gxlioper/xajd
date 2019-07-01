/**
 * @部门:学工产品事业部
 * @日期：2016-3-11 下午02:00:16 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsftj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-11 下午02:00:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsftjService extends SuperServiceImpl<WsftjForm, WsftjDao>{
	private WsftjDao dao = new WsftjDao();
	
	public boolean sfkcx(WsftjForm t){
		return dao.sfkcx(t);
	}
	
	
}
