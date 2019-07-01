/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:12:01 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.jcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-26 下午02:12:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcszService extends SuperServiceImpl<JcszForm,JcszDao>{
	
	public JcszForm getModel() throws Exception{
		return dao.getModel();
	}
	/**
	 * 
	 * @描述:获取申请审核开关状态
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 下午02:54:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getSqShKg() throws Exception{
		return dao.getSqShKg();
	}

}
