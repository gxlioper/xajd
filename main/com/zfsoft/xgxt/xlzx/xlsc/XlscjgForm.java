/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-21 ����11:52:05 
 */  
package com.zfsoft.xgxt.xlzx.xlsc;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ����ɸ�鷽����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2016-12-21 ����11:51:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlscjgForm extends ActionForm {
	private static final long serialVersionUID = 1966791940187986544L;
	private ExportModel exportModel = new ExportModel();//����
	private Pages pages = new Pages();//��ҳ
	private String type;//����
	private SearchModel searchModel = new SearchModel();//��ѯ
	
	private String id;//id
	private String xh;//ѧ��
	private String scrq;//ɸ�����ڣ������գ�
	private String scl;//scl-90���
	private String sds;//sds���
	private String sas;//sas���
	private String bkyy;//��������֢���
	private String bkjl;//���˽���֢���
	private String sfxyyt;//�Ƿ���Ҫ�μ�Լ̸
	private String sfyyt;//�Ƿ��Ѳμ�Լ̸
	
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
	 * @return the scrq
	 */
	public String getScrq() {
		return scrq;
	}
	/**
	 * @param scrqҪ���õ� scrq
	 */
	public void setScrq(String scrq) {
		this.scrq = scrq;
	}
	/**
	 * @return the scl
	 */
	public String getScl() {
		return scl;
	}
	/**
	 * @param sclҪ���õ� scl
	 */
	public void setScl(String scl) {
		this.scl = scl;
	}
	/**
	 * @return the sds
	 */
	public String getSds() {
		return sds;
	}
	/**
	 * @param sdsҪ���õ� sds
	 */
	public void setSds(String sds) {
		this.sds = sds;
	}
	/**
	 * @return the sas
	 */
	public String getSas() {
		return sas;
	}
	/**
	 * @param sasҪ���õ� sas
	 */
	public void setSas(String sas) {
		this.sas = sas;
	}
	/**
	 * @return the bkyy
	 */
	public String getBkyy() {
		return bkyy;
	}
	/**
	 * @param bkyyҪ���õ� bkyy
	 */
	public void setBkyy(String bkyy) {
		this.bkyy = bkyy;
	}
	/**
	 * @return the bkjl
	 */
	public String getBkjl() {
		return bkjl;
	}
	/**
	 * @param bkjlҪ���õ� bkjl
	 */
	public void setBkjl(String bkjl) {
		this.bkjl = bkjl;
	}
	/**
	 * @return the sfxyyt
	 */
	public String getSfxyyt() {
		return sfxyyt;
	}
	/**
	 * @param sfxyytҪ���õ� sfxyyt
	 */
	public void setSfxyyt(String sfxyyt) {
		this.sfxyyt = sfxyyt;
	}
	/**
	 * @return the sfyyt
	 */
	public String getSfyyt() {
		return sfyyt;
	}
	/**
	 * @param sfyytҪ���õ� sfyyt
	 */
	public void setSfyyt(String sfyyt) {
		this.sfyyt = sfyyt;
	}
}
