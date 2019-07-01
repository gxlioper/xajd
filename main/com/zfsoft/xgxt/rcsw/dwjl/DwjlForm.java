/**
 * @部门:学工产品事业部
 * @日期：2015-1-21 上午11:02:08 
 */  
package com.zfsoft.xgxt.rcsw.dwjl;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @className	： DwjlForm
 * @description	： TODO(描述这个类的作用)
 * @author 		：CP（1352）
 * @date		： 2017-11-20 上午09:13:12
 * @version 	V1.0
 */

public class DwjlForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	// 分页
	Pages pages = new Pages();
	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();

	FormFile uploadFile;// 上传文件

	private String yclx;//异常数据类型
	
	private String[] primarykey_checkVal;// CheckBox
	
	private String type; //类型
	
	private String id ;//主键
	private String xh ; //学号
	private String jlxx ;
	private String jlkssj ;
	private String jljssj ;
	private String jlzy ;
	private String bz ;
	private String filepath;
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
	 * @return the sqsj
	 */
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-20 上午09:15:41 
	 * @return		: the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-20 上午09:15:41 
	 * @param 		：id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-20 上午09:15:41 
	 * @return		: the jlxx
	 */
	public String getJlxx() {
		return jlxx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-20 上午09:15:41 
	 * @param 		：jlxx the jlxx to set
	 */
	public void setJlxx(String jlxx) {
		this.jlxx = jlxx;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-20 上午09:15:41 
	 * @return		: the jlkssj
	 */
	public String getJlkssj() {
		return jlkssj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-20 上午09:15:41 
	 * @param 		：jlkssj the jlkssj to set
	 */
	public void setJlkssj(String jlkssj) {
		this.jlkssj = jlkssj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-20 上午09:15:41 
	 * @return		: the jljssj
	 */
	public String getJljssj() {
		return jljssj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-20 上午09:15:41 
	 * @param 		：jljssj the jljssj to set
	 */
	public void setJljssj(String jljssj) {
		this.jljssj = jljssj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-20 上午09:15:41 
	 * @return		: the jlzy
	 */
	public String getJlzy() {
		return jlzy;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-20 上午09:15:41 
	 * @param 		：jlzy the jlzy to set
	 */
	public void setJlzy(String jlzy) {
		this.jlzy = jlzy;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-20 上午09:15:41 
	 * @return		: the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-20 上午09:15:41 
	 * @param 		：bz the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-20 上午09:15:41 
	 * @return		: the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-20 上午09:15:41 
	 * @param 		：filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	
}
