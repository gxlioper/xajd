/**
 * @部门:学工产品事业部
 * @日期：2013-12-3 上午10:05:21 
 */  
package com.zfsoft.xgxt.xpjpy.tjcx;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-3 上午10:05:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjmdExportModel extends ActionForm{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String xn;	//学年
	private String xq;	//学期
	private String[] xmlx;//项目类型
	private String[] xmxz;//项目性质
	private String[] xydm; //学院代码
	private String[] xmmc; //项目名称
	
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
	 * @return the xmlx
	 */
	public String[] getXmlx() {
		return xmlx;
	}
	/**
	 * @param xmlx要设置的 xmlx
	 */
	public void setXmlx(String[] xmlx) {
		this.xmlx = xmlx;
	}
	public String[] getXmxz() {
		return xmxz;
	}
	public void setXmxz(String[] xmxz) {
		this.xmxz = xmxz;
	}
	/**
	 * @return the xydm
	 */
	public String[] getXydm() {
		return xydm;
	}
	/**
	 * @param xydm要设置的 xydm
	 */
	public void setXydm(String[] xydm) {
		this.xydm = xydm;
	}
	/**
	 * @return the xmmc
	 */
	public String[] getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc要设置的 xmmc
	 */
	public void setXmmc(String[] xmmc) {
		this.xmmc = xmmc;
	}
	
	
	
}
