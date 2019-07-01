/**
 * @部门:学工产品事业部
 * @日期：2016-6-17 下午01:37:57 
 */  
package com.zfsoft.xgxt.zjly.zhf.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 综合分参数设置(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-17 下午01:37:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfCsszService extends SuperServiceImpl<ZhfCsszForm, ZhfCsszDao>{
	/** 
	 * @描述:获取开关(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：982]
	 * @日期：2016-6-17 下午02:02:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * ZhfCsszForm 返回类型 
	 * @throws 
	 */
	public ZhfCsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	/** 
	 * @描述:删除(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：982]
	 * @日期：2016-6-17 下午02:02:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean deleteJcsz() throws Exception{
		return dao.deleteJcsz();
	}
}
