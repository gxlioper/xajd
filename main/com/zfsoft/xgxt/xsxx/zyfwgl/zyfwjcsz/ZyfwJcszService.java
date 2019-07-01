/**
 * @部门:学工产品事业部
 * @日期：2017年5月4日 上午10:24:32 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 志愿服务管理模块
 * @类功能描述: 志愿服务基础设置Service
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月4日 上午10:24:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwJcszService extends SuperServiceImpl<ZyfwJcszForm,ZyfwJcszDao>{

	/**
	 * @描述:获取基础设置信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月4日 下午2:02:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ZyfwJcszForm 返回类型 
	 * @throws Exception
	 */
	public ZyfwJcszForm getModel() throws Exception {
		
		return dao.getModel();
	}

	/** 
	 * @描述:获取申请审核开关状态
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月4日 下午5:14:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String [] 返回类型 
	 * @throws Exception
	 */
	public String[] getSqShKg() throws Exception{
		
		return dao.getSqShKg();
	}
	
}
