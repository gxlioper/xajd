/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-28 ����04:11:01 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcxm;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @�๦������: ���������Ŀ
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-5-28 ����04:11:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcxmModel extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String xmdm;
	private String xmmc;
	private String xmnr;
	private String jcdx;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdmҪ���õ� xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmcҪ���õ� xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the xmnr
	 */
	public String getXmnr() {
		return xmnr;
	}
	/**
	 * @param xmnrҪ���õ� xmnr
	 */
	public void setXmnr(String xmnr) {
		this.xmnr = xmnr;
	}
	/**
	 * @return the jcdx
	 */
	public String getJcdx() {
		return jcdx;
	}
	/**
	 * @param jcdxҪ���õ� jcdx
	 */
	public void setJcdx(String jcdx) {
		this.jcdx = jcdx;
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
	
	
}
