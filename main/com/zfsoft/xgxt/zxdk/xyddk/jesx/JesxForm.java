/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-7 ����03:12:49 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.jesx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-11-7 ����03:12:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JesxForm extends ActionForm {
	private String xlccdm;
	private String xlccmc;
	private String jesx;
	private String type;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	/**
	 * @return the xlccmc
	 */
	public String getXlccmc() {
		return xlccmc;
	}
	/**
	 * @param xlccmcҪ���õ� xlccmc
	 */
	public void setXlccmc(String xlccmc) {
		this.xlccmc = xlccmc;
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
	 * @return the xlccdm
	 */
	public String getXlccdm() {
		return xlccdm;
	}
	/**
	 * @param xlccdmҪ���õ� xlccdm
	 */
	public void setXlccdm(String xlccdm) {
		this.xlccdm = xlccdm;
	}
	/**
	 * @return the jesx
	 */
	public String getJesx() {
		return jesx;
	}
	/**
	 * @param jesxҪ���õ� jesx
	 */
	public void setJesx(String jesx) {
		this.jesx = jesx;
	}
	
}
