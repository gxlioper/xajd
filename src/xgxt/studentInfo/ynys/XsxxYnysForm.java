package xgxt.studentInfo.ynys;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class XsxxYnysForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Pages pages = new Pages();
	private String tableName;
	private String userName;
	private String xh;
	private String xm;
	private String xb;
	private String xz;
	private String nj;
	private String xydm;
	private String xymc;
	private String zydm;
	private String zymc;
	private String bjdm;
	private String bjmc;
	private boolean isFdy;
	private String sfyby;
	private String byny;
	private String sfzx;
	private String jg;
	private String mzdm;
	private String mzmc;
	private String zzmmdm;
	private String zzmmmc;
	private String jtcyxm;
	private String ssbh;
	private String zw;
	private String syd;
	private String sfzh;
	private String xjztm;
	private String jgshen;
	private String jgshi;
	private String jgxian;
	private String rxrq;
	private String csrq;
	private String sjhm;
	private String pycc;
	private String sfdk;
	private String jtnsrd;
	private String jtnsrg;
	private String lxdh1;//家庭电话
	private String tc;   //特长
	
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
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
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
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
	public boolean isFdy() {
		return isFdy;
	}
	public void setFdy(boolean isFdy) {
		this.isFdy = isFdy;
	}
	public String getSfyby() {
		return sfyby;
	}
	public void setSfyby(String sfyby) {
		this.sfyby = sfyby;
	}
	public String getByny() {
		return byny;
	}
	public void setByny(String byny) {
		this.byny = byny;
	}
	public String getSfzx() {
		return sfzx;
	}
	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}
	public String getJg() {
		return jg;
	}
	public void setJg(String jg) {
		this.jg = jg;
	}
	public String getMzdm() {
		return mzdm;
	}
	public void setMzdm(String mzdm) {
		this.mzdm = mzdm;
	}
	public String getZzmmdm() {
		return zzmmdm;
	}
	public void setZzmmdm(String zzmmdm) {
		this.zzmmdm = zzmmdm;
	}
	public String getMzmc() {
		return mzmc;
	}
	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}
	public String getZzmmmc() {
		return zzmmmc;
	}
	public void setZzmmmc(String zzmmmc) {
		this.zzmmmc = zzmmmc;
	}
	public String getJtcyxm() {
		return jtcyxm;
	}
	public void setJtcyxm(String jtcyxm) {
		this.jtcyxm = jtcyxm;
	}
	public String getSsbh() {
		return ssbh;
	}
	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public String getSyd() {
		return syd;
	}
	public void setSyd(String syd) {
		this.syd = syd;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getXjztm() {
		return xjztm;
	}
	public void setXjztm(String xjztm) {
		this.xjztm = xjztm;
	}
	public String getJgshen() {
		return jgshen;
	}
	public void setJgshen(String jgshen) {
		this.jgshen = jgshen;
	}
	public String getJgshi() {
		return jgshi;
	}
	public void setJgshi(String jgshi) {
		this.jgshi = jgshi;
	}
	public String getJgxian() {
		return jgxian;
	}
	public void setJgxian(String jgxian) {
		this.jgxian = jgxian;
	}
	public String getRxrq() {
		return rxrq;
	}
	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getPycc() {
		return pycc;
	}
	public void setPycc(String pycc) {
		this.pycc = pycc;
	}
	
	public String getSfdk() {
		return sfdk;
	}
	public void setSfdk(String sfdk) {
		this.sfdk = sfdk;
	}
	public String getJtnsrd() {
		return jtnsrd;
	}
	public void setJtnsrd(String jtnsrd) {
		this.jtnsrd = jtnsrd;
	}
	public String getJtnsrg() {
		return jtnsrg;
	}
	public void setJtnsrg(String jtnsrg) {
		this.jtnsrg = jtnsrg;
	}
	public String getLxdh1() {
		return lxdh1;
	}
	public void setLxdh1(String lxdh1) {
		this.lxdh1 = lxdh1;
	}
	public String getTc() {
		return tc;
	}
	public void setTc(String tc) {
		this.tc = tc;
	}	
}
