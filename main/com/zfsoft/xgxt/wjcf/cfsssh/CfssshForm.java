/**
 * @部门:学工产品事业部
 * @日期：2013-10-30 上午09:23:08 
 */  
package com.zfsoft.xgxt.wjcf.cfsssh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (申诉审核) 
 * @作者：陈敏杰[工号:913]
 * @时间： 2013-10-30 上午09:23:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfssshForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	private String ssfilepath;
	
	private String shlx;
	private String guid ;//ID
	private String shid ;//ID
	private String ywid ;//业务ID
	private String shr ;//审核人
	private String shsj ;//审核时间
	private String shzt ;//审核状态
	private String shyj ;//审核意见
	private String gwid ;//审核岗位
	private String splcid ;//审批流程id
	private String cfid;//处分id
	private String sqly; //申请理由
	private String sqsj; //申请时间
	private String ssjg; //申诉结果
	private String sswh; //申诉文号
	private String sssj; //申诉时间
	private String zzssjg; //申诉结果
	private String cfggw; //处分调整类型
	private String fjmc;
	
	private String xh;
	private String thgw;//退回岗位
	
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	
	
		
	/**
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * @param fjmc要设置的 fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	/**
	 * @return the ssfilepath
	 */
	public String getSsfilepath() {
		return ssfilepath;
	}
	/**
	 * @param ssfilepath要设置的 ssfilepath
	 */
	public void setSsfilepath(String ssfilepath) {
		this.ssfilepath = ssfilepath;
	}
	/**
	 * @return the zzssjg
	 */
	public String getZzssjg() {
		return zzssjg;
	}
	/**
	 * @param zzssjg要设置的 zzssjg
	 */
	public void setZzssjg(String zzssjg) {
		this.zzssjg = zzssjg;
	}
	/**
	 * @return the cfggw
	 */
	public String getCfggw() {
		return cfggw;
	}
	/**
	 * @param cfggw要设置的 cfggw
	 */
	public void setCfggw(String cfggw) {
		this.cfggw = cfggw;
	}
	/**
	 * @return the sswh
	 */
	public String getSswh() {
		return sswh;
	}
	/**
	 * @param sswh要设置的 sswh
	 */
	public void setSswh(String sswh) {
		this.sswh = sswh;
	}
	/**
	 * @return the sssj
	 */
	public String getSssj() {
		return sssj;
	}
	/**
	 * @param sssj要设置的 sssj
	 */
	public void setSssj(String sssj) {
		this.sssj = sssj;
	}
	/**
	 * @return the ssjg
	 */
	public String getSsjg() {
		return ssjg;
	}
	/**
	 * @param ssjg要设置的 ssjg
	 */
	public void setSsjg(String ssjg) {
		this.ssjg = ssjg;
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
	 * @return the cfid
	 */
	public String getCfid() {
		return cfid;
	}
	/**
	 * @param cfid要设置的 cfid
	 */
	public void setCfid(String cfid) {
		this.cfid = cfid;
	}
	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcid要设置的 splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
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
	 * @return the shlx
	 */
	public String getShlx() {
		return shlx;
	}
	/**
	 * @param shlx要设置的 shlx
	 */
	public void setShlx(String shlx) {
		this.shlx = shlx;
	}
	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guid要设置的 guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
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
	 * @return the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @param ywid要设置的 ywid
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
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
	
	

}
