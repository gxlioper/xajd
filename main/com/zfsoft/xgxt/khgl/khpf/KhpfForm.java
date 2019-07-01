/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-12 ����02:35:57 
 */  
package com.zfsoft.xgxt.khgl.khpf;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������
 * @�๦������: Form
 * @���ߣ�cq [����:785]
 * @ʱ�䣺 2015-8-12 ����02:35:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KhpfForm extends ActionForm {
	
	private static final long serialVersionUID = -4285619768768178920L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String pfid;
	private String xmid;
	private String xmmc;
	private String khbid;
	private String khbmc;
	private String pfr;
	private String sftj;
	private String sfyx;
	private String khdxr;
	private String khlx;
	private String zbmxid;
	private String fs;
	private String xmszid;
	private String yjjy;
	private String bz;
	private String shzt;
	private String xn;
	private String pflx;

	private String [] zbmxidArr;
	private String [] fsArr;
	
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
	 * @return the pfid
	 */
	public String getPfid() {
		return pfid;
	}
	/**
	 * @param pfidҪ���õ� pfid
	 */
	public void setPfid(String pfid) {
		this.pfid = pfid;
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
	 * @return the khbid
	 */
	public String getKhbid() {
		return khbid;
	}
	/**
	 * @param khbidҪ���õ� khbid
	 */
	public void setKhbid(String khbid) {
		this.khbid = khbid;
	}
	/**
	 * @return the pfr
	 */
	public String getPfr() {
		return pfr;
	}
	/**
	 * @param pfrҪ���õ� pfr
	 */
	public void setPfr(String pfr) {
		this.pfr = pfr;
	}
	/**
	 * @return the sftj
	 */
	public String getSftj() {
		return sftj;
	}
	/**
	 * @param sftjҪ���õ� sftj
	 */
	public void setSftj(String sftj) {
		this.sftj = sftj;
	}
	/**
	 * @return the sfyx
	 */
	public String getSfyx() {
		return sfyx;
	}
	/**
	 * @param sfyxҪ���õ� sfyx
	 */
	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
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
	 * @return the khbmc
	 */
	public String getKhbmc() {
		return khbmc;
	}
	/**
	 * @param khbmcҪ���õ� khbmc
	 */
	public void setKhbmc(String khbmc) {
		this.khbmc = khbmc;
	}
	/**
	 * @return the zbmxid
	 */
	public String getZbmxid() {
		return zbmxid;
	}
	/**
	 * @param zbmxidҪ���õ� zbmxid
	 */
	public void setZbmxid(String zbmxid) {
		this.zbmxid = zbmxid;
	}
	/**
	 * @return the fs
	 */
	public String getFs() {
		return fs;
	}
	/**
	 * @param fsҪ���õ� fs
	 */
	public void setFs(String fs) {
		this.fs = fs;
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
	/**
	 * @return the yjjy
	 */
	public String getYjjy() {
		return yjjy;
	}
	/**
	 * @param yjjyҪ���õ� yjjy
	 */
	public void setYjjy(String yjjy) {
		this.yjjy = yjjy;
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

	public String[] getZbmxidArr() {
		return zbmxidArr;
	}

	public void setZbmxidArr(String[] zbmxidArr) {
		this.zbmxidArr = zbmxidArr;
	}

	public String[] getFsArr() {
		return fsArr;
	}

	public void setFsArr(String[] fsArr) {
		this.fsArr = fsArr;
	}
}
