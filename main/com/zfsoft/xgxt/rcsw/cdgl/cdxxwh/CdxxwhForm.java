/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����09:39:15 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.cdxxwh;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: ������Ϣά��actionForm
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-4-23 ����09:39:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdxxwhForm extends ActionForm{

	/** 
	 * @���� serialVersionUID : -6656773682624980098L 
	 */ 
	private static final long serialVersionUID = -6656773682624980098L;

	/** 
	 * @���� pages : ��ҳģ�� 
	 */
	private Pages pages = new Pages();
	
	/** 
	 * @���� searchModel : �߼���ѯģ�� 
	 */
	private SearchModel searchModel = new SearchModel();
	
	/** 
	 * @���� exportModel : ����ģ�� 
	 */
	private ExportModel exportModel = new ExportModel();
	
	/** 
	 * @���� cdid : ���ر�ʶ 
	 */ 
	private String cdid;
	
	/** 
	 * @���� cdmc : �������� 
	 */ 
	private String cdmc;
	
	/** 
	 * @���� ld : ¥��
	 */ 
	private String ld;
	
	/** 
	 * @���� fj : ����
	 */ 
	private String fj;
	
	/** 
	 * @���� rnrs : ��������
	 */ 
	private String rnrs;
	
	/** 
	 * @���� sfbz : �շѱ�׼
	 */ 
	private String sfbz;
	
	/** 
	 * @���� dwkfsjkssj : ���⿪�ſ�ʼʱ��
	 */ 
	private String dwkfsjkssj;
	
	/** 
	 * @���� dwkfsjjssj : ���⿪�Ž���ʱ��
	 */ 
	private String dwkfsjjssj;
	
	private String xfgfsyxy;
	
	/** 
	 * @���� yt : ��;
	 */ 
	private String yt;
	
	/** 
	 * @���� jbsbjs : �����豸���� 
	 */ 
	private String jbsbjs;
	
	/** 
	 * @���� sfkfsq : �Ƿ񿪷�����
	 * Ĭ������δ ��1�� ������״̬
	 */ 
	private String sfkfsq = "1";
	
	/** 
	 * @���� splcid : ��������id
	 */ 
	private String splcid;
	
	/** 
	 * @���� qxfw : Ȩ�޷�Χ
	 */
	private String qxfw;
	
	private String lxr;//��ϵ��
	
	private String lxfs;//��ϵ��ʽ
	
	//�������
	private FormFile formfile;
	private String filepath;
	private String xysfilepath; // Э���鸽��id
	
	
	/**
	 * @return the lxr
	 */
	public String getLxr() {
		return lxr;
	}

	/**
	 * @param lxrҪ���õ� lxr
	 */
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	/**
	 * @return the lxfs
	 */
	public String getLxfs() {
		return lxfs;
	}

	/**
	 * @param lxfsҪ���õ� lxfs
	 */
	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

	public String getQxfw() {
		return qxfw;
	}

	public void setQxfw(String qxfw) {
		this.qxfw = qxfw;
	}

	/********************��ѯ����*****************************/
	
	private String search_cdmc; //��ѯ����_��������
	
	private String search_rnrsmin; //��ѯ����_�������� min
	
	private String search_rnrsmax; //��ѯ����_�������� max
	
	private String search_yt; //��ѯ����_��;
	
	private String search_dwkfsjkssj; //��ѯ����_���⿪�ſ�ʼʱ��
	
	private String search_dwkfsjjssj; //��ѯ����_���⿪ʼ����ʱ��
	
	private String search_sfkfsq; //��ѯ���������Ƿ���⿪������
	/********************��ѯ����*****************************/
	
	/**
	 * @���� �����췽��
	 */
	public CdxxwhForm() {
		super();
	}

	/**
	 * @return the cdid
	 */
	public String getCdid() {
		return cdid;
	}

	/**
	 * @param cdidҪ���õ� cdid
	 */
	public void setCdid(String cdid) {
		this.cdid = cdid;
	}

	/**
	 * @return the cdmc
	 */
	public String getCdmc() {
		return cdmc;
	}

	/**
	 * @param cdmcҪ���õ� cdmc
	 */
	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}

	/**
	 * @return the ld
	 */
	public String getLd() {
		return ld;
	}

	/**
	 * @param ldҪ���õ� ld
	 */
	public void setLd(String ld) {
		this.ld = ld;
	}

	/**
	 * @return the fj
	 */
	public String getFj() {
		return fj;
	}

	/**
	 * @param fjҪ���õ� fj
	 */
	public void setFj(String fj) {
		this.fj = fj;
	}

	/**
	 * @return the rnrs
	 */
	public String getRnrs() {
		return rnrs;
	}

	/**
	 * @param rnrsҪ���õ� rnrs
	 */
	public void setRnrs(String rnrs) {
		this.rnrs = rnrs;
	}

	/**
	 * @return the sfbz
	 */
	public String getSfbz() {
		return sfbz;
	}

	/**
	 * @param sfbzҪ���õ� sfbz
	 */
	public void setSfbz(String sfbz) {
		this.sfbz = sfbz;
	}

	/**
	 * @return the dwkfsjkssj
	 */
	public String getDwkfsjkssj() {
		return dwkfsjkssj;
	}

	/**
	 * @param dwkfsjkssjҪ���õ� dwkfsjkssj
	 */
	public void setDwkfsjkssj(String dwkfsjkssj) {
		this.dwkfsjkssj = dwkfsjkssj;
	}

	/**
	 * @return the dwkfsjjssj
	 */
	public String getDwkfsjjssj() {
		return dwkfsjjssj;
	}

	/**
	 * @param dwkfsjjssjҪ���õ� dwkfsjjssj
	 */
	public void setDwkfsjjssj(String dwkfsjjssj) {
		this.dwkfsjjssj = dwkfsjjssj;
	}

	public String getXfgfsyxy() {
		return xfgfsyxy;
	}

	public void setXfgfsyxy(String xfgfsyxy) {
		this.xfgfsyxy = xfgfsyxy;
	}

	/**
	 * @return the yt
	 */
	public String getYt() {
		return yt;
	}

	/**
	 * @param ytҪ���õ� yt
	 */
	public void setYt(String yt) {
		this.yt = yt;
	}

	/**
	 * @return the jbsbjs
	 */
	public String getJbsbjs() {
		return jbsbjs;
	}

	/**
	 * @param jbsbjsҪ���õ� jbsbjs
	 */
	public void setJbsbjs(String jbsbjs) {
		this.jbsbjs = jbsbjs;
	}

	/**
	 * @return the sfkfsq
	 */
	public String getSfkfsq() {
		return sfkfsq;
	}

	/**
	 * @param sfkfsqҪ���õ� sfkfsq
	 */
	public void setSfkfsq(String sfkfsq) {
		this.sfkfsq = sfkfsq;
	}

	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}

	/**
	 * @param splcidҪ���õ� splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
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
	 * @return the search_cdmc
	 */
	public String getSearch_cdmc() {
		return search_cdmc;
	}

	/**
	 * @param searchCdmcҪ���õ� search_cdmc
	 */
	public void setSearch_cdmc(String searchCdmc) {
		search_cdmc = searchCdmc;
	}

	/**
	 * @return the search_rnrsmin
	 */
	public String getSearch_rnrsmin() {
		return search_rnrsmin;
	}

	/**
	 * @param searchRnrsminҪ���õ� search_rnrsmin
	 */
	public void setSearch_rnrsmin(String searchRnrsmin) {
		search_rnrsmin = searchRnrsmin;
	}

	/**
	 * @return the search_rnrsmax
	 */
	public String getSearch_rnrsmax() {
		return search_rnrsmax;
	}

	/**
	 * @param searchRnrsmaxҪ���õ� search_rnrsmax
	 */
	public void setSearch_rnrsmax(String searchRnrsmax) {
		search_rnrsmax = searchRnrsmax;
	}

	/**
	 * @return the search_yt
	 */
	public String getSearch_yt() {
		return search_yt;
	}

	/**
	 * @param searchYtҪ���õ� search_yt
	 */
	public void setSearch_yt(String searchYt) {
		search_yt = searchYt;
	}

	/**
	 * @return the search_dwkfsjkssj
	 */
	public String getSearch_dwkfsjkssj() {
		return search_dwkfsjkssj;
	}

	/**
	 * @param searchDwkfsjkssjҪ���õ� search_dwkfsjkssj
	 */
	public void setSearch_dwkfsjkssj(String searchDwkfsjkssj) {
		search_dwkfsjkssj = searchDwkfsjkssj;
	}

	/**
	 * @return the search_dwkfsjjssj
	 */
	public String getSearch_dwkfsjjssj() {
		return search_dwkfsjjssj;
	}

	/**
	 * @param searchDwkfsjjssjҪ���õ� search_dwkfsjjssj
	 */
	public void setSearch_dwkfsjjssj(String searchDwkfsjjssj) {
		search_dwkfsjjssj = searchDwkfsjjssj;
	}

	/**
	 * @return the search_sfkfsq
	 */
	public String getSearch_sfkfsq() {
		return search_sfkfsq;
	}

	/**
	 * @param searchSfkfsqҪ���õ� search_sfkfsq
	 */
	public void setSearch_sfkfsq(String searchSfkfsq) {
		search_sfkfsq = searchSfkfsq;
	}

	/**
	 * @return the formfile
	 */
	public FormFile getFormfile() {
		return formfile;
	}

	/**
	 * @param formfileҪ���õ� formfile
	 */
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}

	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepathҪ���õ� filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @return the xysfilepath
	 */
	public String getXysfilepath() {
		return xysfilepath;
	}

	/**
	 * @param xysfilepathҪ���õ� xysfilepath
	 */
	public void setXysfilepath(String xysfilepath) {
		this.xysfilepath = xysfilepath;
	}
	
}
