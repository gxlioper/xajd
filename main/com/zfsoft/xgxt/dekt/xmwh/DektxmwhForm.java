package com.zfsoft.xgxt.dekt.xmwh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class DektxmwhForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel(); //高级查询
	private Pages pages = new Pages(); // 分页
	private ExportModel exportModel = new ExportModel(); //自定义导出
	
	private String xmid; //项目ID
	private String ssxydm; //所属学院代码
	private String xmdl; //项目大类
	private String lx; //类型
	private String rdxm; //认定项目
	private String rdnrbz; //认定内容标准
	private String dj; //等级
	private String xf; //学风
	private String yjsm; //依据说明
	private String splc; //审批流程
	
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
	 * @return the xmid
	 */
	public String getXmid() {
		return xmid;
	}
	/**
	 * @param xmid要设置的 xmid
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	/**
	 * @return the ssxydm
	 */
	public String getSsxydm() {
		return ssxydm;
	}
	/**
	 * @param ssxydm要设置的 ssxydm
	 */
	public void setSsxydm(String ssxydm) {
		this.ssxydm = ssxydm;
	}
	/**
	 * @return the xmdl
	 */
	public String getXmdl() {
		return xmdl;
	}
	/**
	 * @param xmdl要设置的 xmdl
	 */
	public void setXmdl(String xmdl) {
		this.xmdl = xmdl;
	}
	/**
	 * @return the lx
	 */
	public String getLx() {
		return lx;
	}
	/**
	 * @param lx要设置的 lx
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}
	/**
	 * @return the rdxm
	 */
	public String getRdxm() {
		return rdxm;
	}
	/**
	 * @param rdxm要设置的 rdxm
	 */
	public void setRdxm(String rdxm) {
		this.rdxm = rdxm;
	}
	/**
	 * @return the rdnrbz
	 */
	public String getRdnrbz() {
		return rdnrbz;
	}
	/**
	 * @param rdnrbz要设置的 rdnrbz
	 */
	public void setRdnrbz(String rdnrbz) {
		this.rdnrbz = rdnrbz;
	}
	/**
	 * @return the dj
	 */
	public String getDj() {
		return dj;
	}
	/**
	 * @param dj要设置的 dj
	 */
	public void setDj(String dj) {
		this.dj = dj;
	}
	/**
	 * @return the xf
	 */
	public String getXf() {
		return xf;
	}
	/**
	 * @param xf要设置的 xf
	 */
	public void setXf(String xf) {
		this.xf = xf;
	}
	/**
	 * @return the yjsm
	 */
	public String getYjsm() {
		return yjsm;
	}
	/**
	 * @param yjsm要设置的 yjsm
	 */
	public void setYjsm(String yjsm) {
		this.yjsm = yjsm;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
