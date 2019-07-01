/**
 * @部门:学工产品事业部
 * @日期：2017年6月22日 下午11:19:16 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xuwen[工号:1426]
 * @时间： 2017年6月22日 下午11:19:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KyxmModel implements java.io.Serializable{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1488495199596195796L;
	
	private String id;	//主键
	private String xh;	//学号
	private String xmmc;	//项目名称
	private String xmly;	//项目来源
	private String xmbh;	//项目编号
	private String lxrq;	//立项日期
	private String jsrq;	//结束日期
	private String fzr;	//负责人
	private String bz;	//备注
	private String fjid;	//附件
	
	/**
	 * @return the fjid
	 */
	public String getFjid() {
		return fjid;
	}
	/**
	 * @param fjid要设置的 fjid
	 */
	public void setFjid(String fjid) {
		this.fjid = fjid;
	}
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
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc要设置的 xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the xmly
	 */
	public String getXmly() {
		return xmly;
	}
	/**
	 * @param xmly要设置的 xmly
	 */
	public void setXmly(String xmly) {
		this.xmly = xmly;
	}
	/**
	 * @return the xmbh
	 */
	public String getXmbh() {
		return xmbh;
	}
	/**
	 * @param xmbh要设置的 xmbh
	 */
	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}
	/**
	 * @return the lxrq
	 */
	public String getLxrq() {
		return lxrq;
	}
	/**
	 * @param lxrq要设置的 lxrq
	 */
	public void setLxrq(String lxrq) {
		this.lxrq = lxrq;
	}
	/**
	 * @return the jsrq
	 */
	public String getJsrq() {
		return jsrq;
	}
	/**
	 * @param jsrq要设置的 jsrq
	 */
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}
	/**
	 * @return the fzr
	 */
	public String getFzr() {
		return fzr;
	}
	/**
	 * @param fzr要设置的 fzr
	 */
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	

}
