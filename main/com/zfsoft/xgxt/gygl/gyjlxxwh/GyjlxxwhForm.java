package com.zfsoft.xgxt.gygl.gyjlxxwh;


import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class GyjlxxwhForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	private String xh;
	private String wjid;
	private String wjxn;  //Υ��ѧ��
	private String wjxq;  //Υ��ѧ��
	private String jldldm;//���ɴ������
	private String jldlmc;//���ɴ�������
	private String jllbdm;//����������
	private String jllbmc;//�����������
	private String gyjllbdldm;
	private String gyjllbdm;
	private String dcqk;//�������
	private String wjsj;//Υ��ʱ��
	private String czr;//������
	private String bz;//��ע
	private String ylzd1;
	private String ylzd2;
	private String ylzd3;
	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
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
	/**
	 * @return the wjid
	 */
	public String getWjid() {
		return wjid;
	}
	/**
	 * @param wjidҪ���õ� wjid
	 */
	public void setWjid(String wjid) {
		this.wjid = wjid;
	}
	
	/**
	 * @return the wjxn
	 */
	public String getWjxn() {
		return wjxn;
	}
	/**
	 * @param wjxnҪ���õ� wjxn
	 */
	public void setWjxn(String wjxn) {
		this.wjxn = wjxn;
	}
	/**
	 * @return the wjxq
	 */
	public String getWjxq() {
		return wjxq;
	}
	/**
	 * @param wjxqҪ���õ� wjxq
	 */
	public void setWjxq(String wjxq) {
		this.wjxq = wjxq;
	}
	/**
	 * @return the jldldm
	 */
	public String getJldldm() {
		return jldldm;
	}
	/**
	 * @param jldldmҪ���õ� jldldm
	 */
	public void setJldldm(String jldldm) {
		this.jldldm = jldldm;
	}
	/**
	 * @return the jldlmc
	 */
	public String getJldlmc() {
		return jldlmc;
	}
	/**
	 * @param jldlmcҪ���õ� jldlmc
	 */
	public void setJldlmc(String jldlmc) {
		this.jldlmc = jldlmc;
	}
	/**
	 * @return the jllbdm
	 */
	public String getJllbdm() {
		return jllbdm;
	}
	/**
	 * @param jllbdmҪ���õ� jllbdm
	 */
	public void setJllbdm(String jllbdm) {
		this.jllbdm = jllbdm;
	}
	/**
	 * @return the jllbmc
	 */
	public String getJllbmc() {
		return jllbmc;
	}
	/**
	 * @param jllbmcҪ���õ� jllbmc
	 */
	public void setJllbmc(String jllbmc) {
		this.jllbmc = jllbmc;
	}
	/**
	 * @return the dcqk
	 */
	public String getDcqk() {
		return dcqk;
	}
	/**
	 * @param dcqkҪ���õ� dcqk
	 */
	public void setDcqk(String dcqk) {
		this.dcqk = dcqk;
	}
	/**
	 * @return the wjsj
	 */
	public String getWjsj() {
		return wjsj;
	}
	/**
	 * @param wjsjҪ���õ� wjsj
	 */
	public void setWjsj(String wjsj) {
		this.wjsj = wjsj;
	}
	/**
	 * @return the czr
	 */
	public String getCzr() {
		return czr;
	}
	/**
	 * @param czrҪ���õ� czr
	 */
	public void setCzr(String czr) {
		this.czr = czr;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * @param ylzd1Ҫ���õ� ylzd1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModelҪ���õ� searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
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
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pagesҪ���õ� pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return the gyjllbdldm
	 */
	public String getGyjllbdldm() {
		return gyjllbdldm;
	}
	/**
	 * @param gyjllbdldmҪ���õ� gyjllbdldm
	 */
	public void setGyjllbdldm(String gyjllbdldm) {
		this.gyjllbdldm = gyjllbdldm;
	}
	/**
	 * @return the gyjllbdm
	 */
	public String getGyjllbdm() {
		return gyjllbdm;
	}
	/**
	 * @param gyjllbdmҪ���õ� gyjllbdm
	 */
	public void setGyjllbdm(String gyjllbdm) {
		this.gyjllbdm = gyjllbdm;
	}
	
	
	
}
