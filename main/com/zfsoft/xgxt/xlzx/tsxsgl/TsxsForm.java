/**
 * @部门:学工产品事业部
 * @日期：2013-9-10 上午11:10:19 
 */  
package com.zfsoft.xgxt.xlzx.tsxsgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 特殊学生维护模块(这里用一句话描述这个类的作用) 
 * @作者： wanghj [工号：1004]
 * @时间： 2013-9-10 上午11:10:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsxsForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	private ExportModel exportModel = new ExportModel();

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	  
	private String id; // 主键

	private String xh; //学号
	
	private String knlxdm; //困难类型(西安科技大学，预警程度)
	
	private String gzzt; //关注状态
	
	private String qksm; // 情况说明
	
	private String bz; // 备注

	private String xgsj; //修改时间
	
	private String sjzt; //数据状态0失效1正常
	
	private String jbqkms;//基本情况描述（西安科技大学）
	
	private String clcs;//处理措施（西安科技大学）
	
	private String lrsj;//录入时间（西安科技大学）
	
	private String fjid;//附件（西安科技大学）
	
	private String gzsj;//跟踪时间（西安科技大学）
	
	private String gznr;//跟踪内容（西安科技大学）
	
	private String zc;//周次（湖南城市学院）
	
	private String yyms;//原因描述（湖南城市学院）
	
	
	
	/**
	 * @return the zc
	 */
	public String getZc() {
		return zc;
	}

	/**
	 * @param zc要设置的 zc
	 */
	public void setZc(String zc) {
		this.zc = zc;
	}

	/**
	 * @return the yyms
	 */
	public String getYyms() {
		return yyms;
	}

	/**
	 * @param yyms要设置的 yyms
	 */
	public void setYyms(String yyms) {
		this.yyms = yyms;
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
	 * @return the gzzt
	 */
	public String getGzzt() {
		return gzzt;
	}

	/**
	 * @param gzzt要设置的 gzzt
	 */
	public void setGzzt(String gzzt) {
		this.gzzt = gzzt;
	}

	/**
	 * @return the qksm
	 */
	public String getQksm() {
		return qksm;
	}

	/**
	 * @param qksm要设置的 qksm
	 */
	public void setQksm(String qksm) {
		this.qksm = qksm;
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
	 * @return the xgsj
	 */
	public String getXgsj() {
		return xgsj;
	}

	/**
	 * @param xgsj要设置的 xgsj
	 */
	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}

	/**
	 * @return the knlxdm
	 */
	public String getKnlxdm() {
		return knlxdm;
	}
	
	/**
	 * @param knlxdm要设置的 knlxdm
	 */
	public void setKnlxdm(String knlxdm) {
		this.knlxdm = knlxdm;
	}

	/**
	 * @return the sjzt
	 */
	public String getSjzt() {
		return sjzt;
	}

	/**
	 * @param sjzt要设置的 sjzt
	 */
	public void setSjzt(String sjzt) {
		this.sjzt = sjzt;
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
	 * @return the jbqkms
	 */
	public String getJbqkms() {
		return jbqkms;
	}

	/**
	 * @param jbqkms要设置的 jbqkms
	 */
	public void setJbqkms(String jbqkms) {
		this.jbqkms = jbqkms;
	}

	/**
	 * @return the clcs
	 */
	public String getClcs() {
		return clcs;
	}

	/**
	 * @param clcs要设置的 clcs
	 */
	public void setClcs(String clcs) {
		this.clcs = clcs;
	}

	/**
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}

	/**
	 * @param lrsj要设置的 lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	/**
	 * @return the fjid
	 */
	public String getFjid() {
		return fjid;
	}

	/**
	 * @param fjid要设置的 fjid
	 */
	public void setFjid(String fjid) {
		this.fjid = fjid;
	}

	/**
	 * @return the gzsj
	 */
	public String getGzsj() {
		return gzsj;
	}

	/**
	 * @param gzsj要设置的 gzsj
	 */
	public void setGzsj(String gzsj) {
		this.gzsj = gzsj;
	}

	/**
	 * @return the gznr
	 */
	public String getGznr() {
		return gznr;
	}

	/**
	 * @param gznr要设置的 gznr
	 */
	public void setGznr(String gznr) {
		this.gznr = gznr;
	}
	
}
