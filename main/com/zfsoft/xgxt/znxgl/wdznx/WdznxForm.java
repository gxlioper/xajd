/**
 * @部门:学工产品事业部
 * @日期：2015-8-27 下午06:55:22 
 */  
package com.zfsoft.xgxt.znxgl.wdznx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-8-27 下午06:55:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WdznxForm extends ActionForm {
	private String xjbh;
	private String jsrbh;
	private String fprph;
	private String jsrydbj;
	private String jsrscbj;
	private String fsrbh;
	private String fssj;
	private String fsnr;
	private String ztlb;
	private String xjzt;
	private String fsrydbj;
	private String fsrscbj;
	private String[] xharr;
	private String[] teaarr;
	private String[] jsrbhs;
	private String[] xjbhs;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	/**
	 * @return the xjbhs
	 */
	public String[] getXjbhs() {
		return xjbhs;
	}
	/**
	 * @param xjbhs要设置的 xjbhs
	 */
	public void setXjbhs(String[] xjbhs) {
		this.xjbhs = xjbhs;
	}

	/**
	 * @return the xharr
	 */
	public String[] getXharr() {
		return xharr;
	}
	/**
	 * @param xharr要设置的 xharr
	 */
	public void setXharr(String[] xharr) {
		this.xharr = xharr;
	}
	/**
	 * @return the teaarr
	 */
	public String[] getTeaarr() {
		return teaarr;
	}
	/**
	 * @param teaarr要设置的 teaarr
	 */
	public void setTeaarr(String[] teaarr) {
		this.teaarr = teaarr;
	}

	/**
	 * @return the jsrbhs
	 */
	public String[] getJsrbhs() {
		return jsrbhs;
	}
	/**
	 * @param jsrbhs要设置的 jsrbhs
	 */
	public void setJsrbhs(String[] jsrbhs) {
		this.jsrbhs = jsrbhs;
	}

	/**
	 * @return the xjbh
	 */
	public String getXjbh() {
		return xjbh;
	}
	/**
	 * @param xjbh要设置的 xjbh
	 */
	public void setXjbh(String xjbh) {
		this.xjbh = xjbh;
	}
	/**
	 * @return the jsrbh
	 */
	public String getJsrbh() {
		return jsrbh;
	}
	/**
	 * @param jsrbh要设置的 jsrbh
	 */
	public void setJsrbh(String jsrbh) {
		this.jsrbh = jsrbh;
	}
	/**
	 * @return the fprph
	 */
	public String getFprph() {
		return fprph;
	}
	/**
	 * @param fprph要设置的 fprph
	 */
	public void setFprph(String fprph) {
		this.fprph = fprph;
	}
	/**
	 * @return the jsrydbj
	 */
	public String getJsrydbj() {
		return jsrydbj;
	}
	/**
	 * @param jsrydbj要设置的 jsrydbj
	 */
	public void setJsrydbj(String jsrydbj) {
		this.jsrydbj = jsrydbj;
	}
	/**
	 * @return the jsrscbj
	 */
	public String getJsrscbj() {
		return jsrscbj;
	}
	/**
	 * @param jsrscbj要设置的 jsrscbj
	 */
	public void setJsrscbj(String jsrscbj) {
		this.jsrscbj = jsrscbj;
	}
	/**
	 * @return the fsrbh
	 */
	public String getFsrbh() {
		return fsrbh;
	}
	/**
	 * @param fsrbh要设置的 fsrbh
	 */
	public void setFsrbh(String fsrbh) {
		this.fsrbh = fsrbh;
	}
	/**
	 * @return the fssj
	 */
	public String getFssj() {
		return fssj;
	}
	/**
	 * @param fssj要设置的 fssj
	 */
	public void setFssj(String fssj) {
		this.fssj = fssj;
	}
	/**
	 * @return the fsnr
	 */
	public String getFsnr() {
		return fsnr;
	}
	/**
	 * @param fsnr要设置的 fsnr
	 */
	public void setFsnr(String fsnr) {
		this.fsnr = fsnr;
	}
	/**
	 * @return the ztlb
	 */
	public String getZtlb() {
		return ztlb;
	}
	/**
	 * @param ztlb要设置的 ztlb
	 */
	public void setZtlb(String ztlb) {
		this.ztlb = ztlb;
	}
	/**
	 * @return the xjzt
	 */
	public String getXjzt() {
		return xjzt;
	}
	/**
	 * @param xjzt要设置的 xjzt
	 */
	public void setXjzt(String xjzt) {
		this.xjzt = xjzt;
	}
	/**
	 * @return the fsrydbj
	 */
	public String getFsrydbj() {
		return fsrydbj;
	}
	/**
	 * @param fsrydbj要设置的 fsrydbj
	 */
	public void setFsrydbj(String fsrydbj) {
		this.fsrydbj = fsrydbj;
	}
	/**
	 * @return the fsrscbj
	 */
	public String getFsrscbj() {
		return fsrscbj;
	}
	/**
	 * @param fsrscbj要设置的 fsrscbj
	 */
	public void setFsrscbj(String fsrscbj) {
		this.fsrscbj = fsrscbj;
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

}
