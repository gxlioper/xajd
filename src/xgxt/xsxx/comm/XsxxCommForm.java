package xgxt.xsxx.comm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class XsxxCommForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	// ��ҳ
	Pages pages = new Pages();

	// �߼���ѯ
	SearchModel searchModel = new SearchModel();

	FormFile uploadFile;// �ϴ��ļ�
	
	private String[] primarykey_checkVal;//CheckBox

	private String xh;// ѧ��

	private String xm;// ����

	private String xydm;// ѧԺ����

	private String xymc;// ѧԺ����
	
	private String zydm;// רҵ����

	private String zymc;// רҵ����

	private String bjdm;// �༶����

	private String bjmc;// �༶����

	private String nj;// �꼶
	
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

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}

	public void setPrimarykey_checkVal(String[] primarykey_checkVal) {
		this.primarykey_checkVal = primarykey_checkVal;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
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

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
}
