/**
 * @部门:学工产品事业部
 * @日期：2013-12-30 下午03:24:41 
 */
package com.zfsoft.xgxt.rcsw.jqlx;

import org.apache.struts.action.ActionForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 假期留校设置
 * @作者：945
 * @时间： 2013-12-30 下午03:24:41
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JqlxszModel extends ActionForm {

	private static final long serialVersionUID = 3047822587143156629L;
	private String sqkg;
	private String splc;
	private String sqkssj;
	private String sqjssj;
	private String jqlx; // 假期类型
	private String isopen;// 当前时间是否开启
	private String fjid;// 附件id
	private String lxkssj;
	private String lxjssj;
	private String uploadid;// 非数据库字段，查找附件表
	
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

	public String getJqlx() {
		return jqlx;
	}

	public void setJqlx(String jqlx) {
		this.jqlx = jqlx;
	}

	/**
	 * @return the isopen
	 */
	public String getIsopen() {
		return isopen;
	}

	/**
	 * @param isopen要设置的
	 *            isopen
	 */
	public void setIsopen(String isopen) {
		this.isopen = isopen;
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

	public String getFjid() {
		return fjid;
	}

	public void setFjid(String fjid) {
		this.fjid = fjid;
	}

	public String getLxkssj() {
		return lxkssj;
	}

	public void setLxkssj(String lxkssj) {
		this.lxkssj = lxkssj;
	}

	public String getLxjssj() {
		return lxjssj;
	}

	public void setLxjssj(String lxjssj) {
		this.lxjssj = lxjssj;
	}

	public String getUploadid() {
		return uploadid;
	}

	public void setUploadid(String uploadid) {
		this.uploadid = uploadid;
	}

}
