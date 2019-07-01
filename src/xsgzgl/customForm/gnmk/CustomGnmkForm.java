package xsgzgl.customForm.gnmk;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖_Form类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class CustomGnmkForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	/* 通用 */
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();

	private String gnmkdm;// 功能模块代码

	private String nj;// 年级

	private String xydm;// 学院代码

	private String zydm;// 专业代码

	private String bjdm;// 班级代码

	private String xh;// 学号

	private String xm;// 姓名

	private String xmb;// 项目表

	private String pk;// 主键

	private String pkValue;// 主键值
	
	private String ryfw;// 人员范围

	private String[] zd;// 学号

	private String[] zdz;// 姓名

	private String xhzd;// 主键

	private String[] qtzd = { "xm", "xb", "nj", "xydm", "zydm", "bjdm" };// 其他字段

	private String[] qtzdz;// 其他字段值

	List<HashMap<String, String>> searchContentList;

	public List<HashMap<String, String>> getSearchContentList() {
		return searchContentList;
	}

	public void setSearchContentList(
			List<HashMap<String, String>> searchContentList) {
		this.searchContentList = searchContentList;
	}

	public String getGnmkdm() {
		return gnmkdm;
	}

	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
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

	public String getRyfw() {
		return ryfw;
	}

	public void setRyfw(String ryfw) {
		this.ryfw = ryfw;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
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

	public String[] getZd() {
		return zd;
	}

	public void setZd(String[] zd) {
		this.zd = zd;
	}

	public String[] getZdz() {
		return zdz;
	}

	public void setZdz(String[] zdz) {
		this.zdz = zdz;
	}

	public String getXmb() {
		return xmb;
	}

	public void setXmb(String xmb) {
		this.xmb = xmb;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String[] getQtzd() {
		return qtzd;
	}

	public void setQtzd(String[] qtzd) {
		this.qtzd = qtzd;
	}

	public String[] getQtzdz() {
		return qtzdz;
	}

	public void setQtzdz(String[] qtzdz) {
		this.qtzdz = qtzdz;
	}

	public String getXhzd() {
		return xhzd;
	}

	public void setXhzd(String xhzd) {
		this.xhzd = xhzd;
	}

}
