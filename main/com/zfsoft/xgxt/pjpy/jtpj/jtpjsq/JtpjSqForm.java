package com.zfsoft.xgxt.pjpy.jtpj.jtpjsq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-4-28 上午10:53:23
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JtpjSqForm extends ActionForm {
	private String sqid;// varchar2(32) n 'sys_guid()' 申请id
	private String jxid;// varchar2(32) y 'sys_guid()' 奖项id
	private String sqxn;// varchar2(20) y 申请学年
	private String sqxq;// varchar2(20) y 申请学期
	private String jtpjdw;// varchar2(10) y 集体评奖单位(学院：xy;班级：bj;其他：qt)
	private String pjjtid;// varchar2(20) y 评奖集体id
	private String pjjtmc;// varchar2(20) y 评奖集体名称
	private String jtjj;// varchar2(1000) y 集体简介
	private String rs;// varchar2(20) y 人数
	private String sqrylx;// varchar2(2) y 申请人员类型(stu：学生；tea：教师)
	private String sqr;// varchar2(20) y 申请人(学号/职工号)
	private String sqly;// varchar2(1000) y 申请理由
	private String shzt;// varchar2(2) y 审核状态
	private String splcid;// varchar2(32) y 审批流程id
	private String sqsj;// varchar2(20) y 申请时间

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private String jxmc;
	private String bjmc;
	private String rdfs;
	private String filepath;
	
	private String lddm;
	private String ch;
	private String qsh;
	
	
	
	/**
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}

	/**
	 * @param lddm要设置的 lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	/**
	 * @return the ch
	 */
	public String getCh() {
		return ch;
	}

	/**
	 * @param ch要设置的 ch
	 */
	public void setCh(String ch) {
		this.ch = ch;
	}

	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}

	/**
	 * @param qsh要设置的 qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}

	/**
	 * @param bjmc要设置的 bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}

	/**
	 * @param sqid要设置的
	 *            sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	/**
	 * @return the jxid
	 */
	public String getJxid() {
		return jxid;
	}

	/**
	 * @param jxid要设置的
	 *            jxid
	 */
	public void setJxid(String jxid) {
		this.jxid = jxid;
	}

	/**
	 * @return the sqxn
	 */
	public String getSqxn() {
		return sqxn;
	}

	/**
	 * @param sqxn要设置的
	 *            sqxn
	 */
	public void setSqxn(String sqxn) {
		this.sqxn = sqxn;
	}

	/**
	 * @return the sqxq
	 */
	public String getSqxq() {
		return sqxq;
	}

	/**
	 * @param sqxq要设置的
	 *            sqxq
	 */
	public void setSqxq(String sqxq) {
		this.sqxq = sqxq;
	}

	/**
	 * @return the jtpjdw
	 */
	public String getJtpjdw() {
		return jtpjdw;
	}

	/**
	 * @param jtpjdw要设置的
	 *            jtpjdw
	 */
	public void setJtpjdw(String jtpjdw) {
		this.jtpjdw = jtpjdw;
	}

	/**
	 * @return the pjjtid
	 */
	public String getPjjtid() {
		return pjjtid;
	}

	/**
	 * @param pjjtid要设置的
	 *            pjjtid
	 */
	public void setPjjtid(String pjjtid) {
		this.pjjtid = pjjtid;
	}

	/**
	 * @return the pjjtmc
	 */
	public String getPjjtmc() {
		return pjjtmc;
	}

	/**
	 * @param pjjtmc要设置的
	 *            pjjtmc
	 */
	public void setPjjtmc(String pjjtmc) {
		this.pjjtmc = pjjtmc;
	}

	/**
	 * @return the jtjj
	 */
	public String getJtjj() {
		return jtjj;
	}

	/**
	 * @param jtjj要设置的
	 *            jtjj
	 */
	public void setJtjj(String jtjj) {
		this.jtjj = jtjj;
	}

	/**
	 * @return the rs
	 */
	public String getRs() {
		return rs;
	}

	/**
	 * @param rs要设置的
	 *            rs
	 */
	public void setRs(String rs) {
		this.rs = rs;
	}

	/**
	 * @return the sqrylx
	 */
	public String getSqrylx() {
		return sqrylx;
	}

	/**
	 * @param sqrylx要设置的
	 *            sqrylx
	 */
	public void setSqrylx(String sqrylx) {
		this.sqrylx = sqrylx;
	}

	/**
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}

	/**
	 * @param sqr要设置的
	 *            sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}

	/**
	 * @param sqly要设置的
	 *            sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}

	/**
	 * @param shzt要设置的
	 *            shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}

	/**
	 * @param splcid要设置的
	 *            splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}

	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}

	/**
	 * @param sqsj要设置的
	 *            sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * @param pages要设置的
	 *            pages
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
	 * @param searchModel要设置的
	 *            searchModel
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
	 * @param type要设置的
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the jxmc
	 */
	public String getJxmc() {
		return jxmc;
	}

	/**
	 * @param jxmc要设置的 jxmc
	 */
	public void setJxmc(String jxmc) {
		this.jxmc = jxmc;
	}

	/**
	 * @return the rdfs
	 */
	public String getRdfs() {
		return rdfs;
	}

	/**
	 * @param rdfs要设置的 rdfs
	 */
	public void setRdfs(String rdfs) {
		this.rdfs = rdfs;
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
