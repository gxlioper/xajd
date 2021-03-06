package xgxt.pjpy.ahjg;

import java.io.Serializable;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 安徽建筑工业学院学生成绩查询Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 李涛
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2008-05-30
 * </p>
 */
public class PjpyAhjgXscjQryModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xydm;// 学院代码

	private String zydm;// 专业代码

	private String bjdm;// 班级代码

	private String nj;// 年级

	private String xn;// 学年

	private String nd;// 年度

	private String xh;// 学号

	private String xm;// 姓名

	private String xq;// 学期

	private String[] kcxz;// 课程性质
	
	private String djksmc;

	public String[] getKcxz() {
		return kcxz;
	}

	public void setKcxz(String[] kcxz) {
		this.kcxz = kcxz;
	}

	//	
	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
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

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
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

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getDjksmc() {
		return djksmc;
	}

	public void setDjksmc(String djksmc) {
		this.djksmc = djksmc;
	}

}
