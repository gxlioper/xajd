/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.rssz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ŀά��-��������
 * @�๦������:
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����06:33:00
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmwhRsszForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String guid;// GUID
	private String xn;// ѧ��
	private String xq;// ѧ��
	private String nj;// �꼶
	private String bmdm;// ���Ŵ���
	private String bl;// ����
	private String zzrs;// ��������
	private String xmdm;// ��Ŀ����
	private String pdxn;
	private String pdxq;
	private String rskzfw;// �������Ʒ�Χ
	private String[] grid_key;// 
	private String[] guids;// guid����
	private String[] njs;// �꼶����
	private String[] xydms;//
	private String[] zydms;
	private String[] bjdms;
	private String[] zrss;
	private String[] zzrss;
	private String[] bls;

	//��ѯ����
	private String xydm;
	private String zydm;
	private String bjdm;
	private String njq;
	private String sfysz;//�����Ƿ�������
	private String xslb;
	private String status;// ��ѯ״̬��0|δ����,1|������

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

	public XmwhRsszForm() {
		super();
	}

	public String[] getGrid_key() {
		return grid_key;
	}

	public void setGrid_key(String[] gridKey) {
		grid_key = gridKey;
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

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
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

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}

	public String getZzrs() {
		return zzrs;
	}

	public void setZzrs(String zzrs) {
		this.zzrs = zzrs;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String[] getGuids() {
		return guids;
	}

	public void setGuids(String[] guids) {
		this.guids = guids;
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

	public void setBjdms(String[] bjdms) {
		this.bjdms = bjdms;
	}

	public String[] getZrss() {
		return zrss;
	}

	public void setZrss(String[] zrss) {
		this.zrss = zrss;
	}

	public String[] getZzrss() {
		return zzrss;
	}

	public void setZzrss(String[] zzrss) {
		this.zzrss = zzrss;
	}

	public String[] getBls() {
		return bls;
	}

	public void setBls(String[] bls) {
		this.bls = bls;
	}

	public String getRskzfw() {
		return rskzfw;
	}

	public void setRskzfw(String rskzfw) {
		this.rskzfw = rskzfw;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	/**
	 * @return the xslb
	 */
	public String getXslb() {
		return xslb;
	}

	/**
	 * @param xslbҪ���õ� xslb
	 */
	public void setXslb(String xslb) {
		this.xslb = xslb;
	}

	/**
	 * @return the pdxn
	 */
	public String getPdxn() {
		return pdxn;
	}

	/**
	 * @param pdxnҪ���õ� pdxn
	 */
	public void setPdxn(String pdxn) {
		this.pdxn = pdxn;
	}

	/**
	 * @return the pdxq
	 */
	public String getPdxq() {
		return pdxq;
	}

	/**
	 * @param pdxqҪ���õ� pdxq
	 */
	public void setPdxq(String pdxq) {
		this.pdxq = pdxq;
	}

	
	
	
}
