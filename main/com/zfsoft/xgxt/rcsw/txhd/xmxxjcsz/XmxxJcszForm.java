package com.zfsoft.xgxt.rcsw.txhd.xmxxjcsz;

import org.apache.struts.action.ActionForm;

/**
 * 基础设置
 */
public class XmxxJcszForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private String sqkg;
	private String splc;
	private String sqkssj;
	private String sqjssj;
	private String isopen ;//当前时间是否开启
	
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssj要设置的 sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssj要设置的 sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	/**
	 * @return the isopen
	 */
	public String getIsopen() {
		return isopen;
	}
	/**
	 * @param isopen要设置的 isopen
	 */
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkg要设置的 sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}

}
