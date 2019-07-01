/**
 * @部门:学工产品事业部
 * @日期：2013-12-9 下午01:58:42 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxxg;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称:学生信息
 * @类功能描述:修改字段
 * @作者： ligl
 * @时间： 2013-12-9 下午01:58:42
 * @版本： V1.0
 * @修改记录:
 */

public class XgzdModel implements java.io.Serializable {

	private static final long serialVersionUID = -4055576059215657808L;
	private String sqid;
	private String zd;
	private String zdz;
	private String xgqz;

	/**
	 * @描述 ：
	 */
	public XgzdModel() {
		super();
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getZdz() {
		return zdz;
	}

	public void setZdz(String zdz) {
		this.zdz = zdz;
	}

	public String getXgqz() {
		return xgqz;
	}

	public void setXgqz(String xgqz) {
		this.xgqz = xgqz;
	}

}
