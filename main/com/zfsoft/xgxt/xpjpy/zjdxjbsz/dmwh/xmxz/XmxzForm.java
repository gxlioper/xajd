/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-3-9 ����09:43:20 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmxz;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����ά��_��Ŀ����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-3-9 ����09:43:20 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmxzForm extends ActionForm{
	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();
	private String type;    //����
	private SearchModel searchModel = new SearchModel();
	private String xzdm;	//��Ŀ���ʴ���
	private String xzmc;	//��Ŀ��������
	
	
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
	 * @return the xzdm
	 */
	public String getXzdm() {
		return xzdm;
	}
	/**
	 * @param xzdmҪ���õ� xzdm
	 */
	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}
	/**
	 * @return the xzmc
	 */
	public String getXzmc() {
		return xzmc;
	}
	/**
	 * @param xzmcҪ���õ� xzmc
	 */
	public void setXzmc(String xzmc) {
		this.xzmc = xzmc;
	}
}
