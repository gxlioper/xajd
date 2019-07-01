package xsgzgl.pjpy.general.wdpj;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyWdpjModel {

	private String xmdm;// 项目代码
	
	private String bjdm;// 班级代码

	private String xh;// 学号

	private String zgh;// 职工号
	
	private String gwid;// 岗位ID
	
	private String[] sqr;// 申请人
	
	private String bjdl;// 班级大类
	
	public String getBjdl() {
		return bjdl;
	}

	public void setBjdl(String bjdl) {
		this.bjdl = bjdl;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getGwid() {
		return gwid;
	}

	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	public String[] getSqr() {
		return sqr;
	}

	public void setSqr(String[] sqr) {
		this.sqr = sqr;
	}
}
