/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-2 ����06:13:18 
 */  
package com.zfsoft.xgxt.axcs.wpsz;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ĳ��й���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2014-12-2 ����06:13:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WpszForm extends ActionForm{
	private FormFile xmtp;
	private String xmdm;//��Ŀ����
	private String xmlb;//��Ŀ���
	private String xmlbmc;
	private String xmmc;//��Ŀ����
	private String xn;
	private String xmxxjs;
	private String sqkg;
	private String sqkssj;
	private String sqjssj;
	private String shkg;
	private String shkssj;
	private String shjssj;
	private String splc;
	private String jbsz;
	private String tjsz;
	private User user;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
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
	 * @return the xmlb
	 */
	public String getXmlb() {
		return xmlb;
	}
	/**
	 * @param xmlbҪ���õ� xmlb
	 */
	public void setXmlb(String xmlb) {
		this.xmlb = xmlb;
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
	 * @return the xmxxjs
	 */
	public String getXmxxjs() {
		return xmxxjs;
	}
	/**
	 * @param xmxxjsҪ���õ� xmxxjs
	 */
	public void setXmxxjs(String xmxxjs) {
		this.xmxxjs = xmxxjs;
	}
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkgҪ���õ� sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssjҪ���õ� sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssjҪ���õ� sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	/**
	 * @return the shkg
	 */
	public String getShkg() {
		return shkg;
	}
	/**
	 * @param shkgҪ���õ� shkg
	 */
	public void setShkg(String shkg) {
		this.shkg = shkg;
	}
	/**
	 * @return the shkssj
	 */
	public String getShkssj() {
		return shkssj;
	}
	/**
	 * @param shkssjҪ���õ� shkssj
	 */
	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}
	/**
	 * @return the shjssj
	 */
	public String getShjssj() {
		return shjssj;
	}
	/**
	 * @param shjssjҪ���õ� shjssj
	 */
	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
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
	 * @return the xmtp
	 */
	public FormFile getXmtp() {
		return xmtp;
	}
	/**
	 * @param xmtpҪ���õ� xmtp
	 */
	public void setXmtp(FormFile xmtp) {
		this.xmtp = xmtp;
	}
	/**
	 * @return the jbsz
	 */
	public String getJbsz() {
		return jbsz;
	}
	/**
	 * @param jbszҪ���õ� jbsz
	 */
	public void setJbsz(String jbsz) {
		this.jbsz = jbsz;
	}
	/**
	 * @return the tjsz
	 */
	public String getTjsz() {
		return tjsz;
	}
	/**
	 * @param tjszҪ���õ� tjsz
	 */
	public void setTjsz(String tjsz) {
		this.tjsz = tjsz;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param userҪ���õ� user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the xmlbmc
	 */
	public String getXmlbmc() {
		return xmlbmc;
	}
	/**
	 * @param xmlbmcҪ���õ� xmlbmc
	 */
	public void setXmlbmc(String xmlbmc) {
		this.xmlbmc = xmlbmc;
	}
	

}
