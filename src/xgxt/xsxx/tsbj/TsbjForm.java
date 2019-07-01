package xgxt.xsxx.tsbj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class TsbjForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String doType;//操作类型
	
	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	
	private String save_tsbjdm;//特色班级代码
	
	private String save_tsbjmc;//特色班级名称
	
	private String save_cjr;//创建人
	
	private String save_tsbjsm;//特色班级说明
	
	private String cjkssj;
	
	private String cjjssj;
	
	private String trHtml;
	
	private String tsbjmc;
	
	private String tsbjdm;
	
	private String tsbjXh;
	
	private String cjrxm;

	public String getCjrxm() {
		return cjrxm;
	}

	public void setCjrxm(String cjrxm) {
		this.cjrxm = cjrxm;
	}

	public String getTsbjXh() {
		return tsbjXh;
	}

	public void setTsbjXh(String tsbjXh) {
		this.tsbjXh = tsbjXh;
	}

	public String getTsbjdm() {
		return tsbjdm;
	}

	public void setTsbjdm(String tsbjdm) {
		this.tsbjdm = tsbjdm;
	}

	public String getTsbjmc() {
		return tsbjmc;
	}

	public void setTsbjmc(String tsbjmc) {
		this.tsbjmc = tsbjmc;
	}

	public String getTrHtml() {
		return trHtml;
	}

	public void setTrHtml(String trHtml) {
		this.trHtml = trHtml;
	}

	public String getSave_tsbjdm() {
		return save_tsbjdm;
	}

	public void setSave_tsbjdm(String save_tsbjdm) {
		this.save_tsbjdm = save_tsbjdm;
	}

	public String getSave_tsbjmc() {
		return save_tsbjmc;
	}

	public void setSave_tsbjmc(String save_tsbjmc) {
		this.save_tsbjmc = save_tsbjmc;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getSave_cjr() {
		return save_cjr;
	}

	public void setSave_cjr(String save_cjr) {
		this.save_cjr = save_cjr;
	}

	public String getSave_tsbjsm() {
		return save_tsbjsm;
	}

	public void setSave_tsbjsm(String save_tsbjsm) {
		this.save_tsbjsm = save_tsbjsm;
	}

	public String getCjjssj() {
		return cjjssj;
	}

	public void setCjjssj(String cjjssj) {
		this.cjjssj = cjjssj;
	}

	public String getCjkssj() {
		return cjkssj;
	}

	public void setCjkssj(String cjkssj) {
		this.cjkssj = cjkssj;
	}
}
