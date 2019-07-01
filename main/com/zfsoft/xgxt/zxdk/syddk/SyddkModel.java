/**
 * @部门:学工产品事业部
 * @日期：2014-9-29 上午09:25:23 
 */  
package com.zfsoft.xgxt.zxdk.syddk;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 助学贷款-生源地贷款 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-9-29 上午09:25:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SyddkModel extends ActionForm {

	
	private static final long serialVersionUID = 788630279462692940L;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String id;
	private String xh;
	private String xn;
	private String dkyh;
	private String dkje;
	private String dkqx;
	private String htbh;
	private String dkkssj;
	private String hzjym;
	private String sqly;
	private String lrsj;
	private String xfyss;//学费应收数
	private String zsfyss;//住宿费应收数
	private String sjly;
	private String yhdm;
	private String[] xf;
	private String[] zsf;
	private String[] shf;
	private String[] dkxn;
	
	private String yhmc;
	
	//下载相关
	private FormFile formfile;
	private String filepath;
	
	//河北建材个性化
	private String dkcs;	//贷款次数
	private String dqrq;	//到期日期
	
	
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
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
	 * @return the dkyh
	 */
	public String getDkyh() {
		return dkyh;
	}
	/**
	 * @param dkyh要设置的 dkyh
	 */
	public void setDkyh(String dkyh) {
		this.dkyh = dkyh;
	}
	/**
	 * @return the dkje
	 */
	public String getDkje() {
		return dkje;
	}
	/**
	 * @param dkje要设置的 dkje
	 */
	public void setDkje(String dkje) {
		this.dkje = dkje;
	}
	/**
	 * @return the dkqx
	 */
	public String getDkqx() {
		return dkqx;
	}
	/**
	 * @param dkqx要设置的 dkqx
	 */
	public void setDkqx(String dkqx) {
		this.dkqx = dkqx;
	}
	/**
	 * @return the htbh
	 */
	public String getHtbh() {
		return htbh;
	}
	/**
	 * @param htbh要设置的 htbh
	 */
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	/**
	 * @return the dkkssj
	 */
	public String getDkkssj() {
		return dkkssj;
	}
	/**
	 * @param dkkssj要设置的 dkkssj
	 */
	public void setDkkssj(String dkkssj) {
		this.dkkssj = dkkssj;
	}
	/**
	 * @return the hzjym
	 */
	public String getHzjym() {
		return hzjym;
	}
	/**
	 * @param hzjym要设置的 hzjym
	 */
	public void setHzjym(String hzjym) {
		this.hzjym = hzjym;
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
	 * @return the xf
	 */
	public String[] getXf() {
		return xf;
	}
	/**
	 * @param xf要设置的 xf
	 */
	public void setXf(String[] xf) {
		this.xf = xf;
	}
	/**
	 * @return the zsf
	 */
	public String[] getZsf() {
		return zsf;
	}
	/**
	 * @param zsf要设置的 zsf
	 */
	public void setZsf(String[] zsf) {
		this.zsf = zsf;
	}
	/**
	 * @return the shf
	 */
	public String[] getShf() {
		return shf;
	}
	/**
	 * @param shf要设置的 shf
	 */
	public void setShf(String[] shf) {
		this.shf = shf;
	}
	/**
	 * @return the dkxn
	 */
	public String[] getDkxn() {
		return dkxn;
	}
	/**
	 * @return the yhmc
	 */
	public String getYhmc() {
		return yhmc;
	}
	/**
	 * @param yhmc要设置的 yhmc
	 */
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	/**
	 * @param dkxn要设置的 dkxn
	 */
	public void setDkxn(String[] dkxn) {
		this.dkxn = dkxn;
	}
	/**
	 * @return the xfyss
	 */
	public String getXfyss() {
		return xfyss;
	}
	/**
	 * @param xfyss要设置的 xfyss
	 */
	public void setXfyss(String xfyss) {
		this.xfyss = xfyss;
	}
	/**
	 * @return the zsfyss
	 */
	public String getZsfyss() {
		return zsfyss;
	}
	/**
	 * @param zsfyss要设置的 zsfyss
	 */
	public void setZsfyss(String zsfyss) {
		this.zsfyss = zsfyss;
	}
	/**
	 * @return the yhdm
	 */
	public String getYhdm() {
		return yhdm;
	}
	/**
	 * @param yhdm要设置的 yhdm
	 */
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public FormFile getFormfile() {
		return formfile;
	}
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the dkcs
	 */
	public String getDkcs() {
		return dkcs;
	}
	/**
	 * @param dkcs要设置的 dkcs
	 */
	public void setDkcs(String dkcs) {
		this.dkcs = dkcs;
	}
	/**
	 * @return the dqrq
	 */
	public String getDqrq() {
		return dqrq;
	}
	/**
	 * @param dqrq要设置的 dqrq
	 */
	public void setDqrq(String dqrq) {
		this.dqrq = dqrq;
	}
	
	
}
