package xgxt.pjpy.czxx;

import xgxt.pjpy.PjpyActionForm;
import xgxt.utils.Pages;

public class PjpyCzxxActionForm extends PjpyActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lb;
	private String dm;
	private String bfb;
	private String act;
	private String[] cbv;
	private String jxjsq;
	private String jxjsh;
	private String rychsq;
	private String rychsh;
	private String cj;
	private String[] chkonexy;
	
	private String bz;
	private String userName;
	private String[] cjArr;
	private String[] hiddenCbv;
	private String[] pllb;
	private String[] plyy;
	private String[] plbz;
	private String[] plfs;
	private String pkValue;
	private String save_zysj;
	private String queryequals_xn;
	private String queryequals_xq;
	private String queryequals_bjdm;
	private String queryequals_xydm;
	private String queryequals_zydm;
	private String queryequals_nj;
	private String queryequals_jxjdm;
	private String querylike_xh;
	private String querylike_xm;
	private String queryequals_fdysh;
	private String queryequals_xxsh;
	private String queryequals_xysh;
	private String queryequals_rychdm;
	
	private Pages pages = new Pages();
	
	public String getBfb() {
		return bfb;
	}
	public void setBfb(String bfb) {
		this.bfb = bfb;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
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
	public String getJxjsh() {
		return jxjsh;
	}
	public void setJxjsh(String jxjsh) {
		this.jxjsh = jxjsh;
	}
	public String getJxjsq() {
		return jxjsq;
	}
	public void setJxjsq(String jxjsq) {
		this.jxjsq = jxjsq;
	}
	public String getRychsh() {
		return rychsh;
	}
	public void setRychsh(String rychsh) {
		this.rychsh = rychsh;
	}
	public String getRychsq() {
		return rychsq;
	}
	public void setRychsq(String rychsq) {
		this.rychsq = rychsq;
	}
	public String getCj() {
		return cj;
	}
	public void setCj(String cj) {
		this.cj = cj;
	}
	public String[] getChkonexy() {
		return chkonexy;
	}
	public void setChkonexy(String[] chkonexy) {
		this.chkonexy = chkonexy;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String[] getCjArr() {
		return cjArr;
	}
	public void setCjArr(String[] cjArr) {
		this.cjArr = cjArr;
	}
	public String[] getHiddenCbv() {
		return hiddenCbv;
	}
	public void setHiddenCbv(String[] hiddenCbv) {
		this.hiddenCbv = hiddenCbv;
	}
	public String[] getPlbz() {
		return plbz;
	}
	public void setPlbz(String[] plbz) {
		this.plbz = plbz;
	}
	public String[] getPlfs() {
		return plfs;
	}
	public void setPlfs(String[] plfs) {
		this.plfs = plfs;
	}
	public String[] getPllb() {
		return pllb;
	}
	public void setPllb(String[] pllb) {
		this.pllb = pllb;
	}
	public String[] getPlyy() {
		return plyy;
	}
	public void setPlyy(String[] plyy) {
		this.plyy = plyy;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getSave_zysj() {
		return save_zysj;
	}
	public void setSave_zysj(String save_zysj) {
		this.save_zysj = save_zysj;
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
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
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
	public String getQueryequals_nj() {
		return queryequals_nj;
	}
	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}
	public String getQueryequals_jxjdm() {
		return queryequals_jxjdm;
	}
	public void setQueryequals_jxjdm(String queryequals_jxjdm) {
		this.queryequals_jxjdm = queryequals_jxjdm;
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
	public String getQueryequals_fdysh() {
		return queryequals_fdysh;
	}
	public void setQueryequals_fdysh(String queryequals_fdysh) {
		this.queryequals_fdysh = queryequals_fdysh;
	}
	public String getQueryequals_xxsh() {
		return queryequals_xxsh;
	}
	public void setQueryequals_xxsh(String queryequals_xxsh) {
		this.queryequals_xxsh = queryequals_xxsh;
	}
	public String getQueryequals_xysh() {
		return queryequals_xysh;
	}
	public void setQueryequals_xysh(String queryequals_xysh) {
		this.queryequals_xysh = queryequals_xysh;
	}
	public String getQueryequals_rychdm() {
		return queryequals_rychdm;
	}
	public void setQueryequals_rychdm(String queryequals_rychdm) {
		this.queryequals_rychdm = queryequals_rychdm;
	}
}
