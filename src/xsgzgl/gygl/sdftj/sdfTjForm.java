/**
 * @部门:学工产品事业部
 * @日期：2016-12-9 下午02:25:12 
 */  
package xsgzgl.gygl.sdftj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-12-9 下午02:25:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class sdfTjForm extends ActionForm {
	 private String id;
	 private String  nd;
	 private String  jd;
	 private String  lddm;
	 private String  qsh;
	 private String  sf;
	 private String  df;
	 private static final long serialVersionUID = 1L;
	 private ExportModel exportModel = new ExportModel();
	 private SearchModel searchModel = new SearchModel();
	 private Pages pages = new Pages();
	 private String type;
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
	 * @return the nd
	 */
	public String getNd() {
		return nd;
	}
	/**
	 * @param nd要设置的 nd
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}
	/**
	 * @return the jd
	 */
	public String getJd() {
		return jd;
	}
	/**
	 * @param jd要设置的 jd
	 */
	public void setJd(String jd) {
		this.jd = jd;
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
	 * @return the sf
	 */
	public String getSf() {
		return sf;
	}
	/**
	 * @param sf要设置的 sf
	 */
	public void setSf(String sf) {
		this.sf = sf;
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
	
}
