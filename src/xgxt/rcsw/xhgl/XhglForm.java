package xgxt.rcsw.xhgl;

import org.apache.struts.action.ActionForm;

import xgxt.form.User;
import xgxt.utils.Pages;

public class XhglForm extends ActionForm{
	
	User user = new User();
	
	Pages pages=new Pages();
	
	private String xm;
	
	private String nj;    //年级
	
	private String xydm;   //学院代码
	
	private String zydm;   //专业代码
	
	private String bjdm;   //班级代码
	
	private String xh;     //学号
	
	private String bz;     //备注
	
	private String xn;     //学年
	
	private String xq;     //学期
	
	private String jbr;     //经办人
	
	private String ffsj;     //发放时间
	
	private String nd;     //年度
	
	private String sfff;   //是否发放
	
	private String pkValue; // 主键
	
	private String fdysh;
	
	private String xysh;
	
	private String xxsh;
	
	private String []pkV;//checkBox 数组
	
	private String select_jbr;//经办人
	
	public String getSelect_jbr() {
		return select_jbr;
	}
	public void setSelect_jbr(String select_jbr) {
		this.select_jbr = select_jbr;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getFfsj() {
		return ffsj;
	}
	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
	}
	public String getJbr() {
		return jbr;
	}
	public void setJbr(String jbr) {
		this.jbr = jbr;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
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
	public String[] getPkV() {
		return pkV;
	}
	public void setPkV(String[] pkV) {
		this.pkV = pkV;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getSfff() {
		return sfff;
	}
	public void setSfff(String sfff) {
		this.sfff = sfff;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
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
	public String getFdysh() {
		return fdysh;
	}
	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
