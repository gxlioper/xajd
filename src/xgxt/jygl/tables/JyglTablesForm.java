package xgxt.jygl.tables;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class JyglTablesForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	
	private String save_xh;//学号 
	
	private String save_jcqk;//奖惩情况
	
	private String save_byjd;//毕业鉴定
	
	private String save_zyjsdj;//职业技术等级
	
	private String save_zjjdllcj;//职业鉴定理论成绩
	
	private String save_zjjdjncj;//职业鉴定技能成绩
	
	private String save_cxdd;//操作等第
	
	private String save_xytjyj;//学院推荐意见
	
	private String save_jsdwyj;//接收单位意见
	
	private String save_bz;//备注
	
	private String nj;
	
	private String xydm;
	
	private String zydm;
	
	private String bjdm;
	
	private String[] xkkh;
	
	private String save_bynd;//毕业年度

	public String[] getXkkh() {
		return xkkh;
	}

	public void setXkkh(String[] xkkh) {
		this.xkkh = xkkh;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getSave_byjd() {
		return save_byjd;
	}

	public void setSave_byjd(String save_byjd) {
		this.save_byjd = save_byjd;
	}

	public String getSave_bz() {
		return save_bz;
	}

	public void setSave_bz(String save_bz) {
		this.save_bz = save_bz;
	}

	public String getSave_cxdd() {
		return save_cxdd;
	}

	public void setSave_cxdd(String save_cxdd) {
		this.save_cxdd = save_cxdd;
	}

	public String getSave_jcqk() {
		return save_jcqk;
	}

	public void setSave_jcqk(String save_jcqk) {
		this.save_jcqk = save_jcqk;
	}

	public String getSave_jsdwyj() {
		return save_jsdwyj;
	}

	public void setSave_jsdwyj(String save_jsdwyj) {
		this.save_jsdwyj = save_jsdwyj;
	}

	public String getSave_zjjdjncj() {
		return save_zjjdjncj;
	}

	public void setSave_zjjdjncj(String save_zjjdjncj) {
		this.save_zjjdjncj = save_zjjdjncj;
	}

	public String getSave_zjjdllcj() {
		return save_zjjdllcj;
	}

	public void setSave_zjjdllcj(String save_zjjdllcj) {
		this.save_zjjdllcj = save_zjjdllcj;
	}

	public String getSave_zyjsdj() {
		return save_zyjsdj;
	}

	public void setSave_zyjsdj(String save_zyjsdj) {
		this.save_zyjsdj = save_zyjsdj;
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

	public String getSave_xh() {
		return save_xh;
	}

	public void setSave_xh(String save_xh) {
		this.save_xh = save_xh;
	}

	public String getSave_xytjyj() {
		return save_xytjyj;
	}

	public void setSave_xytjyj(String save_xytjyj) {
		this.save_xytjyj = save_xytjyj;
	}

	public String getSave_bynd() {
		return save_bynd;
	}

	public void setSave_bynd(String saveBynd) {
		save_bynd = saveBynd;
	}
	
	
}
