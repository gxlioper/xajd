/**
 * @部门:学工产品事业部
 * @日期：2016年12月27日 上午11:16:59 
 */  
package com.zfsoft.xgxt.xszz.zzkff;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助-资助款发放管理模块
 * @类功能描述: 学生资助ActionForm
 * @作者： xuwen[工号:1426]
 * @时间： 2016年12月27日 上午11:16:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzkffForm extends ActionForm{
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String id;	//主键
	private String xh;	//学号
	private String xn;	//学年
	private String xq;	//学期
	private String xqmc;//学期名称
	private String je;	//金额
	private String xmmc;	//项目名称
	private String wsyhzt;	//网上银行状态
	private String yhfkxx;	//银行反馈信息
	
	private String xm;	//姓名
	private String nj;	//年级
	private String xy;	//学院
	private String zy;	//专业
	private String bj;	//班级
	
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
	 * @return the je
	 */
	public String getJe() {
		return je;
	}
	/**
	 * @param je要设置的 je
	 */
	public void setJe(String je) {
		this.je = je;
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
	 * @return the wsyhzt
	 */
	public String getWsyhzt() {
		return wsyhzt;
	}
	/**
	 * @param wsyhzt要设置的 wsyhzt
	 */
	public void setWsyhzt(String wsyhzt) {
		this.wsyhzt = wsyhzt;
	}
	/**
	 * @return the yhfkxx
	 */
	public String getYhfkxx() {
		return yhfkxx;
	}
	/**
	 * @param yhfkxx要设置的 yhfkxx
	 */
	public void setYhfkxx(String yhfkxx) {
		this.yhfkxx = yhfkxx;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param nj要设置的 nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @return the xy
	 */
	public String getXy() {
		return xy;
	}
	/**
	 * @param xy要设置的 xy
	 */
	public void setXy(String xy) {
		this.xy = xy;
	}
	/**
	 * @return the zy
	 */
	public String getZy() {
		return zy;
	}
	/**
	 * @param zy要设置的 zy
	 */
	public void setZy(String zy) {
		this.zy = zy;
	}
	/**
	 * @return the bj
	 */
	public String getBj() {
		return bj;
	}
	/**
	 * @param bj要设置的 bj
	 */
	public void setBj(String bj) {
		this.bj = bj;
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
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	
	
}
