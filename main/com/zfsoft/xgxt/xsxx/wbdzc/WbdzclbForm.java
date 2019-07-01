/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 上午10:58:08 
 */  
package com.zfsoft.xgxt.xsxx.wbdzc;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息--报到注册（华师大）--未报到（注册）类别
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 孟威[工号:1186]
 * @时间： 2016-3-8 上午11:21:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WbdzclbForm extends ActionForm{
	
	private static final long serialVersionUID = -1860077048529228835L;
	// 分页
	Pages pages = new Pages();
	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	FormFile uploadFile;// 上传文件
	private String yclx;//异常数据类型
	private String[] primarykey_checkVal;// CheckBox
	private String type;
	private String wbdlbdm ;//未报到注册代码
	private String wbdlbmc ;//未报到注册名称
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
	 * @return the uploadFile
	 */
	public FormFile getUploadFile() {
		return uploadFile;
	}
	/**
	 * @param uploadFile要设置的 uploadFile
	 */
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	/**
	 * @return the yclx
	 */
	public String getYclx() {
		return yclx;
	}
	/**
	 * @param yclx要设置的 yclx
	 */
	public void setYclx(String yclx) {
		this.yclx = yclx;
	}
	/**
	 * @return the primarykey_checkVal
	 */
	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}
	/**
	 * @param primarykeyCheckVal要设置的 primarykey_checkVal
	 */
	public void setPrimarykey_checkVal(String[] primarykeyCheckVal) {
		primarykey_checkVal = primarykeyCheckVal;
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
	 * @return the wbdlbdm
	 */
	public String getWbdlbdm() {
		return wbdlbdm;
	}
	/**
	 * @param wbdlbdm要设置的 wbdlbdm
	 */
	public void setWbdlbdm(String wbdlbdm) {
		this.wbdlbdm = wbdlbdm;
	}
	/**
	 * @return the wbdlbmc
	 */
	public String getWbdlbmc() {
		return wbdlbmc;
	}
	/**
	 * @param wbdlbmc要设置的 wbdlbmc
	 */
	public void setWbdlbmc(String wbdlbmc) {
		this.wbdlbmc = wbdlbmc;
	}
	
}
