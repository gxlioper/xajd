package xgxt.wjsc.wjff;

import xgxt.utils.Pages;

public class WjffModel {

	Pages pages = new Pages();
	
	private String wjh;//�ļ���
	
	private String wjm;//�ļ���
	
	private String ffr;//������

	private String wjffKssj;//�ļ����ſ�ʼʱ��
	
	private String wjffJssj;//�ļ����Ž���ʱ��
	
	public String getFfr() {
		return ffr;
	}
	
	public void setFfr(String ffr) {
		this.ffr = ffr;
	}
	
	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getWjh() {
		return wjh;
	}

	public void setWjh(String wjh) {
		this.wjh = wjh;
	}

	public String getWjm() {
		return wjm;
	}

	public void setWjm(String wjm) {
		this.wjm = wjm;
	}

	public String getWjffKssj() {
		return wjffKssj;
	}

	public void setWjffKssj(String wjffKssj) {
		this.wjffKssj = wjffKssj;
	}

	public String getWjffJssj() {
		return wjffJssj;
	}

	public void setWjffJssj(String wjffJssj) {
		this.wjffJssj = wjffJssj;
	}

	
}
