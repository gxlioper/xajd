/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-4-20 ����03:08:35 
 */  
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxdmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-���ڷ�У����ģ�飨�ൺ����ѧԺ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� lgx[����:1553]
 * @ʱ�䣺 2018-4-20 ����03:08:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
@SuppressWarnings("serial")
public class JqfxDmwhForm extends ActionForm{
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private String fxdm;//��У����
	private String fxmc;//��У����
	
	
	/**
	 * @return the fxdm
	 */
	public String getFxdm() {
		return fxdm;
	}
	/**
	 * @param fxdmҪ���õ� fxdm
	 */
	public void setFxdm(String fxdm) {
		this.fxdm = fxdm;
	}
	/**
	 * @return the fxmc
	 */
	public String getFxmc() {
		return fxmc;
	}
	/**
	 * @param fxmcҪ���õ� fxmc
	 */
	public void setFxmc(String fxmc) {
		this.fxmc = fxmc;
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
	

    
	
}