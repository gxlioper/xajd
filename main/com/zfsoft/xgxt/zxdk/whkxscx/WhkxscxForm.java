/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-23 ����10:32:56 
 */  
package com.zfsoft.xgxt.zxdk.whkxscx;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: δ����ѧ����ѯ
 * @�๦������: δ����ѧ����ѯ
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2016-8-23 ����10:32:56 
 * @�汾��V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WhkxscxForm extends ActionForm{
	private Pages pages = new Pages();//��ҳ
	private SearchModel searchModel = new SearchModel();//�߼���ѯ
	private ExportModel exportModel = new ExportModel();//�Զ��嵼��
	private String type;//����
	
	
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
}
