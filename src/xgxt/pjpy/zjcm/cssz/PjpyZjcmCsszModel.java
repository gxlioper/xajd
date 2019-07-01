package xgxt.pjpy.zjcm.cssz;

import xgxt.pjpy.tablesmodel.PjpyModel;
import xgxt.utils.Pages;

public class PjpyZjcmCsszModel extends PjpyModel {

	private String jxjlb;
	private String key;
	private String fs;
	private String cpfw;
	private String jxjbl;
	
//	 Õ®”√∑÷“≥
	Pages pages = new Pages();
	public String getJxjlb() {
		return jxjlb;
	}
	public void setJxjlb(String jxjlb) {
		this.jxjlb = jxjlb;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getFs() {
		return fs;
	}
	public void setFs(String fs) {
		this.fs = fs;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getCpfw() {
		return cpfw;
	}
	public void setCpfw(String cpfw) {
		this.cpfw = cpfw;
	}
	public String getJxjbl() {
		return jxjbl;
	}
	public void setJxjbl(String jxjbl) {
		this.jxjbl = jxjbl;
	}
	
}
