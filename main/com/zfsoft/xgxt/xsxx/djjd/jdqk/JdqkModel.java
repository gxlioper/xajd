/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����11:26:25 
 */  
package com.zfsoft.xgxt.xsxx.djjd.jdqk;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����11:26:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JdqkModel extends ActionForm {

	
	private static final long serialVersionUID = 1966791940187986544L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String id;
	private String xh;
	private String xn;
	private String xq;
	private String xmdm;
	private String jbdm;
	private String zsbh;
	private String sjly;
	private String xqmc;
	private String xmmc;
	private String jbmc;
	
	
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
	 * @return the jbmc
	 */
	public String getJbmc() {
		return jbmc;
	}
	/**
	 * @param jbmcҪ���õ� jbmc
	 */
	public void setJbmc(String jbmc) {
		this.jbmc = jbmc;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmcҪ���õ� xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
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
	 * @return the jbdm
	 */
	public String getJbdm() {
		return jbdm;
	}
	/**
	 * @param jbdmҪ���õ� jbdm
	 */
	public void setJbdm(String jbdm) {
		this.jbdm = jbdm;
	}
	/**
	 * @return the zsbh
	 */
	public String getZsbh() {
		return zsbh;
	}
	/**
	 * @param zsbhҪ���õ� zsbh
	 */
	public void setZsbh(String zsbh) {
		this.zsbh = zsbh;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
}
