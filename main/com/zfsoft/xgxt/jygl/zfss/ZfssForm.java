/**
 * @部门:学工产品事业部
 * @日期：2013-5-27 下午02:00:28 
 */
package com.zfsoft.xgxt.jygl.zfss;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 社区管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： huj
 * @时间： 2013-5-27 下午02:00:28
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZfssForm extends ActionForm {

	private static final long serialVersionUID = -20000008L;

	private String type;
	SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();

	private String djid;
	private String zgh;// 职工号（辅导员职工号）
	private String bfwyq;// 被访问园区
	private String bfwld;// 被访问楼栋
	private String bfwcs;// 被访问层数
	private String bfwss;// 被访问宿舍
	private String jrsj;// 进入时间
	private String lksj;// 离开时间
	private String zbr;// 值班人
	private String fwly;// 访问理由

	// 查询用
	private String jrsjks;// 进入时间开始
	private String jrsjjz;// 进入时间截止
	private String zgxm;// 职工姓名
	private String xb;// 职工性别
	private String bmmc;// 职工部门
	

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

	public String getDjid() {
		return djid;
	}

	public void setDjid(String djid) {
		this.djid = djid;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getBfwyq() {
		return bfwyq;
	}

	public void setBfwyq(String bfwyq) {
		this.bfwyq = bfwyq;
	}

	public String getBfwld() {
		return bfwld;
	}

	public void setBfwld(String bfwld) {
		this.bfwld = bfwld;
	}

	public String getBfwcs() {
		return bfwcs;
	}

	public void setBfwcs(String bfwcs) {
		this.bfwcs = bfwcs;
	}

	public String getBfwss() {
		return bfwss;
	}

	public void setBfwss(String bfwss) {
		this.bfwss = bfwss;
	}

	public String getJrsj() {
		return jrsj;
	}

	public void setJrsj(String jrsj) {
		this.jrsj = jrsj;
	}

	public String getLksj() {
		return lksj;
	}

	public void setLksj(String lksj) {
		this.lksj = lksj;
	}

	public String getZbr() {
		return zbr;
	}

	public void setZbr(String zbr) {
		this.zbr = zbr;
	}

	public String getFwly() {
		return fwly;
	}

	public void setFwly(String fwly) {
		this.fwly = fwly;
	}

	public String getJrsjks() {
		return jrsjks;
	}

	public void setJrsjks(String jrsjks) {
		this.jrsjks = jrsjks;
	}

	public String getJrsjjz() {
		return jrsjjz;
	}

	public void setJrsjjz(String jrsjjz) {
		this.jrsjjz = jrsjjz;
	}

	public String getZgxm() {
		return zgxm;
	}

	public void setZgxm(String zgxm) {
		this.zgxm = zgxm;
	}

	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}

	/**
	 * @param xb要设置的 xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}

	/**
	 * @return the bmmc
	 */
	public String getBmmc() {
		return bmmc;
	}

	/**
	 * @param bmmc要设置的 bmmc
	 */
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
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
