/**
 * @部门:学工产品事业部
 * @日期：2017年6月22日 下午8:34:15 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xuwen[工号:1426]
 * @时间： 2017年6月22日 下午8:34:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CgcjjlModel implements java.io.Serializable{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 2337244440572917394L;
	
	private String id; 	//主键
	private String xh; 	//学号
	private String lb; 	//类别
	private String xmmc; 	//项目名称
	private String gb; 	//国别
	private String qsrq; 	//起始日期
	private String jsrq; 	//结束日期
	private String xmfl; 	//项目分类
	private String jldw; 	//交流单位
	private String fjid; 	//附件
	
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
	 * @return the lb
	 */
	public String getLb() {
		return lb;
	}
	/**
	 * @param lb要设置的 lb
	 */
	public void setLb(String lb) {
		this.lb = lb;
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
	 * @return the gb
	 */
	public String getGb() {
		return gb;
	}
	/**
	 * @param gb要设置的 gb
	 */
	public void setGb(String gb) {
		this.gb = gb;
	}
	/**
	 * @return the qsrq
	 */
	public String getQsrq() {
		return qsrq;
	}
	/**
	 * @param qsrq要设置的 qsrq
	 */
	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
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
	 * @return the xmfl
	 */
	public String getXmfl() {
		return xmfl;
	}
	/**
	 * @param xmfl要设置的 xmfl
	 */
	public void setXmfl(String xmfl) {
		this.xmfl = xmfl;
	}
	/**
	 * @return the jldw
	 */
	public String getJldw() {
		return jldw;
	}
	/**
	 * @param jldw要设置的 jldw
	 */
	public void setJldw(String jldw) {
		this.jldw = jldw;
	}
	

}
