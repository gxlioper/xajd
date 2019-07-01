package xgxt.pjpy.comm.pjpy.pjlc.jgcx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.pjpy.comm.pjpy.PjpyCommForm;
import xgxt.utils.Pages;

public class PjpyJgcxForm extends PjpyCommForm {

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();

	private SearchModel searchModel = new SearchModel();

	private String xmdm;

	private String xmlx;

	private String xmxz;

	private String xmfw;

	private String nj;

	private String xydm;

	private String zydm;

	private String bjdm;

	private String sqkssj;

	private String sqjssj;

	private String shzt;

	private String[] lcmcValue;

	private String moreTerm;

	private String xmmc;

	public String getMoreTerm() {
		return moreTerm;
	}

	public void setMoreTerm(String moreTerm) {
		this.moreTerm = moreTerm;
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

	public String getSqjssj() {
		return sqjssj;
	}

	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}

	public String getSqkssj() {
		return sqkssj;
	}

	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
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

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String[] getLcmcValue() {
		return lcmcValue;
	}

	public void setLcmcValue(String[] lcmcValue) {
		this.lcmcValue = lcmcValue;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

}
