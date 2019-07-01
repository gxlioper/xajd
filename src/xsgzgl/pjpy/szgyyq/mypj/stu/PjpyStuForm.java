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
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����(ѧ��)_Form��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyStuForm extends PjpyMypjForm {

	private static final long serialVersionUID = 1L;

	DshdModel dshdModel;// ����

	YybdModel yybdModel;// ���Ա��

	IvtltModel ivtltModel;// IVT��̳

	WthdModel wthdModel;// ����

	ZznlModel zznlModel;// ��֯����

	ShsjModel shsjModel;// ���ʵ��

	XsssModel xsssModel;// ѧ������

	XstsModel xstsModel;// ѧ��Ͷ��

	private String xn;// ѧ��

	private String xq;// ѧ��

	private String xh;// ѧ��

	private String xmdm;// ��Ŀ����

	private String sqid;// ����ID

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
