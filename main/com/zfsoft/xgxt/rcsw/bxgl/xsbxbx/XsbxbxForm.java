/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 上午11:30:14 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.xsbxbx;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 保险管理-学生保险报销
 * @作者： 沈晓波 [工号:1123]
 * @时间： 2015-1-26 上午11:30:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsbxbxForm extends ActionForm {
	
	private static final long serialVersionUID = 1966791940187986544L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	
	private String bxid;
	private String xh;
	private String xm;
	private String xb;
	private String nj;
	private String xymc;
	private String zymc;
	private String bjmc;
	private String zlbh;
	private String clzb;
	private String yfjsd;
	private String bxxz;
	private String bxje;
	private String lx;
	private String jbnr;
	private String csfysj;
	private String bxshr;
	private String sjly;
	private String jzyy;
	private String jzsj;
	private String bzqk;
	private String ylzd1;
	
	public String getYlzd1() {
		return ylzd1;
	}
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	//下载相关
	private FormFile formfile;
	private String filepath;
	
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
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
	public String getBxid() {
		return bxid;
	}
	public void setBxid(String bxid) {
		this.bxid = bxid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getZlbh() {
		return zlbh;
	}
	public void setZlbh(String zlbh) {
		this.zlbh = zlbh;
	}
	public String getClzb() {
		return clzb;
	}
	public void setClzb(String clzb) {
		this.clzb = clzb;
	}
	public String getYfjsd() {
		return yfjsd;
	}
	public void setYfjsd(String yfjsd) {
		this.yfjsd = yfjsd;
	}
	public String getBxxz() {
		return bxxz;
	}
	public void setBxxz(String bxxz) {
		this.bxxz = bxxz;
	}
	public String getBxje() {
		return bxje;
	}
	public void setBxje(String bxje) {
		this.bxje = bxje;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getJbnr() {
		return jbnr;
	}
	public void setJbnr(String jbnr) {
		this.jbnr = jbnr;
	}
	public String getCsfysj() {
		return csfysj;
	}
	public void setCsfysj(String csfysj) {
		this.csfysj = csfysj;
	}
	
	public String getBxshr() {
		return bxshr;
	}
	public void setBxshr(String bxshr) {
		this.bxshr = bxshr;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJzyy() {
		return jzyy;
	}
	public void setJzyy(String jzyy) {
		this.jzyy = jzyy;
	}
	public String getJzsj() {
		return jzsj;
	}
	public void setJzsj(String jzsj) {
		this.jzsj = jzsj;
	}
	public String getBzqk() {
		return bzqk;
	}
	public void setBzqk(String bzqk) {
		this.bzqk = bzqk;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	
	
}
