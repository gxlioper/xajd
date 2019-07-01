
package xgxt.rcsw.zzlgdx;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.bdsz.BdzdForm;
import xgxt.utils.Pages;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江理工ActionFrom</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-11-27</p>
 */
public class RcswZzlgdxActionForm extends BdzdForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] pk;
	Pages pages = new Pages();
	
	private String xh;
	private String zxbxjwhcdbx;
	private String lrsj;
	private String xm;
	private String xb;
	private String mzmc;
	private String zzmmmc;
	private String csrq;
	private String rxrq;
	private String xz;
	private String nj;
	private String sfzh;
	private String xydm;
	private String xymc;
	private String zydm;
	private String zymc;
	private String bjdm;
	private String bjmc;
	private String xxshyj;
	private String lxfs_br;
	private String xysh;
	private String xxsh;
	private String fhrq;
	private String sqqx;
	private String lxfs_jz;
	private String cgrq;
	private String sqly;
	private String sqsj;
	private String xyshyj;
	private String guid;
	private String qjlx;
	private String qjksrq;
	private String qjjzrq;
	private String qjkc1;
	private String qjkcs1;
	private String qjkc2;
	private String qjkcs2;
	private String qjkc3;
	private String qjkcs3;
	private String qjyy;
	private String shjg;
	private String shyj;
	private String sj;
	private String xjhj;
	private String rxqhkszd;
	private String jtxxdz;
	private String fmlxfs;
	private String sfygzjzbty;
	
	private String xmmc;//项目名称
	
	private String shlx;//审核类型
	
	private String zxbx;//在校表现
	
	private String xn;//学年
	
	private String xyyj;//学院意见
	
	private String xxyj;//学校意见
	private String fdysh; //辅导员审核
	private String fdyyj;//辅导员意见
	
