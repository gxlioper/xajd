/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-21 ����05:17:09 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.xsyybxxx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ���չ���-ѧ��ԤԼ������Ϣ 
 * @���ߣ� ������ [����:1123]
 * @ʱ�䣺 2015-1-21 ����05:17:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsyybxxxForm extends ActionForm {
	
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;
	
	private String yybxid;
	
	private String xh;
	
	private String yysj;
	
	private String blnr;
	
	private String xm;
	
	private String xb;
	
	private String nj;
	
	private String xymc;
	
	private String zymc;
	
	private String bjmc;

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYybxid() {
		return yybxid;
	}

	public void setYybxid(String yybxid) {
		this.yybxid = yybxid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getYysj() {
		return yysj;
	}

	public void setYysj(String yysj) {
		this.yysj = yysj;
	}

	public String getBlnr() {
		return blnr;
	}

	public void setBlnr(String blnr) {
		this.blnr = blnr;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	
}
