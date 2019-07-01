package xgxt.dtjs.gdby.tygl;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * Title: 学生工作管理系统
 * Description:广东白云 推优管理ActionForm
 * Module:党团建设 - 团员信息
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-7-30
 */
public class TyglForm extends ActionForm{
	
	Pages pages = new Pages();
	private String querylike_xm;			//查询_姓名
	private String queryequals_nj;			//查询_年级
	private String queryequals_xn;         //查询_学年
	private String querylike_xh;			//查询_学号
	private String queryequals_xydm;		//查询_学院
	private String queryequals_zydm;		//查询_专业
	private String queryequals_bjdm;		//查询_班级
	
	private String save_xysh;     //学院审核
	private String save_sqly;     //申请理由
	private String save_xxsh;     //学校审核
	private String save_xh;     //学号
	private String save_fdysh;     //辅导员审核
	private String save_bz;     //备注
	private String save_xn;     //学年
	private String save_xq;     //学期
	private String save_sqsj;     //申请时间
	private String save_xyshyj;     //学院审核意见
	private String save_fdyshyj;     //辅导员审核意见
	private String save_xxshyj;     //学校审核意见
	private String save_fdyshsj;     //辅导员审核时间
	private String save_xyshsj;     //学院审核时间
	private String save_xxshsj;     //学校审核时间
	
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
	public String getSave_fdyshyj() {
		return save_fdyshyj;
	}
	public void setSave_fdyshyj(String save_fdyshyj) {
		this.save_fdyshyj = save_fdyshyj;
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
	
	
}
