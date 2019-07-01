package xgxt.pjpy.zgms;

import xgxt.pjpy.PjpyActionForm;
import xgxt.utils.Pages;

public class PjpyZgmsActionForm extends PjpyActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3989229110736737536L;

	private String kksj;
	private String kkdd;
	private String kkjl;
	private String tid;
	private String tydb;
	private String bz;
	
	Pages pages = new Pages();

	public Pages getPages() {
		return pages;
	}
	
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTydb() {
		return tydb;
	}
	public void setTydb(String tydb) {
		this.tydb = tydb;
	}
	public String getKkdd() {
		return kkdd;
	}
	public void setKkdd(String kkdd) {
		this.kkdd = kkdd;
	}
	public String getKkjl() {
		return kkjl;
	}
	public void setKkjl(String kkjl) {
		this.kkjl = kkjl;
	}
	public String getKksj() {
		return kksj;
	}
	public void setKksj(String kksj) {
		this.kksj = kksj;
	}
}
