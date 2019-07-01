/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:00:24 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjgz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 请假管理模块
 * @类功能描述:form
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:00:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjgzForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String qjgzid;
	private String kssj;
	private String jssj;
	private String splcid;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private String qjqj;
	private String lcxx;
	//新增字段2016-11-29 请假类型
	private String qjlxid;
	private String open;//开关
	private String ssxydm;//所属学院dm（‘qx’为全校）
	private String ssxymc;//所属学院名称

	public String getSsxymc() {
		return ssxymc;
	}

	public void setSsxymc(String ssxymc) {
		this.ssxymc = ssxymc;
	}

	public String getSsxydm() {
		return ssxydm;
	}

	public void setSsxydm(String ssxydm) {
		this.ssxydm = ssxydm;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	/**
	 * @return the qjlxid
	 */
	public String getQjlxid() {
		return qjlxid;
	}

	/**
	 * @param qjlxid要设置的 qjlxid
	 */
	public void setQjlxid(String qjlxid) {
		this.qjlxid = qjlxid;
	}

	/**
	 * @return the qjgzid
	 */
	public String getQjgzid() {
		return qjgzid;
	}

	/**
	 * @param qjgzid要设置的
	 *            qjgzid
	 */
	public void setQjgzid(String qjgzid) {
		this.qjgzid = qjgzid;
	}

	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}

	/**
	 * @param kssj要设置的
	 *            kssj
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}

	/**
	 * @param jssj要设置的
	 *            jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}

	/**
	 * @param splcid要设置的
	 *            splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
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
	 * @return the qjqj
	 */
	public String getQjqj() {
		return qjqj;
	}

	/**
	 * @param qjqj要设置的 qjqj
	 */
	public void setQjqj(String qjqj) {
		this.qjqj = qjqj;
	}

	/**
	 * @return the lcxx
	 */
	public String getLcxx() {
		return lcxx;
	}

	/**
	 * @param lcxx要设置的 lcxx
	 */
	public void setLcxx(String lcxx) {
		this.lcxx = lcxx;
	}
}
