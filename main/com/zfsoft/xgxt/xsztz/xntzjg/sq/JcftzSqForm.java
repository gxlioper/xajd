/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-28 ����05:03:41 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.sq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-28 ����05:03:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcftzSqForm extends ActionForm{
	private String way;
	private String cxmc;
	private String[] ids;
	private String[] xmdms;
    private String[] csms;
    private String sqid;
	private String jgid;
	private String xmdm;
	private String xh;
	private String xm;
	private String tzhjcf;
	private String jxdm;
	private String sfqq;
	private String type;
	private String rdsplc;
	private String xfrdsqzt;
	private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private String bz1;
    private String bz2;
    private String bz3;
    private String bz4;
    private String bz5;
    
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
	public String[] getCsms() {
		return csms;
	}
	/**
	 * @param csmsҪ���õ� csms
	 */
	public void setCsms(String[] csms) {
		this.csms = csms;
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
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}
	/**
	 * @param idsҪ���õ� ids
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	/**
	 * @return the rdsplc
	 */
	public String getRdsplc() {
		return rdsplc;
	}
	/**
	 * @param rdsplcҪ���õ� rdsplc
	 */
	public void setRdsplc(String rdsplc) {
		this.rdsplc = rdsplc;
	}
	/**
	 * @return the xfrdsqzt
	 */
	public String getXfrdsqzt() {
		return xfrdsqzt;
	}
	/**
	 * @param xfrdsqztҪ���õ� xfrdsqzt
	 */
	public void setXfrdsqzt(String xfrdsqzt) {
		this.xfrdsqzt = xfrdsqzt;
	}
	/**
	 * @return the cxmc
	 */
	public String getCxmc() {
		return cxmc;
	}
	/**
	 * @param cxmcҪ���õ� cxmc
	 */
	public void setCxmc(String cxmc) {
		this.cxmc = cxmc;
	}
	/**
	 * @return the xmdms
	 */
	public String[] getXmdms() {
		return xmdms;
	}
	/**
	 * @param xmdmsҪ���õ� xmdms
	 */
	public void setXmdms(String[] xmdms) {
		this.xmdms = xmdms;
	}
	/**
	 * @return the way
	 */
	public String getWay() {
		return way;
	}
	/**
	 * @param wayҪ���õ� way
	 */
	public void setWay(String way) {
		this.way = way;
	}
	
	
	
	
	
	
	
	
    
    
}
