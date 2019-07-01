/**
 * @部门:学工产品事业部
 * @日期：2014-6-3 下午01:30:48 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.xlfdjl;


import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-6-3 下午01:30:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlfdjlService extends SuperServiceImpl<XlfdjlForm, XlfdjlDao> {

	/**
	 * 
	 * @描述:根据学号查询学生心理信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-5 下午02:12:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> xlfxxsxx(String fdid) throws Exception{
		return dao.xlfxxsxx(fdid);
	}

	public XlfdjlService(){
		setDao(new XlfdjlDao());
	}
}
