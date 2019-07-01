/**
 * @部门:学工产品事业部
 * @日期：2013-8-19 下午04:43:51 
 */  
package com.zfsoft.xgxt.xlzx.zxswh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 心理咨询-咨询师排班管理(这里用一句话描述这个类的作用) 
 * @作者： wanghj [工号：1004]
 * @时间： 2013-8-19 下午04:35:22
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 * @  wanghj [工号：1004] 2013-9-4 下午02:35:22
 * 
 */

public class ZxspbForm extends ActionForm {


	private static final long serialVersionUID = 1L;

	User user = new User();

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	
	  
	private String id; // 排班主键
	
	private String pbtype; // 排班类型
	private String pbdate; // 排班日期
	private String zgh; //职工号
	private String sjddm; // 时间段代码
	private String createsj; // 创建日期
	private String createbh; // 创建人编号
	private String createmc; // 创建人名称
	private String bz; // 备注
	private String datazt; // 数据状态0失效1正常
	private String dqny;//当前年月
	private String[] zghs;//职工号
	private String[] sjdm;//时间段代码
	private String[] xqdm;
	private String startDate;
	private String endDate;
	private String sfCopyPb;
	
	public String getSfCopyPb() {
		return sfCopyPb;
	}

	public void setSfCopyPb(String sfCopyPb) {
		this.sfCopyPb = sfCopyPb;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String[] getXqdm() {
		return xqdm;
	}

	public void setXqdm(String[] xqdm) {
		this.xqdm = xqdm;
	}

	public String[] getZghs() {
		return zghs;
	}

	public void setZghs(String[] zghs) {
		this.zghs = zghs;
	}

	public String[] getSjdm() {
		return sjdm;
	}

	public void setSjdm(String[] sjdm) {
		this.sjdm = sjdm;
	}

	/**
	 * @return the dqny
	 */
	public String getDqny() {
		return dqny;
	}

	/**
	 * @param dqny要设置的 dqny
	 */
	public void setDqny(String dqny) {
		this.dqny = dqny;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user要设置的 user
	 */
	public void setUser(User user) {
		this.user = user;
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
	 * @return the sjddm
	 */
	public String getSjddm() {
		return sjddm;
	}

	/**
	 * @param sjddm要设置的 sjddm
	 */
	public void setSjddm(String sjddm) {
		this.sjddm = sjddm;
	}

	/**
	 * @return the pbtype
	 */
	public String getPbtype() {
		return pbtype;
	}

	/**
	 * @param pbtype要设置的 pbtype
	 */
	public void setPbtype(String pbtype) {
		this.pbtype = pbtype;
	}

	/**
	 * @return the pbdate
	 */
	public String getPbdate() {
		return pbdate;
	}

	/**
	 * @param pbdate要设置的 pbdate
	 */
	public void setPbdate(String pbdate) {
		this.pbdate = pbdate;
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

	
	
}
