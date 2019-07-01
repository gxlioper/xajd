/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:41:01 
 */  
package com.zfsoft.xgxt.rcsw.sbgl.jyjl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @�๦������: �豸����ά��
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����09:41:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JyjlModel extends ActionForm {

	
	private static final long serialVersionUID = 7153147718443427966L;
	
	private String id;
	private String zgh;
	private String fldm;
	private String sbdm;
	private String jysj;
	private String ghsj;
	private String bz;
	private String jyyy;
	private String jbr;
	
	private String ghbz;
	private String ghjbr;
	private String jbrxm;
	private String ghjbrxm;
	
	private String flmc;
	private String sbmc;
	
	private ExportModel exportModel = new ExportModel();
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	
	private String[] fldmArr;
	private String[] sbdmArr;
	private String[] bzArr;
	private String[] jysjArr;
	
	
	
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
	 * @return the sbmc
	 */
	public String getSbmc() {
		return sbmc;
	}
	/**
	 * @param sbmcҪ���õ� sbmc
	 */
	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}
	/**
	 * @return the jbrxm
	 */
	public String getJbrxm() {
		return jbrxm;
	}
	/**
	 * @param jbrxmҪ���õ� jbrxm
	 */
	public void setJbrxm(String jbrxm) {
		this.jbrxm = jbrxm;
	}
	/**
	 * @return the ghjbrxm
	 */
	public String getGhjbrxm() {
		return ghjbrxm;
	}
	/**
	 * @param ghjbrxmҪ���õ� ghjbrxm
	 */
	public void setGhjbrxm(String ghjbrxm) {
		this.ghjbrxm = ghjbrxm;
	}
	/**
	 * @return the ghbz
	 */
	public String getGhbz() {
		return ghbz;
	}
	/**
	 * @param ghbzҪ���õ� ghbz
	 */
	public void setGhbz(String ghbz) {
		this.ghbz = ghbz;
	}
	/**
	 * @return the ghjbr
	 */
	public String getGhjbr() {
		return ghjbr;
	}
	/**
	 * @param ghjbrҪ���õ� ghjbr
	 */
	public void setGhjbr(String ghjbr) {
		this.ghjbr = ghjbr;
	}
	/**
	 * @return the fldmArr
	 */
	public String[] getFldmArr() {
		return fldmArr;
	}
	/**
	 * @param fldmArrҪ���õ� fldmArr
	 */
	public void setFldmArr(String[] fldmArr) {
		this.fldmArr = fldmArr;
	}
	/**
	 * @return the sbdmArr
	 */
	public String[] getSbdmArr() {
		return sbdmArr;
	}
	/**
	 * @param sbdmArrҪ���õ� sbdmArr
	 */
	public void setSbdmArr(String[] sbdmArr) {
		this.sbdmArr = sbdmArr;
	}
	/**
	 * @return the bzArr
	 */
	public String[] getBzArr() {
		return bzArr;
	}
	/**
	 * @param bzArrҪ���õ� bzArr
	 */
	public void setBzArr(String[] bzArr) {
		this.bzArr = bzArr;
	}
	/**
	 * @return the jysjArr
	 */
	public String[] getJysjArr() {
		return jysjArr;
	}
	/**
	 * @param jysjArrҪ���õ� jysjArr
	 */
	public void setJysjArr(String[] jysjArr) {
		this.jysjArr = jysjArr;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zghҪ���õ� zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
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
	 * @return the sbdm
	 */
	public String getSbdm() {
		return sbdm;
	}
	/**
	 * @param sbdmҪ���õ� sbdm
	 */
	public void setSbdm(String sbdm) {
		this.sbdm = sbdm;
	}
	/**
	 * @return the jysj
	 */
	public String getJysj() {
		return jysj;
	}
	/**
	 * @param jysjҪ���õ� jysj
	 */
	public void setJysj(String jysj) {
		this.jysj = jysj;
	}
	/**
	 * @return the ghsj
	 */
	public String getGhsj() {
		return ghsj;
	}
	/**
	 * @param ghsjҪ���õ� ghsj
	 */
	public void setGhsj(String ghsj) {
		this.ghsj = ghsj;
	}
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
	/**
	 * @return the jyyy
	 */
	public String getJyyy() {
		return jyyy;
	}
	/**
	 * @param jyyyҪ���õ� jyyy
	 */
	public void setJyyy(String jyyy) {
		this.jyyy = jyyy;
	}
	/**
	 * @return the jbr
	 */
	public String getJbr() {
		return jbr;
	}
	/**
	 * @param jbrҪ���õ� jbr
	 */
	public void setJbr(String jbr) {
		this.jbr = jbr;
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
	
	
}
