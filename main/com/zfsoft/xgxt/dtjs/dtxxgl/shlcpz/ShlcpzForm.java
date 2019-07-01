/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:00:24 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团信息管理模块
 * @类功能描述:form
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:00:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ShlcpzForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	
	private String jddm;//阶段代码
	private String jdmc;//阶段名称
	private String sfszjssj;//是否自动转入下一阶段
	private String sfyrshl;//是否引入审核流
	private String sfsztj;//是否设置条件
	private String dyzd;//对应字段
	private String dyz;//对应值
	private String bz;//备注
	private String jdsx;//阶段顺序
	private String splc;//审批流程id
	private String sfkpzshl;//0：可配置审核流；1：不可配置审核流     (学校按需求配置)
	private String ksqkg;//不可申请(默认0)；1：可申请
	private String ksqkssj;//可申请开始时间
	private String ksqjssj;//可申请结束时间

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	/**
	 * @return the jddm
	 */
	public String getJddm() {
		return jddm;
	}
	/**
	 * @param jddm要设置的 jddm
	 */
	public void setJddm(String jddm) {
		this.jddm = jddm;
	}
	/**
	 * @return the jdmc
	 */
	public String getJdmc() {
		return jdmc;
	}
	/**
	 * @param jdmc要设置的 jdmc
	 */
	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}
	/**
	 * @return the sfszjssj
	 */
	public String getSfszjssj() {
		return sfszjssj;
	}
	/**
	 * @param sfszjssj要设置的 sfszjssj
	 */
	public void setSfszjssj(String sfszjssj) {
		this.sfszjssj = sfszjssj;
	}
	/**
	 * @return the sfyrshl
	 */
	public String getSfyrshl() {
		return sfyrshl;
	}
	/**
	 * @param sfyrshl要设置的 sfyrshl
	 */
	public void setSfyrshl(String sfyrshl) {
		this.sfyrshl = sfyrshl;
	}
	/**
	 * @return the sfsztj
	 */
	public String getSfsztj() {
		return sfsztj;
	}
	/**
	 * @param sfsztj要设置的 sfsztj
	 */
	public void setSfsztj(String sfsztj) {
		this.sfsztj = sfsztj;
	}
	/**
	 * @return the dyzd
	 */
	public String getDyzd() {
		return dyzd;
	}
	/**
	 * @param dyzd要设置的 dyzd
	 */
	public void setDyzd(String dyzd) {
		this.dyzd = dyzd;
	}
	/**
	 * @return the dyz
	 */
	public String getDyz() {
		return dyz;
	}
	/**
	 * @param dyz要设置的 dyz
	 */
	public void setDyz(String dyz) {
		this.dyz = dyz;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the jdsx
	 */
	public String getJdsx() {
		return jdsx;
	}
	/**
	 * @param jdsx要设置的 jdsx
	 */
	public void setJdsx(String jdsx) {
		this.jdsx = jdsx;
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
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the sfkpzshl
	 */
	public String getSfkpzshl() {
		return sfkpzshl;
	}
	/**
	 * @param sfkpzshl要设置的 sfkpzshl
	 */
	public void setSfkpzshl(String sfkpzshl) {
		this.sfkpzshl = sfkpzshl;
	}
	/**
	 * @return the ksqkg
	 */
	public String getKsqkg() {
		return ksqkg;
	}
	/**
	 * @param ksqkg要设置的 ksqkg
	 */
	public void setKsqkg(String ksqkg) {
		this.ksqkg = ksqkg;
	}
	/**
	 * @return the ksqkssj
	 */
	public String getKsqkssj() {
		return ksqkssj;
	}
	/**
	 * @param ksqkssj要设置的 ksqkssj
	 */
	public void setKsqkssj(String ksqkssj) {
		this.ksqkssj = ksqkssj;
	}
	/**
	 * @return the ksqjssj
	 */
	public String getKsqjssj() {
		return ksqjssj;
	}
	/**
	 * @param ksqjssj要设置的 ksqjssj
	 */
	public void setKsqjssj(String ksqjssj) {
		this.ksqjssj = ksqjssj;
	}
}
