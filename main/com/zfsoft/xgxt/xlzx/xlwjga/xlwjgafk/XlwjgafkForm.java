package com.zfsoft.xgxt.xlzx.xlwjga.xlwjgafk;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 心理危机个案反馈
 */
public class XlwjgafkForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String xswjbx;
	private String xyclgc;
	private String ywzyls;
	private String zyqk;
	private String xszk;
	private String wjgabz;
	private String wjgabzmc;
	private String zxfk;
	private String id;
	private String xh;
	private String sbr;
	private String sbsj;
	private String sbrxm;
	private String sbrlxfs;
	private String wjcd;
	private String wjcdmc;
	private String zzfk;
	private String zzfkmc;
	
	private String type;
	// 分页
	Pages pages = new Pages();
	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the xswjbx
	 */
	public String getXswjbx() {
		return xswjbx;
	}
	/**
	 * @param xswjbx要设置的 xswjbx
	 */
	public void setXswjbx(String xswjbx) {
		this.xswjbx = xswjbx;
	}
	/**
	 * @return the xyclgc
	 */
	public String getXyclgc() {
		return xyclgc;
	}
	/**
	 * @param xyclgc要设置的 xyclgc
	 */
	public void setXyclgc(String xyclgc) {
		this.xyclgc = xyclgc;
	}
	/**
	 * @return the ywzyls
	 */
	public String getYwzyls() {
		return ywzyls;
	}
	/**
	 * @param ywzyls要设置的 ywzyls
	 */
	public void setYwzyls(String ywzyls) {
		this.ywzyls = ywzyls;
	}
	/**
	 * @return the zyqk
	 */
	public String getZyqk() {
		return zyqk;
	}
	/**
	 * @param zyqk要设置的 zyqk
	 */
	public void setZyqk(String zyqk) {
		this.zyqk = zyqk;
	}
	/**
	 * @return the xszk
	 */
	public String getXszk() {
		return xszk;
	}
	/**
	 * @param xszk要设置的 xszk
	 */
	public void setXszk(String xszk) {
		this.xszk = xszk;
	}
	/**
	 * @return the wjgabz
	 */
	public String getWjgabz() {
		return wjgabz;
	}
	/**
	 * @param wjgabz要设置的 wjgabz
	 */
	public void setWjgabz(String wjgabz) {
		this.wjgabz = wjgabz;
	}
	/**
	 * @return the wjgabzmc
	 */
	public String getWjgabzmc() {
		return wjgabzmc;
	}
	/**
	 * @param wjgabzmc要设置的 wjgabzmc
	 */
	public void setWjgabzmc(String wjgabzmc) {
		this.wjgabzmc = wjgabzmc;
	}
	/**
	 * @return the zxfk
	 */
	public String getZxfk() {
		return zxfk;
	}
	/**
	 * @param zxfk要设置的 zxfk
	 */
	public void setZxfk(String zxfk) {
		this.zxfk = zxfk;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the sbr
	 */
	public String getSbr() {
		return sbr;
	}
	/**
	 * @param sbr要设置的 sbr
	 */
	public void setSbr(String sbr) {
		this.sbr = sbr;
	}
	/**
	 * @return the sbsj
	 */
	public String getSbsj() {
		return sbsj;
	}
	/**
	 * @param sbsj要设置的 sbsj
	 */
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}
	/**
	 * @return the sbrxm
	 */
	public String getSbrxm() {
		return sbrxm;
	}
	/**
	 * @param sbrxm要设置的 sbrxm
	 */
	public void setSbrxm(String sbrxm) {
		this.sbrxm = sbrxm;
	}
	/**
	 * @return the sbrlxfs
	 */
	public String getSbrlxfs() {
		return sbrlxfs;
	}
	/**
	 * @param sbrlxfs要设置的 sbrlxfs
	 */
	public void setSbrlxfs(String sbrlxfs) {
		this.sbrlxfs = sbrlxfs;
	}
	/**
	 * @return the wjcd
	 */
	public String getWjcd() {
		return wjcd;
	}
	/**
	 * @param wjcd要设置的 wjcd
	 */
	public void setWjcd(String wjcd) {
		this.wjcd = wjcd;
	}
	/**
	 * @return the wjcdmc
	 */
	public String getWjcdmc() {
		return wjcdmc;
	}
	/**
	 * @param wjcdmc要设置的 wjcdmc
	 */
	public void setWjcdmc(String wjcdmc) {
		this.wjcdmc = wjcdmc;
	}
	/**
	 * @return the zzfk
	 */
	public String getZzfk() {
		return zzfk;
	}
	/**
	 * @param zzfk要设置的 zzfk
	 */
	public void setZzfk(String zzfk) {
		this.zzfk = zzfk;
	}
	/**
	 * @return the zzfkmc
	 */
	public String getZzfkmc() {
		return zzfkmc;
	}
	/**
	 * @param zzfkmc要设置的 zzfkmc
	 */
	public void setZzfkmc(String zzfkmc) {
		this.zzfkmc = zzfkmc;
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
	
}
