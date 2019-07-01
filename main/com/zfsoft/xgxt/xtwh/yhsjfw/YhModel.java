/**
 * @部门:学工产品事业部
 * @日期：2014-2-26 上午09:32:21 
 */
package com.zfsoft.xgxt.xtwh.yhsjfw;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称:系统管理
 * @类功能描述:用户表
 * @作者： ligl
 * @时间： 2014-2-26 上午09:32:21
 * @版本： V1.0
 * @修改记录:
 */

public class YhModel extends ActionForm {

	private static final long serialVersionUID = 3371779777190121512L;
	private String type;
	private Pages pages = new Pages();
	private String yhm; // 用户名
	private String xm; // 姓名
	private String kl; // 密码
	private String zdm; // 组代码
	private String szbm; // 所在部门
	private String dwdm; // 单位代码
	private String qx; // 启用
	private String sffz; // 是否分组
	private String sffdy; // 是否辅导员
	private String sfbzr; // 是否班主任

	/**
	 * @描述 ：
	 */
	public YhModel() {
		super();
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getKl() {
		return kl;
	}

	public void setKl(String kl) {
		this.kl = kl;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String getSzbm() {
		return szbm;
	}

	public void setSzbm(String szbm) {
		this.szbm = szbm;
	}

	public String getDwdm() {
		return dwdm;
	}

	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}

	public String getQx() {
		return qx;
	}

	public void setQx(String qx) {
		this.qx = qx;
	}

	public String getSffz() {
		return sffz;
	}

	public void setSffz(String sffz) {
		this.sffz = sffz;
	}

	public String getSffdy() {
		return sffdy;
	}

	public void setSffdy(String sffdy) {
		this.sffdy = sffdy;
	}

	public String getSfbzr() {
		return sfbzr;
	}

	public void setSfbzr(String sfbzr) {
		this.sfbzr = sfbzr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

}
