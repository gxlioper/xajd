/**
 * @日期：2014-12-3 下午01:53:07
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

public class KxyjModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id; //主键ID
	private String xh;   //学号
	private String lwmc; //论文名称
	private String jb;  //级别
	private String sj;  //时间
	private String kw;  //刊物
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh要设置的 xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the lwmc
	 */
	public String getLwmc() {
		return lwmc;
	}
	/**
	 * @param lwmc要设置的 lwmc
	 */
	public void setLwmc(String lwmc) {
		this.lwmc = lwmc;
	}
	/**
	 * @return the jb
	 */
	public String getJb() {
		return jb;
	}
	/**
	 * @param jb要设置的 jb
	 */
	public void setJb(String jb) {
		this.jb = jb;
	}
	/**
	 * @return the sj
	 */
	public String getSj() {
		return sj;
	}
	/**
	 * @param sj要设置的 sj
	 */
	public void setSj(String sj) {
		this.sj = sj;
	}
	/**
	 * @return the kw
	 */
	public String getKw() {
		return kw;
	}
	/**
	 * @param kw要设置的 kw
	 */
	public void setKw(String kw) {
		this.kw = kw;
	}
	
	

}
