/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 下午05:43:29 
 */  
package com.zfsoft.xgxt.rcsw.xbzj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: (学生系部支教管理--潍坊学院) 
 * @作者： cmj [工号：913]
 * @时间： 2013-8-5 下午05:43:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XbzjService extends SuperServiceImpl<XbzjForm, XbzjDAO> {
	private XbzjDAO dao=new XbzjDAO();
	
	public XbzjService(){
		super.setDao(dao);
	}

	/** 
	 * @描述:(判断该学年学期该学生是否已是西部支教学生)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-5 下午06:48:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(XbzjForm model) {
		return dao.isExist(model);
	}

}
