/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-24 ����10:52:35 
 */  
package com.zfsoft.xgxt.wjcf.cflbdmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (����������ά��) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-24 ����10:52:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CflbdmwhForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	
	private String jsslqsr;            //���������ʼ��
	private String cflbdm;            //����������
	private String cflbmc;            //�����������
	private String sfszcfqx;  		  //�Ƿ����ô�������0��1��
	private String cfqx;			//��������
	private String qxnsfkzz; 	    //�������Ƿ����ֹ0��1��
	private String spl;            //������
	private String sfkss;            //�Ƿ������
	private String sfksqjc;            //�Ƿ��������
	private String ssslgzr;            //������������
	
	private String cffwqx;	//���ַ���Ȩ��

	private String cjsj;//����ʱ��

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
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
	 * @return the jsslqsr
	 */
	public String getJsslqsr() {
		return jsslqsr;
	}
	/**
	 * @param jsslqsrҪ���õ� jsslqsr
	 */
	public void setJsslqsr(String jsslqsr) {
		this.jsslqsr = jsslqsr;
	}
	/**
	 * @return the cflbdm
	 */
	public String getCflbdm() {
		return cflbdm;
	}
	/**
	 * @param cflbdmҪ���õ� cflbdm
	 */
	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}
	/**
	 * @return the cflbmc
	 */
	public String getCflbmc() {
		return cflbmc;
	}
	/**
	 * @param cflbmcҪ���õ� cflbmc
	 */
	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}
	/**
	 * @return the spl
	 */
	public String getSpl() {
		return spl;
	}
	/**
	 * @param splҪ���õ� spl
	 */
	public void setSpl(String spl) {
		this.spl = spl;
	}
	/**
	 * @return the sfkss
	 */
	public String getSfkss() {
		return sfkss;
	}
	/**
	 * @param sfkssҪ���õ� sfkss
	 */
	public void setSfkss(String sfkss) {
		this.sfkss = sfkss;
	}
	/**
	 * @return the sfksqjc
	 */
	public String getSfksqjc() {
		return sfksqjc;
	}
	/**
	 * @param sfksqjcҪ���õ� sfksqjc
	 */
	public void setSfksqjc(String sfksqjc) {
		this.sfksqjc = sfksqjc;
	}
	/**
	 * @return the ssslgzr
	 */
	public String getSsslgzr() {
		return ssslgzr;
	}
	/**
	 * @param ssslgzrҪ���õ� ssslgzr
	 */
	public void setSsslgzr(String ssslgzr) {
		this.ssslgzr = ssslgzr;
	}
	
	
	/**
	 * @return the cfqx
	 */
	public String getCfqx() {
		return cfqx;
	}
	/**
	 * @param cfqxҪ���õ� cfqx
	 */
	public void setCfqx(String cfqx) {
		this.cfqx = cfqx;
	}
	/**
	 * @return the sfszcfqx
	 */
	public String getSfszcfqx() {
		return sfszcfqx;
	}
	/**
	 * @param sfszcfqxҪ���õ� sfszcfqx
	 */
	public void setSfszcfqx(String sfszcfqx) {
		this.sfszcfqx = sfszcfqx;
	}
	/**
	 * @return the qxnsfkzz
	 */
	public String getQxnsfkzz() {
		return qxnsfkzz;
	}
	/**
	 * @param qxnsfkzzҪ���õ� qxnsfkzz
	 */
	public void setQxnsfkzz(String qxnsfkzz) {
		this.qxnsfkzz = qxnsfkzz;
	}
	/**
	 * @return the cffwqx
	 */
	public String getCffwqx() {
		return cffwqx;
	}
	/**
	 * @param cffwqxҪ���õ� cffwqx
	 */
	public void setCffwqx(String cffwqx) {
		this.cffwqx = cffwqx;
	}

	
	
}
