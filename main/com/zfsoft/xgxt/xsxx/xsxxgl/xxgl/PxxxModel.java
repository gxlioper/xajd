/**
 * @部门:学工产品事业部
 * @日期：2013-12-19 下午05:21:26 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 培训信息
 * @作者： ligl
 * @时间： 2014-2-18 下午02:16:06
 * @版本： V1.0
 * @修改记录:
 */
public class PxxxModel implements java.io.Serializable {
	private static final long serialVersionUID = -7957660390800045492L;
	private String pxid;// 主键id
	private String xh;// 学号
	private String pxkssj;// 培训开始时间
	private String pxjssj;// 培训结束时间
	private String pxdd;// 培训地点
	private String pxnr;// 培训内容

	/**
	 * @描述 ：
	 */
	public PxxxModel() {
		super();
	}

	public String getPxid() {
		return pxid;
	}

	public void setPxid(String pxid) {
		this.pxid = pxid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getPxkssj() {
		return pxkssj;
	}

	public void setPxkssj(String pxkssj) {
		this.pxkssj = pxkssj;
	}

	public String getPxjssj() {
		return pxjssj;
	}

	public void setPxjssj(String pxjssj) {
		this.pxjssj = pxjssj;
	}

	public String getPxdd() {
		return pxdd;
	}

	public void setPxdd(String pxdd) {
		this.pxdd = pxdd;
	}

	public String getPxnr() {
		return pxnr;
	}

	public void setPxnr(String pxnr) {
		this.pxnr = pxnr;
	}

}
