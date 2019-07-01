/**
 * @部门:学工产品事业部
 * @日期：2014-5-22 下午05:26:04 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.jcsz;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 基本设置 
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-22 下午05:26:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcszForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 6948305626296913637L;
	
	public static final String XXLX_BJ = "0";
	
	public static final String XXLX_GY = "1";
	
	public static final String XXLX_PS = "2";
	
	
	//班级周报日程审批流程ID
	private String bjzbrcSplcid;
	
	//公寓周报日程审批流程ID
	private String gyzbrcSplcid;
	
	//平时信息上报审批流程ID
	private String psxxsbSplcid;

	public String selectSplc(String xxlx){
		if(StringUtils.isBlank(xxlx))
			return null;
		
		if(StringUtils.equals(XXLX_BJ, xxlx)){
			return bjzbrcSplcid;
		}else if(StringUtils.equals(XXLX_GY, xxlx)){
			return gyzbrcSplcid;
		}else if(StringUtils.equals(XXLX_PS, xxlx)){
			return psxxsbSplcid;
		}
		return null;
	}
	
	/**
	 * @return the bjzbrcSplcid
	 */
	public String getBjzbrcSplcid() {
		return bjzbrcSplcid;
	}

	/**
	 * @param bjzbrcSplcid要设置的 bjzbrcSplcid
	 */
	public void setBjzbrcSplcid(String bjzbrcSplcid) {
		this.bjzbrcSplcid = bjzbrcSplcid;
	}

	/**
	 * @return the gyzbrcSplcid
	 */
	public String getGyzbrcSplcid() {
		return gyzbrcSplcid;
	}

	/**
	 * @param gyzbrcSplcid要设置的 gyzbrcSplcid
	 */
	public void setGyzbrcSplcid(String gyzbrcSplcid) {
		this.gyzbrcSplcid = gyzbrcSplcid;
	}

	/**
	 * @return the psxxsbSplcid
	 */
	public String getPsxxsbSplcid() {
		return psxxsbSplcid;
	}

	/**
	 * @param psxxsbSplcid要设置的 psxxsbSplcid
	 */
	public void setPsxxsbSplcid(String psxxsbSplcid) {
		this.psxxsbSplcid = psxxsbSplcid;
	}
	
	

}
