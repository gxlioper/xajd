/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-28 ����09:13:53 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.ysjxj;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����-Ժ�轱ѧ��-���ά��
 * @�๦������: TODO(���ά��) 
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2016-7-28 ����09:13:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YsjxjForm extends ActionForm{
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String juid;
	private String type;//����
	private String xh;//ѧ��
	private String ffsj;//����ʱ��
	private String xmmc;//��Ŀ����
	private String zjly;//�ʽ���Դ
	private String je;//���
	private String bz;//��ע
	private String xn;//ѧ��
	private String xq;//ѧ��
	private String sjly;
	private String xmlx;//��Ŀ����
	
	
	/**
	 * @return the xmlx
	 */
	public String getXmlx() {
		return xmlx;
	}
	/**
	 * @param xmlxҪ���õ� xmlx
	 */
	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
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
	 * @return the juid
	 */
	public String getJuid() {
		return juid;
	}
	/**
	 * @param juidҪ���õ� juid
	 */
	public void setJuid(String juid) {
		this.juid = juid;
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
	 * @return the ffsj
	 */
	public String getFfsj() {
		return ffsj;
	}
	/**
	 * @param ffsjҪ���õ� ffsj
	 */
	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
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
	 * @return the zjly
	 */
	public String getZjly() {
		return zjly;
	}
	/**
	 * @param zjlyҪ���õ� zjly
	 */
	public void setZjly(String zjly) {
		this.zjly = zjly;
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
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
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
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjlyҪ���õ� sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
}
