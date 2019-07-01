/**
 * @部门:学工产品事业部
 * @日期：2015-2-12 下午03:53:50 
 */  
package com.zfsoft.xgxt.xszy.qsppgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-2-12 下午03:53:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XszyQsppForm extends ActionForm{
	
	private String id;
	private String lddm;
	private String ldmc;
	private String qsh;
	private String nj;
	private String xydm;//学园代码
	private String xymc;//学园代码
	private String ssyxdm;
	private String ssyxmc;
	private String qsxb;
	private String dl;//大类
	private String rzrs;//入住人数
	private String sfhhqs;
	private String fpzt;
	private String czsj;
	private String fpczr;
	private String zgh;
	private String xm;
	private String xb;
	private String zwzc;//职务职称
	private String dwdm; //单位
	private String lxdh;//联系电话
	private String zzmmdm;//政治面貌
	private String dzyx;//电子邮箱
	private String dlyq;//大类要求
	private String bz;//
	private String kyxbj;//跨院系标记
	private String bjyx;//标记院系
	private String czr;
	private String type;
	
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getSsyxdm() {
		return ssyxdm;
	}
	public void setSsyxdm(String ssyxdm) {
		this.ssyxdm = ssyxdm;
	}
	public String getSsyxmc() {
		return ssyxmc;
	}
	public void setSsyxmc(String ssyxmc) {
		this.ssyxmc = ssyxmc;
	}
	public String getQsxb() {
		return qsxb;
	}
	public void setQsxb(String qsxb) {
		this.qsxb = qsxb;
	}
	public String getDl() {
		return dl;
	}
	public void setDl(String dl) {
		this.dl = dl;
	}
	public String getRzrs() {
		return rzrs;
	}
	public void setRzrs(String rzrs) {
		this.rzrs = rzrs;
	}
	public String getSfhhqs() {
		return sfhhqs;
	}
	public void setSfhhqs(String sfhhqs) {
		this.sfhhqs = sfhhqs;
	}
	public String getFpzt() {
		return fpzt;
	}
	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}
	public String getCzsj() {
		return czsj;
	}
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}
	public String getFpczr() {
		return fpczr;
	}
	public void setFpczr(String fpczr) {
		this.fpczr = fpczr;
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
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getLdmc() {
		return ldmc;
	}
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getZwzc() {
		return zwzc;
	}
	public void setZwzc(String zwzc) {
		this.zwzc = zwzc;
	}
	public String getDwdm() {
		return dwdm;
	}
	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getZzmmdm() {
		return zzmmdm;
	}
	public void setZzmmdm(String zzmmdm) {
		this.zzmmdm = zzmmdm;
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	public String getDlyq() {
		return dlyq;
	}
	public void setDlyq(String dlyq) {
		this.dlyq = dlyq;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getKyxbj() {
		return kyxbj;
	}
	public void setKyxbj(String kyxbj) {
		this.kyxbj = kyxbj;
	}
	public String getBjyx() {
		return bjyx;
	}
	public void setBjyx(String bjyx) {
		this.bjyx = bjyx;
	}
	public String getCzr() {
		return czr;
	}
	public void setCzr(String czr) {
		this.czr = czr;
	}
	
	

}
