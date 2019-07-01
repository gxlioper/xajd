/**
 * @部门:学工产品事业部
 * @日期：2014-4-23 上午09:39:15 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.cdxxwh;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 场地信息维护actionForm
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-4-23 上午09:39:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdxxwhForm extends ActionForm{

	/** 
	 * @属性 serialVersionUID : -6656773682624980098L 
	 */ 
	private static final long serialVersionUID = -6656773682624980098L;

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
	 * @属性 cdid : 场地标识 
	 */ 
	private String cdid;
	
	/** 
	 * @属性 cdmc : 场地名称 
	 */ 
	private String cdmc;
	
	/** 
	 * @属性 ld : 楼栋
	 */ 
	private String ld;
	
	/** 
	 * @属性 fj : 房间
	 */ 
	private String fj;
	
	/** 
	 * @属性 rnrs : 容纳人数
	 */ 
	private String rnrs;
	
	/** 
	 * @属性 sfbz : 收费标准
	 */ 
	private String sfbz;
	
	/** 
	 * @属性 dwkfsjkssj : 对外开放开始时间
	 */ 
	private String dwkfsjkssj;
	
	/** 
	 * @属性 dwkfsjjssj : 对外开放结束时间
	 */ 
	private String dwkfsjjssj;
	
	private String xfgfsyxy;
	
	/** 
	 * @属性 yt : 用途
	 */ 
	private String yt;
	
	/** 
	 * @属性 jbsbjs : 基本设备介绍 
	 */ 
	private String jbsbjs;
	
	/** 
	 * @属性 sfkfsq : 是否开放申请
	 * 默认设置未 ‘1’ 可申请状态
	 */ 
	private String sfkfsq = "1";
	
	/** 
	 * @属性 splcid : 审批流程id
	 */ 
	private String splcid;
	
	/** 
	 * @属性 qxfw : 权限范围
	 */
	private String qxfw;
	
	private String lxr;//联系人
	
	private String lxfs;//联系方式
	
	//下载相关
	private FormFile formfile;
	private String filepath;
	private String xysfilepath; // 协议书附件id
	
	
	/**
	 * @return the lxr
	 */
	public String getLxr() {
		return lxr;
	}

	/**
	 * @param lxr要设置的 lxr
	 */
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	/**
	 * @return the lxfs
	 */
	public String getLxfs() {
		return lxfs;
	}

	/**
	 * @param lxfs要设置的 lxfs
	 */
	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

	public String getQxfw() {
		return qxfw;
	}

	public void setQxfw(String qxfw) {
		this.qxfw = qxfw;
	}

	/********************查询参数*****************************/
	
	private String search_cdmc; //查询条件_场地名称
	
	private String search_rnrsmin; //查询条件_容纳人数 min
	
	private String search_rnrsmax; //查询条件_容纳人数 max
	
	private String search_yt; //查询条件_用途
	
	private String search_dwkfsjkssj; //查询条件_对外开放开始时间
	
	private String search_dwkfsjjssj; //查询条件_对外开始结束时间
	
	private String search_sfkfsq; //查询条件——是否对外开放申请
	/********************查询参数*****************************/
	
	/**
	 * @描述 ：构造方法
	 */
	public CdxxwhForm() {
		super();
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

	/**
	 * @return the ld
	 */
	public String getLd() {
		return ld;
	}

	/**
	 * @param ld要设置的 ld
	 */
	public void setLd(String ld) {
		this.ld = ld;
	}

	/**
	 * @return the fj
	 */
	public String getFj() {
		return fj;
	}

	/**
	 * @param fj要设置的 fj
	 */
	public void setFj(String fj) {
		this.fj = fj;
	}

	/**
	 * @return the rnrs
	 */
	public String getRnrs() {
		return rnrs;
	}

	/**
	 * @param rnrs要设置的 rnrs
	 */
	public void setRnrs(String rnrs) {
		this.rnrs = rnrs;
	}

	/**
	 * @return the sfbz
	 */
	public String getSfbz() {
		return sfbz;
	}

	/**
	 * @param sfbz要设置的 sfbz
	 */
	public void setSfbz(String sfbz) {
		this.sfbz = sfbz;
	}

	/**
	 * @return the dwkfsjkssj
	 */
	public String getDwkfsjkssj() {
		return dwkfsjkssj;
	}

	/**
	 * @param dwkfsjkssj要设置的 dwkfsjkssj
	 */
	public void setDwkfsjkssj(String dwkfsjkssj) {
		this.dwkfsjkssj = dwkfsjkssj;
	}

	/**
	 * @return the dwkfsjjssj
	 */
	public String getDwkfsjjssj() {
		return dwkfsjjssj;
	}

	/**
	 * @param dwkfsjjssj要设置的 dwkfsjjssj
	 */
	public void setDwkfsjjssj(String dwkfsjjssj) {
		this.dwkfsjjssj = dwkfsjjssj;
	}

	public String getXfgfsyxy() {
		return xfgfsyxy;
	}

	public void setXfgfsyxy(String xfgfsyxy) {
		this.xfgfsyxy = xfgfsyxy;
	}

	/**
	 * @return the yt
	 */
	public String getYt() {
		return yt;
	}

	/**
	 * @param yt要设置的 yt
	 */
	public void setYt(String yt) {
		this.yt = yt;
	}

	/**
	 * @return the jbsbjs
	 */
	public String getJbsbjs() {
		return jbsbjs;
	}

	/**
	 * @param jbsbjs要设置的 jbsbjs
	 */
	public void setJbsbjs(String jbsbjs) {
		this.jbsbjs = jbsbjs;
	}

	/**
	 * @return the sfkfsq
	 */
	public String getSfkfsq() {
		return sfkfsq;
	}

	/**
	 * @param sfkfsq要设置的 sfkfsq
	 */
	public void setSfkfsq(String sfkfsq) {
		this.sfkfsq = sfkfsq;
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
	 * @return the search_cdmc
	 */
	public String getSearch_cdmc() {
		return search_cdmc;
	}

	/**
	 * @param searchCdmc要设置的 search_cdmc
	 */
	public void setSearch_cdmc(String searchCdmc) {
		search_cdmc = searchCdmc;
	}

	/**
	 * @return the search_rnrsmin
	 */
	public String getSearch_rnrsmin() {
		return search_rnrsmin;
	}

	/**
	 * @param searchRnrsmin要设置的 search_rnrsmin
	 */
	public void setSearch_rnrsmin(String searchRnrsmin) {
		search_rnrsmin = searchRnrsmin;
	}

	/**
	 * @return the search_rnrsmax
	 */
	public String getSearch_rnrsmax() {
		return search_rnrsmax;
	}

	/**
	 * @param searchRnrsmax要设置的 search_rnrsmax
	 */
	public void setSearch_rnrsmax(String searchRnrsmax) {
		search_rnrsmax = searchRnrsmax;
	}

	/**
	 * @return the search_yt
	 */
	public String getSearch_yt() {
		return search_yt;
	}

	/**
	 * @param searchYt要设置的 search_yt
	 */
	public void setSearch_yt(String searchYt) {
		search_yt = searchYt;
	}

	/**
	 * @return the search_dwkfsjkssj
	 */
	public String getSearch_dwkfsjkssj() {
		return search_dwkfsjkssj;
	}

	/**
	 * @param searchDwkfsjkssj要设置的 search_dwkfsjkssj
	 */
	public void setSearch_dwkfsjkssj(String searchDwkfsjkssj) {
		search_dwkfsjkssj = searchDwkfsjkssj;
	}

	/**
	 * @return the search_dwkfsjjssj
	 */
	public String getSearch_dwkfsjjssj() {
		return search_dwkfsjjssj;
	}

	/**
	 * @param searchDwkfsjjssj要设置的 search_dwkfsjjssj
	 */
	public void setSearch_dwkfsjjssj(String searchDwkfsjjssj) {
		search_dwkfsjjssj = searchDwkfsjjssj;
	}

	/**
	 * @return the search_sfkfsq
	 */
	public String getSearch_sfkfsq() {
		return search_sfkfsq;
	}

	/**
	 * @param searchSfkfsq要设置的 search_sfkfsq
	 */
	public void setSearch_sfkfsq(String searchSfkfsq) {
		search_sfkfsq = searchSfkfsq;
	}

	/**
	 * @return the formfile
	 */
	public FormFile getFormfile() {
		return formfile;
	}

	/**
	 * @param formfile要设置的 formfile
	 */
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
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

	/**
	 * @return the xysfilepath
	 */
	public String getXysfilepath() {
		return xysfilepath;
	}

	/**
	 * @param xysfilepath要设置的 xysfilepath
	 */
	public void setXysfilepath(String xysfilepath) {
		this.xysfilepath = xysfilepath;
	}
	
}
