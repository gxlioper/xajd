/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:41:01 
 */  
package com.zfsoft.xgxt.rcsw.sbgl.sbxx;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @�๦������: �豸����ά��
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����09:41:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SbxxModel extends ActionForm {

	
	private static final long serialVersionUID = 7426339785751241000L;
	
	private String dm;
	private String mc;
	
	private String fldm;
	private String bh;
	private String flmc;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	
	
	/**
	 * @return the flmc
	 */
	public String getFlmc() {
		return flmc;
	}
	/**
	 * @param flmcҪ���õ� flmc
	 */
	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}
	/**
	 * @return the dm
	 */
	public String getDm() {
		return dm;
	}
	/**
	 * @param dmҪ���õ� dm
	 */
	public void setDm(String dm) {
		this.dm = dm;
	}
	/**
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}
	/**
	 * @param mcҪ���õ� mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
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
	 * @return the fldm
	 */
	public String getFldm() {
		return fldm;
	}
	/**
	 * @param fldmҪ���õ� fldm
	 */
	public void setFldm(String fldm) {
		this.fldm = fldm;
	}
	/**
	 * @return the bh
	 */
	public String getBh() {
		return bh;
	}
	/**
	 * @param bhҪ���õ� bh
	 */
	public void setBh(String bh) {
		this.bh = bh;
	}
	
	

}
