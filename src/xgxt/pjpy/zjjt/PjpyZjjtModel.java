package xgxt.pjpy.zjjt;

import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class PjpyZjjtModel {

	Pages pages = new Pages();

	FormFile uploadFile;// 上传文件
	
	private String pxcc;
	
	private String fdyQx;// 辅导员权限

	private String bzrQx;// 班主任权限

	private String[] checkVal;// 批处理

	private String xysh;// 学院审核

	private String queryequals_xysh;

	private String xxsh;// 学校审核

	private String queryequals_xxsh;

	private String userName;// 用户名

	private String[] pjxh;// 评奖学号
	
	private String xh;// 学号

	private String querylike_xh;

	private String xm;// 姓名

	private String querylike_xm;

	private String xb;// 性别

	private String queryequals_xb;

	private String nj;// 年级

	private String queryequals_nj;

	private String xn;// 学年

	private String queryequals_xn;

	private String xq;// 学期

	private String queryequals_xq;

	private String nd;// 年度

	private String queryequals_nd;

	private String xydm;// 学院代码

	private String xymc;// 学院名称

	private String queryequals_xydm;

	private String zydm;// 专业代码

	private String queryequals_zydm;

	private String zymc;// 专业名称

	private String bjdm;// 班级代码

	private String queryequals_bjdm;

	private String bjmc;// 班级名称

	private String bz;// 备注

	private String lx;// 类型

	private String id;// ID

	private String mklx;// 模块类型

	/* 评奖评优 操行分维护 */

	private String xqdm;// 校区代码

	private String queryequals_xqdm;

	private String lddm;// 楼栋代码

	private String queryequals_lddm;

	private String cs;// 层数

	private String queryequals_cs;

	private String qsh;// 寝室号

	private String queryequals_qsh;

	private String qy;// '区域';

	private String lb;// '类别';

	private String xx;// '细项';

	private String[] jb1;// '级别1';

	private String[] jb2;// '级别2';

	private String[] jb3;// '级别3';

	private String[] jjf;// '加减分';

	private String[] fz;// '分值';

	private String[] rq;// '日期';

	private String[] sy;// '事由';

	private String[] shjg;// '审核结果';
	
	private String[] cxbz;// '操行备注';
	
	private String tjfs;// 统计方式；
	
	public String getTjfs() {
		return tjfs;
	}

	public void setTjfs(String tjfs) {
		this.tjfs = tjfs;
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

	public String getBzrQx() {
		return bzrQx;
	}
	
	public String getPxcc() {
		return pxcc;
	}

	public void setPxcc(String pxcc) {
		this.pxcc = pxcc;
	}

	public void setBzrQx(String bzrQx) {
		this.bzrQx = bzrQx;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String[] getCxbz() {
		return cxbz;
	}

	public void setCxbz(String[] cxbz) {
		this.cxbz = cxbz;
	}

	public String getFdyQx() {
		return fdyQx;
	}

	public void setFdyQx(String fdyQx) {
		this.fdyQx = fdyQx;
	}

	public String[] getFz() {
		return fz;
	}

	public void setFz(String[] fz) {
		this.fz = fz;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getJb1() {
		return jb1;
	}

	public void setJb1(String[] jb1) {
		this.jb1 = jb1;
	}

	public String[] getJb2() {
		return jb2;
	}

	public void setJb2(String[] jb2) {
		this.jb2 = jb2;
	}

	public String[] getJb3() {
		return jb3;
	}

	public void setJb3(String[] jb3) {
		this.jb3 = jb3;
	}

	public String[] getJjf() {
		return jjf;
	}

	public void setJjf(String[] jjf) {
		this.jjf = jjf;
	}

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getLddm() {
		return lddm;
	}

	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getMklx() {
		return mklx;
	}

	public void setMklx(String mklx) {
		this.mklx = mklx;
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

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}

	public String getQueryequals_cs() {
		return queryequals_cs;
	}

	public void setQueryequals_cs(String queryequals_cs) {
		this.queryequals_cs = queryequals_cs;
	}

	public String getQueryequals_lddm() {
		return queryequals_lddm;
	}

	public void setQueryequals_lddm(String queryequals_lddm) {
		this.queryequals_lddm = queryequals_lddm;
	}

	public String getQueryequals_nd() {
		return queryequals_nd;
	}

	public void setQueryequals_nd(String queryequals_nd) {
		this.queryequals_nd = queryequals_nd;
	}

	public String getQueryequals_nj() {
		return queryequals_nj;
	}

	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}

	public String getQueryequals_qsh() {
		return queryequals_qsh;
	}

	public void setQueryequals_qsh(String queryequals_qsh) {
		this.queryequals_qsh = queryequals_qsh;
	}

	public String getQueryequals_xb() {
		return queryequals_xb;
	}

	public void setQueryequals_xb(String queryequals_xb) {
		this.queryequals_xb = queryequals_xb;
	}

	public String getQueryequals_xn() {
		return queryequals_xn;
	}

	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}

	public String getQueryequals_xq() {
		return queryequals_xq;
	}

	public void setQueryequals_xq(String queryequals_xq) {
		this.queryequals_xq = queryequals_xq;
	}

	public String getQueryequals_xqdm() {
		return queryequals_xqdm;
	}

	public void setQueryequals_xqdm(String queryequals_xqdm) {
		this.queryequals_xqdm = queryequals_xqdm;
	}

	public String getQueryequals_xxsh() {
		return queryequals_xxsh;
	}

	public void setQueryequals_xxsh(String queryequals_xxsh) {
		this.queryequals_xxsh = queryequals_xxsh;
	}

	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}

	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}

	public String getQueryequals_xysh() {
		return queryequals_xysh;
	}

	public void setQueryequals_xysh(String queryequals_xysh) {
		this.queryequals_xysh = queryequals_xysh;
	}

	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}

	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}

	public String getQuerylike_xh() {
		return querylike_xh;
	}

	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}

	public String getQuerylike_xm() {
		return querylike_xm;
	}

	public void setQuerylike_xm(String querylike_xm) {
		this.querylike_xm = querylike_xm;
	}

	public String getQy() {
		return qy;
	}

	public void setQy(String qy) {
		this.qy = qy;
	}

	public String[] getRq() {
		return rq;
	}

	public void setRq(String[] rq) {
		this.rq = rq;
	}

	public String[] getShjg() {
		return shjg;
	}

	public void setShjg(String[] shjg) {
		this.shjg = shjg;
	}

	public String[] getSy() {
		return sy;
	}

	public void setSy(String[] sy) {
		this.sy = sy;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
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

	public String getXqdm() {
		return xqdm;
	}

	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}

	public String getXx() {
		return xx;
	}

	public void setXx(String xx) {
		this.xx = xx;
	}

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
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

	public String[] getPjxh() {
		return pjxh;
	}

	public void setPjxh(String[] pjxh) {
		this.pjxh = pjxh;
	}
	

}
