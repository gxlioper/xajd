/**
 * @部门:学工产品事业部
 * @日期：2017-8-4 上午10:19:15 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqjl;

import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2017-8-4 上午10:19:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class kqjlSercice extends SuperServiceImpl<kqjlForm, kqjlDao> {
private kqjlDao dao = new kqjlDao();
	
	@SuppressWarnings("deprecation")
	public kqjlSercice(){
		super.setDao(dao);
	}

	/**
	 * @param kqsj  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2017-8-7 下午04:59:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws 
	 */
	public Map<String, String> getKqjl(String xh, String kqsj) {
		// TODO 自动生成方法存根
		return dao.getKqjl(xh,kqsj);
	}
	
	
	
	
	
	
}
