/**
 * @部门:学工产品事业部
 * @日期：2016-5-16 下午03:15:31 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生银行信息
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-16 下午03:15:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsyhxxModel implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String yhxxid;
	private String xh;
	private String yhdm;
	private String yhkh;
	
	public String getYhxxid() {
		return yhxxid;
	}
	public void setYhxxid(String yhxxid) {
		this.yhxxid = yhxxid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getYhdm() {
		return yhdm;
	}
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public String getYhkh() {
		return yhkh;
	}
	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}

}
