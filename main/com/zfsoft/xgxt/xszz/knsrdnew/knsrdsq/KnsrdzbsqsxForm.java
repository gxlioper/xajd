/**
 * @部门:学工产品事业部
 * @日期：2013-12-17 上午11:47:02 
 */  
package com.zfsoft.xgxt.xszz.knsrdnew.knsrdsq;

import com.zfsoft.xgxt.xszz.knsrdnew.comm.KnsrdForm;





/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 困难生指标申请管理模块
 * @类功能描述: TODO(困难生指标申请-指标属性) 
 * @作者：Dlq[工号:995]
 * @时间： 2014-1-24 下午03:01:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KnsrdzbsqsxForm extends KnsrdForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 6069222938427848815L;
	private String id;
	private String sqid;  
	private String sxid;  
	private String jtqk;
	
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
	 * @return the sxid
	 */
	public String getSxid() {
		return sxid;
	}
	/**
	 * @param sxid要设置的 sxid
	 */
	public void setSxid(String sxid) {
		this.sxid = sxid;
	}
	/**
	 * @return the jtqk
	 */
	public String getJtqk() {
		return jtqk;
	}
	/**
	 * @param jtqk要设置的 jtqk
	 */
	public void setJtqk(String jtqk) {
		this.jtqk = jtqk;
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
	};               
}
