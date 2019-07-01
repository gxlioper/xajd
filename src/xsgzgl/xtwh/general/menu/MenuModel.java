package xsgzgl.xtwh.general.menu;

import org.apache.struts.action.ActionForm;

public class MenuModel extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String gnmkdm;
	private String gnmkmc;
	
	public String getGnmkdm() {
		return gnmkdm;
	}
	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
	}
	public String getGnmkmc() {
		return gnmkmc;
	}
	public void setGnmkmc(String gnmkmc) {
		this.gnmkmc = gnmkmc;
	}
	
	
}
