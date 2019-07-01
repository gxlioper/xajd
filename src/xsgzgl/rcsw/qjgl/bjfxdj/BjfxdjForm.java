package xsgzgl.rcsw.qjgl.bjfxdj;

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
 * Time:2012-7-17 下午13:13:22
 * </p>
 */

public class BjfxdjForm extends CommForm {

	private static final long serialVersionUID = 9219083873367155307L;
	private User user;

	private String guid;

	private String xh;//学号
	
	private String xm;//姓名
	
	private String nj;//年级
	
	private String bj;//班级
	
	private String qjkssj;//请假开始时间
	
	private String qjjssj;//请假结束时间
	
	private String qjts;//请假天数
	
	private String sffx;//是否返校
	
	private String djr;//登记人
	
	private String djsj;//登记时间
	
	private String bz;//备注

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public String getQjkssj() {
		return qjkssj;
	}

	public void setQjkssj(String qjkssj) {
		this.qjkssj = qjkssj;
	}

	public String getQjjssj() {
		return qjjssj;
	}

	public void setQjjssj(String qjjssj) {
		this.qjjssj = qjjssj;
	}

	public String getQjts() {
		return qjts;
	}

	public void setQjts(String qjts) {
		this.qjts = qjts;
	}

	public String getSffx() {
		return sffx;
	}

	public void setSffx(String sffx) {
		this.sffx = sffx;
	}

	public String getDjr() {
		return djr;
	}

	public void setDjr(String djr) {
		this.djr = djr;
	}

	public String getDjsj() {
		return djsj;
	}

	public void setDjsj(String djsj) {
		this.djsj = djsj;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}