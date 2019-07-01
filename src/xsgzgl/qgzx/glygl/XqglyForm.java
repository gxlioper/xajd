package xsgzgl.qgzx.glygl;

import org.apache.struts.action.ActionForm;
import org.mortbay.html.Page;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class XqglyForm extends ActionForm {
	private String zgh;
	private String xq;
	private Pages pages = new Pages();
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
}
