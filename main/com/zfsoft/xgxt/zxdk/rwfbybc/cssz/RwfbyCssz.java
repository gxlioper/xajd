/**
 * @部门:学工产品事业部
 * @日期：2015-9-7 下午04:02:32 
 */
package com.zfsoft.xgxt.zxdk.rwfbybc.cssz;

import org.apache.struts.action.ActionForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 入伍服兵役-参数设置
 * @作者： ChenQ[工号:856]
 * @时间： 2015-9-7 下午04:02:32
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class RwfbyCssz extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String id;
	private String sqkg;
	private String sqkssj;
	private String sqjssj;
	private String splc;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id要设置的
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}

	/**
	 * @param sqkg要设置的
	 *            sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}

	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}

	/**
	 * @param sqkssj要设置的
	 *            sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}

	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}

	/**
	 * @param sqjssj要设置的
	 *            sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}

	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}

	/**
	 * @param splc要设置的
	 *            splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}

}
