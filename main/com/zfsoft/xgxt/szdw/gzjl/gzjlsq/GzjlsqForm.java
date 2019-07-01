/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午04:39:07 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjlsq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-6-25 下午04:39:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzjlsqForm extends ActionForm {
	private String sqid;
	private String zgh;
	private String xm;
	private String xymc;
	private String lbdm;
	private String gzlbmc;
	private String lbbh;
	private String gzsj;
	private String jlsj;
	private String gzzy;
	private String czr;
	private String bz;
	private String sjly;
	private String type;
	private String shzt;
	private String splc;
	private String xh;
	private String lks;
	private String[] xhArr;
	private String lksmc;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zgh要设置的 zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	/**
	 * @return the lbdm
	 */
	public String getLbdm() {
		return lbdm;
	}
	/**
	 * @param lbdm要设置的 lbdm
	 */
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}
	/**
	 * @return the lbbh
	 */
	public String getLbbh() {
		return lbbh;
	}
	/**
	 * @param lbbh要设置的 lbbh
	 */
	public void setLbbh(String lbbh) {
		this.lbbh = lbbh;
	}
	/**
	 * @return the gzsj
	 */
	public String getGzsj() {
		return gzsj;
	}
	/**
	 * @param gzsj要设置的 gzsj
	 */
	public void setGzsj(String gzsj) {
		this.gzsj = gzsj;
	}
	/**
	 * @return the jlsj
	 */
	public String getJlsj() {
		return jlsj;
	}
	/**
	 * @param jlsj要设置的 jlsj
	 */
	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}
	/**
	 * @return the gzzy
	 */
	public String getGzzy() {
		return gzzy;
	}
	/**
	 * @param gzzy要设置的 gzzy
	 */
	public void setGzzy(String gzzy) {
		this.gzzy = gzzy;
	}
	/**
	 * @return the czr
	 */
	public String getCzr() {
		return czr;
	}
	/**
	 * @param czr要设置的 czr
	 */
	public void setCzr(String czr) {
		this.czr = czr;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
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
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
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
	 * @return the gzlbmc
	 */
	public String getGzlbmc() {
		return gzlbmc;
	}
	/**
	 * @param gzlbmc要设置的 gzlbmc
	 */
	public void setGzlbmc(String gzlbmc) {
		this.gzlbmc = gzlbmc;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymc要设置的 xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
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
	 * @return the lks
	 */
	public String getLks() {
		return lks;
	}
	/**
	 * @param lks要设置的 lks
	 */
	public void setLks(String lks) {
		this.lks = lks;
	}
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
	 * @return the lksmc
	 */
	public String getLksmc() {
		return lksmc;
	}
	/**
	 * @param lksmc要设置的 lksmc
	 */
	public void setLksmc(String lksmc) {
		this.lksmc = lksmc;
	}
	
	

}
