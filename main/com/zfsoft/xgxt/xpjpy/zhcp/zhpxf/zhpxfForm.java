/**
 * @部门:学工产品事业部
 * @日期：2017-6-27 下午02:56:58 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zhpxf;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2017-6-27 下午02:56:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class zhpxfForm extends ActionForm{
	  /** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1315015409554665125L;
	private String xn;
	  private String  zcfid;
	  private String xm;
	  private String xh;
	  private String zhpxf;
	  private String drsj;
	  private String pjf;
	  private String zhszf;
	  private String zhszfpm;
	  private String type;
	  private FormFile drmb;//file
	  private User user;//user,用于数据范围控制
	  private String filepath;//存放错误文件的路径
		private Pages pages = new Pages();
		  private SearchModel searchModel = new SearchModel();
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
	 * @return the zcfid
	 */
	public String getZcfid() {
		return zcfid;
	}
	/**
	 * @param zcfid要设置的 zcfid
	 */
	public void setZcfid(String zcfid) {
		this.zcfid = zcfid;
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
	/**
	 * @return the zhpxf
	 */
	public String getZhpxf() {
		return zhpxf;
	}
	/**
	 * @param zhpxf要设置的 zhpxf
	 */
	public void setZhpxf(String zhpxf) {
		this.zhpxf = zhpxf;
	}
	/**
	 * @return the pjf
	 */
	public String getPjf() {
		return pjf;
	}
	/**
	 * @param pjf要设置的 pjf
	 */
	public void setPjf(String pjf) {
		this.pjf = pjf;
	}
	/**
	 * @return the zhszf
	 */
	public String getZhszf() {
		return zhszf;
	}
	/**
	 * @param zhszf要设置的 zhszf
	 */
	public void setZhszf(String zhszf) {
		this.zhszf = zhszf;
	}
	/**
	 * @return the zhszfpm
	 */
	public String getZhszfpm() {
		return zhszfpm;
	}
	/**
	 * @param zhszfpm要设置的 zhszfpm
	 */
	public void setZhszfpm(String zhszfpm) {
		this.zhszfpm = zhszfpm;
	}
	/**
	 * @return the drsj
	 */
	public String getDrsj() {
		return drsj;
	}
	/**
	 * @param drsj要设置的 drsj
	 */
	public void setDrsj(String drsj) {
		this.drsj = drsj;
	}
	  
	  
	  
}
