package com.zfsoft.xgxt.xpjpy.pjpybjpy.jcsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class JcszForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String bjpyisopen ;//当前时间是否开启
	private String bjpykg; // 评奖班级评议开关
	private String bjpykssj; // 评奖班级评议开始时间
	private String bjpyjssj; // 评奖班级评议结束时间
	private String xzrsxx; // 小组人数下限
	private String pyyxbl; // 评议有效比例
	private String xzrssfbxwjs; // 小组人数是否必须为奇数
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getBjpyisopen() {
		return bjpyisopen;
	}
	public void setBjpyisopen(String bjpyisopen) {
		this.bjpyisopen = bjpyisopen;
	}
	public String getBjpykg() {
		return bjpykg;
	}
	public void setBjpykg(String bjpykg) {
		this.bjpykg = bjpykg;
	}
	public String getBjpykssj() {
		return bjpykssj;
	}
	public void setBjpykssj(String bjpykssj) {
		this.bjpykssj = bjpykssj;
	}
	public String getBjpyjssj() {
		return bjpyjssj;
	}
	public void setBjpyjssj(String bjpyjssj) {
		this.bjpyjssj = bjpyjssj;
	}
	public String getXzrsxx() {
		return xzrsxx;
	}
	public void setXzrsxx(String xzrsxx) {
		this.xzrsxx = xzrsxx;
	}
	public String getPyyxbl() {
		return pyyxbl;
	}
	public void setPyyxbl(String pyyxbl) {
		this.pyyxbl = pyyxbl;
	}
	public String getXzrssfbxwjs() {
		return xzrssfbxwjs;
	}
	public void setXzrssfbxwjs(String xzrssfbxwjs) {
		this.xzrssfbxwjs = xzrssfbxwjs;
	}
	
}
