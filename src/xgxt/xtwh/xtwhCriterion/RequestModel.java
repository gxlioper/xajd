package xgxt.xtwh.xtwhCriterion;

import xgxt.utils.Pages;


/**
 * 封装了整个系统流程中所需要的变量
 */
public class RequestModel {
	
	String tableName;
	String realTable;
	String jsczfwdm;
	String sffp;
	String jsxm;
	String jslxdm;
	String doType;
	String pk;
	String pkValue;
	String [] colList;
	String [] checkPkValue;
	private Pages pages = new Pages();

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public String getRealTable() {
		return realTable;
	}

	public void setRealTable(String realTable) {
		this.realTable = realTable;
	}

	public String getJsczfwdm() {
		return jsczfwdm;
	}

	public void setJsczfwdm(String jsczfwdm) {
		this.jsczfwdm = jsczfwdm;
	}

	public String getJslxdm() {
		return jslxdm;
	}

	public void setJslxdm(String jslxdm) {
		this.jslxdm = jslxdm;
	}

	public String getJsxm() {
		return jsxm;
	}

	public void setJsxm(String jsxm) {
		this.jsxm = jsxm;
	}

	public String getSffp() {
		return sffp;
	}

	public void setSffp(String sffp) {
		this.sffp = sffp;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String[] getColList() {
		return colList;
	}

	public void setColList(String[] colList) {
		this.colList = colList;
	}

	public String[] getCheckPkValue() {
		return checkPkValue;
	}

	public void setCheckPkValue(String[] checkPkValue) {
		this.checkPkValue = checkPkValue;
	}

}
