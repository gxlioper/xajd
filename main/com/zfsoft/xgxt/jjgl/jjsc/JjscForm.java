/**
 * @部门:学工产品事业部
 * @日期：2014-8-18 下午02:32:51 
 */  
package com.zfsoft.xgxt.jjgl.jjsc;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class JjscForm extends ActionForm{

	private static final long serialVersionUID = 4169122978722692227L;
	
	private String jjgs;
	private String jjny;
	private String jjbh;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getJjgs() {
		return jjgs;
	}

	public void setJjgs(String jjgs) {
		this.jjgs = jjgs;
	}

	public String getJjny() {
		return jjny;
	}

	public void setJjny(String jjny) {
		this.jjny = jjny;
	}

	public String getJjbh() {
		return jjbh;
	}

	public void setJjbh(String jjbh) {
		this.jjbh = jjbh;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
