package xsgzgl.rcsw.qjgl;

import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xsgzgl.comm.form.CommForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ճ�����_��ٹ���_Form��
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

public class RcswQjglForm extends CommForm {

	private static final long serialVersionUID = 1L;

	private String id = "1";// '��ĿID';

	private String lxmc;// '��������';

	private String lcid;// '����ID';

	private String mints;// '��С����';

	private String maxts;// '�������';

	private String xgr;// '�޸���';

	private String xgsj;// '�޸�ʱ��';

	private String kfxg;// '�ɷ��޸�';

	private String qjid;// '���ID';

	private String xn;// 'ѧ��';

	private String xq;// 'ѧ��';

	private String xh;// 'ѧ��';

	private String sqsj;// '����ʱ��';

	private String sqts;// '��������';

	private String kssj;// '��ʼʱ��';

	private String jssj;// '����ʱ��';

	private String lxdh;// '��ϵ�绰';

	private String jtdh;// '��ͥ�绰';

	private String jtdz;// '��ͥ��ַ';

	private String sqly;// '��������';

	private String bz;// '��ע';

	private String sqjg;// '������';

	private String kzzd1;// '��չ�ֶ�1';

	private String kzzd2;// '��չ�ֶ�2';

	private String kzzd3;// '��չ�ֶ�3';

	private String kzzd4;// '��չ�ֶ�4';

	private String kzzd5;// '��չ�ֶ�5';

	private String[] gwid;// '��λID';

	private String sqid;// '����ID';

	private String shr;// '�����';

	private String shzt;// '���״̬';

	private String shsj;// '���ʱ��';

	private String shyj;// '������';

	private String czxm;// '������Ŀ';

	private String shgw;// '��˸�λ';
	
	private String qjlx;// �������
	
	private FormFile qjclPath;
	
	private String qjcl;
	
	private ExportModel exportModel = new ExportModel();

	public String getQjlx() {
		return qjlx;
	}

	public void setQjlx(String qjlx) {
		this.qjlx = qjlx;
	}

	public String[] getGwid() {
		return gwid;
	}

	public void setGwid(String[] gwid) {
		this.gwid = gwid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLcid() {
		return lcid;
	}

	public void setLcid(String lcid) {
		this.lcid = lcid;
	}

	public String getMaxts() {
		return maxts;
	}

	public void setMaxts(String maxts) {
		this.maxts = maxts;
	}

	public String getMints() {
		return mints;
	}

	public void setMints(String mints) {
		this.mints = mints;
	}

	public String getXgr() {
		return xgr;
	}

	public void setXgr(String xgr) {
		this.xgr = xgr;
	}

	public String getXgsj() {
		return xgsj;
	}

	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}

	public String getLxmc() {
		return lxmc;
	}

	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}

	public String getKfxg() {
		return kfxg;
	}

	public void setKfxg(String kfxg) {
		this.kfxg = kfxg;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getJtdh() {
		return jtdh;
	}

	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}

	public String getJtdz() {
		return jtdz;
	}

	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getKzzd1() {
		return kzzd1;
	}

	public void setKzzd1(String kzzd1) {
		this.kzzd1 = kzzd1;
	}

	public String getKzzd2() {
		return kzzd2;
	}

	public void setKzzd2(String kzzd2) {
		this.kzzd2 = kzzd2;
	}

	public String getKzzd3() {
		return kzzd3;
	}

	public void setKzzd3(String kzzd3) {
		this.kzzd3 = kzzd3;
	}

	public String getKzzd4() {
		return kzzd4;
	}

	public void setKzzd4(String kzzd4) {
		this.kzzd4 = kzzd4;
	}

	public String getKzzd5() {
		return kzzd5;
	}

	public void setKzzd5(String kzzd5) {
		this.kzzd5 = kzzd5;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getQjid() {
		return qjid;
	}

	public void setQjid(String qjid) {
		this.qjid = qjid;
	}

	public String getSqjg() {
		return sqjg;
	}

	public void setSqjg(String sqjg) {
		this.sqjg = sqjg;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getSqts() {
		return sqts;
	}

	public void setSqts(String sqts) {
		this.sqts = sqts;
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

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public String getCzxm() {
		return czxm;
	}

	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}

	public String getShgw() {
		return shgw;
	}

	public void setShgw(String shgw) {
		this.shgw = shgw;
	}

	public String getQjcl() {
		return qjcl;
	}

	public void setQjcl(String qjcl) {
		this.qjcl = qjcl;
	}

	public FormFile getQjclPath() {
		return qjclPath;
	}

	public void setQjclPath(FormFile qjclPath) {
		this.qjclPath = qjclPath;
	}

	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}

	/**
	 * @param exportModelҪ���õ� exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

}
