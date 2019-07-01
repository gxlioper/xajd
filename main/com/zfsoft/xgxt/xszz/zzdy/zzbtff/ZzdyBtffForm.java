/**
 * @部门:学工产品事业部
 * @日期：2015-11-23 上午08:39:17 
 */  
package com.zfsoft.xgxt.xszz.zzdy.zzbtff;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-11-23 上午08:39:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzdyBtffForm extends ActionForm{
	
	private String id ; 
	private String tbyf ;
	private String xmdm ;
	private String zzzje ;
	private String xn ;
	private String xq ;
	private String ffyf ;
	private String xh ;
	private String yffje ;
	private String ffzt ;
	private String tbsj ;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
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
	 * @return the tbyf
	 */
	public String getTbyf() {
		return tbyf;
	}
	/**
	 * @param tbyf要设置的 tbyf
	 */
	public void setTbyf(String tbyf) {
		this.tbyf = tbyf;
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
	 * @return the zzzje
	 */
	public String getZzzje() {
		return zzzje;
	}
	/**
	 * @param zzzje要设置的 zzzje
	 */
	public void setZzzje(String zzzje) {
		this.zzzje = zzzje;
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
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the ffyf
	 */
	public String getFfyf() {
		return ffyf;
	}
	/**
	 * @param ffyf要设置的 ffyf
	 */
	public void setFfyf(String ffyf) {
		this.ffyf = ffyf;
	}
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh要设置的 xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the yffje
	 */
	public String getYffje() {
		return yffje;
	}
	/**
	 * @param yffje要设置的 yffje
	 */
	public void setYffje(String yffje) {
		this.yffje = yffje;
	}
	/**
	 * @return the ffzt
	 */
	public String getFfzt() {
		return ffzt;
	}
	/**
	 * @param ffzt要设置的 ffzt
	 */
	public void setFfzt(String ffzt) {
		this.ffzt = ffzt;
	}
	/**
	 * @return the tbsj
	 */
	public String getTbsj() {
		return tbsj;
	}
	/**
	 * @param tbsj要设置的 tbsj
	 */
	public void setTbsj(String tbsj) {
		this.tbsj = tbsj;
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
	
	
	

}
