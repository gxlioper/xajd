/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-23 ����08:39:17 
 */  
package com.zfsoft.xgxt.xszz.zzdy.zzbtff;

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

public class ZzdyBtffForm extends ActionForm{
	
	private String id ; 
	private String tbyf ;
	private String xmdm ;
	private String zzzje ;
	private String xn ;
	private String xq ;
	private String ffyf ;
	private String xh ;
	private String yffje ;
	private String ffzt ;
	private String tbsj ;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
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
	/**
	 * @return the tbyf
	 */
	public String getTbyf() {
		return tbyf;
	}
	/**
	 * @param tbyfҪ���õ� tbyf
	 */
	public void setTbyf(String tbyf) {
		this.tbyf = tbyf;
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
	 * @return the zzzje
	 */
	public String getZzzje() {
		return zzzje;
	}
	/**
	 * @param zzzjeҪ���õ� zzzje
	 */
	public void setZzzje(String zzzje) {
		this.zzzje = zzzje;
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
	 * @return the ffyf
	 */
	public String getFfyf() {
		return ffyf;
	}
	/**
	 * @param ffyfҪ���õ� ffyf
	 */
	public void setFfyf(String ffyf) {
		this.ffyf = ffyf;
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
	 * @return the yffje
	 */
	public String getYffje() {
		return yffje;
	}
	/**
	 * @param yffjeҪ���õ� yffje
	 */
	public void setYffje(String yffje) {
		this.yffje = yffje;
	}
	/**
	 * @return the ffzt
	 */
	public String getFfzt() {
		return ffzt;
	}
	/**
	 * @param ffztҪ���õ� ffzt
	 */
	public void setFfzt(String ffzt) {
		this.ffzt = ffzt;
	}
	/**
	 * @return the tbsj
	 */
	public String getTbsj() {
		return tbsj;
	}
	/**
	 * @param tbsjҪ���õ� tbsj
	 */
	public void setTbsj(String tbsj) {
		this.tbsj = tbsj;
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
