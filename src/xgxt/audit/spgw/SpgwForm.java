package xgxt.audit.spgw;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class SpgwForm extends ActionForm {
	
	Pages pages = new Pages();
	
	private String tableName;//表名
	
	private String realTable;//导入表;
	
	private String query;//条件
	
	private String []inputArr;//输入值
	
	private List<HashMap<String, String>> zList;
	
	private String id;
	private String mc;
	private String ms;
	private String sfzz;
	private String zzjs;
	
	private String spgw;
	private String spyh;
	
	private String yhm; //用户表字段
	private String xm;
	private String dwdm;
	private String xq;
	private String zdm;
	
	private String []yhList;
	
	private String []checkVal;
	
	private String []colList;//输出字段;
	
	private String []topTr;//表头
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getSfzz() {
		return sfzz;
	}
	public void setSfzz(String sfzz) {
		this.sfzz = sfzz;
	}
	public String getZzjs() {
		return zzjs;
	}
	public void setZzjs(String zzjs) {
		this.zzjs = zzjs;
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
	public String getSpgw() {
		return spgw;
	}
	public void setSpgw(String spgw) {
		this.spgw = spgw;
	}
	public String getSpyh() {
		return spyh;
	}
	public void setSpyh(String spyh) {
		this.spyh = spyh;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getDwdm() {
		return dwdm;
	}
	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String[] getYhList() {
		return yhList;
	}
	public void setYhList(String[] yhList) {
		this.yhList = yhList;
	}
	public List<HashMap<String, String>> getZList() {
		return zList;
	}
	public void setZList(List<HashMap<String, String>> list) {
		zList = list;
	}
	public String getZdm() {
		return zdm;
	}
	public void setZdm(String zdm) {
		this.zdm = zdm;
	}
	
}
