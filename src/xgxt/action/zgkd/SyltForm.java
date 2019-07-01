/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description:大学生生涯论坛form </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-4 下午04:15:28</p>
 */
package xgxt.action.zgkd;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;
import xgxt.utils.Pages;

public class SyltForm extends ActionForm {
	private static final long serialVersionUID = -1787472627104486086L;
	private String yhm;//用户名
	private String nc;//昵称
	private String grqm;//个人签名
	private String bkdm;//板块代码
	private String bkmc;//板块名称
	private String xm;//用户姓名
	private String[] cbv;//主键列表
	private String bkms;//板块描述
	
	private String bklb; //板块列表
	
    //通用
	Pages pages = new Pages();
	public Pages getPages() {
		return pages;
	}
	
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	public String getBkms() {
		return bkms;
	}
	public void setBkms(String bkms) {
		this.bkms = DealString.toGBK(bkms);
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = DealString.toGBK(xm);
	}
	public String getBkdm() {
		return bkdm;
	}
	public void setBkdm(String bkdm) {
		this.bkdm = DealString.toGBK(bkdm);
	}
	public String getGrqm() {
		return grqm;
	}
	public void setGrqm(String grqm) {
		this.grqm = DealString.toGBK(grqm);
	}
	public String getNc() {
		return nc;
	}
	public void setNc(String nc) {
		this.nc = DealString.toGBK(nc);
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = DealString.toGBK(yhm);
	}
	public String getBkmc() {
		return bkmc;
	}
	public void setBkmc(String bkmc) {
		this.bkmc = DealString.toGBK(bkmc);
	}

	public String getBklb() {
		return bklb;
	}

	public void setBklb(String bklb) {
		this.bklb = bklb;
	}
  
}
