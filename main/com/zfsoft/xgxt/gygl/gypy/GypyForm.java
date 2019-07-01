/**
 * @部门:学工产品事业部
 * @日期：2013-8-6 下午03:12:07 
 */  
package com.zfsoft.xgxt.gygl.gypy;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓评优
 * @类功能描述: 公寓form
 * @作者： 张昌路[工号:982]
 * @时间： 2013-8-20 下午04:34:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GypyForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String gypyid;//公寓评优id
	private String xn;//学年
	private String xqdm;//学期
	private String pylx;//评优类型
	private String pyly;//评优原因
	private String gldm;//关联代码
	private String lddm;//楼栋代码
	private String ch;//楼层
	private String qsh;//寝室号
	private String xydm;//学院代码
	private String type;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String otherFilter;
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
	 * @return the pyly
	 */
	public String getPyly() {
		return pyly;
	}
	/**
	 * @param pyly要设置的 pyly
	 */
	public void setPyly(String pyly) {
		this.pyly = pyly;
	}
	/**
	 * @return the gldm
	 */
	public String getGldm() {
		return gldm;
	}
	/**
	 * @param gldm要设置的 gldm
	 */
	public void setGldm(String gldm) {
		this.gldm = gldm;
	}
	/**
	 * @return the lc
	 */
	public String getCh() {
		return ch;
	}
	/**
	 * @param lc要设置的 lc
	 */
	public void setCh(String ch) {
		this.ch = ch;
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
	 * @return the pylx
	 */
	public String getPylx() {
		return pylx;
	}
	/**
	 * @param pylx要设置的 pylx
	 */
	public void setPylx(String pylx) {
		this.pylx = pylx;
	}
	/**
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddm要设置的 lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @param qsh要设置的 qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @param xydm要设置的 xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
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
	 * @return the gypyid
	 */
	public String getGypyid() {
		return gypyid;
	}
	/**
	 * @param gypyid要设置的 gypyid
	 */
	public void setGypyid(String gypyid) {
		this.gypyid = gypyid;
	}
	public String getOtherFilter() {
		return otherFilter;
	}
	public void setOtherFilter(String otherFilter) {
		this.otherFilter = otherFilter;
	}
}
