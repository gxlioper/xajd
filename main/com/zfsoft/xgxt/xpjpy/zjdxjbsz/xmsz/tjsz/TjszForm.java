/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-4-18 ����08:16:16 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.tjsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������-��Ŀ����-��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-4-18 ����08:17:53 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjszForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	private String id;// ID
	private String xmdm;// ��Ŀ����
	private String tjdm;// ��������
	private String yyfw;// Ӧ�÷�Χ
	private String gxdm;// ��ϵ����
	private String tjz;// ����ֵ
	private String ylzq;// ��������
	
	
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
	}
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
	 * @return the tjdm
	 */
	public String getTjdm() {
		return tjdm;
	}
	/**
	 * @param tjdmҪ���õ� tjdm
	 */
	public void setTjdm(String tjdm) {
		this.tjdm = tjdm;
	}
	/**
	 * @return the yyfw
	 */
	public String getYyfw() {
		return yyfw;
	}
	/**
	 * @param yyfwҪ���õ� yyfw
	 */
	public void setYyfw(String yyfw) {
		this.yyfw = yyfw;
	}
	/**
	 * @return the gxdm
	 */
	public String getGxdm() {
		return gxdm;
	}
	/**
	 * @param gxdmҪ���õ� gxdm
	 */
	public void setGxdm(String gxdm) {
		this.gxdm = gxdm;
	}
	/**
	 * @return the tjz
	 */
	public String getTjz() {
		return tjz;
	}
	/**
	 * @param tjzҪ���õ� tjz
	 */
	public void setTjz(String tjz) {
		this.tjz = tjz;
	}
	/**
	 * @return the ylzq
	 */
	public String getYlzq() {
		return ylzq;
	}
	/**
	 * @param ylzqҪ���õ� ylzq
	 */
	public void setYlzq(String ylzq) {
		this.ylzq = ylzq;
	}
}
