package xgxt.pjpy.whlgdx;

import java.io.Serializable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 武汉理工大学评奖评优综合素质测评Model
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-01</p>
 */
@SuppressWarnings("serial")
public class WhlgdxZhszcpModel implements Serializable{
	private String xn;//学年
	private String nd;//年度
	private String xq;//学期
	private String xh;//学号
	private String xm;//姓名
	private String xb;//性别
	private String nj;//年级
	private String xydm;//学院代码
	private String xymc;//学院名称
	private String zydm;//专业代码
	private String zymc;//专业名称
	private String bjdm;//班级代码
	private String bjmc;//班级名称
	private String dcj;//德成绩
	private String xxpjcj;//学习平均成绩
	private String xxpjcjpm;//学习平均层次排名
	private String xxpjcjpmbl;//学习平均成绩排名比例
	private String sztzzf;//素质拓展总分
	private String zhszcpcjpm;//综合素质测评成绩排名
	private String zhszcpcjpmbl;//综合素质测评成绩排名比例
	private String dkzdfs;//单科最低分数
	private String wygjqk;//外语过级情况
	private String zhszcpzf;//综合素质测评总分
	private String stszzf;//身体素质总分
	private String bz;//备注
	
	public String getStszzf() {
		return stszzf;
	}
	public void setStszzf(String stszzf) {
		this.stszzf = stszzf;
	}
	public String getZhszcpzf() {
		return zhszcpzf;
	}
	public void setZhszcpzf(String zhszcpzf) {
		this.zhszcpzf = zhszcpzf;
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
	public String getDcj() {
		return dcj;
	}
	public void setDcj(String dcj) {
		this.dcj = dcj;
	}
	public String getDkzdfs() {
		return dkzdfs;
	}
	public void setDkzdfs(String dkzdfs) {
		this.dkzdfs = dkzdfs;
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
	public String getSztzzf() {
		return sztzzf;
	}
	public void setSztzzf(String sztzzf) {
		this.sztzzf = sztzzf;
	}
	public String getWygjqk() {
		return wygjqk;
	}
	public void setWygjqk(String wygjqk) {
		this.wygjqk = wygjqk;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
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
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXxpjcj() {
		return xxpjcj;
	}
	public void setXxpjcj(String xxpjcj) {
		this.xxpjcj = xxpjcj;
	}
	public String getXxpjcjpm() {
		return xxpjcjpm;
	}
	public void setXxpjcjpm(String xxpjcjpm) {
		this.xxpjcjpm = xxpjcjpm;
	}
	public String getXxpjcjpmbl() {
		return xxpjcjpmbl;
	}
	public void setXxpjcjpmbl(String xxpjcjpmbl) {
		this.xxpjcjpmbl = xxpjcjpmbl;
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
	public String getZhszcpcjpm() {
		return zhszcpcjpm;
	}
	public void setZhszcpcjpm(String zhszcpcjpm) {
		this.zhszcpcjpm = zhszcpcjpm;
	}
	public String getZhszcpcjpmbl() {
		return zhszcpcjpmbl;
	}
	public void setZhszcpcjpmbl(String zhszcpcjpmbl) {
		this.zhszcpcjpmbl = zhszcpcjpmbl;
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
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}

}
