package xgxt.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommSetting;
import xgxt.utils.Pages;

public class RequestForm {

	// ��ҳ
	Pages pages = new Pages();

	// ͨ������
	CommSetting commSetting = new CommSetting();

	// ģ��·��
	private String path;

	// �û�����
	private String userType;

	// �û���
	private String userName;

	// �û����ڲ���
	private String userDep;

	// �û����
	private String userStatus;
	
	// ��Ԣ����ԱȨ��
	private String gyglyQx;
	
	// ��������
	private String doType;

	// ģ�����
	private String title;

	// ����
	private String pk;

	// ����ֵ
	private String pkValue;

	// ������
	private String tableName;

	// ��ʵ��
	private String realTable;

	// ��ʾ��Ϣ
	private String message;

	// ģ�K���
	private String mklx;

	// ����ģ��
	private String gnmk;

	// �˵�
	private String menu;

	// �Ƿ�����
	private String search;

	// ��ѯ����
	private String searchType;

	// ��ʽ��ַ
	private String stylePath;
	
	// �����ֶ�
	private String[] qtzd;

	// �����ֶ�ֵ
	private String[] qtzdz;

	// ��ͷ
	private List<HashMap<String, String>> topTr;

	// ��ϸ��Ϣ
	private HashMap<String, String> rs;

	// ��ϸ�б���Ϣ
	private List<HashMap<String, String>> rsList;

	// ��ϸ�б���Ϣ
	private ArrayList<String[]> rsArrList;

	// ����ֶ�
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
