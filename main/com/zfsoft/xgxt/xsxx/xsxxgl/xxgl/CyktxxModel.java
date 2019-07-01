/**
 * @部门:学工产品事业部
 * @日期：2018-4-17 下午02:08:37 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-4-17 下午02:08:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CyktxxModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id; // 主键ID
	private String xh;   // 学号
	private String cyktsj; // 参与课题时间
	private String ktmc; // 课题名称
	private String ktlb; // 课题类别
	private String ktjb;   // 课题级别
	private String sfwktfzr;   // 是否为课题负责人
	private String scdrw;   // 所承担任务
	private String fjid;   // 附件
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
	 * @return the cyktsj
	 */
	public String getCyktsj() {
		return cyktsj;
	}
	/**
	 * @param cyktsj要设置的 cyktsj
	 */
	public void setCyktsj(String cyktsj) {
		this.cyktsj = cyktsj;
	}
	/**
	 * @return the ktmc
	 */
	public String getKtmc() {
		return ktmc;
	}
	/**
	 * @param ktmc要设置的 ktmc
	 */
	public void setKtmc(String ktmc) {
		this.ktmc = ktmc;
	}
	/**
	 * @return the ktlb
	 */
	public String getKtlb() {
		return ktlb;
	}
	/**
	 * @param ktlb要设置的 ktlb
	 */
	public void setKtlb(String ktlb) {
		this.ktlb = ktlb;
	}
	/**
	 * @return the ktjb
	 */
	public String getKtjb() {
		return ktjb;
	}
	/**
	 * @param ktjb要设置的 ktjb
	 */
	public void setKtjb(String ktjb) {
		this.ktjb = ktjb;
	}
	/**
	 * @return the sfwktfzr
	 */
	public String getSfwktfzr() {
		return sfwktfzr;
	}
	/**
	 * @param sfwktfzr要设置的 sfwktfzr
	 */
	public void setSfwktfzr(String sfwktfzr) {
		this.sfwktfzr = sfwktfzr;
	}
	/**
	 * @return the scdrw
	 */
	public String getScdrw() {
		return scdrw;
	}
	/**
	 * @param scdrw要设置的 scdrw
	 */
	public void setScdrw(String scdrw) {
		this.scdrw = scdrw;
	}
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

}
