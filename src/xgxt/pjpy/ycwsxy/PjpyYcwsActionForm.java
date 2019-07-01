package xgxt.pjpy.ycwsxy;

import xgxt.pjpy.PjpyActionForm;
import xgxt.utils.Pages;

public class PjpyYcwsActionForm extends PjpyActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jxhjxm;
	
	private String hjmx;
	
	private String bz;

	private String jxjxdm;
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

	public String getHjmx() {
		return hjmx;
	}

	public void setHjmx(String hjmx) {
		this.hjmx = hjmx;
	}

	public String getJxhjxm() {
		return jxhjxm;
	}

	public void setJxhjxm(String jxhjxm) {
		this.jxhjxm = jxhjxm;
	}

	public String getJxjxdm() {
		return jxjxdm;
	}

	public void setJxjxdm(String jxjxdm) {
		this.jxjxdm = jxjxdm;
	}
}
