/**
 * @部门:学工产品事业部
 * @日期：2016-9-5 上午10:25:42 
 */  
package xsgzgl.gygl.ydgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 用电管理模块
 * @作者： caopei[工号:1352]
 * @时间： 2016-9-5 上午10:25:42 
 */

public class YdxxForm extends ActionForm{
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	
	private String lddm;
    private String qsh;
	private String ydyf;
	private String ds;
	private String df;
	private String dfye;
	private String jlr;
	private String ydxxid;
	private String jlsj;
	
	private String type;
	private String ch;
	private String cws;
	private String bz;
	private String path;
	
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path要设置的 path
	 */
	public void setPath(String path) {
		this.path = path;
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
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddm要设置的 lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @param qsh要设置的 qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	/**
	 * @return the ydyf
	 */
	public String getYdyf() {
		return ydyf;
	}
	/**
	 * @param ydyf要设置的 ydyf
	 */
	public void setYdyf(String ydyf) {
		this.ydyf = ydyf;
	}
	/**
	 * @return the ds
	 */
	public String getDs() {
		return ds;
	}
	/**
	 * @param ds要设置的 ds
	 */
	public void setDs(String ds) {
		this.ds = ds;
	}
	/**
	 * @return the df
	 */
	public String getDf() {
		return df;
	}
	/**
	 * @param df要设置的 df
	 */
	public void setDf(String df) {
		this.df = df;
	}
	/**
	 * @return the dfye
	 */
	public String getDfye() {
		return dfye;
	}
	/**
	 * @param dfye要设置的 dfye
	 */
	public void setDfye(String dfye) {
		this.dfye = dfye;
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
	 * @return the ch
	 */
	public String getCh() {
		return ch;
	}
	/**
	 * @param ch要设置的 ch
	 */
	public void setCh(String ch) {
		this.ch = ch;
	}
	/**
	 * @return the cws
	 */
	public String getCws() {
		return cws;
	}
	/**
	 * @param cws要设置的 cws
	 */
	public void setCws(String cws) {
		this.cws = cws;
	}
	/**
	 * @return the jlr
	 */
	public String getJlr() {
		return jlr;
	}
	/**
	 * @param jlr要设置的 jlr
	 */
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}
	/**
	 * @return the ydxxid
	 */
	public String getYdxxid() {
		return ydxxid;
	}
	/**
	 * @param ydxxid要设置的 ydxxid
	 */
	public void setYdxxid(String ydxxid) {
		this.ydxxid = ydxxid;
	}
	/**
	 * @return the jlsj
	 */
	public String getJlsj() {
		return jlsj;
	}
	/**
	 * @param jlsj要设置的 jlsj
	 */
	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}
	
	
	
	
	
	
}
