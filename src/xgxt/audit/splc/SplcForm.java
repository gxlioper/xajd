package xgxt.audit.splc;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class SplcForm extends ActionForm {
	
	Pages pages = new Pages();
	
	private String tableName;//����
	
	private String realTable;//�����;
	
	private String query;//����
	
	private String []inputArr;//����ֵ
	
	private String id;
	private String djlx;
	private String mc;
	private String ms;
	private String sfmr;
	
	private String []checkVal;
	
	private String []colList;//����ֶ�;
	
	private String []topTr;//��ͷ
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDjlx() {
		return djlx;
	}
	public void setDjlx(String djlx) {
		this.djlx = djlx;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	public String getSfmr() {
		return sfmr;
	}
	public void setSfmr(String sfmr) {
		this.sfmr = sfmr;
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
