/**
 * @部门:学工产品事业部
 * @日期：2013-11-4 下午06:12:31 
 */  
package com.zfsoft.xgxt.xpjpy.tjcx;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：CQ [工号：785]
 * @时间： 2013-11-4 下午06:12:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjcxModel extends ActionForm{
	
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String type;
	private Pages pages = new Pages();
	private String pk;	//主键
	private String xn;	//学年
	private String xq;	//学期
	private String xh;	//学号
	private String xm;	//姓名
	private String nj;	//年级
	private String xydm;	//学院代码
	private String zydm;	//专业代码
	private String bjdm;	//班级代码
	private String xymc;	//学院代码
	private String xmmc;	//学院名称
	private String hdsj;	//获得时间
	private String xmje;	//项目金额
	private String xmlxmc;	//项目类型名称
	private String xmxzmc;	//项目性质名称

	private String [] xyArr;
	
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

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
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

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getHdsj() {
		return hdsj;
	}

	public void setHdsj(String hdsj) {
		this.hdsj = hdsj;
	}

	public String getXmje() {
		return xmje;
	}

	public void setXmje(String xmje) {
		this.xmje = xmje;
	}

	public String getXmlxmc() {
		return xmlxmc;
	}

	public void setXmlxmc(String xmlxmc) {
		this.xmlxmc = xmlxmc;
	}

	public String getXmxzmc() {
		return xmxzmc;
	}

	public void setXmxzmc(String xmxzmc) {
		this.xmxzmc = xmxzmc;
	}

	public String[] getXyArr() {
		return xyArr;
	}

	public void setXyArr(String[] xyArr) {
		this.xyArr = xyArr;
	}
}
