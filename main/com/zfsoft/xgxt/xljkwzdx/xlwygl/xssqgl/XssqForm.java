/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-23 ����09:57:43 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xssqgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ȩ���� 
 * @�๦������: ѧ����Ȩ���� 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-23 ����09:57:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XssqForm extends ActionForm{

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String xh;
	
	private String lx;
	
	private String rzksrq;
	
	private String rzjsrq;
	
	private String sfxypssb;

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
	 * @return the lx
	 */
	public String getLx() {
		return lx;
	}

	/**
	 * @param lxҪ���õ� lx
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}

	/**
	 * @return the rzksrq
	 */
	public String getRzksrq() {
		return rzksrq;
	}

	/**
	 * @param rzksrqҪ���õ� rzksrq
	 */
	public void setRzksrq(String rzksrq) {
		this.rzksrq = rzksrq;
	}

	/**
	 * @return the rzjsrq
	 */
	public String getRzjsrq() {
		return rzjsrq;
	}

	/**
	 * @param rzjsrqҪ���õ� rzjsrq
	 */
	public void setRzjsrq(String rzjsrq) {
		this.rzjsrq = rzjsrq;
	}

	/**
	 * @return the sfxypssb
	 */
	public String getSfxypssb() {
		return sfxypssb;
	}

	/**
	 * @param sfxypssbҪ���õ� sfxypssb
	 */
	public void setSfxypssb(String sfxypssb) {
		this.sfxypssb = sfxypssb;
	}

}
