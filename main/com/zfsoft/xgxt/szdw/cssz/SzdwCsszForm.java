/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-2 ����06:54:31 
 */  
package com.zfsoft.xgxt.szdw.cssz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:�������� ActionForm
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-7-2 ����06:53:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class SzdwCsszForm extends ActionForm{

	/** 
	 * @���� serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	private String key;
	
	private String kg;
	
	private String kssj;
	
	private String jssj;
	
	private String splc;
	
	private String sqcs;
	
	private String ms;
	
	private String isopen;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKg() {
		return kg;
	}

	public void setKg(String kg) {
		this.kg = kg;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getSplc() {
		return splc;
	}

	public void setSplc(String splc) {
		this.splc = splc;
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

	public String getSqcs() {
		return sqcs;
	}

	public void setSqcs(String sqcs) {
		this.sqcs = sqcs;
	}

	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getIsopen() {
		return isopen;
	}

	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
	
}
