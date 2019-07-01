/**
 * @部门:学工产品事业部
 * @日期：2015-11-3 上午10:58:18 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjxysz;

import org.apache.struts.action.ActionForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者：yxy[工号:1206]
 * @时间： 2015-11-3 上午10:58:18
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjXySzForm extends ActionForm {
	private String id;//ID
	private String mc;//协议名称
	private String content;//协议内容
	private String bjsj;//编辑时间
	private String bjr;//编辑人

	/**
	 * @return the bjr
	 */
	public String getBjr() {
		return bjr;
	}

	/**
	 * @param bjr要设置的 bjr
	 */
	public void setBjr(String bjr) {
		this.bjr = bjr;
	}

	/**
	 * @return the bjsj
	 */
	public String getBjsj() {
		return bjsj;
	}

	/**
	 * @param bjsj要设置的 bjsj
	 */
	public void setBjsj(String bjsj) {
		this.bjsj = bjsj;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content要设置的
	 *            content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id要设置的
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}

	/**
	 * @param mc要设置的
	 *            mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}

}
