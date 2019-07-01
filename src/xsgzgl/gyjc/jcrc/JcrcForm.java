package xsgzgl.gyjc.jcrc;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class JcrcForm extends ActionForm {
	private String guid;
	private String ccrq;
	private String jzrq;
	private String wsjc;
	private String aqjc;
	private String jljc;
	private String js;
	private String xydm;
	private String xn;
	private String xq;
	private String ls;
	private String type;
	private String[] lddms;
	private String[] xydms;
	private String[] jcbls;
	private SearchModel searchModel = new SearchModel();
	public String[] getLddms() {
		return lddms;
	}
	public void setLddms(String[] lddms) {
		this.lddms = lddms;
	}
	public String[] getXydms() {
		return xydms;
	}
	public void setXydms(String[] xydms) {
		this.xydms = xydms;
	}
	public String[] getJcbls() {
		return jcbls;
	}
	public void setJcbls(String[] jcbls) {
		this.jcbls = jcbls;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLs() {
		return ls;
	}
	public void setLs(String ls) {
		this.ls = ls;
	}
	
	public String getXydm() {
		return xydm;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
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
	private Pages pages = new Pages();
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getCcrq() {
		return ccrq;
	}
	public void setCcrq(String ccrq) {
		this.ccrq = ccrq;
	}
	public String getJzrq() {
		return jzrq;
	}
	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}
	public String getWsjc() {
		return wsjc;
	}
	public void setWsjc(String wsjc) {
		this.wsjc = wsjc;
	}
	public String getAqjc() {
		return aqjc;
	}
	public void setAqjc(String aqjc) {
		this.aqjc = aqjc;
	}
	public String getJljc() {
		return jljc;
	}
	public void setJljc(String jljc) {
		this.jljc = jljc;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
