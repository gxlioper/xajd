
package xgxt.pjpy.hbsf;

import java.io.Serializable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 湖北师范学院评奖评优奖学金申请保存MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-10</p>
 */
public class JxjsqSaveModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xh;//学号
	private String xn;//学年
	private String nd;//年度
	private String jxjdm;//奖学金代码
	private String drzw;//担任职务
	private String wysp;//外语水平
	private String sjhm;//手机号码
	private String sqly;//申请理由
	private String kyxm;//科研项目
	private String xxjl;//学习简历
	private String jxj1;//奖学金1
	private String shyg1;//三好优干分1
	private String sxddf;//思想道德素质分
	private String kxwhf;//科学文化分
	private String sxjkf;//身心健康分
	private String kcjqf;//课程加权分
	private String pm;//排名
	private String zhkpzf1;//综合测评总分
	private String zhkppm;//综合测评排名
	private String bjghkms;//不及格缓考门数
	private String cljz;//处理记载
	private String tyhgbz1;//体育合格标准
	public String getBjghkms() {
		return bjghkms;
	}
	public void setBjghkms(String bjghkms) {
		this.bjghkms = bjghkms;
	}
	public String getCljz() {
		return cljz;
	}
	public void setCljz(String cljz) {
		this.cljz = cljz;
	}
	public String getDrzw() {
		return drzw;
	}
	public void setDrzw(String drzw) {
		this.drzw = drzw;
	}
	public String getJxj1() {
		return jxj1;
	}
	public void setJxj1(String jxj1) {
		this.jxj1 = jxj1;
	}
	public String getJxjdm() {
		return jxjdm;
	}
	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}
	public String getKcjqf() {
		return kcjqf;
	}
	public void setKcjqf(String kcjqf) {
		this.kcjqf = kcjqf;
	}
	public String getKxwhf() {
		return kxwhf;
	}
	public void setKxwhf(String kxwhf) {
		this.kxwhf = kxwhf;
	}
	public String getKyxm() {
		return kyxm;
	}
	public void setKyxm(String kyxm) {
		this.kyxm = kyxm;
	}
	public String getPm() {
		return pm;
	}
	public void setPm(String pm) {
		this.pm = pm;
	}
	public String getShyg1() {
		return shyg1;
	}
	public void setShyg1(String shyg1) {
		this.shyg1 = shyg1;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getSxddf() {
		return sxddf;
	}
	public void setSxddf(String sxddf) {
		this.sxddf = sxddf;
	}
	public String getSxjkf() {
		return sxjkf;
	}
	public void setSxjkf(String sxjkf) {
		this.sxjkf = sxjkf;
	}
	public String getTyhgbz1() {
		return tyhgbz1;
	}
	public void setTyhgbz1(String tyhgbz1) {
		this.tyhgbz1 = tyhgbz1;
	}
	public String getWysp() {
		return wysp;
	}
	public void setWysp(String wysp) {
		this.wysp = wysp;
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
	public String getXxjl() {
		return xxjl;
	}
	public void setXxjl(String xxjl) {
		this.xxjl = xxjl;
	}
	public String getZhkppm() {
		return zhkppm;
	}
	public void setZhkppm(String zhkppm) {
		this.zhkppm = zhkppm;
	}
	public String getZhkpzf1() {
		return zhkpzf1;
	}
	public void setZhkpzf1(String zhkpzf1) {
		this.zhkpzf1 = zhkpzf1;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
}
