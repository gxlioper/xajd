/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014��6��12�� ����10:42:09 
 */
package com.zfsoft.xgxt.gygl.ssyd.plyd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����ģ��
 * @�๦������: ���������춯 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014��6��12�� ����10:42:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PlydModel extends ActionForm {

	private static final long serialVersionUID = -6392320533049746745L;
	
	private String xh;
	private String yld;
	private String yss;
	private String ycw;
	private String xld;
	private String xss;
	private String xcw;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xhҪ���õ� xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the yld
	 */
	public String getYld() {
		return yld;
	}
	/**
	 * @param yldҪ���õ� yld
	 */
	public void setYld(String yld) {
		this.yld = yld;
	}
	/**
	 * @return the yss
	 */
	public String getYss() {
		return yss;
	}
	/**
	 * @param yssҪ���õ� yss
	 */
	public void setYss(String yss) {
		this.yss = yss;
	}
	/**
	 * @return the ycw
	 */
	public String getYcw() {
		return ycw;
	}
	/**
	 * @param ycwҪ���õ� ycw
	 */
	public void setYcw(String ycw) {
		this.ycw = ycw;
	}
	/**
	 * @return the xld
	 */
	public String getXld() {
		return xld;
	}
	/**
	 * @param xldҪ���õ� xld
	 */
	public void setXld(String xld) {
		this.xld = xld;
	}
	/**
	 * @return the xss
	 */
	public String getXss() {
		return xss;
	}
	/**
	 * @param xssҪ���õ� xss
	 */
	public void setXss(String xss) {
		this.xss = xss;
	}
	/**
	 * @return the xcw
	 */
	public String getXcw() {
		return xcw;
	}
	/**
	 * @param xcwҪ���õ� xcw
	 */
	public void setXcw(String xcw) {
		this.xcw = xcw;
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
