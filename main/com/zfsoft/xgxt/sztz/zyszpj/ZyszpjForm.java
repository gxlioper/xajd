/**
 * @部门:学工产品事业部
 * @日期：2013-6-6 下午04:59:16 
 */
package com.zfsoft.xgxt.sztz.zyszpj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 职业素质评价
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路 [工号：982]
 * @时间： 2013-6-6 下午04:59:16
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZyszpjForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String zyszid;//职业素质评价id
	private String xh;//学号
	private String zpxx;//自评信息
	private String hpxx;//互评信息
	private String hpr;//互评人
	private String hprid;//互评id
	private String spxx;//师评信息
	private String spr;//师评人
	private String sprid;//师评id
	private String txsj;//填写时间
	private String xn;//学年
	private String xq;//学期
	private String xqmc;//学期名称
	//-------------------------------------
	private String xmlbid;
	private String mc;
	private String xm;//姓名
	private String pjdj;//评价等级
	//---------------------------------
	private List<ZxmForm> zxm;//子项目信息
	private List<Map<String, String>>  zxmMap;//子项目信息（多条详细信息合并）
	private HashMap<String, String> xsxx;//学生信息
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private String dqqx;//当前权限
	private String bjdm;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getZpxx() {
		return zpxx;
	}

	public void setZpxx(String zpxx) {
		this.zpxx = zpxx;
	}

	public String getHpxx() {
		return hpxx;
	}

	public void setHpxx(String hpxx) {
		this.hpxx = hpxx;
	}

	public String getHpr() {
		return hpr;
	}

	public void setHpr(String hpr) {
		this.hpr = hpr;
	}

	public String getHprid() {
		return hprid;
	}

	public void setHprid(String hprId) {
		this.hprid = hprId;
	}

	public String getSpxx() {
		return spxx;
	}

	public void setSpxx(String spxx) {
		this.spxx = spxx;
	}

	public String getSpr() {
		return spr;
	}

	public void setSpr(String spr) {
		this.spr = spr;
	}
	public String getTxsj() {
		return txsj;
	}

	public void setTxsj(String txsj) {
		this.txsj = txsj;
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


	public List<ZxmForm> getZxm() {
		return zxm;
	}

	public void setZxm(List<ZxmForm> zxm) {
		this.zxm = zxm;
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

	public String getZyszid() {
		return zyszid;
	}

	public void setZyszid(String zyszid) {
		this.zyszid = zyszid;
	}

	public String getSprid() {
		return sprid;
	}

	public void setSprid(String sprid) {
		this.sprid = sprid;
	}

	public String getXmlbid() {
		return xmlbid;
	}

	public void setXmlbid(String xmlbid) {
		this.xmlbid = xmlbid;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public HashMap<String, String> getXsxx() {
		return xsxx;
	}

	public void setXsxx(HashMap<String, String> xsxx) {
		this.xsxx = xsxx;
	}

	public String getPjdj() {
		return pjdj;
	}

	public void setPjdj(String pjdj) {
		this.pjdj = pjdj;
	}

	public List<Map<String, String>> getZxmMap() {
		return zxmMap;
	}

	public void setZxmMap(List<Map<String, String>> zxmMap) {
		this.zxmMap = zxmMap;
	}

	public String getDqqx() {
		return dqqx;
	}

	public void setDqqx(String dqqx) {
		this.dqqx = dqqx;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
}
