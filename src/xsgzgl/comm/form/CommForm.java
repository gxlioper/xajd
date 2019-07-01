package xsgzgl.comm.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class CommForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	// ��ҳ
	Pages pages = new Pages();

	// �߼���ѯ
	SearchModel searchModel = new SearchModel();

	// �ϴ��ļ�
	FormFile uploadFile;

	// ��ѡ��
	private String[] checkVal;
	
	// ��ѡ��
	private String[] primarykey_checkVal;

	// ѧУ����
	private String xxdm;

	// ѧУƴ������
	private String xxpymc;

	// �û�����
	private String yhlx;

	// ��Ա��Χ
	private String ryfw = "view_xsbfxx";;
	
	// ѧ��
	private String xh;

	// ����
	private String xm;
	
	// �꼶
	private String nj;
	
	// Ժϵ
	private String xydm;
	
	// רҵ
	private String zydm;
	
	// �༶
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
	 * @param rowConutҪ���õ� rowConut
	 */
	public void setRowConut(String rowConut) {
		this.rowConut = rowConut;
	}
	
}
