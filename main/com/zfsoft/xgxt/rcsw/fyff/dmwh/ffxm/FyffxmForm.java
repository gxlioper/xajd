/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-2 ����01:44:20 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.ffxm;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-���÷���-��������ά��-������Ŀ
 * @�๦������: 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-4-2 ����01:44:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FyffxmForm extends ActionForm {

	
	private static final long serialVersionUID = -3059773922137100902L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String ffxmdm;	//������Ŀ����
	private String ffxmmc;	//������Ŀ����
	private String mrffje;	//Ĭ�Ϸ��Ž��
	private String fffs;	//���ŷ�ʽ
	
	
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
	public String getFfxmdm() {
		return ffxmdm;
	}
	public void setFfxmdm(String ffxmdm) {
		this.ffxmdm = ffxmdm;
	}
	public String getFfxmmc() {
		return ffxmmc;
	}
	public void setFfxmmc(String ffxmmc) {
		this.ffxmmc = ffxmmc;
	}
	public String getMrffje() {
		return mrffje;
	}
	public void setMrffje(String mrffje) {
		this.mrffje = mrffje;
	}
	public String getFffs() {
		return fffs;
	}
	public void setFffs(String fffs) {
		this.fffs = fffs;
	}
	
	
	

}
