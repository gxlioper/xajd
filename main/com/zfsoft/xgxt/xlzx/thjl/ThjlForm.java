/**
 * @部门:学工产品事业部
 * @日期：2013-9-10 上午11:12:19 
 */  
package com.zfsoft.xgxt.xlzx.thjl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 特殊学生维护模块(这里用一句话描述这个类的作用) 
 * @作者： wanghj [工号：1004]
 * @时间： 2013-9-10 上午11:10:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ThjlForm extends ActionForm{
	private static final long serialVersionUID = 1L;


	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	  
	private String id; // 主键
	
	private String xh; //学号

	private String zgh; // 编号（职工号）
	
	private String thsj; //谈话时间
	
	private String thnr; // 谈话内容
	
	private String bz; // 备注

	private String cjsj; //创建时间

	private String  sjzt; //数据状态0失效1正常
	
	private String  fjid; //附件id

	private ExportModel exportModel = new ExportModel();

	/**
	 * @return the fjid
	 */
	public String getFjid() {
		return fjid;
	}

	/**
	 * @param fjid要设置的 fjid
	 */
	public void setFjid(String fjid) {
		this.fjid = fjid;
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
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}

	/**
	 * @param zgh要设置的 zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
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
	 * @return the thsj
	 */
	public String getThsj() {
		return thsj;
	}

	/**
	 * @param thsj要设置的 thsj
	 */
	public void setThsj(String thsj) {
		this.thsj = thsj;
	}

	/**
	 * @return the thnr
	 */
	public String getThnr() {
		return thnr;
	}

	/**
	 * @param thnr要设置的 thnr
	 */
	public void setThnr(String thnr) {
		this.thnr = thnr;
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
	 * @return the cjsj
	 */
	public String getCjsj() {
		return cjsj;
	}

	/**
	 * @param cjsj要设置的 cjsj
	 */
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	/**
	 * @return the sjzt
	 */
	public String getSjzt() {
		return sjzt;
	}

	/**
	 * @param sjzt要设置的 sjzt
	 */
	public void setSjzt(String sjzt) {
		this.sjzt = sjzt;
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
