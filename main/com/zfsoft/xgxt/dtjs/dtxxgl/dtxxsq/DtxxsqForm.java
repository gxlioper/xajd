/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:00:24 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团信息管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2013-10-23 上午11:38:10 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class DtxxsqForm extends ActionForm {
	private static final long serialVersionUID = -7844335000460670544L;
	private String dtxxsqid;	
	private String xh;//学号
	private String jddm;//阶段代码
	private String sqsj;	//申请时间
	private String shzt;//审核状态
	private String splc;//审批流程
	private String zd1;//扩展字段
	private String zd2;//扩展字段
	private String zd3;//扩展字段
	private String zd4;//扩展字段
	private String zd5;//扩展字段
	private String jdmc;//阶段名称 
	private String grxj;//个人小结
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel=new ExportModel();
	private String type;
	private String lcxx;
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
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
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}
	/**
	 * @param zd1要设置的 zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	/**
	 * @return the zd2
	 */
	public String getZd2() {
		return zd2;
	}
	/**
	 * @param zd2要设置的 zd2
	 */
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}
	/**
	 * @return the zd3
	 */
	public String getZd3() {
		return zd3;
	}
	/**
	 * @param zd3要设置的 zd3
	 */
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}
	/**
	 * @return the zd4
	 */
	public String getZd4() {
		return zd4;
	}
	/**
	 * @param zd4要设置的 zd4
	 */
	public void setZd4(String zd4) {
		this.zd4 = zd4;
	}
	/**
	 * @return the zd5
	 */
	public String getZd5() {
		return zd5;
	}
	/**
	 * @param zd5要设置的 zd5
	 */
	public void setZd5(String zd5) {
		this.zd5 = zd5;
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
	 * @return the lcxx
	 */
	public String getLcxx() {
		return lcxx;
	}
	/**
	 * @param lcxx要设置的 lcxx
	 */
	public void setLcxx(String lcxx) {
		this.lcxx = lcxx;
	}
	/**
	 * @return the grxj
	 */
	public String getGrxj() {
		return grxj;
	}
	/**
	 * @param grxj要设置的 grxj
	 */
	public void setGrxj(String grxj) {
		this.grxj = grxj;
	}
	/**
	 * @return the dtxxsqid
	 */
	public String getDtxxsqid() {
		return dtxxsqid;
	}
	/**
	 * @param dtxxsqid要设置的 dtxxsqid
	 */
	public void setDtxxsqid(String dtxxsqid) {
		this.dtxxsqid = dtxxsqid;
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
