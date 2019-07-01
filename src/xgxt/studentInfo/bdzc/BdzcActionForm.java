package xgxt.studentInfo.bdzc;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class BdzcActionForm extends ActionForm {

	private static final long serialVersionUID = 5382080744292800734L;

	Pages pages = new Pages();
	
	private String tjfs;//统计方式
	
	private String save_zckssj;//注册开始时间
	
	private String save_zcjssj;//注册结束时间
	
	private String save_hzcjssj;//缓注册结束办理时间
	
	private String save_zccshsj;//注册初始化时间
	
	private String save_xh;
	
	private String save_xn;
	
	private String save_nd;
	
	private String save_xydm;
	
	private String save_zydm;
	
	private String save_bjdm;
	
	private String save_xysh;
	
	private String save_xxsh;
	
	private String save_xyyj;
	
	private String save_xxyj;
	
	private String save_sqyy;//缓注册申请原因
	
	private String save_sqly;//缓注册申请理由
	
	private String save_sqsj;//缓注册申请时间
	
	private String save_xm;
	
	private String save_sfqjdm;//收费年度
	
	private String save_sfxmdm;//收费项目代码
	
	private String save_ysje;//应收金额
	
	private String save_ssje;//实收金额
	
	private String save_sfsj;//收费时间
	
	private String save_je;//绿色通道金额
	
	private String save_sfzc;//是否注册
	
	private String save_xq;//学期
	
	private String queryequals_sfqf;//是否欠费
	
	private String queryequals_xq;
	
	private String queryequals_isLstd;
	
	private String queryequals_xysh;
	
	private String queryequals_xxsh;
	
	private String queryequals_sfqjdm;
	
	private String queryequals_xn;
	
	private String queryequals_nd;
	
	private String queryequals_xydm;
	
	private String queryequals_zydm;
	
	private String queryequals_bjdm;
	
	private String queryequals_nj;
	
	private String queryequals_zczt;
	
	private String queryequals_sfzc;
	
	private String queryequals_ishzc;
	
	private String querylike_xh;
	
	private String querylike_xm;
	
	private String querylike_sfxmdm;

	public String getQueryequals_sfqjdm() {
		return queryequals_sfqjdm;
	}

	public void setQueryequals_sfqjdm(String queryequals_sfqjdm) {
		this.queryequals_sfqjdm = queryequals_sfqjdm;
	}

	public String getQuerylike_sfxmdm() {
		return querylike_sfxmdm;
	}

	public void setQuerylike_sfxmdm(String querylike_sfxmdm) {
		this.querylike_sfxmdm = querylike_sfxmdm;
	}

	public String getSave_bjdm() {
		return save_bjdm;
	}

	public void setSave_bjdm(String save_bjdm) {
		this.save_bjdm = save_bjdm;
	}

	public String getSave_nd() {
		return save_nd;
	}

	public void setSave_nd(String save_nd) {
		this.save_nd = save_nd;
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

	public String getSave_sqyy() {
		return save_sqyy;
	}

	public void setSave_sqyy(String save_sqyy) {
		this.save_sqyy = save_sqyy;
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

	public String getSave_xxsh() {
		return save_xxsh;
	}

	public void setSave_xxsh(String save_xxsh) {
		this.save_xxsh = save_xxsh;
	}

	public String getSave_xxyj() {
		return save_xxyj;
	}

	public void setSave_xxyj(String save_xxyj) {
		this.save_xxyj = save_xxyj;
	}

	public String getSave_xydm() {
		return save_xydm;
	}

	public void setSave_xydm(String save_xydm) {
		this.save_xydm = save_xydm;
	}

	public String getSave_xysh() {
		return save_xysh;
	}

	public void setSave_xysh(String save_xysh) {
		this.save_xysh = save_xysh;
	}

	public String getSave_xyyj() {
		return save_xyyj;
	}

	public void setSave_xyyj(String save_xyyj) {
		this.save_xyyj = save_xyyj;
	}

	public String getSave_zydm() {
		return save_zydm;
	}

	public void setSave_zydm(String save_zydm) {
		this.save_zydm = save_zydm;
	}

	public String getSave_hzcjssj() {
		return save_hzcjssj;
	}

	public void setSave_hzcjssj(String save_hzcjssj) {
		this.save_hzcjssj = save_hzcjssj;
	}

	public String getSave_zccshsj() {
		return save_zccshsj;
	}

	public void setSave_zccshsj(String save_zccshsj) {
		this.save_zccshsj = save_zccshsj;
	}

	public String getSave_zcjssj() {
		return save_zcjssj;
	}

	public void setSave_zcjssj(String save_zcjssj) {
		this.save_zcjssj = save_zcjssj;
	}

	public String getSave_zckssj() {
		return save_zckssj;
	}

	public void setSave_zckssj(String save_zckssj) {
		this.save_zckssj = save_zckssj;
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

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getSave_sfqjdm() {
		return save_sfqjdm;
	}

	public void setSave_sfqjdm(String save_sfqjdm) {
		this.save_sfqjdm = save_sfqjdm;
	}

	public String getSave_sfsj() {
		return save_sfsj;
	}

	public void setSave_sfsj(String save_sfsj) {
		this.save_sfsj = save_sfsj;
	}

	public String getSave_sfxmdm() {
		return save_sfxmdm;
	}

	public void setSave_sfxmdm(String save_sfxmdm) {
		this.save_sfxmdm = save_sfxmdm;
	}

	public String getSave_ssje() {
		return save_ssje;
	}

	public void setSave_ssje(String save_ssje) {
		this.save_ssje = save_ssje;
	}

	public String getSave_xm() {
		return save_xm;
	}

	public void setSave_xm(String save_xm) {
		this.save_xm = save_xm;
	}

	public String getSave_ysje() {
		return save_ysje;
	}

	public void setSave_ysje(String save_ysje) {
		this.save_ysje = save_ysje;
	}

	public String getSave_je() {
		return save_je;
	}

	public void setSave_je(String save_je) {
		this.save_je = save_je;
	}

	public String getSave_sfzc() {
		return save_sfzc;
	}

	public void setSave_sfzc(String save_sfzc) {
		this.save_sfzc = save_sfzc;
	}

	public String getQueryequals_zczt() {
		return queryequals_zczt;
	}

	public void setQueryequals_zczt(String queryequals_zczt) {
		this.queryequals_zczt = queryequals_zczt;
	}

	public String getQueryequals_ishzc() {
		return queryequals_ishzc;
	}

	public void setQueryequals_ishzc(String queryequals_ishzc) {
		this.queryequals_ishzc = queryequals_ishzc;
	}

	public String getQueryequals_sfzc() {
		return queryequals_sfzc;
	}

	public void setQueryequals_sfzc(String queryequals_sfzc) {
		this.queryequals_sfzc = queryequals_sfzc;
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

	public String getTjfs() {
		return tjfs;
	}

	public void setTjfs(String tjfs) {
		this.tjfs = tjfs;
	}

	public String getSave_xq() {
		return save_xq;
	}

	public void setSave_xq(String save_xq) {
		this.save_xq = save_xq;
	}

	public String getQueryequals_isLstd() {
		return queryequals_isLstd;
	}

	public void setQueryequals_isLstd(String queryequals_isLstd) {
		this.queryequals_isLstd = queryequals_isLstd;
	}

	public String getQueryequals_xq() {
		return queryequals_xq;
	}

	public void setQueryequals_xq(String queryequals_xq) {
		this.queryequals_xq = queryequals_xq;
	}

	public String getQueryequals_sfqf() {
		return queryequals_sfqf;
	}

	public void setQueryequals_sfqf(String queryequals_sfqf) {
		this.queryequals_sfqf = queryequals_sfqf;
	}
}
