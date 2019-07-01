/**
 * @部门:学工产品事业部
 * @日期：2016-7-21 下午03:26:04 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.sq;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-获奖信息管理
 * @类功能描述: 获奖申请 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-7-21 下午03:26:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjsqForm extends ActionForm {
	
	private String sqid;
	private String xh;
	private String sqsj;
	private String shzt;
	private String splc;
	private String jxlbdm;
	private String jxlbmc;
	private String jsfs;
	private String jsfsmc;
	private String jxdjdm;
	private String jxdjmc;
	private String jxmcdm;
	private String jxmcmc;
	private String hdsj;
	private String sqly;
	private String czr;
	private String je;
	
	private String xm;
	private String sfzh;
	private String zzmmmc;
	private String mzmc;
	private String shztmc;
	private String bjmc;
	private String xymc;
	private String zydm;
	private String yhmc;
	private String xydm;
	private String yhkh;
	private String bjdm;
	private String nj;
	private String zymc;
	
	//下载相关
	private FormFile formfile;
	private String filepath;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getJxlbdm() {
		return jxlbdm;
	}
	public void setJxlbdm(String jxlbdm) {
		this.jxlbdm = jxlbdm;
	}
	public String getJxlbmc() {
		return jxlbmc;
	}
	public void setJxlbmc(String jxlbmc) {
		this.jxlbmc = jxlbmc;
	}
	public String getJsfs() {
		return jsfs;
	}
	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}
	public String getJxdjdm() {
		return jxdjdm;
	}
	public void setJxdjdm(String jxdjdm) {
		this.jxdjdm = jxdjdm;
	}
	public String getJxdjmc() {
		return jxdjmc;
	}
	public void setJxdjmc(String jxdjmc) {
		this.jxdjmc = jxdjmc;
	}
	public String getJxmcdm() {
		return jxmcdm;
	}
	public void setJxmcdm(String jxmcdm) {
		this.jxmcdm = jxmcdm;
	}
	public String getJxmcmc() {
		return jxmcmc;
	}
	public void setJxmcmc(String jxmcmc) {
		this.jxmcmc = jxmcmc;
	}
	public String getHdsj() {
		return hdsj;
	}
	public void setHdsj(String hdsj) {
		this.hdsj = hdsj;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getCzr() {
		return czr;
	}
	public void setCzr(String czr) {
		this.czr = czr;
	}
	public FormFile getFormfile() {
		return formfile;
	}
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	public String getJsfsmc() {
		return jsfsmc;
	}
	public void setJsfsmc(String jsfsmc) {
		this.jsfsmc = jsfsmc;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getZzmmmc() {
		return zzmmmc;
	}
	public void setZzmmmc(String zzmmmc) {
		this.zzmmmc = zzmmmc;
	}
	public String getMzmc() {
		return mzmc;
	}
	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}
	public String getShztmc() {
		return shztmc;
	}
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getYhkh() {
		return yhkh;
	}
	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	
}
