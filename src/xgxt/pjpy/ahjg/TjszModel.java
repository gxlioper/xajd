
package xgxt.pjpy.ahjg;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 安徽建筑工业学院评奖评优TJSZMODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-2</p>
 */
public class TjszModel {

	private String xmdm;//奖学金代码
	private String tjdm;//条件字段名
	private String cslx;//参数类型
	private String zdcz;//字段操作
	private String zdbj;//字段比较
	private String zdval;//字段值
	private String tjzdlyb;//条件字段来源表
	private String xn;
	private String tjmc;
	private String czmc;
	public String getCzmc() {
		return czmc;
	}
	public void setCzmc(String czmc) {
		this.czmc = czmc;
	}
	public String getTjmc() {
		return tjmc;
	}
	public void setTjmc(String tjmc) {
		this.tjmc = tjmc;
	}
	public String getZdval() {
		return zdval;
	}
	public void setZdval(String zdval) {
		this.zdval = zdval;
	}
	public String getCslx() {
		return cslx;
	}
	public void setCslx(String cslx) {
		this.cslx = cslx;
	}
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getTjzdlyb() {
		return tjzdlyb;
	}
	public void setTjzdlyb(String tjzdlyb) {
		this.tjzdlyb = tjzdlyb;
	}
	public String getTjdm() {
		return tjdm;
	}
	public void setTjdm(String tjdm) {
		this.tjdm = tjdm;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getZdbj() {
		return zdbj;
	}
	public void setZdbj(String zdbj) {
		this.zdbj = zdbj;
	}
	public String getZdcz() {
		return zdcz;
	}
	public void setZdcz(String zdcz) {
		this.zdcz = zdcz;
	}
}
