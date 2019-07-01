/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-23 ����01:34:40 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �۲���� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-23 ����01:34:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcfsModel extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private String doType;
	private String editType;		//�ж��۲���Ŀ�Ǳ༭ģʽ���ǲ鿴ģʽ
	
	private String id;
	private String xh;
	private String xm;
	private String bjdm;
	private String fs;
	private String lrr;
	private String lrsj;
	private String xn;
	private String xq;
	private String xmdm;
	private FormFile importFile;
	private String pmfs;	//������ʽ
	private String qxyy;	//ȡ��ԭ��
	private String tjr;	//ԭ�ύ��
	private String tjsj;	//ԭ�ύʱ��
	private String zczq;	//�۲�����
	private String zcyf;//�۲��·�
	private String xmdms; // �Ϻ�Ϸ��ѧԺ���Ի�
	private String cxlx;
	
	/**
	 * @return the cxlx
	 */
	public String getCxlx() {
		return cxlx;
	}
	/**
	 * @param cxlxҪ���õ� cxlx
	 */
	public void setCxlx(String cxlx) {
		this.cxlx = cxlx;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getDoType() {
		return doType;
	}
	public void setDoType(String doType) {
		this.doType = doType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getFs() {
		return fs;
	}
	public void setFs(String fs) {
		this.fs = fs;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public String getLrsj() {
		return lrsj;
	}
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
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
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public FormFile getImportFile() {
		return importFile;
	}
	public void setImportFile(FormFile importFile) {
		this.importFile = importFile;
	}
	public String getPmfs() {
		return pmfs;
	}
	public void setPmfs(String pmfs) {
		this.pmfs = pmfs;
	}
	public String getQxyy() {
		return qxyy;
	}
	public void setQxyy(String qxyy) {
		this.qxyy = qxyy;
	}
	public String getTjr() {
		return tjr;
	}
	public void setTjr(String tjr) {
		this.tjr = tjr;
	}
	public String getTjsj() {
		return tjsj;
	}
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}
	public String getZczq() {
		return zczq;
	}
	public void setZczq(String zczq) {
		this.zczq = zczq;
	}
	/**
	 * @return the editType
	 */
	public String getEditType() {
		return editType;
	}
	/**
	 * @param editTypeҪ���õ� editType
	 */
	public void setEditType(String editType) {
		this.editType = editType;
	}
	public String getZcyf() {
		return zcyf;
	}
	public void setZcyf(String zcyf) {
		this.zcyf = zcyf;
	}
	public String getXmdms() {
		return xmdms;
	}
	public void setXmdms(String xmdms) {
		this.xmdms = xmdms;
	}
	
}
