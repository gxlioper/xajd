package xgxt.zxdk.gzdx;

import org.apache.struts.action.ActionForm;

/**
 * 助学贷款数据维护Model
 * 
 */
public class ZxdkGzdxModel {

	private String xh;// 学号
	private String xn;// 学年
	private String xm;// 姓名
	private String nd;// 年度
	private String xb;// 性别
	private String nj;// 年级
	private String zymc;// 专业名称
	private String zydm;// 专业代码
	private String bjmc;// 班级名称
	private String bjdm;// 班级代码
	private String xymc;// 学院名称
	private String xydm;// 学院代码
	private String zxdkmc;// 助学贷款名称
	private String dkje;// 贷款金额
	private String dkje_ks;// 贷款金额开始
	private String dkje_js;// 贷款金额结束
	private String bz;// 备注

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZxdkmc() {
		return zxdkmc;
	}

	public void setZxdkmc(String zxdkmc) {
		this.zxdkmc = zxdkmc;
	}

	public String getDkje() {
		return dkje;
	}

	public void setDkje(String dkje) {
		this.dkje = dkje;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getDkje_ks() {
		return dkje_ks;
	}

	public void setDkje_ks(String dkjeKs) {
		dkje_ks = dkjeKs;
	}

	public String getDkje_js() {
		return dkje_js;
	}

	public void setDkje_js(String dkjeJs) {
		dkje_js = dkjeJs;
	}
}
