package com.zfsoft.xgxt.xsxx.xjyd.xjydsq;

import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.xsxx.xjyd.XjydForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 学籍异动申请
 * @作者： Qilm[工号:964]
 * @时间： 2013-11-28 上午09:40:48 
 * @版本： V5.12.20
 */
public class XjydsqForm extends XjydForm {

	private static final long serialVersionUID = 1L;

	private String shlcidnew; //新审核流程ID
	
	
	private String type;
	/**
	 * 分页用
	 */
	private Pages pages = new Pages();
	/**
	 * 高级查询用
	 */
	private SearchModel searchModel = new SearchModel();
	/**
	 * 导出用
	 */
	private ExportModel exportModel = new ExportModel();
	/**
	 * 学籍异动申请id
	 */
	private String xjydsqid;
	/**
	 * 学号
	 */
	private String xh;
	/**
	 * 申请时间
	 */
	private String sqsj;
	/**
	 * 审批流程id
	 */
	private String splcid;
	/**
	 * 审核状态
	 */
	private String shzt;
	/**
	 * 学年
	 */
	private String xn;
	/**
	 * 学期
	 */
	private String xq;
	/**
	 * 学期名称
	 */
	private String xqmc;
	/**
	 * 异动类别代码
	 */
	private String ydlbdm;
	/**
	 * 异动类别名称
	 */
	private String ydlbmc;
	/**
	 * 申请人/操作人
	 */
	private String sqr;
	/**
	 * 申请理由
	 */
	private String sqly;
	/**
	 * 异动前年级
	 */
	private String ydqnj;
	/**
	 * 异动前学院代码
	 */
	private String ydqxydm;
	/**
	 * 异动前专业代码
	 */
	private String ydqzydm;
	/**
	 * 异动前班级代码
	 */
	private String ydqbjdm;
	/**
	 * 异动前学院名称
	 */
	private String ydqxymc;
	/**
	 * 异动前专业名称
	 */
	private String ydqzymc;
	/**
	 * 异动前班级名称
	 */
	private String ydqbjmc;
	/**
	 * 异动前学籍类别代码
	 */
	private String ydqxjlb;
	/**
	 * 异动前学籍类别名称
	 */
	private String ydqxjlbmc;
	/**
	 * 异动前是否有学籍名称
	 */
	private String ydqsfyxjmc;
	/**
	 * 异动前是否在校名称
	 */
	private String ydqsfzxmc;
	/**
	 * 异动后年级
	 */
	private String ydhnj;
	/**
	 * 异动后学院代码
	 */
	private String ydhxydm;
	/**
	 * 异动后专业代码
	 */
	private String ydhzydm;
	/**
	 * 异动后班级代码
	 */
	private String ydhbjdm;
	/**
	 * 异动后学院名称
	 */
	private String ydhxymc;
	/**
	 * 异动后专业名称
	 */
	private String ydhzymc;
	/**
	 * 异动后班级名称
	 */
	private String ydhbjmc;
	/**
	 * 异动后学籍类别代码
	 */
	private String ydhxjlb;

	/**
	 * 异动后学籍类别名称
	 */
	private String ydhxjlbmc;
	/**
	 * 异动后是否有学籍名称
	 */
	private String ydhsfyxjmc;
	/**
	 * 异动后是否在校名称
	 */
	private String ydhsfzxmc;
	
	/**
	 * 申请开始时间
	 */
	private String sqkssj;
	
	/**
	 * 申请结束时间
	 */
	private String sqjssj;
	
	
	private String ydyydm; // 原因代码
	
	private String ydyymc; // 原因名称
	
	private String lyqxxxdm; // 学校代码
	
	private String xxmc; // 学校名称
	
	private String dqztdm; // 当前状态代码
	
	private String dqztmc; // 当前状态名称
	
	private String xz; // 学制
	
	private String sfsfs; // 是否师范生	
	
