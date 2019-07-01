package xgxt.qgzx;

import org.apache.struts.action.ActionForm;
/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 勤工助学-酬金发放-各部门用工表统计-actionForm类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author HongLin
 * @version 1.0
 */
public class QgzxGbmygExcelActionForm extends ActionForm{
	
	//获取部门
	private String yrdwdm;//用人单位代码表,用人单位代码
	private String yrdwmc;//用人单位代码表,用人单位名称
	private String dlm;//用人单位代码表,登录名，同yrdwdm一起是主键
	//获取地点，可以有多个地点的,和部门有关联
	private String sqdw;//用于关联用人单位代码表,用人单位代码字段
	private String gwdm;//岗位名称
	private String gwsbsj;//岗位申报时间，主键
	//获取人数，主键是xh和gwdm、gwsbsj
	private String xyyj;//学院审核
	private String xxyj;//学校审核
	//费用，主键xh，gwdm，sqsj，nd，yf，ffsj
	private String cjje;//酬金金额
	private String ffsj;//发放时间
	private String ffsjks;//发放时间开始
	private String ffsjjs;//发放时间结束
	private String xh;//学号
	private String sqsj;//岗位申报时间
	private String nd;//年度
	private String yf;//月份
	private String xm;
	private String xydm;//学院
	private String xymc;
	private String zydm;//专业
	private String zymc;
	private String bjdm;//班级
	private String bjmc;
	private String fslx;
	private String xxsh;//学校审核，是否通过 
	
	private String bz;//备注
	private String gwxzmc;//岗位
	
	
	public String getYrdwdm() {
		return yrdwdm;
	}
	public void setYrdwdm(String yrdwdm) {
		this.yrdwdm = yrdwdm;
	}
	public String getYrdwmc() {
		return yrdwmc;
	}
	public void setYrdwmc(String yrdwmc) {
		this.yrdwmc = yrdwmc;
	}
	public String getDlm() {
		return dlm;
	}
	public void setDlm(String dlm) {
		this.dlm = dlm;
	}
	public String getSqdw() {
		return sqdw;
	}
	public void setSqdw(String sqdw) {
		this.sqdw = sqdw;
	}
	public String getGwdm() {
		return gwdm;
	}
	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}
	public String getGwsbsj() {
		return gwsbsj;
	}
	public void setGwsbsj(String gwsbsj) {
		this.gwsbsj = gwsbsj;
	}
	public String getXyyj() {
		return xyyj;
	}
	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}
	public String getXxyj() {
		return xxyj;
	}
	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}
	public String getCjje() {
		return cjje;
	}
	public void setCjje(String cjje) {
		this.cjje = cjje;
	}
	public String getFfsj() {
		return ffsj;
	}
	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
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
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getFfsjks() {
		return ffsjks;
	}
	public void setFfsjks(String ffsjks) {
		this.ffsjks = ffsjks;
	}
	public String getFfsjjs() {
		return ffsjjs;
	}
	public void setFfsjjs(String ffsjjs) {
		this.ffsjjs = ffsjjs;
	}
	public String getFslx() {
		return fslx;
	}
	public void setFslx(String fslx) {
		this.fslx = fslx;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getGwxzmc() {
		return gwxzmc;
	}
	public void setGwxzmc(String gwxzmc) {
		this.gwxzmc = gwxzmc;
	}
	
}
