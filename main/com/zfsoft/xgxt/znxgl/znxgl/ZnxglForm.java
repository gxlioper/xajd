/**
 * @部门:学工产品事业部
 * @日期：2015-8-27 下午06:53:35 
 */
package com.zfsoft.xgxt.znxgl.znxgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者：  yxy[工号:1206]
 * @时间： 2015-8-27 下午06:53:35
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZnxglForm extends ActionForm {
	private String xjbh;
	private String jsrbh;
	private String fprbh;
	private String jsrydbj;
	private String jsrscbj;
	private String fsrbh;
	private String fssj;
	private String fsnr;
	private String ztlb;
	private String xjzt;
	private String fsrydbj;
	private String fsrscbj;
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
	public String getFprbh() {
		return fprbh;
	}
	/**
	 * @param fprph要设置的 fprph
	 */
	public void setFprbh(String fprbh) {
		this.fprbh = fprbh;
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
