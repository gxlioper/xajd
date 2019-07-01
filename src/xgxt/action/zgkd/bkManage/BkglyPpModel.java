
package xgxt.action.zgkd.bkManage;

import java.io.Serializable;

import xgxt.utils.Pages;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description:大学生生涯论坛版块管理员匹配MODEL </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: litao</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-7 下午02:15:28</p>
 */
public class BkglyPpModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String yhm;//用户名
	private String xm;//姓名
	private String bkdm;//版块代码
	
	  //通用
	Pages pages = new Pages();
	public Pages getPages() {
		return pages;
	}
	
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getBkdm() {
		return bkdm;
	}
	public void setBkdm(String bkdm) {
		this.bkdm = bkdm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
}
