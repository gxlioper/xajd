/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.qgzx.zgdzdx;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国地质大学勤工助学Form
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-23</p>
 */
public class QgzxZgdzdxForm extends ActionForm implements Serializable {
	private static final long serialVersionUID = 2591488729161758124L;
	Pages pages = new Pages();
	private String[] checkVal;// 批处理
	private String userName;
	private String userType;
	private String[] cbv;
	private String sfygw;
	private String xqmc;
	///黑名单
	private String pk;
	private String pkValue;
	private String xh;
	private String xm;
	private String xb;
	private String nj;
	private String xydm;
	private String zydm;
	private String bjdm;
	private String xymc;
	private String zymc;
	private String bjmc;
	private String jrsj;
	private String bz;
	//校外岗位信息
	private String gwmc;
	private String sqdwmc;
	private String sqdwdz;
	private String xn;
	private String nd;
	private String xq;
	private String gzkssj;
	private String gzjssj;
	private String xyrs;
	private String xyknsrs;
	private String jcfs;
	private String jcbz;
	private String gzsj;
	private String gznr;
	private String gzyq;
	private String sqsj;
	private String lxdh;	
	//学生岗位申请
	private String kcjqgzxsj;
	private String sfpks;
	private String sqly;	
	//学生岗位申请审核
	private String gwsqsj;
	private String zpsj;
	private String zpdz;
	private String xxsh;	
	//学院人数分配
	private String fprs;	
	//勤工助学申请
	private String xysh;	
	//勤工助学之星
	private String zwjd;//自我鉴定
	private String szbmyj;//用人单位意见
	private String xyyj;//学院意见
	private String xxyj;//学校意见
	private String yesNo;//审核结果
	private String xssq;//学生申请	
	private String yhtc;//有何特长
	private String zzmmdm;//政治面貌代码	
	private String yrdwdm;//用人单位代码
	private String gwdm;//岗位代码
	private String gwsbsj;//岗位上报时间
	private String xmdm;//项目代码
	private String kcsgz;//可从事工作
	private String jjqk;//家庭经济情况
	private String gwxzdm;//岗位性质代码
	private String lsgw;//临时岗位
	private String gwxz;//岗位性质
	private String fdyname;//辅导员名字
	private String isFdy;
	private String sfwj;//是否违纪
	private String gkms;//挂科次数
	private String pdfh;//判断符号
	private int plczrs=1; //批量操作人数
	
	//勤工助学消息提示
	private String save_kssj;//开始时间
	private String save_jssj;//结束时间
	private String save_tsnr;//提示内容
	
	private String querygreaterequal_kssj;
	private String querygreaterequal_jssj;
	private String querylessequal_kssj;
	private String querylessequal_jssj;
	
	
	
