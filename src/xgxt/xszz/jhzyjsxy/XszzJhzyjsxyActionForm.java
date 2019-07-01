
package xgxt.xszz.jhzyjsxy;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 金华职业技术学院ActionForm</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-09-25</p>
 */
public class XszzJhzyjsxyActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nd;
	private String xh;
	private String xm;
	private String xb;
	private String mzmc;
	private String zzmmmc;
	private String csrq;
	private String xz;
	private String nj;
	private String sfzh;
	private String xydm;
	private String xymc;
	private String zydm;
	private String zymc;
	private String bjdm;
	private String bjmc;
	private String jtrjnsr;
	private String zxlxdh;
	private String sqly;
	private String knlx;
	private String sqsj;
	private String fdysh;
	private String fdyshyj;
	private String fdyshsj;
	private String xysh;
	private String xyshyj;
	private String xyshsj;
	private String xxsh;
	private String xxshyj;
	private String xxshsj;
	
	private String rxrq;
	private String byrq;
	private String yhkh;
	private String ykthm;
	private String jtzzjyb;
	private String qsh;
	private String xxztqk;
	private String lxdh;
	private String hhzjl;
	private String cshzcf;
	private String ywzxdkjje;
	private String ywsshzzjhe;
	private String xfjnqk;
	private String xzxfqk;
	private String hsf;
	private String qtfy;
	private String shf_jtgg;
	private String shf_qtly;
	private String jicy1_cw;
	private String jicy1_xm;
	private String jicy1_nl;
	private String jicy1_sr;
	private String jicy1_dw;
	private String jicy2_cw;
	private String jicy2_xm;
	private String jicy2_nl;
	private String jicy2_sr;
	private String jicy2_dw;
	private String jicy3_cw;
	private String jicy3_xm;
	private String jicy3_nl;
	private String jicy3_sr;
	private String jicy3_dw;
	private String jicy4_cw;
	private String jicy4_xm;
	private String jicy4_nl;
	private String jicy4_sr;
	private String jicy4_dw;
	private String jicy5_cw;
	private String jicy5_xm;
	private String jicy5_nl;
	private String jicy5_sr;
	private String jicy5_dw;
	private String jtzrks;
	private String rjnsr;
	private String jtlx;
	private String jtknqk;
	private String[] knlxList;
	private String[] pk;
	
	private String jthk;
	private String jtyzsr;
	private String rjysr;
	private String srly;
	private String jtzz;
	private String yzbm;
	private String jtcy1_xm;
	private String jtcy1_nl;
	private String jtcy1_gx;
	private String jtcy1_dw;
	private String jtcy2_xm;
	private String jtcy2_nl;
	private String jtcy2_gx;
	private String jtcy2_dw;
	private String jtcy3_xm;
	private String jtcy3_nl;
	private String jtcy3_gx;
	private String jtcy3_dw;
	private String jtcy4_xm;
	private String jtcy4_nl;
	private String jtcy4_gx;
	private String jtcy4_dw;
	private String jtcy5_xm;
	private String jtcy5_nl;
	private String jtcy5_gx;
	private String jtcy5_dw;
	private String zxjdm;
	private String zxjdj;
	private String zxjje;
	
	private String chhzjl;
	private String gxnbxkcs;
	private String yxkcs;
	private String lhkcs;
	private String cjpm;
	private String zhkpcj;
	private String zhkppm;
	
	Pages pages = new Pages();
	
	// 帮困助学基金

	private String jg;// 籍贯

	private String jtyb;// 家庭邮编
	
	private String jtnjjcsr;// 家庭年经济纯收入

	private String jtrks;// 家庭人口数

	private String dy;// 德育

	private String cshzjl;// 曾受何种奖励

	private String cshzwjcf;// 曾受何种违纪处分

	private String zxdkjje;// 助学贷款及金额

	private String gjlzzxjjje;// 获国家、校奖学金金额

	private String gjxjxjje;// 获国家励志、助学金及金额

	private String bxnhknbzjje;// 本学年获困难补助及金额

	private String zxjjdm;// 助学基金资助代码

	private String zxjjdj;// 助学基金资助等级

	private String zxjjje;// 助学基金资助金额
	
	public String getBxnhknbzjje() {
		return bxnhknbzjje;
	}
	public void setBxnhknbzjje(String bxnhknbzjje) {
		this.bxnhknbzjje = bxnhknbzjje;
	}
	public String getCshzjl() {
		return cshzjl;
	}
	public void setCshzjl(String cshzjl) {
		this.cshzjl = cshzjl;
	}
	public String getCshzwjcf() {
		return cshzwjcf;
	}
	public void setCshzwjcf(String cshzwjcf) {
		this.cshzwjcf = cshzwjcf;
	}
	public String getDy() {
		return dy;
	}
	public void setDy(String dy) {
		this.dy = dy;
	}
	public String getGjlzzxjjje() {
		return gjlzzxjjje;
	}
	public void setGjlzzxjjje(String gjlzzxjjje) {
		this.gjlzzxjjje = gjlzzxjjje;
	}
	public String getGjxjxjje() {
		return gjxjxjje;
	}
	public void setGjxjxjje(String gjxjxjje) {
		this.gjxjxjje = gjxjxjje;
	}
	public String getJg() {
		return jg;
	}
	public void setJg(String jg) {
		this.jg = jg;
	}
	public String getJtnjjcsr() {
		return jtnjjcsr;
	}
	public void setJtnjjcsr(String jtnjjcsr) {
		this.jtnjjcsr = jtnjjcsr;
	}
	public String getJtrks() {
		return jtrks;
	}
	public void setJtrks(String jtrks) {
		this.jtrks = jtrks;
	}
	public String getJtyb() {
		return jtyb;
	}
	public void setJtyb(String jtyb) {
		this.jtyb = jtyb;
	}
	public String getZxdkjje() {
		return zxdkjje;
	}
	public void setZxdkjje(String zxdkjje) {
		this.zxdkjje = zxdkjje;
	}
	public String getZxjjdj() {
		return zxjjdj;
	}
	public void setZxjjdj(String zxjjdj) {
		this.zxjjdj = zxjjdj;
	}
	public String getZxjjdm() {
		return zxjjdm;
	}
	public void setZxjjdm(String zxjjdm) {
		this.zxjjdm = zxjjdm;
	}
	public String getZxjjje() {
		return zxjjje;
	}
	public void setZxjjje(String zxjjje) {
		this.zxjjje = zxjjje;
	}
	public String getChhzjl() {
		return chhzjl;
	}
	public void setChhzjl(String chhzjl) {
		this.chhzjl = chhzjl;
	}
	public String getCjpm() {
		return cjpm;
	}
	public void setCjpm(String cjpm) {
		this.cjpm = cjpm;
	}
	public String getGxnbxkcs() {
		return gxnbxkcs;
	}
	public void setGxnbxkcs(String gxnbxkcs) {
		this.gxnbxkcs = gxnbxkcs;
	}
	public String getLhkcs() {
		return lhkcs;
	}
	public void setLhkcs(String lhkcs) {
		this.lhkcs = lhkcs;
	}
	public String getYxkcs() {
		return yxkcs;
	}
	public void setYxkcs(String yxkcs) {
		this.yxkcs = yxkcs;
	}
	public String getZhkpcj() {
		return zhkpcj;
	}
	public void setZhkpcj(String zhkpcj) {
		this.zhkpcj = zhkpcj;
	}
	public String getZhkppm() {
		return zhkppm;
	}
	public void setZhkppm(String zhkppm) {
		this.zhkppm = zhkppm;
	}
	public String getJtcy1_dw() {
		return jtcy1_dw;
	}
	public void setJtcy1_dw(String jtcy1_dw) {
		this.jtcy1_dw = jtcy1_dw;
	}
	public String getJtcy1_gx() {
		return jtcy1_gx;
	}
	public void setJtcy1_gx(String jtcy1_gx) {
		this.jtcy1_gx = jtcy1_gx;
	}
	public String getJtcy1_nl() {
		return jtcy1_nl;
	}
	public void setJtcy1_nl(String jtcy1_nl) {
		this.jtcy1_nl = jtcy1_nl;
	}
	public String getJtcy1_xm() {
		return jtcy1_xm;
	}
	public void setJtcy1_xm(String jtcy1_xm) {
		this.jtcy1_xm = jtcy1_xm;
	}
	public String getJtcy2_dw() {
		return jtcy2_dw;
	}
	public void setJtcy2_dw(String jtcy2_dw) {
		this.jtcy2_dw = jtcy2_dw;
	}
	public String getJtcy2_gx() {
		return jtcy2_gx;
	}
	public void setJtcy2_gx(String jtcy2_gx) {
		this.jtcy2_gx = jtcy2_gx;
	}
	public String getJtcy2_nl() {
		return jtcy2_nl;
	}
	public void setJtcy2_nl(String jtcy2_nl) {
		this.jtcy2_nl = jtcy2_nl;
	}
	public String getJtcy2_xm() {
		return jtcy2_xm;
	}
	public void setJtcy2_xm(String jtcy2_xm) {
		this.jtcy2_xm = jtcy2_xm;
	}
	public String getJtcy3_dw() {
		return jtcy3_dw;
	}
	public void setJtcy3_dw(String jtcy3_dw) {
		this.jtcy3_dw = jtcy3_dw;
	}
	public String getJtcy3_gx() {
		return jtcy3_gx;
	}
	public void setJtcy3_gx(String jtcy3_gx) {
		this.jtcy3_gx = jtcy3_gx;
	}
	public String getJtcy3_nl() {
		return jtcy3_nl;
	}
	public void setJtcy3_nl(String jtcy3_nl) {
		this.jtcy3_nl = jtcy3_nl;
	}
	public String getJtcy3_xm() {
		return jtcy3_xm;
	}
	public void setJtcy3_xm(String jtcy3_xm) {
		this.jtcy3_xm = jtcy3_xm;
	}
	public String getJtcy4_dw() {
		return jtcy4_dw;
	}
	public void setJtcy4_dw(String jtcy4_dw) {
		this.jtcy4_dw = jtcy4_dw;
	}
	public String getJtcy4_gx() {
		return jtcy4_gx;
	}
	public void setJtcy4_gx(String jtcy4_gx) {
		this.jtcy4_gx = jtcy4_gx;
	}
	public String getJtcy4_nl() {
		return jtcy4_nl;
	}
	public void setJtcy4_nl(String jtcy4_nl) {
		this.jtcy4_nl = jtcy4_nl;
	}
	public String getJtcy4_xm() {
		return jtcy4_xm;
	}
	public void setJtcy4_xm(String jtcy4_xm) {
		this.jtcy4_xm = jtcy4_xm;
	}
	public String getJtcy5_dw() {
		return jtcy5_dw;
	}
	public void setJtcy5_dw(String jtcy5_dw) {
		this.jtcy5_dw = jtcy5_dw;
	}
	public String getJtcy5_gx() {
		return jtcy5_gx;
	}
	public void setJtcy5_gx(String jtcy5_gx) {
		this.jtcy5_gx = jtcy5_gx;
	}
	public String getJtcy5_nl() {
		return jtcy5_nl;
	}
	public void setJtcy5_nl(String jtcy5_nl) {
		this.jtcy5_nl = jtcy5_nl;
	}
	public String getJtcy5_xm() {
		return jtcy5_xm;
	}
	public void setJtcy5_xm(String jtcy5_xm) {
		this.jtcy5_xm = jtcy5_xm;
	}
	public String getJthk() {
		return jthk;
	}
	public void setJthk(String jthk) {
		this.jthk = jthk;
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
	public String getRjysr() {
		return rjysr;
	}
	public void setRjysr(String rjysr) {
		this.rjysr = rjysr;
	}
	public String getSrly() {
		return srly;
	}
	public void setSrly(String srly) {
		this.srly = srly;
	}
	public String getYzbm() {
		return yzbm;
	}
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	public String getZxjdj() {
		return zxjdj;
	}
	public void setZxjdj(String zxjdj) {
		this.zxjdj = zxjdj;
	}
	public String getZxjdm() {
		return zxjdm;
	}
	public void setZxjdm(String zxjdm) {
		this.zxjdm = zxjdm;
	}
	public String getZxjje() {
		return zxjje;
	}
	public void setZxjje(String zxjje) {
		this.zxjje = zxjje;
	}
	public String[] getPk() {
		return pk;
	}
	public void setPk(String[] pk) {
		this.pk = pk;
	}
	public String[] getKnlxList() {
		return knlxList;
	}
	public void setKnlxList(String[] knlxList) {
		this.knlxList = knlxList;
	}
	public String getByrq() {
		return byrq;
	}
	public void setByrq(String byrq) {
		this.byrq = byrq;
	}
	public String getCshzcf() {
		return cshzcf;
	}
	public void setCshzcf(String cshzcf) {
		this.cshzcf = cshzcf;
	}
	public String getHhzjl() {
		return hhzjl;
	}
	public void setHhzjl(String hhzjl) {
		this.hhzjl = hhzjl;
	}
	public String getHsf() {
		return hsf;
	}
	public void setHsf(String hsf) {
		this.hsf = hsf;
	}
	public String getJicy1_cw() {
		return jicy1_cw;
	}
	public void setJicy1_cw(String jicy1_cw) {
		this.jicy1_cw = jicy1_cw;
	}
	public String getJicy1_dw() {
		return jicy1_dw;
	}
	public void setJicy1_dw(String jicy1_dw) {
		this.jicy1_dw = jicy1_dw;
	}
	public String getJicy1_nl() {
		return jicy1_nl;
	}
	public void setJicy1_nl(String jicy1_nl) {
		this.jicy1_nl = jicy1_nl;
	}
	public String getJicy1_sr() {
		return jicy1_sr;
	}
	public void setJicy1_sr(String jicy1_sr) {
		this.jicy1_sr = jicy1_sr;
	}
	public String getJicy1_xm() {
		return jicy1_xm;
	}
	public void setJicy1_xm(String jicy1_xm) {
		this.jicy1_xm = jicy1_xm;
	}
	public String getJicy2_cw() {
		return jicy2_cw;
	}
	public void setJicy2_cw(String jicy2_cw) {
		this.jicy2_cw = jicy2_cw;
	}
	public String getJicy2_dw() {
		return jicy2_dw;
	}
	public void setJicy2_dw(String jicy2_dw) {
		this.jicy2_dw = jicy2_dw;
	}
	public String getJicy2_nl() {
		return jicy2_nl;
	}
	public void setJicy2_nl(String jicy2_nl) {
		this.jicy2_nl = jicy2_nl;
	}
	public String getJicy2_sr() {
		return jicy2_sr;
	}
	public void setJicy2_sr(String jicy2_sr) {
		this.jicy2_sr = jicy2_sr;
	}
	public String getJicy2_xm() {
		return jicy2_xm;
	}
	public void setJicy2_xm(String jicy2_xm) {
		this.jicy2_xm = jicy2_xm;
	}
	public String getJicy3_cw() {
		return jicy3_cw;
	}
	public void setJicy3_cw(String jicy3_cw) {
		this.jicy3_cw = jicy3_cw;
	}
	public String getJicy3_dw() {
		return jicy3_dw;
	}
	public void setJicy3_dw(String jicy3_dw) {
		this.jicy3_dw = jicy3_dw;
	}
	public String getJicy3_nl() {
		return jicy3_nl;
	}
	public void setJicy3_nl(String jicy3_nl) {
		this.jicy3_nl = jicy3_nl;
	}
	public String getJicy3_sr() {
		return jicy3_sr;
	}
	public void setJicy3_sr(String jicy3_sr) {
		this.jicy3_sr = jicy3_sr;
	}
	public String getJicy3_xm() {
		return jicy3_xm;
	}
	public void setJicy3_xm(String jicy3_xm) {
		this.jicy3_xm = jicy3_xm;
	}
	public String getJicy4_cw() {
		return jicy4_cw;
	}
	public void setJicy4_cw(String jicy4_cw) {
		this.jicy4_cw = jicy4_cw;
	}
	public String getJicy4_dw() {
		return jicy4_dw;
	}
	public void setJicy4_dw(String jicy4_dw) {
		this.jicy4_dw = jicy4_dw;
	}
	public String getJicy4_nl() {
		return jicy4_nl;
	}
	public void setJicy4_nl(String jicy4_nl) {
		this.jicy4_nl = jicy4_nl;
	}
	public String getJicy4_sr() {
		return jicy4_sr;
	}
	public void setJicy4_sr(String jicy4_sr) {
		this.jicy4_sr = jicy4_sr;
	}
	public String getJicy4_xm() {
		return jicy4_xm;
	}
	public void setJicy4_xm(String jicy4_xm) {
		this.jicy4_xm = jicy4_xm;
	}
	public String getJicy5_cw() {
		return jicy5_cw;
	}
	public void setJicy5_cw(String jicy5_cw) {
		this.jicy5_cw = jicy5_cw;
	}
	public String getJicy5_dw() {
		return jicy5_dw;
	}
	public void setJicy5_dw(String jicy5_dw) {
		this.jicy5_dw = jicy5_dw;
	}
	public String getJicy5_nl() {
		return jicy5_nl;
	}
	public void setJicy5_nl(String jicy5_nl) {
		this.jicy5_nl = jicy5_nl;
	}
	public String getJicy5_sr() {
		return jicy5_sr;
	}
	public void setJicy5_sr(String jicy5_sr) {
		this.jicy5_sr = jicy5_sr;
	}
	public String getJicy5_xm() {
		return jicy5_xm;
	}
	public void setJicy5_xm(String jicy5_xm) {
		this.jicy5_xm = jicy5_xm;
	}
	public String getJtknqk() {
		return jtknqk;
	}
	public void setJtknqk(String jtknqk) {
		this.jtknqk = jtknqk;
	}
	public String getJtlx() {
		return jtlx;
	}
	public void setJtlx(String jtlx) {
		this.jtlx = jtlx;
	}
	public String getJtzrks() {
		return jtzrks;
	}
	public void setJtzrks(String jtzrks) {
		this.jtzrks = jtzrks;
	}
	public String getJtzzjyb() {
		return jtzzjyb;
	}
	public void setJtzzjyb(String jtzzjyb) {
		this.jtzzjyb = jtzzjyb;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getQtfy() {
		return qtfy;
	}
	public void setQtfy(String qtfy) {
		this.qtfy = qtfy;
	}
	public String getRjnsr() {
		return rjnsr;
	}
	public void setRjnsr(String rjnsr) {
		this.rjnsr = rjnsr;
	}
	public String getRxrq() {
		return rxrq;
	}
	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}
	public String getShf_jtgg() {
		return shf_jtgg;
	}
	public void setShf_jtgg(String shf_jtgg) {
		this.shf_jtgg = shf_jtgg;
	}
	public String getShf_qtly() {
		return shf_qtly;
	}
	public void setShf_qtly(String shf_qtly) {
		this.shf_qtly = shf_qtly;
	}
	public String getXfjnqk() {
		return xfjnqk;
	}
	public void setXfjnqk(String xfjnqk) {
		this.xfjnqk = xfjnqk;
	}
	public String getXxztqk() {
		return xxztqk;
	}
	public void setXxztqk(String xxztqk) {
		this.xxztqk = xxztqk;
	}
	public String getXzxfqk() {
		return xzxfqk;
	}
	public void setXzxfqk(String xzxfqk) {
		this.xzxfqk = xzxfqk;
	}
	public String getYhkh() {
		return yhkh;
	}
	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}
	public String getYkthm() {
		return ykthm;
	}
	public void setYkthm(String ykthm) {
		this.ykthm = ykthm;
	}
	public String getYwsshzzjhe() {
		return ywsshzzjhe;
	}
	public void setYwsshzzjhe(String ywsshzzjhe) {
		this.ywsshzzjhe = ywsshzzjhe;
	}
	public String getYwzxdkjje() {
		return ywzxdkjje;
	}
	public void setYwzxdkjje(String ywzxdkjje) {
		this.ywzxdkjje = ywzxdkjje;
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
	public String getFdysh() {
		return fdysh;
	}
	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}
	public String getFdyshsj() {
		return fdyshsj;
	}
	public void setFdyshsj(String fdyshsj) {
		this.fdyshsj = fdyshsj;
	}
	public String getFdyshyj() {
		return fdyshyj;
	}
	public void setFdyshyj(String fdyshyj) {
		this.fdyshyj = fdyshyj;
	}
	public String getJtrjnsr() {
		return jtrjnsr;
	}
	public void setJtrjnsr(String jtrjnsr) {
		this.jtrjnsr = jtrjnsr;
	}
	public String getKnlx() {
		return knlx;
	}
	public void setKnlx(String knlx) {
		this.knlx = knlx;
	}
	public String getMzmc() {
		return mzmc;
	}
	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
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
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
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
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXxshsj() {
		return xxshsj;
	}
	public void setXxshsj(String xxshsj) {
		this.xxshsj = xxshsj;
	}
	public String getXxshyj() {
		return xxshyj;
	}
	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
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
	public String getXyshsj() {
		return xyshsj;
	}
	public void setXyshsj(String xyshsj) {
		this.xyshsj = xyshsj;
	}
	public String getXyshyj() {
		return xyshyj;
	}
	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getZxlxdh() {
		return zxlxdh;
	}
	public void setZxlxdh(String zxlxdh) {
		this.zxlxdh = zxlxdh;
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
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
