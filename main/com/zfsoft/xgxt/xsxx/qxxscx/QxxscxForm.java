/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-23 ����03:49:48 
 */  
package com.zfsoft.xgxt.xsxx.qxxscx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ����ģ��
 * @�๦������: ȫУѧ����ѯ
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2016-8-23 ����03:49:48 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QxxscxForm extends ActionForm{
	private Pages pages = new Pages();//��ҳ
	private SearchModel searchModel = new SearchModel();//�߼���ѯ
	private String type;//����
	
	
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
}
