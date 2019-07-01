package xgxt.szdw.ghxy.njzrwh;

import xgxt.utils.Pages;

public class GhxyNjzrwhModel {
	private String bmdm;
	private String []nj;
	private String []CheckVal;
	private String []zgh;
	
	private String save_nj;
	
	private String queryequals_bmdm;
	private String queryequals_nj;
	
	private Pages pages=new Pages();
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	public String getQueryequals_nj() {
		return queryequals_nj;
	}
	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}
	
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getQueryequals_bmdm() {
		return queryequals_bmdm;
	}
	public void setQueryequals_bmdm(String queryequals_bmdm) {
		this.queryequals_bmdm = queryequals_bmdm;
	}
	
	public String getSave_nj() {
		return save_nj;
	}
	public void setSave_nj(String save_nj) {
		this.save_nj = save_nj;
	}
	
	public String[] getCheckVal() {
		return CheckVal;
	}
	public void setCheckVal(String[] checkVal) {
		CheckVal = checkVal;
	}
	public void setZgh(String[] zgh) {
		this.zgh = zgh;
	}
	public String[] getZgh() {
		return zgh;
	}
	public String[] getNj() {
		return nj;
	}
	public void setNj(String[] nj) {
		this.nj = nj;
	}
	
}
