/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����10:32:49 
 */
package com.zfsoft.xgxt.bzjl.wdbzjl.bzjljg;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �ҵ�����-�������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-30 ����10:32:49
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BzjljgModel extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String id;// ID
	private String xh;// ѧ��
	private String xn;// ѧ��
	private String xq;// ѧ��
	private String xqmc; // ѧ������
	private String xmdm;// ��Ŀ����
	private String xmmc;// ��Ŀ����
	private String xmje;// ��Ŀ���
	private String sqsj;// ����ʱ��
	private String sqly;// ��������
	private String ylzd1;// Ԥ���ֶ�һ
	private String ylzd2;// Ԥ���ֶζ�
	private String ylzd3;// Ԥ���ֶ���
	private String ylzd4;// Ԥ���ֶ���
	private String ylzd5;// ����id
	private String sjly;// ������Դ
	private String lylcywid;// ��Դҵ��id

	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private String lxdm;// ��Ŀ����
	private String xzdm;// ��Ŀ����

	private String cpnj; // �����꼶
	private String cpxydm; // ����ѧԺ����
	private String cpxymc; // ����ѧԺ����
	private String cpzydm; // ����רҵ����
	private String cpzymc; // ����רҵ����
	private String cpbjdm; // �����༶����
	private String cpbjmc; // �����༶����

	private String lxdmmc;// ��Ŀ��������
	
	private String bjdw;// �佱��λ
	private String djjl;//���ܺ��ֽ���

	private String sqr;	//������

	private String xydm;	//ѧԺ����

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getDjjl() {
		return djjl;
	}

	public void setDjjl(String djjl) {
		this.djjl = djjl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXmje() {
		return xmje;
	}

	public void setXmje(String xmje) {
		this.xmje = xmje;
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

	public String getYlzd1() {
		return ylzd1;
	}

	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}

	public String getYlzd2() {
		return ylzd2;
	}

	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}

	public String getYlzd3() {
		return ylzd3;
	}

	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}

	public String getYlzd4() {
		return ylzd4;
	}

	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}

	public String getYlzd5() {
		return ylzd5;
	}

	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getLxdm() {
		return lxdm;
	}

	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}

	public String getXzdm() {
		return xzdm;
	}

	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}

	public String getCpnj() {
		return cpnj;
	}

	public void setCpnj(String cpnj) {
		this.cpnj = cpnj;
	}

	public String getCpxydm() {
		return cpxydm;
	}

	public void setCpxydm(String cpxydm) {
		this.cpxydm = cpxydm;
	}

	public String getCpxymc() {
		return cpxymc;
	}

	public void setCpxymc(String cpxymc) {
		this.cpxymc = cpxymc;
	}

	public String getCpzydm() {
		return cpzydm;
	}

	public void setCpzydm(String cpzydm) {
		this.cpzydm = cpzydm;
	}

	public String getCpzymc() {
		return cpzymc;
	}

	public void setCpzymc(String cpzymc) {
		this.cpzymc = cpzymc;
	}

	public String getCpbjdm() {
		return cpbjdm;
	}

	public void setCpbjdm(String cpbjdm) {
		this.cpbjdm = cpbjdm;
	}

	public String getCpbjmc() {
		return cpbjmc;
	}

	public void setCpbjmc(String cpbjmc) {
		this.cpbjmc = cpbjmc;
	}

	public String getLxdmmc() {
		return lxdmmc;
	}

	public void setLxdmmc(String lxdmmc) {
		this.lxdmmc = lxdmmc;
	}

	public String getBjdw() {
		return bjdw;
	}

	public void setBjdw(String bjdw) {
		this.bjdw = bjdw;
	}

	public String getLylcywid() {
		return lylcywid;
	}

	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
	}

	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
}
