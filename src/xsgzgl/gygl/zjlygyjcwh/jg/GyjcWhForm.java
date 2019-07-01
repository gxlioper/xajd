/**
 * @部门:学工产品事业部
 * @日期：2016-4-28 下午04:25:30 
 */  
package xsgzgl.gygl.zjlygyjcwh.jg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-4-28 下午04:25:30 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyjcWhForm extends ActionForm {
	 private String id;
	 private String xh;
	 private String xn;
	 private String jcdm;
	 private String fz;
	 private String xq;
	 private String czsj;
	 private String type;
	 private static final long serialVersionUID = 1L;
	 private SearchModel searchModel = new SearchModel();
	//导出
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
	 /**
	 * @return the czsj
	 */
	public String getCzsj() {
		return czsj;
	}
	/**
	 * @param czsj要设置的 czsj
	 */
	public void setCzsj(String czsj) {
		this.czsj = czsj;
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
	 * @return the jcdm
	 */
	public String getJcdm() {
		return jcdm;
	}
	/**
	 * @param jcdm要设置的 jcdm
	 */
	public void setJcdm(String jcdm) {
		this.jcdm = jcdm;
	}
	/**
	 * @return the fz
	 */
	public String getFz() {
		return fz;
	}
	/**
	 * @param fz要设置的 fz
	 */
	public void setFz(String fz) {
		this.fz = fz;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
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
	
}