//	自定义表单
	private String[] arrZd;

	private String[] arrZdz;
	
	private String[] arrZdlx;
	
	private String[] arrZdcd;
	
	private String[] pkV;
	
	private HashMap<String, ArrayList<HashMap<String, String>>> opslist;
	
	public String[] getArrZd() {
		return arrZd;
	}
	public void setArrZd(String[] arrZd) {
		this.arrZd = arrZd;
	}
	public String[] getArrZdcd() {
		return arrZdcd;
	}
	public void setArrZdcd(String[] arrZdcd) {
		this.arrZdcd = arrZdcd;
	}
	public String[] getArrZdlx() {
		return arrZdlx;
	}
	public void setArrZdlx(String[] arrZdlx) {
		this.arrZdlx = arrZdlx;
	}
	public String[] getArrZdz() {
		return arrZdz;
	}
	public void setArrZdz(String[] arrZdz) {
		this.arrZdz = arrZdz;
	}
	public HashMap<String, ArrayList<HashMap<String, String>>> getOpslist() {
		return opslist;
	}
	public void setOpslist(
			HashMap<String, ArrayList<HashMap<String, String>>> opslist) {
		this.opslist = opslist;
	}
	public String[] getPkV() {
		return pkV;
	}
	public void setPkV(String[] pkV) {
		this.pkV = pkV;
	}
	public String getShlx() {
		return shlx;
	}
	public void setShlx(String shlx) {
		this.shlx = shlx;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getFmlxfs() {
		return fmlxfs;
	}
	public void setFmlxfs(String fmlxfs) {
		this.fmlxfs = fmlxfs;
	}
	public String getJtxxdz() {
		return jtxxdz;
	}
	public void setJtxxdz(String jtxxdz) {
		this.jtxxdz = jtxxdz;
	}
	public String getRxqhkszd() {
		return rxqhkszd;
	}
	public void setRxqhkszd(String rxqhkszd) {
		this.rxqhkszd = rxqhkszd;
	}
	public String getSfygzjzbty() {
		return sfygzjzbty;
	}
	public void setSfygzjzbty(String sfygzjzbty) {
		this.sfygzjzbty = sfygzjzbty;
	}
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = sj;
	}
	public String getXjhj() {
		return xjhj;
	}
	public void setXjhj(String xjhj) {
		this.xjhj = xjhj;
	}
	public String getShjg() {
		return shjg;
	}
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getQjjzrq() {
		return qjjzrq;
	}
	public void setQjjzrq(String qjjzrq) {
		this.qjjzrq = qjjzrq;
	}
	public String getQjkc1() {
		return qjkc1;
	}
	public void setQjkc1(String qjkc1) {
		this.qjkc1 = qjkc1;
	}
	public String getQjkc2() {
		return qjkc2;
	}
	public void setQjkc2(String qjkc2) {
		this.qjkc2 = qjkc2;
	}
	public String getQjkc3() {
		return qjkc3;
	}
	public void setQjkc3(String qjkc3) {
		this.qjkc3 = qjkc3;
	}
	public String getQjkcs1() {
		return qjkcs1;
	}
	public void setQjkcs1(String qjkcs1) {
		this.qjkcs1 = qjkcs1;
	}
	public String getQjkcs2() {
		return qjkcs2;
	}
	public void setQjkcs2(String qjkcs2) {
		this.qjkcs2 = qjkcs2;
	}
	public String getQjkcs3() {
		return qjkcs3;
	}
	public void setQjkcs3(String qjkcs3) {
		this.qjkcs3 = qjkcs3;
	}
	public String getQjksrq() {
		return qjksrq;
	}
	public void setQjksrq(String qjksrq) {
		this.qjksrq = qjksrq;
	}
	public String getQjlx() {
		return qjlx;
	}
	public void setQjlx(String qjlx) {
		this.qjlx = qjlx;
	}
	public String getQjyy() {
		return qjyy;
	}
	public void setQjyy(String qjyy) {
		this.qjyy = qjyy;
	}
	public String getCgrq() {
		return cgrq;
	}
	public void setCgrq(String cgrq) {
		this.cgrq = cgrq;
	}
	public String getFhrq() {
		return fhrq;
	}
	public void setFhrq(String fhrq) {
		this.fhrq = fhrq;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getLxfs_br() {
		return lxfs_br;
	}
	public void setLxfs_br(String lxfs_br) {
		this.lxfs_br = lxfs_br;
	}
	public String getLxfs_jz() {
		return lxfs_jz;
	}
	public void setLxfs_jz(String lxfs_jz) {
		this.lxfs_jz = lxfs_jz;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getSqqx() {
		return sqqx;
	}
	public void setSqqx(String sqqx) {
		this.sqqx = sqqx;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXxshyj() {
		return xxshyj;
	}
	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public String getXyshyj() {
		return xyshyj;
	}
	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
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
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getLrsj() {
		return lrsj;
	}
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	public String getMzmc() {
		return mzmc;
	}
	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
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
	public String[] getPk() {
		return pk;
	}
	public void setPk(String[] pk) {
		this.pk = pk;
	}
	public String getRxrq() {
		return rxrq;
	}
	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
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
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getZxbxjwhcdbx() {
		return zxbxjwhcdbx;
	}
	public void setZxbxjwhcdbx(String zxbxjwhcdbx) {
		this.zxbxjwhcdbx = zxbxjwhcdbx;
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
	public String getZzmmmc() {
		return zzmmmc;
	}
	public void setZzmmmc(String zzmmmc) {
		this.zzmmmc = zzmmmc;
	}
	public String getZxbx() {
		return zxbx;
	}
	public void setZxbx(String zxbx) {
		this.zxbx = zxbx;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXxyj() {
		return xxyj;
	}
	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}
	public String getXyyj() {
		return xyyj;
	}
	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}
	public String getFdysh() {
		return fdysh;
	}
	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}
	public String getFdyyj() {
		return fdyyj;
	}
	public void setFdyyj(String fdyyj) {
		this.fdyyj = fdyyj;
	}
}
