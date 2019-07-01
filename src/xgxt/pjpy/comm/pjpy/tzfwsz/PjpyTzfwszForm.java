package xgxt.pjpy.comm.pjpy.tzfwsz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class PjpyTzfwszForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private Pages pages = new Pages();

	private String pjxn;
	
	private String pjxq;
	
	private String pjnd;
	
	private String xmdm;
	
	private String xmlx;
	
	private String xmxz;
	
	private String xmfw;
	
	private String[] tzxmdm;
	
	private String xmmc;

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String[] getTzxmdm() {
		return tzxmdm;
	}

	public void setTzxmdm(String[] tzxmdm) {
		this.tzxmdm = tzxmdm;
	}

	public String getPjnd() {
		return pjnd;
	}

	public void setPjnd(String pjnd) {
		this.pjnd = pjnd;
	}

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

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXmfw() {
		return xmfw;
	}

	public void setXmfw(String xmfw) {
		this.xmfw = xmfw;
	}

	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}

	public String getXmxz() {
		return xmxz;
	}

	public void setXmxz(String xmxz) {
		this.xmxz = xmxz;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
