/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����11:26:25 
 */  
package com.zfsoft.xgxt.rcsw.sybx.hsd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @�๦������: ��ʦ��-��ҵ����
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����11:26:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HsdSybxModel extends ActionForm {

	
	private static final long serialVersionUID = 1966791940187986544L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String id;
	private String xh;
	private String xn;
	private String bdh;
	private String bdyxq;
	private String be;
	private String gmsj;
	private String je;
	private String bxgs;
	
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
	 * @return the bdh
	 */
	public String getBdh() {
		return bdh;
	}
	/**
	 * @param bdhҪ���õ� bdh
	 */
	public void setBdh(String bdh) {
		this.bdh = bdh;
	}
	/**
	 * @return the bdyxq
	 */
	public String getBdyxq() {
		return bdyxq;
	}
	/**
	 * @param bdyxqҪ���õ� bdyxq
	 */
	public void setBdyxq(String bdyxq) {
		this.bdyxq = bdyxq;
	}
	/**
	 * @return the be
	 */
	public String getBe() {
		return be;
	}
	/**
	 * @param beҪ���õ� be
	 */
	public void setBe(String be) {
		this.be = be;
	}
	/**
	 * @return the gmsj
	 */
	public String getGmsj() {
		return gmsj;
	}
	/**
	 * @param gmsjҪ���õ� gmsj
	 */
	public void setGmsj(String gmsj) {
		this.gmsj = gmsj;
	}
	/**
	 * @return the je
	 */
	public String getJe() {
		return je;
	}
	/**
	 * @param jeҪ���õ� je
	 */
	public void setJe(String je) {
		this.je = je;
	}
	/**
	 * @return the bxgs
	 */
	public String getBxgs() {
		return bxgs;
	}
	/**
	 * @param bxgsҪ���õ� bxgs
	 */
	public void setBxgs(String bxgs) {
		this.bxgs = bxgs;
	}
	
	
	
}
