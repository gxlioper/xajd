/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-12-21 ����06:53:06 
 */  
package com.zfsoft.xgxt.pjpy.zyjgl.grzyjwh;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-רҵ������-����רҵ��ά��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2015-12-21 ����06:53:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GrzyjwhForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	//����ģ��
	ExportModel exportModel = new ExportModel();
	//�߼�����
	SearchModel searchModel = new SearchModel();
	// ��ҳ
	Pages pages = new Pages();
	
	private String ylzd3;
	private String bsmc;
	private String hjsj;
	private String id;	
	private String rddjdm;
	private String rddjmc;
	private String rdzt;
	private String splc;
	private String xh;
	private String xn;
	private String xq;
	private String zbdw;
	private String bz;
	private String type;
	
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getBsmc() {
		return bsmc;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}

	public String getHjsj() {
		return hjsj;
	}

	public String getId() {
		return id;
	}
	
	public Pages getPages() {
		return pages;
	}
	
	public String getRddjdm() {
		return rddjdm;
	}
	
	
	public String getRdzt() {
		return rdzt;
	}
	
	public SearchModel getSearchModel() {
		return searchModel;
	}
	
	public String getSplc() {
		return splc;
	}
	
	public String getXh() {
		return xh;
	}
	
	public String getXn() {
		return xn;
	}
	
	public String getXq() {
		return xq;
	}
	
	public String getZbdw() {
		return zbdw;
	}
	
	public void setBsmc(String bsmc) {
		this.bsmc = bsmc;
	}
	
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	
	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	public void setRddjdm(String rddjdm) {
		this.rddjdm = rddjdm;
	}
	
	/**
	 * @param rdztҪ���õ� rdzt
	 */
	public void setRdzt(String rdzt) {
		this.rdzt = rdzt;
	}
	
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	
	public void setSplc(String splc) {
		this.splc = splc;
	}
	
	public void setXh(String xh) {
		this.xh = xh;
	}
	
	public void setXn(String xn) {
		this.xn = xn;
	}
	
	public void setXq(String xq) {
		this.xq = xq;
	}
	
	public void setZbdw(String zbdw) {
		this.zbdw = zbdw;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * @param ylzd3Ҫ���õ� ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * @return the rddjmc
	 */
	public String getRddjmc() {
		return rddjmc;
	}
	/**
	 * @param rddjmcҪ���õ� rddjmc
	 */
	public void setRddjmc(String rddjmc) {
		this.rddjmc = rddjmc;
	}
	
	
	
	
	
	
	
}
