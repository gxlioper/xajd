
package xgxt.pjpy.nblg;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 宁波理工学院评奖评优ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-30</p>
 */
public class PjpyNblgActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//自动生成版本ID
	
	private String xn;//学年
	private String nd;//年度
	private String xq;//学期
	private String xh;//学号
	private String nj;//年级
	private String xydm;//学院代码
	private String zydm;//专业代码
	private String bjdm;//班级代码
	private String djksdm;//等级考试代码
	private String cjlx;//成绩类型
	private String tj;
	private String bl;
	private String jxjdm;
	private String[] cbv;
	private String fs;
	private String bj;
	private String pm;
	private String rychdm;
	private String tjdm;
	private String tjmc;
	private String tjxz;
	Pages pages = new Pages();
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getCjlx() {
		return cjlx;
	}
	public void setCjlx(String cjlx) {
		this.cjlx = cjlx;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getDjksdm() {
		return djksdm;
	}
	public void setDjksdm(String djksdm) {
		this.djksdm = djksdm;
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
	public String getBl() {
		return bl;
	}
	public void setBl(String bl) {
		this.bl = bl;
	}
	public String getJxjdm() {
		return jxjdm;
	}
	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}
	public String getTj() {
		return tj;
	}
	public void setTj(String tj) {
		this.tj = tj;
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	public String getFs() {
		return fs;
	}
	public void setFs(String fs) {
		this.fs = fs;
	}
	public String getPm() {
		return pm;
	}
	public void setPm(String pm) {
		this.pm = pm;
	}
	public String getRychdm() {
		return rychdm;
	}
	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}
	public String getTjdm() {
		return tjdm;
	}
	public void setTjdm(String tjdm) {
		this.tjdm = tjdm;
	}
	public String getTjmc() {
		return tjmc;
	}
	public void setTjmc(String tjmc) {
		this.tjmc = tjmc;
	}
	public String getTjxz() {
		return tjxz;
	}
	public void setTjxz(String tjxz) {
		this.tjxz = tjxz;
	}
}
