/**
 * @部门:学工产品事业部
 * @日期：2017-7-12 下午06:39:13 
 */  
package com.zfsoft.xgxt.dekt.qnzyhd;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-7-12 下午06:39:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QnzyhdForm extends ActionForm{	
	private static final long serialVersionUID = 1L;
	
	private String hdid;
	private String hdmc;
	private String fwlx;
	private String hddd;
	private String fwdx;
	private String xdrs;
	private String hdkssj;
	private String hdjssj;
	private String hdfzr;
	private String hdfbr;
	private String hdfzrlxfs;
	private String zzbm;
	private String fjpath;
	private String hdxq;
	private String fbzt;
	private String shzt;
	private Pages pages = new Pages();
	private String mhcx;
	private String type;
	private String oldPath;
	private String lastPath;
	private String gsshzt;
	private String bmjzsj;
	private String jbfwgs;
	private String[] xydms;
	private String shyj;
	private String[] ids;
	
	private FormFile file;
	
	private String accept;
	
	private String maxsize;
	
	private String maxcount;
	
	private SearchModel searchModel = new SearchModel();
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
	 * @return the hdmc
	 */
	public String getHdmc() {
		return hdmc;
	}
	/**
	 * @param hdmc要设置的 hdmc
	 */
	public void setHdmc(String hdmc) {
		this.hdmc = hdmc;
	}
	/**
	 * @return the fwlx
	 */
	public String getFwlx() {
		return fwlx;
	}
	/**
	 * @param fwlx要设置的 fwlx
	 */
	public void setFwlx(String fwlx) {
		this.fwlx = fwlx;
	}
	/**
	 * @return the hddd
	 */
	public String getHddd() {
		return hddd;
	}
	/**
	 * @param hddd要设置的 hddd
	 */
	public void setHddd(String hddd) {
		this.hddd = hddd;
	}
	/**
	 * @return the fwdx
	 */
	public String getFwdx() {
		return fwdx;
	}
	/**
	 * @param fwdx要设置的 fwdx
	 */
	public void setFwdx(String fwdx) {
		this.fwdx = fwdx;
	}
	/**
	 * @return the xdrs
	 */
	public String getXdrs() {
		return xdrs;
	}
	/**
	 * @param xdrs要设置的 xdrs
	 */
	public void setXdrs(String xdrs) {
		this.xdrs = xdrs;
	}
	/**
	 * @return the hdkssj
	 */
	public String getHdkssj() {
		return hdkssj;
	}
	/**
	 * @param hdkssj要设置的 hdkssj
	 */
	public void setHdkssj(String hdkssj) {
		this.hdkssj = hdkssj;
	}
	/**
	 * @return the hdjssj
	 */
	public String getHdjssj() {
		return hdjssj;
	}
	/**
	 * @param hdjssj要设置的 hdjssj
	 */
	public void setHdjssj(String hdjssj) {
		this.hdjssj = hdjssj;
	}
	/**
	 * @return the hdfzr
	 */
	public String getHdfzr() {
		return hdfzr;
	}
	/**
	 * @param hdfzr要设置的 hdfzr
	 */
	public void setHdfzr(String hdfzr) {
		this.hdfzr = hdfzr;
	}
	/**
	 * @return the hdfbr
	 */
	public String getHdfbr() {
		return hdfbr;
	}
	/**
	 * @param hdfbr要设置的 hdfbr
	 */
	public void setHdfbr(String hdfbr) {
		this.hdfbr = hdfbr;
	}
	/**
	 * @return the hdfzrlxfs
	 */
	public String getHdfzrlxfs() {
		return hdfzrlxfs;
	}
	/**
	 * @param hdfzrlxfs要设置的 hdfzrlxfs
	 */
	public void setHdfzrlxfs(String hdfzrlxfs) {
		this.hdfzrlxfs = hdfzrlxfs;
	}
	/**
	 * @return the zzbm
	 */
	public String getZzbm() {
		return zzbm;
	}
	/**
	 * @param zzbm要设置的 zzbm
	 */
	public void setZzbm(String zzbm) {
		this.zzbm = zzbm;
	}
	/**
	 * @return the fjpath
	 */
	public String getFjpath() {
		return fjpath;
	}
	/**
	 * @param fjpath要设置的 fjpath
	 */
	public void setFjpath(String fjpath) {
		this.fjpath = fjpath;
	}
	/**
	 * @return the hdxq
	 */
	public String getHdxq() {
		return hdxq;
	}
	/**
	 * @param hdxq要设置的 hdxq
	 */
	public void setHdxq(String hdxq) {
		this.hdxq = hdxq;
	}
	/**
	 * @return the fbzt
	 */
	public String getFbzt() {
		return fbzt;
	}
	/**
	 * @param fbzt要设置的 fbzt
	 */
	public void setFbzt(String fbzt) {
		this.fbzt = fbzt;
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
	 * @return the file
	 */
	public FormFile getFile() {
		return file;
	}
	/**
	 * @param file要设置的 file
	 */
	public void setFile(FormFile file) {
		this.file = file;
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
	 * @return the oldPath
	 */
	public String getOldPath() {
		return oldPath;
	}
	/**
	 * @param oldPath要设置的 oldPath
	 */
	public void setOldPath(String oldPath) {
		this.oldPath = oldPath;
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
	 * @return the lastPath
	 */
	public String getLastPath() {
		return lastPath;
	}
	/**
	 * @param lastPath要设置的 lastPath
	 */
	public void setLastPath(String lastPath) {
		this.lastPath = lastPath;
	}
	/**
	 * @return the bmjzsj
	 */
	public String getBmjzsj() {
		return bmjzsj;
	}
	/**
	 * @param bmjzsj要设置的 bmjzsj
	 */
	public void setBmjzsj(String bmjzsj) {
		this.bmjzsj = bmjzsj;
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
	 * @return the xydms
	 */
	public String[] getXydms() {
		return xydms;
	}
	/**
	 * @param xydms要设置的 xydms
	 */
	public void setXydms(String[] xydms) {
		this.xydms = xydms;
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
	
	
	
	
}
