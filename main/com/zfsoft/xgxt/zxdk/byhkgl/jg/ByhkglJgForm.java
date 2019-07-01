/**
 * @部门:学工产品事业部
 * @日期：2016-5-11 上午09:58:40 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.jg;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 毕业还款管理
 * @类功能描述: 毕业还款结果 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-11 上午09:58:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ByhkglJgForm extends ActionForm{
	
	private String jgid;
	private String sqid;
	private String xh;
	private String hkje;
	private String sfzq;
	private String zqyy;
	private String zqyymc;
	private String zqqx;
	private String shzt;
	private String splc;
	private String sqsj;
	private String bz;
	private String lclyywid;
	private String sjly;
	//下载相关
	private FormFile formfile;
	private String filepath;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	public String getJgid() {
		return jgid;
	}
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getHkje() {
		return hkje;
	}
	public void setHkje(String hkje) {
		this.hkje = hkje;
	}
	public String getSfzq() {
		return sfzq;
	}
	public void setSfzq(String sfzq) {
		this.sfzq = sfzq;
	}
	public String getZqyy() {
		return zqyy;
	}
	public void setZqyy(String zqyy) {
		this.zqyy = zqyy;
	}
	public String getZqyymc() {
		return zqyymc;
	}
	public void setZqyymc(String zqyymc) {
		this.zqyymc = zqyymc;
	}
	public String getZqqx() {
		return zqqx;
	}
	public void setZqqx(String zqqx) {
		this.zqqx = zqqx;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}	
	public String getLclyywid() {
		return lclyywid;
	}
	public void setLclyywid(String lclyywid) {
		this.lclyywid = lclyywid;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
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
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
