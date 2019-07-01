/**
 * @部门:学工产品事业部
 * @日期：2015-4-8 上午11:29:07 
 */
package com.zfsoft.xgxt.zxdk.tyxs.shjg;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 退役学生管理模块
 * @类功能描述: 审核结果表 
 * @作者： 冯兰英[工号:1177]
 * @时间： 2015-4-23 下午04:56:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class TyxsZzjg extends ActionForm {
	/**
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */

	private static final long serialVersionUID = 1L;
	private String id;
	private String xh;
	private String xn;
	private String sqid;
	private String zzzje;
	private String sqxfzj;
	private String sqsj;
	private String sqly;
	private String dkbj;
	private String yhdm;
	private String dkhth;
	private String dkkssj;
	private String dkjssj;
	private String dklx;
	
	//下载相关
	private FormFile formfile;
	private String filepath;
	
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	/**
	 * @return the dkbj
	 */
	public String getDkbj() {
		return dkbj;
	}
	/**
	 * @param dkbj要设置的 dkbj
	 */
	public void setDkbj(String dkbj) {
		this.dkbj = dkbj;
	}
	/**
	 * @return the yhdm
	 */
	public String getYhdm() {
		return yhdm;
	}
	/**
	 * @param yhdm要设置的 yhdm
	 */
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	/**
	 * @return the dkhth
	 */
	public String getDkhth() {
		return dkhth;
	}
	/**
	 * @param dkhth要设置的 dkhth
	 */
	public void setDkhth(String dkhth) {
		this.dkhth = dkhth;
	}
	/**
	 * @return the dkkssj
	 */
	public String getDkkssj() {
		return dkkssj;
	}
	/**
	 * @param dkkssj要设置的 dkkssj
	 */
	public void setDkkssj(String dkkssj) {
		this.dkkssj = dkkssj;
	}
	/**
	 * @return the dkjssj
	 */
	public String getDkjssj() {
		return dkjssj;
	}
	/**
	 * @param dkjssj要设置的 dkjssj
	 */
	public void setDkjssj(String dkjssj) {
		this.dkjssj = dkjssj;
	}
	/**
	 * @return the dklx
	 */
	public String getDklx() {
		return dklx;
	}
	/**
	 * @param dklx要设置的 dklx
	 */
	public void setDklx(String dklx) {
		this.dklx = dklx;
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
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	
	
	

	/**
	 * @return the zzzje
	 */
	public String getZzzje() {
		return zzzje;
	}
	/**
	 * @param zzzje要设置的 zzzje
	 */
	public void setZzzje(String zzzje) {
		this.zzzje = zzzje;
	}
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
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqly要设置的 sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
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
	 * @return the sqxfzj
	 */
	public String getSqxfzj() {
		return sqxfzj;
	}
	/**
	 * @param sqxfzj要设置的 sqxfzj
	 */
	public void setSqxfzj(String sqxfzj) {
		this.sqxfzj = sqxfzj;
	}
	public FormFile getFormfile() {
		return formfile;
	}
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
