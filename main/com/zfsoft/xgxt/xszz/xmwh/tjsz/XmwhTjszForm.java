/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.tjsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 项目维护-条件设置
 * @类功能描述:
 * @作者： ligl
 * @时间： 2013-4-18 下午06:33:00
 * @版本： V1.0
 * @修改记录:
 */
public class XmwhTjszForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String tjdm;// 条件代码
	private String xmdm;// 资助项目代码
	private String tjgx;// 条件关系
	private String tjz;// 条件值
	private String xn;// 限制学年
	private String xq;// 限制学期
	private String bjdl;// 限制范围(班级大类)
	private String sfqy;// 是否启用(是:1,否:0)
	private String yyfw;//条件应用范围 （班级大类or特殊群体）
	public XmwhTjszForm() {
		super();
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

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getTjdm() {
		return tjdm;
	}

	public void setTjdm(String tjdm) {
		this.tjdm = tjdm;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getTjgx() {
		return tjgx;
	}

	public void setTjgx(String tjgx) {
		this.tjgx = tjgx;
	}

	public String getTjz() {
		return tjz;
	}

	public void setTjz(String tjz) {
		this.tjz = tjz;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getBjdl() {
		return bjdl;
	}

	public void setBjdl(String bjdl) {
		this.bjdl = bjdl;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	/**
	 * @return the yyfw
	 */
	public String getYyfw() {
		return yyfw;
	}

	/**
	 * @param yyfw要设置的 yyfw
	 */
	public void setYyfw(String yyfw) {
		this.yyfw = yyfw;
	}

}
