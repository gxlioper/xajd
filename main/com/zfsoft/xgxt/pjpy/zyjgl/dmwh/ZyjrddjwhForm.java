/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-12-21 ����01:16:25 
 */  
package com.zfsoft.xgxt.pjpy.zyjgl.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-רҵ������-����ά��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2015-12-21 ����01:16:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyjrddjwhForm extends ActionForm{
	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type; //��������
	private String rddjdm; //�϶��ȼ�����
	private String rddjmc; //�϶��ȼ�����
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
	/**
	 * @return the rddjdm
	 */
	public String getRddjdm() {
		return rddjdm;
	}
	/**
	 * @param rddjdmҪ���õ� rddjdm
	 */
	public void setRddjdm(String rddjdm) {
		this.rddjdm = rddjdm;
	}
	/**
	 * @return the rddjmc
	 */
	public String getRddjmc() {
		return rddjmc;
	}
	/**
	 * @param rddjmcҪ���õ� rddjmc
	 */
	public void setRddjmc(String rddjmc) {
		this.rddjmc = rddjmc;
	}
	
	
	
	
}
