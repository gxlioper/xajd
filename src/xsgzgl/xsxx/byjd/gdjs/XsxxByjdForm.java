package xsgzgl.xsxx.byjd.gdjs;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

public class XsxxByjdForm extends ActionForm {

	Pages pages = new Pages();

	SearchModel searchModel = new SearchModel();

	User user = new User();

	private String byjd; // 毕业鉴定

	private String xh; // 学号

	private String czr; // 操作人

	private String czsj; // 操作时间

	public String getByjd() {
		return byjd;
	}

	public void setByjd(String byjd) {
		this.byjd = byjd;
	}

	public String getCzr() {
		return czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	public String getCzsj() {
		return czsj;
	}

	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
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

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

}
