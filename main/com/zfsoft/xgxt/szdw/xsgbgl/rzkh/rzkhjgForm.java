/**
 * @部门:学工产品事业部
 * @日期：2015-6-10 下午05:16:50 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.rzkh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：喻鑫源[工号:1206]
 * @时间： 2015-6-10 下午05:16:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class rzkhjgForm extends ActionForm {
	 String khjgid;//考核结果id
	 String xh;//学号
	 String xn;//学年
	 String xq;//学期
	 String zwmc;//职位名称
	 String rzsj;//任职时间
	 String grsz;//个人自述
	 String grzp;//个人自评
	 String zgdwyj;//主管单位意见
	 String qdyj;//区队意见
	 String szdwyj;//所在单位意见
	 String ddyj;//大队意见
	 String xsgzcyj;//学生工作处意见
	 String bz;//备注
	 private static final long serialVersionUID = 1L;
	 private SearchModel searchModel = new SearchModel();
	 private String type;
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
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
	 * @return the khjgid
	 */
	public String getKhjgid() {
		return khjgid;
	}
	/**
	 * @param khjgid要设置的 khjgid
	 */
	public void setKhjgid(String khjgid) {
		this.khjgid = khjgid;
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
	 * @return the zwmc
	 */
	public String getZwmc() {
		return zwmc;
	}
	/**
	 * @param zwmc要设置的 zwmc
	 */
	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}
	/**
	 * @return the rzsj
	 */
	public String getRzsj() {
		return rzsj;
	}
	/**
	 * @param rzsj要设置的 rzsj
	 */
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	/**
	 * @return the grsz
	 */
	public String getGrsz() {
		return grsz;
	}
	/**
	 * @param grsz要设置的 grsz
	 */
	public void setGrsz(String grsz) {
		this.grsz = grsz;
	}
	/**
	 * @return the grzp
	 */
	public String getGrzp() {
		return grzp;
	}
	/**
	 * @param grzp要设置的 grzp
	 */
	public void setGrzp(String grzp) {
		this.grzp = grzp;
	}
	/**
	 * @return the zgdwyj
	 */
	public String getZgdwyj() {
		return zgdwyj;
	}
	/**
	 * @param zgdwyj要设置的 zgdwyj
	 */
	public void setZgdwyj(String zgdwyj) {
		this.zgdwyj = zgdwyj;
	}
	/**
	 * @return the qdyj
	 */
	public String getQdyj() {
		return qdyj;
	}
	/**
	 * @param qdyj要设置的 qdyj
	 */
	public void setQdyj(String qdyj) {
		this.qdyj = qdyj;
	}
	/**
	 * @return the szdwyj
	 */
	public String getSzdwyj() {
		return szdwyj;
	}
	/**
	 * @param szdwyj要设置的 szdwyj
	 */
	public void setSzdwyj(String szdwyj) {
		this.szdwyj = szdwyj;
	}
	/**
	 * @return the ddyj
	 */
	public String getDdyj() {
		return ddyj;
	}
	/**
	 * @param ddyj要设置的 ddyj
	 */
	public void setDdyj(String ddyj) {
		this.ddyj = ddyj;
	}

	/**
	 * @return the xsgzcyj
	 */
	public String getXsgzcyj() {
		return xsgzcyj;
	}
	/**
	 * @param xsgzcyj要设置的 xsgzcyj
	 */
	public void setXsgzcyj(String xsgzcyj) {
		this.xsgzcyj = xsgzcyj;
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
}
