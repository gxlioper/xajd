/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-21 ����02:37:27 
 */  
package com.zfsoft.xgxt.rcsw.sjgl.hqsj;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-21 ����02:37:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HqsjForm extends ActionForm{
	//����ģ��
	ExportModel exportModel = new ExportModel();
	//�߼�����
	SearchModel searchModel = new SearchModel();
	// ��ҳ
	Pages pages = new Pages();
	
	private String id;
	private String xh;
	private String xn;
	private String xq;
	private String qszlccs; //�������Ҳ����
	private String yxqscs; //�������Ҵ���
	private String yxqsjf; //�������Ҽӷ�
	private String qsz; //�Ƿ����ҳ�
	private String sywjdqcs; //ʹ��Υ����������
	private String ybgscs; //ҹ�����޴���
	private String type; //��������
	private String xqmc; //ѧ������
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
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pagesҪ���õ� pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xhҪ���õ� xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xnҪ���õ� xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xqҪ���õ� xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the qszlccs
	 */
	public String getQszlccs() {
		return qszlccs;
	}
	/**
	 * @param qszlccsҪ���õ� qszlccs
	 */
	public void setQszlccs(String qszlccs) {
		this.qszlccs = qszlccs;
	}
	/**
	 * @return the yxqscs
	 */
	public String getYxqscs() {
		return yxqscs;
	}
	/**
	 * @param yxqscsҪ���õ� yxqscs
	 */
	public void setYxqscs(String yxqscs) {
		this.yxqscs = yxqscs;
	}
	/**
	 * @return the yxqsjf
	 */
	public String getYxqsjf() {
		return yxqsjf;
	}
	/**
	 * @param yxqsjfҪ���õ� yxqsjf
	 */
	public void setYxqsjf(String yxqsjf) {
		this.yxqsjf = yxqsjf;
	}
	/**
	 * @return the qsz
	 */
	public String getQsz() {
		return qsz;
	}
	/**
	 * @param qszҪ���õ� qsz
	 */
	public void setQsz(String qsz) {
		this.qsz = qsz;
	}
	/**
	 * @return the sywjdqcs
	 */
	public String getSywjdqcs() {
		return sywjdqcs;
	}
	/**
	 * @param sywjdqcsҪ���õ� sywjdqcs
	 */
	public void setSywjdqcs(String sywjdqcs) {
		this.sywjdqcs = sywjdqcs;
	}
	/**
	 * @return the ybgscs
	 */
	public String getYbgscs() {
		return ybgscs;
	}
	/**
	 * @param ybgscsҪ���õ� ybgscs
	 */
	public void setYbgscs(String ybgscs) {
		this.ybgscs = ybgscs;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmcҪ���õ� xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	
	
	
}
