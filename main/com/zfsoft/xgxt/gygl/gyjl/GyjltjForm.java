/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-27 ����04:14:46 
 */  
package com.zfsoft.xgxt.gygl.gyjl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����ģ��
 * @�๦������: ��Ԣ������ϢDAO
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-4-27 ����04:09:18 
 * @�汾�� V5.9.17
 * @�޸ļ�¼:   
 */

public class GyjltjForm   extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
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
	
	

}
