/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-25 ����09:07:01 
 */  
package com.zfsoft.xgxt.wjcf.cfyydmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (����ԭ�����ά��) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-25 ����09:07:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfyydmForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String cfyydm;            //����ԭ�����
	private String cfyymc;            //����ԭ������
	private String kffs;              //�۷ַ���
	private String cjsj;

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
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
	 * @return the cfyydm
	 */
	public String getCfyydm() {
		return cfyydm;
	}
	/**
	 * @param cfyydmҪ���õ� cfyydm
	 */
	public void setCfyydm(String cfyydm) {
		this.cfyydm = cfyydm;
	}
	/**
	 * @return the cfyymc
	 */
	public String getCfyymc() {
		return cfyymc;
	}
	/**
	 * @param cfyymcҪ���õ� cfyymc
	 */
	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}
	public String getKffs() {
		return kffs;
	}
	public void setKffs(String kffs) {
		this.kffs = kffs;
	}
	
	
}
