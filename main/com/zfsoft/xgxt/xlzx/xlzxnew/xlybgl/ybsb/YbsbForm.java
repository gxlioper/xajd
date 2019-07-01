package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsb;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class YbsbForm extends ActionForm {
	private String sbid;
	private String xn;
	private String yf;
	private String xydm;
	private String txr;
	private String xq;
	private String txrq;
	private String shzt;
	private String splcid;
	private String xm;
	private String ztqk;
	private String sfywt;//是否有问题  西安科技大个性化字段
	private String saveFlag;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	private String[] xhArray;
	private String[] ybwtmsArray;
	private String[] ybgycsArray;
	private String[] ybgyhjgArray;
	private String[] wtfsrqArray;
	//附件上传id
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
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	public String getSbid() {
		return sbid;
	}
	public void setSbid(String sbid) {
		this.sbid = sbid;
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
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSplcid() {
		return splcid;
	}
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getZtqk() {
		return ztqk;
	}
	public void setZtqk(String ztqk) {
		this.ztqk = ztqk;
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
	public String[] getXhArray() {
		return xhArray;
	}
	public void setXhArray(String[] xhArray) {
		this.xhArray = xhArray;
	}
	public String[] getYbwtmsArray() {
		return ybwtmsArray;
	}
	public void setYbwtmsArray(String[] ybwtmsArray) {
		this.ybwtmsArray = ybwtmsArray;
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
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-27 上午10:37:43 
	 * @return		: the sfywt
	 */
	public String getSfywt() {
		return sfywt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-27 上午10:37:43 
	 * @param 		：sfywt the sfywt to set
	 */
	public void setSfywt(String sfywt) {
		this.sfywt = sfywt;
	}
	
}
