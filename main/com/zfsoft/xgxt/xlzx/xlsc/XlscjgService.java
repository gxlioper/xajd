/**
 * @部门:学工产品事业部
 * @日期：2016-12-21 上午11:51:52 
 */  
package com.zfsoft.xgxt.xlzx.xlsc;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 心理筛查方法类
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2016-12-21 上午11:51:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlscjgService extends SuperServiceImpl<XlscjgForm,XlscjgDao>{
	private XlscjgDao dao = new XlscjgDao();
	
	/**
	 * @描述: 保存唯一性判断
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-12-21 下午04:50:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public boolean uniqueness(XlscjgForm model) 
		throws Exception {
		String num = dao.checkForSave(model);
		return Integer.valueOf(num) > 0;
	}
}
