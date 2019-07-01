package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbjg;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class ZbjgForm extends ActionForm {
	 private String sbjgid;
	 private String xh;
	 private String sblx;
	 private String sbsj;
     private String sbzbid;
	 private String ztqk;
	 private String xlxsxxqk;
     private String bz;
     private String sjly;
	 private String sbsqid;
	 private String xn;
	 private String xq;
     private String bjdm;   
	 private String xm;
	 private ExportModel exportModel = new ExportModel();
     private String[] xhArray;
	 private String[] zbwtmsArray;
	 private SearchModel searchModel = new SearchModel();
	 private Pages pages = new Pages();
	 public String[] getXhArray() {
		return xhArray;
	}
	public void setXhArray(String[] xhArray) {
		this.xhArray = xhArray;
	}
	public String[] getZbwtmsArray() {
		return zbwtmsArray;
	}
	public void setZbwtmsArray(String[] zbwtmsArray) {
		this.zbwtmsArray = zbwtmsArray;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	
	public String getSbjgid() {
		return sbjgid;
	}
	public void setSbjgid(String sbjgid) {
		this.sbjgid = sbjgid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSblx() {
		return sblx;
	}
	public void setSblx(String sblx) {
		this.sblx = sblx;
	}
	public String getSbsj() {
		return sbsj;
	}
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}
	public String getSbzbid() {
		return sbzbid;
	}
	public void setSbzbid(String sbzbid) {
		this.sbzbid = sbzbid;
	}
	public String getZtqk() {
		return ztqk;
	}
	public void setZtqk(String ztqk) {
		this.ztqk = ztqk;
	}
	public String getXlxsxxqk() {
		return xlxsxxqk;
	}
	public void setXlxsxxqk(String xlxsxxqk) {
		this.xlxsxxqk = xlxsxxqk;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	public String getSbsqid() {
		return sbsqid;
	}
	public void setSbsqid(String sbsqid) {
		this.sbsqid = sbsqid;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
	   this.exportModel = exportModel;
	}
}
