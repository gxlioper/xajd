package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class YbshForm extends ActionForm {
	private String sbid;
	private String xn;
	private String yf;
	private String xydm;
	private String txr;
	private String xq;
	private String txrq;
	private String shzt;
	private String splcid;
	private String sfywt;//是否有问题  西安科技大个性化字段
	private String xm;
	private String ztqk;
	private String saveFlag;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	
	//审核用
	private String shid;
	private String shjg;
	private String shyj;
	private String gwid;
	private String shlc;
	private String thgw;
	
	//批量审核用
	 private String[] id;
	 private String[] gwids;
	 private String[] txrss;
	 private String[] splcids;
	 private String filepath;
	 public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
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
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
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
	public String getShid() {
		return shid;
	}
	public void setShid(String shid) {
		this.shid = shid;
	}
	public String getShjg() {
		return shjg;
	}
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getGwid() {
		return gwid;
	}
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	public String getShlc() {
		return shlc;
	}
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	public String getThgw() {
		return thgw;
	}
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}
	public String[] getGwids() {
		return gwids;
	}
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	public String[] getTxrss() {
		return txrss;
	}
	public void setTxrss(String[] txrss) {
		this.txrss = txrss;
	}
	public String[] getSplcids() {
		return splcids;
	}
	public void setSplcids(String[] splcids) {
		this.splcids = splcids;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-27 下午04:26:30 
	 * @return		: the sfywt
	 */
	public String getSfywt() {
		return sfywt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-27 下午04:26:30 
	 * @param 		：sfywt the sfywt to set
	 */
	public void setSfywt(String sfywt) {
		this.sfywt = sfywt;
	}
	
}
