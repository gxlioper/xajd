package xgxt.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommSetting;
import xgxt.utils.Pages;

public class RequestForm {

	// 分页
	Pages pages = new Pages();

	// 通用设置
	CommSetting commSetting = new CommSetting();

	// 模块路径
	private String path;

	// 用户类型
	private String userType;

	// 用户名
	private String userName;

	// 用户所在部门
	private String userDep;

	// 用户身份
	private String userStatus;
	
	// 公寓管理员权限
	private String gyglyQx;
	
	// 操作类型
	private String doType;

	// 模块标题
	private String title;

	// 主键
	private String pk;

	// 主键值
	private String pkValue;

	// 操作表
	private String tableName;

	// 真实表
	private String realTable;

	// 提示信息
	private String message;

	// 模塊類型
	private String mklx;

	// 功能模块
	private String gnmk;

	// 菜单
	private String menu;

	// 是否搜索
	private String search;

	// 查询类型
	private String searchType;

	// 样式地址
	private String stylePath;
	
	// 其他字段
	private String[] qtzd;

	// 其他字段值
	private String[] qtzdz;

	// 表头
	private List<HashMap<String, String>> topTr;

	// 详细信息
	private HashMap<String, String> rs;

	// 详细列表信息
	private List<HashMap<String, String>> rsList;

	// 详细列表信息
	private ArrayList<String[]> rsArrList;

	// 输出字段
	private String[] colList;

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getMklx() {
		return mklx;
	}

	public void setMklx(String mklx) {
		this.mklx = mklx;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String[] getQtzd() {
		return qtzd;
	}

	public void setQtzd(String[] qtzd) {
		this.qtzd = qtzd;
	}

	public HashMap<String, String> getRs() {
		return rs;
	}

	public void setRs(HashMap<String, String> rs) {
		this.rs = rs;
	}

	public String getUserDep() {
		return userDep;
	}

	public void setUserDep(String userDep) {
		this.userDep = userDep;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String[] getQtzdz() {
		return qtzdz;
	}

	public void setQtzdz(String[] qtzdz) {
		this.qtzdz = qtzdz;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<HashMap<String, String>> getRsList() {
		return rsList;
	}

	public void setRsList(List<HashMap<String, String>> rsList) {
		this.rsList = rsList;
	}

	public String getRealTable() {
		return realTable;
	}

	public void setRealTable(String realTable) {
		this.realTable = realTable;
	}

	public String getTableName() {
		return tableName;
	}

	public String getStylePath() {
		return stylePath;
	}

	public void setStylePath(String stylePath) {
		this.stylePath = stylePath;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<String[]> getRsArrList() {
		return rsArrList;
	}

	public void setRsArrList(ArrayList<String[]> rsArrList) {
		this.rsArrList = rsArrList;
	}

	public List<HashMap<String, String>> getTopTr() {
		return topTr;
	}

	public void setTopTr(List<HashMap<String, String>> topTr) {
		this.topTr = topTr;
	}

	public String getGnmk() {
		return gnmk;
	}

	public void setGnmk(String gnmk) {
		this.gnmk = gnmk;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String[] getColList() {
		return colList;
	}

	public void setColList(String[] colList) {
		this.colList = colList;
	}

	public CommSetting getCommSetting() {
		return commSetting;
	}

	public void setCommSetting(CommSetting commSetting) {
		this.commSetting = commSetting;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getGyglyQx() {
		return gyglyQx;
	}

	public void setGyglyQx(String gyglyQx) {
		this.gyglyQx = gyglyQx;
	}
}
