package com.zfsoft.xgxt.xszz.knsrdnew.comm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class KnsrdForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	// ��ҳ
	Pages pages = new Pages();

	// �߼���ѯ
	SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();

	FormFile uploadFile;// �ϴ��ļ�

	private String xh;// ѧ��

	private String xm;// ����

	private String yclx;//�쳣��������
	
	private String[] primarykey_checkVal;// CheckBox
	
	private String ylzd2;// Ԥ���ֶ�2
	
	private String ylzd3;// Ԥ���ֶ�3
	
	private String ylzd4;// Ԥ���ֶ�4
	
	private String ylzd5;// Ԥ���ֶ�5
	
	private String ylzd6;// Ԥ���ֶ�6
	
	private String ylzd7;// Ԥ���ֶ�7
	
	private String ylzd8;// Ԥ���ֶ�8
	
	private String ylzd9;// Ԥ���ֶ�9
	
	private String ylzd10;// Ԥ���ֶ�10

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

	public String getYclx() {
		return yclx;
	}

	public void setYclx(String yclx) {
		this.yclx = yclx;
	}

	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}

	/**
	 * @param exportModelҪ���õ� exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	/**
	 * @return the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}

	/**
	 * @param ylzd2Ҫ���õ� ylzd2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}

	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}

	/**
	 * @param ylzd3Ҫ���õ� ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}

	/**
	 * @return the ylzd4
	 */
	public String getYlzd4() {
		return ylzd4;
	}

	/**
	 * @param ylzd4Ҫ���õ� ylzd4
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}

	/**
	 * @return the ylzd5
	 */
	public String getYlzd5() {
		return ylzd5;
	}

	/**
	 * @param ylzd5Ҫ���õ� ylzd5
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}

	/**
	 * @return the ylzd6
	 */
	public String getYlzd6() {
		return ylzd6;
	}

	/**
	 * @param ylzd6Ҫ���õ� ylzd6
	 */
	public void setYlzd6(String ylzd6) {
		this.ylzd6 = ylzd6;
	}

	/**
	 * @return the ylzd7
	 */
	public String getYlzd7() {
		return ylzd7;
	}

	/**
	 * @param ylzd7Ҫ���õ� ylzd7
	 */
	public void setYlzd7(String ylzd7) {
		this.ylzd7 = ylzd7;
	}

	/**
	 * @return the ylzd8
	 */
	public String getYlzd8() {
		return ylzd8;
	}

	/**
	 * @param ylzd8Ҫ���õ� ylzd8
	 */
	public void setYlzd8(String ylzd8) {
		this.ylzd8 = ylzd8;
	}

	/**
	 * @return the ylzd9
	 */
	public String getYlzd9() {
		return ylzd9;
	}

	/**
	 * @param ylzd9Ҫ���õ� ylzd9
	 */
	public void setYlzd9(String ylzd9) {
		this.ylzd9 = ylzd9;
	}

	/**
	 * @return the ylzd10
	 */
	public String getYlzd10() {
		return ylzd10;
	}

	/**
	 * @param ylzd10Ҫ���õ� ylzd10
	 */
	public void setYlzd10(String ylzd10) {
		this.ylzd10 = ylzd10;
	}
}
