package xgxt.rcsw.xhgl;

import org.apache.struts.action.ActionForm;

import xgxt.form.User;
import xgxt.utils.Pages;

public class XhglForm extends ActionForm{
	
	User user = new User();
	
	Pages pages=new Pages();
	
	private String xm;
	
	private String nj;    //�꼶
	
	private String xydm;   //ѧԺ����
	
	private String zydm;   //רҵ����
	
	private String bjdm;   //�༶����
	
	private String xh;     //ѧ��
	
	private String bz;     //��ע
	
	private String xn;     //ѧ��
	
	private String xq;     //ѧ��
	
	private String jbr;     //������
	
	private String ffsj;     //����ʱ��
	
	private String nd;     //���
	
	private String sfff;   //�Ƿ񷢷�
	
	private String pkValue; // ����
	
	private String fdysh;
	
	private String xysh;
	
	private String xxsh;
	
	private String []pkV;//checkBox ����
	
	private String select_jbr;//������
	
	public String getSelect_jbr() {
		return select_jbr;
	}
	public void setSelect_jbr(String select_jbr) {
		this.select_jbr = select_jbr;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getFfsj() {
		return ffsj;
	}
	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
	}
	public String getJbr() {
		return jbr;
	}
	public void setJbr(String jbr) {
		this.jbr = jbr;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
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
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String[] getPkV() {
		return pkV;
	}
	public void setPkV(String[] pkV) {
		this.pkV = pkV;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getSfff() {
		return sfff;
	}
	public void setSfff(String sfff) {
		this.sfff = sfff;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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
	public String getFdysh() {
		return fdysh;
	}
	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
