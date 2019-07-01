/**
 * @部门:学工产品事业部
 * @日期：2017年6月22日 下午11:17:32 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xuwen[工号:1426]
 * @时间： 2017年6月22日 下午11:17:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FblwModel implements java.io.Serializable{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -7992314622839609507L;
	
	private String id;	//主键
	private String xh;	//学号
	private String lwmc;	//论文名称
	private String kwmc;	//刊物名称
	private String kwjb;	//刊物级别
	private String fbrq;	//发表日期
	private String jh;	//卷号
	private String qh;	//期号
	private String brpm;	//本人排名
	private String dyzzwds;	//第一作者是否为导师
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
	 * @return the kwmc
	 */
	public String getKwmc() {
		return kwmc;
	}
	/**
	 * @param kwmc要设置的 kwmc
	 */
	public void setKwmc(String kwmc) {
		this.kwmc = kwmc;
	}
	/**
	 * @return the kwjb
	 */
	public String getKwjb() {
		return kwjb;
	}
	/**
	 * @param kwjb要设置的 kwjb
	 */
	public void setKwjb(String kwjb) {
		this.kwjb = kwjb;
	}
	/**
	 * @return the fbrq
	 */
	public String getFbrq() {
		return fbrq;
	}
	/**
	 * @param fbrq要设置的 fbrq
	 */
	public void setFbrq(String fbrq) {
		this.fbrq = fbrq;
	}
	/**
	 * @return the jh
	 */
	public String getJh() {
		return jh;
	}
	/**
	 * @param jh要设置的 jh
	 */
	public void setJh(String jh) {
		this.jh = jh;
	}
	/**
	 * @return the qh
	 */
	public String getQh() {
		return qh;
	}
	/**
	 * @param qh要设置的 qh
	 */
	public void setQh(String qh) {
		this.qh = qh;
	}
	/**
	 * @return the brpm
	 */
	public String getBrpm() {
		return brpm;
	}
	/**
	 * @param brpm要设置的 brpm
	 */
	public void setBrpm(String brpm) {
		this.brpm = brpm;
	}
	/**
	 * @return the dyzzwds
	 */
	public String getDyzzwds() {
		return dyzzwds;
	}
	/**
	 * @param dyzzwds要设置的 dyzzwds
	 */
	public void setDyzzwds(String dyzzwds) {
		this.dyzzwds = dyzzwds;
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
