/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����10:29:04 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwlx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ�ְ������actionForm
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-7 ����10:29:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwlxForm extends ActionForm{

	/** 
	 * @���� serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String lxdm ;//ְ������
	private String lxmc ;//��������
	private String bz ;//��ע
	private String splc;//��������   �����ֶ� 2016-10-25
	public String getLxdm() {
		return lxdm;
	}
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	/**
	 * @return the splc �����ֶ� ��������
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splcҪ���õ� splc �����ֶ� ��������
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getLxmc() {
		return lxmc;
	}
	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
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
	

}
