/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-20 ����11:38:01 
 */  
package com.zfsoft.xgxt.khgl.khbgl.khnrgl;


import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-20 ����11:38:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KhnrglForm extends ActionForm{
	private String zbmxid; 
	private String khbid; 
	private String yjzb; // һ��ָ��
	private String ejzb;//����ָ��
	private String khnr;
	private String fzlx;
	private String fzzgf;//��ֵ��߷�
	private String fzzdf;//��ֵ��ͷ�
	private String xssx;
	private String pflx;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	public String getZbmxid() {
		return zbmxid;
	}
	public void setZbmxid(String zbmxid) {
		this.zbmxid = zbmxid;
	}
	public String getKhbid() {
		return khbid;
	}
	public void setKhbid(String khbid) {
		this.khbid = khbid;
	}
	public String getYjzb() {
		return yjzb;
	}
	public void setYjzb(String yjzb) {
		this.yjzb = yjzb;
	}
	public String getEjzb() {
		return ejzb;
	}
	public void setEjzb(String ejzb) {
		this.ejzb = ejzb;
	}
	public String getKhnr() {
		return khnr;
	}
	public void setKhnr(String khnr) {
		this.khnr = khnr;
	}
	public String getFzlx() {
		return fzlx;
	}
	public void setFzlx(String fzlx) {
		this.fzlx = fzlx;
	}
	public String getFzzgf() {
		return fzzgf;
	}
	public void setFzzgf(String fzzgf) {
		this.fzzgf = fzzgf;
	}
	public String getFzzdf() {
		return fzzdf;
	}
	public void setFzzdf(String fzzdf) {
		this.fzzdf = fzzdf;
	}
	public String getXssx() {
		return xssx;
	}
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	public String getPflx() {
		return pflx;
	}
	public void setPflx(String pflx) {
		this.pflx = pflx;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
