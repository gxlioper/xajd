/**
 * @部门:学工产品事业部
 * @日期：2016-5-6 上午09:47:22 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 毕业还款管理
 * @类功能描述: 参数设置 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-6 上午09:47:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszService extends SuperServiceImpl<ByhkglCsszForm, CsszDao>{
	
	public ByhkglCsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	/**
	 * 
	 * @描述:获取申请审核开关
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-6 上午10:18:00
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
