/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-11 ����09:03:18 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-11 ����09:03:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxshForm extends ActionForm {
	private String sqid;
	private String xh;
	private String xn;
	private String xq;
	private String jhrxm;
	private String jhrlxfs;
	private String sflx;
	private String lxsj;
	private String lxjtgjdm;
	private String lxcchb;
	private String mddssx;
	private String fxsj;
	private String fxjtgjdm;
	private String fxcchb;
	private String bz;
	private String splcid;
	private String shzt;
	private String sqsj;
	private String type;
	private ExportModel exportModel = new ExportModel();
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	
	//����ֶ�
	private String shid;
	private String shjg;
	private String shyj;
	private String gwid;
	private String thgw;
	private String[] xns;
	private String[] xqs;
	/**
	 * @return the xns
	 */
	public String[] getXns() {
		return xns;
	}
	/**
	 * @param xnsҪ���õ� xns
	 */
	public void setXns(String[] xns) {
		this.xns = xns;
	}
	/**
	 * @return the xqs
	 */
	public String[] getXqs() {
		return xqs;
	}
	/**
	 * @param xqsҪ���õ� xqs
	 */
	public void setXqs(String[] xqs) {
		this.xqs = xqs;
	}
	private String shlc;
	//���������
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private static final long serialVersionUID = 1L;
	
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
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqidҪ���õ� sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xhҪ���õ� xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xqҪ���õ� xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the jhrxm
	 */
	public String getJhrxm() {
		return jhrxm;
	}
	/**
	 * @param jhrxmҪ���õ� jhrxm
	 */
	public void setJhrxm(String jhrxm) {
		this.jhrxm = jhrxm;
	}
	/**
	 * @return the jhrlxfs
	 */
	public String getJhrlxfs() {
		return jhrlxfs;
	}
	/**
	 * @param jhrlxfsҪ���õ� jhrlxfs
	 */
	public void setJhrlxfs(String jhrlxfs) {
		this.jhrlxfs = jhrlxfs;
	}
	/**
	 * @return the sflx
	 */
	public String getSflx() {
		return sflx;
	}
	/**
	 * @param sflxҪ���õ� sflx
	 */
	public void setSflx(String sflx) {
		this.sflx = sflx;
	}
	/**
	 * @return the lxsj
	 */
	public String getLxsj() {
		return lxsj;
	}
	/**
	 * @param lxsjҪ���õ� lxsj
	 */
	public void setLxsj(String lxsj) {
		this.lxsj = lxsj;
	}
	/**
	 * @return the lxjtgjdm
	 */
	public String getLxjtgjdm() {
		return lxjtgjdm;
	}
	/**
	 * @param lxjtgjdmҪ���õ� lxjtgjdm
	 */
	public void setLxjtgjdm(String lxjtgjdm) {
		this.lxjtgjdm = lxjtgjdm;
	}
	/**
	 * @return the lxcchb
	 */
	public String getLxcchb() {
		return lxcchb;
	}
	/**
	 * @param lxcchbҪ���õ� lxcchb
	 */
	public void setLxcchb(String lxcchb) {
		this.lxcchb = lxcchb;
	}
	/**
	 * @return the fxsj
	 */
	public String getFxsj() {
		return fxsj;
	}
	/**
	 * @return the mddssx
	 */
	public String getMddssx() {
		return mddssx;
	}
	/**
	 * @param mddssxҪ���õ� mddssx
	 */
	public void setMddssx(String mddssx) {
		this.mddssx = mddssx;
	}
	/**
	 * @param fxsjҪ���õ� fxsj
	 */
	public void setFxsj(String fxsj) {
		this.fxsj = fxsj;
	}
	/**
	 * @return the fxjtgjdm
	 */
	public String getFxjtgjdm() {
		return fxjtgjdm;
	}
	/**
	 * @param fxjtgjdmҪ���õ� fxjtgjdm
	 */
	public void setFxjtgjdm(String fxjtgjdm) {
		this.fxjtgjdm = fxjtgjdm;
	}
	/**
	 * @return the fxcchb
	 */
	public String getFxcchb() {
		return fxcchb;
	}
	/**
	 * @param fxcchbҪ���õ� fxcchb
	 */
	public void setFxcchb(String fxcchb) {
		this.fxcchb = fxcchb;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcidҪ���õ� splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shztҪ���õ� shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsjҪ���õ� sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
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
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shidҪ���õ� shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjgҪ���õ� shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyjҪ���õ� shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwidҪ���õ� gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgwҪ���õ� thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String[] id) {
		this.id = id;
	}
	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @param gwidsҪ���õ� gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @param xhsҪ���õ� xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	
}
