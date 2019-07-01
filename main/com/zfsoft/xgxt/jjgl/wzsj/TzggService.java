/**
 * @部门:学工产品事业部
 * @日期：2014-9-11 下午02:12:06 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-9-11 下午02:12:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TzggService extends SuperServiceImpl<TzggForm, TzggDao> {
	
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runInsert(java.lang.Object)
	 */
	
	@Override
	public boolean runInsert(TzggForm t) throws Exception {
		return dao.runInsert(t);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runUpdate(java.lang.Object)
	 */
	
	@Override
	public boolean runUpdate(TzggForm t) throws Exception {
		return dao.runUpdate(t);
	}

	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：zxb[工号：1036]
	 * @日期：2014-9-12 下午02:19:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getModelMap(TzggForm t) throws Exception{
		return dao.getModelMap(t);
	}
}
