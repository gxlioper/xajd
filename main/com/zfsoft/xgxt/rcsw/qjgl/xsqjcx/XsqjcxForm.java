/**
 * @部门:学工产品事业部
 * @日期：2016-3-3 下午03:45:18 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.xsqjcx;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2016-3-3 下午03:45:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsqjcxForm extends ActionForm {
	 private String xh;
	 private String zgh;
	 private String type;
	 private String id;
	 private String actinstid;
	 private static final long serialVersionUID = 1L;
	 private SearchModel searchModel = new SearchModel();
	//导出
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
	 private String flag;
	 /**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * @param flag要设置的 flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
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
		 * @return the serialversionuid
		 */
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		/**
		 * @return the actinstid
		 */
		public String getActinstid() {
			return actinstid;
		}
		/**
		 * @param actinstid要设置的 actinstid
		 */
		public void setActinstid(String actinstid) {
			this.actinstid = actinstid;
		}
	

}
