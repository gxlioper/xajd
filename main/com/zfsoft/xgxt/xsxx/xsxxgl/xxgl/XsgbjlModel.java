/**
 * @部门:学工产品事业部
 * @日期：2017年6月22日 下午8:22:32 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xuwen[工号:1426]
 * @时间： 2017年6月22日 下午8:22:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsgbjlModel implements java.io.Serializable{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -6234595325773018912L;
	
	private String id;	//主键
	private String xh;	//学号
	private String qsrq;	//起始日期
	private String jsrq;	//结束日期
	private String dw;	//单位
	private String zw;	//职务
	private String zmr;	//证明人
	private String zzzt;	//在职状态
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
	 * @return the dw
	 */
	public String getDw() {
		return dw;
	}
	/**
	 * @param dw要设置的 dw
	 */
	public void setDw(String dw) {
		this.dw = dw;
	}
	/**
	 * @return the zw
	 */
	public String getZw() {
		return zw;
	}
	/**
	 * @param zw要设置的 zw
	 */
	public void setZw(String zw) {
		this.zw = zw;
	}
	/**
	 * @return the zmr
	 */
	public String getZmr() {
		return zmr;
	}
	/**
	 * @param zmr要设置的 zmr
	 */
	public void setZmr(String zmr) {
		this.zmr = zmr;
	}
	/**
	 * @return the zzzt
	 */
	public String getZzzt() {
		return zzzt;
	}
	/**
	 * @param zzzt要设置的 zzzt
	 */
	public void setZzzt(String zzzt) {
		this.zzzt = zzzt;
	}
	
}
