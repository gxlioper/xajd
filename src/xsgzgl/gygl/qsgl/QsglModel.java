package xsgzgl.gygl.qsgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class QsglModel {
	// 通用 
	Pages pages;
	// 高级查询
	SearchModel searchModel; 
	
	private String sfbz;     //收费标准
	private String nj;     //所属年级
	private String bz;       //备注
	private String qsdh;     //寝室电话
	private String xydm;     //所属学院代码
	private String qsxb;     //寝室性别
	private String cws;      //床位数
	private String lddm;     //楼栋代码
	private String qsh;      //寝室号
	private String yqsh;	//原寝室号
	private String ch;       //层号
	private ExportModel exportModel = new ExportModel();
	private String ywkt;  //有无空调
	
	public String getSfbz() {
		return sfbz;
	}
	public void setSfbz(String sfbz) {
		this.sfbz = sfbz;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getQsdh() {
		return qsdh;
	}
	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getQsxb() {
		return qsxb;
	}
	public void setQsxb(String qsxb) {
		this.qsxb = qsxb;
	}
	public String getCws() {
		return cws;
	}
	public void setCws(String cws) {
		this.cws = cws;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
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
	 * @return the yqsh
	 */
	public String getYqsh() {
		return yqsh;
	}
	/**
	 * @param yqsh要设置的 yqsh
	 */
	public void setYqsh(String yqsh) {
		this.yqsh = yqsh;
	}
	/**
	 * @return the ywkt
	 */
	public String getYwkt() {
		return ywkt;
	}
	/**
	 * @param ywkt要设置的 ywkt
	 */
	public void setYwkt(String ywkt) {
		this.ywkt = ywkt;
	}
	
	
}
