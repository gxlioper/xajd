package xgxt.xszz.whtl.ybbx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-5-22 下午05:50:04</p>
 */
public class XszzYbbxForm extends ActionForm {
	
	User user = new User();
	
	Pages pages=new Pages();
	
	SearchModel searchModel=new SearchModel();

	private String xyshr; //

	private String sqid; //

	private String xxshsj; //

	private String xh; //

	private String xyshyj; //

	private String xxshyj; //

	private String xn; //

	private String xxsh; //

	private String xysh; //

	private String xyshsj; //

	private String xxshr; //
	
	private String pkValue;

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

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

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getXxshr() {
		return xxshr;
	}

	public void setXxshr(String xxshr) {
		this.xxshr = xxshr;
	}

	public String getXxshsj() {
		return xxshsj;
	}

	public void setXxshsj(String xxshsj) {
		this.xxshsj = xxshsj;
	}

	public String getXxshyj() {
		return xxshyj;
	}

	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getXyshr() {
		return xyshr;
	}

	public void setXyshr(String xyshr) {
		this.xyshr = xyshr;
	}

	public String getXyshsj() {
		return xyshsj;
	}

	public void setXyshsj(String xyshsj) {
		this.xyshsj = xyshsj;
	}

	public String getXyshyj() {
		return xyshyj;
	}

	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
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
