/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-23 ����03:18:38 
 */  
package com.zfsoft.xgxt.rcsw.txhd.xmsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ����񡪡�ѧ�Ż
 * @�๦������: ��Ŀ����Form 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-6-23 ����03:18:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TxhdXmszForm extends ActionForm {

	
	private static final long serialVersionUID = -5407318657974976219L;
	
	
	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	
	private String xmdm ;	//��Ŀ����
	private String xmmc ;	//��Ŀ����
	private String hdkssj ;	//���ʼʱ��
	private String hdjssj ;	//�����ʱ��
	private String lbdm ;	//������
	private String sqrssx ;	//������������
	private String shrssx ;	//�����������
	private String shlc ;	//�������
	private String rskzjb ;	//�������Ƽ���
	private String hddd ;	//��ص�
	private String hdsm ;	//�˵��
	private String sqkg ;	//���뿪��
	private String sqkssj ;	//���뿪ʼʱ��
	private String sqjssj ;	//�������ʱ��
	private String shkg ;	//��˿���
	private String shkssj ;	//��˿�ʼʱ��
	private String shjssj ;	//��˽���ʱ��
	private String fbsj ;	//����ʱ��
	private String xn;		//ѧ��
	private String xq;		//ѧ��
	private String xqmc;
	private String kgbz;	//���ر�ע
	private String syxf;	//����ѧ��
	
	private String cbdw;//�а쵥λ
	private String fzrxm;//����������
	private String lxdh;//��ϵ�绰
	private String hdzt;//�����
	private String hdmdyy;//�Ŀ�ļ�����
	private String hdfa;//�����
	private String sjly;
	private String hdggdm;//�������
	
	
	/**
	 * @return the hdggdm
	 */
	public String getHdggdm() {
		return hdggdm;
	}
	/**
	 * @param hdggdmҪ���õ� hdggdm
	 */
	public void setHdggdm(String hdggdm) {
		this.hdggdm = hdggdm;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
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
	public String getHdkssj() {
		return hdkssj;
	}
	public void setHdkssj(String hdkssj) {
		this.hdkssj = hdkssj;
	}
	public String getHdjssj() {
		return hdjssj;
	}
	public void setHdjssj(String hdjssj) {
		this.hdjssj = hdjssj;
	}
	public String getLbdm() {
		return lbdm;
	}
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}
	public String getSqrssx() {
		return sqrssx;
	}
	public void setSqrssx(String sqrssx) {
		this.sqrssx = sqrssx;
	}
	public String getShrssx() {
		return shrssx;
	}
	public void setShrssx(String shrssx) {
		this.shrssx = shrssx;
	}
	public String getShlc() {
		return shlc;
	}
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	public String getRskzjb() {
		return rskzjb;
	}
	public void setRskzjb(String rskzjb) {
		this.rskzjb = rskzjb;
	}
	public String getHddd() {
		return hddd;
	}
	public void setHddd(String hddd) {
		this.hddd = hddd;
	}
	public String getHdsm() {
		return hdsm;
	}
	public void setHdsm(String hdsm) {
		this.hdsm = hdsm;
	}
	public String getSqkg() {
		return sqkg;
	}
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
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
	public String getShkg() {
		return shkg;
	}
	public void setShkg(String shkg) {
		this.shkg = shkg;
	}
	public String getShkssj() {
		return shkssj;
	}
	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}
	public String getShjssj() {
		return shjssj;
	}
	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}
	public String getFbsj() {
		return fbsj;
	}
	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getKgbz() {
		return kgbz;
	}
	public void setKgbz(String kgbz) {
		this.kgbz = kgbz;
	}
	public String getSyxf() {
		return syxf;
	}
	public void setSyxf(String syxf) {
		this.syxf = syxf;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmcҪ���õ� xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the cbdw
	 */
	public String getCbdw() {
		return cbdw;
	}
	/**
	 * @param cbdwҪ���õ� cbdw
	 */
	public void setCbdw(String cbdw) {
		this.cbdw = cbdw;
	}
	/**
	 * @return the fzrxm
	 */
	public String getFzrxm() {
		return fzrxm;
	}
	/**
	 * @param fzrxmҪ���õ� fzrxm
	 */
	public void setFzrxm(String fzrxm) {
		this.fzrxm = fzrxm;
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
	 * @return the hdzt
	 */
	public String getHdzt() {
		return hdzt;
	}
	/**
	 * @param hdztҪ���õ� hdzt
	 */
	public void setHdzt(String hdzt) {
		this.hdzt = hdzt;
	}
	/**
	 * @return the hdmdyy
	 */
	public String getHdmdyy() {
		return hdmdyy;
	}
	/**
	 * @param hdmdyyҪ���õ� hdmdyy
	 */
	public void setHdmdyy(String hdmdyy) {
		this.hdmdyy = hdmdyy;
	}
	/**
	 * @return the hdfa
	 */
	public String getHdfa() {
		return hdfa;
	}
	/**
	 * @param hdfaҪ���õ� hdfa
	 */
	public void setHdfa(String hdfa) {
		this.hdfa = hdfa;
	}
	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjlyҪ���õ� sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	
	

}
