/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-1 ����11:46:10 
 */  
package com.zfsoft.xgxt.gygl.zzdgl.jg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-1 ����11:46:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZzdjgForm extends ActionForm{
	private String zzdid;
	private String xh;
	private String xn;
	private String xq;
	private String sqsj;
	private String zdqssj;
	private String zdzzsj;
	private String sdyy;
	private String splcid;
	private String shzt;
	private String filepath;
	private String jgid;
	private String qrzt;
	private String czy;
	private String czsj;
	private String sjly;
	private String qryj;
	private String[] qrids; //��������ȷ��
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the zzdid
	 */
	public String getZzdid() {
		return zzdid;
	}
	/**
	 * @param zzdidҪ���õ� zzdid
	 */
	public void setZzdid(String zzdid) {
		this.zzdid = zzdid;
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
	 * @return the zdqssj
	 */
	public String getZdqssj() {
		return zdqssj;
	}
	/**
	 * @param zdqssjҪ���õ� zdqssj
	 */
	public void setZdqssj(String zdqssj) {
		this.zdqssj = zdqssj;
	}
	/**
	 * @return the zdzzsj
	 */
	public String getZdzzsj() {
		return zdzzsj;
	}
	/**
	 * @param zdzzsjҪ���õ� zdzzsj
	 */
	public void setZdzzsj(String zdzzsj) {
		this.zdzzsj = zdzzsj;
	}
	/**
	 * @return the sdyy
	 */
	public String getSdyy() {
		return sdyy;
	}
	/**
	 * @param sdyyҪ���õ� sdyy
	 */
	public void setSdyy(String sdyy) {
		this.sdyy = sdyy;
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
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepathҪ���õ� filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
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
	 * @return the qrzt
	 */
	public String getQrzt() {
		return qrzt;
	}
	/**
	 * @param qrztҪ���õ� qrzt
	 */
	public void setQrzt(String qrzt) {
		this.qrzt = qrzt;
	}
	/**
	 * @return the czy
	 */
	public String getCzy() {
		return czy;
	}
	/**
	 * @param czyҪ���õ� czy
	 */
	public void setCzy(String czy) {
		this.czy = czy;
	}
	/**
	 * @return the czsj
	 */
	public String getCzsj() {
		return czsj;
	}
	/**
	 * @param czsjҪ���õ� czsj
	 */
	public void setCzsj(String czsj) {
		this.czsj = czsj;
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
	 * @return the qryj
	 */
	public String getQryj() {
		return qryj;
	}
	/**
	 * @param qryjҪ���õ� qryj
	 */
	public void setQryj(String qryj) {
		this.qryj = qryj;
	}
	/**
	 * @return the qrids
	 */
	public String[] getQrids() {
		return qrids;
	}
	/**
	 * @param qridsҪ���õ� qrids
	 */
	public void setQrids(String[] qrids) {
		this.qrids = qrids;
	}
	
	
	
	
}
