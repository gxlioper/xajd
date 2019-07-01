/**
 * @部门:学工产品事业部
 * @日期：2018年5月22日 上午10:09:42 
 */  
package xsgzgl.gyjc.jcsd;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 抽查项目Form
 * @作者： 刘禹昕[工号:1599]
 * @时间： 2018年5月22日 上午10:09:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxxmForm extends ActionForm {
	private String dm;
	private String mc;
	private String jbz;
	private String type;
	private Pages pages = new Pages();
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pages要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
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
	private String[] dms;
	/**
	 * @return the dm
	 */
	public String getDm() {
		return dm;
	}
	/**
	 * @param dm要设置的 dm
	 */
	public void setDm(String dm) {
		this.dm = dm;
	}
	/**
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}
	/**
	 * @param mc要设置的 mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}
	/**
	 * @return the jbz
	 */
	public String getJbz() {
		return jbz;
	}
	/**
	 * @param jbz要设置的 jbz
	 */
	public void setJbz(String jbz) {
		this.jbz = jbz;
	}
	/**
	 * @return the dms
	 */
	public String[] getDms() {
		return dms;
	}
	/**
	 * @param dms要设置的 dms
	 */
	public void setDms(String[] dms) {
		this.dms = dms;
	}

}
