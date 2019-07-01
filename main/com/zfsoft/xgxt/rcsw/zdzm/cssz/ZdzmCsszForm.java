/**
 * @部门:学工产品事业部
 * @日期：2014-3-3 下午02:28:07 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.cssz;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 日常事务在读证明参数设置
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-3 下午02:28:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class ZdzmCsszForm  extends ActionForm {

	/** 
	 * @变量 serialVersionUID : 2409533176766774109L
	 */ 
	private static final long serialVersionUID = 2409533176766774109L;
	//申请开关
	private String ksqkg = "1"; 
	//可申请开始时间
	private String ksqkssj;
	//可申请结束时间
	private String ksqjssj;
	//审批流id
	private String splid;
	//下载开关
	private String xzkg;
	//下载控制
	private String kxzkz = "0";
	//对应报表
	private String dybb;
	
	//开关
	
	private String isopen;
	
	/**
	 * @描述 ：无参数构造器
	 */
	public ZdzmCsszForm() {
		super();
	}
	/**
	 * @return the ksqkg
	 */
	public String getKsqkg() {
		return ksqkg;
	}
	/**
	 * @param ksqkg要设置的 ksqkg
	 */
	public void setKsqkg(String ksqkg) {
		this.ksqkg = ksqkg;
	}
	/**
	 * @return the ksqkssj
	 */
	public String getKsqkssj() {
		return ksqkssj;
	}
	/**
	 * @param ksqkssj要设置的 ksqkssj
	 */
	public void setKsqkssj(String ksqkssj) {
		this.ksqkssj = ksqkssj;
	}
	/**
	 * @return the ksqjssj
	 */
	public String getKsqjssj() {
		return ksqjssj;
	}
	/**
	 * @param ksqjssj要设置的 ksqjssj
	 */
	public void setKsqjssj(String ksqjssj) {
		this.ksqjssj = ksqjssj;
	}
	/**
	 * @return the splid
	 */
	public String getSplid() {
		return splid;
	}
	/**
	 * @param splid要设置的 splid
	 */
	public void setSplid(String splid) {
		this.splid = splid;
	}
	/**
	 * @return the xzkg
	 */
	public String getXzkg() {
		return xzkg;
	}
	/**
	 * @param xzkg要设置的 xzkg
	 */
	public void setXzkg(String xzkg) {
		this.xzkg = xzkg;
	}
	/**
	 * @return the kxzkz
	 */
	public String getKxzkz() {
		return kxzkz;
	}
	/**
	 * @param kxzkz要设置的 kxzkz
	 */
	public void setKxzkz(String kxzkz) {
		this.kxzkz = kxzkz;
	}
	/**
	 * @return the dybb
	 */
	public String getDybb() {
		return dybb;
	}
	/**
	 * @param dybb要设置的 dybb
	 */
	public void setDybb(String dybb) {
		this.dybb = dybb;
	}
	public String getIsopen() {
		return isopen;
	}
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
}
