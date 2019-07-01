/**
 * @部门:学工产品事业部
 * @日期：2016-5-10 下午03:51:52 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.sh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 毕业还款管理
 * @类功能描述: 毕业还款审核 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-10 下午03:51:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ByhkglShForm extends ActionForm{
	
	private String sqid;
	private String xh;
	private String hkje;
	private String sfzq;
	private String zqyy;
	private String zqyymc;
	private String zqqx;
	private String shzt;
	private String splc;
	private String sqsj;
	private String bz;
	
	private String ywid;
	private String shsj;
	private String shr;
	private String shyj;
	private String shlc;
	private String gwid;
	private String shztmc;
	private String shid;
	private String thgw;//岗位退回
	private String shjg;
	private String filepath;
	
	private String[] id;
	private String[] gwids;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getHkje() {
		return hkje;
	}
	public void setHkje(String hkje) {
		this.hkje = hkje;
	}
	public String getSfzq() {
		return sfzq;
	}
	public void setSfzq(String sfzq) {
		this.sfzq = sfzq;
	}
	public String getZqyy() {
		return zqyy;
	}
	public void setZqyy(String zqyy) {
		this.zqyy = zqyy;
	}
	public String getZqyymc() {
		return zqyymc;
	}
	public void setZqyymc(String zqyymc) {
		this.zqyymc = zqyymc;
	}
	public String getZqqx() {
		return zqqx;
	}
	public void setZqqx(String zqqx) {
		this.zqqx = zqqx;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getYwid() {
		return ywid;
	}
	public void setYwid(String ywid) {
		this.ywid = ywid;
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
	public String getShlc() {
		return shlc;
	}
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	public String getGwid() {
		return gwid;
	}
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	public String getShztmc() {
		return shztmc;
	}
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	public String getShid() {
		return shid;
	}
	public void setShid(String shid) {
		this.shid = shid;
	}
	public String getThgw() {
		return thgw;
	}
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	public String getShjg() {
		return shjg;
	}
	public void setShjg(String shjg) {
		this.shjg = shjg;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
}
