package com.zfsoft.xgxt.xlzx.zxswh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: (��ѯʦ��Ϣ��ѯ) 
 * @���ߣ� whj [���ţ�1004]
 * @ʱ�䣺 2013-8-14 ����2:55:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class ZxsglForm extends ActionForm {


	private static final long serialVersionUID = 1L;


	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	
	private ExportModel exportModel = new ExportModel();
	  
	private String zgh; // ��ţ�ְ���ţ�
	
	private String lxdh; //��ϵ�绰
	
	private String address; //�칫�绰
	
	private String zxslv; // ����
	
	private String zxszg; // �ʸ�

	private String kjdrs; // �ɽӴ�����/��

	private String status; //�ڸ�״̬(�ڸڡ����ڸ�)

	private String createsj; //����ʱ��

	private String createbh; //�����˱��

	private String createmc; //����������
	
	private String  zxsjj; //���
	
	private String  datazt; //����״̬0ʧЧ1����
	
	
	private String xm; // ����
	
	private String xb; // �Ա�
	
	private String age; // ����
	
	private String xq; // У��
	
	private String sclydm; // �ó�����
	

	/**
	 * @return the scly
	 */
	public String getSclydm() {
		return sclydm;
	}

	/**
	 * @param sclyҪ���õ� scly
	 */
	public void setSclydm(String sclydm) {
		this.sclydm = sclydm;
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
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}

	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}

	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}

	/**
	 * @param xbҪ���õ� xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param ageҪ���õ� age
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}

	/**
	 * @param exportModelҪ���õ� exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

}
