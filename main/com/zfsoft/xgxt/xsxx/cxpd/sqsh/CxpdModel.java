/**
 * @部门:学工产品事业部
 * @日期：2014-11-12 上午09:31:56 
 */  
package com.zfsoft.xgxt.xsxx.cxpd.sqsh;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 操行评定-申请审核 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2015-1-14 下午03:55:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class CxpdModel extends SuperAuditModel{

	private static final long serialVersionUID = -5951870429763694581L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String id;
	private String xh;
	private String xn;
	private String xq;
	private String cxdj;
	private String cxpy;
	private String bzr;
	
	private String shzt;
	private String splcid;
	private String sqsj;
	
	private String cxdjmc;
	private String xqmc;
	
	
	
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
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
	 * @return the cxdj
	 */
	public String getCxdj() {
		return cxdj;
	}
	/**
	 * @param cxdj要设置的 cxdj
	 */
	public void setCxdj(String cxdj) {
		this.cxdj = cxdj;
	}
	/**
	 * @return the cxpy
	 */
	public String getCxpy() {
		return cxpy;
	}
	/**
	 * @param cxpy要设置的 cxpy
	 */
	public void setCxpy(String cxpy) {
		this.cxpy = cxpy;
	}
	/**
	 * @return the bzr
	 */
	public String getBzr() {
		return bzr;
	}
	/**
	 * @param bzr要设置的 bzr
	 */
	public void setBzr(String bzr) {
		this.bzr = bzr;
	}
	/**
	 * @return the cxdjmc
	 */
	public String getCxdjmc() {
		return cxdjmc;
	}
	/**
	 * @param cxdjmc要设置的 cxdjmc
	 */
	public void setCxdjmc(String cxdjmc) {
		this.cxdjmc = cxdjmc;
	}
	
	
}
