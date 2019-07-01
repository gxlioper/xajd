/**
 * @部门:学工产品事业部
 * @日期：2014-4-29 下午03:23:19 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.jg;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 场地申请结果Form
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-4-29 下午03:23:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdjgForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -2758461030683062977L;
	
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
	 * @属性 jgid : 结果id 
	 */
	private String jgid;
	
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
	
	private String cyrs;
	private String fzrxm;
	private String fzrlxfs;
	
	
	private String xfgfsyxy;
	
	
	private String type;
	private String pj;
	private String pjbz;
	
	/**
	 * @属性 ：shzt 审核状态
	 */
	private String shzt;
	
	/**
	 * @属性 ：splcid 审批流程id
	 */
	private String splcid;
	
	/**
	 * @属性 ：sjly 数据来源
	 */
	private String sjly;
	
	/**
	 * @属性 ：sqly 申请理由
	 */
	private String sqid;

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
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}

	/**
	 * @param jgid要设置的 jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
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

	public String getXfgfsyxy() {
		return xfgfsyxy;
	}

	public void setXfgfsyxy(String xfgfsyxy) {
		this.xfgfsyxy = xfgfsyxy;
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
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}

	/**
	 * @param sjly要设置的 sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}

	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-18 下午05:59:54 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-18 下午05:59:54 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-18 下午05:59:54 
	 * @return		: the pj
	 */
	public String getPj() {
		return pj;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-18 下午05:59:54 
	 * @param 		：pj the pj to set
	 */
	public void setPj(String pj) {
		this.pj = pj;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-18 下午05:59:54 
	 * @return		: the pjbz
	 */
	public String getPjbz() {
		return pjbz;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-18 下午05:59:54 
	 * @param 		：pjbz the pjbz to set
	 */
	public void setPjbz(String pjbz) {
		this.pjbz = pjbz;
	}
	
	
	
}
