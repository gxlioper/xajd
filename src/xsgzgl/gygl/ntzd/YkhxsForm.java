package xsgzgl.gygl.ntzd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * ��ְͨ��-�¿���ϵ��
 * @���ߣ� qilm
 * @ʱ�䣺 2013-9-25
 * @�汾�� V1.0
 */
public class YkhxsForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	// ��ҳ
	Pages pages = new Pages();

	// �߼���ѯ
	SearchModel searchModel = new SearchModel();
	
	private String[] checkVal;
	private String ny;			//����
	private String khxs;		//����ϵ��
	private String dysskhfz;	//�������ῼ�˷�ֵ
	
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
	public String[] getCheckVal() {
		return checkVal;
	}
	
	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}
	public String getNy() {
		return ny;
	}
	public void setNy(String ny) {
		this.ny = ny;
	}
	public String getKhxs() {
		return khxs;
	}
	public void setKhxs(String khxs) {
		this.khxs = khxs;
	}
	public String getDysskhfz() {
		return dysskhfz;
	}
	public void setDysskhfz(String dysskhfz) {
		this.dysskhfz = dysskhfz;
	}
}
