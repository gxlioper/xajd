/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����03:30:59 
 */  
package com.zfsoft.xgxt.rcsw.xxgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: ѧ����Ѫ����actionForm 
 * @���ߣ� zhangjw 
 * @ʱ�䣺 2013-4-18 ����03:26:39 
 * @�汾�� V5.1.75
 */
public class XsxxglForm extends ActionForm {

	/** 
	 * @���� serialVersionUID 
	 */ 
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String 	xxgldm	;//	����pk_xg_rcsw_xsxxgl
	private String 	xh	;//	ѧ��
	private String 	xn	;//	ѧ��
	private String 	xxsj	;//	��Ѫʱ��
	private String 	bz	;//	��ע
	public String getXxgldm() {
		return xxgldm;
	}
	public void setXxgldm(String xxgldm) {
		this.xxgldm = xxgldm;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXxsj() {
		return xxsj;
	}
	public void setXxsj(String xxsj) {
		this.xxsj = xxsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
