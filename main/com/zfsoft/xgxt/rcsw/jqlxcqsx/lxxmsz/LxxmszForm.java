/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-24 ����11:21:31 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxxmsz;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��У��Ŀ���� xg_cqsx_jqlx_xmsz
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-11-24 ����11:21:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxxmszForm extends ActionForm {
	  private String xmid;
	  private String xmmc;
	  private String lxkssj;
	  private String lxjssj;
	  private static final long serialVersionUID = 1L;
	  private String lxxmsm;
	  private String type;
	  private SearchModel searchModel = new SearchModel();
	  private ExportModel exportModel = new ExportModel();
	  private Pages pages = new Pages();
	  /**
	 * @return the xmid
	 */
	public String getXmid() {
		return xmid;
	}
	/**
	 * @param xmidҪ���õ� xmid
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
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
	 * @return the lxkssj
	 */
	public String getLxkssj() {
		return lxkssj;
	}
	/**
	 * @param lxkssjҪ���õ� lxkssj
	 */
	public void setLxkssj(String lxkssj) {
		this.lxkssj = lxkssj;
	}
	/**
	 * @return the lxjssj
	 */
	public String getLxjssj() {
		return lxjssj;
	}
	/**
	 * @param lxjssjҪ���õ� lxjssj
	 */
	public void setLxjssj(String lxjssj) {
		this.lxjssj = lxjssj;
	}
	/**
	 * @return the lxxmsm
	 */
	public String getLxxmsm() {
		return lxxmsm;
	}
	/**
	 * @param lxxmsmҪ���õ� lxxmsm
	 */
	public void setLxxmsm(String lxxmsm) {
		this.lxxmsm = lxxmsm;
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
	
}
