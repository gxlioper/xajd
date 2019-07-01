/**
 * @部门:学工产品事业部
 * @日期：2017-7-18 下午05:59:35 
 */  
package com.zfsoft.xgxt.dekt.qnzyry;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 青年志愿人员form(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-7-18 下午05:59:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QnzyryForm extends ActionForm{
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 29958884530608231L;
	private String id;
	private String xh;
	private String hdid;
	private String bmzt;
	private String gs;
	private String gsshzt;
	private String[] bmzts;
	private String[] ids;
	private String bmsj;
	private String mhcx;
	private String sftj;
	private String type;
	private String shzt;
	private String gsshyj;
	private String fwjg;
	private String jbfwgs;
	private String fwjgs[];
	private String pjjg;
	private String pjbz;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private Pages pages = new Pages();
	
	private String accept;
	
	private String maxsize;
	
	private String maxcount;
	
	

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
	 * @return the hdid
	 */
	public String getHdid() {
		return hdid;
	}

	/**
	 * @param hdid要设置的 hdid
	 */
	public void setHdid(String hdid) {
		this.hdid = hdid;
	}

	/**
	 * @return the bmzt
	 */
	public String getBmzt() {
		return bmzt;
	}

	/**
	 * @param bmzt要设置的 bmzt
	 */
	public void setBmzt(String bmzt) {
		this.bmzt = bmzt;
	}

	/**
	 * @return the gs
	 */
	public String getGs() {
		return gs;
	}

	/**
	 * @param gs要设置的 gs
	 */
	public void setGs(String gs) {
		this.gs = gs;
	}

	/**
	 * @return the gsshzt
	 */
	public String getGsshzt() {
		return gsshzt;
	}

	/**
	 * @param gsshzt要设置的 gsshzt
	 */
	public void setGsshzt(String gsshzt) {
		this.gsshzt = gsshzt;
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
	 * @return the accept
	 */
	public String getAccept() {
		return accept;
	}

	/**
	 * @param accept要设置的 accept
	 */
	public void setAccept(String accept) {
		this.accept = accept;
	}

	/**
	 * @return the maxsize
	 */
	public String getMaxsize() {
		return maxsize;
	}

	/**
	 * @param maxsize要设置的 maxsize
	 */
	public void setMaxsize(String maxsize) {
		this.maxsize = maxsize;
	}

	/**
	 * @return the maxcount
	 */
	public String getMaxcount() {
		return maxcount;
	}

	/**
	 * @param maxcount要设置的 maxcount
	 */
	public void setMaxcount(String maxcount) {
		this.maxcount = maxcount;
	}

	/**
	 * @return the bmzts
	 */
	public String[] getBmzts() {
		return bmzts;
	}

	/**
	 * @param bmzts要设置的 bmzts
	 */
	public void setBmzts(String[] bmzts) {
		this.bmzts = bmzts;
	}

	/**
	 * @return the bmsj
	 */
	public String getBmsj() {
		return bmsj;
	}

	/**
	 * @param bmsj要设置的 bmsj
	 */
	public void setBmsj(String bmsj) {
		this.bmsj = bmsj;
	}

	/**
	 * @return the mhcx
	 */
	public String getMhcx() {
		return mhcx;
	}

	/**
	 * @param mhcx要设置的 mhcx
	 */
	public void setMhcx(String mhcx) {
		this.mhcx = mhcx;
	}

	/**
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}

	/**
	 * @param ids要设置的 ids
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
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
	 * @return the sftj
	 */
	public String getSftj() {
		return sftj;
	}

	/**
	 * @param sftj要设置的 sftj
	 */
	public void setSftj(String sftj) {
		this.sftj = sftj;
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
	 * @return the gsshyj
	 */
	public String getGsshyj() {
		return gsshyj;
	}

	/**
	 * @param gsshyj要设置的 gsshyj
	 */
	public void setGsshyj(String gsshyj) {
		this.gsshyj = gsshyj;
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
	 * @return the fwjg
	 */
	public String getFwjg() {
		return fwjg;
	}

	/**
	 * @param fwjg要设置的 fwjg
	 */
	public void setFwjg(String fwjg) {
		this.fwjg = fwjg;
	}

	/**
	 * @return the jbfwgs
	 */
	public String getJbfwgs() {
		return jbfwgs;
	}

	/**
	 * @param jbfwgs要设置的 jbfwgs
	 */
	public void setJbfwgs(String jbfwgs) {
		this.jbfwgs = jbfwgs;
	}

	/**
	 * @return the fwjgs
	 */
	public String[] getFwjgs() {
		return fwjgs;
	}

	/**
	 * @param fwjgs要设置的 fwjgs
	 */
	public void setFwjgs(String[] fwjgs) {
		this.fwjgs = fwjgs;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-15 下午05:51:23 
	 * @return		: the pjjg
	 */
	public String getPjjg() {
		return pjjg;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-15 下午05:51:23 
	 * @param 		：pjjg the pjjg to set
	 */
	public void setPjjg(String pjjg) {
		this.pjjg = pjjg;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-15 下午05:51:23 
	 * @return		: the pjbz
	 */
	public String getPjbz() {
		return pjbz;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-15 下午05:51:23 
	 * @param 		：pjbz the pjbz to set
	 */
	public void setPjbz(String pjbz) {
		this.pjbz = pjbz;
	}
	
	
}
