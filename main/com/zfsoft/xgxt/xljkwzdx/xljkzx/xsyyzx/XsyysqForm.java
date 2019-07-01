/**
 * @部门:学工产品事业部
 * @日期：2014-4-25 上午11:22:21 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询-学生预约咨询
 * @类功能描述: 
 * @作者：  王志刚[工号:1060]
 * @时间： 2014-4-25 上午11:22:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsyysqForm extends ActionForm{

	private static final long serialVersionUID = -3752172027912278209L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	/**
	 * 申请ID
	 */
	private String sqid;
	/**
	 * 学号
	 */
	private String xh;
	/**
	 * 咨询预约时间
	 */
	private String yyzxsj;
	/**
	 * 学生练习电话
	 */
	private String xslxdh;
	/**
	 * 问题类型
	 */
	private String wtlx;
	/**
	 * 问题类型数字
	 */
	private String[] wtlxarray;
	/**
	 * 问题简要描述
	 */
	private String yyzxzt;
	/**
	 * 其它备注
	 */
	private String yyzxxq;
	/**
	 * 创建时间
	 */
	private String cjsj;
	/**
	 * 预约状态
	 */
	private String yyzt;
	/**
	 * 数据状态
	 */
	private String sjzt;
	/**
	 * 取消预约原因
	 */
	private String qxyyyy;
	/**
	 * 预约失败原因
	 */
	private String yysbyy;
	
	/**
	 * 预约咨询师
	 */
	private String zxs;
	
	public String getZxs() {
		return zxs;
	}
	public void setZxs(String zxs) {
		this.zxs = zxs;
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
	 * @return the yyzxsj
	 */
	public String getYyzxsj() {
		return yyzxsj;
	}
	/**
	 * @param yyzxsj要设置的 yyzxsj
	 */
	public void setYyzxsj(String yyzxsj) {
		this.yyzxsj = yyzxsj;
	}
	/**
	 * @return the xslxdh
	 */
	public String getXslxdh() {
		return xslxdh;
	}
	/**
	 * @param xslxdh要设置的 xslxdh
	 */
	public void setXslxdh(String xslxdh) {
		this.xslxdh = xslxdh;
	}
	/**
	 * @return the wtlx
	 */
	public String getWtlx() {
		return wtlx;
	}
	/**
	 * @param wtlx要设置的 wtlx
	 */
	public void setWtlx(String wtlx) {
		this.wtlx = wtlx;
	}
	/**
	 * @return the wtlxarray
	 */
	public String[] getWtlxarray() {
		return wtlxarray;
	}
	/**
	 * @param wtlxarray要设置的 wtlxarray
	 */
	public void setWtlxarray(String[] wtlxarray) {
		this.wtlxarray = wtlxarray;
	}
	/**
	 * @return the yyzxzt
	 */
	public String getYyzxzt() {
		return yyzxzt;
	}
	/**
	 * @param yyzxzt要设置的 yyzxzt
	 */
	public void setYyzxzt(String yyzxzt) {
		this.yyzxzt = yyzxzt;
	}
	/**
	 * @return the yyzxxq
	 */
	public String getYyzxxq() {
		return yyzxxq;
	}
	/**
	 * @param yyzxxq要设置的 yyzxxq
	 */
	public void setYyzxxq(String yyzxxq) {
		this.yyzxxq = yyzxxq;
	}
	/**
	 * @return the cjsj
	 */
	public String getCjsj() {
		return cjsj;
	}
	/**
	 * @param cjsj要设置的 cjsj
	 */
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	/**
	 * @return the yyzt
	 */
	public String getYyzt() {
		return yyzt;
	}
	/**
	 * @param yyzt要设置的 yyzt
	 */
	public void setYyzt(String yyzt) {
		this.yyzt = yyzt;
	}
	/**
	 * @return the sjzt
	 */
	public String getSjzt() {
		return sjzt;
	}
	/**
	 * @param sjzt要设置的 sjzt
	 */
	public void setSjzt(String sjzt) {
		this.sjzt = sjzt;
	}
	/**
	 * @return the qxyyyy
	 */
	public String getQxyyyy() {
		return qxyyyy;
	}
	/**
	 * @param qxyyyy要设置的 qxyyyy
	 */
	public void setQxyyyy(String qxyyyy) {
		this.qxyyyy = qxyyyy;
	}
	/**
	 * @return the yysbyy
	 */
	public String getYysbyy() {
		return yysbyy;
	}
	/**
	 * @param yysbyy要设置的 yysbyy
	 */
	public void setYysbyy(String yysbyy) {
		this.yysbyy = yysbyy;
	}
	
}
