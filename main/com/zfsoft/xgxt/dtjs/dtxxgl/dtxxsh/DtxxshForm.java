/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:00:24 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsh;

import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq.DtxxsqForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团管理模块
 * @类功能描述:form
 * @作者： 张昌路[工号:982]
 * @时间： 2013-10-24 下午12:00:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DtxxshForm extends DtxxsqForm {
	private static final long serialVersionUID = -7844335000460670544L;
	//审核相关
	private String shyj;
	private String gwid;
	private String shid;
	private String thgw;
	private String[] ids;
	private String[] gwids;
	private String[] xhs;
	private String[]splcs;
	private String [] jddms;
	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyj要设置的 shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwid要设置的 gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shid要设置的 shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgw要设置的 thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}
	/**
	 * @param ids要设置的 ids
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @param gwids要设置的 gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @param xhs要设置的 xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	/**
	 * @return the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}
	/**
	 * @param splcs要设置的 splcs
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	public String[] getJddms() {
		return jddms;
	}
	public void setJddms(String[] jddms) {
		this.jddms = jddms;
	}
	
	
}
