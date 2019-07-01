package xsgzgl.pjpy.szgyyq.mypj.stu;

import xsgzgl.pjpy.szgyyq.model.ShsjModel;
import xsgzgl.pjpy.szgyyq.model.DshdModel;
import xsgzgl.pjpy.szgyyq.model.IvtltModel;
import xsgzgl.pjpy.szgyyq.model.WthdModel;
import xsgzgl.pjpy.szgyyq.model.XsssModel;
import xsgzgl.pjpy.szgyyq.model.XstsModel;
import xsgzgl.pjpy.szgyyq.model.YybdModel;
import xsgzgl.pjpy.szgyyq.model.ZznlModel;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(学生)_Form类
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

public class PjpyStuForm extends PjpyMypjForm {

	private static final long serialVersionUID = 1L;

	DshdModel dshdModel;// 读书活动

	YybdModel yybdModel;// 语言表达

	IvtltModel ivtltModel;// IVT论坛

	WthdModel wthdModel;// 文体活动

	ZznlModel zznlModel;// 组织能力

	ShsjModel shsjModel;// 社会实践

	XsssModel xsssModel;// 学生申诉

	XstsModel xstsModel;// 学生投诉

	private String xn;// 学年

	private String xq;// 学期

	private String xh;// 学号

	private String xmdm;// 项目代码

	private String sqid;// 申请ID

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public ShsjModel getShsjModel() {
		return shsjModel;
	}

	public void setShsjModel(ShsjModel shsjModel) {
		this.shsjModel = shsjModel;
	}

	public WthdModel getWthdModel() {
		return wthdModel;
	}

	public void setWthdModel(WthdModel wthdModel) {
		this.wthdModel = wthdModel;
	}

	public ZznlModel getZznlModel() {
		return zznlModel;
	}

	public void setZznlModel(ZznlModel zznlModel) {
		this.zznlModel = zznlModel;
	}

	public IvtltModel getIvtltModel() {
		return ivtltModel;
	}

	public void setIvtltModel(IvtltModel ivtltModel) {
		this.ivtltModel = ivtltModel;
	}

	public YybdModel getYybdModel() {
		return yybdModel;
	}

	public void setYybdModel(YybdModel yybdModel) {
		this.yybdModel = yybdModel;
	}

	public DshdModel getDshdModel() {
		return dshdModel;
	}

	public void setDshdModel(DshdModel dshdModel) {
		this.dshdModel = dshdModel;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
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

	public XsssModel getXsssModel() {
		return xsssModel;
	}

	public void setXsssModel(XsssModel xsssModel) {
		this.xsssModel = xsssModel;
	}

	public XstsModel getXstsModel() {
		return xstsModel;
	}

	public void setXstsModel(XstsModel xstsModel) {
		this.xstsModel = xstsModel;
	}
}
