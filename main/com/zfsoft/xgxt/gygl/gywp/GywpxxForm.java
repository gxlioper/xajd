/**
 * @部门:学工产品事业部
 * @日期：2013-8-6 下午03:12:07 
 */  
package com.zfsoft.xgxt.gygl.gywp;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(公寓物品管理) 
 * @作者： cmj [工号：913]
 * @时间： 2013-8-6 下午03:12:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GywpxxForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String id;            //
	private String ids;
	private String wpmc;            //物品名称
	private String wpdldm;            //物品大类代码
	private String wplbdm;            //物品类别代码
	private String sl;            //数量
	private String sfzhgq;            //是否在合格期
	private String sfwh;            //是否完好
	private String lddm;            //楼栋代码
	private String qsh;            //寝室号
	private String hhyy;            //毁坏原因
	private String bz;              //备注
	private String wpdlmc;            //物品大类名称
	private String wplbmc;            //物品类别名称
	private String ldmc;            //楼栋名称
	
	
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
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
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
	 * @return the wpmc
	 */
	public String getWpmc() {
		return wpmc;
	}
	/**
	 * @param wpmc要设置的 wpmc
	 */
	public void setWpmc(String wpmc) {
		this.wpmc = wpmc;
	}
	/**
	 * @return the wpdldm
	 */
	public String getWpdldm() {
		return wpdldm;
	}
	/**
	 * @param wpdldm要设置的 wpdldm
	 */
	public void setWpdldm(String wpdldm) {
		this.wpdldm = wpdldm;
	}
	/**
	 * @return the wplbdm
	 */
	public String getWplbdm() {
		return wplbdm;
	}
	/**
	 * @param wplbdm要设置的 wplbdm
	 */
	public void setWplbdm(String wplbdm) {
		this.wplbdm = wplbdm;
	}
	/**
	 * @return the sl
	 */
	public String getSl() {
		return sl;
	}
	/**
	 * @param sl要设置的 sl
	 */
	public void setSl(String sl) {
		this.sl = sl;
	}
	/**
	 * @return the sfzhgq
	 */
	public String getSfzhgq() {
		return sfzhgq;
	}
	/**
	 * @param sfzhgq要设置的 sfzhgq
	 */
	public void setSfzhgq(String sfzhgq) {
		this.sfzhgq = sfzhgq;
	}
	/**
	 * @return the sfwh
	 */
	public String getSfwh() {
		return sfwh;
	}
	/**
	 * @param sfwh要设置的 sfwh
	 */
	public void setSfwh(String sfwh) {
		this.sfwh = sfwh;
	}
	/**
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddm要设置的 lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @param qsh要设置的 qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	/**
	 * @return the hhyy
	 */
	public String getHhyy() {
		return hhyy;
	}
	/**
	 * @param hhyy要设置的 hhyy
	 */
	public void setHhyy(String hhyy) {
		this.hhyy = hhyy;
	}
	/**
	 * @return the wpdlmc
	 */
	public String getWpdlmc() {
		return wpdlmc;
	}
	/**
	 * @param wpdlmc要设置的 wpdlmc
	 */
	public void setWpdlmc(String wpdlmc) {
		this.wpdlmc = wpdlmc;
	}
	/**
	 * @return the wplbmc
	 */
	public String getWplbmc() {
		return wplbmc;
	}
	/**
	 * @param wplbmc要设置的 wplbmc
	 */
	public void setWplbmc(String wplbmc) {
		this.wplbmc = wplbmc;
	}
	/**
	 * @return the ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmc要设置的 ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getBz() {
		return bz;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getIds() {
		return ids;
	}
	
	
	

}