	public String getQuerygreaterequal_jssj() {
		return querygreaterequal_jssj;
	}
	public void setQuerygreaterequal_jssj(String querygreaterequal_jssj) {
		this.querygreaterequal_jssj = querygreaterequal_jssj;
	}
	public String getQuerygreaterequal_kssj() {
		return querygreaterequal_kssj;
	}
	public void setQuerygreaterequal_kssj(String querygreaterequal_kssj) {
		this.querygreaterequal_kssj = querygreaterequal_kssj;
	}
	public String getQuerylessequal_jssj() {
		return querylessequal_jssj;
	}
	public void setQuerylessequal_jssj(String querylessequal_jssj) {
		this.querylessequal_jssj = querylessequal_jssj;
	}
	public String getQuerylessequal_kssj() {
		return querylessequal_kssj;
	}
	public void setQuerylessequal_kssj(String querylessequal_kssj) {
		this.querylessequal_kssj = querylessequal_kssj;
	}
	public String getSave_jssj() {
		return save_jssj;
	}
	public void setSave_jssj(String save_jssj) {
		this.save_jssj = save_jssj;
	}
	public String getSave_kssj() {
		return save_kssj;
	}
	public void setSave_kssj(String save_kssj) {
		this.save_kssj = save_kssj;
	}
	public String getSave_tsnr() {
		return save_tsnr;
	}
	public void setSave_tsnr(String save_tsnr) {
		this.save_tsnr = save_tsnr;
	}
	public String getLsgw() {
		return lsgw;
	}
	public void setLsgw(String lsgw) {
		this.lsgw = lsgw;
	}
	public String getGwdm() {
		return gwdm;
	}
	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}	
	public String getGwsbsj() {
		return gwsbsj;
	}
	public void setGwsbsj(String gwsbsj) {
		this.gwsbsj = gwsbsj;
	}
	public String getYrdwdm() {
		return yrdwdm;
	}
	public void setYrdwdm(String yrdwdm) {
		this.yrdwdm = yrdwdm;
	}
	public String getXssq() {
		return xssq;
	}
	public void setXssq(String xssq) {
		this.xssq = xssq;
	}
	public String getZwjd() {
		return zwjd;
	}
	public void setZwjd(String zwjd) {
		this.zwjd = zwjd;
	}
	public String getFprs() {
		return fprs;
	}
	public void setFprs(String fprs) {
		this.fprs = fprs;
	}
	public String getGwsqsj() {
		return gwsqsj;
	}
	public void setGwsqsj(String gwsqsj) {
		this.gwsqsj = gwsqsj;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getKcjqgzxsj() {
		return kcjqgzxsj;
	}
	public void setKcjqgzxsj(String kcjqgzxsj) {
		this.kcjqgzxsj = kcjqgzxsj;
	}
	public String getSfpks() {
		return sfpks;
	}
	public void setSfpks(String sfpks) {
		this.sfpks = sfpks;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
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
	public String getJrsj() {
		return jrsj;
	}
	public void setJrsj(String jrsj) {
		this.jrsj = jrsj;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
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
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getGwmc() {
		return gwmc;
	}
	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}
	public String getGzjssj() {
		return gzjssj;
	}
	public void setGzjssj(String gzjssj) {
		this.gzjssj = gzjssj;
	}
	public String getGzkssj() {
		return gzkssj;
	}
	public void setGzkssj(String gzkssj) {
		this.gzkssj = gzkssj;
	}
	public String getGznr() {
		return gznr;
	}
	public void setGznr(String gznr) {
		this.gznr = gznr;
	}
	public String getGzsj() {
		return gzsj;
	}
	public void setGzsj(String gzsj) {
		this.gzsj = gzsj;
	}
	public String getGzyq() {
		return gzyq;
	}
	public void setGzyq(String gzyq) {
		this.gzyq = gzyq;
	}
	public String getJcbz() {
		return jcbz;
	}
	public void setJcbz(String jcbz) {
		this.jcbz = jcbz;
	}
	public String getJcfs() {
		return jcfs;
	}
	public void setJcfs(String jcfs) {
		this.jcfs = jcfs;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getSqdwdz() {
		return sqdwdz;
	}
	public void setSqdwdz(String sqdwdz) {
		this.sqdwdz = sqdwdz;
	}
	public String getSqdwmc() {
		return sqdwmc;
	}
	public void setSqdwmc(String sqdwmc) {
		this.sqdwmc = sqdwmc;
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
	public String getXyknsrs() {
		return xyknsrs;
	}
	public void setXyknsrs(String xyknsrs) {
		this.xyknsrs = xyknsrs;
	}
	public String getXyrs() {
		return xyrs;
	}
	public void setXyrs(String xyrs) {
		this.xyrs = xyrs;
	}
	public String getZpdz() {
		return zpdz;
	}
	public void setZpdz(String zpdz) {
		this.zpdz = zpdz;
	}
	public String getZpsj() {
		return zpsj;
	}
	public void setZpsj(String zpsj) {
		this.zpsj = zpsj;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
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
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}
	public String getSzbmyj() {
		return szbmyj;
	}
	public void setSzbmyj(String szbmyj) {
		this.szbmyj = szbmyj;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getSfygw() {
		return sfygw;
	}
	public void setSfygw(String sfygw) {
		this.sfygw = sfygw;
	}
	public String getYhtc() {
		return yhtc;
	}
	public void setYhtc(String yhtc) {
		this.yhtc = yhtc;
	}
	public String getZzmmdm() {
		return zzmmdm;
	}
	public void setZzmmdm(String zzmmdm) {
		this.zzmmdm = zzmmdm;
	}
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getKcsgz() {
		return kcsgz;
	}
	public void setKcsgz(String kcsgz) {
		this.kcsgz = kcsgz;
	}
	public String getJjqk() {
		return jjqk;
	}
	public void setJjqk(String jjqk) {
		this.jjqk = jjqk;
	}
	public String getGwxzdm() {
		return gwxzdm;
	}
	public void setGwxzdm(String gwxzdm) {
		this.gwxzdm = gwxzdm;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String[] getCheckVal() {
		return checkVal;
	}
	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}
	public String getGwxz() {
		return gwxz;
	}
	public void setGwxz(String gwxz) {
		this.gwxz = gwxz;
	}
	public String getFdyname() {
		return fdyname;
	}
	public void setFdyname(String fdyname) {
		this.fdyname = fdyname;
	}
	public String getIsFdy() {
		return isFdy;
	}
	public void setIsFdy(String isFdy) {
		this.isFdy = isFdy;
	}
	public String getGkms() {
		return gkms;
	}
	public void setGkms(String gkms) {
		this.gkms = gkms;
	}
	public String getSfwj() {
		return sfwj;
	}
	public void setSfwj(String sfwj) {
		this.sfwj = sfwj;
	}
	public String getPdfh() {
		return pdfh;
	}
	public void setPdfh(String pdfh) {
		this.pdfh = pdfh;
	}
	public int getPlczrs() {
		return plczrs;
	}
	public void setPlczrs(int plczrs) {
		this.plczrs = plczrs;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}	
	
}