
package xgxt.studentInfo.model;

import java.io.Serializable;

public class StudentStatusModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2484104705357139164L;

	private String ydjzrq;  //异动截止日期
	private String xh;
	
	private String ydxh;//异动序号
	
	private String clwh;//处理文号
	
	private String ydlbdm;//异动类别代码
	
	private String ydyy;//异动原因
	
	private String ydsm;//异动说明
	
	private String ydqxydm;//异动前学院代码
	
	private String ydqxymc;//异动前学院名称
	
	private String ydqzydm;//异动前专业代码
	
	private String ydqzymc;//异动前专业名称
	
	private String ydqbjmc;//异动前班级名称
	
	private String ydqbjdm;//异动前班级代码
	
	private String ydqnj;//异动前年级
	
	private String ydqxz;//异动前学制
	
	private String ydqxjztm;//异动前学籍状态码
	
	private String xymc;//学院名称
	
	private String zymc;//专业名称
	
	private String bjmc;//班级名称
	
	private String xz;//学制
	
	private String xjztm;//学籍状态码
	
	private String ydrq;//异动日期
	
	private String ydhxydm;//异动后学院代码
	
	private String ydhzydm;//异动后专业代码
	
	private String ydhbjdm;//异动后班级代码
	
	private String ydhnj;//异动后年级
	
	private String ydhxz;//异动后学制
	
	private String ydhxjztm;//异动后学籍状态
	
	private String sfzx;//是否在校
	
	private String xszt;//学生状态
	
	public String getSfzx() {
		return sfzx;
	}

	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getClwh() {
		return clwh;
	}

	public void setClwh(String clwh) {
		this.clwh = clwh;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXjztm() {
		return xjztm;
	}

	public void setXjztm(String xjztm) {
		this.xjztm = xjztm;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	public String getYdhbjdm() {
		return ydhbjdm;
	}

	public void setYdhbjdm(String ydhbjdm) {
		this.ydhbjdm = ydhbjdm;
	}

	public String getYdhnj() {
		return ydhnj;
	}

	public void setYdhnj(String ydhnj) {
		this.ydhnj = ydhnj;
	}

	public String getYdhxjztm() {
		return ydhxjztm;
	}

	public void setYdhxjztm(String ydhxjztm) {
		this.ydhxjztm = ydhxjztm;
	}

	public String getYdhxydm() {
		return ydhxydm;
	}

	public void setYdhxydm(String ydhxydm) {
		this.ydhxydm = ydhxydm;
	}

	public String getYdhxz() {
		return ydhxz;
	}

	public void setYdhxz(String ydhxz) {
		this.ydhxz = ydhxz;
	}

	public String getYdhzydm() {
		return ydhzydm;
	}

	public void setYdhzydm(String ydhzydm) {
		this.ydhzydm = ydhzydm;
	}

	public String getYdlbdm() {
		return ydlbdm;
	}

	public void setYdlbdm(String ydlbdm) {
		this.ydlbdm = ydlbdm;
	}

	public String getYdqbjdm() {
		return ydqbjdm;
	}

	public void setYdqbjdm(String ydqbjdm) {
		this.ydqbjdm = ydqbjdm;
	}

	public String getYdqbjmc() {
		return ydqbjmc;
	}

	public void setYdqbjmc(String ydqbjmc) {
		this.ydqbjmc = ydqbjmc;
	}

	public String getYdqnj() {
		return ydqnj;
	}

	public void setYdqnj(String ydqnj) {
		this.ydqnj = ydqnj;
	}

	public String getYdqxjztm() {
		return ydqxjztm;
	}

	public void setYdqxjztm(String ydqxjztm) {
		this.ydqxjztm = ydqxjztm;
	}

	public String getYdqxydm() {
		return ydqxydm;
	}

	public void setYdqxydm(String ydqxydm) {
		this.ydqxydm = ydqxydm;
	}

	public String getYdqxymc() {
		return ydqxymc;
	}

	public void setYdqxymc(String ydqxymc) {
		this.ydqxymc = ydqxymc;
	}

	public String getYdqxz() {
		return ydqxz;
	}

	public void setYdqxz(String ydqxz) {
		this.ydqxz = ydqxz;
	}

	public String getYdqzydm() {
		return ydqzydm;
	}

	public void setYdqzydm(String ydqzydm) {
		this.ydqzydm = ydqzydm;
	}

	public String getYdqzymc() {
		return ydqzymc;
	}

	public void setYdqzymc(String ydqzymc) {
		this.ydqzymc = ydqzymc;
	}

	public String getYdrq() {
		return ydrq;
	}

	public void setYdrq(String ydrq) {
		this.ydrq = ydrq;
	}

	public String getYdsm() {
		return ydsm;
	}

	public void setYdsm(String ydsm) {
		this.ydsm = ydsm;
	}

	public String getYdxh() {
		return ydxh;
	}

	public void setYdxh(String ydxh) {
		this.ydxh = ydxh;
	}

	public String getYdyy() {
		return ydyy;
	}

	public void setYdyy(String ydyy) {
		this.ydyy = ydyy;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getXszt() {
		return xszt;
	}

	public void setXszt(String xszt) {
		this.xszt = xszt;
	}

	public String getYdjzrq() {
		return ydjzrq;
	}

	public void setYdjzrq(String ydjzrq) {
		this.ydjzrq = ydjzrq;
	}
}
