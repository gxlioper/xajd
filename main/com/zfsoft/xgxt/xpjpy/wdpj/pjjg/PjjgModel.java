/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 上午10:32:49 
 */
package com.zfsoft.xgxt.xpjpy.wdpj.pjjg;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 我的评奖-评奖结果
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-30 上午10:32:49
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class PjjgModel extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String id;// ID
	private String xh;// 学号
	private String xn;// 学年
	private String xq;// 学期
	private String xqmc; // 学期名称
	private String xmdm;// 项目代码
	private String xmmc;// 项目名称
	private String xmje;// 项目金额
	private String sqsj;// 申请时间
	private String sqly;// 申请理由
	private String ylzd1;// 预留字段一
	private String ylzd2;// 预留字段二
	private String ylzd3;// 预留字段三
	private String ylzd4;// 预留字段四
	private String ylzd5;// 附件id
	private String sjly;// 数据来源
	private String lylcywid;// 来源业务id

	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private String lxdm;// 项目类型
	private String xzdm;// 项目性质

	private String cpnj; // 参评年级
	private String cpxydm; // 参评学院代码
	private String cpxymc; // 参评学院名称
	private String cpzydm; // 参评专业代码
	private String cpzymc; // 参评专业名称
	private String cpbjdm; // 参评班级代码
	private String cpbjmc; // 参评班级名称

	private String lxdmmc;// 项目类型名称
	
	private String bjdw;// 颁奖单位
	private String djjl;//曾受何种奖励

	private String sqr;	//申请人

	private String xydm;	//学院代码
	private String sqlyyy;

	public String getSqlyyy() {
		return sqlyyy;
	}

	public void setSqlyyy(String sqlyyy) {
		this.sqlyyy = sqlyyy;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getDjjl() {
		return djjl;
	}

	public void setDjjl(String djjl) {
		this.djjl = djjl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXmje() {
		return xmje;
	}

	public void setXmje(String xmje) {
		this.xmje = xmje;
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

	public String getYlzd1() {
		return ylzd1;
	}

	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}

	public String getYlzd2() {
		return ylzd2;
	}

	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}

	public String getYlzd3() {
		return ylzd3;
	}

	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}

	public String getYlzd4() {
		return ylzd4;
	}

	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}

	public String getYlzd5() {
		return ylzd5;
	}

	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getLxdm() {
		return lxdm;
	}

	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}

	public String getXzdm() {
		return xzdm;
	}

	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}

	public String getCpnj() {
		return cpnj;
	}

	public void setCpnj(String cpnj) {
		this.cpnj = cpnj;
	}

	public String getCpxydm() {
		return cpxydm;
	}

	public void setCpxydm(String cpxydm) {
		this.cpxydm = cpxydm;
	}

	public String getCpxymc() {
		return cpxymc;
	}

	public void setCpxymc(String cpxymc) {
		this.cpxymc = cpxymc;
	}

	public String getCpzydm() {
		return cpzydm;
	}

	public void setCpzydm(String cpzydm) {
		this.cpzydm = cpzydm;
	}

	public String getCpzymc() {
		return cpzymc;
	}

	public void setCpzymc(String cpzymc) {
		this.cpzymc = cpzymc;
	}

	public String getCpbjdm() {
		return cpbjdm;
	}

	public void setCpbjdm(String cpbjdm) {
		this.cpbjdm = cpbjdm;
	}

	public String getCpbjmc() {
		return cpbjmc;
	}

	public void setCpbjmc(String cpbjmc) {
		this.cpbjmc = cpbjmc;
	}

	public String getLxdmmc() {
		return lxdmmc;
	}

	public void setLxdmmc(String lxdmmc) {
		this.lxdmmc = lxdmmc;
	}

	public String getBjdw() {
		return bjdw;
	}

	public void setBjdw(String bjdw) {
		this.bjdw = bjdw;
	}

	public String getLylcywid() {
		return lylcywid;
	}

	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
	}

	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
}
