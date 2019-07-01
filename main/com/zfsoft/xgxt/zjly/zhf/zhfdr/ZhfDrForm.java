/**
 * @部门:学工产品事业部
 * @日期：2016-6-20 上午10:04:28 
 */  
package com.zfsoft.xgxt.zjly.zhf.zhfdr;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-6-20 上午10:04:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfDrForm extends ActionForm {
	 private String id;//ID
	 private String  xh;//学号
	 private String  jfxmdm;//计分项目代码
	 private String  xmmkdm;//项目模块代码
	 private String  sxsm;//事项说明
	 private String  shsj;//审核时间
	 private String  shr;//审核人
	 private String  cysj;//参与/获得时间
	 private String  shzt;//审核状态
	 private String  lrsj;//录入时间
	 private String  lrr;//录入人
	 private String  fj;//附件
	 private String  thyj;//退回意见
	 private FormFile drmb;//file
	 private static final long serialVersionUID = 1L;
	 private SearchModel searchModel = new SearchModel(); //高级查询条件
	 private Pages pages = new Pages();//分页、
	 private String type;//类型，前台跳转判断标志
	 private User user;//user,用于数据范围控制
	 private String fjmc;//附件名称
	 private String filepath;//存放错误文件的路径
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
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * @param fjmc要设置的 fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
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
	 * @return the thyj
	 */
	public String getThyj() {
		return thyj;
	}
	/**
	 * @param thyj要设置的 thyj
	 */
	public void setThyj(String thyj) {
		this.thyj = thyj;
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
	 * @return the jfxmdm
	 */
	public String getJfxmdm() {
		return jfxmdm;
	}
	/**
	 * @param jfxmdm要设置的 jfxmdm
	 */
	public void setJfxmdm(String jfxmdm) {
		this.jfxmdm = jfxmdm;
	}
	/**
	 * @return the xmmkdm
	 */
	public String getXmmkdm() {
		return xmmkdm;
	}
	/**
	 * @param xmmkdm要设置的 xmmkdm
	 */
	public void setXmmkdm(String xmmkdm) {
		this.xmmkdm = xmmkdm;
	}
	/**
	 * @return the sxsm
	 */
	public String getSxsm() {
		return sxsm;
	}
	/**
	 * @param sxsm要设置的 sxsm
	 */
	public void setSxsm(String sxsm) {
		this.sxsm = sxsm;
	}
	/**
	 * @return the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @param shsj要设置的 shsj
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	/**
	 * @return the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @param shr要设置的 shr
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @return the cysj
	 */
	public String getCysj() {
		return cysj;
	}
	/**
	 * @param cysj要设置的 cysj
	 */
	public void setCysj(String cysj) {
		this.cysj = cysj;
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
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @param lrsj要设置的 lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	/**
	 * @return the lrr
	 */
	public String getLrr() {
		return lrr;
	}
	/**
	 * @param lrr要设置的 lrr
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	/**
	 * @return the fj
	 */
	public String getFj() {
		return fj;
	}
	/**
	 * @param fj要设置的 fj
	 */
	public void setFj(String fj) {
		this.fj = fj;
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
	 * @return the drmb
	 */
	public FormFile getDrmb() {
		return drmb;
	}
	/**
	 * @param drmb要设置的 drmb
	 */
	public void setDrmb(FormFile drmb) {
		this.drmb = drmb;
	}

}
