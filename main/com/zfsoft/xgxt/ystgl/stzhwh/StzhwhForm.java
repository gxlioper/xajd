/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-14 ����06:00:19 
 */  
package com.zfsoft.xgxt.ystgl.stzhwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-8-14 ����06:00:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class StzhwhForm extends ActionForm {
	
	 private String xh;
	 private String ystid ;
	 private String id ;
	 private String sjly;
	 private String xq;
	 private String xn;
	 private String cjpd;
	 private String type;
	 private String rtid;
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
	private SearchModel searchModel = new SearchModel();
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
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
	 * @return the stid
	 */
	public String getYstid() {
		return ystid;
	}
	/**
	 * @param stidҪ���õ� stid
	 */
	public void setYstid(String ystid) {
		this.ystid = ystid;
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
	 * @return the cjpd
	 */
	public String getCjpd() {
		return cjpd;
	}
	/**
	 * @param cjpdҪ���õ� cjpd
	 */
	public void setCjpd(String cjpd) {
		this.cjpd = cjpd;
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
}
