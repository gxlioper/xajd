package com.zfsoft.xgxt.dtjs.dxbmgl.pxgl;

import xsgzgl.comm.form.CommForm;

/** 
 * @功能描述：党校培训管理form
 * @author：杨珩 【1346】
 * @date：2017-11-1 下午02:48:41 
 */
public class DxpxglForm extends CommForm {
	private static final long serialVersionUID = 1L;
	private String id;//id
	private String pxqc;//培训期次
	private String pxsj;//培训时间
	private String bmkssj;//报名开始时间
	private String bmjssj;//报名结束时间
	private String pxnr;//培训内容
	private String kqcjbfb;//考勤成绩百分比
	private String sjcjbfb;//实践成绩百分比
	private String bjcjbfb;//笔记成绩百分比
	private String kscjbfb;//考试成绩百分比
	private String fbr;//发布人
	private String fbsj;//发布时间
	private String fbrxm;//发布人姓名
	private String lxdh;//联系电话
	private String type;
	private String sjyl;
	public String getSjyl() {
		return sjyl;
	}
	public void setSjyl(String sjyl) {
		this.sjyl = sjyl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getFbrxm() {
		return fbrxm;
	}
	public void setFbrxm(String fbrxm) {
		this.fbrxm = fbrxm;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPxqc() {
		return pxqc;
	}
	public void setPxqc(String pxqc) {
		this.pxqc = pxqc;
	}
	public String getPxsj() {
		return pxsj;
	}
	public void setPxsj(String pxsj) {
		this.pxsj = pxsj;
	}
	public String getBmkssj() {
		return bmkssj;
	}
	public void setBmkssj(String bmkssj) {
		this.bmkssj = bmkssj;
	}
	public String getBmjssj() {
		return bmjssj;
	}
	public void setBmjssj(String bmjssj) {
		this.bmjssj = bmjssj;
	}
	public String getPxnr() {
		return pxnr;
	}
	public void setPxnr(String pxnr) {
		this.pxnr = pxnr;
	}
	public String getKqcjbfb() {
		return kqcjbfb;
	}
	public void setKqcjbfb(String kqcjbfb) {
		this.kqcjbfb = kqcjbfb;
	}
	public String getSjcjbfb() {
		return sjcjbfb;
	}
	public void setSjcjbfb(String sjcjbfb) {
		this.sjcjbfb = sjcjbfb;
	}
	public String getBjcjbfb() {
		return bjcjbfb;
	}
	public void setBjcjbfb(String bjcjbfb) {
		this.bjcjbfb = bjcjbfb;
	}
	public String getKscjbfb() {
		return kscjbfb;
	}
	public void setKscjbfb(String kscjbfb) {
		this.kscjbfb = kscjbfb;
	}
	public String getFbr() {
		return fbr;
	}
	public void setFbr(String fbr) {
		this.fbr = fbr;
	}
	public String getFbsj() {
		return fbsj;
	}
	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}
}
