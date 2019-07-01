package com.zfsoft.xgxt.xsxx.tdjs;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class TdjsForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String nj;
	private String bmdm;
	private String bmmc;
	private String zydm;
	private String zymc;
	private String bjdm;
	private String bjmc;
	private String zgh;
	private String zdls;
	
	private String[] njArray;
	private String[] xyArray;
	private String[] zyArray;
	private String[] tdArray;
	private String[] zdlsArray;
	private String[] xhArray;
	
	private String jzgh ;//
	private String ywxtbh ;//
	private String zhgxsj ;//
	private String bjjc ;// 
	private String zcrs ;// 

	private String xh;
	private String xm;
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getZdls() {
		return zdls;
	}
	public void setZdls(String zdls) {
		this.zdls = zdls;
	}
	public String getJzgh() {
		return jzgh;
	}
	public void setJzgh(String jzgh) {
		this.jzgh = jzgh;
	}
	public String getYwxtbh() {
		return ywxtbh;
	}
	public void setYwxtbh(String ywxtbh) {
		this.ywxtbh = ywxtbh;
	}
	public String getZhgxsj() {
		return zhgxsj;
	}
	public void setZhgxsj(String zhgxsj) {
		this.zhgxsj = zhgxsj;
	}
	public String getBjjc() {
		return bjjc;
	}
	public void setBjjc(String bjjc) {
		this.bjjc = bjjc;
	}
	public String getZcrs() {
		return zcrs;
	}
	public void setZcrs(String zcrs) {
		this.zcrs = zcrs;
	}
	public String[] getNjArray() {
		return njArray;
	}
	public void setNjArray(String[] njArray) {
		this.njArray = njArray;
	}
	public String[] getXyArray() {
		return xyArray;
	}
	public void setXyArray(String[] xyArray) {
		this.xyArray = xyArray;
	}
	public String[] getZyArray() {
		return zyArray;
	}
	public void setZyArray(String[] zyArray) {
		this.zyArray = zyArray;
	}
	public String[] getTdArray() {
		return tdArray;
	}
	public void setTdArray(String[] tdArray) {
		this.tdArray = tdArray;
	}
	public String[] getZdlsArray() {
		return zdlsArray;
	}
	public void setZdlsArray(String[] zdlsArray) {
		this.zdlsArray = zdlsArray;
	}
	public String[] getXhArray() {
		return xhArray;
	}
	public void setXhArray(String[] xhArray) {
		this.xhArray = xhArray;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	
}
