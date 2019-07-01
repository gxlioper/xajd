/**
 * @部门:学工产品事业部
 * @日期：2016-5-23 下午05:59:20 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优(校外获奖)
 * @类功能描述: 参数设置  
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-23 下午05:59:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszService extends SuperServiceImpl<XwhjCsszForm, CsszDao>{
	
	public XwhjCsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	/**
	 * 
	 * @描述:获取申请审核开关
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-23 下午06:25:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getSqShKg() throws Exception{
		return dao.getSqShKg();
	}
	
	public boolean deleteJcsz() throws Exception{
		return dao.deleteJcsz();
	}
	
}
