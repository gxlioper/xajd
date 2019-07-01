package com.zfsoft.xgxt.pjpy.hjsq.cssz;

import org.apache.struts.action.ActionForm;

public class CsszForm extends ActionForm {
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String splc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
}
	