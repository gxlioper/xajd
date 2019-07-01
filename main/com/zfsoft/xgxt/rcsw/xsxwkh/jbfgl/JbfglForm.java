/**
 * @部门:学工产品事业部
 * @日期：2016-8-2 下午04:09:29 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.jbfgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 基本分表pojo
 * @作者： caopei[工号:1352]
 * @时间： 2016-8-2 下午04:09:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JbfglForm extends ActionForm{
	private String jbfid;	
	private String xn;
	private String xh;
	private String bzrcpdj;//班主任或辅导员测评等级
	private String bzrcpfz;//-------------测评分值
	private String xscpdj;//学生测评等级
	private String xscpfz;//-------分值
	private String bz;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the jbfid
	 */
	public String getJbfid() {
		return jbfid;
	}
	/**
	 * @param jbfid要设置的 jbfid
	 */
	public void setJbfid(String jbfid) {
		this.jbfid = jbfid;
	}
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
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
	 * @return the bzrcpdj
	 */
	public String getBzrcpdj() {
		return bzrcpdj;
	}
	/**
	 * @param bzrcpdj要设置的 bzrcpdj
	 */
	public void setBzrcpdj(String bzrcpdj) {
		this.bzrcpdj = bzrcpdj;
	}
	/**
	 * @return the bzrcpfz
	 */
	public String getBzrcpfz() {
		return bzrcpfz;
	}
	/**
	 * @param bzrcpfz要设置的 bzrcpfz
	 */
	public void setBzrcpfz(String bzrcpfz) {
		this.bzrcpfz = bzrcpfz;
	}
	/**
	 * @return the xscpdj
	 */
	public String getXscpdj() {
		return xscpdj;
	}
	/**
	 * @param xscpdj要设置的 xscpdj
	 */
	public void setXscpdj(String xscpdj) {
		this.xscpdj = xscpdj;
	}
	/**
	 * @return the xscpfz
	 */
	public String getXscpfz() {
		return xscpfz;
	}
	/**
	 * @param xscpfz要设置的 xscpfz
	 */
	public void setXscpfz(String xscpfz) {
		this.xscpfz = xscpfz;
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
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2016-8-5 上午09:44:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param split
	 * @return
	 * int 返回类型 
	 * @throws 
	 */

}
