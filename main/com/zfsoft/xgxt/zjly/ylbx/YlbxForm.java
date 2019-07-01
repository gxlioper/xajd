/**
 * @部门:学工产品事业部
 * @日期：2016-4-19 上午10:03:16 
 */  
package com.zfsoft.xgxt.zjly.ylbx;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 医疗保险管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-4-19 上午10:03:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YlbxForm extends ActionForm {
	  private String xn;
	  private String  id;
	  private String xm;
	  private String xb;
	  private String xymc;
	  private String xh;
	  private String sfzh;
	  private String csrq;
	  private String rxsj;
	  private String lxdh;
	  private String zlbh;
	  private String czxx;
	  private String czzjbh;
	  private String czkssj;
	  private String czzzsj;
	  private String knbs;
	  private String ganbs;
	  private String ycyy;
	  private String yyyybs;
	  private String cxblb;
	  private String shbz;
	  private String shyj;
	  private String type;
	  private FormFile drmb;//file
	  private User user;//user,用于数据范围控制
	  private String filepath;//存放错误文件的路径
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
	private Pages pages = new Pages();
	  private SearchModel searchModel = new SearchModel();
		//导出
	  private ExportModel exportModel = new ExportModel();
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
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
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
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}
	/**
	 * @param xb要设置的 xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymc要设置的 xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
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
	 * @return the sfzh
	 */
	public String getSfzh() {
		return sfzh;
	}
	/**
	 * @param sfzh要设置的 sfzh
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	/**
	 * @return the csrq
	 */
	public String getCsrq() {
		return csrq;
	}
	/**
	 * @param csrq要设置的 csrq
	 */
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	/**
	 * @return the rxsj
	 */
	public String getRxsj() {
		return rxsj;
	}
	/**
	 * @param rxsj要设置的 rxsj
	 */
	public void setRxsj(String rxsj) {
		this.rxsj = rxsj;
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
	 * @return the zlbh
	 */
	public String getZlbh() {
		return zlbh;
	}
	/**
	 * @param zlbh要设置的 zlbh
	 */
	public void setZlbh(String zlbh) {
		this.zlbh = zlbh;
	}
	/**
	 * @return the czxx
	 */
	public String getCzxx() {
		return czxx;
	}
	/**
	 * @param czxx要设置的 czxx
	 */
	public void setCzxx(String czxx) {
		this.czxx = czxx;
	}
	/**
	 * @return the czzjbh
	 */
	public String getCzzjbh() {
		return czzjbh;
	}
	/**
	 * @param czzjbh要设置的 czzjbh
	 */
	public void setCzzjbh(String czzjbh) {
		this.czzjbh = czzjbh;
	}
	/**
	 * @return the czkssj
	 */
	public String getCzkssj() {
		return czkssj;
	}
	/**
	 * @param czkssj要设置的 czkssj
	 */
	public void setCzkssj(String czkssj) {
		this.czkssj = czkssj;
	}
	/**
	 * @return the czzzsj
	 */
	public String getCzzzsj() {
		return czzzsj;
	}
	/**
	 * @param czzzsj要设置的 czzzsj
	 */
	public void setCzzzsj(String czzzsj) {
		this.czzzsj = czzzsj;
	}
	/**
	 * @return the knbs
	 */
	public String getKnbs() {
		return knbs;
	}
	/**
	 * @param knbs要设置的 knbs
	 */
	public void setKnbs(String knbs) {
		this.knbs = knbs;
	}
	/**
	 * @return the ganbs
	 */
	public String getGanbs() {
		return ganbs;
	}
	/**
	 * @param ganbs要设置的 ganbs
	 */
	public void setGanbs(String ganbs) {
		this.ganbs = ganbs;
	}
	/**
	 * @return the ycyy
	 */
	public String getYcyy() {
		return ycyy;
	}
	/**
	 * @param ycyy要设置的 ycyy
	 */
	public void setYcyy(String ycyy) {
		this.ycyy = ycyy;
	}
	/**
	 * @return the yyyybs
	 */
	public String getYyyybs() {
		return yyyybs;
	}
	/**
	 * @param yyyybs要设置的 yyyybs
	 */
	public void setYyyybs(String yyyybs) {
		this.yyyybs = yyyybs;
	}
	/**
	 * @return the cxblb
	 */
	public String getCxblb() {
		return cxblb;
	}
	/**
	 * @param cxblb要设置的 cxblb
	 */
	public void setCxblb(String cxblb) {
		this.cxblb = cxblb;
	}
	/**
	 * @return the shbz
	 */
	public String getShbz() {
		return shbz;
	}
	/**
	 * @param shbz要设置的 shbz
	 */
	public void setShbz(String shbz) {
		this.shbz = shbz;
	}
	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyj要设置的 shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
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
	
}
