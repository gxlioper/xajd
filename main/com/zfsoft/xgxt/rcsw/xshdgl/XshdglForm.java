/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-8 ����02:37:19 
 */  
package com.zfsoft.xgxt.rcsw.xshdgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-6-8 ����02:37:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XshdglForm extends ActionForm {
	 private String sqid;
	 private String hdmc;
	 private String hdsj;
	 private String hddd;
	 private String zbdwdm;
	 private String xbdwdm;
	 private String cbdwdm;
	 private String hdfzr;
	 private String cyry;
	 private String hdjj;
	 private String filepath;
	 private String type;
	 private String hdlx;
	 private SearchModel searchModel = new SearchModel();
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
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
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqidҪ���õ� sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @return the hdmc
	 */
	public String getHdmc() {
		return hdmc;
	}
	/**
	 * @param hdmcҪ���õ� hdmc
	 */
	public void setHdmc(String hdmc) {
		this.hdmc = hdmc;
	}
	/**
	 * @return the hdsj
	 */
	public String getHdsj() {
		return hdsj;
	}
	/**
	 * @param hdsjҪ���õ� hdsj
	 */
	public void setHdsj(String hdsj) {
		this.hdsj = hdsj;
	}
	/**
	 * @return the hddd
	 */
	public String getHddd() {
		return hddd;
	}
	/**
	 * @param hdddҪ���õ� hddd
	 */
	public void setHddd(String hddd) {
		this.hddd = hddd;
	}
	/**
	 * @return the zbdwdm
	 */
	public String getZbdwdm() {
		return zbdwdm;
	}
	/**
	 * @param zbdwdmҪ���õ� zbdwdm
	 */
	public void setZbdwdm(String zbdwdm) {
		this.zbdwdm = zbdwdm;
	}
	/**
	 * @return the xbdwdm
	 */
	public String getXbdwdm() {
		return xbdwdm;
	}
	/**
	 * @param xbdwdmҪ���õ� xbdwdm
	 */
	public void setXbdwdm(String xbdwdm) {
		this.xbdwdm = xbdwdm;
	}
	/**
	 * @return the cbdwdm
	 */
	public String getCbdwdm() {
		return cbdwdm;
	}
	/**
	 * @param cbdwdmҪ���õ� cbdwdm
	 */
	public void setCbdwdm(String cbdwdm) {
		this.cbdwdm = cbdwdm;
	}
	/**
	 * @return the hdfzr
	 */
	public String getHdfzr() {
		return hdfzr;
	}
	/**
	 * @param hdfzrҪ���õ� hdfzr
	 */
	public void setHdfzr(String hdfzr) {
		this.hdfzr = hdfzr;
	}
	/**
	 * @return the cyry
	 */
	public String getCyry() {
		return cyry;
	}
	/**
	 * @param cyryҪ���õ� cyry
	 */
	public void setCyry(String cyry) {
		this.cyry = cyry;
	}
	/**
	 * @return the hdjj
	 */
	public String getHdjj() {
		return hdjj;
	}
	/**
	 * @param hdjjҪ���õ� hdjj
	 */
	public void setHdjj(String hdjj) {
		this.hdjj = hdjj;
	}
	/**
	 * @return the filePath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filePathҪ���õ� filePath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
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
	 * @return the hdlx
	 */
	public String getHdlx() {
		return hdlx;
	}
	/**
	 * @param hdlxҪ���õ� hdlx
	 */
	public void setHdlx(String hdlx) {
		this.hdlx = hdlx;
	}
}
