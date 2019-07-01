/**
 * @部门:学工产品事业部
 * @日期：2014-7-8 下午07:35:46 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.cssz;

import org.apache.struts.action.ActionForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-7-8 下午07:35:46
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BysXxCsSzForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String bynd;
	private String kgzt;

	private String shlid;

	public String getKgzt() {
		return kgzt;
	}

	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}

	public String getShlid() {
		return shlid;
	}

	public void setShlid(String shlid) {
		this.shlid = shlid;
	}

	/**
	 * @return the bynd
	 */
	public String getBynd() {
		return bynd;
	}

	/**
	 * @param bynd要设置的 bynd
	 */
	public void setBynd(String bynd) {
		this.bynd = bynd;
	}
	

}
