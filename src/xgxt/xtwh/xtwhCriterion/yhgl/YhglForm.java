package xgxt.xtwh.xtwhCriterion.yhgl;

import xgxt.xtwh.xtwhCriterion.QxwhForm;

public class YhglForm extends QxwhForm{
	private String yhm;		// 用户名
	private String mm;		// 密码
	private String xm;		// 姓名
	private String szbm;	// 所在部门
	private String kqzt = "1";	// 开启状态
	private String bz;      // 备注
	private String gnmkdm;	// 功能模块代码
	private String[] yyjs;  // 拥有角色
	private String jsmc;	// 角色名
	private String jslxdm;  // 角色类型代码
	private String jscmdm;	// 角色操作范围代码
	private String xslxlbdm;// 学生类型类别代码
	private String xslxmc;  // 学生类型名称
	private String kssj;	// 开始时间
	private String jssj; 	// 结束时间
	private String sfksq;   // 是否可授权
	private String jsdm;    // 角色代码
	private String mrqx;	// 默认权限
	
	public String getMrqx() {
		return mrqx;
	}
	public void setMrqx(String mrqx) {
		this.mrqx = mrqx;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSzbm() {
		return szbm;
	}
	public void setSzbm(String szbm) {
		this.szbm = szbm;
	}
	public String getKqzt() {
		return kqzt;
	}
	public void setKqzt(String kqzt) {
		this.kqzt = kqzt;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getGnmkdm() {
		return gnmkdm;
	}
	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
	}
	public String[] getYyjs() {
		return yyjs;
	}
	public void setYyjs(String[] yyjs) {
		this.yyjs = yyjs;
	}
	public String getJsmc() {
		return jsmc;
	}
	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}
	public String getJslxdm() {
		return jslxdm;
	}
	public void setJslxdm(String jslxdm) {
		this.jslxdm = jslxdm;
	}
	public String getJscmdm() {
		return jscmdm;
	}
	public void setJscmdm(String jscmdm) {
		this.jscmdm = jscmdm;
	}
	public String getXslxlbdm() {
		return xslxlbdm;
	}
	public void setXslxlbdm(String xslxlbdm) {
		this.xslxlbdm = xslxlbdm;
	}
	public String getXslxmc() {
		return xslxmc;
	}
	public void setXslxmc(String xslxmc) {
		this.xslxmc = xslxmc;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getSfksq() {
		return sfksq;
	}
	public void setSfksq(String sfksq) {
		this.sfksq = sfksq;
	}
	public String getJsdm() {
		return jsdm;
	}
	public void setJsdm(String jsdm) {
		this.jsdm = jsdm;
	}
}
