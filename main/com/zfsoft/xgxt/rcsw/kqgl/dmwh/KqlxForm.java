/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-6 ����09:35:38 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ڹ���ģ��
 * @�๦������: �������ʹ���ά��
 * @���ߣ� �ո־�[����:1075]
 * @ʱ�䣺 2014-6-6 ����09:35:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqlxForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String kqlxdm; //�������ʹ���
	private String kqlxmc; //������������
	
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
	public String getKqlxdm() {
		return kqlxdm;
	}
	public void setKqlxdm(String kqlxdm) {
		this.kqlxdm = kqlxdm;
	}
	public String getKqlxmc() {
		return kqlxmc;
	}
	public void setKqlxmc(String kqlxmc) {
		this.kqlxmc = kqlxmc;
	}
	
}
