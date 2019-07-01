package xgxt.xszz.nbty.jtjjknsbz;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.Pages;

/** 
 * author 裘力俊
 * MyEclipse Struts
 * Creation date: 06-30-2010
 * 
 * XDoclet definition:
 * @struts.form name="nbtyJtjjknsbzForm"
 */
public class NbtyJtjjknsForm extends ActionForm {
	/*
	 * Generated Methods
	 */
	private String xh;
	private String save_nj;     //年级
	private String save_cjqgdg;     //是否参加勤工和贷款
	private String save_sqly;     //申请理由
	private String save_bjpy;     //班级评议
	private String save_yldyj;     //院领导意见
	private String save_xh;     //学号
	private String save_lsknbz;     //临时困难补助
	private String save_xn;     //学年
	private String save_yxyj;     //院系意见
	private String save_xnzz;     //何学年获何资助
	private String save_pddd;     //品德等第
	private String save_mybz;     //每月补助
	private String save_lsbz;     //临时补助
	private String save_zxj;     //助学金
	private String save_nd;     //年度
	private String save_fffs;     //发放方式
	private String save_bz;		//备注
	private String save_fdysh;   //辅导员审核
	private String save_xysh;    //学院审核
	private String save_xxsh;    //学校审核
	private String save_bzlx;    //补助类型
	private String save_bzje;    //补助金额
	private String fffs;
	private String pkValue;
	private String xydm;
	private String queryequals_bzlx;
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
	 Pages pages=new Pages();
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getSave_bz() {
		return save_bz;
	}
	public void setSave_bz(String save_bz) {
		this.save_bz = save_bz;
	}
	public String getSave_bjpy() {
		return save_bjpy;
	}
	public void setSave_bjpy(String save_bjpy) {
		this.save_bjpy = save_bjpy;
	}
	public String getSave_cjqgdg() {
		return save_cjqgdg;
	}
	public void setSave_cjqgdg(String save_cjqgdg) {
		this.save_cjqgdg = save_cjqgdg;
	}
	public String getSave_fffs() {
		return save_fffs;
	}
	public void setSave_fffs(String save_fffs) {
		this.save_fffs = save_fffs;
	}
	public String getSave_lsbz() {
		return save_lsbz;
	}
	public void setSave_lsbz(String save_lsbz) {
		this.save_lsbz = save_lsbz;
	}
	public String getSave_lsknbz() {
		return save_lsknbz;
	}
	public void setSave_lsknbz(String save_lsknbz) {
		this.save_lsknbz = save_lsknbz;
	}
	public String getSave_mybz() {
		return save_mybz;
	}
	public void setSave_mybz(String save_mybz) {
		this.save_mybz = save_mybz;
	}
	public String getSave_nd() {
		return save_nd;
	}
	public void setSave_nd(String save_nd) {
		this.save_nd = save_nd;
	}
	public String getSave_nj() {
		return save_nj;
	}
	public void setSave_nj(String save_nj) {
		this.save_nj = save_nj;
	}
	public String getSave_pddd() {
		return save_pddd;
	}
	public void setSave_pddd(String save_pddd) {
		this.save_pddd = save_pddd;
	}
	public String getSave_sqly() {
		return save_sqly;
	}
	public void setSave_sqly(String save_sqly) {
		this.save_sqly = save_sqly;
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
	public String getSave_xnzz() {
		return save_xnzz;
	}
	public void setSave_xnzz(String save_xnzz) {
		this.save_xnzz = save_xnzz;
	}
	public String getSave_yldyj() {
		return save_yldyj;
	}
	public void setSave_yldyj(String save_yldyj) {
		this.save_yldyj = save_yldyj;
	}
	public String getSave_yxyj() {
		return save_yxyj;
	}
	public void setSave_yxyj(String save_yxyj) {
		this.save_yxyj = save_yxyj;
	}
	public String getSave_zxj() {
		return save_zxj;
	}
	public void setSave_zxj(String save_zxj) {
		this.save_zxj = save_zxj;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
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
	public String getSave_fdysh() {
		return save_fdysh;
	}
	public void setSave_fdysh(String save_fdysh) {
		this.save_fdysh = save_fdysh;
	}
	public String getSave_xxsh() {
		return save_xxsh;
	}
	public void setSave_xxsh(String save_xxsh) {
		this.save_xxsh = save_xxsh;
	}
	public String getSave_xysh() {
		return save_xysh;
	}
	public void setSave_xysh(String save_xysh) {
		this.save_xysh = save_xysh;
	}
	public String getSave_bzje() {
		return save_bzje;
	}
	public void setSave_bzje(String save_bzje) {
		this.save_bzje = save_bzje;
	}
	public String getSave_bzlx() {
		return save_bzlx;
	}
	public void setSave_bzlx(String save_bzlx) {
		this.save_bzlx = save_bzlx;
	}
	public String getFffs() {
		return fffs;
	}
	public void setFffs(String fffs) {
		this.fffs = fffs;
	}
	public String getQueryequals_bzlx() {
		return queryequals_bzlx;
	}
	public void setQueryequals_bzlx(String queryequals_bzlx) {
		this.queryequals_bzlx = queryequals_bzlx;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
}