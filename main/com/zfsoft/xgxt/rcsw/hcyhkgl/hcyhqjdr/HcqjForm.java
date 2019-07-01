/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhqjdr;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class HcqjForm extends ActionForm{
	/**
	 * @fields ：serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 4508487990146756184L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private ExportModel exportModel = new ExportModel();
	private String path;
	private String xh;
	private String xm;
	private String hcyhk;
    private FormFile drmb;//file
    private User user;//user,用于数据范围控制
	private String filepath;//存放错误文件的路径
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @return		: the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @param 		：path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @param 		：xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @return		: the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @param 		：xm the xm to set
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @return		: the hcyhk
	 */
	public String getHcyhk() {
		return hcyhk;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @param 		：hcyhk the hcyhk to set
	 */
	public void setHcyhk(String hcyhk) {
		this.hcyhk = hcyhk;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @return		: the drmb
	 */
	public FormFile getDrmb() {
		return drmb;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @param 		：drmb the drmb to set
	 */
	public void setDrmb(FormFile drmb) {
		this.drmb = drmb;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @return		: the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @param 		：user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @return		: the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-9 上午09:21:06 
	 * @param 		：filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	 
	
	
	
	
	
	
	
	
	
	
	
}
