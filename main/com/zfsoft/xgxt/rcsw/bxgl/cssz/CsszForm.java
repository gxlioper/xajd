/**
 * @部门:学工产品事业部
 * @日期：2015-3-25 下午04:42:37 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.cssz;

import org.apache.struts.action.ActionForm;

import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-3-25 下午04:42:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszForm extends ActionForm{
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
	private String mzbxsx;
	public String getMzbxsx() {
		return mzbxsx;
	}
	public void setMzbxsx(String mzbxsx) {
		this.mzbxsx = mzbxsx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSqkg() {
		return sqkg;
	}
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	public String getSqkssj() {
		return sqkssj;
	}
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	public String getSqjssj() {
		return sqjssj;
	}
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	public String getShkg() {
		return shkg;
	}
	public void setShkg(String shkg) {
		this.shkg = shkg;
	}
	public String getShkssj() {
		return shkssj;
	}
	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}
	public String getShjssj() {
		return shjssj;
	}
	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
