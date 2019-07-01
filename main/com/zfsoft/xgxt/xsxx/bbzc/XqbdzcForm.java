/**
 * @部门:学工产品事业部
 * @日期：2013-12-16 上午10:00:59 
 */  
package com.zfsoft.xgxt.xsxx.bbzc;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学期报到注册 华师大用
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-16 上午10:00:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XqbdzcForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	
	
	private String id; //标识符
	
	private String xh;	//学号
	
	private String xn;	//学年
	
	private String xq; //学期
	
	private String xqmc; //学期名称
	
	private String zczt; //注册状态
	
	private String zcsj;//注册时间
	
	private String zcr;//注册人
	
	private String wbdyy;//未报到原因
	private String yjbdsj;//预计报到时间
	private String wbdlbdm;//未报到类别代码

	private String plIds;
	private String rownum_t;
	private String rownum_wx;
	private String rownum_w;
	private String rownum_x;
	private String rownum_y;
	private String searchXn;
	private String searchXq;
	
	/**
	 * @return the yjbdsj
	 */
	public String getYjbdsj() {
		return yjbdsj;
	}

	/**
	 * @param yjbdsj要设置的 yjbdsj
	 */
	public void setYjbdsj(String yjbdsj) {
		this.yjbdsj = yjbdsj;
	}

	/**
	 * @return the wbdlbdm
	 */
	public String getWbdlbdm() {
		return wbdlbdm;
	}

	/**
	 * @param wbdlbdm要设置的 wbdlbdm
	 */
	public void setWbdlbdm(String wbdlbdm) {
		this.wbdlbdm = wbdlbdm;
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
	 * @return the zczt
	 */
	public String getZczt() {
		return zczt;
	}

	/**
	 * @param zczt要设置的 zczt
	 */
	public void setZczt(String zczt) {
		this.zczt = zczt;
	}

	/**
	 * @return the zcsj
	 */
	public String getZcsj() {
		return zcsj;
	}

	/**
	 * @param zcsj要设置的 zcsj
	 */
	public void setZcsj(String zcsj) {
		this.zcsj = zcsj;
	}

	/**
	 * @return the zcr
	 */
	public String getZcr() {
		return zcr;
	}

	/**
	 * @param zcr要设置的 zcr
	 */
	public void setZcr(String zcr) {
		this.zcr = zcr;
	}

	/**
	 * @return the searchXn
	 */
	public String getSearchXn() {
		return searchXn;
	}

	/**
	 * @param searchXn要设置的 searchXn
	 */
	public void setSearchXn(String searchXn) {
		this.searchXn = searchXn;
	}

	/**
	 * @return the searchXq
	 */
	public String getSearchXq() {
		return searchXq;
	}

	/**
	 * @param searchXq要设置的 searchXq
	 */
	public void setSearchXq(String searchXq) {
		this.searchXq = searchXq;
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

	/**
	 * @return the plIds
	 */
	public String getPlIds() {
		return plIds;
	}

	/**
	 * @param plIds要设置的 plIds
	 */
	public void setPlIds(String plIds) {
		this.plIds = plIds;
	}

	

	

	/**
	 * @return the rownum_w
	 */
	public String getRownum_w() {
		return rownum_w;
	}

	/**
	 * @param rownumW要设置的 rownum_w
	 */
	public void setRownum_w(String rownumW) {
		rownum_w = rownumW;
	}

	/**
	 * @return the rownum_y
	 */
	public String getRownum_y() {
		return rownum_y;
	}

	/**
	 * @param rownumY要设置的 rownum_y
	 */
	public void setRownum_y(String rownumY) {
		rownum_y = rownumY;
	}

	/**
	 * @return the rownum_t
	 */
	public String getRownum_t() {
		return rownum_t;
	}

	/**
	 * @param rownumT要设置的 rownum_t
	 */
	public void setRownum_t(String rownumT) {
		rownum_t = rownumT;
	}

	public String getWbdyy() {
		return wbdyy;
	}

	public void setWbdyy(String wbdyy) {
		this.wbdyy = wbdyy;
	}

	/**
	 * @param rownumX要设置的 rownum_x
	 */
	public void setRownum_x(String rownumX) {
		rownum_x = rownumX;
	}

	/**
	 * @return the rownum_x
	 */
	public String getRownum_x() {
		return rownum_x;
	}

	/**
	 * @param rownumWX要设置的 rownum_wx
	 */
	public void setRownum_wx(String rownumWX) {
		rownum_wx = rownumWX;
	}

	/**
	 * @return the rownum_wt
	 */
	public String getRownum_wx() {
		return rownum_wx;
	}
	


}
