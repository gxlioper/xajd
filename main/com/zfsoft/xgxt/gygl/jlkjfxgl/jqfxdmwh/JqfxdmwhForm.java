/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-19 ����10:01:13 
 */  
package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxdmwh;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ������ڷ�У����ά������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� Dulq[����:995]
 * @ʱ�䣺 2016-2-19 ����10:01:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
@SuppressWarnings("serial")
public class JqfxdmwhForm extends ActionForm{
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private String fxdm;//��У����
	private String fxmc;//��У����
	
	
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
	
    
	
}
