/**
 * @部门:学工产品事业部
 * @日期：2016-4-18 下午02:14:57 
 */  
package com.zfsoft.xgxt.gygl.lkxxgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-4-18 下午02:14:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LkxxService extends SuperServiceImpl<LkxxForm, LkxxDao>{
	private LkxxDao dao = new LkxxDao();
	
	/** 
	 * @描述:判断当天是否有住宿记录
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-18 下午03:55:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isHaveRecord(LkxxForm t){
		return dao.isHaveRecord(t);
	}
}
