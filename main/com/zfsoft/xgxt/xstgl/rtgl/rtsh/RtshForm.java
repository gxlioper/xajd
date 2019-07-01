/**
 * @部门:学工产品事业部
 * @日期：2015-8-14 上午08:37:52 
 */
package com.zfsoft.xgxt.xstgl.rtgl.rtsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-8-14 上午08:37:52
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class RtshForm extends ActionForm {
	private String rtid;
	private String xh;
	private String stid;
	private String rtxn;
	private String rtxq;
	private String sqly;
	private String tc;// 特长
	private String shzt;
	private String stxmmc;
	private String xmlbdm;
	private String sqsj;
	private String splc;
	private String shlc;
	private String shid;
	private String shyj;
	private String shjg;
	private String thgw;
	private String gwid;
	private String rylbdm;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;

	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	private String[] stids;

	/**
	 * @return the rylbdm
	 */
	public String getRylbdm() {
		return rylbdm;
	}

	/**
	 * @param rylbdm要设置的 rylbdm
	 */
	public void setRylbdm(String rylbdm) {
		this.rylbdm = rylbdm;
	}

	/**
	 * @return the rtxn
	 */
	public String getRtxn() {
		return rtxn;
	}

	/**
	 * @param rtxn要设置的 rtxn
	 */
	public void setRtxn(String rtxn) {
		this.rtxn = rtxn;
	}

	/**
	 * @return the rtxq
	 */
	public String getRtxq() {
		return rtxq;
	}

	/**
	 * @param rtxq要设置的 rtxq
	 */
	public void setRtxq(String rtxq) {
		this.rtxq = rtxq;
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
	 * @return the rtid
	 */
	public String getRtid() {
		return rtid;
	}

	/**
	 * @param rtid要设置的
	 *            rtid
	 */
	public void setRtid(String rtid) {
		this.rtid = rtid;
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
	 * @return the stid
	 */
	public String getStid() {
		return stid;
	}

	/**
	 * @param stid要设置的
	 *            stid
	 */
	public void setStid(String stid) {
		this.stid = stid;
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
	 * @return the tc
	 */
	public String getTc() {
		return tc;
	}

	/**
	 * @param tc要设置的
	 *            tc
	 */
	public void setTc(String tc) {
		this.tc = tc;
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
	 * @return the stxmmc
	 */
	public String getStxmmc() {
		return stxmmc;
	}

	/**
	 * @param stxmmc要设置的
	 *            stxmmc
	 */
	public void setStxmmc(String stxmmc) {
		this.stxmmc = stxmmc;
	}

	/**
	 * @return the xmlbdm
	 */
	public String getXmlbdm() {
		return xmlbdm;
	}

	/**
	 * @param xmlbdm要设置的
	 *            xmlbdm
	 */
	public void setXmlbdm(String xmlbdm) {
		this.xmlbdm = xmlbdm;
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
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}

	/**
	 * @param shlc要设置的
	 *            shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}

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
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}

	/**
	 * @param id要设置的
	 *            id
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
	 * @param gwids要设置的
	 *            gwids
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
	 * @param xhs要设置的
	 *            xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	/**
	 * @return the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}

	/**
	 * @param splcs要设置的
	 *            splcs
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}

	/**
	 * @return the stids
	 */
	public String[] getStids() {
		return stids;
	}

	/**
	 * @param stids要设置的
	 *            stids
	 */
	public void setStids(String[] stids) {
		this.stids = stids;
	}
}
