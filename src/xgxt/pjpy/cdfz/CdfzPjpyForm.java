package xgxt.pjpy.cdfz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

public class CdfzPjpyForm extends ActionForm {
	
	SearchModel searchModel = new SearchModel();
	User user=new User();
	private String xydm;
	private String zydm;
	private String bjdm;
	private String xh;
	private String xm;
	private String nj;
	private Pages pages = new Pages();
	private String xn;
	private String xq;
	private String pjxn;
	private String pjxq;
	public String getPjxn() {
		return pjxn;
	}
	public void setPjxn(String pjxn) {
		this.pjxn = pjxn;
	}
	public String getPjxq() {
		return pjxq;
	}
	public void setPjxq(String pjxq) {
		this.pjxq = pjxq;
	}
	private String tjlx;//统计类型;
	private String queryequals_xydm;
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
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getTjlx() {
		return tjlx;
	}
	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
	}
	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}
	public void setQueryequals_xydm(String queryequalsXydm) {
		queryequals_xydm = queryequalsXydm;
	}


}
