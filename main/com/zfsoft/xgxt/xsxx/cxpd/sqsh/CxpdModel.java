/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-12 ����09:31:56 
 */  
package com.zfsoft.xgxt.xsxx.cxpd.sqsh;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��������-������� 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2015-1-14 ����03:55:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class CxpdModel extends SuperAuditModel{

	private static final long serialVersionUID = -5951870429763694581L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String id;
	private String xh;
	private String xn;
	private String xq;
	private String cxdj;
	private String cxpy;
	private String bzr;
	
	private String shzt;
	private String splcid;
	private String sqsj;
	
	private String cxdjmc;
	private String xqmc;
	
	
	
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
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsjҪ���õ� sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @return the cxdj
	 */
	public String getCxdj() {
		return cxdj;
	}
	/**
	 * @param cxdjҪ���õ� cxdj
	 */
	public void setCxdj(String cxdj) {
		this.cxdj = cxdj;
	}
	/**
	 * @return the cxpy
	 */
	public String getCxpy() {
		return cxpy;
	}
	/**
	 * @param cxpyҪ���õ� cxpy
	 */
	public void setCxpy(String cxpy) {
		this.cxpy = cxpy;
	}
	/**
	 * @return the bzr
	 */
	public String getBzr() {
		return bzr;
	}
	/**
	 * @param bzrҪ���õ� bzr
	 */
	public void setBzr(String bzr) {
		this.bzr = bzr;
	}
	/**
	 * @return the cxdjmc
	 */
	public String getCxdjmc() {
		return cxdjmc;
	}
	/**
	 * @param cxdjmcҪ���õ� cxdjmc
	 */
	public void setCxdjmc(String cxdjmc) {
		this.cxdjmc = cxdjmc;
	}
	
	
}
