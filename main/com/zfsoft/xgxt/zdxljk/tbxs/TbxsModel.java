/**
 * @部门:学工产品事业部
 * @日期：2015-2-11 上午09:11:04 
 */  
package com.zfsoft.xgxt.zdxljk.tbxs;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @类功能描述: 浙大心理健康--特别关心学生 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-2-11 上午09:11:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TbxsModel extends ActionForm{

	
	private static final long serialVersionUID = -7826057325474125444L;

	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String xh;
	private String[] thsjArr;
	private String[] gxlxArr;
	private String gzlx;
	private String[] gxsjArr;
	private String[] gzsjArr;
	private String[] thnrArr;
	private String[] cljgArr;
	private String zxzt;
	private String[] ftrArr;
	private String[] qxyyArr;
	private String qxsj;
	private String qxyy;
	private String czsj;
	private String[] canUpdateArr;
	
	
	
	/**
	 * @return the qxsj
	 */
	public String getQxsj() {
		return qxsj;
	}
	/**
	 * @param qxsj要设置的 qxsj
	 */
	public void setQxsj(String qxsj) {
		this.qxsj = qxsj;
	}
	/**
	 * @return the qxyy
	 */
	public String getQxyy() {
		return qxyy;
	}
	/**
	 * @param qxyy要设置的 qxyy
	 */
	public void setQxyy(String qxyy) {
		this.qxyy = qxyy;
	}
	/**
	 * @return the zxzt
	 */
	public String getZxzt() {
		return zxzt;
	}
	/**
	 * @param zxzt要设置的 zxzt
	 */
	public void setZxzt(String zxzt) {
		this.zxzt = zxzt;
	}
	/**
	 * @return the thsjArr
	 */
	public String[] getThsjArr() {
		return thsjArr;
	}
	/**
	 * @param thsjArr要设置的 thsjArr
	 */
	public void setThsjArr(String[] thsjArr) {
		this.thsjArr = thsjArr;
	}
	/**
	 * @return the gxlxArr
	 */
	public String[] getGxlxArr() {
		return gxlxArr;
	}
	/**
	 * @param gxlxArr要设置的 gxlxArr
	 */
	public void setGxlxArr(String[] gxlxArr) {
		this.gxlxArr = gxlxArr;
	}
	
	public String getGzlx() {
		return gzlx;
	}
	public void setGzlx(String gzlx) {
		this.gzlx = gzlx;
	}
	/**
	 * @return the gxsjArr
	 */
	public String[] getGxsjArr() {
		return gxsjArr;
	}
	/**
	 * @param gxsjArr要设置的 gxsjArr
	 */
	public void setGxsjArr(String[] gxsjArr) {
		this.gxsjArr = gxsjArr;
	}
	/**
	 * @return the gzsjArr
	 */
	public String[] getGzsjArr() {
		return gzsjArr;
	}
	/**
	 * @param gzsjArr要设置的 gzsjArr
	 */
	public void setGzsjArr(String[] gzsjArr) {
		this.gzsjArr = gzsjArr;
	}
	/**
	 * @return the thnrArr
	 */
	public String[] getThnrArr() {
		return thnrArr;
	}
	/**
	 * @param thnrArr要设置的 thnrArr
	 */
	public void setThnrArr(String[] thnrArr) {
		this.thnrArr = thnrArr;
	}
	/**
	 * @return the cljgArr
	 */
	public String[] getCljgArr() {
		return cljgArr;
	}
	/**
	 * @param cljgArr要设置的 cljgArr
	 */
	public void setCljgArr(String[] cljgArr) {
		this.cljgArr = cljgArr;
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
	public String[] getFtrArr() {
		return ftrArr;
	}
	public void setFtrArr(String[] ftrArr) {
		this.ftrArr = ftrArr;
	}
	public String[] getQxyyArr() {
		return qxyyArr;
	}
	public void setQxyyArr(String[] qxyyArr) {
		this.qxyyArr = qxyyArr;
	}
	public String getCzsj() {
		return czsj;
	}
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}
	public String[] getCanUpdateArr() {
		return canUpdateArr;
	}
	public void setCanUpdateArr(String[] canUpdateArr) {
		this.canUpdateArr = canUpdateArr;
	}
	
	
	
}
