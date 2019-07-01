package xsgzgl.xsxx.general.jcsz;

import xsgzgl.comm.form.CommForm;

public class XsxxJcszForm extends CommForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String kgzt;
	
	private String shlid;
	
	private String sqcs;
	private String sqkssj;//申请开始时间
	private String sqjssj;//申请结束时间
	private String shkg;//审核开关
	private String shkssj;//审核开始时间
	private String shjssj;//审核结束时间

	public String getKgzt() {
		return kgzt;
	}

	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}

	public String getShlid() {
		return shlid;
	}

	public void setShlid(String shlid) {
		this.shlid = shlid;
	}
	
	/**
	 * @return the sqcs
	 */
	public String getSqcs() {
		return sqcs;
	}

	/**
	 * @param sqcs要设置的 sqcs
	 */
	public void setSqcs(String sqcs) {
		this.sqcs = sqcs;
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
	 * @return the shkg
	 */
	public String getShkg() {
		return shkg;
	}

	/**
	 * @param shkg要设置的 shkg
	 */
	public void setShkg(String shkg) {
		this.shkg = shkg;
	}

	/**
	 * @return the shkssj
	 */
	public String getShkssj() {
		return shkssj;
	}

	/**
	 * @param shkssj要设置的 shkssj
	 */
	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}

	/**
	 * @return the shjssj
	 */
	public String getShjssj() {
		return shjssj;
	}

	/**
	 * @param shjssj要设置的 shjssj
	 */
	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}

	
	
}
