/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-24 ����10:54:31 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.zxsgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ-��ѯʦ����
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-24 ����10:54:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxsxxForm extends ActionForm{

	private static final long serialVersionUID = 6392868410836088459L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	
	/**
	 * ְ����(��ѯʦ���)
	 */
	private String zgh;
	/**
	 * ��ϵ�绰
	 */
	private String lxdh;
	/**
	 * ��ѯʦ����
	 */
	private String zxslv;
	/**
	 * ��ְ����
	 */
	private String zxszg;
	/**
	 * �ɽӴ�����/��
	 */
	private String kjdrs;
	/**
	 * �ڸ�״̬
	 */
	private String status;
	/**
	 * ����ʱ��
	 */
	private String createsj;
	/**
	 * �����˱��
	 */
	private String createbh;
	/**
	 * ����������
	 */
	private String createmc;
	/**
	 * ��ѯʦ���
	 */
	private String zxsjj;
	/**
	 * ����״̬0ʧЧ1����
	 */
	private String datazt;
	/**
	 * ��ѯ��ϸ��ַ
	 */
	private String address;
	
	
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
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zghҪ���õ� zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdhҪ���õ� lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return the zxslv
	 */
	public String getZxslv() {
		return zxslv;
	}
	/**
	 * @param zxslvҪ���õ� zxslv
	 */
	public void setZxslv(String zxslv) {
		this.zxslv = zxslv;
	}
	/**
	 * @return the zxszg
	 */
	public String getZxszg() {
		return zxszg;
	}
	/**
	 * @param zxszgҪ���õ� zxszg
	 */
	public void setZxszg(String zxszg) {
		this.zxszg = zxszg;
	}
	/**
	 * @return the kjdrs
	 */
	public String getKjdrs() {
		return kjdrs;
	}
	/**
	 * @param kjdrsҪ���õ� kjdrs
	 */
	public void setKjdrs(String kjdrs) {
		this.kjdrs = kjdrs;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param statusҪ���õ� status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the createsj
	 */
	public String getCreatesj() {
		return createsj;
	}
	/**
	 * @param createsjҪ���õ� createsj
	 */
	public void setCreatesj(String createsj) {
		this.createsj = createsj;
	}
	/**
	 * @return the createbh
	 */
	public String getCreatebh() {
		return createbh;
	}
	/**
	 * @param createbhҪ���õ� createbh
	 */
	public void setCreatebh(String createbh) {
		this.createbh = createbh;
	}
	/**
	 * @return the createmc
	 */
	public String getCreatemc() {
		return createmc;
	}
	/**
	 * @param createmcҪ���õ� createmc
	 */
	public void setCreatemc(String createmc) {
		this.createmc = createmc;
	}
	/**
	 * @return the zxsjj
	 */
	public String getZxsjj() {
		return zxsjj;
	}
	/**
	 * @param zxsjjҪ���õ� zxsjj
	 */
	public void setZxsjj(String zxsjj) {
		this.zxsjj = zxsjj;
	}
	/**
	 * @return the datazt
	 */
	public String getDatazt() {
		return datazt;
	}
	/**
	 * @param dataztҪ���õ� datazt
	 */
	public void setDatazt(String datazt) {
		this.datazt = datazt;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param addressҪ���õ� address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
}





