/**
 * @部门:学工产品事业部
 * @日期：2016-1-21 下午02:37:27 
 */  
package com.zfsoft.xgxt.rcsw.sjgl.hqsj;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-1-21 下午02:37:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HqsjForm extends ActionForm{
	//导出模型
	ExportModel exportModel = new ExportModel();
	//高级搜搜
	SearchModel searchModel = new SearchModel();
	// 分页
	Pages pages = new Pages();
	
	private String id;
	private String xh;
	private String xn;
	private String xq;
	private String qszlccs; //寝室脏乱差次数
	private String yxqscs; //优秀寝室次数
	private String yxqsjf; //优秀寝室加分
	private String qsz; //是否寝室长
	private String sywjdqcs; //使用违禁电器次数
	private String ybgscs; //夜不归宿次数
	private String type; //操作类型
	private String xqmc; //学期名称
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
	 * @return the qszlccs
	 */
	public String getQszlccs() {
		return qszlccs;
	}
	/**
	 * @param qszlccs要设置的 qszlccs
	 */
	public void setQszlccs(String qszlccs) {
		this.qszlccs = qszlccs;
	}
	/**
	 * @return the yxqscs
	 */
	public String getYxqscs() {
		return yxqscs;
	}
	/**
	 * @param yxqscs要设置的 yxqscs
	 */
	public void setYxqscs(String yxqscs) {
		this.yxqscs = yxqscs;
	}
	/**
	 * @return the yxqsjf
	 */
	public String getYxqsjf() {
		return yxqsjf;
	}
	/**
	 * @param yxqsjf要设置的 yxqsjf
	 */
	public void setYxqsjf(String yxqsjf) {
		this.yxqsjf = yxqsjf;
	}
	/**
	 * @return the qsz
	 */
	public String getQsz() {
		return qsz;
	}
	/**
	 * @param qsz要设置的 qsz
	 */
	public void setQsz(String qsz) {
		this.qsz = qsz;
	}
	/**
	 * @return the sywjdqcs
	 */
	public String getSywjdqcs() {
		return sywjdqcs;
	}
	/**
	 * @param sywjdqcs要设置的 sywjdqcs
	 */
	public void setSywjdqcs(String sywjdqcs) {
		this.sywjdqcs = sywjdqcs;
	}
	/**
	 * @return the ybgscs
	 */
	public String getYbgscs() {
		return ybgscs;
	}
	/**
	 * @param ybgscs要设置的 ybgscs
	 */
	public void setYbgscs(String ybgscs) {
		this.ybgscs = ybgscs;
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
	
	
	
}
