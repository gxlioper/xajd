package com.zfsoft.xgxt.base.log;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.utils.Pages;


/**
 * 
 * @�๦������: ������־
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-9-9 ����11:23:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
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
	
	private String zdm; //�û�������
	private String czmk; //����ģ��
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
	 * @param zmcҪ���õ� zdm
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
	 * @param czmkҪ���õ� czmk
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
	 * @param czkssjҪ���õ� czkssj
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
	 * @param czjssjҪ���õ� czjssj
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
	 * @param pagesҪ���õ� pages
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
	 * @param dotimeҪ���õ� dotime
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
	 * @param statusҪ���õ� status
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
	 * @param idҪ���õ� id
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
	 * @param userTypeҪ���õ� userType
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
