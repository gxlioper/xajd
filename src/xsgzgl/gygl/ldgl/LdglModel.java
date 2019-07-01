package xsgzgl.gygl.ldgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class LdglModel {

	
	private static final long serialVersionUID = 1L;

	// 分页
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();
	
	private String[] checkVal;

	private String lddm;//楼栋代码
	private String ldmc;//楼栋名称
	private String ldxb;//楼栋性别
	private String ldcs;//楼栋层数
	private String qsch;//起始层号
	private String sfhlc;//是否含0层
	private String xqdm;//校区代码
	private String yqdm;//园区代码
	private String bz;//备注

	private String originalLdcs;	//原始楼栋层数
	private String sfkzjlc;	//是否可增加楼层，1：是

	private ExportModel exportModel = new ExportModel();
	/**
	 * @return pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pages 要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModel 要设置的 searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddm 要设置的 lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @return ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmc 要设置的 ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	/**
	 * @return ldxb
	 */
	public String getLdxb() {
		return ldxb;
	}
	/**
	 * @param ldxb 要设置的 ldxb
	 */
	public void setLdxb(String ldxb) {
		this.ldxb = ldxb;
	}
	/**
	 * @return ldcs
	 */
	public String getLdcs() {
		return ldcs;
	}
	/**
	 * @param ldcs 要设置的 ldcs
	 */
	public void setLdcs(String ldcs) {
		this.ldcs = ldcs;
	}
	/**
	 * @return qsch
	 */
	public String getQsch() {
		return qsch;
	}
	/**
	 * @param qsch 要设置的 qsch
	 */
	public void setQsch(String qsch) {
		this.qsch = qsch;
	}
	/**
	 * @return sfhlc
	 */
	public String getSfhlc() {
		return sfhlc;
	}
	/**
	 * @param sfhlc 要设置的 sfhlc
	 */
	public void setSfhlc(String sfhlc) {
		this.sfhlc = sfhlc;
	}
	/**
	 * @return xqdm
	 */
	public String getXqdm() {
		return xqdm;
	}
	/**
	 * @param xqdm 要设置的 xqdm
	 */
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	/**
	 * @return yqdm
	 */
	public String getYqdm() {
		return yqdm;
	}
	/**
	 * @param yqdm 要设置的 yqdm
	 */
	public void setYqdm(String yqdm) {
		this.yqdm = yqdm;
	}
	/**
	 * @return bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz 要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return checkVal
	 */
	public String[] getCheckVal() {
		return checkVal;
	}
	/**
	 * @param checkVal 要设置的 checkVal
	 */
	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
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

	public String getOriginalLdcs() {
		return originalLdcs;
	}

	public void setOriginalLdcs(String originalLdcs) {
		this.originalLdcs = originalLdcs;
	}

	public String getSfkzjlc() {
		return sfkzjlc;
	}

	public void setSfkzjlc(String sfkzjlc) {
		this.sfkzjlc = sfkzjlc;
	}
}
