package xsgzgl.wjcf.xscfcx;

import org.apache.struts.upload.FormFile;

import xgxt.comm.CommForm;
import xgxt.form.User;
import xsgzgl.wjcf.cfssgl.WjcfCfssglForm;


/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ltt
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-17 下午12:36:30
 * </p>
 */
public class WjcfXscfActionForm  extends CommForm  {

	private String cfid;
	private String sqsj;
	private String sqly;
	private FormFile fj;
	private FormFile jcfj;
	private User user;
	private String fjmc;
	private String jcsqly;
	public String getJcsqly() {
		return jcsqly;
	}

	public void setJcsqly(String jcsqly) {
		this.jcsqly = jcsqly;
	}

	public String getFjmc() {
		return fjmc;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	public FormFile getFj() {
		return fj;
	}

	public void setFj(FormFile fj) {
		this.fj = fj;
	}

	public FormFile getJcfj() {
		return jcfj;
	}

	public void setJcfj(FormFile jcfj) {
		this.jcfj = jcfj;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getCfid() {
		return cfid;
	}

	public void setCfid(String cfid) {
		this.cfid = cfid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
