/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-2-6 ����05:34:13 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.fwmddc;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ������������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-2-6 ����05:34:13 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FwmddcForm extends ActionForm{
	
	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	
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
}
