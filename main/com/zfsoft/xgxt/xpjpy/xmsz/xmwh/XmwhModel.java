/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����02:37:36 
 */
package com.zfsoft.xgxt.xpjpy.xmsz.xmwh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��Ŀά��
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-30 ����02:37:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XmwhModel extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	private String xmdm;// ��Ŀ����
	private String lxdm;// ���ʹ���
	private String xzdm;// ���ʴ���
	private String xmmc;// ��Ŀ����
	private String xmywmc;//��ĿӢ������
	private String sfqy;// �Ƿ�����
	private String xmje;// ��Ŀ���
	private String shlc;// �������
	private String rskzjb;// �������Ƽ���
	private String jdkzjb;// ��ÿ��Ƽ���
	private String rsfpfs;// �������䷽ʽ
	private String rsfpz;// ����������������
	private String rsfpnj;// ���������꼶
	private String zcfpm;// �۲��������ʽ
	private String xmsm;// ��Ŀ˵��
	private String sqkssj;// ���뿪ʼʱ��
	private String sqjssj;// �������ʱ��
	private String sqkg;// ���뿪��
	private String shkssj;// ��˿�ʼʱ��
	private String shjssj;// ��˽���ʱ��
	private String shkg;// ��˿���
	private String kgbz;// ���ر�ע
	private String xn;// ѧ��
	private String xq;// ѧ��
	private String xsxh;// ��ʾ˳��
	private String czfs; //������ʽ
	private String dysbbb;//��Ӧ�ϱ�����
	private String xxtbsj;//ѧУ���ʱ��
	private String sfkfhjqkxz;//�Ƿ���Ҫ����ѧ����Ҫ�����ѡ��
	private String sfyxgb;//�Ƿ�����ѧ���ɲ�
	private String xmxz;//��Ŀ����
	private String pycc;//�������


	public String getPycc() {
		return pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	public String getXmxz() {
		return xmxz;
	}

	public void setXmxz(String xmxz) {
		this.xmxz = xmxz;
	}

	/**
	 * ��С�򡾹���1036�� �����ǼǱ�����
	 */

	private String dybb;// ��Ӧ����

	public XmwhModel() {
		super();
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
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

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	public String getXmje() {
		return xmje;
	}

	public void setXmje(String xmje) {
		this.xmje = xmje;
	}

	public String getShlc() {
		return shlc;
	}

	public void setShlc(String shlc) {
		this.shlc = shlc;
	}

	public String getRskzjb() {
		return rskzjb;
	}

	public void setRskzjb(String rskzjb) {
		this.rskzjb = rskzjb;
	}

	public String getJdkzjb() {
		return jdkzjb;
	}

	public void setJdkzjb(String jdkzjb) {
		this.jdkzjb = jdkzjb;
	}

	public String getRsfpfs() {
		return rsfpfs;
	}

	public void setRsfpfs(String rsfpfs) {
		this.rsfpfs = rsfpfs;
	}

	public String getRsfpz() {
		return rsfpz;
	}

	public void setRsfpz(String rsfpz) {
		this.rsfpz = rsfpz;
	}

	public String getRsfpnj() {
		return rsfpnj;
	}

	public void setRsfpnj(String rsfpnj) {
		this.rsfpnj = rsfpnj;
	}

	public String getZcfpm() {
		return zcfpm;
	}

	public void setZcfpm(String zcfpm) {
		this.zcfpm = zcfpm;
	}

	public String getXmsm() {
		return xmsm;
	}

	public void setXmsm(String xmsm) {
		this.xmsm = xmsm;
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

	public String getSqkg() {
		return sqkg;
	}

	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
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

	public String getShkg() {
		return shkg;
	}

	public void setShkg(String shkg) {
		this.shkg = shkg;
	}

	public String getKgbz() {
		return kgbz;
	}

	public void setKgbz(String kgbz) {
		this.kgbz = kgbz;
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

	/**
	 * @return the dybb
	 */
	public String getDybb() {
		return dybb;
	}

	/**
	 * @param dybbҪ���õ�
	 *            dybb
	 */
	public void setDybb(String dybb) {
		this.dybb = dybb;
	}

	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}

	/**
	 * @return the xmywmc
	 */
	public String getXmywmc() {
		return xmywmc;
	}

	/**
	 * @param xmywmcҪ���õ� xmywmc
	 */
	public void setXmywmc(String xmywmc) {
		this.xmywmc = xmywmc;
	}

	/**
	 * @return the czfs
	 */
	public String getCzfs() {
		return czfs;
	}

	/**
	 * @param czfsҪ���õ� czfs
	 */
	public void setCzfs(String czfs) {
		this.czfs = czfs;
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

	/**
	 * @return the dysbbb
	 */
	public String getDysbbb() {
		return dysbbb;
	}

	/**
	 * @param dysbbbҪ���õ� dysbbb
	 */
	public void setDysbbb(String dysbbb) {
		this.dysbbb = dysbbb;
	}

	/**
	 * @return the xxtbsj
	 */
	public String getXxtbsj() {
		return xxtbsj;
	}

	/**
	 * @param xxtbsjҪ���õ� xxtbsj
	 */
	public void setXxtbsj(String xxtbsj) {
		this.xxtbsj = xxtbsj;
	}

	/**
	 * @return the sfkfhjqkxz
	 */
	public String getSfkfhjqkxz() {
		return sfkfhjqkxz;
	}

	/**
	 * @param sfkfhjqkxzҪ���õ� sfkfhjqkxz
	 */
	public void setSfkfhjqkxz(String sfkfhjqkxz) {
		this.sfkfhjqkxz = sfkfhjqkxz;
	}

	public String getSfyxgb() {
		return sfyxgb;
	}

	public void setSfyxgb(String sfyxgb) {
		this.sfyxgb = sfyxgb;
	}
	
}
