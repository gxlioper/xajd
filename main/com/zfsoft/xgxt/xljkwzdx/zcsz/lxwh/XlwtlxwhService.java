/**
 * @部门:学工产品事业部
 * @日期：2014-4-23 下午03:51:57 
 */  
package com.zfsoft.xgxt.xljkwzdx.zcsz.lxwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理健康-基础设置-类型维护-心理问题类型
 * @类功能描述: 
 * @作者：王志刚[工号:1060]
 * @时间： 2014-4-23 下午03:51:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlwtlxwhService extends SuperServiceImpl<XlwtlxwhForm, XlwtlxwhDao>{

	public XlwtlxwhService() {
		super.setDao(new XlwtlxwhDao());
	}
	
	/** 
	 * @描述:(心理问题类型代码是否存在)
	 * @作者：王志刚 [工号：1060]
	 * @日期：2014-4-24 下午06:49:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 */
	public boolean xlwtlxIsExist(XlwtlxwhForm model) {
		return dao.xlwtlxIsExist(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:获取全部心理问题
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-3 下午04:05:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllXlwtList() throws Exception{
		return dao.getAllList(new XlwtlxwhForm());
	}
	
}
