/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-13 ����05:25:23 
 */  
package com.zfsoft.xgxt.dagl.qdmb;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�  wanghj [���ţ�1004]
 * @ʱ�䣺 2014-2-13 ����05:25:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DaqdmbForm extends ActionForm {
	
	private String daqdmb_id;
	private String daqdmb_mc;
	private String qyzt;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	/**
	 * @return the daqdmb_id
	 */
	public String getDaqdmb_id() {
		return daqdmb_id;
	}
	/**
	 * @param daqdmb_idҪ���õ� daqdmb_id
	 */
	public void setDaqdmb_id(String daqdmb_id) {
		this.daqdmb_id = daqdmb_id;
	}
	/**
	 * @return the daqdmb_mc
	 */
	public String getDaqdmb_mc() {
		return daqdmb_mc;
	}
	/**
	 * @param daqdmb_mcҪ���õ� daqdmb_mc
	 */
	public void setDaqdmb_mc(String daqdmb_mc) {
		this.daqdmb_mc = daqdmb_mc;
	}
	
	/**
	 * @return the qyzt
	 */
	public String getQyzt() {
		return qyzt;
	}
	/**
	 * @param qyztҪ���õ� qyzt
	 */
	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
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

}
