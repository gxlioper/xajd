/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-21 ����09:25:14 
 */  
package com.zfsoft.xgxt.xszz.sqlydmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������-�������϶�
 * @�๦������: �������ɴ���ά��
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-6-21 ����09:25:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SqlyDmwhForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String sqlydm;
	private String sqlymc;
	
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
	public String getSqlydm() {
		return sqlydm;
	}
	public void setSqlydm(String sqlydm) {
		this.sqlydm = sqlydm;
	}
	public String getSqlymc() {
		return sqlymc;
	}
	public void setSqlymc(String sqlymc) {
		this.sqlymc = sqlymc;
	}
	
}
