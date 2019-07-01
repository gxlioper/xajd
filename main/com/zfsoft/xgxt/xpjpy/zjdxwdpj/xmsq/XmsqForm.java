/**
 * @部门:学工产品(1)部
 * @日期：2017-5-12 下午05:00:34 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsq;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 我的评奖_奖项申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-5-12 下午05:00:34 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmsqForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();/*分页*/
	private SearchModel searchModel = new SearchModel();/*高级查询*/
	private ExportModel exportModel = new ExportModel();/*导出*/
	private String type;/*类型*/
	private String id;/*id*/
	private String xh;/*学号*/
	private String xq;/*学期，实际浙大是用不到xq这个字段的，因后面业务要求还是加上吧*/
	private String xn;/*学年*/
	private String xmdm;/*项目代码*/
	private String xmmc;/*项目名称*/
	private String xzdm;/*性质代码*/
	private String lxdm;/*类型代码*/
	private String sqr;/*申请人*/
	private String sqsj;/*申请时间*/
	private String sqly;/*申请理由*/
	private String splc;/*审批流程*/
	private String shzt;/*审核状态*/
	private String fjxx;/*附件id*/
	private String crsj;/*插入时间*/
	private String wysp;/*外语水平*/
	private String ssdh;/*宿舍电话*/
	private String gzzw;/*担任社会工作职务*/
	private String cjkyqk;/*参加科研情况*/
	private String dwrs;/*对设奖单位的认识*/
	private String grxxjl;	//个人学习经历

	/**
	 * 申请类型，1：荣誉申请，2：奖项申请。
	 */
	private String sqlx;

	
	/**
	 * @return the fjxx
	 */
	public String getFjxx() {
		return fjxx;
	}
	/**
	 * @param fjxx要设置的 fjxx
	 */
	public void setFjxx(String fjxx) {
		this.fjxx = fjxx;
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
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdm要设置的 xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	/**
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}
	/**
	 * @param sqr要设置的 sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
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
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqly要设置的 sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
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
	 * @return the crsj
	 */
	public String getCrsj() {
		return crsj;
	}
	/**
	 * @param crsj要设置的 crsj
	 */
	public void setCrsj(String crsj) {
		this.crsj = crsj;
	}
	/**
	 * @return the wysp
	 */
	public String getWysp() {
		return wysp;
	}
	/**
	 * @param wysp要设置的 wysp
	 */
	public void setWysp(String wysp) {
		this.wysp = wysp;
	}
	/**
	 * @return the ssdh
	 */
	public String getSsdh() {
		return ssdh;
	}
	/**
	 * @param ssdh要设置的 ssdh
	 */
	public void setSsdh(String ssdh) {
		this.ssdh = ssdh;
	}
	/**
	 * @return the gzzw
	 */
	public String getGzzw() {
		return gzzw;
	}
	/**
	 * @param gzzw要设置的 gzzw
	 */
	public void setGzzw(String gzzw) {
		this.gzzw = gzzw;
	}
	/**
	 * @return the cjkyqk
	 */
	public String getCjkyqk() {
		return cjkyqk;
	}
	/**
	 * @param cjkyqk要设置的 cjkyqk
	 */
	public void setCjkyqk(String cjkyqk) {
		this.cjkyqk = cjkyqk;
	}
	/**
	 * @return the dwrs
	 */
	public String getDwrs() {
		return dwrs;
	}
	/**
	 * @param dwrs要设置的 dwrs
	 */
	public void setDwrs(String dwrs) {
		this.dwrs = dwrs;
	}

	public String getSqlx() {
		return sqlx;
	}
	public void setSqlx(String sqlx) {
		this.sqlx = sqlx;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc要设置的 xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
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
	 * @return the xzdm
	 */
	public String getXzdm() {
		return xzdm;
	}
	/**
	 * @param xzdm要设置的 xzdm
	 */
	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}
	/**
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}
	/**
	 * @param lxdm要设置的 lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}

	public String getGrxxjl() {
		return grxxjl;
	}

	public void setGrxxjl(String grxxjl) {
		this.grxxjl = grxxjl;
	}
}
