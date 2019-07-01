/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午02:37:36 
 */
package com.zfsoft.xgxt.xpjpy.xmsz.xmwh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 项目维护
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-30 下午02:37:36
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XmwhModel extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	private String xmdm;// 项目代码
	private String lxdm;// 类型代码
	private String xzdm;// 性质代码
	private String xmmc;// 项目名称
	private String xmywmc;//项目英文名称
	private String sfqy;// 是否启用
	private String xmje;// 项目金额
	private String shlc;// 审核流程
	private String rskzjb;// 人数控制级别
	private String jdkzjb;// 兼得控制级别
	private String rsfpfs;// 人数分配方式
	private String rsfpz;// 人数分配名额或比例
	private String rsfpnj;// 人数分配年级
	private String zcfpm;// 综测分排名方式
	private String xmsm;// 项目说明
	private String sqkssj;// 申请开始时间
	private String sqjssj;// 申请结束时间
	private String sqkg;// 申请开关
	private String shkssj;// 审核开始时间
	private String shjssj;// 审核结束时间
	private String shkg;// 审核开关
	private String kgbz;// 开关备注
	private String xn;// 学年
	private String xq;// 学期
	private String xsxh;// 显示顺序
	private String czfs; //操作方式
	private String dysbbb;//对应上报报表
	private String xxtbsj;//学校填表时间
	private String sfkfhjqkxz;//是否需要开放学生主要获奖情况选择
	private String sfyxgb;//是否优秀学生干部
	private String xmxz;//项目性质
	private String pycc;//培养层次


	public String getPycc() {
		return pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	public String getXmxz() {
		return xmxz;
	}

	public void setXmxz(String xmxz) {
		this.xmxz = xmxz;
	}

	/**
	 * 张小彬【工号1036】 新增登记表设置
	 */

	private String dybb;// 对应报表

	public XmwhModel() {
		super();
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
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

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	public String getXmje() {
		return xmje;
	}

	public void setXmje(String xmje) {
		this.xmje = xmje;
	}

	public String getShlc() {
		return shlc;
	}

	public void setShlc(String shlc) {
		this.shlc = shlc;
	}

	public String getRskzjb() {
		return rskzjb;
	}

	public void setRskzjb(String rskzjb) {
		this.rskzjb = rskzjb;
	}

	public String getJdkzjb() {
		return jdkzjb;
	}

	public void setJdkzjb(String jdkzjb) {
		this.jdkzjb = jdkzjb;
	}

	public String getRsfpfs() {
		return rsfpfs;
	}

	public void setRsfpfs(String rsfpfs) {
		this.rsfpfs = rsfpfs;
	}

	public String getRsfpz() {
		return rsfpz;
	}

	public void setRsfpz(String rsfpz) {
		this.rsfpz = rsfpz;
	}

	public String getRsfpnj() {
		return rsfpnj;
	}

	public void setRsfpnj(String rsfpnj) {
		this.rsfpnj = rsfpnj;
	}

	public String getZcfpm() {
		return zcfpm;
	}

	public void setZcfpm(String zcfpm) {
		this.zcfpm = zcfpm;
	}

	public String getXmsm() {
		return xmsm;
	}

	public void setXmsm(String xmsm) {
		this.xmsm = xmsm;
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

	public String getSqkg() {
		return sqkg;
	}

	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
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

	public String getShkg() {
		return shkg;
	}

	public void setShkg(String shkg) {
		this.shkg = shkg;
	}

	public String getKgbz() {
		return kgbz;
	}

	public void setKgbz(String kgbz) {
		this.kgbz = kgbz;
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

	/**
	 * @return the dybb
	 */
	public String getDybb() {
		return dybb;
	}

	/**
	 * @param dybb要设置的
	 *            dybb
	 */
	public void setDybb(String dybb) {
		this.dybb = dybb;
	}

	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}

	/**
	 * @return the xmywmc
	 */
	public String getXmywmc() {
		return xmywmc;
	}

	/**
	 * @param xmywmc要设置的 xmywmc
	 */
	public void setXmywmc(String xmywmc) {
		this.xmywmc = xmywmc;
	}

	/**
	 * @return the czfs
	 */
	public String getCzfs() {
		return czfs;
	}

	/**
	 * @param czfs要设置的 czfs
	 */
	public void setCzfs(String czfs) {
		this.czfs = czfs;
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
	 * @return the dysbbb
	 */
	public String getDysbbb() {
		return dysbbb;
	}

	/**
	 * @param dysbbb要设置的 dysbbb
	 */
	public void setDysbbb(String dysbbb) {
		this.dysbbb = dysbbb;
	}

	/**
	 * @return the xxtbsj
	 */
	public String getXxtbsj() {
		return xxtbsj;
	}

	/**
	 * @param xxtbsj要设置的 xxtbsj
	 */
	public void setXxtbsj(String xxtbsj) {
		this.xxtbsj = xxtbsj;
	}

	/**
	 * @return the sfkfhjqkxz
	 */
	public String getSfkfhjqkxz() {
		return sfkfhjqkxz;
	}

	/**
	 * @param sfkfhjqkxz要设置的 sfkfhjqkxz
	 */
	public void setSfkfhjqkxz(String sfkfhjqkxz) {
		this.sfkfhjqkxz = sfkfhjqkxz;
	}

	public String getSfyxgb() {
		return sfyxgb;
	}

	public void setSfyxgb(String sfyxgb) {
		this.sfyxgb = sfyxgb;
	}
	
}
