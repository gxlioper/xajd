/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-6 ����10:23:39 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxlb;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-����Ϣ����
 * @�๦������: ����ά��-������� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-6 ����10:23:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DmwhJxlbForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String jxlbdm;
	private String jxlbmc;
	
	
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
	public String getJxlbdm() {
		return jxlbdm;
	}
	public void setJxlbdm(String jxlbdm) {
		this.jxlbdm = jxlbdm;
	}
	public String getJxlbmc() {
		return jxlbmc;
	}
	public void setJxlbmc(String jxlbmc) {
		this.jxlbmc = jxlbmc;
	}
}
