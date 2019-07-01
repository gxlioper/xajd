/**
 * @部门:学工产品事业部
 * @时间： 2013-12-3 上午10:56:46 
 */  
package com.zfsoft.xgxt.xsxx.bycl;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @模块名称: 学生信息
 * @类功能描述:毕业处理
 * @作者： Qilm[工号:964]
 * @时间： 2013-12-3 上午10:56:46 
 * @版本： V1.0
 */
public class ByclForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	/**
	 * 学号
	 */
	private String xh;

	/**
	 * 是否已毕业(0/空：否；1：是)
	 */
	private String sfyby;

	/**
	 * 毕业年月
	 */
	private String byny;
	
	/**
	 * 是否全选（all:全选）
	 */
	private String selected;
	
	/**
	 * 已选择学生
	 */
	private String values;

	private String searchTj;
	private String searchTjz;
	private String mhcx_lx;
	private String searchLx;
	private String input_mhcx;
	private String path;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private FormFile drmb;/*毕业学生信息导入模板*/
	private String filepath;/*导入模板路径*/
	
	
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

	public String getSearchTj() {
		return searchTj;
	}

	public void setSearchTj(String searchTj) {
		this.searchTj = searchTj;
	}

	public String getSearchTjz() {
		return searchTjz;
	}

	public void setSearchTjz(String searchTjz) {
		this.searchTjz = searchTjz;
	}

	public String getMhcx_lx() {
		return mhcx_lx;
	}

	public void setMhcx_lx(String mhcxLx) {
		mhcx_lx = mhcxLx;
	}

	public String getSearchLx() {
		return searchLx;
	}

	public void setSearchLx(String searchLx) {
		this.searchLx = searchLx;
	}

	public String getInput_mhcx() {
		return input_mhcx;
	}

	public void setInput_mhcx(String inputMhcx) {
		input_mhcx = inputMhcx;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getSfyby() {
		return sfyby;
	}

	public void setSfyby(String sfyby) {
		this.sfyby = sfyby;
	}

	public String getByny() {
		return byny;
	}

	public void setByny(String byny) {
		this.byny = byny;
	}
}
