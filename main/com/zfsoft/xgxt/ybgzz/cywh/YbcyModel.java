/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����11:26:25 
 */  
package com.zfsoft.xgxt.ybgzz.cywh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @�๦������: �װ��Աά�� 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-1-30 ����01:56:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YbcyModel extends ActionForm {

	
	private static final long serialVersionUID = 1966791940187986544L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String id;
	private String xh;
	private String sjly;
	private String sqjrsj;
	private String sqly;
	private String sqsj;
	
	private String tcsj;
	private String tcyy;
	
	
	
	/**
	 * @return the tcsj
	 */
	public String getTcsj() {
		return tcsj;
	}
	/**
	 * @param tcsjҪ���õ� tcsj
	 */
	public void setTcsj(String tcsj) {
		this.tcsj = tcsj;
	}
	/**
	 * @return the tcyy
	 */
	public String getTcyy() {
		return tcyy;
	}
	/**
	 * @param tcyyҪ���õ� tcyy
	 */
	public void setTcyy(String tcyy) {
		this.tcyy = tcyy;
	}
	/**
	 * @return the sqjrsj
	 */
	public String getSqjrsj() {
		return sqjrsj;
	}
	/**
	 * @param sqjrsjҪ���õ� sqjrsj
	 */
	public void setSqjrsj(String sqjrsj) {
		this.sqjrsj = sqjrsj;
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
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjlyҪ���õ� sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
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
	
	
}
