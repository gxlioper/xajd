/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:00:24 
 */
package com.zfsoft.xgxt.gygl.ssyd.shlc;

import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;


/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �����춯
 * @�๦������:form
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:00:24
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ShlcForm extends ActionForm {
	private String id;
	private String splcid;
	private String tssplcid;
	private String sstzsplcid;
	private String sxlssplcid;
	private String rzsplcid;
	private String type;
	private String zsfxs;
	
	private List<String[]> paramList;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String ssydlx;//�����춯����
	
	/**
	 * @return the ssydlx
	 */
	public String getSsydlx() {
		return ssydlx;
	}
	/**
	 * @param ssydlxҪ���õ� ssydlx
	 */
	public void setSsydlx(String ssydlx) {
		this.ssydlx = ssydlx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSplcid() {
		return splcid;
	}
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	/**
	 * @return the tssplcid
	 */
	public String getTssplcid() {
		return tssplcid;
	}
	/**
	 * @param tssplcidҪ���õ� tssplcid
	 */
	public void setTssplcid(String tssplcid) {
		this.tssplcid = tssplcid;
	}
	/**
	 * @return the sstzsplcid
	 */
	public String getSstzsplcid() {
		return sstzsplcid;
	}
	/**
	 * @param sstzsplcidҪ���õ� sstzsplcid
	 */
	public void setSstzsplcid(String sstzsplcid) {
		this.sstzsplcid = sstzsplcid;
	}
	/**
	 * @return the sxlssplcid
	 */
	public String getSxlssplcid() {
		return sxlssplcid;
	}
	/**
	 * @param sxlssplcidҪ���õ� sxlssplcid
	 */
	public void setSxlssplcid(String sxlssplcid) {
		this.sxlssplcid = sxlssplcid;
	}
	/**
	 * @return the rzsplcid
	 */
	public String getRzsplcid() {
		return rzsplcid;
	}
	/**
	 * @param rzsplcidҪ���õ� rzsplcid
	 */
	public void setRzsplcid(String rzsplcid) {
		this.rzsplcid = rzsplcid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getZsfxs() {
		return zsfxs;
	}
	public void setZsfxs(String zsfxs) {
		this.zsfxs = zsfxs;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the paramList
	 */
	public List<String[]> getParamList() {
		return paramList;
	}
	/**
	 * @param paramListҪ���õ� paramList
	 */
	public void setParamList(List<String[]> paramList) {
		this.paramList = paramList;
	}
	
}