package xsgzgl.xtwh.general.mobilemessage;

import xgxt.comm.CommForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class MobileMessageForm extends CommForm {
	private static final long serialVersionUID = 1L;
	private String id;
	private String fsr;
	private String fssj;
	private String fsnr;
	private String sxr;
	private String lxdh;
	private String failsxr;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFsr() {
		return fsr;
	}
	public void setFsr(String fsr) {
		this.fsr = fsr;
	}
	public String getFssj() {
		return fssj;
	}
	public void setFssj(String fssj) {
		this.fssj = fssj;
	}
	public String getFsnr() {
		return fsnr;
	}
	public void setFsnr(String fsnr) {
		this.fsnr = fsnr;
	}
	public String getSxr() {
		return sxr;
	}
	public void setSxr(String sxr) {
		this.sxr = sxr;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getFailsxr() {
		return failsxr;
	}
	public void setFailsxr(String failsxr) {
		this.failsxr = failsxr;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	
}
