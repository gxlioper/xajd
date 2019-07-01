/**
 * @部门:学工产品事业部
 * @日期：2014-8-18 上午11:16:58 
 */  
package com.zfsoft.xgxt.jjgl.jjzg;

import java.io.Serializable;


import com.zfsoft.xgxt.base.model.SuperAuditModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @类功能描述: 家教资格 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-8-18 上午11:16:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JjzgForm extends SuperAuditModel implements Serializable{

	private static final long serialVersionUID = -6134358457163622433L;
	
	private String sqid;
	private String xh;
	private String jjnjdm;
	private String jjkma;
	private String jjkmb;
	private String jjkmc;
	private String lxdh;
	private String bz;
	private String jjpxqk;
	private String rddj;
	private String shzt;
	private String sqsj;
	private String splcid;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String xkmca;
	private String xkmcb;
	private String xkmcc;
	private String jjnjmc;
	private String sckmmcs;
	/**
	 * @return the jjnjmc
	 */
	public String getJjnjmc() {
		return jjnjmc;
	}
	/**
	 * @param jjnjmc要设置的 jjnjmc
	 */
	public void setJjnjmc(String jjnjmc) {
		this.jjnjmc = jjnjmc;
	}
	/**
	 * @return the xkmca
	 */
	public String getXkmca() {
		return xkmca;
	}
	/**
	 * @param xkmca要设置的 xkmca
	 */
	public void setXkmca(String xkmca) {
		this.xkmca = xkmca;
	}
	/**
	 * @return the xkmcb
	 */
	public String getXkmcb() {
		return xkmcb;
	}
	/**
	 * @param xkmcb要设置的 xkmcb
	 */
	public void setXkmcb(String xkmcb) {
		this.xkmcb = xkmcb;
	}
	/**
	 * @return the xkmcc
	 */
	public String getXkmcc() {
		return xkmcc;
	}
	/**
	 * @param xkmcc要设置的 xkmcc
	 */
	public void setXkmcc(String xkmcc) {
		this.xkmcc = xkmcc;
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
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	 * @return the jjnjdm
	 */
	public String getJjnjdm() {
		return jjnjdm;
	}
	/**
	 * @param jjnjdm要设置的 jjnjdm
	 */
	public void setJjnjdm(String jjnjdm) {
		this.jjnjdm = jjnjdm;
	}
	/**
	 * @return the jjkma
	 */
	public String getJjkma() {
		return jjkma;
	}
	/**
	 * @param jjkma要设置的 jjkma
	 */
	public void setJjkma(String jjkma) {
		this.jjkma = jjkma;
	}
	/**
	 * @return the jjkmb
	 */
	public String getJjkmb() {
		return jjkmb;
	}
	/**
	 * @param jjkmb要设置的 jjkmb
	 */
	public void setJjkmb(String jjkmb) {
		this.jjkmb = jjkmb;
	}
	/**
	 * @return the jjkmc
	 */
	public String getJjkmc() {
		return jjkmc;
	}
	/**
	 * @param jjkmc要设置的 jjkmc
	 */
	public void setJjkmc(String jjkmc) {
		this.jjkmc = jjkmc;
	}
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdh要设置的 lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
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
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the jjpxqk
	 */
	public String getJjpxqk() {
		return jjpxqk;
	}
	/**
	 * @param jjpxqk要设置的 jjpxqk
	 */
	public void setJjpxqk(String jjpxqk) {
		this.jjpxqk = jjpxqk;
	}
	/**
	 * @return the rddj
	 */
	public String getRddj() {
		return rddj;
	}
	/**
	 * @param rddj要设置的 rddj
	 */
	public void setRddj(String rddj) {
		this.rddj = rddj;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcid要设置的 splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}

	public String getSckmmcs() {
		return sckmmcs;
	}

	public void setSckmmcs(String sckmmcs) {
		this.sckmmcs = sckmmcs;
	}
}
