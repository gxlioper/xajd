/**
 * @部门:学工产品事业部
 * @日期：2016-05-07 下午04:28:03 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.cssz;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生行为考核参数设置model
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-8-2 下午02:33:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XsxwCsszForm extends ActionForm {
	private static final long serialVersionUID = -1604650553048161540L;
	private String id;
	private String kgzt;
	private String kssj;
	private String jssj;
	private String xn;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getKgzt() {
		return kgzt;
	}
	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}
	
	
}
