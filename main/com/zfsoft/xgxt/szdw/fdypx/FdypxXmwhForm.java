/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-5 ����02:37:53 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:����Ա��ѵ��Ŀά��
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-7-5 ����02:35:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdypxXmwhForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String xmdm ;//��Ŀ����
	private String xmmc ;//��Ŀ����
	private String pxdd ;//��ѵ�ص�
	private String pxsj ;//��ѵ����ʱ��
	private String sqkssj ;//���뿪ʼʱ��
	private String sqjssj ;//�������ʱ��
	private String sqkg ;//���뿪��
	private String pxjj ;//��ѵ���
	private String fbsj ;//����ʱ��
	private String fbr ;//������
	private String bmdm ;//��֯����
	private String pxxs ; //��ѵѧʱ

	public String getPxxs() {
		return pxxs;
	}
	public void setPxxs(String pxxs) {
		this.pxxs = pxxs;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
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
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getPxdd() {
		return pxdd;
	}
	public void setPxdd(String pxdd) {
		this.pxdd = pxdd;
	}
	public String getPxsj() {
		return pxsj;
	}
	public void setPxsj(String pxsj) {
		this.pxsj = pxsj;
	}
	public String getSqkssj() {
		return sqkssj;
	}
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	public String getSqjssj() {
		return sqjssj;
	}
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	public String getSqkg() {
		return sqkg;
	}
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	public String getPxjj() {
		return pxjj;
	}
	public void setPxjj(String pxjj) {
		this.pxjj = pxjj;
	}
	public String getFbsj() {
		return fbsj;
	}
	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}
	public String getFbr() {
		return fbr;
	}
	public void setFbr(String fbr) {
		this.fbr = fbr;
	}
	
	
}
