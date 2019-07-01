package xgxt.dwjl;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class DwjlForm extends ActionForm  {
	Pages pages = new Pages();
	private static final long serialVersionUID = -7998722147754604073L;
	private String xh;//学号
	private String xm;//姓名
	private String nj;//年级
	private String xydm;//学院代码
	private String xymc;//学院名称
	private String zydm;//专业代码
	private String zymc;//专业名称
	private String bjdm;//班级代码
	private String bjmc;//班级名称
	private String jg;//籍贯
	private String xl;//学历
	private String cet;//英语过级情况
	private String tem;
	private String toeft;// 雅思或托福成绩
	private String jzxm;//家长姓名
	private String jzgzdw;//家长工作单位
	private String jtdz;//家庭地址
	private String gj;//国家
	private String xx;//留学学校
	private String jdxx;//就读学校
	private String ss;//宿舍
	private String qqh;//qq号
	private String dzyx;//电子邮箱
	private String lxdh;//联系电话
	private String sfzzlxxx;//是否自主联系学校
	private String xmdm;//项目代码
	private String bjdypm;//班级德育排名
	private String njzypm;//年级智育排名
	private String tcah;//特长爱好
	private String shgzqk;//社会工作情况
	private String sqje;//申请金额
	private String jlbx;//交流表现
	private String sqrq;//申请日期
	private String userType;//用户类型
	private String yesNo;//审核结果	
	private String sqly;//申请理由
	private String pk;//主键
	private String xn;
	private String nd;
	private String xq;	
	private String queryequals_xn;
	private String queryequals_nd;
	private String queryequals_xq;
	private String queryequals_nj;
	private String queryequals_xydm;
	private String queryequals_zydm;
	private String queryequals_bjdm;
	private String queryequals_jlxmdm;
	private String querylike_xh;
	private String querylike_xm;
	

	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getSqrq() {
		return sqrq;
	}
	public void setSqrq(String sqrq) {
		this.sqrq = sqrq;
	}
	public String getBjdypm() {
		return bjdypm;
	}
	public void setBjdypm(String bjdypm) {
		this.bjdypm = bjdypm;
	}
	public String getNjzypm() {
		return njzypm;
	}
	public void setNjzypm(String njzypm) {
		this.njzypm = njzypm;
	}
	public String getShgzqk() {
		return shgzqk;
	}
	public void setShgzqk(String shgzqk) {
		this.shgzqk = shgzqk;
	}
	public String getTcah() {
		return tcah;
	}
	public void setTcah(String tcah) {
		this.tcah = tcah;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getJlbx() {
		return jlbx;
	}
	public void setJlbx(String jlbx) {
		this.jlbx = jlbx;
	}
	public String getSqje() {
		return sqje;
	}
	public void setSqje(String sqje) {
		this.sqje = sqje;
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
	public String getCet() {
		return cet;
	}
	public void setCet(String cet) {
		this.cet = cet;
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	public String getGj() {
		return gj;
	}
	public void setGj(String gj) {
		this.gj = gj;
	}
	public String getJg() {
		return jg;
	}
	public void setJg(String jg) {
		this.jg = jg;
	}
	public String getJtdz() {
		return jtdz;
	}
	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}
	public String getJzgzdw() {
		return jzgzdw;
	}
	public void setJzgzdw(String jzgzdw) {
		this.jzgzdw = jzgzdw;
	}
	public String getJzxm() {
		return jzxm;
	}
	public void setJzxm(String jzxm) {
		this.jzxm = jzxm;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getQqh() {
		return qqh;
	}
	public void setQqh(String qqh) {
		this.qqh = qqh;
	}
	public String getSfzzlxxx() {
		return sfzzlxxx;
	}
	public void setSfzzlxxx(String sfzzlxxx) {
		this.sfzzlxxx = sfzzlxxx;
	}
	public String getSs() {
		return ss;
	}
	public void setSs(String ss) {
		this.ss = ss;
	}
	public String getTem() {
		return tem;
	}
	public void setTem(String tem) {
		this.tem = tem;
	}
	public String getToeft() {
		return toeft;
	}
	public void setToeft(String toeft) {
		this.toeft = toeft;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXx() {
		return xx;
	}
	public void setXx(String xx) {
		this.xx = xx;
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
	public String getXl() {
		return xl;
	}
	public void setXl(String xl) {
		this.xl = xl;
	}
	public String getJdxx() {
		return jdxx;
	}
	public void setJdxx(String jdxx) {
		this.jdxx = jdxx;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getQueryequals_xn() {
		return queryequals_xn;
	}
	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}
	public String getQueryequals_nd() {
		return queryequals_nd;
	}
	public void setQueryequals_nd(String queryequals_nd) {
		this.queryequals_nd = queryequals_nd;
	}
	public String getQueryequals_xq() {
		return queryequals_xq;
	}
	public void setQueryequals_xq(String queryequals_xq) {
		this.queryequals_xq = queryequals_xq;
	}
	public String getQueryequals_nj() {
		return queryequals_nj;
	}
	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
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
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}
	public String getQueryequals_jlxmdm() {
		return queryequals_jlxmdm;
	}
	public void setQueryequals_jlxmdm(String queryequals_jlxmdm) {
		this.queryequals_jlxmdm = queryequals_jlxmdm;
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
	
}
