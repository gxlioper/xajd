package com.zfsoft.xgxt.pjpy.xzhcp;

import org.apache.struts.action.ActionForm;

public class ZhcpForm extends ActionForm {
	private String sqid;
	private String splc;
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
}
