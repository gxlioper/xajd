package xsgzgl.gygl.xxtj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class XxtjForm extends ActionForm {
	/* ͨ�� */
	Pages pages = new Pages();

	//�߼���ѯ
	SearchModel searchModel = new SearchModel();
	
	private String xqdm;

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

	/**
	 * @return the xqdm
	 */
	public String getXqdm() {
		return xqdm;
	}

	/**
	 * @param xqdmҪ���õ� xqdm
	 */
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	} 
}
