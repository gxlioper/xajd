/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-6-1 ����09:26:42 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.rssz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������
 * @�๦������: ��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-6-1 ����09:26:42 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RsszForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String guid;// 
	private String xmdm;// ��Ŀ����
	private String bmdm;// ���Ŵ���
	private String nj;// �꼶
	private String fpbl;// ����
	private String zzme;// ��������
	private String czfs;//������ʽ
	private String zd1;
	private String zd2;

	private String rsfpfs;// �������Ʒ�Χ
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
	private String xydm;
	private String zydm;
	private String bjdm;
	private String njq;
	private String sfysz;//�����Ƿ�������
	private String cpzmc;//����������
	//============��ѯ����============
	private String status;// ��ѯ״̬��0|δ����,1|������
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
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guidҪ���õ� guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
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
	 * @return the bmdm
	 */
	public String getBmdm() {
		return bmdm;
	}
	/**
	 * @param bmdmҪ���õ� bmdm
	 */
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param njҪ���õ� nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @return the fpbl
	 */
	public String getFpbl() {
		return fpbl;
	}
	/**
	 * @param fpblҪ���õ� fpbl
	 */
	public void setFpbl(String fpbl) {
		this.fpbl = fpbl;
	}
	/**
	 * @return the zzme
	 */
	public String getZzme() {
		return zzme;
	}
	/**
	 * @param zzmeҪ���õ� zzme
	 */
	public void setZzme(String zzme) {
		this.zzme = zzme;
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
}
