/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����01:33:14 
 */  
package com.zfsoft.xgxt.xpjpy.cpmd;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������2013��-����ѧ������ 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-20 ����01:33:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CpmdModel extends ActionForm {

	
	private static final long serialVersionUID = 1L;

	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private String xh ;		//ѧ�� 
	private String xn ;		//ѧ�� 
	private String xq ;		//ѧ�� 
	private String nj;		// �꼶
	private String xydm;	// ѧԺ����
	private String xymc;	// ѧԺ����
	private String zydm;	// רҵ����
	private String zymc;	// רҵ����
	private String bjdm;	// �༶����
	private String bjmc;	// �༶����
	private String tzhbjdm;	// ������༶����
	

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public SearchModel getSearchModel() {
		return searchModel;
	}


	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}


	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}


	public Pages getPages() {
		return pages;
	}


	public void setPages(Pages pages) {
		this.pages = pages;
	}


	public String getXh() {
		return xh;
	}


	public void setXh(String xh) {
		this.xh = xh;
	}


	public String getXn() {
		return xn;
	}


	public void setXn(String xn) {
		this.xn = xn;
	}


	public String getXq() {
		return xq;
	}


	public void setXq(String xq) {
		this.xq = xq;
	}


	public String getNj() {
		return nj;
	}


	public void setNj(String nj) {
		this.nj = nj;
	}


	public String getXydm() {
		return xydm;
	}


	public void setXydm(String xydm) {
		this.xydm = xydm;
	}


	public String getXymc() {
		return xymc;
	}


	public void setXymc(String xymc) {
		this.xymc = xymc;
	}


	public String getZydm() {
		return zydm;
	}


	public void setZydm(String zydm) {
		this.zydm = zydm;
	}


	public String getZymc() {
		return zymc;
	}


	public void setZymc(String zymc) {
		this.zymc = zymc;
	}


	public String getBjdm() {
		return bjdm;
	}


	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}


	public String getBjmc() {
		return bjmc;
	}


	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}


	public String getTzhbjdm() {
		return tzhbjdm;
	}


	public void setTzhbjdm(String tzhbjdm) {
		this.tzhbjdm = tzhbjdm;
	}


	
}
