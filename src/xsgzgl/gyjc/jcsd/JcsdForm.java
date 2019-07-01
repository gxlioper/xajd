package xsgzgl.gyjc.jcsd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class JcsdForm extends ActionForm {
	private String xydm;
	private String zgh;
	private String jjlx;
	private String js;
	private SearchModel searchModel =  new SearchModel();
	private Pages pages = new Pages();
	private String type;
	private String sffp;
	private String[] xydms;
	private String[] zghs;
	public String[] getXydms() {
		return xydms;
	}
	public void setXydms(String[] xydms) {
		this.xydms = xydms;
	}
	public String[] getZghs() {
		return zghs;
	}
	public void setZghs(String[] zghs) {
		this.zghs = zghs;
	}
	
	public String getSffp() {
		return sffp;
	}
	public void setSffp(String sffp) {
		this.sffp = sffp;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getJjlx() {
		return jjlx;
	}
	public void setJjlx(String jjlx) {
		this.jjlx = jjlx;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
