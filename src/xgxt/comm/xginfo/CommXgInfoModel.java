package xgxt.comm.xginfo;

import xgxt.form.User;
import xgxt.utils.Pages;

public class CommXgInfoModel {
	private Pages pages = new Pages();
	
	private String nj;
	
	private String xydm;
	
	private String xymc;
	
	private String zydm;
	
	private String zymc;
	
	private String bjdm;
	
	private String bjmc;
	
	private String tjbcyfs;
	
	private String lcmc;
	
	private String yxj;
	private User user;

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getTjbcyfs() {
		return tjbcyfs;
	}

	public void setTjbcyfs(String tjbcyfs) {
		this.tjbcyfs = tjbcyfs;
	}

	public String getLcmc() {
		return lcmc;
	}

	public void setLcmc(String lcmc) {
		this.lcmc = lcmc;
	}

	public String getYxj() {
		return yxj;
	}

	public void setYxj(String yxj) {
		this.yxj = yxj;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
}
