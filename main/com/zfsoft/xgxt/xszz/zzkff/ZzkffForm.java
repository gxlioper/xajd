/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016��12��27�� ����11:16:59 
 */  
package com.zfsoft.xgxt.xszz.zzkff;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������-������Ź���ģ��
 * @�๦������: ѧ������ActionForm
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2016��12��27�� ����11:16:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZzkffForm extends ActionForm{
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String id;	//����
	private String xh;	//ѧ��
	private String xn;	//ѧ��
	private String xq;	//ѧ��
	private String xqmc;//ѧ������
	private String je;	//���
	private String xmmc;	//��Ŀ����
	private String wsyhzt;	//��������״̬
	private String yhfkxx;	//���з�����Ϣ
	
	private String xm;	//����
	private String nj;	//�꼶
	private String xy;	//ѧԺ
	private String zy;	//רҵ
	private String bj;	//�༶
	
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
	 * @return the je
	 */
	public String getJe() {
		return je;
	}
	/**
	 * @param jeҪ���õ� je
	 */
	public void setJe(String je) {
		this.je = je;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmcҪ���õ� xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the wsyhzt
	 */
	public String getWsyhzt() {
		return wsyhzt;
	}
	/**
	 * @param wsyhztҪ���õ� wsyhzt
	 */
	public void setWsyhzt(String wsyhzt) {
		this.wsyhzt = wsyhzt;
	}
	/**
	 * @return the yhfkxx
	 */
	public String getYhfkxx() {
		return yhfkxx;
	}
	/**
	 * @param yhfkxxҪ���õ� yhfkxx
	 */
	public void setYhfkxx(String yhfkxx) {
		this.yhfkxx = yhfkxx;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param njҪ���õ� nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @return the xy
	 */
	public String getXy() {
		return xy;
	}
	/**
	 * @param xyҪ���õ� xy
	 */
	public void setXy(String xy) {
		this.xy = xy;
	}
	/**
	 * @return the zy
	 */
	public String getZy() {
		return zy;
	}
	/**
	 * @param zyҪ���õ� zy
	 */
	public void setZy(String zy) {
		this.zy = zy;
	}
	/**
	 * @return the bj
	 */
	public String getBj() {
		return bj;
	}
	/**
	 * @param bjҪ���õ� bj
	 */
	public void setBj(String bj) {
		this.bj = bj;
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
