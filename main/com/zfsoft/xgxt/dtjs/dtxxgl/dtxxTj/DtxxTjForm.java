/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-29 ����04:06:38 
 */  
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxTj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���
 * @�๦������: ͳ�Ʋ�ѯ����
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-3-29 ����04:06:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DtxxTjForm extends ActionForm {
	
	Pages pages = new Pages();
	
	private static final long serialVersionUID = 1L;
	
	private String xydm;  // ѧԺ
	private String xh;    // ѧ��
	private String xm;    // ����
	private String nd;    // ���
	
	// �߼���ѯ
	SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	
	
	
	
}
