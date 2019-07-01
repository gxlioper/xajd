/**
 * @部门：学工产品事业部
 * @日期：2016年11月17日
 */  
package com.zfsoft.xgxt.xlzx.pxwh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称：学生工作管理系统
 * @模块名称：心理健康培训维护 管理模块
 * @类功能描述：心理健康培训维护 实例
 * @作者：卓耐[工号:1391]
 * @时间：2016年11月17日
 * @版本：V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class PxwhForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel(); //高级查询
	private Pages pages = new Pages(); // 分页
	private ExportModel exportModel = new ExportModel(); //自定义导出
	private String type;
	
	private  String pxid; //培训ID
	private  String pxzt; //培训主题
	private  String pxdd; //培训地点
	private  String pxsj; //培训时间
	private  String js; //讲师
	private  String sxrs; //上线人数
	private  String pxnr; //培训内容
	private  String bmkg; //报名开关
	private  String kssj; //开始时间
	private  String jssj; //结束时间
	private String sfybm; //是否已报名
	private String ybmrs; //已报名人数
	
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
	
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	public String getPxid() {
		return pxid;
	}
	public void setPxid(String pxid) {
		this.pxid = pxid;
	}
	
	public String getPxzt() {
		return pxzt;
	}
	public void setPxzt(String pxzt) {
		this.pxzt = pxzt;
	}
	
	public String getPxdd() {
		return pxdd;
	}
	public void setPxdd(String pxdd) {
		this.pxdd = pxdd;
	}
	
	public String getPxsj() {
		return pxsj;
	}
	public void setPxsj(String pxsj) {
		this.pxsj = pxsj;
	}
	
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	
	public String getSxrs() {
		return sxrs;
	}
	public void setSxrs(String sxrs) {
		this.sxrs = sxrs;
	}
	
	public String getPxnr() {
		return pxnr;
	}
	public void setPxnr(String pxnr) {
		this.pxnr = pxnr;
	}


	public String getBmkg() {
		return bmkg;
	}
	public void setBmkg(String bmkg) {
		this.bmkg = bmkg;
	}


	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	

	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	
	public String getSfybm() {
		return sfybm;
	}
	public void setSfybm(String sfybm) {
		this.sfybm = sfybm;
	}
	
	public String getYbmrs() {
		return ybmrs;
	}
	public void setYbmrs(String ybmrs) {
		this.ybmrs = ybmrs;
	}
	
}
