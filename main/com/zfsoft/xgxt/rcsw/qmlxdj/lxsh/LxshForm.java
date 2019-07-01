/**
 * @部门:学工产品事业部
 * @日期：2017-1-11 上午09:03:18 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-11 上午09:03:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxshForm extends ActionForm {
	private String sqid;
	private String xh;
	private String xn;
	private String xq;
	private String jhrxm;
	private String jhrlxfs;
	private String sflx;
	private String lxsj;
	private String lxjtgjdm;
	private String lxcchb;
	private String mddssx;
	private String fxsj;
	private String fxjtgjdm;
	private String fxcchb;
	private String bz;
	private String splcid;
	private String shzt;
	private String sqsj;
	private String type;
	private ExportModel exportModel = new ExportModel();
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	
	//审核字段
	private String shid;
	private String shjg;
	private String shyj;
	private String gwid;
	private String thgw;
	private String[] xns;
	private String[] xqs;
	/**
	 * @return the xns
	 */
	public String[] getXns() {
		return xns;
	}
	/**
	 * @param xns要设置的 xns
	 */
	public void setXns(String[] xns) {
		this.xns = xns;
	}
	/**
	 * @return the xqs
	 */
	public String[] getXqs() {
		return xqs;
	}
	/**
	 * @param xqs要设置的 xqs
	 */
	public void setXqs(String[] xqs) {
		this.xqs = xqs;
	}
	private String shlc;
	//批量审核用
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private static final long serialVersionUID = 1L;
	
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
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlc要设置的 shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the jhrxm
	 */
	public String getJhrxm() {
		return jhrxm;
	}
	/**
	 * @param jhrxm要设置的 jhrxm
	 */
	public void setJhrxm(String jhrxm) {
		this.jhrxm = jhrxm;
	}
	/**
	 * @return the jhrlxfs
	 */
	public String getJhrlxfs() {
		return jhrlxfs;
	}
	/**
	 * @param jhrlxfs要设置的 jhrlxfs
	 */
	public void setJhrlxfs(String jhrlxfs) {
		this.jhrlxfs = jhrlxfs;
	}
	/**
	 * @return the sflx
	 */
	public String getSflx() {
		return sflx;
	}
	/**
	 * @param sflx要设置的 sflx
	 */
	public void setSflx(String sflx) {
		this.sflx = sflx;
	}
	/**
	 * @return the lxsj
	 */
	public String getLxsj() {
		return lxsj;
	}
	/**
	 * @param lxsj要设置的 lxsj
	 */
	public void setLxsj(String lxsj) {
		this.lxsj = lxsj;
	}
	/**
	 * @return the lxjtgjdm
	 */
	public String getLxjtgjdm() {
		return lxjtgjdm;
	}
	/**
	 * @param lxjtgjdm要设置的 lxjtgjdm
	 */
	public void setLxjtgjdm(String lxjtgjdm) {
		this.lxjtgjdm = lxjtgjdm;
	}
	/**
	 * @return the lxcchb
	 */
	public String getLxcchb() {
		return lxcchb;
	}
	/**
	 * @param lxcchb要设置的 lxcchb
	 */
	public void setLxcchb(String lxcchb) {
		this.lxcchb = lxcchb;
	}
	/**
	 * @return the fxsj
	 */
	public String getFxsj() {
		return fxsj;
	}
	/**
	 * @return the mddssx
	 */
	public String getMddssx() {
		return mddssx;
	}
	/**
	 * @param mddssx要设置的 mddssx
	 */
	public void setMddssx(String mddssx) {
		this.mddssx = mddssx;
	}
	/**
	 * @param fxsj要设置的 fxsj
	 */
	public void setFxsj(String fxsj) {
		this.fxsj = fxsj;
	}
	/**
	 * @return the fxjtgjdm
	 */
	public String getFxjtgjdm() {
		return fxjtgjdm;
	}
	/**
	 * @param fxjtgjdm要设置的 fxjtgjdm
	 */
	public void setFxjtgjdm(String fxjtgjdm) {
		this.fxjtgjdm = fxjtgjdm;
	}
	/**
	 * @return the fxcchb
	 */
	public String getFxcchb() {
		return fxcchb;
	}
	/**
	 * @param fxcchb要设置的 fxcchb
	 */
	public void setFxcchb(String fxcchb) {
		this.fxcchb = fxcchb;
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
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcid要设置的 splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
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
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shid要设置的 shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjg要设置的 shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyj要设置的 shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwid要设置的 gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgw要设置的 thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String[] id) {
		this.id = id;
	}
	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @param gwids要设置的 gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
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
	
}
