package xsgzgl.xtwh.general.power;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_权限_通用_Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XtwhPowerModel {

	private String gnmkdm;// 功能模块码
	private String yhm;// 用户名
	private String zdm;// 组代码

	private String  xm;// 姓名
	private String  szbm;// 所在部门
	
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getSzbm() {
		return szbm;
	}

	public void setSzbm(String szbm) {
		this.szbm = szbm;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getZdm() {
		return zdm;
	}

	public String getGnmkdm() {
		return gnmkdm;
	}

	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}
}
