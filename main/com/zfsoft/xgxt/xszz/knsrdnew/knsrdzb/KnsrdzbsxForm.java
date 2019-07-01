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
 * @时间： 2014-1-24 上午09:03:10 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KnsrdzbsxForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private String sxid;
	private String zbid;
	private String sxmc;
	private String qzbl;
	private String xssx;
	
	
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
	 * @return the sxid
	 */
	public String getSxid() {
		return sxid;
	}
	/**
	 * @param sxid要设置的 sxid
	 */
	public void setSxid(String sxid) {
		this.sxid = sxid;
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
	/**
	 * @return the sxmc
	 */
	public String getSxmc() {
		return sxmc;
	}
	/**
	 * @param sxmc要设置的 sxmc
	 */
	public void setSxmc(String sxmc) {
		this.sxmc = sxmc;
	}
	/**
	 * @return the qzbl
	 */
	public String getQzbl() {
		return qzbl;
	}
	/**
	 * @param qzbl要设置的 qzbl
	 */
	public void setQzbl(String qzbl) {
		this.qzbl = qzbl;
	}
	/**
	 * @return the xssx
	 */
	public String getXssx() {
		return xssx;
	}
	/**
	 * @param xssx要设置的 xssx
	 */
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}



	

}
