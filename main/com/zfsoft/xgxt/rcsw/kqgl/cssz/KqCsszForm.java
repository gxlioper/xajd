/**
 * @部门:学工产品事业部
 * @日期：2016-10-26 上午09:32:39 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.cssz;

import org.apache.struts.action.ActionForm;

import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务——考情管理——管理模块
 * @类功能描述: 参数设置form 
 * @作者： cq [工号:785]
 * @时间： 2016-10-26 上午09:32:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqCsszForm extends ActionForm {

	private static final long serialVersionUID = -3120586281278575070L;
	
	private String id;
	private String sqkg;
	private String sqkssj;
	private String sqjssj;
	private String shkg;
	private String shkssj;
	private String shjssj;
	private String splc;
	private User user;
	private String type;
	
	
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
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkg要设置的 sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssj要设置的 sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssj要设置的 sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	/**
	 * @return the shkg
	 */
	public String getShkg() {
		return shkg;
	}
	/**
	 * @param shkg要设置的 shkg
	 */
	public void setShkg(String shkg) {
		this.shkg = shkg;
	}
	/**
	 * @return the shkssj
	 */
	public String getShkssj() {
		return shkssj;
	}
	/**
	 * @param shkssj要设置的 shkssj
	 */
	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}
	/**
	 * @return the shjssj
	 */
	public String getShjssj() {
		return shjssj;
	}
	/**
	 * @param shjssj要设置的 shjssj
	 */
	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user要设置的 user
	 */
	public void setUser(User user) {
		this.user = user;
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

}
