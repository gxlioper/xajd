/**
 * @部门:学工产品事业部
 * @日期：2016-8-1 上午08:56:54 
 */  
package com.zfsoft.xgxt.xsztz.jdwhsz;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-8-1 上午08:56:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JdwhSzForm extends ActionForm {
	 private String  jdwhid;//阶段维护ID
	 private String  xmdm;//项目代码
	 private String  jdid;//阶段ID
	 private String  jdcy;//阶段成员,个人项目这里存的是学号，团体项目这里存的是团队ID
	 private String  jbf;//级别分
	 private String  hdsc;//活动时长
	 private String  bz;//备注
	 private String  jdsj;//阶段时间
	 private String  xhs;//字符串拼接学号
	 private String  xh;
	 private FormFile file;
	 private String filepath;
	 private String[]  jdcys;//阶段成员数组
	 private String  ylzd1;
	 private String  ylzd2;
	 private String  ylzd3;
	 private String  ylzd4;
	 private String  ylzd5;
	 private String  sjly1;
	 private String  lylcywid1;
	 private SearchModel searchModel = new SearchModel();
	 private static final long serialVersionUID = 1L;
	 private String type;
     private ExportModel exportModel = new ExportModel();
     private Pages pages = new Pages();
	 /**
	 * @return the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * @param ylzd1要设置的 ylzd1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * @return the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * @param ylzd2要设置的 ylzd2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * @param ylzd3要设置的 ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * @return the ylzd4
	 */
	public String getYlzd4() {
		return ylzd4;
	}
	/**
	 * @param ylzd4要设置的 ylzd4
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	/**
	 * @return the ylzd5
	 */
	public String getYlzd5() {
		return ylzd5;
	}
	/**
	 * @param ylzd5要设置的 ylzd5
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}
	/**
	 * @return the sjly1
	 */
	public String getSjly1() {
		return sjly1;
	}
	/**
	 * @param sjly1要设置的 sjly1
	 */
	public void setSjly1(String sjly1) {
		this.sjly1 = sjly1;
	}
	/**
	 * @return the lylcywid1
	 */
	public String getLylcywid1() {
		return lylcywid1;
	}
	/**
	 * @param lylcywid1要设置的 lylcywid1
	 */
	public void setLylcywid1(String lylcywid1) {
		this.lylcywid1 = lylcywid1;
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
	 * @return the file
	 */
	public FormFile getFile() {
		return file;
	}
	/**
	 * @param file要设置的 file
	 */
	public void setFile(FormFile file) {
		this.file = file;
	}
	
	 /**
	 * @return the jdcys
	 */
	public String[] getJdcys() {
		return jdcys;
	}
	/**
	 * @param jdcys要设置的 jdcys
	 */
	public void setJdcys(String[] jdcys) {
		this.jdcys = jdcys;
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
	 * @return the xhs
	 */
	public String getXhs() {
		return xhs;
	}
	/**
	 * @param xhs要设置的 xhs
	 */
	public void setXhs(String xhs) {
		this.xhs = xhs;
	}
  
     /**
	 * @return the jdwhid
	 */
	public String getJdwhid() {
		return jdwhid;
	}
	/**
	 * @param jdwhid要设置的 jdwhid
	 */
	public void setJdwhid(String jdwhid) {
		this.jdwhid = jdwhid;
	}
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdm要设置的 xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	/**
	 * @return the jdid
	 */
	public String getJdid() {
		return jdid;
	}
	/**
	 * @param jdid要设置的 jdid
	 */
	public void setJdid(String jdid) {
		this.jdid = jdid;
	}
	/**
	 * @return the jdcy
	 */
	public String getJdcy() {
		return jdcy;
	}
	/**
	 * @param jdcy要设置的 jdcy
	 */
	public void setJdcy(String jdcy) {
		this.jdcy = jdcy;
	}
	/**
	 * @return the jbf
	 */
	public String getJbf() {
		return jbf;
	}
	/**
	 * @param jbf要设置的 jbf
	 */
	public void setJbf(String jbf) {
		this.jbf = jbf;
	}
	/**
	 * @return the hdsc
	 */
	public String getHdsc() {
		return hdsc;
	}
	/**
	 * @param hdsc要设置的 hdsc
	 */
	public void setHdsc(String hdsc) {
		this.hdsc = hdsc;
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
	 * @return the jdsj
	 */
	public String getJdsj() {
		return jdsj;
	}
	/**
	 * @param jdsj要设置的 jdsj
	 */
	public void setJdsj(String jdsj) {
		this.jdsj = jdsj;
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
	
}
