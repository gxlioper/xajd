/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-22 ����10:40:54 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.sq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-7-22 ����10:40:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TttzxmForm extends ActionForm {
	  private String  ttsqid;
	  private String  xmdm;                    
	  private String  sqr;
	  private String  sqsj;
	  private String  sqly;
	  private String  splc;
	  private String  shzt;                    
	  private String  tdmc;
	  private String  dzxh;
	  private String  xhs;
	  private String  ttcyid;
	  private String[] xhArr;
	  private SearchModel searchModel = new SearchModel();
		 // private static final long serialVersionUID = 1L;
	  private String type;
	  private ExportModel exportModel = new ExportModel();
	  private Pages pages = new Pages();
	  /**
	 * @return the xhArr
	 */
	public String[] getXhArr() {
		return xhArr;
	}
	/**
	 * @param xhArrҪ���õ� xhArr
	 */
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}
	
	  /**
	 * @return the ttcyid
	 */
	public String getTtcyid() {
		return ttcyid;
	}
	/**
	 * @param ttcyidҪ���õ� ttcyid
	 */
	public void setTtcyid(String ttcyid) {
		this.ttcyid = ttcyid;
	}
	
    /**
	 * @return the xhs
	 */
	public String getXhs() {
		return xhs;
	}
	/**
	 * @param xhsҪ���õ� xhs
	 */
	public void setXhs(String xhs) {
		this.xhs = xhs;
	}
	
	/**
	 * @return the ttsqid
	 */
	public String getTtsqid() {
		return ttsqid;
	}
	/**
	 * @param ttsqidҪ���õ� ttsqid
	 */
	public void setTtsqid(String ttsqid) {
		this.ttsqid = ttsqid;
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
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}
	/**
	 * @param sqrҪ���õ� sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
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
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqlyҪ���õ� sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
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
	 * @return the tdmc
	 */
	public String getTdmc() {
		return tdmc;
	}
	/**
	 * @param tdmcҪ���õ� tdmc
	 */
	public void setTdmc(String tdmc) {
		this.tdmc = tdmc;
	}
	/**
	 * @return the dzxh
	 */
	public String getDzxh() {
		return dzxh;
	}
	/**
	 * @param dzxhҪ���õ� dzxh
	 */
	public void setDzxh(String dzxh) {
		this.dzxh = dzxh;
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
	  
}
