/**
 * @部门:学工产品事业部
 * @日期：2013-10-30 下午04:20:13 
 */  
package com.zfsoft.xgxt.wjcf.cfjcsq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分解除申请管理) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-30 下午04:19:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfjcsqForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String xh;
	private String jcid;            //解除id
	private String cfid;            //处分ID
	private String sqsj;            //申请时间
	private String sqly;            //申请理由
	private String sqjg;            //申请结果
	private String splcid;            //审批流程id
	private String jdxx;            //鉴定信息
	
	
	//玉林师范学院解除处分打印表需要用到的字段
	private String xm;				
	private String xb;	
	private String xymc;			//学院名称	
	private String bjmc;			//班级名称
	private String cflbmc;				//处分类别名称
	private String cfsj;			//处分时间
	private String cfyymc;				//处分原因
	private String jcsj;			//处分解除时间
	private String dysj;			//打印时间
	private String bjyj;//班级意见
	private String qksm;//情况说明
	private String filepath;//跟踪教育记录表
	private String filepath2;//成绩单

	public String getBjyj() {
		return bjyj;
	}

	public void setBjyj(String bjyj) {
		this.bjyj = bjyj;
	}

	public String getQksm() {
		return qksm;
	}

	public void setQksm(String qksm) {
		this.qksm = qksm;
	}

	public String getFilepath2() {
		return filepath2;
	}

	public void setFilepath2(String filepath2) {
		this.filepath2 = filepath2;
	}

	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepath要设置的 filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getCflbmc() {
		return cflbmc;
	}
	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}
	public String getCfsj() {
		return cfsj;
	}
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}
	public String getCfyymc() {
		return cfyymc;
	}
	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}
	public String getJcsj() {
		return jcsj;
	}
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	public String getDysj() {
		return dysj;
	}
	public void setDysj(String dysj) {
		this.dysj = dysj;
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
	 * @return the jcid
	 */
	public String getJcid() {
		return jcid;
	}
	/**
	 * @param jcid要设置的 jcid
	 */
	public void setJcid(String jcid) {
		this.jcid = jcid;
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
	 * @return the sqjg
	 */
	public String getSqjg() {
		return sqjg;
	}
	/**
	 * @param sqjg要设置的 sqjg
	 */
	public void setSqjg(String sqjg) {
		this.sqjg = sqjg;
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
	public String getJdxx() {
		return jdxx;
	}
	public void setJdxx(String jdxx) {
		this.jdxx = jdxx;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	
	
}
