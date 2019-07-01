package xsgzgl.gygl.rcjc.wsjc;

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

public class WsjcForm extends CommForm {

	private static final long serialVersionUID = 9219083873367155307L;

	private User user;

	private String guid;

	private String ld;//楼栋号

	private String qs;//寝室号

	private String xy;//学院

	private String bj;//班级

	private String xb;//性别

	private String sgxfdy;//所管辖辅导员

	private String wsdj;//卫生等级

	private String dgldq;//大功率电器

	private String jcsj;//检查时间

	private String jcry;//检查人员

	private String wsdjbz;//卫生等级备注

	private String dgldqbz;//大功率电器备注

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

	public String getLd() {
		return ld;
	}

	public void setLd(String ld) {
		this.ld = ld;
	}

	public String getQs() {
		return qs;
	}

	public void setQs(String qs) {
		this.qs = qs;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getSgxfdy() {
		return sgxfdy;
	}

	public void setSgxfdy(String sgxfdy) {
		this.sgxfdy = sgxfdy;
	}

	public String getWsdj() {
		return wsdj;
	}

	public void setWsdj(String wsdj) {
		this.wsdj = wsdj;
	}

	public String getDgldq() {
		return dgldq;
	}

	public void setDgldq(String dgldq) {
		this.dgldq = dgldq;
	}

	public String getJcsj() {
		return jcsj;
	}

	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}

	public String getJcry() {
		return jcry;
	}

	public void setJcry(String jcry) {
		this.jcry = jcry;
	}

	public String getWsdjbz() {
		return wsdjbz;
	}

	public void setWsdjbz(String wsdjbz) {
		this.wsdjbz = wsdjbz;
	}

	public String getDgldqbz() {
		return dgldqbz;
	}

	public void setDgldqbz(String dgldqbz) {
		this.dgldqbz = dgldqbz;
	}
}