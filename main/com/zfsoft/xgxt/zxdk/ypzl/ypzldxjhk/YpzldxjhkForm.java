/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-3 ����10:47:45 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.ypzldxjhk;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ƽ������ѧ�𻹿���Ϣά��
 * @�๦������: ��ƽ������ѧ�𻹿���Ϣά�� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-3 ����10:47:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YpzldxjhkForm extends ActionForm{	
	private String jgid;
	private String xh;
	private String xn;
	private String xq;
	private String hksj;
	private String sjly;
	private String hkje;
	private String bz;
	private String hkzt;
	private String czsj;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	public String getJgid() {
		return jgid;
	}
	public void setJgid(String jgid) {
		this.jgid = jgid;
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
	public String getHksj() {
		return hksj;
	}
	public void setHksj(String hksj) {
		this.hksj = hksj;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	public String getHkje() {
		return hkje;
	}
	public void setHkje(String hkje) {
		this.hkje = hkje;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getHkzt() {
		return hkzt;
	}
	public void setHkzt(String hkzt) {
		this.hkzt = hkzt;
	}
	public String getCzsj() {
		return czsj;
	}
	public void setCzsj(String czsj) {
		this.czsj = czsj;
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
