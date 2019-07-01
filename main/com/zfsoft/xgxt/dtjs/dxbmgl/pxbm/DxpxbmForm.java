package com.zfsoft.xgxt.dtjs.dxbmgl.pxbm;

import com.zfsoft.xgxt.dtjs.dxbmgl.pxgl.DxpxglForm;

/** 
 * @功能描述：
 * @author：杨珩 【1346】
 * @date：2017-11-1 下午03:05:45 
 */
public class DxpxbmForm extends DxpxglForm {
	private static final long serialVersionUID = 1L;
	private String sqid;//id
	private String pxid;//培训id
	private String xh;//学号
	private String splc;//审批流程
	private String shzt;//审核状态
	private String shztmc;//审核状态名称
	private String sqsj;//申请时间
	private String type;
	private String fbrxm;//发布人姓名
	private String lxdh;//联系电话
	private String pxqc;//培训期次
	private String pxsj;//培训时间
	private String bmkssj;//报名开始时间
	private String bmjssj;//报名结束时间
	private String sfbm;//是否已报名
	private String sfbmmc;//名称
	public String getSfbm() {
		return sfbm;
	}
	public void setSfbm(String sfbm) {
		this.sfbm = sfbm;
	}
	public String getSfbmmc() {
		return sfbmmc;
	}
	public void setSfbmmc(String sfbmmc) {
		this.sfbmmc = sfbmmc;
	}
	public String getShztmc() {
		return shztmc;
	}
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	public String getFbrxm() {
		return fbrxm;
	}
	public void setFbrxm(String fbrxm) {
		this.fbrxm = fbrxm;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
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
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