	//下载相关
	private FormFile formfile;
	private String filepath;
	
	
	public String getDqztdm() {
		return dqztdm;
	}
	public void setDqztdm(String dqztdm) {
		this.dqztdm = dqztdm;
	}
	public String getDqztmc() {
		return dqztmc;
	}
	public void setDqztmc(String dqztmc) {
		this.dqztmc = dqztmc;
	}
	public String getYdyymc() {
		return ydyymc;
	}
	public void setYdyymc(String ydyymc) {
		this.ydyymc = ydyymc;
	}
	public String getXxmc() {
		return xxmc;
	}
	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}
	public String getYdyydm() {
		return ydyydm;
	}
	public void setYdyydm(String ydyydm) {
		this.ydyydm = ydyydm;
	}
	public String getLyqxxxdm() {
		return lyqxxxdm;
	}
	public void setLyqxxxdm(String lyqxxxdm) {
		this.lyqxxxdm = lyqxxxdm;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getSfsfs() {
		return sfsfs;
	}
	public void setSfsfs(String sfsfs) {
		this.sfsfs = sfsfs;
	}
	public String getSqkssj() {
		return sqkssj;
	}
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	public String getSqjssj() {
		return sqjssj;
	}
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public String getYdlbmc() {
		return ydlbmc;
	}
	public void setYdlbmc(String ydlbmc) {
		this.ydlbmc = ydlbmc;
	}
	public String getYdqxymc() {
		return ydqxymc;
	}
	public void setYdqxymc(String ydqxymc) {
		this.ydqxymc = ydqxymc;
	}
	public String getYdqzymc() {
		return ydqzymc;
	}
	public void setYdqzymc(String ydqzymc) {
		this.ydqzymc = ydqzymc;
	}
	public String getYdqbjmc() {
		return ydqbjmc;
	}
	public void setYdqbjmc(String ydqbjmc) {
		this.ydqbjmc = ydqbjmc;
	}
	public String getYdqxjlbmc() {
		return ydqxjlbmc;
	}
	public void setYdqxjlbmc(String ydqxjlbmc) {
		this.ydqxjlbmc = ydqxjlbmc;
	}
	public String getYdqsfyxjmc() {
		return ydqsfyxjmc;
	}
	public void setYdqsfyxjmc(String ydqsfyxjmc) {
		this.ydqsfyxjmc = ydqsfyxjmc;
	}
	public String getYdqsfzxmc() {
		return ydqsfzxmc;
	}
	public void setYdqsfzxmc(String ydqsfzxmc) {
		this.ydqsfzxmc = ydqsfzxmc;
	}
	public String getYdhxymc() {
		return ydhxymc;
	}
	public void setYdhxymc(String ydhxymc) {
		this.ydhxymc = ydhxymc;
	}
	public String getYdhzymc() {
		return ydhzymc;
	}
	public void setYdhzymc(String ydhzymc) {
		this.ydhzymc = ydhzymc;
	}
	public String getYdhbjmc() {
		return ydhbjmc;
	}
	public void setYdhbjmc(String ydhbjmc) {
		this.ydhbjmc = ydhbjmc;
	}
	public String getYdhxjlbmc() {
		return ydhxjlbmc;
	}
	public void setYdhxjlbmc(String ydhxjlbmc) {
		this.ydhxjlbmc = ydhxjlbmc;
	}
	public String getYdhsfyxjmc() {
		return ydhsfyxjmc;
	}
	public void setYdhsfyxjmc(String ydhsfyxjmc) {
		this.ydhsfyxjmc = ydhsfyxjmc;
	}
	public String getYdhsfzxmc() {
		return ydhsfzxmc;
	}
	public void setYdhsfzxmc(String ydhsfzxmc) {
		this.ydhsfzxmc = ydhsfzxmc;
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
	public String getXjydsqid() {
		return xjydsqid;
	}
	public void setXjydsqid(String xjydsqid) {
		this.xjydsqid = xjydsqid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getSplcid() {
		return splcid;
	}
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
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
	public String getYdlbdm() {
		return ydlbdm;
	}
	public void setYdlbdm(String ydlbdm) {
		this.ydlbdm = ydlbdm;
	}
	public String getSqr() {
		return sqr;
	}
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getYdqnj() {
		return ydqnj;
	}
	public void setYdqnj(String ydqnj) {
		this.ydqnj = ydqnj;
	}
	public String getYdqxydm() {
		return ydqxydm;
	}
	public void setYdqxydm(String ydqxydm) {
		this.ydqxydm = ydqxydm;
	}
	public String getYdqzydm() {
		return ydqzydm;
	}
	public void setYdqzydm(String ydqzydm) {
		this.ydqzydm = ydqzydm;
	}
	public String getYdqbjdm() {
		return ydqbjdm;
	}
	public void setYdqbjdm(String ydqbjdm) {
		this.ydqbjdm = ydqbjdm;
	}
	public String getYdqxjlb() {
		return ydqxjlb;
	}
	public void setYdqxjlb(String ydqxjlb) {
		this.ydqxjlb = ydqxjlb;
	}
	public String getYdhnj() {
		return ydhnj;
	}
	public void setYdhnj(String ydhnj) {
		this.ydhnj = ydhnj;
	}
	public String getYdhxydm() {
		return ydhxydm;
	}
	public void setYdhxydm(String ydhxydm) {
		this.ydhxydm = ydhxydm;
	}
	public String getYdhzydm() {
		return ydhzydm;
	}
	public void setYdhzydm(String ydhzydm) {
		this.ydhzydm = ydhzydm;
	}
	public String getYdhbjdm() {
		return ydhbjdm;
	}
	public void setYdhbjdm(String ydhbjdm) {
		this.ydhbjdm = ydhbjdm;
	}
	public String getYdhxjlb() {
		return ydhxjlb;
	}
	public void setYdhxjlb(String ydhxjlb) {
		this.ydhxjlb = ydhxjlb;
	}
	public String getShlcidnew() {
		return shlcidnew;
	}
	public void setShlcidnew(String shlcidnew) {
		this.shlcidnew = shlcidnew;
	}
	/**
	 * @return the formfile
	 */
	public FormFile getFormfile() {
		return formfile;
	}
	/**
	 * @param formfile要设置的 formfile
	 */
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
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
