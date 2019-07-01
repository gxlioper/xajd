/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 上午10:28:12 
 */
package com.zfsoft.xgxt.bzjl.wdbzjl.bzjlsqsh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 我的评奖-申请审核
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-30 上午10:28:12
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BzjlsqshModel extends ActionForm {

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	private String type;

	private String sqid;// 申请ID
	private String xn;// 学年
	private String xq;// 学期
	private String xqmc;// 学期名称
	private String xmdm;// 项目代码
	private String xh;// 学号
	private String sqr;// 申请人
	private String sqsj;// 申请时间
	private String sqly;// 申请理由
	private String splc;// 审批流程
	private String ylzd1;// 预留字段一
	private String ylzd2;// 预留字段二
	private String ylzd3;// 预留字段三
	private String ylzd4;// 预留字段四
	private String ylzd5;// 附件id
	private String shzt;// 审核状态
	private String shid;// 审核岗位ID
	private String shsj;// 审核时间
	private String shr;// 审核人
	private String shyj;// 审核意见
	private String pdjx;// 评定奖项
	private String tjdw;// 统计单位
	private String bmdm;// 部门代码
	private String thgw;// 退回岗位
	private String shjg;// 审核结果
	private String gwid;// 岗位id
	private String tzhxmdm;// 调整后项目代码
	private String bjdms;// 班级代码
	private String xmdms;// 项目代码集
	private String dqxmdm; //当前项目代码
	
	private String xyXmdm; //学院或者项目名称

	private String xmmc;
	private String lxdm;
	private String xzdm;
	private String xmje;

	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	
	private String bjpyjgshzt;
	private String bjpyjgshztmc;
	private String bjpyjgpyhsj;
	private String bjpyjgpyhdd;
	private String bjpyjgpyyj;
	private String bjpyxzcyxms;
	private String bjpyxzdbxms;
	//衢州学院个性化字段
	private String shje;
	//徐州医药高等专科学校个性化字段
	private String djjl;
	
	//中国美术学院个性化修改
	private String[] ids;
	private String sfysq;
	public String getSfysq() {
		return sfysq;
	}

	public void setSfysq(String sfysq) {
		this.sfysq = sfysq;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getDjjl() {
		return djjl;
	}

	public void setDjjl(String djjl) {
		this.djjl = djjl;
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSplc() {
		return splc;
	}

	public void setSplc(String splc) {
		this.splc = splc;
	}

	public String getYlzd1() {
		return ylzd1;
	}

	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}

	public String getYlzd2() {
		return ylzd2;
	}

	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}

	public String getYlzd3() {
		return ylzd3;
	}

	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}

	public String getYlzd4() {
		return ylzd4;
	}

	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}

	public String getYlzd5() {
		return ylzd5;
	}

	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
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

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	public String getShid() {
		return shid;
	}

	public void setShid(String shid) {
		this.shid = shid;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getPdjx() {
		return pdjx;
	}

	public void setPdjx(String pdjx) {
		this.pdjx = pdjx;
	}

	public String getTjdw() {
		return tjdw;
	}

	public void setTjdw(String tjdw) {
		this.tjdw = tjdw;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getThgw() {
		return thgw;
	}

	public void setThgw(String thgw) {
		this.thgw = thgw;
	}

	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}

	/**
	 * @param xmmc要设置的
	 *            xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	/**
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}

	/**
	 * @param lxdm要设置的
	 *            lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}

	/**
	 * @return the xzdm
	 */
	public String getXzdm() {
		return xzdm;
	}

	/**
	 * @param xzdm要设置的
	 *            xzdm
	 */
	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}

	/**
	 * @return the xmje
	 */
	public String getXmje() {
		return xmje;
	}

	/**
	 * @param xmje要设置的
	 *            xmje
	 */
	public void setXmje(String xmje) {
		this.xmje = xmje;
	}

	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}

	/**
	 * @param xqmc要设置的
	 *            xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getGwid() {
		return gwid;
	}

	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	public String getTzhxmdm() {
		return tzhxmdm;
	}

	public void setTzhxmdm(String tzhxmdm) {
		this.tzhxmdm = tzhxmdm;
	}

	public String getBjdms() {
		return bjdms;
	}

	public void setBjdms(String bjdms) {
		this.bjdms = bjdms;
	}

	public String getXmdms() {
		return xmdms;
	}

	public void setXmdms(String xmdms) {
		this.xmdms = xmdms;
	}

	public String getDqxmdm() {
		return dqxmdm;
	}

	public void setDqxmdm(String dqxmdm) {
		this.dqxmdm = dqxmdm;
	}
	
	
	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getGwids() {
		return gwids;
	}

	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}

	public String[] getXhs() {
		return xhs;
	}

	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	public String getBjpyjgshzt() {
		return bjpyjgshzt;
	}

	public void setBjpyjgshzt(String bjpyjgshzt) {
		this.bjpyjgshzt = bjpyjgshzt;
	}

	public String getBjpyjgshztmc() {
		return bjpyjgshztmc;
	}

	public void setBjpyjgshztmc(String bjpyjgshztmc) {
		this.bjpyjgshztmc = bjpyjgshztmc;
	}

	public String getBjpyjgpyhsj() {
		return bjpyjgpyhsj;
	}

	public void setBjpyjgpyhsj(String bjpyjgpyhsj) {
		this.bjpyjgpyhsj = bjpyjgpyhsj;
	}

	public String getBjpyjgpyhdd() {
		return bjpyjgpyhdd;
	}

	public void setBjpyjgpyhdd(String bjpyjgpyhdd) {
		this.bjpyjgpyhdd = bjpyjgpyhdd;
	}

	public String getBjpyjgpyyj() {
		return bjpyjgpyyj;
	}

	public void setBjpyjgpyyj(String bjpyjgpyyj) {
		this.bjpyjgpyyj = bjpyjgpyyj;
	}

	public String getBjpyxzcyxms() {
		return bjpyxzcyxms;
	}

	public void setBjpyxzcyxms(String bjpyxzcyxms) {
		this.bjpyxzcyxms = bjpyxzcyxms;
	}

	public String getBjpyxzdbxms() {
		return bjpyxzdbxms;
	}

	public void setBjpyxzdbxms(String bjpyxzdbxms) {
		this.bjpyxzdbxms = bjpyxzdbxms;
	}

	/**
	 * @return the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}

	/**
	 * @param splcs要设置的 splcs
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
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
	 * @return the shje
	 */
	public String getShje() {
		return shje;
	}

	/**
	 * @param shje要设置的 shje
	 */
	public void setShje(String shje) {
		this.shje = shje;
	}
	
	

}
