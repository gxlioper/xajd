/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-16 ����09:04:42 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������_����ά������Ŀ���ͺ����ʣ�
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-16 ����09:04:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjdmModel extends ActionForm{

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String	xmxzdm;
	private String	xmxzmc;
	private String	xmlxdm;
	private String	xmlxmc;
	private String xmxz;

	public String getXmxz() {
		return xmxz;
	}

	public void setXmxz(String xmxz) {
		this.xmxz = xmxz;
	}

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
	public String getXmxzdm() {
		return xmxzdm;
	}
	public void setXmxzdm(String xmxzdm) {
		this.xmxzdm = xmxzdm;
	}
	public String getXmxzmc() {
		return xmxzmc;
	}
	public void setXmxzmc(String xmxzmc) {
		this.xmxzmc = xmxzmc;
	}
	public String getXmlxdm() {
		return xmlxdm;
	}
	public void setXmlxdm(String xmlxdm) {
		this.xmlxdm = xmlxdm;
	}
	public String getXmlxmc() {
		return xmlxmc;
	}
	public void setXmlxmc(String xmlxmc) {
		this.xmlxmc = xmlxmc;
	}
	
	
	

}
