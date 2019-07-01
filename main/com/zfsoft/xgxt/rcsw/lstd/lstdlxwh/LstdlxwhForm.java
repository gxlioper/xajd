/**
 * @部门:学工产品事业部
 * @日期：2014-11-25 上午09:35:31 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdlxwh;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 绿色通道类型维护
 * @作者： cq [工号:785]
 * @时间： 2014-11-25 上午09:35:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LstdlxwhForm extends ActionForm {
	
	
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
	
	private String lxdm ;//绿色通道代码
	private String lxmc ;//绿色通道名称
	private String bs ;//绿色通道标识,0-否 可删除，1-是 不可删除
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public FormFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getYclx() {
		return yclx;
	}
	public void setYclx(String yclx) {
		this.yclx = yclx;
	}
	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}
	public void setPrimarykey_checkVal(String[] primarykeyCheckVal) {
		primarykey_checkVal = primarykeyCheckVal;
	}
	public String getLxdm() {
		return lxdm;
	}
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	public String getLxmc() {
		return lxmc;
	}
	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}
	public String getBs() {
		return bs;
	}
	public void setBs(String bs) {
		this.bs = bs;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


}
