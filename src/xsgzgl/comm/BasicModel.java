package xsgzgl.comm;

import java.util.HashMap;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

public class BasicModel {
	
	// 用户对象
	User user = new User();
	
	// 高级查询
	SearchModel searchModel = new SearchModel();
	
	// 分页
	Pages pages = new Pages();

	private HashMap<String, String> valueMap; //传入值

	private HashMap<String, Object> arrayMap; //

	private String tableName;//表名

	private String viewName;//视图名

	private String[] colList = null;//结果集显示字段

	private String save;//保存

	private String update;

	private String[]pk;

	private String[] pkValue;

	private String gnmk;

	private String path;

	private String[] oneZd;

	private String[] arrayZd;
	
	private String[] queryV;
	
	private String[] queryLikeV;
	
	private String[] inputV;
	
	private String[] outPut;
	
	private String[] orderBy;
	
	private String xmb;
	
	private String sqb;
	
	private String shb;
	
	private String[] sqbPk;
	
	private String[] shbPk;
	
	private ExportModel exportModel = new ExportModel();

	public String[] getShbPk() {
		return shbPk;
	}

	public void setShbPk(String[] shbPk) {
		this.shbPk = shbPk;
	}

	public String[] getSqbPk() {
		return sqbPk;
	}

	public void setSqbPk(String[] sqbPk) {
		this.sqbPk = sqbPk;
	}

	public String getShb() {
		return shb;
	}

	public void setShb(String shb) {
		this.shb = shb;
	}

	public String getSqb() {
		return sqb;
	}

	public void setSqb(String sqb) {
		this.sqb = sqb;
	}

	public String getXmb() {
		return xmb;
	}

	public void setXmb(String xmb) {
		this.xmb = xmb;
	}

	public HashMap<String, Object> getArrayMap() {
		return arrayMap;
	}

	public void setArrayMap(HashMap<String, Object> arrayMap) {
		this.arrayMap = arrayMap;
	}

	public String[] getArrayZd() {
		return arrayZd;
	}

	public void setArrayZd(String[] arrayZd) {
		this.arrayZd = arrayZd;
	}

	public String[] getColList() {
		return colList;
	}

	public void setColList(String[] colList) {
		this.colList = colList;
	}

	public String getGnmk() {
		return gnmk;
	}

	public void setGnmk(String gnmk) {
		this.gnmk = gnmk;
	}

	public String[] getInputV() {
		return inputV;
	}

	public void setInputV(String[] inputV) {
		this.inputV = inputV;
	}

	public String[] getOneZd() {
		return oneZd;
	}

	public void setOneZd(String[] oneZd) {
		this.oneZd = oneZd;
	}

	public String[] getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String[] orderBy) {
		this.orderBy = orderBy;
	}

	public String[] getOutPut() {
		return outPut;
	}

	public void setOutPut(String[] outPut) {
		this.outPut = outPut;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String[] getPk() {
		return pk;
	}

	public void setPk(String[] pk) {
		this.pk = pk;
	}

	public String[] getPkValue() {
		return pkValue;
	}

	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}

	public String[] getQueryV() {
		return queryV;
	}

	public void setQueryV(String[] queryV) {
		this.queryV = queryV;
	}

	public String getSave() {
		return save;
	}

	public void setSave(String save) {
		this.save = save;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
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

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String[] getQueryLikeV() {
		return queryLikeV;
	}

	public void setQueryLikeV(String[] queryLikeV) {
		this.queryLikeV = queryLikeV;
	}

	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}

	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
}
