/**
 * @部门:学工产品事业部
 * @日期：2016-6-27 上午09:55:38 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 综合分管理模块
 * @类功能描述: 项目维护(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-27 上午09:55:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfXmmkwhService extends SuperServiceImpl<ZhfXmmkwhForm, ZhfXmmkwhDao>{
	private ZhfXmmkwhDao dao = new ZhfXmmkwhDao();
	
	/** 
	 * @描述:验证是否存在(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-27 上午10:00:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(ZhfXmmkwhForm t){
		return dao.count(t)>0;
	}
	
	/** 
	 * @描述:是否能删除项目模块(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-27 下午03:29:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmmkdms
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isCanDel(String[] xmmkdms){
		return dao.countJfxm(xmmkdms)<1;
	}
	
}
