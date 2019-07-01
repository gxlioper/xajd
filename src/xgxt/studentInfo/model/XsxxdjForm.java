package xgxt.studentInfo.model;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class XsxxdjForm extends ActionForm implements Serializable{
	Pages pages = new Pages();
	
	FormFile bkzp;
	FormFile rxzp;
	FormFile byzp;
	private String isFdy;           //辅导员
	private String isBzr;           //班主任
	private String userName;        //用户名
	private String xh;
	private String querylike_xh;
	private String querylike_xm;
	private String queryequals_xydm;
	private String queryequals_zydm;
	private String queryequals_bjdm;
	private String queryequals_nj;
	
	private String save_xh;
	private String save_csd;
	private String save_xuex;
	private String save_sfsty;
	private String save_jrgqtsj;
	private String save_tytysj;
	private String save_zybdysj;
	private String save_rxqjkzk;
	private String save_rxhjkzk;
	private String save_ywbs;
	private String save_sjdh;
	private String save_qqhm;
	private String save_zjxy;
	private String save_txdz;
	private String save_sxyy;
	private String save_sxzsdz;
	private String save_zyjnzs;
	private String save_hksfqr;
	private String save_jcqk;
	private String save_zwjs;
	
	private String save_kssj;
	private String save_jssj;
	private String save_szdw;
	private String save_drzw;
	private String save_zmr;
	
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getIsFdy() {
		return isFdy;
	}
	public void setIsFdy(String isFdy) {
		this.isFdy = isFdy;
	}
	public String getIsBzr() {
		return isBzr;
	}
	public void setIsBzr(String isBzr) {
		this.isBzr = isBzr;
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
	public String getQueryequals_nj() {
		return queryequals_nj;
	}
	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}
	public String getSave_xh() {
		return save_xh;
	}
	public void setSave_xh(String save_xh) {
		this.save_xh = save_xh;
	}
	public String getSave_csd() {
		return save_csd;
	}
	public void setSave_csd(String save_csd) {
		this.save_csd = save_csd;
	}
	public String getSave_xuex() {
		return save_xuex;
	}
	public void setSave_xuex(String save_xuex) {
		this.save_xuex = save_xuex;
	}
	public String getSave_sfsty() {
		return save_sfsty;
	}
	public void setSave_sfsty(String save_sfsty) {
		this.save_sfsty = save_sfsty;
	}
	public String getSave_jrgqtsj() {
		return save_jrgqtsj;
	}
	public void setSave_jrgqtsj(String save_jrgqtsj) {
		this.save_jrgqtsj = save_jrgqtsj;
	}
	public String getSave_tytysj() {
		return save_tytysj;
	}
	public void setSave_tytysj(String save_tytysj) {
		this.save_tytysj = save_tytysj;
	}
	public String getSave_zybdysj() {
		return save_zybdysj;
	}
	public void setSave_zybdysj(String save_zybdysj) {
		this.save_zybdysj = save_zybdysj;
	}
	public String getSave_rxqjkzk() {
		return save_rxqjkzk;
	}
	public void setSave_rxqjkzk(String save_rxqjkzk) {
		this.save_rxqjkzk = save_rxqjkzk;
	}
	public String getSave_rxhjkzk() {
		return save_rxhjkzk;
	}
	public void setSave_rxhjkzk(String save_rxhjkzk) {
		this.save_rxhjkzk = save_rxhjkzk;
	}
	public String getSave_ywbs() {
		return save_ywbs;
	}
	public void setSave_ywbs(String save_ywbs) {
		this.save_ywbs = save_ywbs;
	}
	public String getSave_sjdh() {
		return save_sjdh;
	}
	public void setSave_sjdh(String save_sjdh) {
		this.save_sjdh = save_sjdh;
	}
	public String getSave_qqhm() {
		return save_qqhm;
	}
	public void setSave_qqhm(String save_qqhm) {
		this.save_qqhm = save_qqhm;
	}
	public String getSave_zjxy() {
		return save_zjxy;
	}
	public void setSave_zjxy(String save_zjxy) {
		this.save_zjxy = save_zjxy;
	}
	public String getSave_txdz() {
		return save_txdz;
	}
	public void setSave_txdz(String save_txdz) {
		this.save_txdz = save_txdz;
	}
	public String getSave_sxyy() {
		return save_sxyy;
	}
	public void setSave_sxyy(String save_sxyy) {
		this.save_sxyy = save_sxyy;
	}
	public String getSave_sxzsdz() {
		return save_sxzsdz;
	}
	public void setSave_sxzsdz(String save_sxzsdz) {
		this.save_sxzsdz = save_sxzsdz;
	}
	public String getSave_zyjnzs() {
		return save_zyjnzs;
	}
	public void setSave_zyjnzs(String save_zyjnzs) {
		this.save_zyjnzs = save_zyjnzs;
	}
	public String getSave_hksfqr() {
		return save_hksfqr;
	}
	public void setSave_hksfqr(String save_hksfqr) {
		this.save_hksfqr = save_hksfqr;
	}
	public String getSave_jcqk() {
		return save_jcqk;
	}
	public void setSave_jcqk(String save_jcqk) {
		this.save_jcqk = save_jcqk;
	}
	public String getSave_zwjs() {
		return save_zwjs;
	}
	public void setSave_zwjs(String save_zwjs) {
		this.save_zwjs = save_zwjs;
	}
	public String getSave_kssj() {
		return save_kssj;
	}
	public void setSave_kssj(String save_kssj) {
		this.save_kssj = save_kssj;
	}
	public String getSave_jssj() {
		return save_jssj;
	}
	public void setSave_jssj(String save_jssj) {
		this.save_jssj = save_jssj;
	}
	public String getSave_szdw() {
		return save_szdw;
	}
	public void setSave_szdw(String save_szdw) {
		this.save_szdw = save_szdw;
	}
	public String getSave_drzw() {
		return save_drzw;
	}
	public void setSave_drzw(String save_drzw) {
		this.save_drzw = save_drzw;
	}
	public String getSave_zmr() {
		return save_zmr;
	}
	public void setSave_zmr(String save_zmr) {
		this.save_zmr = save_zmr;
	}
	public FormFile getBkzp() {
		return bkzp;
	}
	public void setBkzp(FormFile bkzp) {
		this.bkzp = bkzp;
	}
	public FormFile getRxzp() {
		return rxzp;
	}
	public void setRxzp(FormFile rxzp) {
		this.rxzp = rxzp;
	}
	public FormFile getByzp() {
		return byzp;
	}
	public void setByzp(FormFile byzp) {
		this.byzp = byzp;
	}	
}
