/**
 * @部门:学工产品事业部
 * @日期：2015-7-24 上午09:13:10 
 */  
package com.zfsoft.xgxt.szdw.fdyxx;

import java.io.Serializable;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述: 辅导员信息-教学工作
 * @作者： 沈晓波[工号:1123]
 * @时间： 2015-12-16 上午10:45:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JxgzModel implements Serializable{

	private static final long serialVersionUID = -8692278407785586702L;
	
	private String jxid;
	private String qzsj;
	private String skmc;
	private String skcc;
	private String jxgzl;
	private String pjjl;
	private String ndkhjl;
	
	
	public String getJxid() {
		return jxid;
	}
	public void setJxid(String jxid) {
		this.jxid = jxid;
	}
	public String getQzsj() {
		return qzsj;
	}
	public void setQzsj(String qzsj) {
		this.qzsj = qzsj;
	}
	public String getSkmc() {
		return skmc;
	}
	public void setSkmc(String skmc) {
		this.skmc = skmc;
	}
	public String getSkcc() {
		return skcc;
	}
	public void setSkcc(String skcc) {
		this.skcc = skcc;
	}
	public String getJxgzl() {
		return jxgzl;
	}
	public void setJxgzl(String jxgzl) {
		this.jxgzl = jxgzl;
	}
	public String getPjjl() {
		return pjjl;
	}
	public void setPjjl(String pjjl) {
		this.pjjl = pjjl;
	}
	public String getNdkhjl() {
		return ndkhjl;
	}
	public void setNdkhjl(String ndkhjl) {
		this.ndkhjl = ndkhjl;
	}

}
