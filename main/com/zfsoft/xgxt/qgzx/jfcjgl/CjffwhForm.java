package com.zfsoft.xgxt.qgzx.jfcjgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ����ģ��
 * @�๦������: ���ѳ�����  ����ά��Form
 * @���ߣ� zhangjw 
 * @ʱ�䣺2013-04-15 ����09:46:37 
 * @�汾�� V5.1.75
 * @�޸ļ�¼: 
 */
public class CjffwhForm  extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String	wbh	;	//	Ψһ���
	private String	xh	;	//	ѧ��
	private String	xn	;	//	ѧ��
	private String	yrbm	;	//	���˲���
	private String	yrdw	;	//	���˵�λ
	private String	gwdm	;	//	��λ����
	private String	gwmc	;	//	��λ����
	private String	zgzt	;	//	�ڸ�״̬
	private String	gs	;	//	��ʱ
	private String	je	;	//	���
	private String	bz	;	//	��ע
	private String	sjbs	;	//���ݱ�ʾ	
	private String ffsj;//����ʱ��
	private String	bmdm; //���Ŵ���
	private String xq;
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
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
	public String getYrbm() {
		return yrbm;
	}
	public void setYrbm(String yrbm) {
		this.yrbm = yrbm;
	}
	public String getGwmc() {
		return gwmc;
	}
	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}
	public String getZgzt() {
		return zgzt;
	}
	public void setZgzt(String zgzt) {
		this.zgzt = zgzt;
	}
	public String getGs() {
		return gs;
	}
	public void setGs(String gs) {
		this.gs = gs;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getFfsj() {
		return ffsj;
	}
	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getWbh() {
		return wbh;
	}
	public void setWbh(String wbh) {
		this.wbh = wbh;
	}
	public String getYrdw() {
		return yrdw;
	}
	public void setYrdw(String yrdw) {
		this.yrdw = yrdw;
	}
	public String getGwdm() {
		return gwdm;
	}
	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}
	public String getSjbs() {
		return sjbs;
	}
	public void setSjbs(String sjbs) {
		this.sjbs = sjbs;
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
