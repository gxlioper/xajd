/**
 * @部门:学工产品事业部
 * @日期：2013-5-9 上午08:45:18 
 */  
package com.zfsoft.xgxt.rcsw.sybx;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: TODO 商业保险管理
 * @作者： honglin 
 * @时间： 2013-5-8 下午05:22:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SybxForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	private String type;//类型
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String guid ;//ID
	private String xh ;//学号
	private String xn ;//学年
	private String jhrxm ;//监护人姓名
	private String jhrsfzh ;//监护人
	private String txdz ;//通讯地址
	private String bxje ;//保险总金额
	private String bz ;//备注
	private String czjmylbxje ;//城镇居民医疗保险金额
	private String sybxje ;//商业保险金额
	private String zjyy ;//增加原因
	private String czjmylbxcbqsrq ;//城镇居民医疗保险参保起始日期
	private String czjmylbxcbjsrq ;//城镇居民医疗保险参保结束日期
	private String sybxcbqsrq ;//商业保险参保起始日期
	private String sybxcbjsrq ;//商业保险参保结束日期
	private String cbrylb ;//参保人员类别
	private String jfrylb ;//缴费人员类别
	private String sfzqfjg ;//身份证签发机关
	private String sfzyxqxqsrq ;//身份证有效期限起始日期
	private String sfzyxqxjzrq ;//身份证有效期限截止日期

	private ExportModel exportModel = new ExportModel();
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getJhrxm() {
		return jhrxm;
	}
	public void setJhrxm(String jhrxm) {
		this.jhrxm = jhrxm;
	}
	public String getJhrsfzh() {
		return jhrsfzh;
	}
	public void setJhrsfzh(String jhrsfzh) {
		this.jhrsfzh = jhrsfzh;
	}
	public String getTxdz() {
		return txdz;
	}
	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getBxje() {
		return bxje;
	}
	public void setBxje(String bxje) {
		this.bxje = bxje;
	}
	public String getCzjmylbxje() {
		return czjmylbxje;
	}
	public void setCzjmylbxje(String czjmylbxje) {
		this.czjmylbxje = czjmylbxje;
	}
	public String getSybxje() {
		return sybxje;
	}
	public void setSybxje(String sybxje) {
		this.sybxje = sybxje;
	}
	public String getZjyy() {
		return zjyy;
	}
	public void setZjyy(String zjyy) {
		this.zjyy = zjyy;
	}
	public String getCzjmylbxcbqsrq() {
		return czjmylbxcbqsrq;
	}
	public void setCzjmylbxcbqsrq(String czjmylbxcbqsrq) {
		this.czjmylbxcbqsrq = czjmylbxcbqsrq;
	}
	public String getCzjmylbxcbjsrq() {
		return czjmylbxcbjsrq;
	}
	public void setCzjmylbxcbjsrq(String czjmylbxcbjsrq) {
		this.czjmylbxcbjsrq = czjmylbxcbjsrq;
	}
	public String getSybxcbqsrq() {
		return sybxcbqsrq;
	}
	public void setSybxcbqsrq(String sybxcbqsrq) {
		this.sybxcbqsrq = sybxcbqsrq;
	}
	public String getSybxcbjsrq() {
		return sybxcbjsrq;
	}
	public void setSybxcbjsrq(String sybxcbjsrq) {
		this.sybxcbjsrq = sybxcbjsrq;
	}
	public String getCbrylb() {
		return cbrylb;
	}
	public void setCbrylb(String cbrylb) {
		this.cbrylb = cbrylb;
	}
	public String getJfrylb() {
		return jfrylb;
	}
	public void setJfrylb(String jfrylb) {
		this.jfrylb = jfrylb;
	}
	public String getSfzqfjg() {
		return sfzqfjg;
	}
	public void setSfzqfjg(String sfzqfjg) {
		this.sfzqfjg = sfzqfjg;
	}
	public String getSfzyxqxqsrq() {
		return sfzyxqxqsrq;
	}
	public void setSfzyxqxqsrq(String sfzyxqxqsrq) {
		this.sfzyxqxqsrq = sfzyxqxqsrq;
	}
	public String getSfzyxqxjzrq() {
		return sfzyxqxjzrq;
	}
	public void setSfzyxqxjzrq(String sfzyxqxjzrq) {
		this.sfzyxqxjzrq = sfzyxqxjzrq;
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
	
}
