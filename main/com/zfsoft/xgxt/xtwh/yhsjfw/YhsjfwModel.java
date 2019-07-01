/**
 * @部门:学工产品事业部
 * @日期： 2014-2-26 上午09:27:02 
 */
package com.zfsoft.xgxt.xtwh.yhsjfw;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 系统管理
 * @类功能描述: 用户数据范围
 * @作者： ligl
 * @时间： 2014-2-26 上午09:27:02
 * @版本： V1.0
 * @修改记录:
 */
public class YhsjfwModel extends ActionForm {

	private static final long serialVersionUID = 2766816538055718857L;
	private String yhm;// 类别代码
	private String sjfwdm;// 数据范围代码
	private String sjfwmc;// 数据范围名称

	private String ids;//

	public YhsjfwModel() {
		super();
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getSjfwdm() {
		return sjfwdm;
	}

	public void setSjfwdm(String sjfwdm) {
		this.sjfwdm = sjfwdm;
	}

	public String getSjfwmc() {
		return sjfwmc;
	}

	public void setSjfwmc(String sjfwmc) {
		this.sjfwmc = sjfwmc;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
