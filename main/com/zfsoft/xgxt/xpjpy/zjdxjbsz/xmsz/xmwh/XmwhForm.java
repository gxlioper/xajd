/**
 * @����:ѧ����Ʒ1��
 * @���ڣ�2017-4-7 ����10:59:56 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������_��Ŀ����_��Ŀά��
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-4-7 ����10:57:56 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmwhForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String xmdm;// ��Ŀ����
	private String xn;// ѧ��
	private String xzdm;// ���ʴ���
	private String lxdm;// ���ʹ���
	private String xmmc;// ��Ŀ����
	private String ywmc;// Ӣ������
	private String xmje;// ��Ŀ���
	private String xssx;// ��ʾ˳��
	private String shlc;// �������
	private String sqkg;// ���뿪��
	private String sqkssj;// ���뿪ʼʱ��
	private String sqjssj;// �������ʱ��
	private String shkg;// ��˿���
	private String shkssj;// ��˿�ʼʱ��
	private String shjssj;// ��˽���ʱ��
	private String kgbz;// ���ر�ע
	private String djb;// �ǼǱ�
	private String sbb;// �ϱ���
	private String xmsm;// ��Ŀ˵��
	private String czfs; //������ʽ
	private String rskzjb;// �������Ƽ���
	private String jdkzjb;// ��ÿ��Ƽ���
	private String rsfpfs;// �������䷽ʽ
	private String rsfpz;// ����������������
	private String rsfpnj;// ���������꼶
	private String zcfpm;// �۲��������ʽ
	private String xmdyb;// ��������һ����Ŀʱ������һ���ձ��������ʱ����ѧ�����ش�ӡ����Ŀ��ӡ��
	private String sfrssz;//����������Ŀ�Ƿ�Ҫ��������������Ϊ�е���Ŀ�ǲ���Ҫ�������õġ��Ƿ��������ã�0������Ҫ���á�1����Ҫ���á�
	
	
	
	/**
	 * @return the jdkzjb
	 */
	public String getJdkzjb() {
		return jdkzjb;
	}
	/**
	 * @param jdkzjbҪ���õ� jdkzjb
	 */
	public void setJdkzjb(String jdkzjb) {
		this.jdkzjb = jdkzjb;
	}
	/**
	 * @return the rsfpfs
	 */
	public String getRsfpfs() {
		return rsfpfs;
	}
	/**
	 * @param rsfpfsҪ���õ� rsfpfs
	 */
	public void setRsfpfs(String rsfpfs) {
		this.rsfpfs = rsfpfs;
	}
	/**
	 * @return the rsfpz
	 */
	public String getRsfpz() {
		return rsfpz;
	}
	/**
	 * @param rsfpzҪ���õ� rsfpz
	 */
	public void setRsfpz(String rsfpz) {
		this.rsfpz = rsfpz;
	}
	/**
	 * @return the rsfpnj
	 */
	public String getRsfpnj() {
		return rsfpnj;
	}
	/**
	 * @param rsfpnjҪ���õ� rsfpnj
	 */
	public void setRsfpnj(String rsfpnj) {
		this.rsfpnj = rsfpnj;
	}
	/**
	 * @return the zcfpm
	 */
	public String getZcfpm() {
		return zcfpm;
	}
	/**
	 * @param zcfpmҪ���õ� zcfpm
	 */
	public void setZcfpm(String zcfpm) {
		this.zcfpm = zcfpm;
	}
	/**
	 * @return the czfs
	 */
	public String getCzfs() {
		return czfs;
	}
	/**
	 * @param czfsҪ���õ� czfs
	 */
	public void setCzfs(String czfs) {
		this.czfs = czfs;
	}
	/**
	 * @return the rskzjb
	 */
	public String getRskzjb() {
		return rskzjb;
	}
	/**
	 * @param rskzjbҪ���õ� rskzjb
	 */
	public void setRskzjb(String rskzjb) {
		this.rskzjb = rskzjb;
	}
	/**
	 * @return the xmsm
	 */
	public String getXmsm() {
		return xmsm;
	}
	/**
	 * @param xmsmҪ���õ� xmsm
	 */
	public void setXmsm(String xmsm) {
		this.xmsm = xmsm;
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
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xnҪ���õ� xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
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
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}
	/**
	 * @param lxdmҪ���õ� lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmcҪ���õ� xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the ywmc
	 */
	public String getYwmc() {
		return ywmc;
	}
	/**
	 * @param ywmcҪ���õ� ywmc
	 */
	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}
	/**
	 * @return the xmje
	 */
	public String getXmje() {
		return xmje;
	}
	/**
	 * @param xmjeҪ���õ� xmje
	 */
	public void setXmje(String xmje) {
		this.xmje = xmje;
	}
	/**
	 * @return the xssx
	 */
	public String getXssx() {
		return xssx;
	}
	/**
	 * @param xssxҪ���õ� xssx
	 */
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	/**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlcҪ���õ� shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkgҪ���õ� sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssjҪ���õ� sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssjҪ���õ� sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	/**
	 * @return the shkg
	 */
	public String getShkg() {
		return shkg;
	}
	/**
	 * @param shkgҪ���õ� shkg
	 */
	public void setShkg(String shkg) {
		this.shkg = shkg;
	}
	/**
	 * @return the shkssj
	 */
	public String getShkssj() {
		return shkssj;
	}
	/**
	 * @param shkssjҪ���õ� shkssj
	 */
	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}
	/**
	 * @return the shjssj
	 */
	public String getShjssj() {
		return shjssj;
	}
	/**
	 * @param shjssjҪ���õ� shjssj
	 */
	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}
	/**
	 * @return the kgbz
	 */
	public String getKgbz() {
		return kgbz;
	}
	/**
	 * @param kgbzҪ���õ� kgbz
	 */
	public void setKgbz(String kgbz) {
		this.kgbz = kgbz;
	}
	/**
	 * @return the djb
	 */
	public String getDjb() {
		return djb;
	}
	/**
	 * @param djbҪ���õ� djb
	 */
	public void setDjb(String djb) {
		this.djb = djb;
	}
	/**
	 * @return the sbb
	 */
	public String getSbb() {
		return sbb;
	}
	/**
	 * @param sbbҪ���õ� sbb
	 */
	public void setSbb(String sbb) {
		this.sbb = sbb;
	}
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
	/**
	 * @return the xmdyb
	 */
	public String getXmdyb() {
		return xmdyb;
	}
	/**
	 * @param xmdybҪ���õ� xmdyb
	 */
	public void setXmdyb(String xmdyb) {
		this.xmdyb = xmdyb;
	}
	/**
	 * @return the sfrssz
	 */
	public String getSfrssz() {
		return sfrssz;
	}
	/**
	 * @param sfrsszҪ���õ� sfrssz
	 */
	public void setSfrssz(String sfrssz) {
		this.sfrssz = sfrssz;
	}
}
