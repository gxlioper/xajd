/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 上午10:26:28 
 */  
package com.zfsoft.xgxt.xsztz.xmsbgl.glygl;

import xgxt.comm.CommForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-9 上午10:26:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GlyglForm extends CommForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String yhm;//用户名
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
