/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-26 ����05:31:24 
 */  
package com.zfsoft.xgxt.xpjpy.bbwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-11-26 ����05:31:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BbwhForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	private String bbdm;// �������
	private String bbmc;// ��������
	private String xmdm;// ��Ŀ����
	private String bblx;//��������
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
	 * @return the bbdm
	 */
	public String getBbdm() {
		return bbdm;
	}
	/**
	 * @param bbdmҪ���õ� bbdm
	 */
	public void setBbdm(String bbdm) {
		this.bbdm = bbdm;
	}
	/**
	 * @return the bbmc
	 */
	public String getBbmc() {
		return bbmc;
	}
	/**
	 * @param bbmcҪ���õ� bbmc
	 */
	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
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
	 * @return the bblx
	 */
	public String getBblx() {
		return bblx;
	}
	/**
	 * @param bblxҪ���õ� bblx
	 */
	public void setBblx(String bblx) {
		this.bblx = bblx;
	}
	
	

}
