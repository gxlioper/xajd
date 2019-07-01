/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.xmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 资助项目代码bean
 * @类功能描述:
 * @作者： ligl
 * @时间： 2013-4-18 下午06:33:00
 * @版本： V1.0
 * @修改记录:
 */
public class XmwhForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	private String xmdm;// 项目代码
	private String xmmc;// 项目名称
	private String lbdm;// 类别代码
	private String je;// 金额
	private String dybb;// 对应报表
	private String dysbbb;// 对应上报报表
	private String sqkg;// 申请开关
	private String sqxn;// 申请学年
	private String sqxq;// 申请学期
	private String sqkssj;// 申请开始时间
	private String sqjssj;// 申请结束时间
	private String splc;// 审批流程
	private String sfkns;// 是否困难生才能申请
	private String knsbdxn;// 困难生绑定学年
	private String knsbdxq;// 困难生绑定学期
	private String knsbddc;// 困难生绑定档次
	private String tjkg;// 条件控制开关
	private String tjkzjb;// 条件控制级别
	private String rskg;// 人数控制开关
	private String rskzjb;// 人数控制级别
	private String jdkg;// 兼得控制开关
	private String jdkzjb;// 兼得控制级别
	private String rskzfw;// 人数控制范围
	private String zme;// 总名额
	private String rskznj;// 人数控制年级
	private String jesfxssq;// 金额是否学生申请
	private String sqzqdm;// 申请周期代码
	private String zqdm;// 周期代码
	private String zqmc;// 周期名称
	private String pdxn; // 评定学年
	private String pdxq; // 评定学期
	private String pdxqmc; // 评定学期名称
	private String ywmc;
	private String jekzfw;
	private String jgsqzq;	//间隔申请周期
	private String xslb;
	private String xmsm; //项目说明
	private String pycc;//培养层次
	
	//重庆三峡医高专个性化审核开关，审核开始时间，审核结束时间
	private String shkg;
	private String shkssj;
	private String shjssj;

	public String getPycc() {
		return pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	public String getShkg() {
		return shkg;
	}

	public void setShkg(String shkg) {
		this.shkg = shkg;
	}

	public String getShkssj() {
		return shkssj;
	}

	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}

	public String getShjssj() {
		return shjssj;
	}

	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}

	public String getXmsm() {
		return xmsm;
	}

	public void setXmsm(String xmsm) {
		this.xmsm = xmsm;
	}

	public XmwhForm() {
		super();
	}

	public String getZme() {
		return zme;
	}

	public void setZme(String zme) {
		this.zme = zme;
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

	public String getLbdm() {
		return lbdm;
	}

	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getDybb() {
		return dybb;
	}

	public void setDybb(String dybb) {
		this.dybb = dybb;
	}

	public String getDysbbb() {
		return dysbbb;
	}

	public void setDysbbb(String dysbbb) {
		this.dysbbb = dysbbb;
	}

	public String getSqkg() {
		return sqkg;
	}

	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}

	public String getSqxn() {
		return sqxn;
	}

	public void setSqxn(String sqxn) {
		this.sqxn = sqxn;
	}

	public String getSqxq() {
		return sqxq;
	}

	public void setSqxq(String sqxq) {
		this.sqxq = sqxq;
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

	public String getSplc() {
		return splc;
	}

	public void setSplc(String splc) {
		this.splc = splc;
	}

	public String getSfkns() {
		return sfkns;
	}

	public void setSfkns(String sfkns) {
		this.sfkns = sfkns;
	}

	public String getKnsbdxn() {
		return knsbdxn;
	}

	public void setKnsbdxn(String knsbdxn) {
		this.knsbdxn = knsbdxn;
	}

	public String getKnsbdxq() {
		return knsbdxq;
	}

	public void setKnsbdxq(String knsbdxq) {
		this.knsbdxq = knsbdxq;
	}

	public String getKnsbddc() {
		return knsbddc;
	}

	public void setKnsbddc(String knsbddc) {
		this.knsbddc = knsbddc;
	}

	public String getTjkg() {
		return tjkg;
	}

	public void setTjkg(String tjkg) {
		this.tjkg = tjkg;
	}

	public String getTjkzjb() {
		return tjkzjb;
	}

	public void setTjkzjb(String tjkzjb) {
		this.tjkzjb = tjkzjb;
	}

	public String getRskg() {
		return rskg;
	}

	public void setRskg(String rskg) {
		this.rskg = rskg;
	}

	public String getRskzjb() {
		return rskzjb;
	}

	public void setRskzjb(String rskzjb) {
		this.rskzjb = rskzjb;
	}

	public String getJdkg() {
		return jdkg;
	}

	public void setJdkg(String jdkg) {
		this.jdkg = jdkg;
	}

	public String getJdkzjb() {
		return jdkzjb;
	}

	public void setJdkzjb(String jdkzjb) {
		this.jdkzjb = jdkzjb;
	}

	public String getRskzfw() {
		return rskzfw;
	}

	public void setRskzfw(String rskzfw) {
		this.rskzfw = rskzfw;
	}

	public String getRskznj() {
		return rskznj;
	}

	public void setRskznj(String rskznj) {
		this.rskznj = rskznj;
	}

	public void setJesfxssq(String jesfxssq) {
		this.jesfxssq = jesfxssq;
	}

	public String getJesfxssq() {
		return jesfxssq;
	}

	public String getSqzqdm() {
		return sqzqdm;
	}

	public void setSqzqdm(String sqzqdm) {
		this.sqzqdm = sqzqdm;
	}

	public String getZqdm() {
		return zqdm;
	}

	public void setZqdm(String zqdm) {
		this.zqdm = zqdm;
	}

	public String getZqmc() {
		return zqmc;
	}

	public void setZqmc(String zqmc) {
		this.zqmc = zqmc;
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

	public String getPdxqmc() {
		return pdxqmc;
	}

	public void setPdxqmc(String pdxqmc) {
		this.pdxqmc = pdxqmc;
	}

	public String getYwmc() {
		return ywmc;
	}

	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}

	public String getJekzfw() {
		return jekzfw;
	}

	public void setJekzfw(String jekzfw) {
		this.jekzfw = jekzfw;
	}

	/**
	 * @return the jgsqzq
	 */
	public String getJgsqzq() {
		return jgsqzq;
	}

	/**
	 * @param jgsqzq要设置的 jgsqzq
	 */
	public void setJgsqzq(String jgsqzq) {
		this.jgsqzq = jgsqzq;
	}

	/**
	 * @return the xslb
	 */
	public String getXslb() {
		return xslb;
	}

	/**
	 * @param xslb要设置的 xslb
	 */
	public void setXslb(String xslb) {
		this.xslb = xslb;
	}
	

}
