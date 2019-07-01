/**
 * @部门:学工产品事业部
 * @日期：2016-6-1 上午11:06:37 
 */  
package com.zfsoft.xgxt.qgzx.xsgwnew.sq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学
 * @类功能描述: 学生岗位申请 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-6-1 上午11:06:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsgwsqForm extends ActionForm {
	/** 
	 * @变量 serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String sqbh;//申请编号
	
	private String xh;//学号
	
	private String xm;
	
	private String xn;//学年
	
	private String yrdwmc;//用人单位名称
	private String yrdwdm;
	
	private String gwdm;//岗位代码
	
	private String gwmc;//岗位名称
	
	private String gwxzmc;//岗位性质名称
	
	private String xqrs;//需求人数
	
	private String zgrs;//在岗人数
	
	private String knss;//困难生数
	
	private String sqsj;//申请时间
	
	private String shzt;//审核状态
	
	private String gwms;//岗位描述
	
	private String splc;//审批流程
	
	private String sqly;//申请理由
	
	private String bz;//备注
	
	private String gwryyq;//岗位人员要求
	
	private String gwyqryxg;//岗位人员要求
	
	private String shgw;

	private String gwcjsx;//岗位酬金上限
	
	private String mxrq; //明细日期
	
	private String sffcap; //是否服从安排
	
	private String sfzqscy; // 是否自强社成员
	private String sfsgwsqsxzmc; // 是否受岗位申请数限制
	
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
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getSqbh() {
		return sqbh;
	}
	public void setSqbh(String sqbh) {
		this.sqbh = sqbh;
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
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getYrdwmc() {
		return yrdwmc;
	}
	public void setYrdwmc(String yrdwmc) {
		this.yrdwmc = yrdwmc;
	}
	public String getYrdwdm() {
		return yrdwdm;
	}
	public void setYrdwdm(String yrdwdm) {
		this.yrdwdm = yrdwdm;
	}
	public String getGwdm() {
		return gwdm;
	}
	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}
	public String getGwmc() {
		return gwmc;
	}
	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}
	public String getGwxzmc() {
		return gwxzmc;
	}
	public void setGwxzmc(String gwxzmc) {
		this.gwxzmc = gwxzmc;
	}
	public String getXqrs() {
		return xqrs;
	}
	public void setXqrs(String xqrs) {
		this.xqrs = xqrs;
	}
	public String getZgrs() {
		return zgrs;
	}
	public void setZgrs(String zgrs) {
		this.zgrs = zgrs;
	}
	public String getKnss() {
		return knss;
	}
	public void setKnss(String knss) {
		this.knss = knss;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getGwms() {
		return gwms;
	}
	public void setGwms(String gwms) {
		this.gwms = gwms;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getGwryyq() {
		return gwryyq;
	}
	public void setGwryyq(String gwryyq) {
		this.gwryyq = gwryyq;
	}
	public String getGwyqryxg() {
		return gwyqryxg;
	}
	public void setGwyqryxg(String gwyqryxg) {
		this.gwyqryxg = gwyqryxg;
	}
	public String getShgw() {
		return shgw;
	}
	public void setShgw(String shgw) {
		this.shgw = shgw;
	}
	public String getGwcjsx() {
		return gwcjsx;
	}
	public void setGwcjsx(String gwcjsx) {
		this.gwcjsx = gwcjsx;
	}
	public String getMxrq() {
		return mxrq;
	}
	public void setMxrq(String mxrq) {
		this.mxrq = mxrq;
	}
	public String getSffcap() {
		return sffcap;
	}
	public void setSffcap(String sffcap) {
		this.sffcap = sffcap;
	}
	public String getSfzqscy() {
		return sfzqscy;
	}
	public void setSfzqscy(String sfzqscy) {
		this.sfzqscy = sfzqscy;
	}
	public String getSfsgwsqsxzmc() {
		return sfsgwsqsxzmc;
	}
	public void setSfsgwsqsxzmc(String sfsgwsqsxzmc) {
		this.sfsgwsqsxzmc = sfsgwsqsxzmc;
	}
	
}
