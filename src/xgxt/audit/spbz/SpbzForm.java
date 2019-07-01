package xgxt.audit.spbz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class SpbzForm extends ActionForm {
	
	Pages pages = new Pages();
	
	private String tableName;//表名
	
	private String realTable;//导入表;
	
	private String query;//条件
	
	private String []inputArr;//输入值
	
	private String id;
	private String splc;
	private Integer xh;
	private String spgw;
	
	private String spgwmc;
	
	private String []checkVal;
	
	private String []colList;//输出字段;
	
	private String []topTr;//表头

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSplc() {
		return splc;
	}

	public void setSplc(String splc) {
		this.splc = splc;
	}

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public String getSpgw() {
		return spgw;
	}

	public void setSpgw(String spgw) {
		this.spgw = spgw;
	}

	public String getSpgwmc() {
		return spgwmc;
	}

	public void setSpgwmc(String spgwmc) {
		this.spgwmc = spgwmc;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String[] getInputArr() {
		return inputArr;
	}

	public void setInputArr(String[] inputArr) {
		this.inputArr = inputArr;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String[] getColList() {
		return colList;
	}

	public void setColList(String[] colList) {
		this.colList = colList;
	}

	public String[] getTopTr() {
		return topTr;
	}

	public void setTopTr(String[] topTr) {
		this.topTr = topTr;
	}
	
	

}
