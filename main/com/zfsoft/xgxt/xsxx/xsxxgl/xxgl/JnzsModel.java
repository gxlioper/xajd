/**
 * @部门:学工产品事业部
 * @日期：2014-12-3 下午01:53:07
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

public class JnzsModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id; //主键ID
	private String xh;   //学号
	private String jnzs; //技能证书
	private String sj;  //时间
	private String fzdw;  //发证单位
	private String dj;  //等级
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
	 * @return the jnzs
	 */
	public String getJnzs() {
		return jnzs;
	}
	/**
	 * @param jnzs要设置的 jnzs
	 */
	public void setJnzs(String jnzs) {
		this.jnzs = jnzs;
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
	 * @return the fzdw
	 */
	public String getFzdw() {
		return fzdw;
	}
	/**
	 * @param fzdw要设置的 fzdw
	 */
	public void setFzdw(String fzdw) {
		this.fzdw = fzdw;
	}
	/**
	 * @return the dj
	 */
	public String getDj() {
		return dj;
	}
	/**
	 * @param dj要设置的 dj
	 */
	public void setDj(String dj) {
		this.dj = dj;
	}

}
