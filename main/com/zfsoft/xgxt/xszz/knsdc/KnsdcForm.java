package com.zfsoft.xgxt.xszz.knsdc;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class KnsdcForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String dcdm ;//���δ���
	private String dcmc ;//��������
	private String xmsm ;//��Ŀ˵��
	private String yydm; //����ԭ��
	private String yymc;// ����ԭ������
	
	private String xh;//����ԭ�����
	
	private String zme;//������������

	private String sfqy;//�Ƿ�����
	
	/**
	 * @return the zme
	 */
	public String getZme() {
		return zme;
	}
	/**
	 * @param zmeҪ���õ� zme
	 */
	public void setZme(String zme) {
		this.zme = zme;
	}
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xhҪ���õ� xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getDcdm() {
		return dcdm;
	}
	public void setDcdm(String dcdm) {
		this.dcdm = dcdm;
	}
	public String getDcmc() {
		return dcmc;
	}
	public void setDcmc(String dcmc) {
		this.dcmc = dcmc;
	}
	public String getXmsm() {
		return xmsm;
	}
	public void setXmsm(String xmsm) {
		this.xmsm = xmsm;
	}
	/**
	 * @return the yydm
	 */
	public String getYydm() {
		return yydm;
	}
	/**
	 * @param yydmҪ���õ� yydm
	 */
	public void setYydm(String yydm) {
		this.yydm = yydm;
	}
	/**
	 * @return the yymc
	 */
	public String getYymc() {
		return yymc;
	}
	/**
	 * @param yymcҪ���õ� yymc
	 */
	public void setYymc(String yymc) {
		this.yymc = yymc;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
}
