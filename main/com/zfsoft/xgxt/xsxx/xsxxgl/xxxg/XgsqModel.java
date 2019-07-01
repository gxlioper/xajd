/**
 * @部门:学工产品事业部
 * @日期：2013-12-6 上午10:26:27 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxxg;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 学生信息修改申请
 * @作者： ligl
 * @时间： 2013-12-6 上午10:26:27
 * @版本： V1.0
 * @修改记录:
 */

public class XgsqModel implements java.io.Serializable {
	private static final long serialVersionUID = -7538118568402400940L;
	private String sqid;// 申请ID
	private String xh;// 学号
	private String xgsj;// 修改时间
	private String shjg;// 审核结果(未审核,审核中,通过,退回)
	private String xgzd;// 修改字段

	/**
	 * @描述 ：
	 */
	public XgsqModel() {
		super();
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXgsj() {
		return xgsj;
	}

	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getXgzd() {
		return xgzd;
	}

	public void setXgzd(String xgzd) {
		this.xgzd = xgzd;
	}

}
