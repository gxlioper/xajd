package xgxt.xszz.ynys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;

public class XszzYnysActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7257261936551302858L;

	/** COMMON 在Action 操作中有用 */
	private String doType ;

	private String action ;

	private String tableName ; //表

	private String realTable ; //视图

	private String userType;   //用户类型
	
	private String userName ;  //用户名
	
	private List<HashMap<String, String>> columnCN = new ArrayList<HashMap<String, String>>();

	private String pkVal ;// 作为主键查询
	
	private String xxmc  ;//学校名称
	
	private String shlb ; //审核类别【通过 未审核 不通过】
	
	private String sqms; //申请模式

	/** 学生基本信息 */
	private String xh; //学号

	private String xn; //学年

	private String xm; //姓名

	private String xb; //性别

	private String nj; //年级

	private String xz; //学制
	
	private String xq; //学期
 
	private String csrq;

	private String sfzh;

	private String mzmc;

	private String zzmmmc;

	private String xydm; //

	private String xymc; //

	private String zydm; //

	private String zymc; //

	private String bjdm; //

	private String bjmc; //

	/** 国家励志奖学金 */
	private String nd; //年度

	private String rxny;
	
	private String lxdh;

	private String sxncj;

	private String chhzjl;

	private String jthk;  //家庭户口

	private String jtrkzs;

	private String jtyzsr;

	private String rjysr;

	private String srly;

	private String jtzz;

	private String yzbm;

	private String xxcj;

	private String sqly;

	private String sqsj;

	private String xysh;

	private String xyshyj;

	private String xxsh;

	private String xxshyj;
	
	private String kh; //卡号
	
	private String jb; //级别
	private String rxrq;
	private String hjje;
	private String hjqx;

	public void do_Common_GBK() {
		// this.xh = DealString.toGBK(xh);
		// this.xn = DealString.toGBK(xn);
		this.xm = DealString.toGBK(xm);
		this.xb = DealString.toGBK(xb);
		// this.nj = DealString.toGBK(nj);
		this.xz = DealString.toGBK(xz);
		this.csrq = DealString.toGBK(csrq);
		this.sfzh = DealString.toGBK(sfzh);
		this.mzmc = DealString.toGBK(mzmc);
		this.zzmmmc = DealString.toGBK(zzmmmc);
		// this.xydm = DealString.toGBK(xydm);
		this.xymc = DealString.toGBK(xymc);
		// this.zydm = DealString.toGBK(zydm);
		this.zymc = DealString.toGBK(zymc);
		// this.bjdm = DealString.toGBK(bjdm);
		this.bjmc = DealString.toGBK(bjmc);
		this.xxmc = DealString.toGBK(xxmc);
		this.shlb = DealString.toGBK(shlb);
	}

	/** GBK 国家励志奖学金 */
	public void do_Gjlzjxj_GBK() {
		this.do_Common_GBK();
		// this.nd = DealString.toGBK(nd);
		// this.xh = DealString.toGBK(xh);
		this.lxdh = DealString.toGBK(lxdh);
		this.sxncj = DealString.toGBK(sxncj); // 上学期成绩
		this.chhzjl = DealString.toGBK(chhzjl);
		this.jthk = DealString.toGBK(jthk);  //家庭户口   
		this.jtrkzs = DealString.toGBK(jtrkzs);
		this.jtyzsr = DealString.toGBK(jtyzsr);
		this.rjysr = DealString.toGBK(rjysr);
		this.srly = DealString.toGBK(srly);
		this.jtzz = DealString.toGBK(jtzz);
		this.yzbm = DealString.toGBK(yzbm);
		this.xxcj = DealString.toGBK(xxcj);
		this.sqly = DealString.toGBK(sqly);
		this.sqsj = DealString.toGBK(sqsj);
		this.xysh = DealString.toGBK(xysh);
		this.xyshyj = DealString.toGBK(xyshyj);
		this.xxsh = DealString.toGBK(xxsh);
		this.xxshyj = DealString.toGBK(xxshyj);
		this.rxny = DealString.toGBK(rxny);
		//this.kh = DealString.toGBK(kh);
	}

	/** set Result */
	public String setResult(boolean result) {
		return (result == true) ? "ok" : "no";
	}

	public String getChhzjl() {
		return chhzjl;
	}

	public void setChhzjl(String chhzjl) {
		this.chhzjl = chhzjl;
	}

	public String getJthk() {
		return jthk;
	}

	public void setJthk(String jthk) {
		this.jthk = jthk;
	}

	public String getJtrkzs() {
		return jtrkzs;
	}

	public void setJtrkzs(String jtrkzs) {
		this.jtrkzs = jtrkzs;
	}

	public String getJtyzsr() {
		return jtyzsr;
	}

	public void setJtyzsr(String jtyzsr) {
		this.jtyzsr = jtyzsr;
	}

	public String getJtzz() {
		return jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
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

	public String getRjysr() {
		return rjysr;
	}

	public void setRjysr(String rjysr) {
		this.rjysr = rjysr;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getSrly() {
		return srly;
	}

	public void setSrly(String srly) {
		this.srly = srly;
	}

	public String getSxncj() {
		return sxncj;
	}

	public void setSxncj(String sxncj) {
		this.sxncj = sxncj;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXxcj() {
		return xxcj;
	}

	public void setXxcj(String xxcj) {
		this.xxcj = xxcj;
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

	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<HashMap<String, String>> getColumnCN() {
		return columnCN;
	}

	public void setColumnCN(List<HashMap<String, String>> columnCN) {
		this.columnCN = columnCN;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getPkVal() {
		return pkVal;
	}

	public void setPkVal(String pkVal) {
		this.pkVal = pkVal;
	}

	public String getRealTable() {
		return realTable;
	}

	public void setRealTable(String realTable) {
		this.realTable = realTable;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getXxmc() {
		return xxmc;
	}

	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}

	public String getRxny() {
		return rxny;
	}

	public void setRxny(String rxny) {
		this.rxny = rxny;
	}

	public String getShlb() {
		return shlb;
	}

	public void setShlb(String shlb) {
		this.shlb = shlb;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getKh() {
		return kh;
	}

	public void setKh(String kh) {
		this.kh = kh;
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

	public String getSqms() {
		return sqms;
	}

	public void setSqms(String sqms) {
		this.sqms = sqms;
	}

	public String getHjje() {
		return hjje;
	}

	public void setHjje(String hjje) {
		this.hjje = hjje;
	}

	public String getHjqx() {
		return hjqx;
	}

	public void setHjqx(String hjqx) {
		this.hjqx = hjqx;
	}

	public String getRxrq() {
		return rxrq;
	}

	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}

	public String getJb() {
		return jb;
	}

	public void setJb(String jb) {
		this.jb = jb;
	}

}
