/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-19 ����05:01:04 
 */  
package com.zfsoft.xgxt.dagl.cqxxdaxxgl.daxxjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�linguoxia	[����:1553]
 * @ʱ�䣺 2016-8-19 ����05:01:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CqxxDaxxjgForm extends ActionForm{
	private String daxxid;	
	private String xn;
	private String xh;
	private String kddh;
	private String yjdz;
	private String yjr;
	private String sj;
	private String bz;
	private String pj;

	private String xjzt;//ѧ��״̬
	private String dahh;//�����к�
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the xjzt
	 */
	public String getXjzt() {
		return xjzt;
	}
	/**
	 * @param xjztҪ���õ� xjzt
	 */
	public void setXjzt(String xjzt) {
		this.xjzt = xjzt;
	}
	/**
	 * @return the dahh
	 */
	public String getDahh() {
		return dahh;
	}
	/**
	 * @param dahhҪ���õ� dahh
	 */
	public void setDahh(String dahh) {
		this.dahh = dahh;
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
	
}
