/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-18 ����01:57:33 
 */  
package com.zfsoft.xgxt.gygl.lkxxgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-4-18 ����01:57:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LkxxForm extends ActionForm{
	private String id;
	private String xm;
	private String sfzh;
	private String xb;
	private String hkszd;
	private String rzld;
	private String rzfj;
	private String rzcw;
	private String rzyj;
	private String rzsj;
	private String tssj;
	private String bz;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
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
	 * @return the sfzh
	 */
	public String getSfzh() {
		return sfzh;
	}
	/**
	 * @param sfzhҪ���õ� sfzh
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}
	/**
	 * @param xbҪ���õ� xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}
	/**
	 * @return the hkszd
	 */
	public String getHkszd() {
		return hkszd;
	}
	/**
	 * @param hkszdҪ���õ� hkszd
	 */
	public void setHkszd(String hkszd) {
		this.hkszd = hkszd;
	}
	/**
	 * @return the rzld
	 */
	public String getRzld() {
		return rzld;
	}
	/**
	 * @param rzldҪ���õ� rzld
	 */
	public void setRzld(String rzld) {
		this.rzld = rzld;
	}
	/**
	 * @return the rzfj
	 */
	public String getRzfj() {
		return rzfj;
	}
	/**
	 * @param rzfjҪ���õ� rzfj
	 */
	public void setRzfj(String rzfj) {
		this.rzfj = rzfj;
	}
	/**
	 * @return the rzcw
	 */
	public String getRzcw() {
		return rzcw;
	}
	/**
	 * @param rzcwҪ���õ� rzcw
	 */
	public void setRzcw(String rzcw) {
		this.rzcw = rzcw;
	}
	/**
	 * @return the rzyj
	 */
	public String getRzyj() {
		return rzyj;
	}
	/**
	 * @param rzyjҪ���õ� rzyj
	 */
	public void setRzyj(String rzyj) {
		this.rzyj = rzyj;
	}
	/**
	 * @return the rzsj
	 */
	public String getRzsj() {
		return rzsj;
	}
	/**
	 * @param rzsjҪ���õ� rzsj
	 */
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	/**
	 * @return the tssj
	 */
	public String getTssj() {
		return tssj;
	}
	/**
	 * @param tssjҪ���õ� tssj
	 */
	public void setTssj(String tssj) {
		this.tssj = tssj;
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
	
	
	
}
