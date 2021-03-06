/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午10:10:29 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午10:10:29
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglGzpzTjXxForm  extends ActionForm {
	private String pk;
	private String pzgzid;// varchar2(100) n 配置规则id
	private String tjgzid;// varchar2(20) n 关联条件规则id
	private String tjszzd;// varchar2(100) n 条件设置字段
	private String sx;// number y 顺序
	private String xxz;// varchar2(300) y 选项值
	private String wsbl;// varchar2(1) y 位数补零
	private String ylz;// varchar2(300) y 预览值取值
	private String qsz;//起始值
	private String sfkxg;// varchar2(1) y 是否可修改
	private String ppbmzd;// varchar2(300) y 匹配表名字段

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type要设置的
	 *            type
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
	 * @param pages要设置的
	 *            pages
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
	 * @param searchModel要设置的
	 *            searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	/**
	 * @return the pzgzid
	 */
	public String getPzgzid() {
		return pzgzid;
	}

	/**
	 * @param pzgzid要设置的
	 *            pzgzid
	 */
	public void setPzgzid(String pzgzid) {
		this.pzgzid = pzgzid;
	}

	/**
	 * @return the tjgzid
	 */
	public String getTjgzid() {
		return tjgzid;
	}

	/**
	 * @param tjgzid要设置的
	 *            tjgzid
	 */
	public void setTjgzid(String tjgzid) {
		this.tjgzid = tjgzid;
	}

	/**
	 * @return the tjszzd
	 */
	public String getTjszzd() {
		return tjszzd;
	}

	/**
	 * @param tjszzd要设置的
	 *            tjszzd
	 */
	public void setTjszzd(String tjszzd) {
		this.tjszzd = tjszzd;
	}

	/**
	 * @return the sx
	 */
	public String getSx() {
		return sx;
	}

	/**
	 * @param sx要设置的
	 *            sx
	 */
	public void setSx(String sx) {
		this.sx = sx;
	}

	/**
	 * @return the xxz
	 */
	public String getXxz() {
		return xxz;
	}

	/**
	 * @param xxz要设置的
	 *            xxz
	 */
	public void setXxz(String xxz) {
		this.xxz = xxz;
	}

	/**
	 * @return the wsbl
	 */
	public String getWsbl() {
		return wsbl;
	}

	/**
	 * @param wsbl要设置的
	 *            wsbl
	 */
	public void setWsbl(String wsbl) {
		this.wsbl = wsbl;
	}

	/**
	 * @return the ylz
	 */
	public String getYlz() {
		return ylz;
	}

	/**
	 * @param ylz要设置的
	 *            ylz
	 */
	public void setYlz(String ylz) {
		this.ylz = ylz;
	}

	/**
	 * @return the sfkxg
	 */
	public String getSfkxg() {
		return sfkxg;
	}

	/**
	 * @param sfkxg要设置的
	 *            sfkxg
	 */
	public void setSfkxg(String sfkxg) {
		this.sfkxg = sfkxg;
	}

	/**
	 * @return the ppbmzd
	 */
	public String getPpbmzd() {
		return ppbmzd;
	}

	/**
	 * @param ppbmzd要设置的
	 *            ppbmzd
	 */
	public void setPpbmzd(String ppbmzd) {
		this.ppbmzd = ppbmzd;
	}

	/**
	 * @return the pk
	 */
	public String getPk() {
		return pk;
	}

	/**
	 * @param pk要设置的 pk
	 */
	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getQsz() {
		return qsz;
	}

	public void setQsz(String qsz) {
		this.qsz = qsz;
	}
	
}
