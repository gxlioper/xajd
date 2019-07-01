package com.zfsoft.xgxt.dekt.xmwh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class DektxmwhForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel(); //�߼���ѯ
	private Pages pages = new Pages(); // ��ҳ
	private ExportModel exportModel = new ExportModel(); //�Զ��嵼��
	
	private String xmid; //��ĿID
	private String ssxydm; //����ѧԺ����
	private String xmdl; //��Ŀ����
	private String lx; //����
	private String rdxm; //�϶���Ŀ
	private String rdnrbz; //�϶����ݱ�׼
	private String dj; //�ȼ�
	private String xf; //ѧ��
	private String yjsm; //����˵��
	private String splc; //��������
	
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModelҪ���õ� searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pagesҪ���õ� pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
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
	/**
	 * @return the xmid
	 */
	public String getXmid() {
		return xmid;
	}
	/**
	 * @param xmidҪ���õ� xmid
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	/**
	 * @return the ssxydm
	 */
	public String getSsxydm() {
		return ssxydm;
	}
	/**
	 * @param ssxydmҪ���õ� ssxydm
	 */
	public void setSsxydm(String ssxydm) {
		this.ssxydm = ssxydm;
	}
	/**
	 * @return the xmdl
	 */
	public String getXmdl() {
		return xmdl;
	}
	/**
	 * @param xmdlҪ���õ� xmdl
	 */
	public void setXmdl(String xmdl) {
		this.xmdl = xmdl;
	}
	/**
	 * @return the lx
	 */
	public String getLx() {
		return lx;
	}
	/**
	 * @param lxҪ���õ� lx
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}
	/**
	 * @return the rdxm
	 */
	public String getRdxm() {
		return rdxm;
	}
	/**
	 * @param rdxmҪ���õ� rdxm
	 */
	public void setRdxm(String rdxm) {
		this.rdxm = rdxm;
	}
	/**
	 * @return the rdnrbz
	 */
	public String getRdnrbz() {
		return rdnrbz;
	}
	/**
	 * @param rdnrbzҪ���õ� rdnrbz
	 */
	public void setRdnrbz(String rdnrbz) {
		this.rdnrbz = rdnrbz;
	}
	/**
	 * @return the dj
	 */
	public String getDj() {
		return dj;
	}
	/**
	 * @param djҪ���õ� dj
	 */
	public void setDj(String dj) {
		this.dj = dj;
	}
	/**
	 * @return the xf
	 */
	public String getXf() {
		return xf;
	}
	/**
	 * @param xfҪ���õ� xf
	 */
	public void setXf(String xf) {
		this.xf = xf;
	}
	/**
	 * @return the yjsm
	 */
	public String getYjsm() {
		return yjsm;
	}
	/**
	 * @param yjsmҪ���õ� yjsm
	 */
	public void setYjsm(String yjsm) {
		this.yjsm = yjsm;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
