/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-29 ����01:47:44 
 */  
package com.zfsoft.xgxt.wjcf.cfsssq;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (������������) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-29 ����01:46:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfsssqForm extends ActionForm {

	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String ssid;            //����ID
	private String cfid;            //����ID
	private String sqsj;            //��������ʱ��
	private String sqly;            //��������
	private String ssjg;            //���߽��
	private String fjmc;            //��������
	private String ssfilepath;       
	private FormFile fj;            //����
	private String splcid;          //��������id
	
	private String returnflag;       //�˻ر�־
	
	private String xh;
	
	
	
	/**
	 * @return the ssid
	 */
	public String getSsid() {
		return ssid;
	}
	/**
	 * @param ssidҪ���õ� ssid
	 */
	public void setSsid(String ssid) {
		this.ssid = ssid;
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
	 * @return the cfid
	 */
	public String getCfid() {
		return cfid;
	}
	/**
	 * @param cfidҪ���õ� cfid
	 */
	public void setCfid(String cfid) {
		this.cfid = cfid;
	}
	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsjҪ���õ� sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqlyҪ���õ� sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @return the ssjg
	 */
	public String getSsjg() {
		return ssjg;
	}
	/**
	 * @param ssjgҪ���õ� ssjg
	 */
	public void setSsjg(String ssjg) {
		this.ssjg = ssjg;
	}
	/**
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * @param fjmcҪ���õ� fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	/**
	 * @return the ssfilepath
	 */
	public String getSsfilepath() {
		return ssfilepath;
	}
	/**
	 * @param ssfilepathҪ���õ� ssfilepath
	 */
	public void setSsfilepath(String ssfilepath) {
		this.ssfilepath = ssfilepath;
	}
	/**
	 * @return the fj
	 */
	public FormFile getFj() {
		return fj;
	}
	/**
	 * @param fjҪ���õ� fj
	 */
	public void setFj(FormFile fj) {
		this.fj = fj;
	}
	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcidҪ���õ� splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	/**
	 * @return the returnflag
	 */
	public String getReturnflag() {
		return returnflag;
	}
	/**
	 * @param returnflagҪ���õ� returnflag
	 */
	public void setReturnflag(String returnflag) {
		this.returnflag = returnflag;
	}

	
	
}
