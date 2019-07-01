/**
 * @部门:学工产品事业部
 * @日期：2015-5-14 下午01:45:59 
 */  
package com.zfsoft.xgxt.xsxx.tsxsgl.tsxswh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-5-14 下午01:45:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsxsglForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	private ExportModel exportModel = new ExportModel();

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	  
	private String id; // 主键

	private String xh; //学号
	
	private String xslxdm; //特殊学生类型
	
	private String gzzt; //关注状态
	
	private String qksm; // 情况说明
	
	private String bz; // 备注

	private String lrsj; //录入时间
	
	private String  sjzt; //数据状态0失效1正常
	
	private String clcs;//处理措施
	
	private String lrr;//录入人
	private String xslxmc;
	
	private String gzztmc;
	
	private String type;
	
	

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
	 * @return the xslxdm
	 */
	public String getXslxdm() {
		return xslxdm;
	}

	/**
	 * @param xslxdm要设置的 xslxdm
	 */
	public void setXslxdm(String xslxdm) {
		this.xslxdm = xslxdm;
	}

	/**
	 * @return the gzzt
	 */
	public String getGzzt() {
		return gzzt;
	}

	/**
	 * @param gzzt要设置的 gzzt
	 */
	public void setGzzt(String gzzt) {
		this.gzzt = gzzt;
	}

	/**
	 * @return the qksm
	 */
	public String getQksm() {
		return qksm;
	}

	/**
	 * @param qksm要设置的 qksm
	 */
	public void setQksm(String qksm) {
		this.qksm = qksm;
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
	 * @return the sjzt
	 */
	public String getSjzt() {
		return sjzt;
	}

	/**
	 * @param sjzt要设置的 sjzt
	 */
	public void setSjzt(String sjzt) {
		this.sjzt = sjzt;
	}

	/**
	 * @return the clcs
	 */
	public String getClcs() {
		return clcs;
	}

	/**
	 * @param clcs要设置的 clcs
	 */
	public void setClcs(String clcs) {
		this.clcs = clcs;
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
	 * @return the xslxmc
	 */
	public String getXslxmc() {
		return xslxmc;
	}

	/**
	 * @param xslxmc要设置的 xslxmc
	 */
	public void setXslxmc(String xslxmc) {
		this.xslxmc = xslxmc;
	}

	/**
	 * @return the gzztmc
	 */
	public String getGzztmc() {
		return gzztmc;
	}

	/**
	 * @param gzztmc要设置的 gzztmc
	 */
	public void setGzztmc(String gzztmc) {
		this.gzztmc = gzztmc;
	}
	
	
	
	
	

}
