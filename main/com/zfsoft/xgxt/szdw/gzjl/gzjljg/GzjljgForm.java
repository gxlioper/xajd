/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����04:31:25 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjljg;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-6-25 ����04:31:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzjljgForm extends ActionForm {
	private String jgid;
	private String sqid;
	private String zgh;
	private String lbdm;
	private String xm;
	private String xymc;
	private String gzlbmc;
	private String lbbh;
	private String gzsj;
	private String jlsj;
	private String gzzy;
	private String czr;
	private String bz;
	private String sjly;
	private String type;
	private String lks;
	private String xh;
	private String[] xhArr;
	private String lksmc;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @param jgidҪ���õ� jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqidҪ���õ� sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	 * @return the lbdm
	 */
	public String getLbdm() {
		return lbdm;
	}
	/**
	 * @param lbdmҪ���õ� lbdm
	 */
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}
	/**
	 * @return the gzsj
	 */
	public String getGzsj() {
		return gzsj;
	}
	/**
	 * @param gzsjҪ���õ� gzsj
	 */
	public void setGzsj(String gzsj) {
		this.gzsj = gzsj;
	}
	/**
	 * @return the jlsj
	 */
	public String getJlsj() {
		return jlsj;
	}
	/**
	 * @param jlsjҪ���õ� jlsj
	 */
	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}
	/**
	 * @return the gzzy
	 */
	public String getGzzy() {
		return gzzy;
	}
	/**
	 * @param gzzyҪ���õ� gzzy
	 */
	public void setGzzy(String gzzy) {
		this.gzzy = gzzy;
	}
	/**
	 * @return the czr
	 */
	public String getCzr() {
		return czr;
	}
	/**
	 * @param czrҪ���õ� czr
	 */
	public void setCzr(String czr) {
		this.czr = czr;
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
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjlyҪ���õ� sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
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
	 * @return the lbbh
	 */
	public String getLbbh() {
		return lbbh;
	}
	/**
	 * @param lbbhҪ���õ� lbbh
	 */
	public void setLbbh(String lbbh) {
		this.lbbh = lbbh;
	}
	/**
	 * @return the gzlbmc
	 */
	public String getGzlbmc() {
		return gzlbmc;
	}
	/**
	 * @param gzlbmcҪ���õ� gzlbmc
	 */
	public void setGzlbmc(String gzlbmc) {
		this.gzlbmc = gzlbmc;
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
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymcҪ���õ� xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the lks
	 */
	public String getLks() {
		return lks;
	}
	/**
	 * @param lksҪ���õ� lks
	 */
	public void setLks(String lks) {
		this.lks = lks;
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
	 * @return the xhArr
	 */
	public String[] getXhArr() {
		return xhArr;
	}
	/**
	 * @param xhArrҪ���õ� xhArr
	 */
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}
	/**
	 * @return the lksmc
	 */
	public String getLksmc() {
		return lksmc;
	}
	/**
	 * @param lksmcҪ���õ� lksmc
	 */
	public void setLksmc(String lksmc) {
		this.lksmc = lksmc;
	}
	
	
	
	
	
}
