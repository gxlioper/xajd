/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-6 ����04:23:39 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.xskqgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ڹ���ģ��
 * @�๦������: ѧ�����ڹ��� 
 * @���ߣ� �ո־�[����:1075]
 * @ʱ�䣺 2014-6-6 ����04:23:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqglForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String kqdjid;  //���ڵǼ�id
	private String xh;  	//ѧ��
	private String xn;  	//ѧ��
	private String xq;  	//ѧ��
	private String zc;  	//�ܴ�
	private String kqkc;	//���ڿγ�
	private String dd; 		//�ص�
	private String kqlxdm; 	//�������ʹ���
	private String kqsj; 	//����ʱ��
	private String kqqk; 	//�������
	private String zjsj; 	//����ʱ��
	
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
	public String getKqdjid() {
		return kqdjid;
	}
	public void setKqdjid(String kqdjid) {
		this.kqdjid = kqdjid;
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
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
	}
	public String getKqkc() {
		return kqkc;
	}
	public void setKqkc(String kqkc) {
		this.kqkc = kqkc;
	}
	public String getDd() {
		return dd;
	}
	public void setDd(String dd) {
		this.dd = dd;
	}
	public String getKqlxdm() {
		return kqlxdm;
	}
	public void setKqlxdm(String kqlxdm) {
		this.kqlxdm = kqlxdm;
	}
	public String getKqsj() {
		return kqsj;
	}
	public void setKqsj(String kqsj) {
		this.kqsj = kqsj;
	}
	public String getKqqk() {
		return kqqk;
	}
	public void setKqqk(String kqqk) {
		this.kqqk = kqqk;
	}
	public String getZjsj() {
		return zjsj;
	}
	public void setZjsj(String zjsj) {
		this.zjsj = zjsj;
	}
	
}
