/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-2 ����10:13:48 
 */  
package com.zfsoft.xgxt.xpjpy.tsxs;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���������Ź���ģ��
 * @�๦������: ����ѧ��ά�� 
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-2 ����10:13:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsModel extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private Pages pages = new Pages();
	private String tsxsxh;		//����ѧ��ѧ��
	private String lxdm;		//����ѧ������
	private String lxmc;		//����ѧ������
	private String lsmc;		//����ѧ������
	private String pjzq;		//��������
	private String xhs;			//ѧ����	
	private String xq;			//ѧ��
	private String xn; 			//ѧ��
	private String mklx;		//ģ������
	
	public String getXhs() {
		return xhs;
	}
	public void setXhs(String xhs) {
		this.xhs = xhs;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
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
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getTsxsxh() {
		return tsxsxh;
	}
	public void setTsxsxh(String tsxsxh) {
		this.tsxsxh = tsxsxh;
	}
	public String getLxdm() {
		return lxdm;
	}
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	
	public String getLxmc() {
		return lxmc;
	}
	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}
	public String getLsmc() {
		return lsmc;
	}
	public void setLsmc(String lsmc) {
		this.lsmc = lsmc;
	}
	public String getPjzq() {
		return pjzq;
	}
	public void setPjzq(String pjzq) {
		this.pjzq = pjzq;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getMklx() {
		return mklx;
	}
	public void setMklx(String mklx) {
		this.mklx = mklx;
	}
	
	
}
