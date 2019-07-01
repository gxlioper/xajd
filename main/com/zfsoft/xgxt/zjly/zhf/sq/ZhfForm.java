/**
 * @部门:学工产品事业部
 * @日期：2016-6-17 下午02:52:23 
 */  
package com.zfsoft.xgxt.zjly.zhf.sq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 综合分(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-17 下午02:52:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfForm extends ActionForm{
	private static final long serialVersionUID = 4791318853675293550L;
	
	private String id;
	private String xh;
	private String jfxmdm;
	private String xmmkdm;
	private String sxsm;
	private String shsj;
	private String shr;
	private String cysj;
	private String shzt;
	private String lrsj;
	private String lrr;
	private String fj;
	private String fjmc;
	private String cxzd;
	private String jfxmdms[];
	private String xmmkdms[];
	private String sxsms[];
	private String fjs[];
	private String xhs[];
	private String cysjs[];
	private String jfxmmc;
	private String xmmkmc;
	private String fs;
	private String khyd;
	private String thyj;
	private String filepath;
	private String defaultimagepath;//默认图片路径
	private String[] xhArr;
	private String lb; //用来区分菜单是审核还是修改
	
	/**
	 * @return the xhArr
	 */
	public String[] getXhArr() {
		return xhArr;
	}
	/**
	 * @param xhArr要设置的 xhArr
	 */
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}
	/**
	 * @return the defaultimagepath
	 */
	public String getDefaultimagepath() {
		return defaultimagepath;
	}
	/**
	 * @param defaultimagepath要设置的 defaultimagepath
	 */
	public void setDefaultimagepath(String defaultimagepath) {
		this.defaultimagepath = defaultimagepath;
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
	/**
	 * @return the thyj
	 */
	public String getThyj() {
		return thyj;
	}
	/**
	 * @param thyj要设置的 thyj
	 */
	public void setThyj(String thyj) {
		this.thyj = thyj;
	}
	private String type;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
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
	 * @return the jfxmdm
	 */
	public String getJfxmdm() {
		return jfxmdm;
	}
	/**
	 * @param jfxmdm要设置的 jfxmdm
	 */
	public void setJfxmdm(String jfxmdm) {
		this.jfxmdm = jfxmdm;
	}
	/**
	 * @return the xmmkdm
	 */
	public String getXmmkdm() {
		return xmmkdm;
	}
	/**
	 * @param xmmkdm要设置的 xmmkdm
	 */
	public void setXmmkdm(String xmmkdm) {
		this.xmmkdm = xmmkdm;
	}
	/**
	 * @return the sxsm
	 */
	public String getSxsm() {
		return sxsm;
	}
	/**
	 * @param sxsm要设置的 sxsm
	 */
	public void setSxsm(String sxsm) {
		this.sxsm = sxsm;
	}
	/**
	 * @return the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @param shsj要设置的 shsj
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	/**
	 * @return the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @param shr要设置的 shr
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @return the cysj
	 */
	public String getCysj() {
		return cysj;
	}
	/**
	 * @param cysj要设置的 cysj
	 */
	public void setCysj(String cysj) {
		this.cysj = cysj;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
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
	 * @return the fj
	 */
	public String getFj() {
		return fj;
	}
	/**
	 * @param fj要设置的 fj
	 */
	public void setFj(String fj) {
		this.fj = fj;
	}
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
	 * @return the cxzd
	 */
	public String getCxzd() {
		return cxzd;
	}
	/**
	 * @param cxzd要设置的 cxzd
	 */
	public void setCxzd(String cxzd) {
		this.cxzd = cxzd;
	}
	/**
	 * @return the jfxmdms
	 */
	public String[] getJfxmdms() {
		return jfxmdms;
	}
	/**
	 * @param jfxmdms要设置的 jfxmdms
	 */
	public void setJfxmdms(String[] jfxmdms) {
		this.jfxmdms = jfxmdms;
	}
	/**
	 * @return the xmmkdms
	 */
	public String[] getXmmkdms() {
		return xmmkdms;
	}
	/**
	 * @param xmmkdms要设置的 xmmkdms
	 */
	public void setXmmkdms(String[] xmmkdms) {
		this.xmmkdms = xmmkdms;
	}
	
	/**
	 * @return the fjs
	 */
	public String[] getFjs() {
		return fjs;
	}
	/**
	 * @param fjs要设置的 fjs
	 */
	public void setFjs(String[] fjs) {
		this.fjs = fjs;
	}
	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @param xhs要设置的 xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	/**
	 * @return the sxsms
	 */
	public String[] getSxsms() {
		return sxsms;
	}
	/**
	 * @param sxsms要设置的 sxsms
	 */
	public void setSxsms(String[] sxsms) {
		this.sxsms = sxsms;
	}
	/**
	 * @return the cysjs
	 */
	public String[] getCysjs() {
		return cysjs;
	}
	/**
	 * @param cysjs要设置的 cysjs
	 */
	public void setCysjs(String[] cysjs) {
		this.cysjs = cysjs;
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
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * @param fjmc要设置的 fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	/**
	 * @return the jfxmmc
	 */
	public String getJfxmmc() {
		return jfxmmc;
	}
	/**
	 * @param jfxmmc要设置的 jfxmmc
	 */
	public void setJfxmmc(String jfxmmc) {
		this.jfxmmc = jfxmmc;
	}
	/**
	 * @return the xmmkmc
	 */
	public String getXmmkmc() {
		return xmmkmc;
	}
	/**
	 * @param xmmkmc要设置的 xmmkmc
	 */
	public void setXmmkmc(String xmmkmc) {
		this.xmmkmc = xmmkmc;
	}
	/**
	 * @return the fs
	 */
	public String getFs() {
		return fs;
	}
	/**
	 * @param fs要设置的 fs
	 */
	public void setFs(String fs) {
		this.fs = fs;
	}
	/**
	 * @return the khyd
	 */
	public String getKhyd() {
		return khyd;
	}
	/**
	 * @param khyd要设置的 khyd
	 */
	public void setKhyd(String khyd) {
		this.khyd = khyd;
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
	 * @return the lb
	 */
	public String getLb() {
		return lb;
	}
	/**
	 * @param lb要设置的 lb
	 */
	public void setLb(String lb) {
		this.lb = lb;
	}
	
	
}
