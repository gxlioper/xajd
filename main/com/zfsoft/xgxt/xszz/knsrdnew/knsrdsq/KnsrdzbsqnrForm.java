/**
 * @部门:学工产品事业部
 * @日期：2013-12-17 上午11:47:02 
 */  
package com.zfsoft.xgxt.xszz.knsrdnew.knsrdsq;

import com.zfsoft.xgxt.xszz.knsrdnew.comm.KnsrdForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 困难指标申请管理模块
 * @类功能描述: TODO(困难指标申请-指标内容表) 
 * @作者：Dlq[工号:995]
 * @时间： 2014-1-24 下午02:59:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KnsrdzbsqnrForm extends KnsrdForm {
	
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;
	private String id;     
	private String sqid;  
	private String nrid;                
	private String sqfz;                
	private String shfz;
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
	 * @return the nrid
	 */
	public String getNrid() {
		return nrid;
	}
	/**
	 * @param nrid要设置的 nrid
	 */
	public void setNrid(String nrid) {
		this.nrid = nrid;
	}
	/**
	 * @return the sqfz
	 */
	public String getSqfz() {
		return sqfz;
	}
	/**
	 * @param sqfz要设置的 sqfz
	 */
	public void setSqfz(String sqfz) {
		this.sqfz = sqfz;
	}
	/**
	 * @return the shfz
	 */
	public String getShfz() {
		return shfz;
	}
	/**
	 * @param shfz要设置的 shfz
	 */
	public void setShfz(String shfz) {
		this.shfz = shfz;
	}
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}               
}
