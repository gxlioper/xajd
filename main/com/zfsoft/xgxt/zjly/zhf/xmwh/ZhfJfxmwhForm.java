/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-27 ����04:36:01 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ۺϷֹ���ģ��
 * @�๦������: �Ʒ���Ŀ(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-27 ����04:36:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfJfxmwhForm extends ActionForm{
	
	private static final long serialVersionUID = -2024530567050197284L;
	
	private String jfxmdm;
	private String jfxmmc;
	private String khyd;
	private String fs;
	private String sfxf;
	private String xdfs;
	private String sfbt;
	private String xmmkdm;
	private String cxzd;
	private String cxmc;
	private String jdszContent;
	
	private String type;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the jfxmdm
	 */
	public String getJfxmdm() {
		return jfxmdm;
	}
	/**
	 * @param jfxmdmҪ���õ� jfxmdm
	 */
	public void setJfxmdm(String jfxmdm) {
		this.jfxmdm = jfxmdm;
	}
	/**
	 * @return the jfxmmc
	 */
	public String getJfxmmc() {
		return jfxmmc;
	}
	/**
	 * @param jfxmmcҪ���õ� jfxmmc
	 */
	public void setJfxmmc(String jfxmmc) {
		this.jfxmmc = jfxmmc;
	}
	/**
	 * @return the khyd
	 */
	public String getKhyd() {
		return khyd;
	}
	/**
	 * @param khydҪ���õ� khyd
	 */
	public void setKhyd(String khyd) {
		this.khyd = khyd;
	}
	/**
	 * @return the fs
	 */
	public String getFs() {
		return fs;
	}
	/**
	 * @param fsҪ���õ� fs
	 */
	public void setFs(String fs) {
		this.fs = fs;
	}
	/**
	 * @return the sfxf
	 */
	public String getSfxf() {
		return sfxf;
	}
	/**
	 * @param sfxfҪ���õ� sfxf
	 */
	public void setSfxf(String sfxf) {
		this.sfxf = sfxf;
	}
	/**
	 * @return the xdfs
	 */
	public String getXdfs() {
		return xdfs;
	}
	/**
	 * @param xdfsҪ���õ� xdfs
	 */
	public void setXdfs(String xdfs) {
		this.xdfs = xdfs;
	}
	/**
	 * @return the sfbt
	 */
	public String getSfbt() {
		return sfbt;
	}
	/**
	 * @param sfbtҪ���õ� sfbt
	 */
	public void setSfbt(String sfbt) {
		this.sfbt = sfbt;
	}
	/**
	 * @return the xmmkdm
	 */
	public String getXmmkdm() {
		return xmmkdm;
	}
	/**
	 * @param xmmkdmҪ���õ� xmmkdm
	 */
	public void setXmmkdm(String xmmkdm) {
		this.xmmkdm = xmmkdm;
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
	 * @return the cxzd
	 */
	public String getCxzd() {
		return cxzd;
	}
	/**
	 * @param cxzdҪ���õ� cxzd
	 */
	public void setCxzd(String cxzd) {
		this.cxzd = cxzd;
	}
	/**
	 * @return the cxmc
	 */
	public String getCxmc() {
		return cxmc;
	}
	/**
	 * @param cxmcҪ���õ� cxmc
	 */
	public void setCxmc(String cxmc) {
		this.cxmc = cxmc;
	}
	/**
	 * @return the jdszContent
	 */
	public String getJdszContent() {
		return jdszContent;
	}
	/**
	 * @param jdszContentҪ���õ� jdszContent
	 */
	public void setJdszContent(String jdszContent) {
		this.jdszContent = jdszContent;
	}
	
	
}
