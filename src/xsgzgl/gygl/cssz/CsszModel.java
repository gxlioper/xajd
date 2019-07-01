package xsgzgl.gygl.cssz;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class CsszModel {
	// 通用 
	Pages pages = new Pages();

	//高级查询
	SearchModel searchModel = new SearchModel();
	
	private String kssj;	// 开始时间
	
	private String jssj;	// 结束时间
	
	private String sfqy;	// 是否启用
	
	private String[] primarykey_cbv; // 主键列表

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

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	public String[] getPrimarykey_cbv() {
		return primarykey_cbv;
	}

	public void setPrimarykey_cbv(String[] primarykeyCbv) {
		primarykey_cbv = primarykeyCbv;
	}
}
