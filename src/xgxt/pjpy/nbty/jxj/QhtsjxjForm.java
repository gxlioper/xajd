package xgxt.pjpy.nbty.jxj;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class QhtsjxjForm extends ActionForm{
	
	Pages pages = new Pages();
	private String save_sqly;     //申请理由
	private String save_xysh;     //学院审核
	private String save_xxsh;     //学校审核
	private String save_bjpy;     //班级评议
	private String save_bjshsj;     //班级审核时间
	private String save_jcqk;     //奖惩情况
	private String save_xn;     		//学年
	private String save_xh;			//保存_学号
	private String jxjdm;    		 //奖学金代码
	private String save_hxnhzzz;     //何学年何种资助
	private String save_sfcjqgzx;     //是否参见勤工俭学
	private String save_sfsqzxdk;     //是否申请助学贷款
	private String save_pddd;     //品德等第
	private String save_xyshyj;     //学院审核意见
	private String save_bjsh;     //班级审核
	private String save_sqsj;     //申请时间
	private String save_xxshyj;     //学校审核意见
	private String save_xyshsj;     //学院审核时间
	private String save_xxshsj;     //学校审核时间
	
	private String querylike_xm;			//查询_姓名
	private String queryequals_nj;			//查询_年级
	private String queryequals_xn;         //查询_学年
	private String querylike_xh;			//查询_学号
	private String queryequals_xydm;		//查询_学院
	private String queryequals_zydm;		//查询_专业
	private String queryequals_bjdm;		//查询_班级

	public String getJxjdm() {
		return jxjdm;
	}
	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
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
	public String getQueryequals_xn() {
		return queryequals_xn;
	}
	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
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
	public String getSave_bjpy() {
		return save_bjpy;
	}
	public void setSave_bjpy(String save_bjpy) {
		this.save_bjpy = save_bjpy;
	}
	public String getSave_bjsh() {
		return save_bjsh;
	}
	public void setSave_bjsh(String save_bjsh) {
		this.save_bjsh = save_bjsh;
	}
	public String getSave_bjshsj() {
		return save_bjshsj;
	}
	public void setSave_bjshsj(String save_bjshsj) {
		this.save_bjshsj = save_bjshsj;
	}
	public String getSave_hxnhzzz() {
		return save_hxnhzzz;
	}
	public void setSave_hxnhzzz(String save_hxnhzzz) {
		this.save_hxnhzzz = save_hxnhzzz;
	}
	public String getSave_jcqk() {
		return save_jcqk;
	}
	public void setSave_jcqk(String save_jcqk) {
		this.save_jcqk = save_jcqk;
	}
	public String getSave_pddd() {
		return save_pddd;
	}
	public void setSave_pddd(String save_pddd) {
		this.save_pddd = save_pddd;
	}
	public String getSave_sfcjqgzx() {
		return save_sfcjqgzx;
	}
	public void setSave_sfcjqgzx(String save_sfcjqgzx) {
		this.save_sfcjqgzx = save_sfcjqgzx;
	}
	public String getSave_sfsqzxdk() {
		return save_sfsqzxdk;
	}
	public void setSave_sfsqzxdk(String save_sfsqzxdk) {
		this.save_sfsqzxdk = save_sfsqzxdk;
	}
	public String getSave_sqly() {
		return save_sqly;
	}
	public void setSave_sqly(String save_sqly) {
		this.save_sqly = save_sqly;
	}
	public String getSave_sqsj() {
		return save_sqsj;
	}
	public void setSave_sqsj(String save_sqsj) {
		this.save_sqsj = save_sqsj;
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
	public String getSave_xxshyj() {
		return save_xxshyj;
	}
	public void setSave_xxshyj(String save_xxshyj) {
		this.save_xxshyj = save_xxshyj;
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
	public String getSave_xyshyj() {
		return save_xyshyj;
	}
	public void setSave_xyshyj(String save_xyshyj) {
		this.save_xyshyj = save_xyshyj;
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
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	
}
