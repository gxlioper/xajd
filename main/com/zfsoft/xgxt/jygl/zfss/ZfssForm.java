/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-27 ����02:00:28 
 */
package com.zfsoft.xgxt.jygl.zfss;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ��������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� huj
 * @ʱ�䣺 2013-5-27 ����02:00:28
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZfssForm extends ActionForm {

	private static final long serialVersionUID = -20000008L;

	private String type;
	SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();

	private String djid;
	private String zgh;// ְ���ţ�����Աְ���ţ�
	private String bfwyq;// ������԰��
	private String bfwld;// ������¥��
	private String bfwcs;// �����ʲ���
	private String bfwss;// ����������
	private String jrsj;// ����ʱ��
	private String lksj;// �뿪ʱ��
	private String zbr;// ֵ����
	private String fwly;// ��������

	// ��ѯ��
	private String jrsjks;// ����ʱ�俪ʼ
	private String jrsjjz;// ����ʱ���ֹ
	private String zgxm;// ְ������
	private String xb;// ְ���Ա�
	private String bmmc;// ְ������
	

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

	public String getDjid() {
		return djid;
	}

	public void setDjid(String djid) {
		this.djid = djid;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getBfwyq() {
		return bfwyq;
	}

	public void setBfwyq(String bfwyq) {
		this.bfwyq = bfwyq;
	}

	public String getBfwld() {
		return bfwld;
	}

	public void setBfwld(String bfwld) {
		this.bfwld = bfwld;
	}

	public String getBfwcs() {
		return bfwcs;
	}

	public void setBfwcs(String bfwcs) {
		this.bfwcs = bfwcs;
	}

	public String getBfwss() {
		return bfwss;
	}

	public void setBfwss(String bfwss) {
		this.bfwss = bfwss;
	}

	public String getJrsj() {
		return jrsj;
	}

	public void setJrsj(String jrsj) {
		this.jrsj = jrsj;
	}

	public String getLksj() {
		return lksj;
	}

	public void setLksj(String lksj) {
		this.lksj = lksj;
	}

	public String getZbr() {
		return zbr;
	}

	public void setZbr(String zbr) {
		this.zbr = zbr;
	}

	public String getFwly() {
		return fwly;
	}

	public void setFwly(String fwly) {
		this.fwly = fwly;
	}

	public String getJrsjks() {
		return jrsjks;
	}

	public void setJrsjks(String jrsjks) {
		this.jrsjks = jrsjks;
	}

	public String getJrsjjz() {
		return jrsjjz;
	}

	public void setJrsjjz(String jrsjjz) {
		this.jrsjjz = jrsjjz;
	}

	public String getZgxm() {
		return zgxm;
	}

	public void setZgxm(String zgxm) {
		this.zgxm = zgxm;
	}

	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}

	/**
	 * @param xbҪ���õ� xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}

	/**
	 * @return the bmmc
	 */
	public String getBmmc() {
		return bmmc;
	}

	/**
	 * @param bmmcҪ���õ� bmmc
	 */
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}

	/**
	 * @param searchModelҪ���õ� searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
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
