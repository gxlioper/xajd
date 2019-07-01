/**
 * @部门:学工产品事业部
 * @日期：2016-12-26 上午09:40:36 
 */  
package xsgzgl.qgzx.zjdx.tjcx;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-12-26 上午09:40:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjcxForm extends ActionForm {
	private String id;
	private String nd;
	private String bmlb;
	private String yf;
	private String xh;
	private String xm;
	private String ffndyf;
	private String yrdwdm;
	private String yrdwmc;
	private String xqdm;
	private String gwlbdm;
	private String gwxzdm;
	private String gss;
	private String bcje;
	private String gznr;
	private String bz;
	private String sftj;
	private String lrr;
	private String lrsj;
	private String type;
	private FormFile drmb;
	private String filepath;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
	private static final long serialVersionUID = 1L;
	/**
	 * @return the yrdwmc
	 */
	public String getYrdwmc() {
		return yrdwmc;
	}
	/**
	 * @param yrdwmc要设置的 yrdwmc
	 */
	public void setYrdwmc(String yrdwmc) {
		this.yrdwmc = yrdwmc;
	}
	/**
	 * @return the bmlb
	 */
	public String getBmlb() {
		return bmlb;
	}
	/**
	 * @param bmlb要设置的 bmlb
	 */
	public void setBmlb(String bmlb) {
		this.bmlb = bmlb;
	}
	
	/**
	 * @return the nd
	 */
	public String getNd() {
		return nd;
	}
	/**
	 * @param nd要设置的 nd
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}
	/**
	 * @return the yf
	 */
	public String getYf() {
		return yf;
	}
	/**
	 * @param yf要设置的 yf
	 */
	public void setYf(String yf) {
		this.yf = yf;
	}
	
	/**
	 * @return the drmb
	 */
	public FormFile getDrmb() {
		return drmb;
	}
	/**
	 * @param drmb要设置的 drmb
	 */
	public void setDrmb(FormFile drmb) {
		this.drmb = drmb;
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
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the ffndyf
	 */
	public String getFfndyf() {
		return ffndyf;
	}
	/**
	 * @param ffndyf要设置的 ffndyf
	 */
	public void setFfndyf(String ffndyf) {
		this.ffndyf = ffndyf;
	}
	/**
	 * @return the yrdwdm
	 */
	public String getYrdwdm() {
		return yrdwdm;
	}
	/**
	 * @param yrdwdm要设置的 yrdwdm
	 */
	public void setYrdwdm(String yrdwdm) {
		this.yrdwdm = yrdwdm;
	}
	/**
	 * @return the xqdm
	 */
	public String getXqdm() {
		return xqdm;
	}
	/**
	 * @param xqdm要设置的 xqdm
	 */
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	/**
	 * @return the gwlbdm
	 */
	public String getGwlbdm() {
		return gwlbdm;
	}
	/**
	 * @param gwlbdm要设置的 gwlbdm
	 */
	public void setGwlbdm(String gwlbdm) {
		this.gwlbdm = gwlbdm;
	}
	/**
	 * @return the gwxzdm
	 */
	public String getGwxzdm() {
		return gwxzdm;
	}
	/**
	 * @param gwxzdm要设置的 gwxzdm
	 */
	public void setGwxzdm(String gwxzdm) {
		this.gwxzdm = gwxzdm;
	}
	/**
	 * @return the gss
	 */
	public String getGss() {
		return gss;
	}
	/**
	 * @param gss要设置的 gss
	 */
	public void setGss(String gss) {
		this.gss = gss;
	}
	/**
	 * @return the bcje
	 */
	public String getBcje() {
		return bcje;
	}
	/**
	 * @param bcje要设置的 bcje
	 */
	public void setBcje(String bcje) {
		this.bcje = bcje;
	}
	/**
	 * @return the gznr
	 */
	public String getGznr() {
		return gznr;
	}
	/**
	 * @param gznr要设置的 gznr
	 */
	public void setGznr(String gznr) {
		this.gznr = gznr;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
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
	 * @return the lrr
	 */
	public String getLrr() {
		return lrr;
	}
	/**
	 * @param lrr要设置的 lrr
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	/**
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @param lrsj要设置的 lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
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

}
