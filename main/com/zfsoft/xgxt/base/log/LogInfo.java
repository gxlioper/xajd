package com.zfsoft.xgxt.base.log;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.utils.Pages;


/**
 * 
 * @类功能描述: 操作日志
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-9-9 上午11:23:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class LogInfo extends ActionForm{

	private static final long serialVersionUID = -2043476620305906487L;
	
	private String id;
	private String userType;
	private String username;
	private String ip;
	private String description;
	private String dotime;
	private String status;
	private String message;
	private String className;
	private String methodName;
	private String osName;
	private String browserName;
	private String browserVersion;
	
	private String czkssj;
	private String czjssj;
	
	private Pages pages = new Pages();
	
	private String zdm; //用户组名称
	private String czmk; //操作模块
	private ExportModel exportModel = new ExportModel();
	
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	
	/**
	 * @return the zdm
	 */
	public String getZdm() {
		return zdm;
	}
	/**
	 * @param zmc要设置的 zdm
	 */
	public void setZdm(String zdm) {
		this.zdm = zdm;
	}
	/**
	 * @return the czmk
	 */
	public String getCzmk() {
		return czmk;
	}
	/**
	 * @param czmk要设置的 czmk
	 */
	public void setCzmk(String czmk) {
		this.czmk = czmk;
	}
	/**
	 * @return the czkssj
	 */
	public String getCzkssj() {
		return czkssj;
	}
	/**
	 * @param czkssj要设置的 czkssj
	 */
	public void setCzkssj(String czkssj) {
		this.czkssj = czkssj;
	}
	/**
	 * @return the czjssj
	 */
	public String getCzjssj() {
		return czjssj;
	}
	/**
	 * @param czjssj要设置的 czjssj
	 */
	public void setCzjssj(String czjssj) {
		this.czjssj = czjssj;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the dotime
	 */
	public String getDotime() {
		return dotime;
	}
	/**
	 * @param dotime要设置的 dotime
	 */
	public void setDotime(String dotime) {
		this.dotime = dotime;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status要设置的 status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public String getBrowserName() {
		return browserName;
	}
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	public String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
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
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType要设置的 userType
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
