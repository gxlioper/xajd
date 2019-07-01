/**
 * @部门:学工产品事业部
 * @日期：2013-7-31 下午04:59:33 
 */
package com.zfsoft.xgxt.xpjpy.xmsz.tjsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 条件设置
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-31 下午04:59:33
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TjszModel extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	private String id;// ID
	private String xmdm;// 项目代码
	private String tjdm;// 条件代码
	private String yyfw;// 应用范围
	private String gxdm;// 关系代码
	private String tjz;// 条件值
	private String ylzq;// 依赖周期

	public TjszModel() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getTjdm() {
		return tjdm;
	}

	public void setTjdm(String tjdm) {
		this.tjdm = tjdm;
	}

	public String getYyfw() {
		return yyfw;
	}

	public void setYyfw(String yyfw) {
		this.yyfw = yyfw;
	}

	public String getGxdm() {
		return gxdm;
	}

	public void setGxdm(String gxdm) {
		this.gxdm = gxdm;
	}

	public String getTjz() {
		return tjz;
	}

	public void setTjz(String tjz) {
		this.tjz = tjz;
	}

	public String getYlzq() {
		return ylzq;
	}

	public void setYlzq(String ylzq) {
		this.ylzq = ylzq;
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

}
