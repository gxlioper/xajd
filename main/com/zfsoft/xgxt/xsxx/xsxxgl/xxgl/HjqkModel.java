/**
 * @部门:学工产品事业部
 * @日期：2013-12-19 下午05:21:26 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 获奖情况
 * @作者： ligl
 * @时间： 2014-2-18 下午02:16:06
 * @版本： V1.0
 * @修改记录:
 */
public class HjqkModel implements java.io.Serializable {
	private static final long serialVersionUID = -7957060390800045492L;

	private String hjid;// 主键id
	private String xh;// 学号
	private String hjsj;// 获奖时间
	private String fjdw;// 发奖单位
	private String hjmc;// 获奖名称
	private String id;
	private String hjjb;
	private String jlmc;
	private String bjdw;
	private String hjrs;
	private String brpm;
	private String bz;
	private String jldj;//奖励等级

	/**
	 * @return the jldj
	 */
	public String getJldj() {
		return jldj;
	}

	/**
	 * @param jldj要设置的 jldj
	 */
	public void setJldj(String jldj) {
		this.jldj = jldj;
	}

	/**
	 * @描述 ：
	 */
	public HjqkModel() {
		super();
	}

	public String getHjid() {
		return hjid;
	}

	public void setHjid(String hjid) {
		this.hjid = hjid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getHjsj() {
		return hjsj;
	}

	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}

	public String getFjdw() {
		return fjdw;
	}

	public void setFjdw(String fjdw) {
		this.fjdw = fjdw;
	}

	public String getHjmc() {
		return hjmc;
	}

	public void setHjmc(String hjmc) {
		this.hjmc = hjmc;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-29 上午10:41:24 
	 * @return		: the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-29 上午10:41:24 
	 * @param 		：id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-29 上午10:41:24 
	 * @return		: the hjjb
	 */
	public String getHjjb() {
		return hjjb;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-29 上午10:41:24 
	 * @param 		：hjjb the hjjb to set
	 */
	public void setHjjb(String hjjb) {
		this.hjjb = hjjb;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-29 上午10:41:24 
	 * @return		: the jlmc
	 */
	public String getJlmc() {
		return jlmc;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-29 上午10:41:24 
	 * @param 		：jlmc the jlmc to set
	 */
	public void setJlmc(String jlmc) {
		this.jlmc = jlmc;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-29 上午10:41:24 
	 * @return		: the bjdw
	 */
	public String getBjdw() {
		return bjdw;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-29 上午10:41:24 
	 * @param 		：bjdw the bjdw to set
	 */
	public void setBjdw(String bjdw) {
		this.bjdw = bjdw;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-29 上午10:41:24 
	 * @return		: the hjrs
	 */
	public String getHjrs() {
		return hjrs;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-29 上午10:41:24 
	 * @param 		：hjrs the hjrs to set
	 */
	public void setHjrs(String hjrs) {
		this.hjrs = hjrs;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-29 上午10:41:24 
	 * @return		: the brpm
	 */
	public String getBrpm() {
		return brpm;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-29 上午10:41:24 
	 * @param 		：brpm the brpm to set
	 */
	public void setBrpm(String brpm) {
		this.brpm = brpm;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-29 上午10:41:24 
	 * @return		: the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-29 上午10:41:24 
	 * @param 		：bz the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
