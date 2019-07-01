/**
 * @部门:学工产品事业部
 * @日期：2014-9-11 下午02:13:30 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-9-11 下午02:13:30 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JJgzService extends SuperServiceImpl<JJgzForm, JJgzDao> {

	/*
    描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runInsert(java.lang.Object)
*/

	@Override
	public boolean runInsert(JJgzForm t) throws Exception {
		return dao.runInsert(t);
	}
	
	/*
	    描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runUpdate(java.lang.Object)
	*/
	
	@Override
	public boolean runUpdate(JJgzForm t) throws Exception {
		return dao.runUpdate(t);
	}
}
