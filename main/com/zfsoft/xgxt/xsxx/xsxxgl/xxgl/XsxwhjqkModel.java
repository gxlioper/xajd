/**
 * @部门:学工产品事业部
 * @日期：2017年5月27日 下午5:31:51 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息管理模块
 * @类功能描述: 学生（校外）获奖情况Model
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月27日 下午5:31:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxwhjqkModel implements java.io.Serializable{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -805473258583248852L;

	private String hjid;	//获奖id
	private String xh;	//学号
	private String xn;	//学年
	private String xq;	//学期
	private String xqmc;	//学期名称
	private String hjjb;	//获奖级别
	private String hjmc;	//获奖名称
	private String hjdc;	//获奖等次
	private String fzdw;	//发证单位
	private String fzsj;	//发证时间
	/**
	 * @return the hjid
	 */
	public String getHjid() {
		return hjid;
	}
	/**
	 * @param hjid要设置的 hjid
	 */
	public void setHjid(String hjid) {
		this.hjid = hjid;
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
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the hjjb
	 */
	public String getHjjb() {
		return hjjb;
	}
	/**
	 * @param hjjb要设置的 hjjb
	 */
	public void setHjjb(String hjjb) {
		this.hjjb = hjjb;
	}
	/**
	 * @return the hjmc
	 */
	public String getHjmc() {
		return hjmc;
	}
	/**
	 * @param hjmc要设置的 hjmc
	 */
	public void setHjmc(String hjmc) {
		this.hjmc = hjmc;
	}
	/**
	 * @return the hjdc
	 */
	public String getHjdc() {
		return hjdc;
	}
	/**
	 * @param hjdc要设置的 hjdc
	 */
	public void setHjdc(String hjdc) {
		this.hjdc = hjdc;
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
	 * @return the fzsj
	 */
	public String getFzsj() {
		return fzsj;
	}
	/**
	 * @param fzsj要设置的 fzsj
	 */
	public void setFzsj(String fzsj) {
		this.fzsj = fzsj;
	}
	
	
}
