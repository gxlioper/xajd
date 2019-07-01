package xsgzgl.gygl.rcjc.qszf;

import xgxt.comm.CommForm;
import xgxt.form.User;

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
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-9 下午14:19:22
 * </p>
 */

public class QszfForm extends CommForm {

	private static final long serialVersionUID = 9219083873367155307L;

	private User user;

	private String guid;

	private String xy;// 学院

	private String bzrxm;// 班主任姓名

	private String bj;// 班级

	private String xsszld;// 学生所在楼栋

	private String xsszqs;// 学生所在寝室

	private String bjBefore;// 之前班级

	private String xsszldBefore;// 之前学生所在楼栋

	private String xsszqsBefore;// 之前学生所在寝室

	private String xqsj;// 下寝时间

	private String bz;// 备注

	private String zflsgh;// 走访老师工号

	private String zflsxm;// 走访老师姓名

	private String fzbj;// 负责班级

	private String xsszqsh;// 学生所在寝室号
	
	private String zgh;//职工号
	
	private String xm;//姓名

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getBjBefore() {
		return bjBefore;
	}

	public void setBjBefore(String bjBefore) {
		this.bjBefore = bjBefore;
	}

	public String getXsszldBefore() {
		return xsszldBefore;
	}

	public void setXsszldBefore(String xsszldBefore) {
		this.xsszldBefore = xsszldBefore;
	}

	public String getXsszqsBefore() {
		return xsszqsBefore;
	}

	public void setXsszqsBefore(String xsszqsBefore) {
		this.xsszqsBefore = xsszqsBefore;
	}

	public String getZflsxm() {
		return zflsxm;
	}

	public void setZflsxm(String zflsxm) {
		this.zflsxm = zflsxm;
	}

	public String getFzbj() {
		return fzbj;
	}

	public void setFzbj(String fzbj) {
		this.fzbj = fzbj;
	}

	public String getXsszqsh() {
		return xsszqsh;
	}

	public void setXsszqsh(String xsszqsh) {
		this.xsszqsh = xsszqsh;
	}

	public String getZflsgh() {
		return zflsgh;
	}

	public void setZflsgh(String zflsgh) {
		this.zflsgh = zflsgh;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getBzrxm() {
		return bzrxm;
	}

	public void setBzrxm(String bzrxm) {
		this.bzrxm = bzrxm;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public String getXsszld() {
		return xsszld;
	}

	public void setXsszld(String xsszld) {
		this.xsszld = xsszld;
	}

	public String getXsszqs() {
		return xsszqs;
	}

	public void setXsszqs(String xsszqs) {
		this.xsszqs = xsszqs;
	}

	public String getXqsj() {
		return xqsj;
	}

	public void setXqsj(String xqsj) {
		this.xqsj = xqsj;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
}