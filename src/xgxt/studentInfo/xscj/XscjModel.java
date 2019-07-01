package xgxt.studentInfo.xscj;

import java.io.Serializable;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 池州学院学生成绩维护Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 宏琳
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2012-03-14
 * </p>
 */
public class XscjModel implements Serializable {

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
	
	private String cjlx;//成绩类型
	
	private String djksmc;
	
	private String kcmc;//课程名称

	public String getKcmc() {
		return kcmc;
	}

	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
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

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
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

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String[] getKcxz() {
		return kcxz;
	}

	public void setKcxz(String[] kcxz) {
		this.kcxz = kcxz;
	}
	
	public String getCjlx() {
		return cjlx;
	}

	public void setCjlx(String cjlx) {
		this.cjlx = cjlx;
	}

	public String getDjksmc() {
		return djksmc;
	}

	public void setDjksmc(String djksmc) {
		this.djksmc = djksmc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
