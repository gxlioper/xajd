/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-6 ����01:40:47 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.sq;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ҵ�������
 * @�๦������: ��ҵ�������� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-6 ����01:40:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ByhkglSqForm extends ActionForm{
	
	private String sqid;
	private String xh;
	private String hkje;
	private String sfzq;
	private String zqyy;
	private String zqyymc;
	private String zqqx;
	private String shzt;
	private String splc;
	private String sqsj;
	private String bz;
	//�������
	private FormFile formfile;
	private String filepath;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getHkje() {
		return hkje;
	}
	public void setHkje(String hkje) {
		this.hkje = hkje;
	}
	public String getSfzq() {
		return sfzq;
	}
	public void setSfzq(String sfzq) {
		this.sfzq = sfzq;
	}
	public String getZqyy() {
		return zqyy;
	}
	public void setZqyy(String zqyy) {
		this.zqyy = zqyy;
	}
	public String getZqqx() {
		return zqqx;
	}
	public void setZqqx(String zqqx) {
		this.zqqx = zqqx;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public FormFile getFormfile() {
		return formfile;
	}
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
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
	public String getZqyymc() {
		return zqyymc;
	}
	public void setZqyymc(String zqyymc) {
		this.zqyymc = zqyymc;
	}	

}
