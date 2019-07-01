package xgxt.form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class TestModifyForm extends ActionForm implements Serializable{
	Pages pages = new Pages();
	
	private String isFdy;           //辅导员
	private String isBzr;           //班主任
	private String userName;        //用户名
	
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
}
