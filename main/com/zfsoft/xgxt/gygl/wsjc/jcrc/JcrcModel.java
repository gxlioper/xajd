/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-1 ����09:01:43 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcrc;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @�๦������: ����ճ�
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-1 ����09:01:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcrcModel extends ActionForm {

	
	private static final long serialVersionUID = 1430900341644686761L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String id;
	private String xn;
	private String xq;
	private String rcmc;
	private String kssj;
	private String jssj;
	private String fslx;
	private String bz;
	private String tjzt;
	
	private String[] xmdm;
	
	
	/**
	 * @return the xmdm
	 */
	public String[] getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdmҪ���õ� xmdm
	 */
	public void setXmdm(String[] xmdm) {
		this.xmdm = xmdm;
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
	 * @return the rcmc
	 */
	public String getRcmc() {
		return rcmc;
	}
	/**
	 * @param rcmcҪ���õ� rcmc
	 */
	public void setRcmc(String rcmc) {
		this.rcmc = rcmc;
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
	 * @return the fslx
	 */
	public String getFslx() {
		return fslx;
	}
	/**
	 * @param fslxҪ���õ� fslx
	 */
	public void setFslx(String fslx) {
		this.fslx = fslx;
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
	 * @return the tjzt
	 */
	public String getTjzt() {
		return tjzt;
	}
	/**
	 * @param tjztҪ���õ� tjzt
	 */
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}

	
}
