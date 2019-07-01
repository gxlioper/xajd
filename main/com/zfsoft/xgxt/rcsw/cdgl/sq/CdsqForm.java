/**
 * @部门:学工产品事业部
 * @日期：2014-4-23 下午04:49:38 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 场地申请Form 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-4-23 下午04:49:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdsqForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : 5245794201435149247L 
	 */ 
	
	private static final long serialVersionUID = 5245794201435149247L;

	/** 
	 * @属性 pages : 分页模型 
	 */
	private Pages pages = new Pages();
	
	/** 
	 * @属性 searchModel : 高级查询模型 
	 */
	private SearchModel searchModel = new SearchModel();
	
	/** 
	 * @属性 exportModel : 导出模型 
	 */
	private ExportModel exportModel = new ExportModel();
	
	/**
	 * @属性 ：sqid 申请id
	 */
	private String sqid;
	
	/**
	 * @属性 ：cdid 场地id
	 */
	private String cdid;
	
	/**
	 * @属性 ：xh 学号
	 */
	private String xh;
	
	/**
	 * @属性 ：bmlbdm 部门类别代码
	 */
	private String bmlbdm;
	
	/**
	 * @属性 ：sqsj 申请时间
	 */
	private String sqsj;
	
	/**
	 * @属性 ：sqsjdkssj 申请时间段开始时间
	 */
	private String sqsjdkssj;
	
	/**
	 * @属性 ：sqsjdjssj 申请时间段结束时间
	 */
	private String sqsjdjssj;
	
	/**
	 * @属性 ：sqly 申请理由
	 */
	private String sqly;
	
	/**
	 * @属性 ：shzt 审核状态
	 */
	private String shzt;
	
	/**
	 * @属性 ：splcid 审批流程id
	 */
	private String splcid;
	
	/**
	 * @属性 ：qxfw 权限范围
	 */
	private String qxfw;
	private String cyrs;
	private String fzrxm;
	private String fzrlxfs;
	private String type;
	private String cdmc;
	private String xfgfsyxy;
	
	/**
	 * @描述 ：构造方法
	 */
	public CdsqForm() {
		super();
	}

	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}

	

	/**
	 * @return the cdid
	 */
	public String getCdid() {
		return cdid;
	}

	/**
	 * @param cdid要设置的 cdid
	 */
	public void setCdid(String cdid) {
		this.cdid = cdid;
	}

	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	 * @return the bmlbdm
	 */
	public String getBmlbdm() {
		return bmlbdm;
	}

	/**
	 * @param bmlbdm要设置的 bmlbdm
	 */
	public void setBmlbdm(String bmlbdm) {
		this.bmlbdm = bmlbdm;
	}

	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}

	public String getXfgfsyxy() {
		return xfgfsyxy;
	}

	public void setXfgfsyxy(String xfgfsyxy) {
		this.xfgfsyxy = xfgfsyxy;
	}

	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	/**
	 * @return the sqsjdkssj
	 */
	public String getSqsjdkssj() {
		return sqsjdkssj;
	}

	/**
	 * @param sqsjdkssj要设置的 sqsjdkssj
	 */
	public void setSqsjdkssj(String sqsjdkssj) {
		this.sqsjdkssj = sqsjdkssj;
	}

	/**
	 * @return the sqsjdjssj
	 */
	public String getSqsjdjssj() {
		return sqsjdjssj;
	}

	/**
	 * @param sqsjdjssj要设置的 sqsjdjssj
	 */
	public void setSqsjdjssj(String sqsjdjssj) {
		this.sqsjdjssj = sqsjdjssj;
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

	public String getQxfw() {
		return qxfw;
	}

	public void setQxfw(String qxfw) {
		this.qxfw = qxfw;
	}

	/**
	 * @return the cyrs
	 */
	public String getCyrs() {
		return cyrs;
	}

	/**
	 * @param cyrs要设置的 cyrs
	 */
	public void setCyrs(String cyrs) {
		this.cyrs = cyrs;
	}

	/**
	 * @return the fzrxm
	 */
	public String getFzrxm() {
		return fzrxm;
	}

	/**
	 * @param fzrxm要设置的 fzrxm
	 */
	public void setFzrxm(String fzrxm) {
		this.fzrxm = fzrxm;
	}

	/**
	 * @return the fzrlxfs
	 */
	public String getFzrlxfs() {
		return fzrlxfs;
	}

	/**
	 * @param fzrlxfs要设置的 fzrlxfs
	 */
	public void setFzrlxfs(String fzrlxfs) {
		this.fzrlxfs = fzrlxfs;
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
	 * @return the cdmc
	 */
	public String getCdmc() {
		return cdmc;
	}

	/**
	 * @param cdmc要设置的 cdmc
	 */
	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}
	
	
}
