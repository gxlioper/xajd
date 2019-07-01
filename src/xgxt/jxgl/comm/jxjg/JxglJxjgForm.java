package xgxt.jxgl.comm.jxjg;


import xgxt.form.User;
import xgxt.jxgl.comm.JxglCommForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_军训结果_Form类
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

public class JxglJxjgForm extends JxglCommForm {

	private static final long serialVersionUID = 1L;
	
	User user = new User();

	private String bzdm;// 编制代码

	private String bzmc;// 编制名称
	
	private String xh;//学号
	
	private String xm;//姓名
	
	private String xydm;//学院代码
	
	private String zydm;//专业代码
	
	private String bjdm;//班级代码
	
	private String nj;//年级
	
	private String xn;//学年
	
	private String dm1;
	
	private String dm2;
	
	private String dm3;
	
	private String dm4;
	
	private String dm5;

	public String getDm1() {
		return dm1;
	}

	public void setDm1(String dm1) {
		this.dm1 = dm1;
	}

	public String getDm2() {
		return dm2;
	}

	public void setDm2(String dm2) {
		this.dm2 = dm2;
	}

	public String getDm3() {
		return dm3;
	}

	public void setDm3(String dm3) {
		this.dm3 = dm3;
	}

	public String getDm4() {
		return dm4;
	}

	public void setDm4(String dm4) {
		this.dm4 = dm4;
	}

	public String getDm5() {
		return dm5;
	}

	public void setDm5(String dm5) {
		this.dm5 = dm5;
	}

	public String getBzdm() {
		return bzdm;
	}

	public void setBzdm(String bzdm) {
		this.bzdm = bzdm;
	}

	public String getBzmc() {
		return bzmc;
	}

	public void setBzmc(String bzmc) {
		this.bzmc = bzmc;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
