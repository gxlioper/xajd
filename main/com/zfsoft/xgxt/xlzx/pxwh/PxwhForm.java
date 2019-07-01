/**
 * @���ţ�ѧ����Ʒ��ҵ��
 * @���ڣ�2016��11��17��
 */  
package com.zfsoft.xgxt.xlzx.pxwh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ���ƣ�ѧ����������ϵͳ
 * @ģ�����ƣ���������ѵά�� ����ģ��
 * @�๦����������������ѵά�� ʵ��
 * @���ߣ�׿��[����:1391]
 * @ʱ�䣺2016��11��17��
 * @�汾��V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class PxwhForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel(); //�߼���ѯ
	private Pages pages = new Pages(); // ��ҳ
	private ExportModel exportModel = new ExportModel(); //�Զ��嵼��
	private String type;
	
	private  String pxid; //��ѵID
	private  String pxzt; //��ѵ����
	private  String pxdd; //��ѵ�ص�
	private  String pxsj; //��ѵʱ��
	private  String js; //��ʦ
	private  String sxrs; //��������
	private  String pxnr; //��ѵ����
	private  String bmkg; //��������
	private  String kssj; //��ʼʱ��
	private  String jssj; //����ʱ��
	private String sfybm; //�Ƿ��ѱ���
	private String ybmrs; //�ѱ�������
	
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
	
	public String getPxid() {
		return pxid;
	}
	public void setPxid(String pxid) {
		this.pxid = pxid;
	}
	
	public String getPxzt() {
		return pxzt;
	}
	public void setPxzt(String pxzt) {
		this.pxzt = pxzt;
	}
	
	public String getPxdd() {
		return pxdd;
	}
	public void setPxdd(String pxdd) {
		this.pxdd = pxdd;
	}
	
	public String getPxsj() {
		return pxsj;
	}
	public void setPxsj(String pxsj) {
		this.pxsj = pxsj;
	}
	
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	
	public String getSxrs() {
		return sxrs;
	}
	public void setSxrs(String sxrs) {
		this.sxrs = sxrs;
	}
	
	public String getPxnr() {
		return pxnr;
	}
	public void setPxnr(String pxnr) {
		this.pxnr = pxnr;
	}


	public String getBmkg() {
		return bmkg;
	}
	public void setBmkg(String bmkg) {
		this.bmkg = bmkg;
	}


	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	

	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	
	public String getSfybm() {
		return sfybm;
	}
	public void setSfybm(String sfybm) {
		this.sfybm = sfybm;
	}
	
	public String getYbmrs() {
		return ybmrs;
	}
	public void setYbmrs(String ybmrs) {
		this.ybmrs = ybmrs;
	}
	
}
