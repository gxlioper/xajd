/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-28 ����09:12:04 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����-Ժ�轱ѧ���㽭��ѧ���Ի�����
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2016-7-28 ����09:12:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DmwhForm extends ActionForm {
	private static final long serialVersionUID = 3768375193652880594L;
	private String type;
	private String zjlydm;
	private String zjlymc;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	
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
	 * @return the zjlydm
	 */
	public String getZjlydm() {
		return zjlydm;
	}
	/**
	 * @param zjlydmҪ���õ� zjlydm
	 */
	public void setZjlydm(String zjlydm) {
		this.zjlydm = zjlydm;
	}
	/**
	 * @return the zjlymc
	 */
	public String getZjlymc() {
		return zjlymc;
	}
	/**
	 * @param zjlymcҪ���õ� zjlymc
	 */
	public void setZjlymc(String zjlymc) {
		this.zjlymc = zjlymc;
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
}
