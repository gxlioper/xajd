package com.zfsoft.xgxt.xsxx.xjyd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: ѧ���춯
 * @���ߣ� Qilm[����:964]
 * @ʱ�䣺 2013-11-28 ����09:40:48 
 * @�汾�� V5.12.20
 */
public class XjydForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	/**
	 * ѧ�������롾ԭ��
	 */
	private String xjlbdmold;
	
	/**
	 * ѧ��������
	 */
	private String xjlbdm;

	/**
	 * ѧ���������
	 */
	private String xjlbmc;
	/**
	 * �Ƿ���ѧ��
	 */
	private String sfyxj;
	/**
	 * �Ƿ���ѧ������
	 */
	private String sfyxjmc;
	/**
	 * �Ƿ���У
	 */
	private String sfzx;
	/**
	 * �Ƿ���У����
	 */
	private String sfzxmc;
	
	/**
	 * �������
	 */
	private String shlcid;
	/**
	 * �������
	 */
	private String shlcmc;
	
	/**
	 * �Ƿ���Ҫ�����༶
	 */
	private String sftjbj;
	/**
	 * �Ƿ���Ҫ¼����ֹʱ��
	 */
	private String lrqzsj;
	
	/**
	 * �Ƿ������
	 */
	private String sfksq;
	/**
	 * ���뿪ʼʱ��
	 */
	private String sqkssj;
	/**
	 * �������ʱ��
	 */
	private String sqjssj;
	
	private String xzsfkq; // ѧ���Ƿ���(����ʦ��)
	
	private String xxsfkq; // ��Դȥ��ѧУ�Ƿ���(����ʦ��)
	
	public String getXzsfkq() {
		return xzsfkq;
	}

	public void setXzsfkq(String xzsfkq) {
		this.xzsfkq = xzsfkq;
	}

	public String getXxsfkq() {
		return xxsfkq;
	}

	public void setXxsfkq(String xxsfkq) {
		this.xxsfkq = xxsfkq;
	}

	public String getShlcmc() {
		return shlcmc;
	}

	public void setShlcmc(String shlcmc) {
		this.shlcmc = shlcmc;
	}

	public String getShlcid() {
		return shlcid;
	}

	public void setShlcid(String shlcid) {
		this.shlcid = shlcid;
	}

	public String getSftjbj() {
		return sftjbj;
	}

	public void setSftjbj(String sftjbj) {
		this.sftjbj = sftjbj;
	}

	public String getSfksq() {
		return sfksq;
	}

	public void setSfksq(String sfksq) {
		this.sfksq = sfksq;
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

	public String getXjlbdmold() {
		return xjlbdmold;
	}

	public void setXjlbdmold(String xjlbdmold) {
		this.xjlbdmold = xjlbdmold;
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

	public String getXjlbdm() {
		return xjlbdm;
	}

	public void setXjlbdm(String xjlbdm) {
		this.xjlbdm = xjlbdm;
	}

	public String getXjlbmc() {
		return xjlbmc;
	}

	public void setXjlbmc(String xjlbmc) {
		this.xjlbmc = xjlbmc;
	}

	public String getSfyxj() {
		return sfyxj;
	}

	public void setSfyxj(String sfyxj) {
		this.sfyxj = sfyxj;
	}

	public String getSfyxjmc() {
		return sfyxjmc;
	}

	public void setSfyxjmc(String sfyxjmc) {
		this.sfyxjmc = sfyxjmc;
	}

	public String getSfzx() {
		return sfzx;
	}

	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}

	public String getSfzxmc() {
		return sfzxmc;
	}

	public void setSfzxmc(String sfzxmc) {
		this.sfzxmc = sfzxmc;
	}

	public String getLrqzsj() {
		return lrqzsj;
	}

	public void setLrqzsj(String lrqzsj) {
		this.lrqzsj = lrqzsj;
	}	
	
}
