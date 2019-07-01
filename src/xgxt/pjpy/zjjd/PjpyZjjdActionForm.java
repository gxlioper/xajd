
package xgxt.pjpy.zjjd;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江机电评奖评优ActionForm
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-05</p>
 */
public class PjpyZjjdActionForm extends ActionForm {

	/**
	 * 自动生成版本ID
	 */
	private static final long serialVersionUID = 1L;

	private String xh;//学号
	private String xn;//学年
	private String nd;//年度
	private String nj;//年级
	private String xq;//学期
	private String xydm;//学院代码
	private String zydm;//专业代码
	private String bjdm;//班级代码
	private String xm;//姓名
	private String yf;//月份
	private String jf;//加分
	private String kf;//扣分
	private String sx;//事项
	private String[] cbv;
	private String sh;//审核
	private String yj;//审核意见
	private String drzw;//担任职务
	private String rzsj;//任职时间
	private String khdj;//考核等级
	private String bz;//备注
	private String xybxf;//校园表现分
	private String gybxf;//公寓表现分
	private String xybxjf;//校园表现总加分
	private String xybxkf;//校园表现总扣分
	private String gybxjf;//公寓表现总加分
	private String gybxkf;//公寓表现总扣分
	private String dyfjf;//德育附加分
	private String dyzf;//德育总分
	private String dyxj;//德育小计
	private String pjcj;//平均成绩
	private String pjcjmc;//平均成绩名称
	private String zyfjf;//智育附加分
	private String zyzf;//智育总分
	private String zyxj;//智育小计
	private String tycj;//体育成绩
	private String tyfjf;//体育附加分
	private String tyzf;//体育总分
	private String tyxj;//体育小计
	private String zhszcpzf;//综合素质测评总分
	private String zhszcpmc;//综合素质测评名称
	private String tzjkbzdj;//体质健康标准
	private String bjpddj;//班级评定等级
	private String szxyj;//所在系意见
	private String xysh;//学院审核
	private String xxsh;//学校审核
	private String xyshyj;//学院审核意见
	private String xxshyj;//学校审核意见
	private String fdyyj;//辅导员意见
	private String jxjdm;//奖学金代码
	private String rychdm;//荣誉称号代码
	private String sfcf;//是否处分
	private String wydj;//外语等级
	private String jsjdj;//计算机等级
	private String rq;//日期

	//通用
	Pages pages = new Pages();
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getJxjdm() {
		return jxjdm;
	}
	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getSh() {
		return sh;
	}
	public void setSh(String sh) {
		this.sh = sh;
	}
	public String getYj() {
		return yj;
	}
	public void setYj(String yj) {
		this.yj = yj;
	}
	public String getJf() {
		return jf;
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public void setJf(String jf) {
		this.jf = jf;
	}
	public String getKf() {
		return kf;
	}
	public void setKf(String kf) {
		this.kf = kf;
	}
	public String getSx() {
		return sx;
	}
	public void setSx(String sx) {
		this.sx = sx;
	}
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
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	public String getDrzw() {
		return drzw;
	}
	public void setDrzw(String drzw) {
		this.drzw = drzw;
	}
	public String getKhdj() {
		return khdj;
	}
	public void setKhdj(String khdj) {
		this.khdj = khdj;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public String getDyfjf() {
		return dyfjf;
	}
	public void setDyfjf(String dyfjf) {
		this.dyfjf = dyfjf;
	}
	public String getDyxj() {
		return dyxj;
	}
	public void setDyxj(String dyxj) {
		this.dyxj = dyxj;
	}
	public String getDyzf() {
		return dyzf;
	}
	public void setDyzf(String dyzf) {
		this.dyzf = dyzf;
	}
	public String getGybxf() {
		return gybxf;
	}
	public void setGybxf(String gybxf) {
		this.gybxf = gybxf;
	}
	public String getPjcj() {
		return pjcj;
	}
	public void setPjcj(String pjcj) {
		this.pjcj = pjcj;
	}
	public String getPjcjmc() {
		return pjcjmc;
	}
	public void setPjcjmc(String pjcjmc) {
		this.pjcjmc = pjcjmc;
	}
	public String getTycj() {
		return tycj;
	}
	public void setTycj(String tycj) {
		this.tycj = tycj;
	}
	public String getTyfjf() {
		return tyfjf;
	}
	public void setTyfjf(String tyfjf) {
		this.tyfjf = tyfjf;
	}
	public String getTyxj() {
		return tyxj;
	}
	public void setTyxj(String tyxj) {
		this.tyxj = tyxj;
	}
	public String getTyzf() {
		return tyzf;
	}
	public void setTyzf(String tyzf) {
		this.tyzf = tyzf;
	}
	public String getXybxf() {
		return xybxf;
	}
	public void setXybxf(String xybxf) {
		this.xybxf = xybxf;
	}
	public String getZhszcpmc() {
		return zhszcpmc;
	}
	public void setZhszcpmc(String zhszcpmc) {
		this.zhszcpmc = zhszcpmc;
	}
	public String getZhszcpzf() {
		return zhszcpzf;
	}
	public void setZhszcpzf(String zhszcpzf) {
		this.zhszcpzf = zhszcpzf;
	}
	public String getZyfjf() {
		return zyfjf;
	}
	public void setZyfjf(String zyfjf) {
		this.zyfjf = zyfjf;
	}
	public String getZyxj() {
		return zyxj;
	}
	public void setZyxj(String zyxj) {
		this.zyxj = zyxj;
	}
	public String getZyzf() {
		return zyzf;
	}
	public void setZyzf(String zyzf) {
		this.zyzf = zyzf;
	}
	public String getBjpddj() {
		return bjpddj;
	}
	public void setBjpddj(String bjpddj) {
		this.bjpddj = bjpddj;
	}
	public String getFdyyj() {
		return fdyyj;
	}
	public void setFdyyj(String fdyyj) {
		this.fdyyj = fdyyj;
	}
	public String getSzxyj() {
		return szxyj;
	}
	public void setSzxyj(String szxyj) {
		this.szxyj = szxyj;
	}
	public String getTzjkbzdj() {
		return tzjkbzdj;
	}
	public void setTzjkbzdj(String tzjkbzdj) {
		this.tzjkbzdj = tzjkbzdj;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXxshyj() {
		return xxshyj;
	}
	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public String getXyshyj() {
		return xyshyj;
	}
	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}
	public String getJsjdj() {
		return jsjdj;
	}
	public void setJsjdj(String jsjdj) {
		this.jsjdj = jsjdj;
	}
	public String getRychdm() {
		return rychdm;
	}
	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}
	public String getSfcf() {
		return sfcf;
	}
	public void setSfcf(String sfcf) {
		this.sfcf = sfcf;
	}
	public String getWydj() {
		return wydj;
	}
	public void setWydj(String wydj) {
		this.wydj = wydj;
	}
	public String getGybxjf() {
		return gybxjf;
	}
	public void setGybxjf(String gybxjf) {
		this.gybxjf = gybxjf;
	}
	public String getGybxkf() {
		return gybxkf;
	}
	public void setGybxkf(String gybxkf) {
		this.gybxkf = gybxkf;
	}
	public String getXybxjf() {
		return xybxjf;
	}
	public void setXybxjf(String xybxjf) {
		this.xybxjf = xybxjf;
	}
	public String getXybxkf() {
		return xybxkf;
	}
	public void setXybxkf(String xybxkf) {
		this.xybxkf = xybxkf;
	}
}
