/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-21 ����10:08:22 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm.pjxzdm;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-21 ����10:08:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjxzdmForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String xmxzdm;	//��Ŀ���ʴ���
	private String xmxzmc;	//��Ŀ��������
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
	public String getXmxzdm() {
		return xmxzdm;
	}
	public void setXmxzdm(String xmxzdm) {
		this.xmxzdm = xmxzdm;
	}
	public String getXmxzmc() {
		return xmxzmc;
	}
	public void setXmxzmc(String xmxzmc) {
		this.xmxzmc = xmxzmc;
	}
	
	

}
