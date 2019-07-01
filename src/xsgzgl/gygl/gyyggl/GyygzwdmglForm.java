/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午03:45:21 
 */  
package xsgzgl.gygl.gyyggl;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xucy [工号：754]
 * @时间： 2013-7-30 下午03:45:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyygzwdmglForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String zwdm;//职位代码
	private String zwmc;//职位名称
	
	private String type;
	private Pages pages = new Pages();
	
	public String getZwdm() {
		return zwdm;
	}
	public void setZwdm(String zwdm) {
		this.zwdm = zwdm;
	}
	public String getZwmc() {
		return zwmc;
	}
	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
}
