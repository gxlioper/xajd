/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-13 ����09:05:03 
 */  
package com.zfsoft.xgxt.rwgl.rwtw;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������ģ��
 * @�๦������: TODO����ѧ�Ѳ�������
 * @���ߣ�HongLin 
 * @ʱ�䣺 2013-5-13 ����08:55:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwxfbcglForm extends ActionForm{

private static final long serialVersionUID = 1L;
	
	private String type;//����
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String guid ;//ID
	private String xh ;//ѧ��
	private String xn ;//ѧ��
	private String xfbcsj ;//ѧ�Ѳ���ʱ��
	private String xfbcje ;//ѧ�Ѳ������
	private String bz ;//��ע
	private String[] id;//id����
	private String[] xhs;//ѧ������
	private ExportModel exportModel = new ExportModel();
	
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
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
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
	public String getXfbcsj() {
		return xfbcsj;
	}
	public void setXfbcsj(String xfbcsj) {
		this.xfbcsj = xfbcsj;
	}
	public String getXfbcje() {
		return xfbcje;
	}
	public void setXfbcje(String xfbcje) {
		this.xfbcje = xfbcje;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
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
	
}
