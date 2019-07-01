package xgxt.dtjs.zjlg.zbgl;

import xgxt.utils.Pages;

public class ZbglModel {

	private String[] checkVal;// 批处理

	private String xysh;// 学院审核

	private String xxsh;// 学校审核

	private String userName;// 用户名

	private String xh;// 学号

	private String xm;// 姓名

	private String xb;// 性别

	private String nj;// 年级

	private String xn;// 学年

	private String xq;// 学期

	private String nd;// 年度

	private String xydm;// 学院代码

	private String xymc;// 学院名称

	private String zydm;// 专业代码

	private String zymc;// 专业名称

	private String bjdm;// 班级代码

	private String bjmc;// 班级名称

	private String bz;// 备注

	private String lx;// 类型

	private String id;// ID

	private String zzzt;// 在职状态
	
	private String pycc;

	private String rdsjdks;

	private String rdsjdjs;
	
	private String ssxx;// '所属学院';

	private String zbdm; // 支部代码
	
	private String zbmc;// '支部名称';
	
	private String[] zbcy; // 支部成员

	private String pxxmdm;// 培训项目代码
	
	private String zgh;// '职工号';

	private String fdyxm;// '辅导员姓名';
	
	// 通用分页
	Pages pages = new Pages();

	public Pages getPages() {
		return pages;
	}

	public String getFdyxm() {
		return fdyxm;
	}

	public void setFdyxm(String fdyxm) {
		this.fdyxm = fdyxm;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String[] getZbcy() {
		return zbcy;
	}

	public void setZbcy(String[] zbcy) {
		this.zbcy = zbcy;
	}

	public String getZbdm() {
		return zbdm;
	}

	public void setZbdm(String zbdm) {
		this.zbdm = zbdm;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZbmc() {
		return zbmc;
	}

	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}

	public String getSsxx() {
		return ssxx;
	}

	public void setSsxx(String ssxx) {
		this.ssxx = ssxx;
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

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
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

	public String getPycc() {
		return pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	public String getRdsjdjs() {
		return rdsjdjs;
	}

	public void setRdsjdjs(String rdsjdjs) {
		this.rdsjdjs = rdsjdjs;
	}

	public String getRdsjdks() {
		return rdsjdks;
	}

	public void setRdsjdks(String rdsjdks) {
		this.rdsjdks = rdsjdks;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
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

	public String getZzzt() {
		return zzzt;
	}

	public void setZzzt(String zzzt) {
		this.zzzt = zzzt;
	}

	public String getPxxmdm() {
		return pxxmdm;
	}

	public void setPxxmdm(String pxxmdm) {
		this.pxxmdm = pxxmdm;
	}
}
