/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-11 ����08:56:37 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxdj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-11 ����08:56:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxdjForm extends ActionForm {
	private String sqid;
	private String xh;
	private String xn;
	private String xq;
	private String jhrxm;
	private String jhrlxfs;
	private String sflx;
	private String lxsj;
	private String lxjtgjdm;
	private String lxcchb;
	private String mddssx;
	private String fxsj;
	private String fxjtgjdm;
	private String fxcchb;
	private String bz;
	private String splcid;
	private String shzt;
	private String sqsj;
	private String type;
	private String lxlx;//��У����
	private String sflxdm;//�Ƿ���У
	private ExportModel exportModel = new ExportModel(); 
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();

	public String getLxlx() {
		return lxlx;
	}

	public void setLxlx(String lxlx) {
		this.lxlx = lxlx;
	}

	public String getSflxdm() {
		return sflxdm;
	}

	public void setSflxdm(String sflxdm) {
		this.sflxdm = sflxdm;
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
	 * @return the jhrxm
	 */
	public String getJhrxm() {
		return jhrxm;
	}
	/**
	 * @param jhrxmҪ���õ� jhrxm
	 */
	public void setJhrxm(String jhrxm) {
		this.jhrxm = jhrxm;
	}
	/**
	 * @return the jhrlxfs
	 */
	public String getJhrlxfs() {
		return jhrlxfs;
	}
	/**
	 * @param jhrlxfsҪ���õ� jhrlxfs
	 */
	public void setJhrlxfs(String jhrlxfs) {
		this.jhrlxfs = jhrlxfs;
	}
	/**
	 * @return the sflx
	 */
	public String getSflx() {
		return sflx;
	}
	/**
	 * @param sflxҪ���õ� sflx
	 */
	public void setSflx(String sflx) {
		this.sflx = sflx;
	}
	/**
	 * @return the lxsj
	 */
	public String getLxsj() {
		return lxsj;
	}
	/**
	 * @param lxsjҪ���õ� lxsj
	 */
	public void setLxsj(String lxsj) {
		this.lxsj = lxsj;
	}
	/**
	 * @return the lxjtgjdm
	 */
	public String getLxjtgjdm() {
		return lxjtgjdm;
	}
	/**
	 * @param lxjtgjdmҪ���õ� lxjtgjdm
	 */
	public void setLxjtgjdm(String lxjtgjdm) {
		this.lxjtgjdm = lxjtgjdm;
	}
	/**
	 * @return the lxcchb
	 */
	public String getLxcchb() {
		return lxcchb;
	}
	/**
	 * @param lxcchbҪ���õ� lxcchb
	 */
	public void setLxcchb(String lxcchb) {
		this.lxcchb = lxcchb;
	}
	
	/**
	 * @return the mddssx
	 */
	public String getMddssx() {
		return mddssx;
	}
	/**
	 * @param mddssxҪ���õ� mddssx
	 */
	public void setMddssx(String mddssx) {
		this.mddssx = mddssx;
	}
	/**
	 * @return the fxsj
	 */
	public String getFxsj() {
		return fxsj;
	}
	/**
	 * @param fxsjҪ���õ� fxsj
	 */
	public void setFxsj(String fxsj) {
		this.fxsj = fxsj;
	}
	/**
	 * @return the fxjtgjdm
	 */
	public String getFxjtgjdm() {
		return fxjtgjdm;
	}
	/**
	 * @param fxjtgjdmҪ���õ� fxjtgjdm
	 */
	public void setFxjtgjdm(String fxjtgjdm) {
		this.fxjtgjdm = fxjtgjdm;
	}
	/**
	 * @return the fxcchb
	 */
	public String getFxcchb() {
		return fxcchb;
	}
	/**
	 * @param fxcchbҪ���õ� fxcchb
	 */
	public void setFxcchb(String fxcchb) {
		this.fxcchb = fxcchb;
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
}
