/**
 * @部门:学工产品事业部
 * @日期：2013-12-18 下午04:16:08 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.jcsz;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-18 下午04:16:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcszForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String kg; //开关
	
	private String spl; //审批流ID
	
	private String xjxn;
	
	private String type;

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

	/**
	 * @return the kg
	 */
	public String getKg() {
		return kg;
	}

	/**
	 * @param kg要设置的 kg
	 */
	public void setKg(String kg) {
		this.kg = kg;
	}

	/**
	 * @return the spl
	 */
	public String getSpl() {
		return spl;
	}

	/**
	 * @param spl要设置的 spl
	 */
	public void setSpl(String spl) {
		this.spl = spl;
	}

	/**
	 * @return the xjxn
	 */
	public String getXjxn() {
		return xjxn;
	}

	/**
	 * @param xjxn要设置的 xjxn
	 */
	public void setXjxn(String xjxn) {
		this.xjxn = xjxn;
	}


	
	
	
	
}
