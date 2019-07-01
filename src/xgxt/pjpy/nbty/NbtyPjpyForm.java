package xgxt.pjpy.nbty;

import xgxt.pjpy.PjpyActionForm;
import xgxt.utils.Pages;

public class NbtyPjpyForm extends PjpyActionForm {

	/**
	 * 
	 */
	private String userType;
	private String yesNo;
	private String[] checkVal;
	private String shjg;
	private String save_shjg;
	private String save_nd;
	private String save_hjsjmc;
	private String save_xh;
	private String save_xn;
	private String save_rychdm;
	private String save_zysj;
	private String save_xrzw;
	private String save_fdysh;
	private String save_xysh;
	private String save_xxsh;
	private String save_xxshsj;
	private String save_xyshsj;
	private String save_fdyshsj;
	private String save_rxzsj;
	private String save_xnpjcj;
	private String save_bz;
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
	
	Pages pages = new Pages();
	public String getSave_bz() {
		return save_bz;
	}
	public void setSave_bz(String save_bz) {
		this.save_bz = save_bz;
	}
	public String getSave_rxzsj() {
		return save_rxzsj;
	}
	public void setSave_rxzsj(String save_rxzsj) {
		this.save_rxzsj = save_rxzsj;
	}
	public String getSave_rychdm() {
		return save_rychdm;
	}
	public void setSave_rychdm(String save_rychdm) {
		this.save_rychdm = save_rychdm;
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
	public String getSave_xnpjcj() {
		return save_xnpjcj;
	}
	public void setSave_xnpjcj(String save_xnpjcj) {
		this.save_xnpjcj = save_xnpjcj;
	}
	public String getSave_xrzw() {
		return save_xrzw;
	}
	public void setSave_xrzw(String save_xrzw) {
		this.save_xrzw = save_xrzw;
	}
	public String getSave_zysj() {
		return save_zysj;
	}
	public void setSave_zysj(String save_zysj) {
		this.save_zysj = save_zysj;
	}
	public String getSave_hjsjmc() {
		return save_hjsjmc;
	}
	public void setSave_hjsjmc(String save_hjsjmc) {
		this.save_hjsjmc = save_hjsjmc;
	}
	
	
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getBfb() {
		return bfb;
	}
	public void setBfb(String bfb) {
		this.bfb = bfb;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public String[] getChkonexy() {
		return chkonexy;
	}
	public void setChkonexy(String[] chkonexy) {
		this.chkonexy = chkonexy;
	}
	public String getCj() {
		return cj;
	}
	public void setCj(String cj) {
		this.cj = cj;
	}
	public String[] getCjArr() {
		return cjArr;
	}
	public void setCjArr(String[] cjArr) {
		this.cjArr = cjArr;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String[] getHiddenCbv() {
		return hiddenCbv;
	}
	public void setHiddenCbv(String[] hiddenCbv) {
		this.hiddenCbv = hiddenCbv;
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
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
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
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}
	public String getQueryequals_fdysh() {
		return queryequals_fdysh;
	}
	public void setQueryequals_fdysh(String queryequals_fdysh) {
		this.queryequals_fdysh = queryequals_fdysh;
	}
	public String getQueryequals_jxjdm() {
		return queryequals_jxjdm;
	}
	public void setQueryequals_jxjdm(String queryequals_jxjdm) {
		this.queryequals_jxjdm = queryequals_jxjdm;
	}
	public String getQueryequals_nj() {
		return queryequals_nj;
	}
	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}
	public String getQueryequals_rychdm() {
		return queryequals_rychdm;
	}
	public void setQueryequals_rychdm(String queryequals_rychdm) {
		this.queryequals_rychdm = queryequals_rychdm;
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
	public String getQueryequals_xxsh() {
		return queryequals_xxsh;
	}
	public void setQueryequals_xxsh(String queryequals_xxsh) {
		this.queryequals_xxsh = queryequals_xxsh;
	}
	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}
	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}
	public String getQueryequals_xysh() {
		return queryequals_xysh;
	}
	public void setQueryequals_xysh(String queryequals_xysh) {
		this.queryequals_xysh = queryequals_xysh;
	}
	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}
	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSave_fdysh() {
		return save_fdysh;
	}
	public void setSave_fdysh(String save_fdysh) {
		this.save_fdysh = save_fdysh;
	}
	public String getSave_fdyshsj() {
		return save_fdyshsj;
	}
	public void setSave_fdyshsj(String save_fdyshsj) {
		this.save_fdyshsj = save_fdyshsj;
	}
	public String getSave_xxsh() {
		return save_xxsh;
	}
	public void setSave_xxsh(String save_xxsh) {
		this.save_xxsh = save_xxsh;
	}
	public String getSave_xxshsj() {
		return save_xxshsj;
	}
	public void setSave_xxshsj(String save_xxshsj) {
		this.save_xxshsj = save_xxshsj;
	}
	public String getSave_xysh() {
		return save_xysh;
	}
	public void setSave_xysh(String save_xysh) {
		this.save_xysh = save_xysh;
	}
	public String getSave_xyshsj() {
		return save_xyshsj;
	}
	public void setSave_xyshsj(String save_xyshsj) {
		this.save_xyshsj = save_xyshsj;
	}
	public String[] getCheckVal() {
		return checkVal;
	}
	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}
	public String getShjg() {
		return shjg;
	}
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String getSave_shjg() {
		return save_shjg;
	}
	public void setSave_shjg(String save_shjg) {
		this.save_shjg = save_shjg;
	}
	public String getSave_nd() {
		return save_nd;
	}
	public void setSave_nd(String save_nd) {
		this.save_nd = save_nd;
	}
}
