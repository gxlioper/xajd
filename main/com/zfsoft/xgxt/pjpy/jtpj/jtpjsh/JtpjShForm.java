/**
 * @部门:学工产品事业部
 * @日期：2014-5-4 下午03:36:35 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpjsh;

import com.zfsoft.xgxt.pjpy.jtpj.jtpjsq.JtpjSqForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-5-4 下午03:36:35
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JtpjShForm extends JtpjSqForm {
	// 审核相关
	private String shyj;
	private String gwid;
	private String shid;
	private String shjg;
	private String thgw;
	private String[] sqids;
	private String[] gwids;
	private String[] splcs;
	private String[] pjjtmcs;
	
	
	
	/**
	 * @return the pjjtmcs
	 */
	public String[] getPjjtmcs() {
		return pjjtmcs;
	}

	/**
	 * @param pjjtmcs要设置的 pjjtmcs
	 */
	public void setPjjtmcs(String[] pjjtmcs) {
		this.pjjtmcs = pjjtmcs;
	}

	/**
	 * @return the sqids
	 */
	public String[] getSqids() {
		return sqids;
	}

	/**
	 * @param sqids要设置的 sqids
	 */
	public void setSqids(String[] sqids) {
		this.sqids = sqids;
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

	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}

	/**
	 * @param shyj要设置的
	 *            shyj
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
	 * @param gwid要设置的
	 *            gwid
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
	 * @param shid要设置的
	 *            shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}

	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}

	/**
	 * @param shjg要设置的
	 *            shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}

	/**
	 * @param thgw要设置的
	 *            thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}


	
	
}
