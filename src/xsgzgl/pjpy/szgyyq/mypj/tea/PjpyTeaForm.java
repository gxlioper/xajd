package xsgzgl.pjpy.szgyyq.mypj.tea;

import org.apache.struts.upload.FormFile;

import xsgzgl.pjpy.szgyyq.model.FivesModel;
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
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����(��ʦ)_Form��
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

public class PjpyTeaForm extends PjpyMypjForm {

	private static final long serialVersionUID = 1L;

	DshdModel dshdModel;// ����

	YybdModel yybdModel;// ���Ա��

	IvtltModel ivtltModel;// IVT��̳

	WthdModel wthdModel;// ����

	ZznlModel zznlModel;// ��֯����

	ShsjModel shsjModel;// ���ʵ��

	XsssModel xsssModel;// ѧ������

	XstsModel xstsModel;// ѧ��Ͷ��

	FivesModel FivesModel;// 5S

	private String xn;// ѧ��

	private String xq;// ѧ��

	private String xh;// ѧ��

	private String xmdm;// ��Ŀ����
	
	private String nj;//�꼶
	
	private String xydm;//ѧԺ
	
	private String zydm;//רҵ
	
	private String bjdm;//�༶

	private String[] id;// ID

	private String[] bzrshf;// '��������˷�';

	private String[] bzrsh;// '���������';

	private String[] bzrshsj;// '���������ʱ��';

	private String[] bzrshr;// '�����������';

	private String[] xyshf;// 'ѧԺ��˷�';

	private String[] xysh;// 'ѧԺ���';

	private String[] xyshsj;// 'ѧԺ���ʱ��';

	private String[] xyshr;// 'ѧԺ�����';

	private String[] xxshf;// 'ѧУ��˷�';

	private String[] xxsh;// 'ѧУ���';

	private String[] xxshsj;// 'ѧУ���ʱ��';

	private String[] xxshr;// 'ѧУ�����';

	private String[] shxh;// '���ѧ��';
	
	private String shzt = "tg";// '���״̬';
	
	private FormFile impFilePath;//�����ļ�

	public FormFile getImpFilePath() {
		return impFilePath;
	}

	public void setImpFilePath(FormFile impFilePath) {
		this.impFilePath = impFilePath;
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

	public FivesModel getFivesModel() {
		return FivesModel;
	}

	public void setFivesModel(FivesModel fivesModel) {
		FivesModel = fivesModel;
	}

	public String[] getBzrsh() {
		return bzrsh;
	}

	public void setBzrsh(String[] bzrsh) {
		this.bzrsh = bzrsh;
	}

	public String[] getBzrshf() {
		return bzrshf;
	}

	public void setBzrshf(String[] bzrshf) {
		this.bzrshf = bzrshf;
	}

	public String[] getBzrshr() {
		return bzrshr;
	}

	public void setBzrshr(String[] bzrshr) {
		this.bzrshr = bzrshr;
	}

	public String[] getBzrshsj() {
		return bzrshsj;
	}

	public void setBzrshsj(String[] bzrshsj) {
		this.bzrshsj = bzrshsj;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getXxsh() {
		return xxsh;
	}

	public void setXxsh(String[] xxsh) {
		this.xxsh = xxsh;
	}

	public String[] getXxshf() {
		return xxshf;
	}

	public void setXxshf(String[] xxshf) {
		this.xxshf = xxshf;
	}

	public String[] getXxshr() {
		return xxshr;
	}

	public void setXxshr(String[] xxshr) {
		this.xxshr = xxshr;
	}

	public String[] getXxshsj() {
		return xxshsj;
	}

	public void setXxshsj(String[] xxshsj) {
		this.xxshsj = xxshsj;
	}

	public String[] getXysh() {
		return xysh;
	}

	public void setXysh(String[] xysh) {
		this.xysh = xysh;
	}

	public String[] getXyshf() {
		return xyshf;
	}

	public void setXyshf(String[] xyshf) {
		this.xyshf = xyshf;
	}

	public String[] getXyshr() {
		return xyshr;
	}

	public void setXyshr(String[] xyshr) {
		this.xyshr = xyshr;
	}

	public String[] getXyshsj() {
		return xyshsj;
	}

	public void setXyshsj(String[] xyshsj) {
		this.xyshsj = xyshsj;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String[] getShxh() {
		return shxh;
	}

	public void setShxh(String[] shxh) {
		this.shxh = shxh;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
}
