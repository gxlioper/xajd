/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����02:04:57 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.zjsysxhb;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ChenQ[����:856]
 * @ʱ�䣺 2015-6-25 ����02:04:57
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZjsySxhbForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String xh;
	private String jddm;
	private String jdmc;
	private String sjfs;
	private String sjsj;
	private String type;
	private String sxhbid;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	
	public String getSxhbid() {
		return sxhbid;
	}

	public void setSxhbid(String sxhbid) {
		this.sxhbid = sxhbid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getJddm() {
		return jddm;
	}

	public void setJddm(String jddm) {
		this.jddm = jddm;
	}

	public String getJdmc() {
		return jdmc;
	}

	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}

	public String getSjfs() {
		return sjfs;
	}

	public void setSjfs(String sjfs) {
		this.sjfs = sjfs;
	}

	public String getSjsj() {
		return sjsj;
	}

	public void setSjsj(String sjsj) {
		this.sjsj = sjsj;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

}
