/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-18 ����04:43:18 
 */  
package com.zfsoft.xgxt.dagl.daxxgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2014-2-18 ����04:43:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DaxxglForm extends ActionForm {
	
	
	/** 
	 * @���� serialVersionUID 
	 */ 
	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String pk;
	private String xh;
	private String dazrsj;
	private String dazrfs;
	private String zrfsbh;
	private String dazcsj;
	private String dazcfs;
	private String zcfsbh;
	private String dazjdw;
	private String dazjyb;
	private String dazjdz;
	private String dazjdwlxr;
	private String dazjdwdh;
	private String byqxdm;
	private String dwmc;
	private String dwyb;
	private String dwdz;
	private String dwlxr;
	private String dwlxdh;
	private String bz;
	private String daqdmb_id;

	private String searchTj;
	private String searchTjz;
	private String mhcx_lx;
	private String searchLx;
	private String input_mhcx;
	private String path;
	
	/**
	 * ��ѡ��ѧ��
	 */
	private String values;
	
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public String getInput_mhcx() {
		return input_mhcx;
	}
	public void setInput_mhcx(String inputMhcx) {
		input_mhcx = inputMhcx;
	}
	public String getSearchTj() {
		return searchTj;
	}
	public void setSearchTj(String searchTj) {
		this.searchTj = searchTj;
	}
	public String getSearchTjz() {
		return searchTjz;
	}
	public void setSearchTjz(String searchTjz) {
		this.searchTjz = searchTjz;
	}
	public String getMhcx_lx() {
		return mhcx_lx;
	}
	public void setMhcx_lx(String mhcxLx) {
		mhcx_lx = mhcxLx;
	}
	public String getSearchLx() {
		return searchLx;
	}
	public void setSearchLx(String searchLx) {
		this.searchLx = searchLx;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
	
	/**
	 * @return the pk
	 */
	public String getPk() {
		return pk;
	}
	/**
	 * @param pkҪ���õ� pk
	 */
	public void setPk(String pk) {
		this.pk = pk;
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
	 * @return the dazrsj
	 */
	public String getDazrsj() {
		return dazrsj;
	}
	/**
	 * @param dazrsjҪ���õ� dazrsj
	 */
	public void setDazrsj(String dazrsj) {
		this.dazrsj = dazrsj;
	}
	/**
	 * @return the dazrfs
	 */
	public String getDazrfs() {
		return dazrfs;
	}
	/**
	 * @param dazrfsҪ���õ� dazrfs
	 */
	public void setDazrfs(String dazrfs) {
		this.dazrfs = dazrfs;
	}
	/**
	 * @return the zrfsbh
	 */
	public String getZrfsbh() {
		return zrfsbh;
	}
	/**
	 * @param zrfsbhҪ���õ� zrfsbh
	 */
	public void setZrfsbh(String zrfsbh) {
		this.zrfsbh = zrfsbh;
	}
	/**
	 * @return the dazcsj
	 */
	public String getDazcsj() {
		return dazcsj;
	}
	/**
	 * @param dazcsjҪ���õ� dazcsj
	 */
	public void setDazcsj(String dazcsj) {
		this.dazcsj = dazcsj;
	}
	/**
	 * @return the dazcfs
	 */
	public String getDazcfs() {
		return dazcfs;
	}
	/**
	 * @param dazcfsҪ���õ� dazcfs
	 */
	public void setDazcfs(String dazcfs) {
		this.dazcfs = dazcfs;
	}
	/**
	 * @return the zcfsbh
	 */
	public String getZcfsbh() {
		return zcfsbh;
	}
	/**
	 * @param zcfsbhҪ���õ� zcfsbh
	 */
	public void setZcfsbh(String zcfsbh) {
		this.zcfsbh = zcfsbh;
	}
	/**
	 * @return the dazjdw
	 */
	public String getDazjdw() {
		return dazjdw;
	}
	/**
	 * @param dazjdwҪ���õ� dazjdw
	 */
	public void setDazjdw(String dazjdw) {
		this.dazjdw = dazjdw;
	}
	/**
	 * @return the dazjyb
	 */
	public String getDazjyb() {
		return dazjyb;
	}
	/**
	 * @param dazjybҪ���õ� dazjyb
	 */
	public void setDazjyb(String dazjyb) {
		this.dazjyb = dazjyb;
	}
	/**
	 * @return the dazjdz
	 */
	public String getDazjdz() {
		return dazjdz;
	}
	/**
	 * @param dazjdzҪ���õ� dazjdz
	 */
	public void setDazjdz(String dazjdz) {
		this.dazjdz = dazjdz;
	}
	/**
	 * @return the dazjdwlxr
	 */
	public String getDazjdwlxr() {
		return dazjdwlxr;
	}
	/**
	 * @param dazjdwlxrҪ���õ� dazjdwlxr
	 */
	public void setDazjdwlxr(String dazjdwlxr) {
		this.dazjdwlxr = dazjdwlxr;
	}
	/**
	 * @return the dazjdwdh
	 */
	public String getDazjdwdh() {
		return dazjdwdh;
	}
	/**
	 * @param dazjdwdhҪ���õ� dazjdwdh
	 */
	public void setDazjdwdh(String dazjdwdh) {
		this.dazjdwdh = dazjdwdh;
	}
	/**
	 * @return the byqxdm
	 */
	public String getByqxdm() {
		return byqxdm;
	}
	/**
	 * @param byqxdmҪ���õ� byqxdm
	 */
	public void setByqxdm(String byqxdm) {
		this.byqxdm = byqxdm;
	}
	/**
	 * @return the dwmc
	 */
	public String getDwmc() {
		return dwmc;
	}
	/**
	 * @param dwmcҪ���õ� dwmc
	 */
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	/**
	 * @return the dwyb
	 */
	public String getDwyb() {
		return dwyb;
	}
	/**
	 * @param dwybҪ���õ� dwyb
	 */
	public void setDwyb(String dwyb) {
		this.dwyb = dwyb;
	}
	/**
	 * @return the dwdz
	 */
	public String getDwdz() {
		return dwdz;
	}
	/**
	 * @param dwdzҪ���õ� dwdz
	 */
	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}
	/**
	 * @return the dwlxr
	 */
	public String getDwlxr() {
		return dwlxr;
	}
	/**
	 * @param dwlxrҪ���õ� dwlxr
	 */
	public void setDwlxr(String dwlxr) {
		this.dwlxr = dwlxr;
	}
	
	/**
	 * @return the dwlxdh
	 */
	public String getDwlxdh() {
		return dwlxdh;
	}
	/**
	 * @param dwlxdhҪ���õ� dwlxdh
	 */
	public void setDwlxdh(String dwlxdh) {
		this.dwlxdh = dwlxdh;
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
	 * @return the daqdmb_id
	 */
	public String getDaqdmb_id() {
		return daqdmb_id;
	}
	/**
	 * @param daqdmb_idҪ���õ� daqdmb_id
	 */
	public void setDaqdmb_id(String daqdmb_id) {
		this.daqdmb_id = daqdmb_id;
	}
	
	
}
