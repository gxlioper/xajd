package xsgzgl.jcsj.comm;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * ��������form
 */
public class JcsjForm extends ActionForm{
	
	/* ͨ�� */
	Pages pages = new Pages();
	
	//�߼���ѯ
	SearchModel searchModel = new SearchModel();
	
	//��ѯ����
	private String query_text;//�ı��ֶ�
	private String query_sfycsj;//�Ƿ��쳣����

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
