/**
 * @部门:学工产品事业部
 * @日期：2016-2-18 下午03:51:56 
 */  
package com.zfsoft.xgxt.zxdk.xnwxjkhk;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-2-18 下午03:51:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnwxjkhkService extends SuperServiceImpl<XnwxjkhkForm, XnwxjkhkDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	XnwxjkhkDao dao = new XnwxjkhkDao();
	
	/** 
	 * @描述:判断是否有记录
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-25 下午04:22:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isHaveRecord(XnwxjkhkForm form){
		return dao.isHaveRecord(form);
	}
	
	public String getXqmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
		
}
