/**
 * @部门:学工产品事业部
 * @日期：2014-6-3 上午10:03:33 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbjggl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-6-3 上午10:03:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XxsbjgForm extends ActionForm {
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;
	
	private String sbjgid;
	
	private String sbsqid;
	
	private String xh;
	
	private String sblx;
	
	private String sbsj;
	
	private String sbzbid;
	
	private String ztqk;
	
	private String xlxsxxqk;
	
	private String bz;
	
	private String sjly;
	
	private String xn;
	
	private String xq;

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
	 * @return the sbjgid
	 */
	public String getSbjgid() {
		return sbjgid;
	}

	/**
	 * @param sbjgid要设置的 sbjgid
	 */
	public void setSbjgid(String sbjgid) {
		this.sbjgid = sbjgid;
	}

	/**
	 * @return the sbsqid
	 */
	public String getSbsqid() {
		return sbsqid;
	}

	/**
	 * @param sbsqid要设置的 sbsqid
	 */
	public void setSbsqid(String sbsqid) {
		this.sbsqid = sbsqid;
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
	 * @return the sblx
	 */
	public String getSblx() {
		return sblx;
	}

	/**
	 * @param sblx要设置的 sblx
	 */
	public void setSblx(String sblx) {
		this.sblx = sblx;
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
	 * @return the sbzbid
	 */
	public String getSbzbid() {
		return sbzbid;
	}

	/**
	 * @param sbzbid要设置的 sbzbid
	 */
	public void setSbzbid(String sbzbid) {
		this.sbzbid = sbzbid;
	}

	/**
	 * @return the ztqk
	 */
	public String getZtqk() {
		return ztqk;
	}

	/**
	 * @param ztqk要设置的 ztqk
	 */
	public void setZtqk(String ztqk) {
		this.ztqk = ztqk;
	}

	/**
	 * @return the xlxsxxqk
	 */
	public String getXlxsxxqk() {
		return xlxsxxqk;
	}

	/**
	 * @param xlxsxxqk要设置的 xlxsxxqk
	 */
	public void setXlxsxxqk(String xlxsxxqk) {
		this.xlxsxxqk = xlxsxxqk;
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
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}

	/**
	 * @param sjly要设置的 sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}
}
