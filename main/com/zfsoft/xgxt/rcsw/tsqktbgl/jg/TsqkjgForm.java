/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-18 ����02:11:24 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.jg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-18 ����02:11:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsqkjgForm extends ActionForm{
	private String jgid;
	private String xn;
	private String xq;
	private String xh;
	private String xqdm1;
	private String xqdm2;
	private String tbsj;
	private String tsxq;
	private String tsxqgyqk;
	private String txsj;
	private String txr;
	private String sjly;
	private String lylcywid;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
	private String clcj;
	
	private String wtjjcd;//������ҽҩ��������̶�
	
	
	/**
	 * @return the wtjjcd
	 */
	public String getWtjjcd() {
		return wtjjcd;
	}
	/**
	 * @param wtjjcdҪ���õ� wtjjcd
	 */
	public void setWtjjcd(String wtjjcd) {
		this.wtjjcd = wtjjcd;
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
	 * @return the xqdm1
	 */
	public String getXqdm1() {
		return xqdm1;
	}
	/**
	 * @param xqdm1Ҫ���õ� xqdm1
	 */
	public void setXqdm1(String xqdm1) {
		this.xqdm1 = xqdm1;
	}
	/**
	 * @return the xqdm2
	 */
	public String getXqdm2() {
		return xqdm2;
	}
	/**
	 * @param xqdm2Ҫ���õ� xqdm2
	 */
	public void setXqdm2(String xqdm2) {
		this.xqdm2 = xqdm2;
	}
	/**
	 * @return the tbsj
	 */
	public String getTbsj() {
		return tbsj;
	}
	/**
	 * @param tbsjҪ���õ� tbsj
	 */
	public void setTbsj(String tbsj) {
		this.tbsj = tbsj;
	}
	/**
	 * @return the tsxq
	 */
	public String getTsxq() {
		return tsxq;
	}
	/**
	 * @param tsxqҪ���õ� tsxq
	 */
	public void setTsxq(String tsxq) {
		this.tsxq = tsxq;
	}
	/**
	 * @return the tsxqgyqk
	 */
	public String getTsxqgyqk() {
		return tsxqgyqk;
	}
	/**
	 * @param tsxqgyqkҪ���õ� tsxqgyqk
	 */
	public void setTsxqgyqk(String tsxqgyqk) {
		this.tsxqgyqk = tsxqgyqk;
	}
	/**
	 * @return the txsj
	 */
	public String getTxsj() {
		return txsj;
	}
	/**
	 * @param txsjҪ���õ� txsj
	 */
	public void setTxsj(String txsj) {
		this.txsj = txsj;
	}
	/**
	 * @return the txr
	 */
	public String getTxr() {
		return txr;
	}
	/**
	 * @param txrҪ���õ� txr
	 */
	public void setTxr(String txr) {
		this.txr = txr;
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
	 * @return the lylcywid
	 */
	public String getLylcywid() {
		return lylcywid;
	}
	/**
	 * @param lylcywidҪ���õ� lylcywid
	 */
	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
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
	 * @return the clcj
	 */
	public String getClcj() {
		return clcj;
	}
	/**
	 * @param clcjҪ���õ� clcj
	 */
	public void setClcj(String clcj) {
		this.clcj = clcj;
	}
	
	
	
}
