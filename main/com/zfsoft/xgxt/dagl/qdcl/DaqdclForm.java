/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-10 ����05:25:23 
 */  
package com.zfsoft.xgxt.dagl.qdcl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�  wanghj [���ţ�1004]
 * @ʱ�䣺 2014-2-10 ����05:25:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DaqdclForm extends ActionForm {
	
	private String daqdcl_id;
	private String daqdcl_mc;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	/**
	 * @return the daqdcl_id
	 */
	public String getDaqdcl_id() {
		return daqdcl_id;
	}
	/**
	 * @param daqdcl_idҪ���õ� daqdcl_id
	 */
	public void setDaqdcl_id(String daqdcl_id) {
		this.daqdcl_id = daqdcl_id;
	}
	/**
	 * @return the daqdcl_mc
	 */
	public String getDaqdcl_mc() {
		return daqdcl_mc;
	}
	/**
	 * @param daqdcl_mcҪ���õ� daqdcl_mc
	 */
	public void setDaqdcl_mc(String daqdcl_mc) {
		this.daqdcl_mc = daqdcl_mc;
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
