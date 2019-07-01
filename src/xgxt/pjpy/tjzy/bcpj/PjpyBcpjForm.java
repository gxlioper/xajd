package xgxt.pjpy.tjzy.bcpj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

public class PjpyBcpjForm extends ActionForm{
	
	Pages pages=new Pages();
	
	SearchModel searchModel=new SearchModel();
	
	User user = new User();
	
	private String sqly; // 申请理由

	private String kzzd1; // 扩展字段1

	private String kzzd6; // 扩展字段六

	private String pjxq; // 评奖学期

	private String xh; // 学号

	private String kzzd3; // 扩展字段3

	private String pjxn; // 评奖学年

	private String tjr; // 提交人

	private String kzzd7; // 扩展字段七

	private String sqjg; // 申请结果

	private String kzzd2; // 扩展字段2

	private String kzzd4; // 扩展字段4

	private String xmdm; // 项目代码

	private String pjnd; // 评奖年度

	private String sqsj; // 申请时间

	private String over; // 结束标志

	private String kzzd5; // 扩展字段5

	public String getKzzd1() {
		return kzzd1;
	}

	public void setKzzd1(String kzzd1) {
		this.kzzd1 = kzzd1;
	}

	public String getKzzd2() {
		return kzzd2;
	}

	public void setKzzd2(String kzzd2) {
		this.kzzd2 = kzzd2;
	}

	public String getKzzd3() {
		return kzzd3;
	}

	public void setKzzd3(String kzzd3) {
		this.kzzd3 = kzzd3;
	}

	public String getKzzd4() {
		return kzzd4;
	}

	public void setKzzd4(String kzzd4) {
		this.kzzd4 = kzzd4;
	}

	public String getKzzd5() {
		return kzzd5;
	}

	public void setKzzd5(String kzzd5) {
		this.kzzd5 = kzzd5;
	}

	public String getKzzd6() {
		return kzzd6;
	}

	public void setKzzd6(String kzzd6) {
		this.kzzd6 = kzzd6;
	}

	public String getKzzd7() {
		return kzzd7;
	}

	public void setKzzd7(String kzzd7) {
		this.kzzd7 = kzzd7;
	}

	public String getOver() {
		return over;
	}

	public void setOver(String over) {
		this.over = over;
	}

	public String getPjnd() {
		return pjnd;
	}

	public void setPjnd(String pjnd) {
		this.pjnd = pjnd;
	}

	public String getPjxn() {
		return pjxn;
	}

	public void setPjxn(String pjxn) {
		this.pjxn = pjxn;
	}

	public String getPjxq() {
		return pjxq;
	}

	public void setPjxq(String pjxq) {
		this.pjxq = pjxq;
	}

	public String getSqjg() {
		return sqjg;
	}

	public void setSqjg(String sqjg) {
		this.sqjg = sqjg;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getTjr() {
		return tjr;
	}

	public void setTjr(String tjr) {
		this.tjr = tjr;
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

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
