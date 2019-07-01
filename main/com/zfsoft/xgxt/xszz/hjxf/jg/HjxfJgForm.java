/**
 * @部门:学工产品事业部
 * @日期：2016-3-15 上午11:50:58 
 */  
package com.zfsoft.xgxt.xszz.hjxf.jg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-3-15 上午11:50:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjxfJgForm extends ActionForm {
	 private String jgid;
	 private String hjxfid;
	 private String xh;
	 private String xn;
	 private String xq;
	 private String dkqk;
	 private String wnqfje;
	 private String yjje;
	 private String hjje;
	 private String jqjzsj;
	 private String sqyy;
	 private String splcid;
	 private String shzt;
	 private String filepath;
	 private String sqsj;
	 private String pkdj;
	 private String sjly;
	 private String czy;
	
	private String  type;
	 private static final long serialVersionUID = 1L;
	 private SearchModel searchModel = new SearchModel();
	//导出
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
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
	
	 /**
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @param jgid要设置的 jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	/**
	 * @return the hjjgid
	 */
	public String getHjxfid() {
		return hjxfid;
	}
	/**
	 * @param hjjgid要设置的 hjjgid
	 */
	public void setHjxfid(String hjxfid) {
		this.hjxfid = hjxfid;
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the dkqk
	 */
	public String getDkqk() {
		return dkqk;
	}
	/**
	 * @param dkqk要设置的 dkqk
	 */
	public void setDkqk(String dkqk) {
		this.dkqk = dkqk;
	}
	/**
	 * @return the wnqfje
	 */
	public String getWnqfje() {
		return wnqfje;
	}
	/**
	 * @param wnqfje要设置的 wnqfje
	 */
	public void setWnqfje(String wnqfje) {
		this.wnqfje = wnqfje;
	}
	/**
	 * @return the yjje
	 */
	public String getYjje() {
		return yjje;
	}
	/**
	 * @param yjje要设置的 yjje
	 */
	public void setYjje(String yjje) {
		this.yjje = yjje;
	}
	/**
	 * @return the hjje
	 */
	public String getHjje() {
		return hjje;
	}
	/**
	 * @param hjje要设置的 hjje
	 */
	public void setHjje(String hjje) {
		this.hjje = hjje;
	}
	/**
	 * @return the jqjzsj
	 */
	public String getJqjzsj() {
		return jqjzsj;
	}
	/**
	 * @param jqjzsj要设置的 jqjzsj
	 */
	public void setJqjzsj(String jqjzsj) {
		this.jqjzsj = jqjzsj;
	}
	/**
	 * @return the sqyy
	 */
	public String getSqyy() {
		return sqyy;
	}
	/**
	 * @param sqyy要设置的 sqyy
	 */
	public void setSqyy(String sqyy) {
		this.sqyy = sqyy;
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
	 * @return the pkdj
	 */
	public String getPkdj() {
		return pkdj;
	}
	/**
	 * @param pkdj要设置的 pkdj
	 */
	public void setPkdj(String pkdj) {
		this.pkdj = pkdj;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	 /**
	 * @return the czy
	 */
	public String getCzy() {
		return czy;
	}
	/**
	 * @param czy要设置的 czy
	 */
	public void setCzy(String czy) {
		this.czy = czy;
	}
}
