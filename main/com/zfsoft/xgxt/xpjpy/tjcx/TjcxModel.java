/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-4 ����06:12:31 
 */  
package com.zfsoft.xgxt.xpjpy.tjcx;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-11-4 ����06:12:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjcxModel extends ActionForm{
	
	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String type;
	private Pages pages = new Pages();
	private String pk;	//����
	private String xn;	//ѧ��
	private String xq;	//ѧ��
	private String xh;	//ѧ��
	private String xm;	//����
	private String nj;	//�꼶
	private String xydm;	//ѧԺ����
	private String zydm;	//רҵ����
	private String bjdm;	//�༶����
	private String xymc;	//ѧԺ����
	private String xmmc;	//ѧԺ����
	private String hdsj;	//���ʱ��
	private String xmje;	//��Ŀ���
	private String xmlxmc;	//��Ŀ��������
	private String xmxzmc;	//��Ŀ��������

	private String [] xyArr;
	
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
	
	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
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

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getHdsj() {
		return hdsj;
	}

	public void setHdsj(String hdsj) {
		this.hdsj = hdsj;
	}

	public String getXmje() {
		return xmje;
	}

	public void setXmje(String xmje) {
		this.xmje = xmje;
	}

	public String getXmlxmc() {
		return xmlxmc;
	}

	public void setXmlxmc(String xmlxmc) {
		this.xmlxmc = xmlxmc;
	}

	public String getXmxzmc() {
		return xmxzmc;
	}

	public void setXmxzmc(String xmxzmc) {
		this.xmxzmc = xmxzmc;
	}

	public String[] getXyArr() {
		return xyArr;
	}

	public void setXyArr(String[] xyArr) {
		this.xyArr = xyArr;
	}
}
