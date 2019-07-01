/**
 * @部门:学工产品事业部
 * @日期：2017年6月22日 下午11:20:37 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xuwen[工号:1426]
 * @时间： 2017年6月22日 下午11:20:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QtcgModel implements java.io.Serializable{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -8637902149383461547L;
	
	private String id;	//主键
	private String xh;	//学号
	private String cgmc;	//成果名称
	private String cglx;	//成果类型
	private String cgjb;	//成果级别
	private String fbny;	//发布年月
	private String cgzrs;	//成果总人数
	private String brpm;	//本人排名
	private String dyzzwds;	//第一作者是否为导师
	private String bz;	// 备注
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
	 * @return the cgmc
	 */
	public String getCgmc() {
		return cgmc;
	}
	/**
	 * @param cgmc要设置的 cgmc
	 */
	public void setCgmc(String cgmc) {
		this.cgmc = cgmc;
	}
	/**
	 * @return the cglx
	 */
	public String getCglx() {
		return cglx;
	}
	/**
	 * @param cglx要设置的 cglx
	 */
	public void setCglx(String cglx) {
		this.cglx = cglx;
	}
	/**
	 * @return the cgjb
	 */
	public String getCgjb() {
		return cgjb;
	}
	/**
	 * @param cgjb要设置的 cgjb
	 */
	public void setCgjb(String cgjb) {
		this.cgjb = cgjb;
	}
	/**
	 * @return the fbny
	 */
	public String getFbny() {
		return fbny;
	}
	/**
	 * @param fbny要设置的 fbny
	 */
	public void setFbny(String fbny) {
		this.fbny = fbny;
	}
	/**
	 * @return the cgzrs
	 */
	public String getCgzrs() {
		return cgzrs;
	}
	/**
	 * @param cgzrs要设置的 cgzrs
	 */
	public void setCgzrs(String cgzrs) {
		this.cgzrs = cgzrs;
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
