/**
 * @部门:学工产品事业部
 * @日期：2014-2-18 下午04:43:18 
 */  
package com.zfsoft.xgxt.dagl.daxxgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： wanghj [工号：1004]
 * @时间： 2014-2-18 下午04:43:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DaxxglForm extends ActionForm {
	
	
	/** 
	 * @变量 serialVersionUID 
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
	 * 已选择学生
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
	 * @param type要设置的 type
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
	 * @param pages要设置的 pages
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
	 * @param searchModel要设置的 searchModel
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
	 * @param exportModel要设置的 exportModel
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
	 * @param pk要设置的 pk
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
	 * @param xh要设置的 xh
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
	 * @param dazrsj要设置的 dazrsj
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
	 * @param dazrfs要设置的 dazrfs
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
	 * @param zrfsbh要设置的 zrfsbh
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
	 * @param dazcsj要设置的 dazcsj
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
	 * @param dazcfs要设置的 dazcfs
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
	 * @param zcfsbh要设置的 zcfsbh
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
	 * @param dazjdw要设置的 dazjdw
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
	 * @param dazjyb要设置的 dazjyb
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
	 * @param dazjdz要设置的 dazjdz
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
	 * @param dazjdwlxr要设置的 dazjdwlxr
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
	 * @param dazjdwdh要设置的 dazjdwdh
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
	 * @param byqxdm要设置的 byqxdm
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
	 * @param dwmc要设置的 dwmc
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
	 * @param dwyb要设置的 dwyb
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
	 * @param dwdz要设置的 dwdz
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
	 * @param dwlxr要设置的 dwlxr
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
	 * @param dwlxdh要设置的 dwlxdh
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
	 * @param bz要设置的 bz
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
	 * @param daqdmb_id要设置的 daqdmb_id
	 */
	public void setDaqdmb_id(String daqdmb_id) {
		this.daqdmb_id = daqdmb_id;
	}
	
	
}
