/**
 * @部门:学工产品事业部
 * @日期：2014-11-5 上午09:29:08 
 */  
package com.zfsoft.xgxt.zxdk.tqhk;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 提前还款 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-11-5 上午09:29:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TqhkModel extends SuperAuditModel {

	private static final long serialVersionUID = 5193255054692579117L;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String id;
	private String xh;
	private String hkzh;
	private String hkje;
	private String hkbj;
	private String hkly;
	private String bz;
	private String splcid;
	private String shzt;
	private String sqsj;
	private String filepath;
	
	
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
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
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
	 * @return the hkbj
	 */
	public String getHkbj() {
		return hkbj;
	}
	/**
	 * @param hkbj要设置的 hkbj
	 */
	public void setHkbj(String hkbj) {
		this.hkbj = hkbj;
	}
	/**
	 * @return the hkzh
	 */
	public String getHkzh() {
		return hkzh;
	}
	/**
	 * @param hkzh要设置的 hkzh
	 */
	public void setHkzh(String hkzh) {
		this.hkzh = hkzh;
	}
	/**
	 * @return the hkje
	 */
	public String getHkje() {
		return hkje;
	}
	/**
	 * @param hkje要设置的 hkje
	 */
	public void setHkje(String hkje) {
		this.hkje = hkje;
	}
	/**
	 * @return the hkly
	 */
	public String getHkly() {
		return hkly;
	}
	/**
	 * @param hkly要设置的 hkly
	 */
	public void setHkly(String hkly) {
		this.hkly = hkly;
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
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcid要设置的 splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	
	
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepath要设置的 filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
}
