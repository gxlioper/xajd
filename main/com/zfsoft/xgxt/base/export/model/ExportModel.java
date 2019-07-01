package com.zfsoft.xgxt.base.export.model;

import java.util.List;

/**
 * 公用导出模型
 * 
 * @author Penghui.Qu
 * 
 */
public class ExportModel {

	private String dcclbh;
	@SuppressWarnings("rawtypes")
	private List dataList;
	private String zgh;
	private String[] unselectCol;
	private String[] selectCol;
	private String selectZd;
	private String unselectZd;
	private String backUrl;

	public String getDcclbh() {
		return dcclbh;
	}

	public void setDcclbh(String dcclbh) {
		this.dcclbh = dcclbh;
	}

	@SuppressWarnings("rawtypes")
	public List getDataList() {
		return dataList;
	}

	@SuppressWarnings("rawtypes")
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String[] getUnselectCol() {
		return unselectCol;
	}

	public void setUnselectCol(String[] unselectCol) {
		this.unselectCol = unselectCol;
	}

	public String[] getSelectCol() {
		return selectCol;
	}

	public void setSelectCol(String[] selectCol) {
		this.selectCol = selectCol;
	}

	public String getSelectZd() {
		return selectZd;
	}

	public void setSelectZd(String selectZd) {
		this.selectZd = selectZd;
	}

	public String getUnselectZd() {
		return unselectZd;
	}

	public void setUnselectZd(String unselectZd) {
		this.unselectZd = unselectZd;
	}

}
