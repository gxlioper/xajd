/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-19 ����11:45:44 
 */  
package com.zfsoft.xgxt.gygl.ggwpgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-4-19 ����11:45:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GgwpjyForm extends ActionForm{
	private String id;
	private String xh;
	private String fldm;
	private String sbdm;
	private String jysj;
	private String ghzt;
	private String ghsj;
	private String bz;
	private String jyyy;
	private String jbr;
	private String ghjbr;
	private String fldmArr[];
	private String sbdmArr[];
	private String jysjArr[];
	private String bzArr[];
	private String xm;
	private String jbrxm;
	private String sbmc;
	private String flmc;
	private String ghztmc;
	private String ghjbrxm;
	private String ghbz;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
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
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xhҪ���õ� xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
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
	 * @return the ghzt
	 */
	public String getGhzt() {
		return ghzt;
	}
	/**
	 * @param ghztҪ���õ� ghzt
	 */
	public void setGhzt(String ghzt) {
		this.ghzt = ghzt;
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
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
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
	 * @return the ghztmc
	 */
	public String getGhztmc() {
		return ghztmc;
	}
	/**
	 * @param ghztmcҪ���õ� ghztmc
	 */
	public void setGhztmc(String ghztmc) {
		this.ghztmc = ghztmc;
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
	
	
	
	
}
