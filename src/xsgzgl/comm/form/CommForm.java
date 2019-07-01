package xsgzgl.comm.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class CommForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	// 分页
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();

	// 上传文件
	FormFile uploadFile;

	// 复选框
	private String[] checkVal;
	
	// 复选框
	private String[] primarykey_checkVal;

	// 学校代码
	private String xxdm;

	// 学校拼音名称
	private String xxpymc;

	// 用户类型
	private String yhlx;

	// 人员范围
	private String ryfw = "view_xsbfxx";;
	
	// 学号
	private String xh;

	// 姓名
	private String xm;
	
	// 年级
	private String nj;
	
	// 院系
	private String xydm;
	
	// 专业
	private String zydm;
	
	// 班级
	private String bjdm;
	
	private String rowConut;

	public String getRyfw() {
		return ryfw;
	}

	public void setRyfw(String ryfw) {
		this.ryfw = ryfw;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
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

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}

	public void setPrimarykey_checkVal(String[] primarykeyCheckVal) {
		primarykey_checkVal = primarykeyCheckVal;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
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

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getXxpymc() {
		return xxpymc;
	}

	public void setXxpymc(String xxpymc) {
		this.xxpymc = xxpymc;
	}

	public String getXxdm() {
		return xxdm;
	}

	public void setXxdm(String xxdm) {
		this.xxdm = xxdm;
	}

	public String getYhlx() {
		return yhlx;
	}

	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}

	/**
	 * @return the rowConut
	 */
	public String getRowConut() {
		return rowConut;
	}

	/**
	 * @param rowConut要设置的 rowConut
	 */
	public void setRowConut(String rowConut) {
		this.rowConut = rowConut;
	}
	
}
