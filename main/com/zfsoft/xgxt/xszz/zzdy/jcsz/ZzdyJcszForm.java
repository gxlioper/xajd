/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-23 ����08:39:17 
 */  
package com.zfsoft.xgxt.xszz.zzdy.jcsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-11-23 ����08:39:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZzdyJcszForm extends ActionForm{
	
	private String sqkg ;//���뿪�� 
	private String kssj ;//��ʼʱ�� 
	private String jssj ;//����ʱ�� 
	private String szid ;
	private String xmdm ;
	private String xmmc ;
	private String xn ;
	private String xq ;
	private String ffys ;
	private String ffksyf ;
	private String ffjsyf ;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkgҪ���õ� sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
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
	 * @return the szid
	 */
	public String getSzid() {
		return szid;
	}
	/**
	 * @param szidҪ���õ� szid
	 */
	public void setSzid(String szid) {
		this.szid = szid;
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
	 * @return the ffys
	 */
	public String getFfys() {
		return ffys;
	}
	/**
	 * @param ffysҪ���õ� ffys
	 */
	public void setFfys(String ffys) {
		this.ffys = ffys;
	}
	/**
	 * @return the ffksyf
	 */
	public String getFfksyf() {
		return ffksyf;
	}
	/**
	 * @param ffksyfҪ���õ� ffksyf
	 */
	public void setFfksyf(String ffksyf) {
		this.ffksyf = ffksyf;
	}
	/**
	 * @return the ffjsyf
	 */
	public String getFfjsyf() {
		return ffjsyf;
	}
	/**
	 * @param ffjsyfҪ���õ� ffjsyf
	 */
	public void setFfjsyf(String ffjsyf) {
		this.ffjsyf = ffjsyf;
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
	
	

}
