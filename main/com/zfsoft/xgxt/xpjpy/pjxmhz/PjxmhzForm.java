/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-2-22 ����05:09:28 
 */  
package com.zfsoft.xgxt.xpjpy.pjxmhz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
  * @ģ������: ��������-ͳ�ƹ���-������Ŀ����
 * @�๦������: ͳ��ÿѧ�ꡢÿѧ�ڡ�ÿ����Ŀ�Ļ�����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-2-22 ����05:09:28 
 * @�汾��Ver 5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjxmhzForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String id;// id
	private String xn;// ѧ��
	private String lxdm;// ��Ŀ���ʹ���
	private String xzdm;// ��Ŀ���ʴ���
	private String xmmc;// ��Ŀ����
	private String rowConut;
	
	
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
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}
	/**
	 * @param lxdmҪ���õ� lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	/**
	 * @return the xzdm
	 */
	public String getXzdm() {
		return xzdm;
	}
	/**
	 * @param xzdmҪ���õ� xzdm
	 */
	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmcҪ���õ� xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
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
