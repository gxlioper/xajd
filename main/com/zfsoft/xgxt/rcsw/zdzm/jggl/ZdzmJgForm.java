/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-7 ����02:47:25 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.jggl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-7 ����02:47:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzmJgForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : -5338289385430930034L
	 */ 
	
	private static final long serialVersionUID = -5338289385430930034L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;
	
	private String zdzmjgid;
	
	private String xh ;
	
	private String sqsj;
	
	private String sqly;
	
	private String jrsj;
	
	private String sjly;
	
	private String zdzmsqid;

	
	
	/**
	 * @���� ���޲���������
	 */
	public ZdzmJgForm() {
		super();
	}

	/**
	 * @return the zdzmjgid
	 */
	public String getZdzmjgid() {
		return zdzmjgid;
	}

	/**
	 * @param zdzmjgidҪ���õ� zdzmjgid
	 */
	public void setZdzmjgid(String zdzmjgid) {
		this.zdzmjgid = zdzmjgid;
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
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}

	/**
	 * @param sqsjҪ���õ� sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}

	/**
	 * @param sqlyҪ���õ� sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	/**
	 * @return the jrsj
	 */
	public String getJrsj() {
		return jrsj;
	}

	/**
	 * @param jrsjҪ���õ� jrsj
	 */
	public void setJrsj(String jrsj) {
		this.jrsj = jrsj;
	}

	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}

	/**
	 * @param sjlyҪ���õ� sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	/**
	 * @return the zdzmsqid
	 */
	public String getZdzmsqid() {
		return zdzmsqid;
	}

	/**
	 * @param zdzmsqidҪ���õ� zdzmsqid
	 */
	public void setZdzmsqid(String zdzmsqid) {
		this.zdzmsqid = zdzmsqid;
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
