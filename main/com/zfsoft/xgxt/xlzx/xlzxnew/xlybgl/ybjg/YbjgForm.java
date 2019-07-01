package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybjg;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class YbjgForm extends ActionForm {
	private String jgid;
	private String xn;
	private String yf;
	private String xydm;
	private String txr;
	private String xq;
	private String txrq;
	private String sjly;
	private String xm;
	private String sbid;
	private String ztqk;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	private String[] xhArray;
	private String[] ybwtmsArray;
	private String[] ybgycsArray;
	private String[] ybgyhjgArray;
	private String[] wtfsrqArray;
	private String sfywt;//是否有问题  西安科技大个性化字段
	private String type;
	//附件上传(西安科技个性化)
	private String filepath;
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String[] getWtfsrqArray() {
		return wtfsrqArray;
	}
	public void setWtfsrqArray(String[] wtfsrqArray) {
		this.wtfsrqArray = wtfsrqArray;
	}
	public String getZtqk() {
		return ztqk;
	}
	public void setZtqk(String ztqk) {
		this.ztqk = ztqk;
	}
	
	public String getXm() {
		return xm;
	}
	public String[] getXhArray() {
		return xhArray;
	}
	public void setXhArray(String[] xhArray) {
		this.xhArray = xhArray;
	}
	public String[] getYbgycsArray() {
		return ybgycsArray;
	}
	public void setYbgycsArray(String[] ybgycsArray) {
		this.ybgycsArray = ybgycsArray;
	}
	public String[] getYbgyhjgArray() {
		return ybgyhjgArray;
	}
	public void setYbgyhjgArray(String[] ybgyhjgArray) {
		this.ybgyhjgArray = ybgyhjgArray;
	}
	public String[] getYbwtmsArray() {
		return ybwtmsArray;
	}
	public void setYbwtmsArray(String[] ybwtmsArray) {
		this.ybwtmsArray = ybwtmsArray;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getJgid() {
		return jgid;
	}
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getTxr() {
		return txr;
	}
	public void setTxr(String txr) {
		this.txr = txr;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getTxrq() {
		return txrq;
	}
	public void setTxrq(String txrq) {
		this.txrq = txrq;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	public String getSbid() {
		return sbid;
	}
	public void setSbid(String sbid) {
		this.sbid = sbid;
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
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-27 下午04:27:22 
	 * @return		: the sfywt
	 */
	public String getSfywt() {
		return sfywt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-27 下午04:27:22 
	 * @param 		：sfywt the sfywt to set
	 */
	public void setSfywt(String sfywt) {
		this.sfywt = sfywt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-27 下午07:18:52 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-27 下午07:18:52 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	} 
	
}
