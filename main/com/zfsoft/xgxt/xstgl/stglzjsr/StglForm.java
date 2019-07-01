package com.zfsoft.xgxt.xstgl.stglzjsr;

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
public class StglForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel(); //高级查询
	private Pages pages = new Pages(); // 分页
	private ExportModel exportModel = new ExportModel(); //自定义导出
	
	private String id;
	private String bh;//编号
	private String stmc;//社团名称
	private String stlb;//社团类别
	private String sz;//社长
	private String cwfzr;//财务负责人
	private String zdls;//指导老师
	private String yxzt;//有效状态 1有效 0无效
	private String filepath;//附件路径
	private String bz;//备注
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
	 * @return the bh
	 */
	public String getBh() {
		return bh;
	}
	/**
	 * @param bh要设置的 bh
	 */
	public void setBh(String bh) {
		this.bh = bh;
	}
	/**
	 * @return the stmc
	 */
	public String getStmc() {
		return stmc;
	}
	/**
	 * @param stmc要设置的 stmc
	 */
	public void setStmc(String stmc) {
		this.stmc = stmc;
	}
	/**
	 * @return the stlb
	 */
	public String getStlb() {
		return stlb;
	}
	/**
	 * @param stlb要设置的 stlb
	 */
	public void setStlb(String stlb) {
		this.stlb = stlb;
	}
	/**
	 * @return the sz
	 */
	public String getSz() {
		return sz;
	}
	/**
	 * @param sz要设置的 sz
	 */
	public void setSz(String sz) {
		this.sz = sz;
	}
	/**
	 * @return the cwfzr
	 */
	public String getCwfzr() {
		return cwfzr;
	}
	/**
	 * @param cwfzr要设置的 cwfzr
	 */
	public void setCwfzr(String cwfzr) {
		this.cwfzr = cwfzr;
	}
	/**
	 * @return the zdls
	 */
	public String getZdls() {
		return zdls;
	}
	/**
	 * @param zdls要设置的 zdls
	 */
	public void setZdls(String zdls) {
		this.zdls = zdls;
	}
	/**
	 * @return the yxzt
	 */
	public String getYxzt() {
		return yxzt;
	}
	/**
	 * @param yxzt要设置的 yxzt
	 */
	public void setYxzt(String yxzt) {
		this.yxzt = yxzt;
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
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepath要设置的 filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
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
	
}
