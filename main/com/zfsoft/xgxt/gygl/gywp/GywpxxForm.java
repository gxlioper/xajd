/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-6 ����03:12:07 
 */  
package com.zfsoft.xgxt.gygl.gywp;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(��Ԣ��Ʒ����) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-8-6 ����03:12:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GywpxxForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String id;            //
	private String ids;
	private String wpmc;            //��Ʒ����
	private String wpdldm;            //��Ʒ�������
	private String wplbdm;            //��Ʒ������
	private String sl;            //����
	private String sfzhgq;            //�Ƿ��ںϸ���
	private String sfwh;            //�Ƿ����
	private String lddm;            //¥������
	private String qsh;            //���Һ�
	private String hhyy;            //�ٻ�ԭ��
	private String bz;              //��ע
	private String wpdlmc;            //��Ʒ��������
	private String wplbmc;            //��Ʒ�������
	private String ldmc;            //¥������
	
	
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
	 * @return the wpmc
	 */
	public String getWpmc() {
		return wpmc;
	}
	/**
	 * @param wpmcҪ���õ� wpmc
	 */
	public void setWpmc(String wpmc) {
		this.wpmc = wpmc;
	}
	/**
	 * @return the wpdldm
	 */
	public String getWpdldm() {
		return wpdldm;
	}
	/**
	 * @param wpdldmҪ���õ� wpdldm
	 */
	public void setWpdldm(String wpdldm) {
		this.wpdldm = wpdldm;
	}
	/**
	 * @return the wplbdm
	 */
	public String getWplbdm() {
		return wplbdm;
	}
	/**
	 * @param wplbdmҪ���õ� wplbdm
	 */
	public void setWplbdm(String wplbdm) {
		this.wplbdm = wplbdm;
	}
	/**
	 * @return the sl
	 */
	public String getSl() {
		return sl;
	}
	/**
	 * @param slҪ���õ� sl
	 */
	public void setSl(String sl) {
		this.sl = sl;
	}
	/**
	 * @return the sfzhgq
	 */
	public String getSfzhgq() {
		return sfzhgq;
	}
	/**
	 * @param sfzhgqҪ���õ� sfzhgq
	 */
	public void setSfzhgq(String sfzhgq) {
		this.sfzhgq = sfzhgq;
	}
	/**
	 * @return the sfwh
	 */
	public String getSfwh() {
		return sfwh;
	}
	/**
	 * @param sfwhҪ���õ� sfwh
	 */
	public void setSfwh(String sfwh) {
		this.sfwh = sfwh;
	}
	/**
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddmҪ���õ� lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @param qshҪ���õ� qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	/**
	 * @return the hhyy
	 */
	public String getHhyy() {
		return hhyy;
	}
	/**
	 * @param hhyyҪ���õ� hhyy
	 */
	public void setHhyy(String hhyy) {
		this.hhyy = hhyy;
	}
	/**
	 * @return the wpdlmc
	 */
	public String getWpdlmc() {
		return wpdlmc;
	}
	/**
	 * @param wpdlmcҪ���õ� wpdlmc
	 */
	public void setWpdlmc(String wpdlmc) {
		this.wpdlmc = wpdlmc;
	}
	/**
	 * @return the wplbmc
	 */
	public String getWplbmc() {
		return wplbmc;
	}
	/**
	 * @param wplbmcҪ���õ� wplbmc
	 */
	public void setWplbmc(String wplbmc) {
		this.wplbmc = wplbmc;
	}
	/**
	 * @return the ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmcҪ���õ� ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getBz() {
		return bz;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getIds() {
		return ids;
	}
	
	
	

}
