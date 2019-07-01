/**
 * @部门:学工产品事业部
 * @日期：2013-12-19 下午01:43:58 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.xjtx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-19 下午01:43:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnxjForm extends ActionForm {

	
	
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	
	private String type;
	private String shQryType;
	private String kgzt;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String searchXn ; //搜索学年
	
	private String id; //业务主键
	
	private String xh;	//学号
	
	private String xn;	//学年
	
	private String xjnr; //小结内容
	
	private String txsj;	//填写时间
	
	private String shjg;  //审核结果
	
	private String splid;  //审核流ID

	private String xtgwid; //岗位id
	
	private String shid; //审核id
	
	private String shyj;//审核意见
	
	private String thgw; //退回岗位id
	
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
	 * @return the xjnr
	 */
	public String getXjnr() {
		return xjnr;
	}

	/**
	 * @param xjnr要设置的 xjnr
	 */
	public void setXjnr(String xjnr) {
		this.xjnr = xjnr;
	}

	/**
	 * @return the txsj
	 */
	public String getTxsj() {
		return txsj;
	}

	/**
	 * @param txsj要设置的 txsj
	 */
	public void setTxsj(String txsj) {
		this.txsj = txsj;
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
	 * @return the splid
	 */
	public String getSplid() {
		return splid;
	}

	/**
	 * @param splid要设置的 splid
	 */
	public void setSplid(String splid) {
		this.splid = splid;
	}

	/**
	 * @return the searchXn
	 */
	public String getSearchXn() {
		return searchXn;
	}

	/**
	 * @param searchXn要设置的 searchXn
	 */
	public void setSearchXn(String searchXn) {
		this.searchXn = searchXn;
	}

	/**
	 * @return the kgzt
	 */
	public String getKgzt() {
		return kgzt;
	}

	/**
	 * @param kgzt要设置的 kgzt
	 */
	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}

	/**
	 * @return the shQryType
	 */
	public String getShQryType() {
		return shQryType;
	}

	/**
	 * @param shQryType要设置的 shQryType
	 */
	public void setShQryType(String shQryType) {
		this.shQryType = shQryType;
	}

	/**
	 * @return the xtgwid
	 */
	public String getXtgwid() {
		return xtgwid;
	}

	/**
	 * @param xtgwid要设置的 xtgwid
	 */
	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
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


	
	
}
