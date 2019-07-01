package xgxt.pjpy.whlgdx;

import java.io.Serializable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 武汉理工大学评奖评优先进班级Model
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-04</p>
 */
@SuppressWarnings("serial")
public class WhlgdxXjbjModel implements Serializable {
	private String xn;//学年
	private String nd;//年度
	private String xq;//学期
	private String xydm;//学院代码
	private String xymc;//学院名称
	private String zydm;//专业代码
	private String zymc;//专业名称
	private String bjdm;//班级代码
	private String bjmc;//班级名称
	private String nj;//年级
	private String xjbjlbdm;//先进班级类别代码
	private String xjbjlbmc;//先进班级类别名称
	private String bz;//备注
	private String userName;//用户名
	private String userType;//用户类型
	private boolean isFdy;//是否为辅导员
	
	public boolean isFdy() {
		return isFdy;
	}
	public void setFdy(boolean isFdy) {
		this.isFdy = isFdy;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
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
	public String getXjbjlbdm() {
		return xjbjlbdm;
	}
	public void setXjbjlbdm(String xjbjlbdm) {
		this.xjbjlbdm = xjbjlbdm;
	}
	public String getXjbjlbmc() {
		return xjbjlbmc;
	}
	public void setXjbjlbmc(String xjbjlbmc) {
		this.xjbjlbmc = xjbjlbmc;
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
}
