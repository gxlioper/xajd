package xsgzgl.xsxx.bzrpy;

import java.util.HashMap;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

public class BasicModel {

	User user = new User();

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();

	private HashMap<String, String> valueMap;

	private HashMap<String, Object> arrayMap;

	private String tableName;

	private String viewName;

	private String[] colList = null;

	private String save;

	private String update;

	private String[]pk;

	private String[] pkValue;

	private String gnmk;

	private String path;

	private String[] oneZd;

	private String[] arrayZd;
	
	private String[] queryV;
	
	private String[] inputV;
	
	private String[] outPut;
	
	private String[] orderBy;

	public String[] getInputV() {
		return inputV;
	}

	public void setInputV(String[] inputV) {
		this.inputV = inputV;
	}

	public String[] getQueryV() {
		return queryV;
	}

	public void setQueryV(String[] queryV) {
		this.queryV = queryV;
	}

	public String getGnmk() {
		return gnmk;
	}

	public void setGnmk(String gnmk) {
		this.gnmk = gnmk;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String[] getColList() {
		return colList;
	}

	public void setColList(String[] colList) {
		this.colList = colList;
	}

	public String[] getPk() {
		return pk;
	}

	public void setPk(String[] pk) {
		this.pk = pk;
	}

	public String getSave() {
		return save;
	}

	public void setSave(String save) {
		this.save = save;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public HashMap<String, String> getValueMap() {
		return valueMap;
	}

	public void setValueMap(HashMap<String, String> valueMap) {
		this.valueMap = valueMap;
	}

	public HashMap<String, Object> getArrayMap() {
		return arrayMap;
	}

	public void setArrayMap(HashMap<String, Object> arrayMap) {
		this.arrayMap = arrayMap;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String[] getPkValue() {
		return pkValue;
	}

	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}

	public String[] getArrayZd() {
		return arrayZd;
	}

	public void setArrayZd(String[] arrayZd) {
		this.arrayZd = arrayZd;
	}

	public String[] getOneZd() {
		return oneZd;
	}

	public void setOneZd(String[] oneZd) {
		this.oneZd = oneZd;
	}

	public String[] getOutPut() {
		return outPut;
	}

	public void setOutPut(String[] outPut) {
		this.outPut = outPut;
	}

	public String[] getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String[] orderBy) {
		this.orderBy = orderBy;
	}
}
