
package xgxt.pjpy.jgsdx;

import java.io.Serializable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学评奖评优综合素质和学习成绩MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-026</p>
 */
public class ZhszandXxcjSaveModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String zhszpm;//综合素质排名
	private String xxcjpm;//学习成绩排名
	private String bz;//备注
	private String sfsf;//是否师范
	private String xh;//学号
	private String xq;//学期
	private String xn;//学年
	private String jxjdm;//奖学金代码
	private String pkValue;//学键
	private String shzt;//
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getSfsf() {
		return sfsf;
	}
	public void setSfsf(String sfsf) {
		this.sfsf = sfsf;
	}
	public String getXxcjpm() {
		return xxcjpm;
	}
	public void setXxcjpm(String xxcjpm) {
		this.xxcjpm = xxcjpm;
	}
	public String getZhszpm() {
		return zhszpm;
	}
	public void setZhszpm(String zhszpm) {
		this.zhszpm = zhszpm;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getJxjdm() {
		return jxjdm;
	}
	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}
}
