package xsgzgl.pjpy.szgyyq.model;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_学生投诉_Model类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XstsModel {

	private String xn;// '学年';

	private String xq;// '学期';

	private String xh;// '学号';

	private String xmlx;// '项目类型';

	private String btsr;// '被投诉人';

	private String tsnr;// '投诉内容';

	private String tssj;// '投诉时间';

	private String clr;// '处理人';

	private String clyj;// '处理意见';

	private String clsj;// '处理时间';

	public String getBtsr() {
		return btsr;
	}

	public void setBtsr(String btsr) {
		this.btsr = btsr;
	}

	public String getClr() {
		return clr;
	}

	public void setClr(String clr) {
		this.clr = clr;
	}

	public String getClsj() {
		return clsj;
	}

	public void setClsj(String clsj) {
		this.clsj = clsj;
	}

	public String getClyj() {
		return clyj;
	}

	public void setClyj(String clyj) {
		this.clyj = clyj;
	}

	public String getTsnr() {
		return tsnr;
	}

	public void setTsnr(String tsnr) {
		this.tsnr = tsnr;
	}

	public String getTssj() {
		return tssj;
	}

	public void setTssj(String tssj) {
		this.tssj = tssj;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}
}
