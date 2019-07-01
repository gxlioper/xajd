/**
 * @部门:学工产品事业部
 * @日期：2014-5-14 下午02:25:41 
 */  
package com.zfsoft.xgxt.szdw.bjxwjl.bjxwjlwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @类功能描述: 班级信息记录维护
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-14 下午02:25:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjxwjlwhForm extends ActionForm {
	//
	public static class Record{
		public String ixndsdm;
		
		public String ijlsj;
		
		public String ijlnr;

		public String ibjdm;

		/**
		 * @描述 ：TODO描述下当前构造方法
		 */
		public Record() {
			super();
		}

		/**
		 * @描述 ：TODO描述下当前构造方法
		 * @param xndsdm
		 * @param jlsj
		 * @param jlnr
		 * @param bjdm
		 */
		public Record(String ixndsdm, String ijlsj, String ijlnr,
				String ibjdm) {
			super();
			this.ixndsdm = ixndsdm;
			this.ijlsj = ijlsj;
			this.ijlnr = ijlnr;
			this.ibjdm = ibjdm;
		}

		/**
		 * @return the _xndsdm
		 */
		public String getIxndsdm() {
			return ixndsdm;
		}

		/**
		 * @param xndsdm要设置的 _xndsdm
		 */
		public void setIxndsdm(String xndsdm) {
			ixndsdm = xndsdm;
		}

		/**
		 * @return the _jlsj
		 */
		public String getIjlsj() {
			return ijlsj;
		}

		/**
		 * @param jlsj要设置的 _jlsj
		 */
		public void setIjlsj(String jlsj) {
			ijlsj = jlsj;
		}

		/**
		 * @return the _jlnr
		 */
		public String getIjlnr() {
			return ijlnr;
		}

		/**
		 * @param jlnr要设置的 _jlnr
		 */
		public void setIjlnr(String jlnr) {
			ijlnr = jlnr;
		}

		/**
		 * @return the _bjdm
		 */
		public String getIbjdm() {
			return ibjdm;
		}

		/**
		 * @param bjdm要设置的 _bjdm
		 */
		public void setIbjdm(String bjdm) {
			ibjdm = bjdm;
		}
		 
		 
	}
	
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	/** 
	 * @属性 pages : 分页模型 
	 */
	private Pages pages = new Pages();
	
	/** 
	 * @属性 searchModel : 高级查询模型 
	 */
	private SearchModel searchModel = new SearchModel();
	
	/** 
	 * @属性 exportModel : 导出模型 
	 */
	private ExportModel exportModel = new ExportModel();
	
	private String guid;
	
	private String xn;
	
	private String xqdm;
	
	private String xndsdm;
	
	private String jlr;
	
	private String jlsj;
	
	private String jlnr;
	
	private String bjdm;

	private String type;
	
	private String jsondata;
	
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
	 * @return the xqdm
	 */
	public String getXqdm() {
		return xqdm;
	}

	/**
	 * @param xqdm要设置的 xqdm
	 */
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}

	/**
	 * @return the xndsdm
	 */
	public String getXndsdm() {
		return xndsdm;
	}

	/**
	 * @param xndsdm要设置的 xndsdm
	 */
	public void setXndsdm(String xndsdm) {
		this.xndsdm = xndsdm;
	}

	/**
	 * @return the jlr
	 */
	public String getJlr() {
		return jlr;
	}

	/**
	 * @param jlr要设置的 jlr
	 */
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}

	/**
	 * @return the jlsj
	 */
	public String getJlsj() {
		return jlsj;
	}

	/**
	 * @param jlsj要设置的 jlsj
	 */
	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}

	/**
	 * @return the jlnr
	 */
	public String getJlnr() {
		return jlnr;
	}

	/**
	 * @param jlnr要设置的 jlnr
	 */
	public void setJlnr(String jlnr) {
		this.jlnr = jlnr;
	}

	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}

	/**
	 * @param bjdm要设置的 bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * @param guid要设置的 guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * @return the jsondata
	 */
	public String getJsondata() {
		return jsondata;
	}

	/**
	 * @param jsondata要设置的 jsondata
	 */
	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}
	
}
