/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-3-21 ����09:30:28 
 */  
package com.zfsoft.xgxt.zjly.zhf.dellog;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ۺϷ�ɾ����־��ѯ
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� CP[����:1352]
 * @ʱ�䣺 2017-3-21 ����09:30:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfdelForm extends ActionForm{

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 5821280498417293083L;
	
	
	private String logid;
	private String scrzgh;//ɾ���˹���
	private String scrxm;
	private String scsj;
	private String xh;
	private String jfxmdm;
	private String xmmkdm;
	private String sxsm;
	private String shzt;
	private String cysj;
	private String lrr;
	private String lrrxm;
	private String lrsj;
	
	private String type;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	
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
	 * @return the logid
	 */
	public String getLogid() {
		return logid;
	}
	/**
	 * @param logidҪ���õ� logid
	 */
	public void setLogid(String logid) {
		this.logid = logid;
	}
	/**
	 * @return the scrzgh
	 */
	public String getScrzgh() {
		return scrzgh;
	}
	/**
	 * @param scrzghҪ���õ� scrzgh
	 */
	public void setScrzgh(String scrzgh) {
		this.scrzgh = scrzgh;
	}
	/**
	 * @return the scrxm
	 */
	public String getScrxm() {
		return scrxm;
	}
	/**
	 * @param scrxmҪ���õ� scrxm
	 */
	public void setScrxm(String scrxm) {
		this.scrxm = scrxm;
	}
	/**
	 * @return the scsj
	 */
	public String getScsj() {
		return scsj;
	}
	/**
	 * @param scsjҪ���õ� scsj
	 */
	public void setScsj(String scsj) {
		this.scsj = scsj;
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
	 * @return the jfxmdm
	 */
	public String getJfxmdm() {
		return jfxmdm;
	}
	/**
	 * @param jfxmdmҪ���õ� jfxmdm
	 */
	public void setJfxmdm(String jfxmdm) {
		this.jfxmdm = jfxmdm;
	}
	/**
	 * @return the xmmkdm
	 */
	public String getXmmkdm() {
		return xmmkdm;
	}
	/**
	 * @param xmmkdmҪ���õ� xmmkdm
	 */
	public void setXmmkdm(String xmmkdm) {
		this.xmmkdm = xmmkdm;
	}
	/**
	 * @return the sxsm
	 */
	public String getSxsm() {
		return sxsm;
	}
	/**
	 * @param sxsmҪ���õ� sxsm
	 */
	public void setSxsm(String sxsm) {
		this.sxsm = sxsm;
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
	 * @return the cysj
	 */
	public String getCysj() {
		return cysj;
	}
	/**
	 * @param cysjҪ���õ� cysj
	 */
	public void setCysj(String cysj) {
		this.cysj = cysj;
	}
	/**
	 * @return the lrr
	 */
	public String getLrr() {
		return lrr;
	}
	/**
	 * @param lrrҪ���õ� lrr
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	/**
	 * @return the lrrxm
	 */
	public String getLrrxm() {
		return lrrxm;
	}
	/**
	 * @param lrrxmҪ���õ� lrrxm
	 */
	public void setLrrxm(String lrrxm) {
		this.lrrxm = lrrxm;
	}
	/**
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @param lrsjҪ���õ� lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	
	
}
