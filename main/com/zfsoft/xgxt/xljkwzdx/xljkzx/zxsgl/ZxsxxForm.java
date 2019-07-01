/**
 * @部门:学工产品事业部
 * @日期：2014-4-24 上午10:54:31 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.zxsgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询-咨询师管理
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-24 上午10:54:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxsxxForm extends ActionForm{

	private static final long serialVersionUID = 6392868410836088459L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	
	/**
	 * 职工号(咨询师编号)
	 */
	private String zgh;
	/**
	 * 联系电话
	 */
	private String lxdh;
	/**
	 * 咨询师级别
	 */
	private String zxslv;
	/**
	 * 任职资质
	 */
	private String zxszg;
	/**
	 * 可接待人数/日
	 */
	private String kjdrs;
	/**
	 * 在岗状态
	 */
	private String status;
	/**
	 * 创建时间
	 */
	private String createsj;
	/**
	 * 创建人编号
	 */
	private String createbh;
	/**
	 * 创建人名称
	 */
	private String createmc;
	/**
	 * 咨询师简介
	 */
	private String zxsjj;
	/**
	 * 数据状态0失效1正常
	 */
	private String datazt;
	/**
	 * 咨询详细地址
	 */
	private String address;
	
	
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
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zgh要设置的 zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
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
	 * @return the zxslv
	 */
	public String getZxslv() {
		return zxslv;
	}
	/**
	 * @param zxslv要设置的 zxslv
	 */
	public void setZxslv(String zxslv) {
		this.zxslv = zxslv;
	}
	/**
	 * @return the zxszg
	 */
	public String getZxszg() {
		return zxszg;
	}
	/**
	 * @param zxszg要设置的 zxszg
	 */
	public void setZxszg(String zxszg) {
		this.zxszg = zxszg;
	}
	/**
	 * @return the kjdrs
	 */
	public String getKjdrs() {
		return kjdrs;
	}
	/**
	 * @param kjdrs要设置的 kjdrs
	 */
	public void setKjdrs(String kjdrs) {
		this.kjdrs = kjdrs;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status要设置的 status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the createsj
	 */
	public String getCreatesj() {
		return createsj;
	}
	/**
	 * @param createsj要设置的 createsj
	 */
	public void setCreatesj(String createsj) {
		this.createsj = createsj;
	}
	/**
	 * @return the createbh
	 */
	public String getCreatebh() {
		return createbh;
	}
	/**
	 * @param createbh要设置的 createbh
	 */
	public void setCreatebh(String createbh) {
		this.createbh = createbh;
	}
	/**
	 * @return the createmc
	 */
	public String getCreatemc() {
		return createmc;
	}
	/**
	 * @param createmc要设置的 createmc
	 */
	public void setCreatemc(String createmc) {
		this.createmc = createmc;
	}
	/**
	 * @return the zxsjj
	 */
	public String getZxsjj() {
		return zxsjj;
	}
	/**
	 * @param zxsjj要设置的 zxsjj
	 */
	public void setZxsjj(String zxsjj) {
		this.zxsjj = zxsjj;
	}
	/**
	 * @return the datazt
	 */
	public String getDatazt() {
		return datazt;
	}
	/**
	 * @param datazt要设置的 datazt
	 */
	public void setDatazt(String datazt) {
		this.datazt = datazt;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address要设置的 address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
}





