/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-31 ����11:13:12 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.jg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-31 ����11:13:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcftzJgForm extends ActionForm{
	private String jgid;
	private String xmdm;
	private String xh;
	private String tzhjcf;
	private String jxdm;
	private String sfqq;
	private String lylcywid;
	private String sjly;
	private String type;
	private String xfrdjgzt;
	private String[] xhs;
	private String[] jxdms;
	private String[] sfqqs;
	private String[] tzhjcfs;
	private String[] jgids;
	private String csms;
	private String[] csmss;
	private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
	
    private String bz1;
    private String bz2;
    private String bz3;
    private String bz4;
    private String bz5;
    
    private String[] bz1s;
    private String[] bz2s;
    private String[] bz3s;
    private String[] bz4s;
    private String[] bz5s;
    
    
	/**
	 * @return the bz1s
	 */
	public String[] getBz1s() {
		return bz1s;
	}
	/**
	 * @param bz1sҪ���õ� bz1s
	 */
	public void setBz1s(String[] bz1s) {
		this.bz1s = bz1s;
	}
	/**
	 * @return the bz2s
	 */
	public String[] getBz2s() {
		return bz2s;
	}
	/**
	 * @param bz2sҪ���õ� bz2s
	 */
	public void setBz2s(String[] bz2s) {
		this.bz2s = bz2s;
	}
	/**
	 * @return the bz3s
	 */
	public String[] getBz3s() {
		return bz3s;
	}
	/**
	 * @param bz3sҪ���õ� bz3s
	 */
	public void setBz3s(String[] bz3s) {
		this.bz3s = bz3s;
	}
	/**
	 * @return the bz4s
	 */
	public String[] getBz4s() {
		return bz4s;
	}
	/**
	 * @param bz4sҪ���õ� bz4s
	 */
	public void setBz4s(String[] bz4s) {
		this.bz4s = bz4s;
	}
	/**
	 * @return the bz5s
	 */
	public String[] getBz5s() {
		return bz5s;
	}
	/**
	 * @param bz5sҪ���õ� bz5s
	 */
	public void setBz5s(String[] bz5s) {
		this.bz5s = bz5s;
	}
	/**
	 * @return the bz1
	 */
	public String getBz1() {
		return bz1;
	}
	/**
	 * @param bz1Ҫ���õ� bz1
	 */
	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}
	/**
	 * @return the bz2
	 */
	public String getBz2() {
		return bz2;
	}
	/**
	 * @param bz2Ҫ���õ� bz2
	 */
	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}
	/**
	 * @return the bz3
	 */
	public String getBz3() {
		return bz3;
	}
	/**
	 * @param bz3Ҫ���õ� bz3
	 */
	public void setBz3(String bz3) {
		this.bz3 = bz3;
	}
	/**
	 * @return the bz4
	 */
	public String getBz4() {
		return bz4;
	}
	/**
	 * @param bz4Ҫ���õ� bz4
	 */
	public void setBz4(String bz4) {
		this.bz4 = bz4;
	}
	/**
	 * @return the bz5
	 */
	public String getBz5() {
		return bz5;
	}
	/**
	 * @param bz5Ҫ���õ� bz5
	 */
	public void setBz5(String bz5) {
		this.bz5 = bz5;
	}
	/**
	 * @return the csms
	 */
	public String getCsms() {
		return csms;
	}
	/**
	 * @param csmsҪ���õ� csms
	 */
	public void setCsms(String csms) {
		this.csms = csms;
	}
	/**
	 * @return the csmss
	 */
	public String[] getCsmss() {
		return csmss;
	}
	/**
	 * @param csmssҪ���õ� csmss
	 */
	public void setCsmss(String[] csmss) {
		this.csmss = csmss;
	}

	/**
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @param jgidҪ���õ� jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
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
	 * @return the tzhjcf
	 */
	public String getTzhjcf() {
		return tzhjcf;
	}
	/**
	 * @param tzhjcfҪ���õ� tzhjcf
	 */
	public void setTzhjcf(String tzhjcf) {
		this.tzhjcf = tzhjcf;
	}
	/**
	 * @return the jxdm
	 */
	public String getJxdm() {
		return jxdm;
	}
	/**
	 * @param jxdmҪ���õ� jxdm
	 */
	public void setJxdm(String jxdm) {
		this.jxdm = jxdm;
	}
	/**
	 * @return the sfqq
	 */
	public String getSfqq() {
		return sfqq;
	}
	/**
	 * @param sfqqҪ���õ� sfqq
	 */
	public void setSfqq(String sfqq) {
		this.sfqq = sfqq;
	}
	/**
	 * @return the lylcywid
	 */
	public String getLylcywid() {
		return lylcywid;
	}
	/**
	 * @param lylcywidҪ���õ� lylcywid
	 */
	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
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
	 * @return the xfrdjgzt
	 */
	public String getXfrdjgzt() {
		return xfrdjgzt;
	}
	/**
	 * @param xfrdjgztҪ���õ� xfrdjgzt
	 */
	public void setXfrdjgzt(String xfrdjgzt) {
		this.xfrdjgzt = xfrdjgzt;
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
	/**
	 * @return the jxdms
	 */
	public String[] getJxdms() {
		return jxdms;
	}
	/**
	 * @param jxdmsҪ���õ� jxdms
	 */
	public void setJxdms(String[] jxdms) {
		this.jxdms = jxdms;
	}
	/**
	 * @return the sfqqs
	 */
	public String[] getSfqqs() {
		return sfqqs;
	}
	/**
	 * @param sfqqsҪ���õ� sfqqs
	 */
	public void setSfqqs(String[] sfqqs) {
		this.sfqqs = sfqqs;
	}
	/**
	 * @return the tzhjcfs
	 */
	public String[] getTzhjcfs() {
		return tzhjcfs;
	}
	/**
	 * @param tzhjcfsҪ���õ� tzhjcfs
	 */
	public void setTzhjcfs(String[] tzhjcfs) {
		this.tzhjcfs = tzhjcfs;
	}
	/**
	 * @return the jgids
	 */
	public String[] getJgids() {
		return jgids;
	}
	/**
	 * @param jgidsҪ���õ� jgids
	 */
	public void setJgids(String[] jgids) {
		this.jgids = jgids;
	}
	
	
    
	
}
