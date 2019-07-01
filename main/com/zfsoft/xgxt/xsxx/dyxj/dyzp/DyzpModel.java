/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-19 ����11:08:33 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.dyzp;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @�๦������: ��������  
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-19 ����11:08:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DyzpModel extends SuperAuditModel {

	private static final long serialVersionUID = 2316939303013825371L;

	private String id ;
	private String xh ;
	private String xn ;
	private String xq ;
	private String djdm ;
	private String zpnr ;
	private String pysj ;
    private String splcid ;
    private String shzt ;
    private String type ;
    private String splc;
    private String xtgwid;
    private String pddjdm;
    
    private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String xqmc;
	private String djmc;
	
	private String[] sqid;
	private String[] splcids;
	private String[] xtgwids;
	private String[] xhs;
	
	
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmcҪ���õ� xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the djmc
	 */
	public String getDjmc() {
		return djmc;
	}
	/**
	 * @param djmcҪ���õ� djmc
	 */
	public void setDjmc(String djmc) {
		this.djmc = djmc;
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
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xnҪ���õ� xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xqҪ���õ� xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the djdm
	 */
	public String getDjdm() {
		return djdm;
	}
	/**
	 * @param djdmҪ���õ� djdm
	 */
	public void setDjdm(String djdm) {
		this.djdm = djdm;
	}
	/**
	 * @return the zpnr
	 */
	public String getZpnr() {
		return zpnr;
	}
	/**
	 * @param zpnrҪ���õ� zpnr
	 */
	public void setZpnr(String zpnr) {
		this.zpnr = zpnr;
	}
	/**
	 * @return the pysj
	 */
	public String getPysj() {
		return pysj;
	}
	/**
	 * @param pysjҪ���õ� pysj
	 */
	public void setPysj(String pysj) {
		this.pysj = pysj;
	}
	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcidҪ���õ� splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shztҪ���õ� shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String[] getSqid() {
		return sqid;
	}
	public void setSqid(String[] sqid) {
		this.sqid = sqid;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String[] getSplcids() {
		return splcids;
	}
	public void setSplcids(String[] splcids) {
		this.splcids = splcids;
	}
	public String getXtgwid() {
		return xtgwid;
	}
	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
	}
	public String[] getXtgwids() {
		return xtgwids;
	}
	public void setXtgwids(String[] xtgwids) {
		this.xtgwids = xtgwids;
	}
	public String getPddjdm() {
		return pddjdm;
	}
	public void setPddjdm(String pddjdm) {
		this.pddjdm = pddjdm;
	}
	
	
}
