/**
 * @部门:学工产品事业部
 * @日期：2017年6月22日 上午10:45:24 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息管理模块
 * @类功能描述: 等级考试成绩
 * @作者： xuwen[工号:1426]
 * @时间： 2017年6月22日 上午10:45:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DjkscjModel implements java.io.Serializable{
	private static final long serialVersionUID = 6010256160977658735L;
	
	private String id;	//主键
	private String xh;	//学号
	private String djksdm;	//等级考试代码
	private String djksmc;	//等级考试名称
	private String zyjnspdm;	//专业技能水平代码
	private String zyjnspmc;	//专业技能水平名称
	private String hqsj;	//获取时间
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
	 * @return the djksdm
	 */
	public String getDjksdm() {
		return djksdm;
	}
	/**
	 * @param djksdm要设置的 djksdm
	 */
	public void setDjksdm(String djksdm) {
		this.djksdm = djksdm;
	}
	/**
	 * @return the djksmc
	 */
	public String getDjksmc() {
		return djksmc;
	}
	/**
	 * @param djksmc要设置的 djksmc
	 */
	public void setDjksmc(String djksmc) {
		this.djksmc = djksmc;
	}
	/**
	 * @return the zyjnspdm
	 */
	public String getZyjnspdm() {
		return zyjnspdm;
	}
	/**
	 * @param zyjnspdm要设置的 zyjnspdm
	 */
	public void setZyjnspdm(String zyjnspdm) {
		this.zyjnspdm = zyjnspdm;
	}
	/**
	 * @return the zyjnspmc
	 */
	public String getZyjnspmc() {
		return zyjnspmc;
	}
	/**
	 * @param zyjnspmc要设置的 zyjnspmc
	 */
	public void setZyjnspmc(String zyjnspmc) {
		this.zyjnspmc = zyjnspmc;
	}
	/**
	 * @return the hqsj
	 */
	public String getHqsj() {
		return hqsj;
	}
	/**
	 * @param hqsj要设置的 hqsj
	 */
	public void setHqsj(String hqsj) {
		this.hqsj = hqsj;
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
