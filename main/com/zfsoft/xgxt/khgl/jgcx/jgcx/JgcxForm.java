/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-18 ����03:10:43 
 */  
package com.zfsoft.xgxt.khgl.jgcx.jgcx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���˹���
 * @�๦������: ���˽��
 * @���ߣ�cq [����:785]
 * @ʱ�䣺 2015-8-18 ����03:10:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JgcxForm extends ActionForm {
	
	private static final long serialVersionUID = 1584857913791763482L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	
	private String xmid;
	private String khlx;
	private String xmmc;
	private String khdxid;
	private String kssj;
	private String jssj;
	private String xmms;
	private String khdxr;
	private String bmdm;
	private String pflx;
	private String pfzt;
	private String xmszid;

	
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
	 * @return the xmid
	 */
	public String getXmid() {
		return xmid;
	}
	/**
	 * @param xmidҪ���õ� xmid
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	/**
	 * @return the khlx
	 */
	public String getKhlx() {
		return khlx;
	}
	/**
	 * @param khlxҪ���õ� khlx
	 */
	public void setKhlx(String khlx) {
		this.khlx = khlx;
	}
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
	 * @return the khdxid
	 */
	public String getKhdxid() {
		return khdxid;
	}
	/**
	 * @param khdxidҪ���õ� khdxid
	 */
	public void setKhdxid(String khdxid) {
		this.khdxid = khdxid;
	}
	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}
	/**
	 * @param kssjҪ���õ� kssj
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}
	/**
	 * @param jssjҪ���õ� jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	/**
	 * @return the xmms
	 */
	public String getXmms() {
		return xmms;
	}
	/**
	 * @param xmmsҪ���õ� xmms
	 */
	public void setXmms(String xmms) {
		this.xmms = xmms;
	}
	/**
	 * @return the khdxr
	 */
	public String getKhdxr() {
		return khdxr;
	}
	/**
	 * @param khdxrҪ���õ� khdxr
	 */
	public void setKhdxr(String khdxr) {
		this.khdxr = khdxr;
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
	 * @return the pflx
	 */
	public String getPflx() {
		return pflx;
	}
	/**
	 * @param pflxҪ���õ� pflx
	 */
	public void setPflx(String pflx) {
		this.pflx = pflx;
	}
	/**
	 * @return the pfzt
	 */
	public String getPfzt() {
		return pfzt;
	}
	/**
	 * @param pfztҪ���õ� pfzt
	 */
	public void setPfzt(String pfzt) {
		this.pfzt = pfzt;
	}
	/**
	 * @return the xmszid
	 */
	public String getXmszid() {
		return xmszid;
	}
	/**
	 * @param xmszidҪ���õ� xmszid
	 */
	public void setXmszid(String xmszid) {
		this.xmszid = xmszid;
	}

}
