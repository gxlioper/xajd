/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-19 ����09:33:09 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl.jg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-19 ����09:33:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxxKzxxglJgForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 8140417221625843289L;
	
	/** 
	 * @���� pages : ��ҳģ�� 
	 */
	private Pages pages = new Pages();
	
	/** 
	 * @���� searchModel : �߼���ѯģ�� 
	 */
	private SearchModel searchModel = new SearchModel();
	
	/** 
	 * @���� exportModel : ����ģ�� 
	 */
	private ExportModel exportModel = new ExportModel();
	
	private String jgid;
	
	private String sqid;
	
	private String xh;
	
	private String jrsj;
	
	private String sjly;
	
	private String actionType;

	private String exendDateModuleId;
	
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
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}

	/**
	 * @param sqidҪ���õ� sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	/**
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}

	/**
	 * @param jgidҪ���õ� jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
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
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionTypeҪ���õ� actionType
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the exendDateModuleId
	 */
	public String getExendDateModuleId() {
		return exendDateModuleId;
	}

	/**
	 * @param exendDateModuleIdҪ���õ� exendDateModuleId
	 */
	public void setExendDateModuleId(String exendDateModuleId) {
		this.exendDateModuleId = exendDateModuleId;
	}
	
	
}
