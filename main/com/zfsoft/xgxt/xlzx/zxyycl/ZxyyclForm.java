/**
 * @部门:学工产品事业部
 * @日期：2013-9-13 上午08:54:02 
 */  
package com.zfsoft.xgxt.xlzx.zxyycl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 咨询预约处理  
 * @作者： wanghj [工号：1004]
 * @时间： 2013-9-13 上午08:54:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxyyclForm  extends ActionForm {


	private static final long serialVersionUID = 1L;

	User user = new User();

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	
	private String id; //主键
	private String yyid; //预约编号
	private String zxlx;//咨询类型
	private String zxrq;//咨询时间
	private String qssj;//咨询时间
	private String jssj;//咨询时间
	private String zxdz;//咨询地址
	private String zxtell;//咨询电话
	private String xspj;//学生评价
	private String xspjsj;//评价时间
	private String zxsfk;//咨询师反馈
	private String zxsfksj;//反馈时间
	private String zxstatus;//咨询状态-1待咨询2已咨询
	private String bz;//备注
	private String datazt;//数据状态-0失效1正常
	private String xspjzt;//学生评价状态-1待评价2已评价
	
	private String xh;//学号
	private String zgh;//职工号
	
	private String yyzxzt;///预约咨询状态
	private String yyzxxq;//预约咨询详情
	private String xstell;//学生
	
	private String xn;//学年
	private String xq;//学期
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user要设置的 user
	 */
	public void setUser(User user) {
		this.user = user;
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
	 * @return the yyid
	 */
	public String getYyid() {
		return yyid;
	}
	/**
	 * @param yyid要设置的 yyid
	 */
	public void setYyid(String yyid) {
		this.yyid = yyid;
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
	 * @return the zxlx
	 */
	public String getZxlx() {
		return zxlx;
	}
	/**
	 * @param zxlx要设置的 zxlx
	 */
	public void setZxlx(String zxlx) {
		this.zxlx = zxlx;
	}
	/**
	 * @return the zxrq
	 */
	public String getZxrq() {
		return zxrq;
	}
	/**
	 * @param zxrq要设置的 zxrq
	 */
	public void setZxrq(String zxrq) {
		this.zxrq = zxrq;
	}
	
	/**
	 * @return the qssj
	 */
	public String getQssj() {
		return qssj;
	}
	/**
	 * @param qssj要设置的 qssj
	 */
	public void setQssj(String qssj) {
		this.qssj = qssj;
	}
	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}
	/**
	 * @param jssj要设置的 jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	/**
	 * @return the zxdz
	 */
	public String getZxdz() {
		return zxdz;
	}
	/**
	 * @param zxdz要设置的 zxdz
	 */
	public void setZxdz(String zxdz) {
		this.zxdz = zxdz;
	}
	
	
	/**
	 * @return the zxtell
	 */
	public String getZxtell() {
		return zxtell;
	}
	/**
	 * @param zxtell要设置的 zxtell
	 */
	public void setZxtell(String zxtell) {
		this.zxtell = zxtell;
	}
	/**
	 * @return the xspj
	 */
	public String getXspj() {
		return xspj;
	}
	/**
	 * @param xspj要设置的 xspj
	 */
	public void setXspj(String xspj) {
		this.xspj = xspj;
	}
	/**
	 * @return the xspjsj
	 */
	public String getXspjsj() {
		return xspjsj;
	}
	/**
	 * @param xspjsj要设置的 xspjsj
	 */
	public void setXspjsj(String xspjsj) {
		this.xspjsj = xspjsj;
	}
	/**
	 * @return the zxsfk
	 */
	public String getZxsfk() {
		return zxsfk;
	}
	/**
	 * @param zxsfk要设置的 zxsfk
	 */
	public void setZxsfk(String zxsfk) {
		this.zxsfk = zxsfk;
	}
	/**
	 * @return the zxsfksj
	 */
	public String getZxsfksj() {
		return zxsfksj;
	}
	/**
	 * @param zxsfksj要设置的 zxsfksj
	 */
	public void setZxsfksj(String zxsfksj) {
		this.zxsfksj = zxsfksj;
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
	 * @return the datazt
	 */
	public String getDatazt() {
		return datazt;
	}
	/**
	 * @param datazt要设置的 datazt
	 */
	public void setDatazt(String datazt) {
		this.datazt = datazt;
	}
	
	/**
	 * @return the zxstatus
	 */
	public String getZxstatus() {
		return zxstatus;
	}
	/**
	 * @param zxstatus要设置的 zxstatus
	 */
	public void setZxstatus(String zxstatus) {
		this.zxstatus = zxstatus;
	}
	/**
	 * @return the xspjzt
	 */
	public String getXspjzt() {
		return xspjzt;
	}
	/**
	 * @param xspjzt要设置的 xspjzt
	 */
	public void setXspjzt(String xspjzt) {
		this.xspjzt = xspjzt;
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
	 * @return the xstell
	 */
	public String getXstell() {
		return xstell;
	}
	/**
	 * @param xstell要设置的 xstell
	 */
	public void setXstell(String xstell) {
		this.xstell = xstell;
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
	
}
