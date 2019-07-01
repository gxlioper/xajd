/**
 * @部门:学工产品事业部
 * @日期：2016-8-18 上午10:34:16 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.bddc;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-8-18 上午10:34:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExportModel extends ActionForm {
	private static final long serialVersionUID = 7850562411516988844L;
	private String dcclbh;
	private List<HashMap<String, String>> dataList;
	private String zgh;
	private String[] unselectCol;
	private String[] selectCol;
	private String selectZd;
	private String unselectZd;
	private String backUrl;
	private String exportWjgs;
	private String[] xhArr;
	private String path;//导出时建文件夹用
	private String xh;//学号，导出时用
	private String filepath;//文件路径，导出文件时用
	private String defaultimagepath;//默认图片路径
	
	/**
	 * @return the defaultimagepath
	 */
	public String getDefaultimagepath() {
		return defaultimagepath;
	}
	/**
	 * @param defaultimagepath要设置的 defaultimagepath
	 */
	public void setDefaultimagepath(String defaultimagepath) {
		this.defaultimagepath = defaultimagepath;
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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path要设置的 path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the xhArr
	 */
	public String[] getXhArr() {
		return xhArr;
	}
	/**
	 * @param xhArr要设置的 xhArr
	 */
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}
	public String getDcclbh() {
		return dcclbh;
	}
	public void setDcclbh(String dcclbh) {
		this.dcclbh = dcclbh;
	}
	public List<HashMap<String, String>> getDataList() {
		return dataList;
	}
	public void setDataList(List<HashMap<String, String>> dataList) {
		this.dataList = dataList;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public String[] getUnselectCol() {
		return unselectCol;
	}
	public void setUnselectCol(String[] unselectCol) {
		this.unselectCol = unselectCol;
	}
	public String[] getSelectCol() {
		return selectCol;
	}
	public void setSelectCol(String[] selectCol) {
		this.selectCol = selectCol;
	}
	public String getSelectZd() {
		return selectZd;
	}
	public void setSelectZd(String selectZd) {
		this.selectZd = selectZd;
	}
	public String getUnselectZd() {
		return unselectZd;
	}
	public void setUnselectZd(String unselectZd) {
		this.unselectZd = unselectZd;
	}
	public String getExportWjgs() {
		return exportWjgs;
	}
	public void setExportWjgs(String exportWjgs) {
		this.exportWjgs = exportWjgs;
	}
	
}
