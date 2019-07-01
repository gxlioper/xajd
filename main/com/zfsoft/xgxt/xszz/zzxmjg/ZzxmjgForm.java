/**
 * @部门:学工产品事业部
 * @日期：2013-4-24 下午01:42:50 
 */
package com.zfsoft.xgxt.xszz.zzxmjg;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 资助结果管理模块
 * @类功能描述: form
 * @作者： maxh
 * @时间： 2013-4-24 下午01:42:50
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZzxmjgForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String guid;// guid
	private String xh;// 学号
	private String xn;// 学年
	private String xq;// 学期
	private String xqmc;
	private String nj;//年级
	private String xydm;//学院代码
	private String lbdm;// 类别代码
	private String lbmc;
	private String xmmc;// 项目名称
	private String xmdm;// 项目代码
	private String sjly;// 数据来源
	private String je;// 金额
	private String sqsj;// 申请时间
	private String sqly;// 申请理由
	private String lylcywid;// 数据原来业务id
	private String pdxn; //评定学年
	private String pdxq; //评定学期
	private String pdxqmc;
	private String zsbm;//证书编码
	private String ylzd1 ;//预留字段1 
	private String ylzd2 ;//预留字段2 
	private String ylzd3 ;//预留字段3 
	private String ylzd4 ;//预留字段4 
	private String ylzd5 ;//附件id
	
	private String bjpyjgshzt;
	private String bjpyjgshztmc;
	private String bjpyjgpyhsj;
	private String bjpyjgpyhdd;
	private String bjpyjgpyyj;
	private String bjpyxzcyxms;
	private String bjpyxzdbxms;
	private FormFile drmb;//导入模板文件
	private String fjmc;//附件名称
	private String filepath;//存放错误文件的路径
	private String sfzh;

	//河北民族师范学院个性化
	private String sxxm;//选择的项目名称

	public String getSxxm() {
		return sxxm;
	}

	public void setSxxm(String sxxm) {
		this.sxxm = sxxm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
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

	public String getLbdm() {
		return lbdm;
	}

	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
	}

	public String getLylcywid() {
		return lylcywid;
	}

	public String getPdxn() {
		return pdxn;
	}

	public void setPdxn(String pdxn) {
		this.pdxn = pdxn;
	}

	public String getPdxq() {
		return pdxq;
	}

	public void setPdxq(String pdxq) {
		this.pdxq = pdxq;
	}

	/**
	 * @return the zsbm
	 */
	public String getZsbm() {
		return zsbm;
	}

	/**
	 * @param zsbm要设置的 zsbm
	 */
	public void setZsbm(String zsbm) {
		this.zsbm = zsbm;
	}

	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}

	/**
	 * @param nj要设置的 nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}

	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}

	/**
	 * @param xydm要设置的 xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

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

	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	/**
	 * @return the lbmc
	 */
	public String getLbmc() {
		return lbmc;
	}

	/**
	 * @param lbmc要设置的 lbmc
	 */
	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}

	/**
	 * @return the bjpyjgshzt
	 */
	public String getBjpyjgshzt() {
		return bjpyjgshzt;
	}

	/**
	 * @param bjpyjgshzt要设置的 bjpyjgshzt
	 */
	public void setBjpyjgshzt(String bjpyjgshzt) {
		this.bjpyjgshzt = bjpyjgshzt;
	}

	/**
	 * @return the bjpyjgshztmc
	 */
	public String getBjpyjgshztmc() {
		return bjpyjgshztmc;
	}

	/**
	 * @param bjpyjgshztmc要设置的 bjpyjgshztmc
	 */
	public void setBjpyjgshztmc(String bjpyjgshztmc) {
		this.bjpyjgshztmc = bjpyjgshztmc;
	}

	/**
	 * @return the bjpyjgpyhsj
	 */
	public String getBjpyjgpyhsj() {
		return bjpyjgpyhsj;
	}

	/**
	 * @param bjpyjgpyhsj要设置的 bjpyjgpyhsj
	 */
	public void setBjpyjgpyhsj(String bjpyjgpyhsj) {
		this.bjpyjgpyhsj = bjpyjgpyhsj;
	}

	/**
	 * @return the bjpyjgpyhdd
	 */
	public String getBjpyjgpyhdd() {
		return bjpyjgpyhdd;
	}

	/**
	 * @param bjpyjgpyhdd要设置的 bjpyjgpyhdd
	 */
	public void setBjpyjgpyhdd(String bjpyjgpyhdd) {
		this.bjpyjgpyhdd = bjpyjgpyhdd;
	}

	/**
	 * @return the bjpyjgpyyj
	 */
	public String getBjpyjgpyyj() {
		return bjpyjgpyyj;
	}

	/**
	 * @param bjpyjgpyyj要设置的 bjpyjgpyyj
	 */
	public void setBjpyjgpyyj(String bjpyjgpyyj) {
		this.bjpyjgpyyj = bjpyjgpyyj;
	}

	/**
	 * @return the bjpyxzcyxms
	 */
	public String getBjpyxzcyxms() {
		return bjpyxzcyxms;
	}

	/**
	 * @param bjpyxzcyxms要设置的 bjpyxzcyxms
	 */
	public void setBjpyxzcyxms(String bjpyxzcyxms) {
		this.bjpyxzcyxms = bjpyxzcyxms;
	}

	/**
	 * @return the bjpyxzdbxms
	 */
	public String getBjpyxzdbxms() {
		return bjpyxzdbxms;
	}

	/**
	 * @param bjpyxzdbxms要设置的 bjpyxzdbxms
	 */
	public void setBjpyxzdbxms(String bjpyxzdbxms) {
		this.bjpyxzdbxms = bjpyxzdbxms;
	}

	/**
	 * @return the drmb
	 */
	public FormFile getDrmb() {
		return drmb;
	}

	/**
	 * @param drmb要设置的 drmb
	 */
	public void setDrmb(FormFile drmb) {
		this.drmb = drmb;
	}

	/**
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}

	/**
	 * @param fjmc要设置的 fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
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
	 * @return the sfzh
	 */
	public String getSfzh() {
		return sfzh;
	}

	/**
	 * @param sfzh要设置的 sfzh
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	/**
	 * @return the pdxqmc
	 */
	public String getPdxqmc() {
		return pdxqmc;
	}

	/**
	 * @param pdxqmc要设置的 pdxqmc
	 */
	public void setPdxqmc(String pdxqmc) {
		this.pdxqmc = pdxqmc;
	}
	
	
	
	
}
