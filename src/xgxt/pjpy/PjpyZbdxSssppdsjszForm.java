package xgxt.pjpy;

import org.apache.struts.action.ActionForm;

public class PjpyZbdxSssppdsjszForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String xn ;
	private String xspdkssj;
	private String xspdjssj; 
	private String jspdkssj; 
	private String jspdjssj;
	
	public void setXn(String xn){
		this.xn = xn;
	}
	
	public String getXn(){
		return this.xn;
	}
	
	public void setXspdkssj(String xspdkssj){
		this.xspdkssj = xspdkssj;
	}
	
	public String getXspdkssj(){
		return this.xspdkssj;
	}
	
	public void setXspdjssj(String xspdjssj){
		this.xspdjssj = xspdjssj;
	}
	
	public String getXspdjssj(){
		return this.xspdjssj;
	}
	
	public void setJspdkssj(String jspdkssj){
		this.jspdkssj = jspdkssj;
	}
	
	public String getJspdkssj(){
		return this.jspdkssj;
	}
	
	public void setJspdjssj(String jspdjssj){
		this.jspdjssj = jspdjssj;
	}
	
	public String getJspdjssj(){
		return this.jspdjssj;
	}
}
