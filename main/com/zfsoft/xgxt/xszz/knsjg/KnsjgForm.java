/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-20 ����01:15:19 
 */
package com.zfsoft.xgxt.xszz.knsjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������ѯ����ģ��
 * @�๦������: form
 * @���ߣ� maxh
 * @ʱ�䣺 2013-4-20 ����01:15:19
 * @�汾�� V1.0
 */

public class KnsjgForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String xh;// ѧ��
	private String xm;// ����
	private String xn;// ѧ��
	private String xq;// ѧ��
	private String xqmc;// ѧ������
	private String sqsj;// ����ʱ��
	private String rddc;// �϶�����
	private String sqly;// ��������
	private String sjly;// ������Դ
	private String ylzd1;// Ԥ���ֶ�1
	private String ylzd2;// Ԥ���ֶ�2
	private String ylzd3;// Ԥ���ֶ�3
	private String ylzd4;// Ԥ���ֶ�4
	private String ylzd5;// Ԥ���ֶ�5
	private String guid;// ID
	private String dcmc;// �϶�����
	private String lylcywid;// ����ԭ��ҵ��id
	
	private String czr;//
	private String czsj;//
	private String qxyy;//
	
	private String sqlydm;//��������(���������ѧ)
	private String sfqxrd;
	
	private String knpx;//��������  �������ո��Ի��ֶ�
	private String jtrjnsr;// ��ͥ�˾�������
	private String sqlyyy;

	public String getSqlyyy() {
		return sqlyyy;
	}

	public void setSqlyyy(String sqlyyy) {
		this.sqlyyy = sqlyyy;
	}

	public String getKnpx() {
		return knpx;
	}

	public void setKnpx(String knpx) {
		this.knpx = knpx;
	}

	/**
	 * @return the czr
	 */
	public String getCzr() {
		return czr;
	}

	/**
	 * @param czrҪ���õ� czr
	 */
	public void setCzr(String czr) {
		this.czr = czr;
	}

	/**
	 * @return the czsj
	 */
	public String getCzsj() {
		return czsj;
	}

	/**
	 * @param czsjҪ���õ� czsj
	 */
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}

	/**
	 * @return the qxyy
	 */
	public String getQxyy() {
		return qxyy;
	}

	/**
	 * @param qxyyҪ���õ� qxyy
	 */
	public void setQxyy(String qxyy) {
		this.qxyy = qxyy;
	}

	public String getDcmc() {
		return dcmc;
	}

	public void setDcmc(String dcmc) {
		this.dcmc = dcmc;
	}

	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
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

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getRddc() {
		return rddc;
	}

	public void setRddc(String rddc) {
		this.rddc = rddc;
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
		
	public String getJtrjnsr() {
		return jtrjnsr;
	}

	public void setJtrjnsr(String jtrjnsr) {
		this.jtrjnsr = jtrjnsr;
	}

	public String getYlzd5() {
		return ylzd5;
	}

	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
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

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getLylcywid() {
		return lylcywid;
	}

	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
	}

	public String getSqlydm() {
		return sqlydm;
	}

	public void setSqlydm(String sqlydm) {
		this.sqlydm = sqlydm;
	}

	public String getSfqxrd() {
		return sfqxrd;
	}

	public void setSfqxrd(String sfqxrd) {
		this.sfqxrd = sfqxrd;
	}	
	
}
