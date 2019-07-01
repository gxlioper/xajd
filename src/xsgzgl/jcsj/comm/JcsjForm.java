package xsgzgl.jcsj.comm;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 基础数据form
 */
public class JcsjForm extends ActionForm{
	
	/* 通用 */
	Pages pages = new Pages();
	
	//高级查询
	SearchModel searchModel = new SearchModel();
	
	//查询条件
	private String query_text;//文本字段
	private String query_sfycsj;//是否异常数据

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

	public String getQuery_text() {
		return query_text;
	}

	public void setQuery_text(String queryText) {
		query_text = queryText;
	}

	public String getQuery_sfycsj() {
		return query_sfycsj;
	}

	public void setQuery_sfycsj(String querySfycsj) {
		query_sfycsj = querySfycsj;
	}

	

}
