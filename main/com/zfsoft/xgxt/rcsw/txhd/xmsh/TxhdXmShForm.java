/**
 * @部门:学工产品事业部
 * @日期：2014-6-26 下午01:57:29 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-6-26 下午01:57:29
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TxhdXmShForm extends ActionForm {
	private String sqr;// VARCHAR2(20) N 申请人
	private String sqly;// VARCHAR2(2000) Y 申请理由
	private String xmdm;// VARCHAR2(32) N 项目代码
	private String xn;// VARCHAR2(10) Y 学年
	private String xq;// VARCHAR2(2) Y 学期
	private String xqmc;
	private String sqid;
	private String xh;
	private String xmmc;
	private String xm;
	private String xb;
	private String xymc;
	private String bjmc;
	private String sqxmmc;
	private String sqsj;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private String lcxx;
	// 审核相关
	private String splc;
	private String shyj;
	private String gwid;
	private String shid;
	private String shjg;
	private String thgw;
	private String shzt;

	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * @param pages要设置的
	 *            pages
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
	 * @param searchModel要设置的
	 *            searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type要设置的
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the lcxx
	 */
	public String getLcxx() {
		return lcxx;
	}

	/**
	 * @param lcxx要设置的
	 *            lcxx
	 */
	public void setLcxx(String lcxx) {
		this.lcxx = lcxx;
	}

	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}

	/**
	 * @param shyj要设置的
	 *            shyj
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
	 * @param gwid要设置的
	 *            gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}

	/**
	 * @param shid要设置的
	 *            shid
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
	 * @param shjg要设置的
	 *            shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}

	/**
	 * @param thgw要设置的
	 *            thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}

	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}

	/**
	 * @param shzt要设置的
	 *            shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}

	/**
	 * @param xh要设置的
	 *            xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}


	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}

	/**
	 * @param xb要设置的
	 *            xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}

	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}

	/**
	 * @param xymc要设置的
	 *            xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}

	/**
	 * @param bjmc要设置的
	 *            bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	/**
	 * @return the sqxmmc
	 */
	public String getSqxmmc() {
		return sqxmmc;
	}

	/**
	 * @param sqxmmc要设置的
	 *            sqxmmc
	 */
	public void setSqxmmc(String sqxmmc) {
		this.sqxmmc = sqxmmc;
	}

	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}

	/**
	 * @param sqsj要设置的
	 *            sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}

	/**
	 * @param sqid要设置的
	 *            sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}

	/**
	 * @param splc要设置的
	 *            splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}

	/**
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}

	/**
	 * @param sqr要设置的
	 *            sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}

	/**
	 * @param sqly要设置的
	 *            sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}

	/**
	 * @param xmdm要设置的
	 *            xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}

	/**
	 * @param xn要设置的
	 *            xn
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
	 * @param xq要设置的
	 *            xq
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
}
