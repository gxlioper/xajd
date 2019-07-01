/**
 * @部门:学工产品事业部
 * @日期：2016-4-19 上午11:45:44 
 */  
package com.zfsoft.xgxt.gygl.ggwpgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-4-19 上午11:45:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GgwpjyForm extends ActionForm{
	private String id;
	private String xh;
	private String fldm;
	private String sbdm;
	private String jysj;
	private String ghzt;
	private String ghsj;
	private String bz;
	private String jyyy;
	private String jbr;
	private String ghjbr;
	private String fldmArr[];
	private String sbdmArr[];
	private String jysjArr[];
	private String bzArr[];
	private String xm;
	private String jbrxm;
	private String sbmc;
	private String flmc;
	private String ghztmc;
	private String ghjbrxm;
	private String ghbz;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
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
	 * @return the ghzt
	 */
	public String getGhzt() {
		return ghzt;
	}
	/**
	 * @param ghzt要设置的 ghzt
	 */
	public void setGhzt(String ghzt) {
		this.ghzt = ghzt;
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
	 * @return the ghztmc
	 */
	public String getGhztmc() {
		return ghztmc;
	}
	/**
	 * @param ghztmc要设置的 ghztmc
	 */
	public void setGhztmc(String ghztmc) {
		this.ghztmc = ghztmc;
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
	
	
	
	
}
