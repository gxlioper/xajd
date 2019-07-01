/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午10:24:33 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午10:24:33
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglGzdmForm extends ActionForm{
	private String gzdm;// varchar2(20) n 规则代码
	private String gzmc;// varchar2(200) n 规则名称
	private String tjgzid;// varchar2(20) n 关联条件规则id(逗号分隔)

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	public String[] getTjgz(){
		if(StringUtils.isNull(tjgzid)){
			return null;
		}
		return tjgzid.split(",");
	}
	/**
	 * @return the gzdm
	 */
	public String getGzdm() {
		return gzdm;
	}

	/**
	 * @param gzdm要设置的
	 *            gzdm
	 */
	public void setGzdm(String gzdm) {
		this.gzdm = gzdm;
	}

	/**
	 * @return the gzmc
	 */
	public String getGzmc() {
		return gzmc;
	}

	/**
	 * @param gzmc要设置的
	 *            gzmc
	 */
	public void setGzmc(String gzmc) {
		this.gzmc = gzmc;
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
}
