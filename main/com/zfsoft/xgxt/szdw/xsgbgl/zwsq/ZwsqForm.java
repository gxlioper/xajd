/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-24 ����4:17:36 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwsq;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhForm;


/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ�ְ������
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-8 ����2:30:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZwsqForm extends ZwwhForm {

	/** 
	 * @���� serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String sqid ;//����id
	private String zwid ;//ְ��id
	private String xh ;//ѧ��
	private String sqr ;//������
	private String shzt ;//���״̬
	private String splc ;//��������
	private String sqsj ;//����ʱ��
	private String sqly ;//��������
	
	private String xymc ;//ѧԺ����
	private String zymc ;//רҵ
	private String bjmc ;//�༶
	private String xm ;//����
	private ExportModel exportModel = new ExportModel();

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	public String getZwid() {
		return zwid;
	}
	public void setZwid(String zwid) {
		this.zwid = zwid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSqr() {
		return sqr;
	}
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	
	
	


}
