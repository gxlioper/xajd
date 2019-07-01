
package xgxt.pjpy;

import org.apache.struts.action.ActionForm;

import xgxt.bdsz.BdzdForm;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class PjpyActionForm extends BdzdForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 156208654779020468L;
	private String userName;
	private String userType;
	private String isFdy;
	public String xydm;//学院代码
	public String zydm;//专业代码
	public String bjdm;//班级代码
	public String nj;//年级
	public String xh;//学号
	public String xm;//姓名
	public String nd;//年度
	public String xqmc;
	public String[] cbv;//主键列表
	public String xn;//学年
	public String yesNo;//审核状态
	public String yj;//意见
	public String mz;//民簇
	public String zzmm;//政治面貌
	public String csrq;//出生日期
	public String rxrq;//入学日期
	public String syd;//生源地
	public String jtdz;//家庭地址
	public String sjhm;//手机号码
	public String wysp;//外语水平
	public String jxjdm;//奖学金代码
	public String drzw;//担任职务
	public String dnshjxj;//当年所获奖学金
	public String xq;//学期
	public String fdyyj;
	public String fdysh;
	public String xysh;
	public String xxsh;
	public String rychdm;
	public String pkValue;
	public String act;
	public String bjmc;
	private String sh;
	private String shjb;
	
	private String save_xh;
	private String save_xn;
	private String save_xq;
	private String save_jxjdm;
	private String save_nd;
	private String save_rychdm;
	private String queryequals_xydm;
	private String queryequals_zydm;
	private String queryequals_bjdm;
	private String queryequals_jxjdm;//奖学金代码
	private String queryequals_rychdm;//荣誉称号代码
	private String queryequals_sfksq;//是否可申请
	private String queryequils_xb;
	private String queryequals_nj;
	private String queryequals_xn;
	private String queryequals_nd;
	private String querylike_xh;
	private String querylike_xm;
	private String queryequals_xq;
	private String save_fdysh;
	private String save_xysh;
	private String save_xxsh;
	private String save_fdyshyj;
	private String save_xyshyj;
	private String save_xxshyj;
	
	private Pages pages = new Pages(); 
	
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
	public String getIsFdy() {
		return isFdy;
	}
	public void setIsFdy(String isFdy) {
		this.isFdy = isFdy;
	}
	public String getShjb() {
		return shjb;
	}
	public void setShjb(String shjb) {
		this.shjb = shjb;
	}
	public String getSh() {
		return sh;
	}
	public void setSh(String sh) {
		this.sh = sh;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getFdyyj() {
		return fdyyj;
	}
	public void setFdyyj(String fdyyj) {
		this.fdyyj = fdyyj;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getJtdz() {
		return jtdz;
	}
	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getRxrq() {
		return rxrq;
	}
	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}
	public String getSyd() {
		return syd;
	}
	public void setSyd(String syd) {
		this.syd = syd;
	}
	public String getZzmm() {
		return zzmm;
	}
	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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
		return StringUtils.isNotNull(xh) ? xh.trim() : xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return StringUtils.isNotNull(xm) ? xm.trim() : xm;
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
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}
	public String getYj() {
		return yj;
	}
	public void setYj(String yj) {
		this.yj = yj;
	}
	public String getDnshjxj() {
		return dnshjxj;
	}
	public void setDnshjxj(String dnshjxj) {
		this.dnshjxj = dnshjxj;
	}
	public String getDrzw() {
		return drzw;
	}
	public void setDrzw(String drzw) {
		this.drzw = drzw;
	}
	public String getJxjdm() {
		return jxjdm;
	}
	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getWysp() {
		return wysp;
	}
	public void setWysp(String wysp) {
		this.wysp = wysp;
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
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public String getRychdm() {
		return rychdm;
	}
	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getsave_jxjdm() {
		return save_jxjdm;
	}
	public void setsave_jxjdm(String save_jxjdm) {
		this.save_jxjdm = save_jxjdm;
	}
	public String getsave_nd() {
		return save_nd;
	}
	public void setsave_nd(String save_nd) {
		this.save_nd = save_nd;
	}
	public String getsave_rychdm() {
		return save_rychdm;
	}
	public void setsave_rychdm(String save_rychdm) {
		this.save_rychdm = save_rychdm;
	}
	public String getsave_xh() {
		return save_xh;
	}
	public void setsave_xh(String save_xh) {
		this.save_xh = save_xh;
	}
	public String getsave_xn() {
		return save_xn;
	}
	public void setsave_xn(String save_xn) {
		this.save_xn = save_xn;
	}
	public String getsave_xq() {
		return save_xq;
	}
	public void setsave_xq(String save_xq) {
		this.save_xq = save_xq;
	}
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
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
	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}
	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}
	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}
	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}
	public String getQueryequils_xb() {
		return queryequils_xb;
	}
	public void setQueryequils_xb(String queryequils_xb) {
		this.queryequils_xb = queryequils_xb;
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
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getFdysh() {
		return fdysh;
	}
	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}
	public String getSave_xh() {
		return save_xh;
	}
	public void setSave_xh(String save_xh) {
		this.save_xh = save_xh;
	}
	public String getSave_xn() {
		return save_xn;
	}
	public void setSave_xn(String save_xn) {
		this.save_xn = save_xn;
	}
	public String getSave_xq() {
		return save_xq;
	}
	public void setSave_xq(String save_xq) {
		this.save_xq = save_xq;
	}
	public String getSave_jxjdm() {
		return save_jxjdm;
	}
	public void setSave_jxjdm(String save_jxjdm) {
		this.save_jxjdm = save_jxjdm;
	}
	public String getSave_nd() {
		return save_nd;
	}
	public void setSave_nd(String save_nd) {
		this.save_nd = save_nd;
	}
	public String getSave_rychdm() {
		return save_rychdm;
	}
	public void setSave_rychdm(String save_rychdm) {
		this.save_rychdm = save_rychdm;
	}
	public String getSave_fdysh() {
		return save_fdysh;
	}
	public void setSave_fdysh(String save_fdysh) {
		this.save_fdysh = save_fdysh;
	}
	public String getSave_xysh() {
		return save_xysh;
	}
	public void setSave_xysh(String save_xysh) {
		this.save_xysh = save_xysh;
	}
	public String getSave_xxsh() {
		return save_xxsh;
	}
	public void setSave_xxsh(String save_xxsh) {
		this.save_xxsh = save_xxsh;
	}
	public String getSave_fdyshyj() {
		return save_fdyshyj;
	}
	public void setSave_fdyshyj(String save_fdyshyj) {
		this.save_fdyshyj = save_fdyshyj;
	}
	public String getSave_xyshyj() {
		return save_xyshyj;
	}
	public void setSave_xyshyj(String save_xyshyj) {
		this.save_xyshyj = save_xyshyj;
	}
	public String getSave_xxshyj() {
		return save_xxshyj;
	}
	public void setSave_xxshyj(String save_xxshyj) {
		this.save_xxshyj = save_xxshyj;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public String getQueryequals_jxjdm() {
		return queryequals_jxjdm;
	}
	public void setQueryequals_jxjdm(String queryequals_jxjdm) {
		this.queryequals_jxjdm = queryequals_jxjdm;
	}
	public String getQueryequals_rychdm() {
		return queryequals_rychdm;
	}
	public void setQueryequals_rychdm(String queryequals_rychdm) {
		this.queryequals_rychdm = queryequals_rychdm;
	}
	public String getQueryequals_sfksq() {
		return queryequals_sfksq;
	}
	public void setQueryequals_sfksq(String queryequals_sfksq) {
		this.queryequals_sfksq = queryequals_sfksq;
	}	
}
