/**
 * @部门:学工产品事业部
 * @日期：2017年5月4日 下午2:22:42 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 志愿服务管理模块
 * @类功能描述: 志愿服务申请Form
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月4日 下午2:22:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwSqForm extends ActionForm{
	
	private static final long serialVersionUID = -8916494147378969804L;
	
	private String fwid;	//服务（申请）id
	private String xh;	//学号
	private String xn;	//学年
	private String xq;	//学期
	private String xqmc;	//学期名称
	private String fwkssj;	//服务开始时间
	private String fwjssj;	//服务结束时间
	private String fwddssx;	//服务地点省市县
	private String fwdd;	//服务地点
	private String jzr;	//见证人
	private String fwxss;	//服务小时数
	private String fwnr;	//服务内容
	private String splc;	//审批流程
	private String shzt;	//审核状态
	
	//分页
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	
	private String type;

	/**
	 * @return the fwid
	 */
	public String getFwid() {
		return fwid;
	}

	/**
	 * @param fwid要设置的 fwid
	 */
	public void setFwid(String fwid) {
		this.fwid = fwid;
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
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}

	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	/**
	 * @return the fwkssj
	 */
	public String getFwkssj() {
		return fwkssj;
	}

	/**
	 * @param fwkssj要设置的 fwkssj
	 */
	public void setFwkssj(String fwkssj) {
		this.fwkssj = fwkssj;
	}

	/**
	 * @return the fwjssj
	 */
	public String getFwjssj() {
		return fwjssj;
	}

	/**
	 * @param fwjssj要设置的 fwjssj
	 */
	public void setFwjssj(String fwjssj) {
		this.fwjssj = fwjssj;
	}

	/**
	 * @return the fwddssx
	 */
	public String getFwddssx() {
		return fwddssx;
	}

	/**
	 * @param fwddssx要设置的 fwddssx
	 */
	public void setFwddssx(String fwddssx) {
		this.fwddssx = fwddssx;
	}

	/**
	 * @return the fwdd
	 */
	public String getFwdd() {
		return fwdd;
	}

	/**
	 * @param fwdd要设置的 fwdd
	 */
	public void setFwdd(String fwdd) {
		this.fwdd = fwdd;
	}

	/**
	 * @return the jzr
	 */
	public String getJzr() {
		return jzr;
	}

	/**
	 * @param jzr要设置的 jzr
	 */
	public void setJzr(String jzr) {
		this.jzr = jzr;
	}

	/**
	 * @return the fwxss
	 */
	public String getFwxss() {
		return fwxss;
	}

	/**
	 * @param fwxss要设置的 fwxss
	 */
	public void setFwxss(String fwxss) {
		this.fwxss = fwxss;
	}

	/**
	 * @return the fwnr
	 */
	public String getFwnr() {
		return fwnr;
	}

	/**
	 * @param fwnr要设置的 fwnr
	 */
	public void setFwnr(String fwnr) {
		this.fwnr = fwnr;
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
	

}
