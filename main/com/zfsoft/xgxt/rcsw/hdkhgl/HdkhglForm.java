/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-18 ����04:48:10 
 */  
package com.zfsoft.xgxt.rcsw.hdkhgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-8-18 ����04:48:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HdkhglForm extends ActionForm {
	 private String id;
	 private String hdjgid;
	 private String xh;
	 private String xmmc;
	 private String xn;
	 private String xq;
	 private String sfcj;
	 private String qqyy;
	 private SearchModel searchModel = new SearchModel();
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
	 private String type;
	 private String[] ids;
	 private String[] hdjgids;
	 private String[] xhs;
	 private String[] xmmcs;
	 private String[] xns;
	 private String[] xqs;
	 /**
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}
	/**
	 * @param idsҪ���õ� ids
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	/**
	 * @return the hdjgids
	 */
	public String[] getHdjgids() {
		return hdjgids;
	}
	/**
	 * @param hdjgidsҪ���õ� hdjgids
	 */
	public void setHdjgids(String[] hdjgids) {
		this.hdjgids = hdjgids;
	}
	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @param xhsҪ���õ� xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	/**
	 * @return the xmmcs
	 */
	public String[] getXmmcs() {
		return xmmcs;
	}
	/**
	 * @param xmmcsҪ���õ� xmmcs
	 */
	public void setXmmcs(String[] xmmcs) {
		this.xmmcs = xmmcs;
	}
	/**
	 * @return the xns
	 */
	public String[] getXns() {
		return xns;
	}
	/**
	 * @param xnsҪ���õ� xns
	 */
	public void setXns(String[] xns) {
		this.xns = xns;
	}
	/**
	 * @return the xqs
	 */
	public String[] getXqs() {
		return xqs;
	}
	/**
	 * @param xqsҪ���õ� xqs
	 */
	public void setXqs(String[] xqs) {
		this.xqs = xqs;
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
	 * @return the hdjgid
	 */
	public String getHdjgid() {
		return hdjgid;
	}
	/**
	 * @param hdjgidҪ���õ� hdjgid
	 */
	public void setHdjgid(String hdjgid) {
		this.hdjgid = hdjgid;
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
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmcҪ���õ� xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
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
	 * @return the sfcj
	 */
	public String getSfcj() {
		return sfcj;
	}
	/**
	 * @param sfcjҪ���õ� sfcj
	 */
	public void setSfcj(String sfcj) {
		this.sfcj = sfcj;
	}
	/**
	 * @return the qqyy
	 */
	public String getQqyy() {
		return qqyy;
	}
	/**
	 * @param qqyyҪ���õ� qqyy
	 */
	public void setQqyy(String qqyy) {
		this.qqyy = qqyy;
	}
	/**
	 * @return the jlygx
	 */
	public String getJlygx() {
		return jlygx;
	}
	/**
	 * @param jlygxҪ���õ� jlygx
	 */
	public void setJlygx(String jlygx) {
		this.jlygx = jlygx;
	}
	private String jlygx;
}
