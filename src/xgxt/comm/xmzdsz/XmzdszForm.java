package xgxt.comm.xmzdsz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class XmzdszForm extends ActionForm {

	Pages pages = new Pages();
	
	private String queryequals_xmdm;
	
	private String querylike_lybmc;
	
	private String querylike_zdmc;
	
	private String bxlr;
	
	private String zdlx;

	private String lrxz;
	
	public String getLrxz() {
		return lrxz;
	}

	public void setLrxz(String lrxz) {
		this.lrxz = lrxz;
	}

	public String getZdlx() {
		return zdlx;
	}

	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}

	public String getBxlr() {
		return bxlr;
	}

	public void setBxlr(String bxlr) {
		this.bxlr = bxlr;
	}

	public String getQueryequals_xmdm() {
		return queryequals_xmdm;
	}

	public void setQueryequals_xmdm(String queryequals_xmdm) {
		this.queryequals_xmdm = queryequals_xmdm;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getQuerylike_lybmc() {
		return querylike_lybmc;
	}

	public void setQuerylike_lybmc(String querylike_lybmc) {
		this.querylike_lybmc = querylike_lybmc;
	}

	public String getQuerylike_zdmc() {
		return querylike_zdmc;
	}

	public void setQuerylike_zdmc(String querylike_zdmc) {
		this.querylike_zdmc = querylike_zdmc;
	}

}
