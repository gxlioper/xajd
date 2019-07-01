package xgxt.szdw.ntzy;

import xgxt.dtjs.gdby.tygl.BasicExtendForm;
import xgxt.utils.Pages;

public class NtzyFdypxForm extends BasicExtendForm{
	private Pages pages = new Pages();
	
	private String save_pxlx;
	private String save_pxfw;
	private String save_jtnr;
	private String save_kssj;
	private String save_jssj;
	private String save_pxxm;
	private String queryequals_pxfw;
	private String queryequals_pxlx;

	public Pages getPages() {
		return pages;
	}
	public String getQueryequals_pxfw() {
		return queryequals_pxfw;
	}
	public void setQueryequals_pxfw(String queryequalsPxfw) {
		queryequals_pxfw = queryequalsPxfw;
	}
	public String getQueryequals_pxlx() {
		return queryequals_pxlx;
	}
	public void setQueryequals_pxlx(String queryequalsPxlx) {
		queryequals_pxlx = queryequalsPxlx;
	}
	public String getSave_pxlx() {
		return save_pxlx;
	}
	public String getSave_pxxm() {
		return save_pxxm;
	}
	public void setSave_pxxm(String savePxxm) {
		save_pxxm = savePxxm;
	}
	public void setSave_pxlx(String savePxlx) {
		save_pxlx = savePxlx;
	}
	public String getSave_pxfw() {
		return save_pxfw;
	}
	public void setSave_pxfw(String savePxfw) {
		save_pxfw = savePxfw;
	}
	public String getSave_kssj() {
		return save_kssj;
	}
	public void setSave_kssj(String saveKssj) {
		save_kssj = saveKssj;
	}
	public String getSave_jssj() {
		return save_jssj;
	}
	public void setSave_jssj(String saveJssj) {
		save_jssj = saveJssj;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getSave_jtnr() {
		return save_jtnr;
	}
	public void setSave_jtnr(String saveJtnr) {
		save_jtnr = saveJtnr;
	}
}
