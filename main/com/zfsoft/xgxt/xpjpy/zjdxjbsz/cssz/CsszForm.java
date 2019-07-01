/**
 * @部门:学工产品1部
 * @日期：2017-3-21 上午09:19:54 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 新评奖评优_基本设置_参数设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-3-21 上午09:19:54 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();//分页
	private String type;//类型
	private String id;//ID
	private String pjkg;//评奖开关
	private String kssj;//评奖开始时间
	private String jssj;//评奖结束时间
	private String xn;//评奖学年
	private String kgzt;//开关状态
	private String xmdm;//综测项目代码
	private String xmmc;//综测项目项目名称
	private String fjdm;//综测项目父级代码
	private String xmlx;//综测项目项目类型
	private String px;//综测项目排序
	private String zxfz;//综测项目最小分值
	private String zdfz;//综测项目最大分值
	private String mc;//名称
	private SearchModel searchModel = new SearchModel();
	
	
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
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}
	/**
	 * @param mc要设置的 mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
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
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc要设置的 xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the fjdm
	 */
	public String getFjdm() {
		return fjdm;
	}
	/**
	 * @param fjdm要设置的 fjdm
	 */
	public void setFjdm(String fjdm) {
		this.fjdm = fjdm;
	}
	/**
	 * @return the xmlx
	 */
	public String getXmlx() {
		return xmlx;
	}
	/**
	 * @param xmlx要设置的 xmlx
	 */
	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}
	/**
	 * @return the px
	 */
	public String getPx() {
		return px;
	}
	/**
	 * @param px要设置的 px
	 */
	public void setPx(String px) {
		this.px = px;
	}
	/**
	 * @return the zxfz
	 */
	public String getZxfz() {
		return zxfz;
	}
	/**
	 * @param zxfz要设置的 zxfz
	 */
	public void setZxfz(String zxfz) {
		this.zxfz = zxfz;
	}
	/**
	 * @return the zdfz
	 */
	public String getZdfz() {
		return zdfz;
	}
	/**
	 * @param zdfz要设置的 zdfz
	 */
	public void setZdfz(String zdfz) {
		this.zdfz = zdfz;
	}
	/**
	 * @return the kgzt
	 */
	public String getKgzt() {
		return kgzt;
	}
	/**
	 * @param kgzt要设置的 kgzt
	 */
	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
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
	 * @return the pjkg
	 */
	public String getPjkg() {
		return pjkg;
	}
	/**
	 * @param pjkg要设置的 pjkg
	 */
	public void setPjkg(String pjkg) {
		this.pjkg = pjkg;
	}
	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}
	/**
	 * @param kssj要设置的 kssj
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}
	/**
	 * @param jssj要设置的 jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
}
