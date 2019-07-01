package com.zfsoft.xgxt.qgzx.jtff;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @系统名称：学生工作管理系统
 * @模块名称：勤工助学-津贴发放
 * @类功能描述：
 * @作者：卓耐[工号:1391]
 * @时间：2017年4月20日
 * @版本：V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class JtffForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel(); //高级查询
	private Pages pages = new Pages(); // 分页
	private ExportModel exportModel = new ExportModel(); //自定义导出
	
	private String id;
	private String xh;//学号
	private String sj;//时间
	private String yrbm;//用人部门
	private String bz;//备注
	private String dj;//等级
	private String zd1;//字段1
	private String zd2;//字段2
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
	 * @return the sj
	 */
	public String getSj() {
		return sj;
	}
	/**
	 * @param sj要设置的 sj
	 */
	public void setSj(String sj) {
		this.sj = sj;
	}
	/**
	 * @return the yrbm
	 */
	public String getYrbm() {
		return yrbm;
	}
	/**
	 * @param yrbm要设置的 yrbm
	 */
	public void setYrbm(String yrbm) {
		this.yrbm = yrbm;
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
	 * @return the dj
	 */
	public String getDj() {
		return dj;
	}
	/**
	 * @param dj要设置的 dj
	 */
	public void setDj(String dj) {
		this.dj = dj;
	}
	/**
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}
	/**
	 * @param zd1要设置的 zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	/**
	 * @return the zd2
	 */
	public String getZd2() {
		return zd2;
	}
	/**
	 * @param zd2要设置的 zd2
	 */
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}
	
	
	
}
