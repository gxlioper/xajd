/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-2 ����02:24:28 
 */  
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.stlbgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-07-31 ����02:24:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class StlbglForm extends ActionForm{
	private String stlbdm; // ����
	private String stlbmc; // ����
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	public String getStlbdm() {
		return stlbdm;
	}
	public void setStlbdm(String stlbdm) {
		this.stlbdm = stlbdm;
	}
	public String getStlbmc() {
		return stlbmc;
	}
	public void setStlbmc(String stlbmc) {
		this.stlbmc = stlbmc;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
