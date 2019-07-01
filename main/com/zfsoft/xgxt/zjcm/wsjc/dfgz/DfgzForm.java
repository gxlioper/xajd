/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-2 ����08:58:42 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.dfgz;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ý������
 * @�๦������: ��ֹ���
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-3-2 ����08:58:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DfgzForm extends ActionForm {

	private static final long serialVersionUID = -1353497542272030712L;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String dfszid;
	private String xn;
	private String xq;
	private String ccny;
	private String wwsj;
	private String pfzid;
	private String ccbl;
	private String bhbyb;
	private String wwzzsj;
	private String dfzid;
	private String xqmc;
	private String tjzt;
	
	private List<HashMap<String, String>> pfzList = null;	//������list
	private List<HashMap<String, String>> pfzszList = null; //����������list
	
	
	
	
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
	 * @return the dfszid
	 */
	public String getDfszid() {
		return dfszid;
	}
	/**
	 * @param dfszidҪ���õ� dfszid
	 */
	public void setDfszid(String dfszid) {
		this.dfszid = dfszid;
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
	 * @return the ccny
	 */
	public String getCcny() {
		return ccny;
	}
	/**
	 * @param ccnyҪ���õ� ccny
	 */
	public void setCcny(String ccny) {
		this.ccny = ccny;
	}
	/**
	 * @return the wwsj
	 */
	public String getWwsj() {
		return wwsj;
	}
	/**
	 * @param wwsjҪ���õ� wwsj
	 */
	public void setWwsj(String wwsj) {
		this.wwsj = wwsj;
	}
	/**
	 * @return the pfzid
	 */
	public String getPfzid() {
		return pfzid;
	}
	/**
	 * @param pfzidҪ���õ� pfzid
	 */
	public void setPfzid(String pfzid) {
		this.pfzid = pfzid;
	}
	/**
	 * @return the ccbl
	 */
	public String getCcbl() {
		return ccbl;
	}
	/**
	 * @param ccblҪ���õ� ccbl
	 */
	public void setCcbl(String ccbl) {
		this.ccbl = ccbl;
	}
	/**
	 * @return the bhbyb
	 */
	public String getBhbyb() {
		return bhbyb;
	}
	/**
	 * @param bhbybҪ���õ� bhbyb
	 */
	public void setBhbyb(String bhbyb) {
		this.bhbyb = bhbyb;
	}
	/**
	 * @return the wwzzsj
	 */
	public String getWwzzsj() {
		return wwzzsj;
	}
	/**
	 * @param wwzzsjҪ���õ� wwzzsj
	 */
	public void setWwzzsj(String wwzzsj) {
		this.wwzzsj = wwzzsj;
	}
	/**
	 * @return the pfzList
	 */
	public List<HashMap<String, String>> getPfzList() {
		return pfzList;
	}
	/**
	 * @param pfzListҪ���õ� pfzList
	 */
	public void setPfzList(List<HashMap<String, String>> pfzList) {
		this.pfzList = pfzList;
	}
	/**
	 * @return the pfzszList
	 */
	public List<HashMap<String, String>> getPfzszList() {
		return pfzszList;
	}
	/**
	 * @param pfzszListҪ���õ� pfzszList
	 */
	public void setPfzszList(List<HashMap<String, String>> pfzszList) {
		this.pfzszList = pfzszList;
	}
	/**
	 * @return the dfzid
	 */
	public String getDfzid() {
		return dfzid;
	}
	/**
	 * @param dfzidҪ���õ� dfzid
	 */
	public void setDfzid(String dfzid) {
		this.dfzid = dfzid;
	}
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
	 * @return the tjzt
	 */
	public String getTjzt() {
		return tjzt;
	}
	/**
	 * @param tjztҪ���õ� tjzt
	 */
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}
	
	
}
