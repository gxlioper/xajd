/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-7-18 ����09:30:42 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.xyrssz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ѧԺ��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-7-18 ����09:30:42 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyrsszForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private String xmdm;// ��Ŀ����
	private String xn;// ѧ��
	private String xzdm;// ���ʴ���
	private String lxdm;// ���ʹ���
	private String xmmc;// ��Ŀ����
	private String xmje;// ��Ŀ���
	private String sqkg;// ���뿪��
	private String rskzjb;// �������Ƽ���
	private String jdkzjb;// ��ÿ��Ƽ���
	private String rsfpfs;// �������䷽ʽ
	private String rsfpz;// ����������������
	private String rsfpnj;// ���������꼶
	private String zcfpm;// �۲��������ʽ
	private String sfysz;
	private String xydm;
	private String status;// ��ѯ״̬��0|δ����,1|������
	private String zd1;//������
	private String zd2;//����ʱ��
	
	private String[] grid_key;// 
	private String[] guids;// guid����
	private String[] njs;// �꼶����
	private String[] xydms;//
	private String[] zydms;
	private String[] bjdms;
	private String[] cpzdms;
	private String[] zrss;
	private String[] zzmes;
	private String[] fpbls;
	private String[] jsrsHid;
	

	//============��ѯ����============
	private String zydm;
	private String bjdm;
	private String njq;
	private String cpzmc;//����������
	
	
	/**
	 * @return the grid_key
	 */
	public String[] getGrid_key() {
		return grid_key;
	}
	/**
	 * @param gridKeyҪ���õ� grid_key
	 */
	public void setGrid_key(String[] gridKey) {
		grid_key = gridKey;
	}
	/**
	 * @return the guids
	 */
	public String[] getGuids() {
		return guids;
	}
	/**
	 * @param guidsҪ���õ� guids
	 */
	public void setGuids(String[] guids) {
		this.guids = guids;
	}
	/**
	 * @return the njs
	 */
	public String[] getNjs() {
		return njs;
	}
	/**
	 * @param njsҪ���õ� njs
	 */
	public void setNjs(String[] njs) {
		this.njs = njs;
	}
	/**
	 * @return the xydms
	 */
	public String[] getXydms() {
		return xydms;
	}
	/**
	 * @param xydmsҪ���õ� xydms
	 */
	public void setXydms(String[] xydms) {
		this.xydms = xydms;
	}
	/**
	 * @return the zydms
	 */
	public String[] getZydms() {
		return zydms;
	}
	/**
	 * @param zydmsҪ���õ� zydms
	 */
	public void setZydms(String[] zydms) {
		this.zydms = zydms;
	}
	/**
	 * @return the bjdms
	 */
	public String[] getBjdms() {
		return bjdms;
	}
	/**
	 * @param bjdmsҪ���õ� bjdms
	 */
	public void setBjdms(String[] bjdms) {
		this.bjdms = bjdms;
	}
	/**
	 * @return the cpzdms
	 */
	public String[] getCpzdms() {
		return cpzdms;
	}
	/**
	 * @param cpzdmsҪ���õ� cpzdms
	 */
	public void setCpzdms(String[] cpzdms) {
		this.cpzdms = cpzdms;
	}
	/**
	 * @return the zrss
	 */
	public String[] getZrss() {
		return zrss;
	}
	/**
	 * @param zrssҪ���õ� zrss
	 */
	public void setZrss(String[] zrss) {
		this.zrss = zrss;
	}
	/**
	 * @return the zzmes
	 */
	public String[] getZzmes() {
		return zzmes;
	}
	/**
	 * @param zzmesҪ���õ� zzmes
	 */
	public void setZzmes(String[] zzmes) {
		this.zzmes = zzmes;
	}
	/**
	 * @return the fpbls
	 */
	public String[] getFpbls() {
		return fpbls;
	}
	/**
	 * @param fpblsҪ���õ� fpbls
	 */
	public void setFpbls(String[] fpbls) {
		this.fpbls = fpbls;
	}
	/**
	 * @return the jsrsHid
	 */
	public String[] getJsrsHid() {
		return jsrsHid;
	}
	/**
	 * @param jsrsHidҪ���õ� jsrsHid
	 */
	public void setJsrsHid(String[] jsrsHid) {
		this.jsrsHid = jsrsHid;
	}
	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * @param zydmҪ���õ� zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdmҪ���õ� bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the njq
	 */
	public String getNjq() {
		return njq;
	}
	/**
	 * @param njqҪ���õ� njq
	 */
	public void setNjq(String njq) {
		this.njq = njq;
	}
	/**
	 * @return the cpzmc
	 */
	public String getCpzmc() {
		return cpzmc;
	}
	/**
	 * @param cpzmcҪ���õ� cpzmc
	 */
	public void setCpzmc(String cpzmc) {
		this.cpzmc = cpzmc;
	}
	/**
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}
	/**
	 * @param zd1Ҫ���õ� zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	/**
	 * @return the zd2
	 */
	public String getZd2() {
		return zd2;
	}
	/**
	 * @param zd2Ҫ���õ� zd2
	 */
	public void setZd2(String zd2) {
		this.zd2 = zd2;
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
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @param xydmҪ���õ� xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	/**
	 * @return the sfysz
	 */
	public String getSfysz() {
		return sfysz;
	}
	/**
	 * @param sfyszҪ���õ� sfysz
	 */
	public void setSfysz(String sfysz) {
		this.sfysz = sfysz;
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
}
