package xgxt.rcgl.gzdx.lxgl;

import org.apache.struts.action.ActionForm;
import xgxt.utils.Pages;

public class LxglForm extends ActionForm{
	private Pages pages = new Pages();
	
	private String sh2;     //学校审核 
	private String shyj2;     //学校审核意见 
	private String xh;     //学号 
	private String sh1;     //学院审核 
	private String jssj;     //结束时间 
	private String jzlxfs;     //家长联系方式 
	private String kssj;     //开始时间 
	private String ts;     //天数 
	private String qsh;     //天数 
	private String sfnyf;     //是否吃年夜饭 
	private String lxyy;     //留校原因 
	private String shyj1;     //学院审核意见 
	private String xydm;	// 学院代码
	private String zydm;	// 专业代码
	private String bjdm;	// 班级代码
	private String xm;		// 姓名
	
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getSh2() {
		return sh2;
	}
	public void setSh2(String sh2) {
		this.sh2 = sh2;
	}
	public String getShyj2() {
		return shyj2;
	}
	public void setShyj2(String shyj2) {
		this.shyj2 = shyj2;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSh1() {
		return sh1;
	}
	public void setSh1(String sh1) {
		this.sh1 = sh1;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getJzlxfs() {
		return jzlxfs;
	}
	public void setJzlxfs(String jzlxfs) {
		this.jzlxfs = jzlxfs;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getSfnyf() {
		return sfnyf;
	}
	public void setSfnyf(String sfnyf) {
		this.sfnyf = sfnyf;
	}
	public String getLxyy() {
		return lxyy;
	}
	public void setLxyy(String lxyy) {
		this.lxyy = lxyy;
	}
	public String getShyj1() {
		return shyj1;
	}
	public void setShyj1(String shyj1) {
		this.shyj1 = shyj1;
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
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
}
