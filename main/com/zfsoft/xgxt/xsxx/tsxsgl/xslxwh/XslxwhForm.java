/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-14 ����01:48:25 
 */  
package com.zfsoft.xgxt.xsxx.tsxsgl.xslxwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-5-14 ����01:48:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XslxwhForm  extends ActionForm{
	private String xslxdm;
	private String xslxmc;
	private String type;
	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	/**
	 * @return the xslxdm
	 */
	public String getXslxdm() {
		return xslxdm;
	}
	/**
	 * @param xslxdmҪ���õ� xslxdm
	 */
	public void setXslxdm(String xslxdm) {
		this.xslxdm = xslxdm;
	}
	/**
	 * @return the xslxmc
	 */
	public String getXslxmc() {
		return xslxmc;
	}
	/**
	 * @param xslxmcҪ���õ� xslxmc
	 */
	public void setXslxmc(String xslxmc) {
		this.xslxmc = xslxmc;
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
	

}
