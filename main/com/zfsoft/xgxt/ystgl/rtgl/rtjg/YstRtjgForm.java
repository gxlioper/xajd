/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-7 ����10:35:48 
 */  
package com.zfsoft.xgxt.ystgl.rtgl.rtjg;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-8-7 ����10:35:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YstRtjgForm extends ActionForm {
	private String rtid;
	private String xh;
	private String ystid;
	private String sqly;
	private String tc;//�س�
	private String shzt;
	private String ystxmmc;
	private String xmlbdm;
	private String sqsj;
	private String rtsj;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	private String rtxn;
	private String rtxq;
	private String tnzt;
	private String sjly;
	/**
	 * @return the rtid
	 */
	public String getRtid() {
		return rtid;
	}
	/**
	 * @param rtidҪ���õ� rtid
	 */
	public void setRtid(String rtid) {
		this.rtid = rtid;
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
	 * @return the ystid
	 */
	public String getYstid() {
		return ystid;
	}
	/**
	 * @param ystidҪ���õ� ystid
	 */
	public void setYstid(String ystid) {
		this.ystid = ystid;
	}
	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqlyҪ���õ� sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @return the tc
	 */
	public String getTc() {
		return tc;
	}
	/**
	 * @param tcҪ���õ� tc
	 */
	public void setTc(String tc) {
		this.tc = tc;
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
	 * @return the ystxmmc
	 */
	public String getYstxmmc() {
		return ystxmmc;
	}
	/**
	 * @param ystxmmcҪ���õ� ystxmmc
	 */
	public void setYstxmmc(String ystxmmc) {
		this.ystxmmc = ystxmmc;
	}
	/**
	 * @return the xmlbdm
	 */
	public String getXmlbdm() {
		return xmlbdm;
	}
	/**
	 * @param xmlbdmҪ���õ� xmlbdm
	 */
	public void setXmlbdm(String xmlbdm) {
		this.xmlbdm = xmlbdm;
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
	 * @return the rtxn
	 */
	public String getRtxn() {
		return rtxn;
	}
	/**
	 * @param rtxnҪ���õ� rtxn
	 */
	public void setRtxn(String rtxn) {
		this.rtxn = rtxn;
	}
	/**
	 * @return the rtxq
	 */
	public String getRtxq() {
		return rtxq;
	}
	/**
	 * @param rtxqҪ���õ� rtxq
	 */
	public void setRtxq(String rtxq) {
		this.rtxq = rtxq;
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
	 * @return the rtsj
	 */
	public String getRtsj() {
		return rtsj;
	}
	/**
	 * @param rtsjҪ���õ� rtsj
	 */
	public void setRtsj(String rtsj) {
		this.rtsj = rtsj;
	}
	/**
	 * @return the tnzt
	 */
	public String getTnzt() {
		return tnzt;
	}
	/**
	 * @param tnztҪ���õ� tnzt
	 */
	public void setTnzt(String tnzt) {
		this.tnzt = tnzt;
	}
	
}
