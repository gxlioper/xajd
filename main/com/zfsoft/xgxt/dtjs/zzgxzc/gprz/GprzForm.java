/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��2��14�� ����3:34:39 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.gprz;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���-��֯��ϵת��-������־ģ��
 * @�๦������: Form
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��2��14�� ����3:34:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GprzForm extends ActionForm{
	
	private String type;
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	private SearchModel searchModel = new SearchModel();
	
	
	private String id;		//��־id
	private String xgsj;	//�޸�ʱ��
	private String xgr;		//�޸���
	private String xh;	//ѧ��
	private String xgqjl;	//�޸�ǰ��¼
	private String xghjl;	//�޸ĺ��¼
	private String gpyy;	//����ԭ��
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
	 * @return the xgsj
	 */
	public String getXgsj() {
		return xgsj;
	}
	/**
	 * @param xgsjҪ���õ� xgsj
	 */
	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}
	/**
	 * @return the xgr
	 */
	public String getXgr() {
		return xgr;
	}
	/**
	 * @param xgrҪ���õ� xgr
	 */
	public void setXgr(String xgr) {
		this.xgr = xgr;
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
	 * @return the xgqjl
	 */
	public String getXgqjl() {
		return xgqjl;
	}
	/**
	 * @param xgqjlҪ���õ� xgqjl
	 */
	public void setXgqjl(String xgqjl) {
		this.xgqjl = xgqjl;
	}
	/**
	 * @return the xghjl
	 */
	public String getXghjl() {
		return xghjl;
	}
	/**
	 * @param xghjlҪ���õ� xghjl
	 */
	public void setXghjl(String xghjl) {
		this.xghjl = xghjl;
	}
	/**
	 * @return the gpyy
	 */
	public String getGpyy() {
		return gpyy;
	}
	/**
	 * @param gpyyҪ���õ� gpyy
	 */
	public void setGpyy(String gpyy) {
		this.gpyy = gpyy;
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
	
	
}
