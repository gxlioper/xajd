/**
 * @部门:学工产品事业部
 * @日期：2017-5-22 下午06:02:14 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优
 * @类功能描述: 评奖评优-我的评奖-奖项审核
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-5-22 下午05:42:24 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmshForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();/*分页*/
	private SearchModel searchModel = new SearchModel();/*高级查询*/
	private ExportModel exportModel = new ExportModel();/*导出*/
	private String type;/*类型*/
	private String shzt;/*审核状态*/
	private String id;/*申请ID*/
	private String shyj;/*审核意见*/
	private String xyXmdm;/*学院或者项目名称*/
	private String xmdm;/*项目代码*/
	private String dqxmdm; /*当前项目代码*/
	private String pdjx;// 评定奖项
	private String shid;// 审核岗位ID
	private String shsj;// 审核时间
	private String shr;// 审核人
	private String tjdw;// 统计单位
	private String bmdm;// 部门代码
	private String thgw;// 退回岗位
	private String shjg;// 审核结果
	private String gwid;// 岗位id
	private String tzhxmdm;// 调整后项目代码
	private String bjdms;// 班级代码
	private String xmdms;// 项目代码集
	private String splc;// 审批流程
	private String xn;// 学年
	private String xh;// 学号
	private String fjxx;/*附件id*/
	private String sqsj;/*申请时间*/
	private String sqly;/*申请理由*/
	private String sqr;// 申请人
	private String crsj;/*插入时间*/
	
	private String wysp;/*外语水平*/
	private String ssdh;/*宿舍电话*/
	private String gzzw;/*担任社会工作职务*/
	private String cjkyqk;/*参加科研情况*/
	private String dwrs;/*对设奖单位的认识*/
	private String grxxjl;	//个人学习经历
	
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
	 * @return the tjdw
	 */
	public String getTjdw() {
		return tjdw;
	}
	/**
	 * @param tjdw要设置的 tjdw
	 */
	public void setTjdw(String tjdw) {
		this.tjdw = tjdw;
	}
	/**
	 * @return the bmdm
	 */
	public String getBmdm() {
		return bmdm;
	}
	/**
	 * @param bmdm要设置的 bmdm
	 */
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
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
	 * @return the tzhxmdm
	 */
	public String getTzhxmdm() {
		return tzhxmdm;
	}
	/**
	 * @param tzhxmdm要设置的 tzhxmdm
	 */
	public void setTzhxmdm(String tzhxmdm) {
		this.tzhxmdm = tzhxmdm;
	}
	/**
	 * @return the bjdms
	 */
	public String getBjdms() {
		return bjdms;
	}
	/**
	 * @param bjdms要设置的 bjdms
	 */
	public void setBjdms(String bjdms) {
		this.bjdms = bjdms;
	}
	/**
	 * @return the xmdms
	 */
	public String getXmdms() {
		return xmdms;
	}
	/**
	 * @param xmdms要设置的 xmdms
	 */
	public void setXmdms(String xmdms) {
		this.xmdms = xmdms;
	}
	/**
	 * @return the pdjx
	 */
	public String getPdjx() {
		return pdjx;
	}
	/**
	 * @param pdjx要设置的 pdjx
	 */
	public void setPdjx(String pdjx) {
		this.pdjx = pdjx;
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
	 * @return the dqxmdm
	 */
	public String getDqxmdm() {
		return dqxmdm;
	}
	/**
	 * @param dqxmdm要设置的 dqxmdm
	 */
	public void setDqxmdm(String dqxmdm) {
		this.dqxmdm = dqxmdm;
	}
	/**
	 * @return the xyXmdm
	 */
	public String getXyXmdm() {
		return xyXmdm;
	}
	/**
	 * @param xyXmdm要设置的 xyXmdm
	 */
	public void setXyXmdm(String xyXmdm) {
		this.xyXmdm = xyXmdm;
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

	public String getGrxxjl() {
		return grxxjl;
	}

	public void setGrxxjl(String grxxjl) {
		this.grxxjl = grxxjl;
	}
}
