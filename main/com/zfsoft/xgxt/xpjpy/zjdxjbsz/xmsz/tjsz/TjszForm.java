/**
 * @部门:学工产品事业部
 * @日期：2017-4-18 下午08:16:16 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.tjsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优-项目设置-条件设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-4-18 下午08:17:53 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjszForm extends ActionForm {
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
	
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pages要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModel要设置的 searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdm要设置的 xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	/**
	 * @return the tjdm
	 */
	public String getTjdm() {
		return tjdm;
	}
	/**
	 * @param tjdm要设置的 tjdm
	 */
	public void setTjdm(String tjdm) {
		this.tjdm = tjdm;
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
	/**
	 * @return the gxdm
	 */
	public String getGxdm() {
		return gxdm;
	}
	/**
	 * @param gxdm要设置的 gxdm
	 */
	public void setGxdm(String gxdm) {
		this.gxdm = gxdm;
	}
	/**
	 * @return the tjz
	 */
	public String getTjz() {
		return tjz;
	}
	/**
	 * @param tjz要设置的 tjz
	 */
	public void setTjz(String tjz) {
		this.tjz = tjz;
	}
	/**
	 * @return the ylzq
	 */
	public String getYlzq() {
		return ylzq;
	}
	/**
	 * @param ylzq要设置的 ylzq
	 */
	public void setYlzq(String ylzq) {
		this.ylzq = ylzq;
	}
}
