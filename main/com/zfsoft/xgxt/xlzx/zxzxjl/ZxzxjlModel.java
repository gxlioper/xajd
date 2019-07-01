/**
 * @部门:学工产品事业部
 * @日期：2016-12-22 上午10:46:43 
 */  
package com.zfsoft.xgxt.xlzx.zxzxjl;

import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;



/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-12-22 上午10:46:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxzxjlModel extends ActionForm {	
	private static final long serialVersionUID = 1L;
	
	private String xh;
	private String[] xhs;
	private String sfdszn;
	private String jtszd;
	private String jtjjzk;
	private String fqwhcd;
	private String mqwhcd;
	private String fmhyzk;
	private String jtjsbs;
	private String jtxhcd;
	private String sfzl;
	private String djrq;
	private String[] yzxwts;
	private String yzxwt;
	private String wtbc;
	private String zxqw;
	private Pages pages = new Pages();
	private List<ZxzxjlxxModel> xxList;
	private String[] zjs;
	private String[] xgs;
	private String[] delIds;
	
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	private String txr;
	public String getTxr() {
		return txr;
	}
	public void setTxr(String txr) {
		this.txr = txr;
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
	 * @return the sfdszn
	 */
	public String getSfdszn() {
		return sfdszn;
	}
	/**
	 * @param sfdszn要设置的 sfdszn
	 */
	public void setSfdszn(String sfdszn) {
		this.sfdszn = sfdszn;
	}
	/**
	 * @return the jtszd
	 */
	public String getJtszd() {
		return jtszd;
	}
	/**
	 * @param jtszd要设置的 jtszd
	 */
	public void setJtszd(String jtszd) {
		this.jtszd = jtszd;
	}
	/**
	 * @return the jtjjzk
	 */
	public String getJtjjzk() {
		return jtjjzk;
	}
	/**
	 * @param jtjjzk要设置的 jtjjzk
	 */
	public void setJtjjzk(String jtjjzk) {
		this.jtjjzk = jtjjzk;
	}
	/**
	 * @return the fqwhcd
	 */
	public String getFqwhcd() {
		return fqwhcd;
	}
	/**
	 * @param fqwhcd要设置的 fqwhcd
	 */
	public void setFqwhcd(String fqwhcd) {
		this.fqwhcd = fqwhcd;
	}
	/**
	 * @return the mqwhcd
	 */
	public String getMqwhcd() {
		return mqwhcd;
	}
	/**
	 * @param mqwhcd要设置的 mqwhcd
	 */
	public void setMqwhcd(String mqwhcd) {
		this.mqwhcd = mqwhcd;
	}
	/**
	 * @return the fmhyzk
	 */
	public String getFmhyzk() {
		return fmhyzk;
	}
	/**
	 * @param fmhyzk要设置的 fmhyzk
	 */
	public void setFmhyzk(String fmhyzk) {
		this.fmhyzk = fmhyzk;
	}
	/**
	 * @return the jtjsbs
	 */
	public String getJtjsbs() {
		return jtjsbs;
	}
	/**
	 * @param jtjsbs要设置的 jtjsbs
	 */
	public void setJtjsbs(String jtjsbs) {
		this.jtjsbs = jtjsbs;
	}
	/**
	 * @return the jtxhcd
	 */
	public String getJtxhcd() {
		return jtxhcd;
	}
	/**
	 * @param jtxhcd要设置的 jtxhcd
	 */
	public void setJtxhcd(String jtxhcd) {
		this.jtxhcd = jtxhcd;
	}
	/**
	 * @return the sfzl
	 */
	public String getSfzl() {
		return sfzl;
	}
	/**
	 * @param sfzl要设置的 sfzl
	 */
	public void setSfzl(String sfzl) {
		this.sfzl = sfzl;
	}
	/**
	 * @return the djrq
	 */
	public String getDjrq() {
		return djrq;
	}
	/**
	 * @param djrq要设置的 djrq
	 */
	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}
	/**
	 * @return the yzxwt
	 */
	public String getYzxwt() {
		return yzxwt;
	}
	/**
	 * @param yzxwt要设置的 yzxwt
	 */
	public void setYzxwt(String yzxwt) {
		this.yzxwt = yzxwt;
	}
	/**
	 * @return the wtbc
	 */
	public String getWtbc() {
		return wtbc;
	}
	/**
	 * @param wtbc要设置的 wtbc
	 */
	public void setWtbc(String wtbc) {
		this.wtbc = wtbc;
	}
	/**
	 * @return the zxqw
	 */
	public String getZxqw() {
		return zxqw;
	}
	/**
	 * @param zxqw要设置的 zxqw
	 */
	public void setZxqw(String zxqw) {
		this.zxqw = zxqw;
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
	 * @return the xxList
	 */
	public List<ZxzxjlxxModel> getXxList() {
		return xxList;
	}
	/**
	 * @param xxList要设置的 xxList
	 */
	public void setXxList(List<ZxzxjlxxModel> xxList) {
		this.xxList = xxList;
	}
	/**
	 * @return the yzxwts
	 */
	public String[] getYzxwts() {
		return yzxwts;
	}
	/**
	 * @param yzxwts要设置的 yzxwts
	 */
	public void setYzxwts(String[] yzxwts) {
		this.yzxwts = yzxwts;
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
	 * @return the zjs
	 */
	public String[] getZjs() {
		return zjs;
	}
	/**
	 * @param zjs要设置的 zjs
	 */
	public void setZjs(String[] zjs) {
		this.zjs = zjs;
	}
	/**
	 * @return the xgs
	 */
	public String[] getXgs() {
		return xgs;
	}
	/**
	 * @param xgs要设置的 xgs
	 */
	public void setXgs(String[] xgs) {
		this.xgs = xgs;
	}
	/**
	 * @return the delIds
	 */
	public String[] getDelIds() {
		return delIds;
	}
	/**
	 * @param delIds要设置的 delIds
	 */
	public void setDelIds(String[] delIds) {
		this.delIds = delIds;
	}
	
	
	  
}
