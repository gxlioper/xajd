package xgxt.pjpy.ghxy.bjryf;

import xgxt.utils.Pages;

public class GhxyBjryfModel {
	private String xmfz[];     //��Ŀ��ֵ
	private String pc[];     //����
	private String xn[];     //ѧ��
	private String xq[];     //ѧ��
	private String plbjdm[];     //�����༶����
	private String xmdm[];     //��Ŀ����
	private String checkVal[]; //
	
	private String bjdm;
	private String zydm;
	private String xydm;
	
	private Pages pages=new Pages();

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String[] getPc() {
		return pc;
	}

	public void setPc(String[] pc) {
		this.pc = pc;
	}

	public String[] getPlbjdm() {
		return plbjdm;
	}

	public void setPlbjdm(String[] plbjdm) {
		this.plbjdm = plbjdm;
	}

	

	public String[] getXmdm() {
		return xmdm;
	}

	public void setXmdm(String[] xmdm) {
		this.xmdm = xmdm;
	}

	public String[] getXmfz() {
		return xmfz;
	}

	public void setXmfz(String[] xmfz) {
		this.xmfz = xmfz;
	}

	public String[] getXn() {
		return xn;
	}

	public void setXn(String[] xn) {
		this.xn = xn;
	}

	public String[] getXq() {
		return xq;
	}

	public void setXq(String[] xq) {
		this.xq = xq;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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

	

}
