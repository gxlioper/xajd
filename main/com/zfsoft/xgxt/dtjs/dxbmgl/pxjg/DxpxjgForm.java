package com.zfsoft.xgxt.dtjs.dxbmgl.pxjg;

import com.zfsoft.xgxt.dtjs.dxbmgl.pxgl.DxpxglForm;

/** 
 * @功能描述：党校培训结果form
 * @author：杨珩 【1346】
 * @date：2017-11-1 下午03:47:08 
 */
public class DxpxjgForm extends DxpxglForm {
	private static final long serialVersionUID = 1L;
	private String jgid;//jgid
	private String pxid;//培训id
	private String xh;//学号
	private String sjly;//数据来源
	private String sqid;//申请id
	private String sqsj;//申请时间
	private String kqcj;//考勤成绩
	private String sjcj;//实践成绩
	private String bjcj;//笔记成绩
	private String kscj;//考试成绩
	private String zpcj;//总评成绩
	private String type;
	private String pjjg;//评价结果
	public String getPjjg() {
		return pjjg;
	}
	public void setPjjg(String pjjg) {
		this.pjjg = pjjg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJgid() {
		return jgid;
	}
	public void setJgid(String jgid) {
		this.jgid = jgid;
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
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getKqcj() {
		return kqcj;
	}
	public void setKqcj(String kqcj) {
		this.kqcj = kqcj;
	}
	public String getSjcj() {
		return sjcj;
	}
	public void setSjcj(String sjcj) {
		this.sjcj = sjcj;
	}
	public String getBjcj() {
		return bjcj;
	}
	public void setBjcj(String bjcj) {
		this.bjcj = bjcj;
	}
	public String getKscj() {
		return kscj;
	}
	public void setKscj(String kscj) {
		this.kscj = kscj;
	}
	public String getZpcj() {
		return zpcj;
	}
	public void setZpcj(String zpcj) {
		this.zpcj = zpcj;
	}
}
