/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-17 ����02:06:36 
 */  
package com.zfsoft.xgxt.dagl.sxdaxxgl.daxxwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @���ߣ�caopei[����:1352]
 * @ʱ�䣺 2016-8-17 ����02:06:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SxDaxxglForm extends ActionForm{
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
	 private String bjid;
	 private String bjdm;
	private String daxxid;
	private String xh;
	private String kddh;
	private String yjdz;
	private String yjr;
	private String sj;
	private String bz;
	private String pj;
	
	 private String bjdms;
	 private String flag;
	 private String flag1;
	 private String xn;
	 private String xq;
	 
	 
	 private String bjrs;
	 private String whrs;
	 private String ywh;
	 
	/**
	 * @return the ywh
	 */
	public String getYwh() {
		return ywh;
	}
	/**
	 * @param ywhҪ���õ� ywh
	 */
	public void setYwh(String ywh) {
		this.ywh = ywh;
	}
	/**
	 * @return the bjid
	 */
	public String getBjid() {
		return bjid;
	}
	/**
	 * @param bjidҪ���õ� bjid
	 */
	public void setBjid(String bjid) {
		this.bjid = bjid;
	}
	/**
	 * @return the flag1
	 */
	public String getFlag1() {
		return flag1;
	}
	/**
	 * @param flag1Ҫ���õ� flag1
	 */
	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}
	/**
	 * @return the flag
	 */
	 
	public String getFlag() {
		return flag;
	}
	/**
	 * @param flagҪ���õ� flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * @return the bjdms
	 */
	public String getBjdms() {
		return bjdms;
	}
	/**
	 * @param bjdmsҪ���õ� bjdms
	 */
	public void setBjdms(String bjdms) {
		this.bjdms = bjdms;
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
	 * @return the daxxid
	 */
	public String getDaxxid() {
		return daxxid;
	}
	/**
	 * @param daxxidҪ���õ� daxxid
	 */
	public void setDaxxid(String daxxid) {
		this.daxxid = daxxid;
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
	 * @return the kddh
	 */
	public String getKddh() {
		return kddh;
	}
	/**
	 * @param kddhҪ���õ� kddh
	 */
	public void setKddh(String kddh) {
		this.kddh = kddh;
	}
	/**
	 * @return the yjdz
	 */
	public String getYjdz() {
		return yjdz;
	}
	/**
	 * @param yjdzҪ���õ� yjdz
	 */
	public void setYjdz(String yjdz) {
		this.yjdz = yjdz;
	}
	/**
	 * @return the yjr
	 */
	public String getYjr() {
		return yjr;
	}
	/**
	 * @param yjrҪ���õ� yjr
	 */
	public void setYjr(String yjr) {
		this.yjr = yjr;
	}
	/**
	 * @return the sj
	 */
	public String getSj() {
		return sj;
	}
	/**
	 * @param sjҪ���õ� sj
	 */
	public void setSj(String sj) {
		this.sj = sj;
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
	 * @return the pj
	 */
	public String getPj() {
		return pj;
	}
	/**
	 * @param pjҪ���õ� pj
	 */
	public void setPj(String pj) {
		this.pj = pj;
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
	 * @return the xymc
	 */
	
	public String getBjrs() {
		return bjrs;
	}
	/**
	 * @param bjrsҪ���õ� bjrs
	 */
	public void setBjrs(String bjrs) {
		this.bjrs = bjrs;
	}
	/**
	 * @return the whrs
	 */
	public String getWhrs() {
		return whrs;
	}
	/**
	 * @param whrsҪ���õ� whrs
	 */
	public void setWhrs(String whrs) {
		this.whrs = whrs;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
