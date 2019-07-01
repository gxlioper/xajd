package xgxt.pjpy.whlgdx;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WhlgJxjModel implements Serializable {
	private String xn;//学年
	private String xq;//学期
	private String nd;//年度
	private String xydm;// 学院代码
	private String zydm;// 专业代码
	private String nj;// 年级
	private String bjdm;// 班级代码
	private String sqly;// 申请理由
	private String jxjfl;// 奖学金分类
	private String jxjdm;// 奖学金代码
	private String xh;// 学号
	private String sfpks;// 是否贫困生
	private String gkfs;// 高考分数
	private String gzashjqk;// 高中奥赛获奖情况
	private String sbdj;// 申报等级
	private String sfdlsq;// 是否单列申请
	private String dlsqly;//单列申请理由
	private String lwmc;//论文名称
	private String qkmc;//期刊名称
	private String fbsj;//发表时间
	private String sfdyzz;//是否第一作者
	private String pkVal;//主键
	private String userType;//用户类型
	private String yesNo;//审核结果
	private String xxshyj;//学校审核意见
	private String xyshyj;//学院审核意见
	private String fdyyj;//辅导员审核意见
	private String rychdm;//荣誉称号代码
	private String tjFlag;//条件标记
	
	public String getTjFlag() {
		return tjFlag;
	}
	public void setTjFlag(String tjFlag) {
		this.tjFlag = tjFlag;
	}
	public String getRychdm() {
		return rychdm;
	}
	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}
	public String getFdyyj() {
		return fdyyj;
	}
	public void setFdyyj(String fdyyj) {
		this.fdyyj = fdyyj;
	}
	public String getXxshyj() {
		return xxshyj;
	}
	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}
	public String getXyshyj() {
		return xyshyj;
	}
	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}
	public String getPkVal() {
		return pkVal;
	}
	public void setPkVal(String pkVal) {
		this.pkVal = pkVal;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}
	public String getFbsj() {
		return fbsj;
	}
	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}
	public String getLwmc() {
		return lwmc;
	}
	public void setLwmc(String lwmc) {
		this.lwmc = lwmc;
	}
	public String getQkmc() {
		return qkmc;
	}
	public void setQkmc(String qkmc) {
		this.qkmc = qkmc;
	}
	public String getSfdyzz() {
		return sfdyzz;
	}
	public void setSfdyzz(String sfdyzz) {
		this.sfdyzz = sfdyzz;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getDlsqly() {
		return dlsqly;
	}
	public void setDlsqly(String dlsqly) {
		this.dlsqly = dlsqly;
	}
	public String getGkfs() {
		return gkfs;
	}
	public void setGkfs(String gkfs) {
		this.gkfs = gkfs;
	}
	public String getGzashjqk() {
		return gzashjqk;
	}
	public void setGzashjqk(String gzashjqk) {
		this.gzashjqk = gzashjqk;
	}
	public String getJxjdm() {
		return jxjdm;
	}
	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}
	public String getJxjfl() {
		return jxjfl;
	}
	public void setJxjfl(String jxjfl) {
		this.jxjfl = jxjfl;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getSbdj() {
		return sbdj;
	}
	public void setSbdj(String sbdj) {
		this.sbdj = sbdj;
	}
	public String getSfdlsq() {
		return sfdlsq;
	}
	public void setSfdlsq(String sfdlsq) {
		this.sfdlsq = sfdlsq;
	}
	public String getSfpks() {
		return sfpks;
	}
	public void setSfpks(String sfpks) {
		this.sfpks = sfpks;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
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
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	
}
