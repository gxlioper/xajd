package com.zfsoft.xgxt.xszz.knsrdnew.knsrdzb;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：Dlq[工号:995]
 * @时间： 2014-1-24 上午09:01:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KnsrdzbForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private String zbmc;
	private String qyzt;
	private String zbid;
	
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
	 * @return the zbmc
	 */
	public String getZbmc() {
		return zbmc;
	}
	/**
	 * @param zbmc要设置的 zbmc
	 */
	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}
	/**
	 * @return the qyzt
	 */
	public String getQyzt() {
		return qyzt;
	}
	/**
	 * @param qyzt要设置的 qyzt
	 */
	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
	}
	/**
	 * @return the zbid
	 */
	public String getZbid() {
		return zbid;
	}
	/**
	 * @param zbid要设置的 zbid
	 */
	public void setZbid(String zbid) {
		this.zbid = zbid;
	}
	

}
