
package xgxt.rcgl;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class rcgl_form extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errMsg;
	private String[] errMsgs;
	
	private String xh = "";//学号
	private String xbdm="";//性别代码
	private String xb="";//性别
	private String xm="";//姓名
	private String xydm="";//学院代码
	private String xymc="";//学院名称
	private String zydm="";//专业代码
	private String zymc="";//专业名称
	private String bjdm="";//班级代码
	private String bjmc="";//班级名称
	private String nj="";//年级
	private String yesNo;//审核标志
	private String xn="";//学年
	private String xq="";//学期
	private String bbxmmc;

	public String getBbxmmc() {
		return bbxmmc;
	}
	public void setBbxmmc(String bbxmmc) {
		this.bbxmmc = bbxmmc;
	}
	Pages pages = new Pages();//分页
	
	
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXbdm() {
		return xbdm;
	}
	public void setXbdm(String xbdm) {
		this.xbdm = xbdm;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String[] getErrMsgs() {
		return errMsgs;
	}
	public void setErrMsgs(String[] errMsgs) {
		this.errMsgs = errMsgs;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}
}
