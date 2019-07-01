/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.dsgl.smwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class SmwhForm extends ActionForm{
	private Pages pages = new Pages();
	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();

	private String type;
	private String smid;
	private String nj;
	private String ssm;
	private String cbs;
	private String author;
	private String lx;
	private String ebook;
	private String sftj;//是否推荐
	private String stp;//书图片

	public String getStp() {
		return stp;
	}

	public void setStp(String stp) {
		this.stp = stp;
	}

	public String getSftj() {
		return sftj;
	}

	public void setSftj(String sftj) {
		this.sftj = sftj;
	}

	/**

	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @return		: the smid
	 */
	public String getSmid() {
		return smid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @param 		：smid the smid to set
	 */
	public void setSmid(String smid) {
		this.smid = smid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @return		: the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @param 		：nj the nj to set
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @return		: the ssm
	 */
	public String getSsm() {
		return ssm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @param 		：ssm the ssm to set
	 */
	public void setSsm(String ssm) {
		this.ssm = ssm;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @return		: the cbs
	 */
	public String getCbs() {
		return cbs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @param 		：cbs the cbs to set
	 */
	public void setCbs(String cbs) {
		this.cbs = cbs;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @return		: the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @param 		：author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @return		: the lx
	 */
	public String getLx() {
		return lx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @param 		：lx the lx to set
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @return		: the ebook
	 */
	public String getEbook() {
		return ebook;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-12 上午11:29:09 
	 * @param 		：ebook the ebook to set
	 */
	public void setEbook(String ebook) {
		this.ebook = ebook;
	}



	
}
