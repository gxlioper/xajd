/**
 * @部门:学工产品事业部
 * @日期：2015-6-4 上午09:07:37 
 */  
package com.zfsoft.extend.action.form;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-4 上午09:07:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExtendActionForm extends ActionForm {


	private static final long serialVersionUID = 5245899687315237346L;
	
	
	/*配置模块代码*/
	private String extendModuleID;

	/*数据ID*/
	private String dataId;

	/*数据类型，1：正式表数据，2临时表数据*/
	private String dataType;
	
	//学号
	private String xh;
	
	//职工还
	private String zgh;
	
	/*学生表单配置id*/
	private String bdpzid;
	
	/*用户提交的数据*/
	private String data;
	
	/*辅助数据类型*/
	private String lx;
	
	/**
	 * @return the extendModuleID
	 */
	public String getExtendModuleID() {
		return extendModuleID;
	}


	/**
	 * @param extendModuleID要设置的 extendModuleID
	 */
	public void setExtendModuleID(String extendModuleID) {
		this.extendModuleID = extendModuleID;
	}



	/**
	 * @return the dataId
	 */
	public String getDataId() {
		return dataId;
	}


	/**
	 * @param dataId要设置的 dataId
	 */
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}


	/**
	 * @return the bdpzid
	 */
	public String getBdpzid() {
		return bdpzid;
	}


	/**
	 * @param bdpzid要设置的 bdpzid
	 */
	public void setBdpzid(String bdpzid) {
		this.bdpzid = bdpzid;
	}


	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}


	/**
	 * @param data要设置的 data
	 */
	public void setData(String data) {
		this.data = data;
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
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}


	/**
	 * @param zgh要设置的 zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}


	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}


	/**
	 * @param dataType要设置的 dataType
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}


	/**
	 * @return the lx
	 */
	public String getLx() {
		return lx;
	}


	/**
	 * @param lx要设置的 lx
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}
	
	
}
