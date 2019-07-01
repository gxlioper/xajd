/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:41:01 
 */  
package com.zfsoft.xgxt.rcsw.sbgl.jyjl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @类功能描述: 设备分类维护
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:41:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JyjlModel extends ActionForm {

	
	private static final long serialVersionUID = 7153147718443427966L;
	
	private String id;
	private String zgh;
	private String fldm;
	private String sbdm;
	private String jysj;
	private String ghsj;
	private String bz;
	private String jyyy;
	private String jbr;
	
	private String ghbz;
	private String ghjbr;
	private String jbrxm;
	private String ghjbrxm;
	
	private String flmc;
	private String sbmc;
	
	private ExportModel exportModel = new ExportModel();
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	
	private String[] fldmArr;
	private String[] sbdmArr;
	private String[] bzArr;
	private String[] jysjArr;
	
	
	
	/**
	 * @return the flmc
	 */
	public String getFlmc() {
		return flmc;
	}
	/**
	 * @param flmc要设置的 flmc
	 */
	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}
	/**
	 * @return the sbmc
	 */
	public String getSbmc() {
		return sbmc;
	}
	/**
	 * @param sbmc要设置的 sbmc
	 */
	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}
	/**
	 * @return the jbrxm
	 */
	public String getJbrxm() {
		return jbrxm;
	}
	/**
	 * @param jbrxm要设置的 jbrxm
	 */
	public void setJbrxm(String jbrxm) {
		this.jbrxm = jbrxm;
	}
	/**
	 * @return the ghjbrxm
	 */
	public String getGhjbrxm() {
		return ghjbrxm;
	}
	/**
	 * @param ghjbrxm要设置的 ghjbrxm
	 */
	public void setGhjbrxm(String ghjbrxm) {
		this.ghjbrxm = ghjbrxm;
	}
	/**
	 * @return the ghbz
	 */
	public String getGhbz() {
		return ghbz;
	}
	/**
	 * @param ghbz要设置的 ghbz
	 */
	public void setGhbz(String ghbz) {
		this.ghbz = ghbz;
	}
	/**
	 * @return the ghjbr
	 */
	public String getGhjbr() {
		return ghjbr;
	}
	/**
	 * @param ghjbr要设置的 ghjbr
	 */
	public void setGhjbr(String ghjbr) {
		this.ghjbr = ghjbr;
	}
	/**
	 * @return the fldmArr
	 */
	public String[] getFldmArr() {
		return fldmArr;
	}
	/**
	 * @param fldmArr要设置的 fldmArr
	 */
	public void setFldmArr(String[] fldmArr) {
		this.fldmArr = fldmArr;
	}
	/**
	 * @return the sbdmArr
	 */
	public String[] getSbdmArr() {
		return sbdmArr;
	}
	/**
	 * @param sbdmArr要设置的 sbdmArr
	 */
	public void setSbdmArr(String[] sbdmArr) {
		this.sbdmArr = sbdmArr;
	}
	/**
	 * @return the bzArr
	 */
	public String[] getBzArr() {
		return bzArr;
	}
	/**
	 * @param bzArr要设置的 bzArr
	 */
	public void setBzArr(String[] bzArr) {
		this.bzArr = bzArr;
	}
	/**
	 * @return the jysjArr
	 */
	public String[] getJysjArr() {
		return jysjArr;
	}
	/**
	 * @param jysjArr要设置的 jysjArr
	 */
	public void setJysjArr(String[] jysjArr) {
		this.jysjArr = jysjArr;
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
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zgh要设置的 zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	/**
	 * @return the fldm
	 */
	public String getFldm() {
		return fldm;
	}
	/**
	 * @param fldm要设置的 fldm
	 */
	public void setFldm(String fldm) {
		this.fldm = fldm;
	}
	/**
	 * @return the sbdm
	 */
	public String getSbdm() {
		return sbdm;
	}
	/**
	 * @param sbdm要设置的 sbdm
	 */
	public void setSbdm(String sbdm) {
		this.sbdm = sbdm;
	}
	/**
	 * @return the jysj
	 */
	public String getJysj() {
		return jysj;
	}
	/**
	 * @param jysj要设置的 jysj
	 */
	public void setJysj(String jysj) {
		this.jysj = jysj;
	}
	/**
	 * @return the ghsj
	 */
	public String getGhsj() {
		return ghsj;
	}
	/**
	 * @param ghsj要设置的 ghsj
	 */
	public void setGhsj(String ghsj) {
		this.ghsj = ghsj;
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
	 * @return the jyyy
	 */
	public String getJyyy() {
		return jyyy;
	}
	/**
	 * @param jyyy要设置的 jyyy
	 */
	public void setJyyy(String jyyy) {
		this.jyyy = jyyy;
	}
	/**
	 * @return the jbr
	 */
	public String getJbr() {
		return jbr;
	}
	/**
	 * @param jbr要设置的 jbr
	 */
	public void setJbr(String jbr) {
		this.jbr = jbr;
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
