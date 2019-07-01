package xgxt.pjpy.bjlhly.zhcp;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

public class BjlhlyZhcpForm extends ActionForm {

	User user = new User();

	Pages pages = new Pages();
	
	private String xh;
	
	private String xydm;
	
	private String zydm;
	
	private String bjdm;
	
	private String nj;
	
	private String select_xydm;
	
	private String select_zydm;
	
	private String select_bjdm;
	
	private String select_nj;
	
	SearchModel searchModel=new SearchModel();

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getSelect_bjdm() {
		return select_bjdm;
	}

	public void setSelect_bjdm(String select_bjdm) {
		this.select_bjdm = select_bjdm;
	}

	public String getSelect_nj() {
		return select_nj;
	}

	public void setSelect_nj(String select_nj) {
		this.select_nj = select_nj;
	}

	public String getSelect_xydm() {
		return select_xydm;
	}

	public void setSelect_xydm(String select_xydm) {
		this.select_xydm = select_xydm;
	}

	public String getSelect_zydm() {
		return select_zydm;
	}

	public void setSelect_zydm(String select_zydm) {
		this.select_zydm = select_zydm;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
}
