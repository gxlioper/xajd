/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xpjpy.xmsz.rssz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������:��Ŀά��-��������
 * @���ߣ� ligl
 * @���ڣ�2013-8-5 ����11:11:11
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class RsszModel extends ActionForm {

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
	private String sfyxgb;//�Ƿ�����ѧ���ɲ�

	//============��ѯ����============
	private String xydm;
	private String zydm;
	private String bjdm;
	private String njq;
	private String sfysz;//�����Ƿ�������
	private String cpzmc;//����������
	//============��ѯ����============
	private String status;// ��ѯ״̬��0|δ����,1|������
	private String pycc;//�������

	
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

	public RsszModel() {
		super();
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

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String[] getCpzdms() {
		return cpzdms;
	}

	public void setCpzdms(String[] cpzdms) {
		this.cpzdms = cpzdms;
	}

	public String getFpbl() {
		return fpbl;
	}

	public void setFpbl(String fpbl) {
		this.fpbl = fpbl;
	}

	public String getZzme() {
		return zzme;
	}

	public void setZzme(String zzme) {
		this.zzme = zzme;
	}

	public String getRsfpfs() {
		return rsfpfs;
	}

	public void setRsfpfs(String rsfpfs) {
		this.rsfpfs = rsfpfs;
	}

	public String[] getGrid_key() {
		return grid_key;
	}

	public void setGrid_key(String[] gridKey) {
		grid_key = gridKey;
	}

	public String[] getNjs() {
		return njs;
	}

	public void setNjs(String[] njs) {
		this.njs = njs;
	}

	public String[] getXydms() {
		return xydms;
	}

	public void setXydms(String[] xydms) {
		this.xydms = xydms;
	}

	public String[] getZydms() {
		return zydms;
	}

	public void setZydms(String[] zydms) {
		this.zydms = zydms;
	}

	public String[] getBjdms() {
		return bjdms;
	}

	public String[] getGuids() {
		return guids;
	}

	public void setGuids(String[] guids) {
		this.guids = guids;
	}

	public void setBjdms(String[] bjdms) {
		this.bjdms = bjdms;
	}

	public String[] getZrss() {
		return zrss;
	}

	public void setZrss(String[] zrss) {
		this.zrss = zrss;
	}

	public String[] getZzmes() {
		return zzmes;
	}

	public void setZzmes(String[] zzmes) {
		this.zzmes = zzmes;
	}

	public String[] getFpbls() {
		return fpbls;
	}

	public void setFpbls(String[] fpbls) {
		this.fpbls = fpbls;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public void setSfysz(String sfysz) {
		this.sfysz = sfysz;
	}

	public String getSfysz() {
		return sfysz;
	}

	public void setNjq(String njq) {
		this.njq = njq;
	}

	public String getNjq() {
		return njq;
	}

	public String getCpzmc() {
		return cpzmc;
	}

	public void setCpzmc(String cpzmc) {
		this.cpzmc = cpzmc;
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

	public String[] getJsrsHid() {
		return jsrsHid;
	}

	public void setJsrsHid(String[] jsrsHid) {
		this.jsrsHid = jsrsHid;
	}
	
	public String getSfyxgb() {
		return sfyxgb;
	}

	public void setSfyxgb(String sfyxgb) {
		this.sfyxgb = sfyxgb;
	}

	/**
	 * @return the pycc
	 */
	public String getPycc() {
		return pycc;
	}

	/**
	 * @param pyccҪ���õ� pycc
	 */
	public void setPycc(String pycc) {
		this.pycc = pycc;
	}
	
}
