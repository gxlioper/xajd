/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��2��4�� ����2:32:15 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.bysdzbwh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���-��֯��ϵת������ģ��
 * @�๦������: ��ҵ����֧������ά��Form
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��2��4�� ����2:32:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BysdzbwhForm extends ActionForm{
	
	private static final long serialVersionUID = -1325439137478373664L;
	
	private String type;
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	
	private String dzbdm;	//��֧������
	private String dzbmc;	//��֧������
	
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
	 * @return the dzbdm
	 */
	public String getDzbdm() {
		return dzbdm;
	}
	/**
	 * @param dzbdmҪ���õ� dzbdm
	 */
	public void setDzbdm(String dzbdm) {
		this.dzbdm = dzbdm;
	}
	/**
	 * @return the dzbmc
	 */
	public String getDzbmc() {
		return dzbmc;
	}
	/**
	 * @param dzbmcҪ���õ� dzbmc
	 */
	public void setDzbmc(String dzbmc) {
		this.dzbmc = dzbmc;
	}
	
}
