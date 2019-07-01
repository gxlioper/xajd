package xgxt.audit.gnsz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class ShgnszForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();

	private String querylike_gnmc;
	
	private String querylike_mc;
	
	private String save_gnmc;
	
	private String save_lcid;
	
	public String getQuerylike_gnmc() {
		return querylike_gnmc;
	}

	public void setQuerylike_gnmc(String querylike_gnmc) {
		this.querylike_gnmc = querylike_gnmc;
	}

	public String getQuerylike_mc() {
		return querylike_mc;
	}

	public void setQuerylike_mc(String querylike_mc) {
		this.querylike_mc = querylike_mc;
	}

	public String getSave_gnmc() {
		return save_gnmc;
	}

	public void setSave_gnmc(String save_gnmc) {
		this.save_gnmc = save_gnmc;
	}

	public String getSave_lcid() {
		return save_lcid;
	}

	public void setSave_lcid(String save_lcid) {
		this.save_lcid = save_lcid;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
