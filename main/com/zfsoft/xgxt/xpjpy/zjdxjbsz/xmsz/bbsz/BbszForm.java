/**
 * @部门:学工产品(1)部
 * @日期：2017-4-20 上午09:16:35 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.bbsz;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-基本设置-项目设置-报表设置
 * @类功能描述: 登记表、上报表设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-4-20 上午09:16:35 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BbszForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String bbdm;// 报表代码
	private String bbmc;// 报表名称
	private String bblx;// 报表类型
	private String xmdm;// 项目代码
	public BbszForm() {
		super();
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
	 * @return the bbdm
	 */
	public String getBbdm() {
		return bbdm;
	}
	/**
	 * @param bbdm要设置的 bbdm
	 */
	public void setBbdm(String bbdm) {
		this.bbdm = bbdm;
	}
	/**
	 * @return the bbmc
	 */
	public String getBbmc() {
		return bbmc;
	}
	/**
	 * @param bbmc要设置的 bbmc
	 */
	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}
	/**
	 * @return the bblx
	 */
	public String getBblx() {
		return bblx;
	}
	/**
	 * @param bblx要设置的 bblx
	 */
	public void setBblx(String bblx) {
		this.bblx = bblx;
	}
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdm要设置的 xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
}
