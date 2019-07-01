/**
 * @部门: 学工产品1部
 * @日期： 2017-3-13 下午02:00:23 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 新评奖评优_我的评奖_评奖结果
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-3-13 下午02:00:23 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjjgForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	
	private String id;        //GUID
	private String xh;        //学号
	private String xn;        //学年
	private String xmdm;      //项目代码
	private String xmmc;      //项目名称
	private String xmje;      //项目金额
	private String sqsj;      //申请时间
	private String sqly;      //申请理由
	private String sjly;      //数据来源 0:导入、1:申请流、 2:后台增加
	private String lrr;       //录入人
	private String xzdm;      //性质代码
	private String lxdm;      //类型代码
	private String cpnj;      //参评年级
	private String cpxymc;    //参评学院名称
	private String cpzymc;    //参评专业名称
	private String cpbjmc;    //参评班级名称
	private String cpxydm;    //参评学院代码
	private String cpzydm;    //参评专业代码
	private String cpbjdm;    //参评班级代码
	private String ylzd1;     //预留字段一
	private String ylzd2;     //预留字段二
	private String ylzd3;     //预留字段三
	private String ylzd4;     //预留字段四
	private String ylzd5;     //附件ID
	private String lrsj;      //录入时间
	private String lylcywid;  // 来源业务id
	private String bjdw;      // 颁奖单位
	
	private String wysp;/*外语水平*/
	private String ssdh;/*宿舍电话*/
	private String gzzw;/*担任社会工作职务*/
	private String cjkyqk;/*参加科研情况*/
	private String dwrs;/*对设奖单位的认识*/
	private String grxxjl;	//个人学习经历
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
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc要设置的 xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the xmje
	 */
	public String getXmje() {
		return xmje;
	}
	/**
	 * @param xmje要设置的 xmje
	 */
	public void setXmje(String xmje) {
		this.xmje = xmje;
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
	 * @return the lrr
	 */
	public String getLrr() {
		return lrr;
	}
	/**
	 * @param lrr要设置的 lrr
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	/**
	 * @return the xzdm
	 */
	public String getXzdm() {
		return xzdm;
	}
	/**
	 * @param xzdm要设置的 xzdm
	 */
	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}
	/**
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}
	/**
	 * @param lxdm要设置的 lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	/**
	 * @return the cpnj
	 */
	public String getCpnj() {
		return cpnj;
	}
	/**
	 * @param cpnj要设置的 cpnj
	 */
	public void setCpnj(String cpnj) {
		this.cpnj = cpnj;
	}
	/**
	 * @return the cpxymc
	 */
	public String getCpxymc() {
		return cpxymc;
	}
	/**
	 * @param cpxymc要设置的 cpxymc
	 */
	public void setCpxymc(String cpxymc) {
		this.cpxymc = cpxymc;
	}
	/**
	 * @return the cpzymc
	 */
	public String getCpzymc() {
		return cpzymc;
	}
	/**
	 * @param cpzymc要设置的 cpzymc
	 */
	public void setCpzymc(String cpzymc) {
		this.cpzymc = cpzymc;
	}
	/**
	 * @return the cpbjmc
	 */
	public String getCpbjmc() {
		return cpbjmc;
	}
	/**
	 * @param cpbjmc要设置的 cpbjmc
	 */
	public void setCpbjmc(String cpbjmc) {
		this.cpbjmc = cpbjmc;
	}
	/**
	 * @return the cpxydm
	 */
	public String getCpxydm() {
		return cpxydm;
	}
	/**
	 * @param cpxydm要设置的 cpxydm
	 */
	public void setCpxydm(String cpxydm) {
		this.cpxydm = cpxydm;
	}
	/**
	 * @return the cpzydm
	 */
	public String getCpzydm() {
		return cpzydm;
	}
	/**
	 * @param cpzydm要设置的 cpzydm
	 */
	public void setCpzydm(String cpzydm) {
		this.cpzydm = cpzydm;
	}
	/**
	 * @return the cpbjdm
	 */
	public String getCpbjdm() {
		return cpbjdm;
	}
	/**
	 * @param cpbjdm要设置的 cpbjdm
	 */
	public void setCpbjdm(String cpbjdm) {
		this.cpbjdm = cpbjdm;
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
	/**
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @param lrsj要设置的 lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	/**
	 * @return the lylcywid
	 */
	public String getLylcywid() {
		return lylcywid;
	}
	/**
	 * @param lylcywid要设置的 lylcywid
	 */
	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
	}
	/**
	 * @return the bjdw
	 */
	public String getBjdw() {
		return bjdw;
	}
	/**
	 * @param bjdw要设置的 bjdw
	 */
	public void setBjdw(String bjdw) {
		this.bjdw = bjdw;
	}
	/**
	 * @return the wysp
	 */
	public String getWysp() {
		return wysp;
	}
	/**
	 * @param wysp要设置的 wysp
	 */
	public void setWysp(String wysp) {
		this.wysp = wysp;
	}
	/**
	 * @return the ssdh
	 */
	public String getSsdh() {
		return ssdh;
	}
	/**
	 * @param ssdh要设置的 ssdh
	 */
	public void setSsdh(String ssdh) {
		this.ssdh = ssdh;
	}
	/**
	 * @return the gzzw
	 */
	public String getGzzw() {
		return gzzw;
	}
	/**
	 * @param gzzw要设置的 gzzw
	 */
	public void setGzzw(String gzzw) {
		this.gzzw = gzzw;
	}
	/**
	 * @return the cjkyqk
	 */
	public String getCjkyqk() {
		return cjkyqk;
	}
	/**
	 * @param cjkyqk要设置的 cjkyqk
	 */
	public void setCjkyqk(String cjkyqk) {
		this.cjkyqk = cjkyqk;
	}
	/**
	 * @return the dwrs
	 */
	public String getDwrs() {
		return dwrs;
	}
	/**
	 * @param dwrs要设置的 dwrs
	 */
	public void setDwrs(String dwrs) {
		this.dwrs = dwrs;
	}
	/**
	 * @return the grxxjl
	 */
	public String getGrxxjl() {
		return grxxjl;
	}
	/**
	 * @param grxxjl要设置的 grxxjl
	 */
	public void setGrxxjl(String grxxjl) {
		this.grxxjl = grxxjl;
	}
}
