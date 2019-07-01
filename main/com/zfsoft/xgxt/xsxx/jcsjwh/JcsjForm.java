package com.zfsoft.xgxt.xsxx.jcsjwh;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @类功能描述:基础数据维护（年级学院专业班级）
 * @作者： Qilm[工号:964]
 * @时间： 2013-12-3 上午10:56:46 
 * @版本： V1.0
 */
public class JcsjForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 选择FLG(0：学院；1：专业；2：班级 3：年级) 
	 */
	String xzflg;

	/**
	 * 年级
	 */
	private String nj;

	/**
	 * 学院代码
	 */
	private String xydm;

	/**
	 * 学院代码Old
	 */
	private String xydmold;
	
	/**
	 * 学院名称
	 */
	private String xymc;
	
	/**
	 * 专业代码
	 */
	private String zydm;
	
	/**
	 * 专业代码Old
	 */
	private String zydmold;
	
	/**
	 * 学院代码[专业]
	 */
	private String xydmzy;
	
	/**
	 * 专业名称
	 */
	private String zymc;
	
	/**
	 * 班级代码
	 */
	private String bjdm;

	/**
	 * 班级代码Old
	 */
	private String bjdmold;
	
	/**
	 * 学院代码[班级]
	 */
	private String xydmbj;
	/**
	 * 专业代码[班级]
	 */
	private String zydmbj;
	
	/**
	 * 班级名称
	 */
	private String bjmc;

	/**
	 * 学生数
	 */
	private String xss;
	
	/**
	 * 专业数
	 */
	private String zys;

	/**
	 * 班级数
	 */
	private String bjs;
	
	/**
	 * 部门类别
	 */
	private String bmlb;
	
	
	/**
	 * 是否控制数据范围[0:控制;1:不控制]	
	 */
	private String sfkzSjfw;
	
	/**
	 * 是否在校范围[0:在校view_njxyzybj;1:不在校view_njxyzybj_all]	
	 */
	private String sfzx;
	
	/**
	 * 托管学院代码
	 */
	private String tgxydm;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	
	public String getSfkzSjfw() {
		return sfkzSjfw;
	}

	public void setSfkzSjfw(String sfkzSjfw) {
		this.sfkzSjfw = sfkzSjfw;
	}

	public String getSfzx() {
		return sfzx;
	}

	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}

	public String getTgxydm() {
		return tgxydm;
	}

	public void setTgxydm(String tgxydm) {
		this.tgxydm = tgxydm;
	}

	public String getBmlb() {
		return bmlb;
	}
	
	public void setBmlb(String bmlb) {
		this.bmlb = bmlb;
	}

	public String getXydmold() {
		return xydmold;
	}

	public void setXydmold(String xydmold) {
		this.xydmold = xydmold;
	}

	public String getZydmold() {
		return zydmold;
	}

	public void setZydmold(String zydmold) {
		this.zydmold = zydmold;
	}

	public String getBjdmold() {
		return bjdmold;
	}

	public void setBjdmold(String bjdmold) {
		this.bjdmold = bjdmold;
	}

	public String getXydmzy() {
		return xydmzy;
	}

	public void setXydmzy(String xydmzy) {
		this.xydmzy = xydmzy;
	}

	public String getXydmbj() {
		return xydmbj;
	}

	public void setXydmbj(String xydmbj) {
		this.xydmbj = xydmbj;
	}

	public String getZydmbj() {
		return zydmbj;
	}

	public void setZydmbj(String zydmbj) {
		this.zydmbj = zydmbj;
	}

	public String getXzflg() {
		return xzflg;
	}

	public void setXzflg(String xzflg) {
		this.xzflg = xzflg;
	}

	public String getZys() {
		return zys;
	}

	public void setZys(String zys) {
		this.zys = zys;
	}

	public String getBjs() {
		return bjs;
	}

	public void setBjs(String bjs) {
		this.bjs = bjs;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getXss() {
		return xss;
	}

	public void setXss(String xss) {
		this.xss = xss;
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

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
}
