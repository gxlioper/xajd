package com.zfsoft.xgxt.xszz.knsjcsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class JcszForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String sqkg ;//���뿪��
	private String sqkssj ;//���뿪ʼʱ��
	private String sqjssj ;//�������ʱ��
	private String splc ;//��������
	private String sfwcjtdc ;//�Ƿ���ɼ�ͥ����
	private String isopen ;//��ǰʱ���Ƿ���
	private String sqsftxdc;//����ʱ�Ƿ���д���ѵ���
	private String kxzrdzb;// ��ѡ���϶�ָ�꣨0�� �����ƣ�1: 1����
	private String rdxn;//�������϶�ѧ��
	private String rdxq;//�������϶�ѧ��
	
	//========================�������ò���start===============//
	private String[] guids;// guid����
	private String[] njs;// �꼶����
	private String[] xydms;//
	private String[] zydms;
	private String[] bjdms;
	private String[] zrss;
	private String[] zzrss;
	private String[] bls;
	
	private String rskznj;//���������꼶
	private String rssfkz;//�����Ƿ����
	private String rskzjb;//�������Ƽ���
	private String rskzfw;//�������Ʒ�Χ
	private String xn;//��ǰѧ��
	private String xq;//��ǰѧ��
	private String bl;//����
	private String guid;
	private String bmdm;
	private String zzrs;
	private String nj;
	private String zme;//������
	//��ѯ����
	private String xydm;
	private String zydm;
	private String bjdm;
	private String njq;
	private String sfysz;//�����Ƿ�������
	//========================�������ò���end=================//
	
	//������Ͽҽ��ר���Ի���˿��أ���˿�ʼʱ�䣬��˽���ʱ��
	private String shkg;
	private String shkssj;
	private String shjssj;
	private String isopensh;
	
	private String rddc;//�϶�����
	
	/**
	 * @return the rddc
	 */
	public String getRddc() {
		return rddc;
	}
	/**
	 * @param rddcҪ���õ� rddc
	 */
	public void setRddc(String rddc) {
		this.rddc = rddc;
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
	public String getIsopensh() {
		return isopensh;
	}
	public void setIsopensh(String isopensh) {
		this.isopensh = isopensh;
	}
	public String getSqkg() {
		return sqkg;
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
	 * @return the zzrss
	 */
	public String[] getZzrss() {
		return zzrss;
	}
	/**
	 * @param zzrssҪ���õ� zzrss
	 */
	public void setZzrss(String[] zzrss) {
		this.zzrss = zzrss;
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
	 * @return the zzrs
	 */
	public String getZzrs() {
		return zzrs;
	}
	/**
	 * @param zzrsҪ���õ� zzrs
	 */
	public void setZzrs(String zzrs) {
		this.zzrs = zzrs;
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
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getSfwcjtdc() {
		return sfwcjtdc;
	}
	public void setSfwcjtdc(String sfwcjtdc) {
		this.sfwcjtdc = sfwcjtdc;
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
	public String getIsopen() {
		return isopen;
	}
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
	public void setSqsftxdc(String sqsftxdc) {
		this.sqsftxdc = sqsftxdc;
	}
	public String getSqsftxdc() {
		return sqsftxdc;
	}
	public String getKxzrdzb() {
		return kxzrdzb;
	}
	public void setKxzrdzb(String kxzrdzb) {
		this.kxzrdzb = kxzrdzb;
	}
	public void setRssfkz(String rssfkz) {
		this.rssfkz = rssfkz;
	}
	public String getRssfkz() {
		return rssfkz;
	}
	public void setRskzjb(String rskzjb) {
		this.rskzjb = rskzjb;
	}
	public String getRskzjb() {
		return rskzjb;
	}
	public void setRskzfw(String rskzfw) {
		this.rskzfw = rskzfw;
	}
	public String getRskzfw() {
		return rskzfw;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXn() {
		return xn;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXq() {
		return xq;
	}
	public void setBl(String bl) {
		this.bl = bl;
	}
	public String getBl() {
		return bl;
	}
	public void setRskznj(String rskznj) {
		this.rskznj = rskznj;
	}
	public String getRskznj() {
		return rskznj;
	}
	public void setZme(String zme) {
		this.zme = zme;
	}
	public String getZme() {
		return zme;
	}
	public void setBls(String[] bls) {
		this.bls = bls;
	}
	public String[] getBls() {
		return bls;
	}
	public void setNjq(String njq) {
		this.njq = njq;
	}
	public String getNjq() {
		return njq;
	}
	/**
	 * @return the rdxn
	 */
	public String getRdxn() {
		return rdxn;
	}
	/**
	 * @param rdxnҪ���õ� rdxn
	 */
	public void setRdxn(String rdxn) {
		this.rdxn = rdxn;
	}
	/**
	 * @return the rdxq
	 */
	public String getRdxq() {
		return rdxq;
	}
	/**
	 * @param rdxqҪ���õ� rdxq
	 */
	public void setRdxq(String rdxq) {
		this.rdxq = rdxq;
	}
	
	


}
