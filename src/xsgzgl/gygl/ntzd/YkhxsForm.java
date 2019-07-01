package xsgzgl.gygl.ntzd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * 南通职大-月考核系数
 * @作者： qilm
 * @时间： 2013-9-25
 * @版本： V1.0
 */
public class YkhxsForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	// 分页
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();
	
	private String[] checkVal;
	private String ny;			//年月
	private String khxs;		//考核系数
	private String dysskhfz;	//当月宿舍考核分值
	
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
