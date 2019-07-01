/**
 * @部门:学工产品事业部
 * @日期：2016-12-28 上午11:55:02 
 */  
package com.zfsoft.xgxt.rcsw.zyfw.zyfwhz;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：cp[工号：1352]
 * @时间： 2016-12-28 上午11:55:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwForm  extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String qsrq;
	private String jsrq;
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-20 上午09:04:24 
	 * @return		: the qsrq
	 */
	public String getQsrq() {
		return qsrq;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-20 上午09:04:24 
	 * @param 		：qsrq the qsrq to set
	 */
	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-20 上午09:04:24 
	 * @return		: the jsrq
	 */
	public String getJsrq() {
		return jsrq;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-20 上午09:04:24 
	 * @param 		：jsrq the jsrq to set
	 */
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}
	
	
}
