package com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhklx;

import com.zfsoft.xgxt.rcsw.hcyhkgl.comm.HcyhkForm;

/**
 * 火车优惠卡类型维护
 */
public class HcyhklxForm extends HcyhkForm{
	
	private static final long serialVersionUID = -8998807497101093040L;
	private String type;
	private String lxdm;
	private String lxmc;
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}
	/**
	 * @param lxdm要设置的 lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	/**
	 * @return the lxmc
	 */
	public String getLxmc() {
		return lxmc;
	}
	/**
	 * @param lxmc要设置的 lxmc
	 */
	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}
	
}
