/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-8 ����02:15:49 
 */  
package com.zfsoft.xgxt.jygl.zfss;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ��������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� huj
 * @ʱ�䣺 2013-6-8 ����02:15:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JzygForm extends ActionForm {

	private static final long serialVersionUID = -60909231L;

	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String zgh;
	private String zglx;
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
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getZglx() {
		return zglx;
	}
	public void setZglx(String zglx) {
		this.zglx = zglx;
	}
	
	

}
