/**
 * @部门:学工产品事业部
 * @日期：2016-3-17 上午09:26:46 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-17 上午09:26:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XqflCsszService extends SuperServiceImpl<XqflCsszForm, XqflCsszDao>{
	private XqflCsszDao dao = new XqflCsszDao();
	
	public XqflCsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	/** 
	 * @描述:获取申请审核开关
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-17 上午09:29:25
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
