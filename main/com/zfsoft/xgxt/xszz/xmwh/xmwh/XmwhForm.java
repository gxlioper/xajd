/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.xmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������Ŀ����bean
 * @�๦������:
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����06:33:00
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmwhForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	private String xmdm;// ��Ŀ����
	private String xmmc;// ��Ŀ����
	private String lbdm;// ������
	private String je;// ���
	private String dybb;// ��Ӧ����
	private String dysbbb;// ��Ӧ�ϱ�����
	private String sqkg;// ���뿪��
	private String sqxn;// ����ѧ��
	private String sqxq;// ����ѧ��
	private String sqkssj;// ���뿪ʼʱ��
	private String sqjssj;// �������ʱ��
	private String splc;// ��������
	private String sfkns;// �Ƿ���������������
	private String knsbdxn;// ��������ѧ��
	private String knsbdxq;// ��������ѧ��
	private String knsbddc;// �������󶨵���
	private String tjkg;// �������ƿ���
	private String tjkzjb;// �������Ƽ���
	private String rskg;// �������ƿ���
	private String rskzjb;// �������Ƽ���
	private String jdkg;// ��ÿ��ƿ���
	private String jdkzjb;// ��ÿ��Ƽ���
	private String rskzfw;// �������Ʒ�Χ
	private String zme;// ������
	private String rskznj;// ���������꼶
	private String jesfxssq;// ����Ƿ�ѧ������
	private String sqzqdm;// �������ڴ���
	private String zqdm;// ���ڴ���
	private String zqmc;// ��������
	private String pdxn; // ����ѧ��
	private String pdxq; // ����ѧ��
	private String pdxqmc; // ����ѧ������
	private String ywmc;
	private String jekzfw;
	private String jgsqzq;	//�����������
	private String xslb;
	private String xmsm; //��Ŀ˵��
	private String pycc;//�������
	
	//������Ͽҽ��ר���Ի���˿��أ���˿�ʼʱ�䣬��˽���ʱ��
	private String shkg;
	private String shkssj;
	private String shjssj;

	public String getPycc() {
		return pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	public String getShkg() {
		return shkg;
	}

	public void setShkg(String shkg) {
		this.shkg = shkg;
	}

	public String getShkssj() {
		return shkssj;
	}

	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}

	public String getShjssj() {
		return shjssj;
	}

	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}

	public String getXmsm() {
		return xmsm;
	}

	public void setXmsm(String xmsm) {
		this.xmsm = xmsm;
	}

	public XmwhForm() {
		super();
	}

	public String getZme() {
		return zme;
	}

	public void setZme(String zme) {
		this.zme = zme;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
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

	public String getLbdm() {
		return lbdm;
	}

	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getDybb() {
		return dybb;
	}

	public void setDybb(String dybb) {
		this.dybb = dybb;
	}

	public String getDysbbb() {
		return dysbbb;
	}

	public void setDysbbb(String dysbbb) {
		this.dysbbb = dysbbb;
	}

	public String getSqkg() {
		return sqkg;
	}

	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}

	public String getSqxn() {
		return sqxn;
	}

	public void setSqxn(String sqxn) {
		this.sqxn = sqxn;
	}

	public String getSqxq() {
		return sqxq;
	}

	public void setSqxq(String sqxq) {
		this.sqxq = sqxq;
	}

	public String getSqkssj() {
		return sqkssj;
	}

	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}

	public String getSqjssj() {
		return sqjssj;
	}

	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}

	public String getSplc() {
		return splc;
	}

	public void setSplc(String splc) {
		this.splc = splc;
	}

	public String getSfkns() {
		return sfkns;
	}

	public void setSfkns(String sfkns) {
		this.sfkns = sfkns;
	}

	public String getKnsbdxn() {
		return knsbdxn;
	}

	public void setKnsbdxn(String knsbdxn) {
		this.knsbdxn = knsbdxn;
	}

	public String getKnsbdxq() {
		return knsbdxq;
	}

	public void setKnsbdxq(String knsbdxq) {
		this.knsbdxq = knsbdxq;
	}

	public String getKnsbddc() {
		return knsbddc;
	}

	public void setKnsbddc(String knsbddc) {
		this.knsbddc = knsbddc;
	}

	public String getTjkg() {
		return tjkg;
	}

	public void setTjkg(String tjkg) {
		this.tjkg = tjkg;
	}

	public String getTjkzjb() {
		return tjkzjb;
	}

	public void setTjkzjb(String tjkzjb) {
		this.tjkzjb = tjkzjb;
	}

	public String getRskg() {
		return rskg;
	}

	public void setRskg(String rskg) {
		this.rskg = rskg;
	}

	public String getRskzjb() {
		return rskzjb;
	}

	public void setRskzjb(String rskzjb) {
		this.rskzjb = rskzjb;
	}

	public String getJdkg() {
		return jdkg;
	}

	public void setJdkg(String jdkg) {
		this.jdkg = jdkg;
	}

	public String getJdkzjb() {
		return jdkzjb;
	}

	public void setJdkzjb(String jdkzjb) {
		this.jdkzjb = jdkzjb;
	}

	public String getRskzfw() {
		return rskzfw;
	}

	public void setRskzfw(String rskzfw) {
		this.rskzfw = rskzfw;
	}

	public String getRskznj() {
		return rskznj;
	}

	public void setRskznj(String rskznj) {
		this.rskznj = rskznj;
	}

	public void setJesfxssq(String jesfxssq) {
		this.jesfxssq = jesfxssq;
	}

	public String getJesfxssq() {
		return jesfxssq;
	}

	public String getSqzqdm() {
		return sqzqdm;
	}

	public void setSqzqdm(String sqzqdm) {
		this.sqzqdm = sqzqdm;
	}

	public String getZqdm() {
		return zqdm;
	}

	public void setZqdm(String zqdm) {
		this.zqdm = zqdm;
	}

	public String getZqmc() {
		return zqmc;
	}

	public void setZqmc(String zqmc) {
		this.zqmc = zqmc;
	}

	public String getPdxn() {
		return pdxn;
	}

	public void setPdxn(String pdxn) {
		this.pdxn = pdxn;
	}

	public String getPdxq() {
		return pdxq;
	}

	public void setPdxq(String pdxq) {
		this.pdxq = pdxq;
	}

	public String getPdxqmc() {
		return pdxqmc;
	}

	public void setPdxqmc(String pdxqmc) {
		this.pdxqmc = pdxqmc;
	}

	public String getYwmc() {
		return ywmc;
	}

	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}

	public String getJekzfw() {
		return jekzfw;
	}

	public void setJekzfw(String jekzfw) {
		this.jekzfw = jekzfw;
	}

	/**
	 * @return the jgsqzq
	 */
	public String getJgsqzq() {
		return jgsqzq;
	}

	/**
	 * @param jgsqzqҪ���õ� jgsqzq
	 */
	public void setJgsqzq(String jgsqzq) {
		this.jgsqzq = jgsqzq;
	}

	/**
	 * @return the xslb
	 */
	public String getXslb() {
		return xslb;
	}

	/**
	 * @param xslbҪ���õ� xslb
	 */
	public void setXslb(String xslb) {
		this.xslb = xslb;
	}
	

}
