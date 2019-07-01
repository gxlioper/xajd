package xgxt.pjpy.zjcm.xfjs;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class PjpyXfjsForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private boolean fdy;
	private String[] cbv;//主键列
	private String xydm;//学院代码
	private String zydm;//专业代码
	private String bjdm;//班级代码
	private String nj;//年级
	private String xh;//学号
	private String xm;//姓名
	private String xn;//学年
	private String xq;//学期
	private String xymc;
	private String zymc;
	private String bjmc;
	private String xb;
	private String jclxmc;
	private String xqmc;
	private String wjlxmc;
	private String ccrq;//抽查日期
	private String ccrqks;//抽查日期开始
	private String ccrqjs;//抽查日期结束
	private String jclxdm;//检测类型代码
	private String qjlxdm;//请假类型代码
	private String qjlxmc;//请假类型名称
	private String ydrs;//应到人数
	private String sdrs;//实到人数
	private String qqrs;//缺勤人数
	private String wjrs;//违纪人数
	private String fdyclsj;//辅导员处理时间
	private String wjlxdm;//违纪类型代码
	private String wjcs;//违纪次数	
	private String bz;//备注
	private String fdyclbz;//辅导员处理备注
	private String fdysjclsj;//辅导员时间处理时间
	private String xxsh;//学校审核
	private String ccyhlx;//抽查用户的类型
	Pages pages = new Pages();
	private String[] xhArr;
	private String[] wjlxdmArr;
	private String[] qjlxdmArr;
	private String[] wjcsArr;
	private String[] bzArr;
	//查询条件
	private String sfyr;
	private String sfzgdsjcl;
	private String sfcl;	
	private String dyym;
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCcrq() {
		return ccrq;
	}
	public void setCcrq(String ccrq) {
		this.ccrq = ccrq;
	}
	public String getJclxdm() {
		return jclxdm;
	}
	public void setJclxdm(String jclxdm) {
		this.jclxdm = jclxdm;
	}
	public String getQjlxdm() {
		return qjlxdm;
	}
	public void setQjlxdm(String qjlxdm) {
		this.qjlxdm = qjlxdm;
	}
	public String getYdrs() {
		return ydrs;
	}
	public void setYdrs(String ydrs) {
		this.ydrs = ydrs;
	}
	public String getSdrs() {
		return sdrs;
	}
	public void setSdrs(String sdrs) {
		this.sdrs = sdrs;
	}
	public String getQqrs() {
		return qqrs;
	}
	public void setQqrs(String qqrs) {
		this.qqrs = qqrs;
	}
	public String getWjlxdm() {
		return wjlxdm;
	}
	public void setWjlxdm(String wjlxdm) {
		this.wjlxdm = wjlxdm;
	}
	public String getWjcs() {
		return wjcs;
	}
	public void setWjcs(String wjcs) {
		this.wjcs = wjcs;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
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
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getJclxmc() {
		return jclxmc;
	}
	public void setJclxmc(String jclxmc) {
		this.jclxmc = jclxmc;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public String getWjlxmc() {
		return wjlxmc;
	}
	public void setWjlxmc(String wjlxmc) {
		this.wjlxmc = wjlxmc;
	}
	public String[] getXhArr() {
		return xhArr;
	}
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}
	public String[] getWjlxdmArr() {
		return wjlxdmArr;
	}
	public void setWjlxdmArr(String[] wjlxdmArr) {
		this.wjlxdmArr = wjlxdmArr;
	}
	public String[] getQjlxdmArr() {
		return qjlxdmArr;
	}
	public void setQjlxdmArr(String[] qjlxdmArr) {
		this.qjlxdmArr = qjlxdmArr;
	}
	public String[] getWjcsArr() {
		return wjcsArr;
	}
	public void setWjcsArr(String[] wjcsArr) {
		this.wjcsArr = wjcsArr;
	}
	public String[] getBzArr() {
		return bzArr;
	}
	public void setBzArr(String[] bzArr) {
		this.bzArr = bzArr;
	}
	public String getWjrs() {
		return wjrs;
	}
	public void setWjrs(String wjrs) {
		this.wjrs = wjrs;
	}
	public String getFdyclsj() {
		return fdyclsj;
	}
	public void setFdyclsj(String fdyclsj) {
		this.fdyclsj = fdyclsj;
	}
	public String getFdyclbz() {
		return fdyclbz;
	}
	public void setFdyclbz(String fdyclbz) {
		this.fdyclbz = fdyclbz;
	}
	public String getFdysjclsj() {
		return fdysjclsj;
	}
	public void setFdysjclsj(String fdysjclsj) {
		this.fdysjclsj = fdysjclsj;
	}
	public String getSfyr() {
		return sfyr;
	}
	public void setSfyr(String sfyr) {
		this.sfyr = sfyr;
	}
	public String getSfzgdsjcl() {
		return sfzgdsjcl;
	}
	public void setSfzgdsjcl(String sfzgdsjcl) {
		this.sfzgdsjcl = sfzgdsjcl;
	}
	public String getSfcl() {
		return sfcl;
	}
	public void setSfcl(String sfcl) {
		this.sfcl = sfcl;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getDyym() {
		return dyym;
	}
	public void setDyym(String dyym) {
		this.dyym = dyym;
	}
	public String getQjlxmc() {
		return qjlxmc;
	}
	public void setQjlxmc(String qjlxmc) {
		this.qjlxmc = qjlxmc;
	}
	public String getCcyhlx() {
		return ccyhlx;
	}
	public void setCcyhlx(String ccyhlx) {
		this.ccyhlx = ccyhlx;
	}
	public boolean isFdy() {
		return fdy;
	}
	public void setFdy(boolean fdy) {
		this.fdy = fdy;
	}
	public String getCcrqks() {
		return ccrqks;
	}
	public void setCcrqks(String ccrqks) {
		this.ccrqks = ccrqks;
	}
	public String getCcrqjs() {
		return ccrqjs;
	}
	public void setCcrqjs(String ccrqjs) {
		this.ccrqjs = ccrqjs;
	}
	
}
