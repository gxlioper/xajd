/**
 * @����:ѧ����Ʒ��1����
 * @���ڣ�2017-6-22 ����09:41:52 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.zcwh;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �۲��ά��
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-6-22 ����09:41:52 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcwhForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel();/*�߼���ѯ*/
	private ExportModel exportModel = new ExportModel();/*����*/
	private Pages pages = new Pages();/*��ҳ*/
	private String doType;/*����*/
	private String editType;/*�ж��۲���Ŀ�Ǳ༭ģʽ���ǲ鿴ģʽ*/
	
	private String id;/*ID*/
	private String xh;/*ѧ��*/
	private String xn;/*ѧ��*/
	private String fs;/*����*/
	private String lrr;/*¼����*/
	private String lrsj;/*¼��ʱ��*/
	private String xmdm;/*��Ŀ����*/
	private FormFile importFile;/*����*/
	private String qxyy;/*ȡ���ύ�۲��ԭ��*/
	private String xmdms;
	private String zcxmdmForTop;
	
	
	
	/**
	 * @return the zcxmdmForTop
	 */
	public String getZcxmdmForTop() {
		return zcxmdmForTop;
	}
	/**
	 * @param zcxmdmForTopҪ���õ� zcxmdmForTop
	 */
	public void setZcxmdmForTop(String zcxmdmForTop) {
		this.zcxmdmForTop = zcxmdmForTop;
	}
	/**
	 * @return the qxyy
	 */
	public String getQxyy() {
		return qxyy;
	}
	/**
	 * @param qxyyҪ���õ� qxyy
	 */
	public void setQxyy(String qxyy) {
		this.qxyy = qxyy;
	}
	/**
	 * @return the importFile
	 */
	public FormFile getImportFile() {
		return importFile;
	}
	/**
	 * @param importFileҪ���õ� importFile
	 */
	public void setImportFile(FormFile importFile) {
		this.importFile = importFile;
	}
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdmҪ���õ� xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
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
	 * @return the doType
	 */
	public String getDoType() {
		return doType;
	}
	/**
	 * @param doTypeҪ���õ� doType
	 */
	public void setDoType(String doType) {
		this.doType = doType;
	}
	/**
	 * @return the editType
	 */
	public String getEditType() {
		return editType;
	}
	/**
	 * @param editTypeҪ���õ� editType
	 */
	public void setEditType(String editType) {
		this.editType = editType;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xnҪ���õ� xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the fs
	 */
	public String getFs() {
		return fs;
	}
	/**
	 * @param fsҪ���õ� fs
	 */
	public void setFs(String fs) {
		this.fs = fs;
	}
	/**
	 * @return the lrr
	 */
	public String getLrr() {
		return lrr;
	}
	/**
	 * @param lrrҪ���õ� lrr
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	/**
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @param lrsjҪ���õ� lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	/**
	 * @return the xmdms
	 */
	public String getXmdms() {
		return xmdms;
	}
	/**
	 * @param xmdmsҪ���õ� xmdms
	 */
	public void setXmdms(String xmdms) {
		this.xmdms = xmdms;
	}
}
