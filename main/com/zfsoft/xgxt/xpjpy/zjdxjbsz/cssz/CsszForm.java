/**
 * @����:ѧ����Ʒ1��
 * @���ڣ�2017-3-21 ����09:19:54 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����������_��������_��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-3-21 ����09:19:54 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();//��ҳ
	private String type;//����
	private String id;//ID
	private String pjkg;//��������
	private String kssj;//������ʼʱ��
	private String jssj;//��������ʱ��
	private String xn;//����ѧ��
	private String kgzt;//����״̬
	private String xmdm;//�۲���Ŀ����
	private String xmmc;//�۲���Ŀ��Ŀ����
	private String fjdm;//�۲���Ŀ��������
	private String xmlx;//�۲���Ŀ��Ŀ����
	private String px;//�۲���Ŀ����
	private String zxfz;//�۲���Ŀ��С��ֵ
	private String zdfz;//�۲���Ŀ����ֵ
	private String mc;//����
	private SearchModel searchModel = new SearchModel();
	
	
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
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}
	/**
	 * @param mcҪ���õ� mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdmҪ���õ� xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
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
	 * @return the fjdm
	 */
	public String getFjdm() {
		return fjdm;
	}
	/**
	 * @param fjdmҪ���õ� fjdm
	 */
	public void setFjdm(String fjdm) {
		this.fjdm = fjdm;
	}
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
	 * @return the px
	 */
	public String getPx() {
		return px;
	}
	/**
	 * @param pxҪ���õ� px
	 */
	public void setPx(String px) {
		this.px = px;
	}
	/**
	 * @return the zxfz
	 */
	public String getZxfz() {
		return zxfz;
	}
	/**
	 * @param zxfzҪ���õ� zxfz
	 */
	public void setZxfz(String zxfz) {
		this.zxfz = zxfz;
	}
	/**
	 * @return the zdfz
	 */
	public String getZdfz() {
		return zdfz;
	}
	/**
	 * @param zdfzҪ���õ� zdfz
	 */
	public void setZdfz(String zdfz) {
		this.zdfz = zdfz;
	}
	/**
	 * @return the kgzt
	 */
	public String getKgzt() {
		return kgzt;
	}
	/**
	 * @param kgztҪ���õ� kgzt
	 */
	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
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
	 * @return the pjkg
	 */
	public String getPjkg() {
		return pjkg;
	}
	/**
	 * @param pjkgҪ���õ� pjkg
	 */
	public void setPjkg(String pjkg) {
		this.pjkg = pjkg;
	}
	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}
	/**
	 * @param kssjҪ���õ� kssj
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}
	/**
	 * @param jssjҪ���õ� jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
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
}
