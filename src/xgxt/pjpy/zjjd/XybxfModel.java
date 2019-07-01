
package xgxt.pjpy.zjjd;

import xgxt.base.DealString;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江机电评奖评优校园平均分MODEL
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-05</p>
 */
public class XybxfModel {

	private String xn;//学年
	private String xq;//学期
	private String yf;//月份
	private String xh;//学号
	private String xm;//姓名
	private String xydm;//学院代码
	private String zydm;//专业代码
	private String bjdm;//班级代码
	private String jf;//加分
	private String kf;//扣分
	private String sx;//事项
	private String nj;
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getJf() {
		return jf;
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
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	@Override
	public String toString() {
		// TODO 自动生成方法存根
		return DealString.toGBK(xm);
	}
}
