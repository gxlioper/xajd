package xsgzgl.rcsw.zjbb;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

public class RcswZjbbForm extends ActionForm{
	
	Pages pages=new Pages();
	
	User user=new User();
	
	SearchModel searchModel=new SearchModel();
	
	private String xmid;

	private String sqly;
	
	private String sqid[];
	
	private String shzt;
	
	private String shyj;
	
	private String shr;
	
	private String spgw;
	
	private String zjmc;
	
	private String sfksc;
	private ExportModel exportModel = new ExportModel();
	
	public String getSpgw() {
		return spgw;
	}

	public void setSpgw(String spgw) {
		this.spgw = spgw;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String[] getSqid() {
		return sqid;
	}

	public void setSqid(String[] sqid) {
		this.sqid = sqid;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
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

	public String getXmid() {
		return xmid;
	}

	public void setXmid(String xmid) {
		this.xmid = xmid;
	}

	public String getSfksc() {
		return sfksc;
	}

	public void setSfksc(String sfksc) {
		this.sfksc = sfksc;
	}

	public String getZjmc() {
		return zjmc;
	}

	public void setZjmc(String zjmc) {
		this.zjmc = zjmc;
	}

	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}

	/**
	 * @param exportModelÒªÉèÖÃµÄ exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
}
