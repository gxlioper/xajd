/**
 * @部门:学工产品事业部
 * @日期：2016-7-28 上午09:13:53 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.ysjxj;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款-院设奖学金-结果维护
 * @类功能描述: TODO(结果维护) 
 * @作者： MengWei[工号:1186]
 * @时间： 2016-7-28 上午09:13:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YsjxjForm extends ActionForm{
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String juid;
	private String type;//类型
	private String xh;//学号
	private String ffsj;//发放时间
	private String xmmc;//项目名称
	private String zjly;//资金来源
	private String je;//金额
	private String bz;//备注
	private String xn;//学年
	private String xq;//学期
	private String sjly;
	private String xmlx;//项目类型
	
	
	/**
	 * @return the xmlx
	 */
	public String getXmlx() {
		return xmlx;
	}
	/**
	 * @param xmlx要设置的 xmlx
	 */
	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
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
	 * @return the juid
	 */
	public String getJuid() {
		return juid;
	}
	/**
	 * @param juid要设置的 juid
	 */
	public void setJuid(String juid) {
		this.juid = juid;
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
	 * @return the ffsj
	 */
	public String getFfsj() {
		return ffsj;
	}
	/**
	 * @param ffsj要设置的 ffsj
	 */
	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc要设置的 xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the zjly
	 */
	public String getZjly() {
		return zjly;
	}
	/**
	 * @param zjly要设置的 zjly
	 */
	public void setZjly(String zjly) {
		this.zjly = zjly;
	}
	/**
	 * @return the je
	 */
	public String getJe() {
		return je;
	}
	/**
	 * @param je要设置的 je
	 */
	public void setJe(String je) {
		this.je = je;
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
}
