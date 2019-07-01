/**
 * @部门:学工产品事业部
 * @日期：2013-8-26 上午10:20:27 
 */  
package com.zfsoft.xgxt.comm.browser;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2013-8-26 上午10:20:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BrowserForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String content;
	private String param;
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content要设置的 content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param param要设置的 param
	 */
	public void setParam(String param) {
		this.param = param;
	}
}
