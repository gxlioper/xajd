/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-10 ����04:41:19 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsftj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-10 ����04:41:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsftjForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String wsfid;
	private String ssxq;
	private String lddm;
	private String ldmc;
	private String qsh;
	private String qsmc;
	private String pfzid;
	private String dfszid;
	private String ccny;
	private String ccr;
	private String ccrq;
	private String ccrip;
	private String fz;
	private String fzbz;
	private String ssxy;
	private String tjzt;
	private String xn;
	private String xq;
	private String[] ids;
	private String nj;
	private String nd;
	private String yf;
	private User user;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the wsfid
	 */
	public String getWsfid() {
		return wsfid;
	}
	/**
	 * @param wsfidҪ���õ� wsfid
	 */
	public void setWsfid(String wsfid) {
		this.wsfid = wsfid;
	}
	/**
	 * @return the ssxq
	 */
	public String getSsxq() {
		return ssxq;
	}
	/**
	 * @param ssxqҪ���õ� ssxq
	 */
	public void setSsxq(String ssxq) {
		this.ssxq = ssxq;
	}
	/**
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddmҪ���õ� lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @return the ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmcҪ���õ� ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @param qshҪ���õ� qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	/**
	 * @return the qsmc
	 */
	public String getQsmc() {
		return qsmc;
	}
	/**
	 * @param qsmcҪ���õ� qsmc
	 */
	public void setQsmc(String qsmc) {
		this.qsmc = qsmc;
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
	 * @return the ccr
	 */
	public String getCcr() {
		return ccr;
	}
	/**
	 * @param ccrҪ���õ� ccr
	 */
	public void setCcr(String ccr) {
		this.ccr = ccr;
	}
	/**
	 * @return the ccrq
	 */
	public String getCcrq() {
		return ccrq;
	}
	/**
	 * @param ccrqҪ���õ� ccrq
	 */
	public void setCcrq(String ccrq) {
		this.ccrq = ccrq;
	}
	/**
	 * @return the ccrip
	 */
	public String getCcrip() {
		return ccrip;
	}
	/**
	 * @param ccripҪ���õ� ccrip
	 */
	public void setCcrip(String ccrip) {
		this.ccrip = ccrip;
	}
	/**
	 * @return the fz
	 */
	public String getFz() {
		return fz;
	}
	/**
	 * @param fzҪ���õ� fz
	 */
	public void setFz(String fz) {
		this.fz = fz;
	}
	/**
	 * @return the fzbz
	 */
	public String getFzbz() {
		return fzbz;
	}
	/**
	 * @param fzbzҪ���õ� fzbz
	 */
	public void setFzbz(String fzbz) {
		this.fzbz = fzbz;
	}
	/**
	 * @return the ssxy
	 */
	public String getSsxy() {
		return ssxy;
	}
	/**
	 * @param ssxyҪ���õ� ssxy
	 */
	public void setSsxy(String ssxy) {
		this.ssxy = ssxy;
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
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param njҪ���õ� nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
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
	 * @return the nd
	 */
	public String getNd() {
		return nd;
	}
	/**
	 * @param ndҪ���õ� nd
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}
	/**
	 * @return the yf
	 */
	public String getYf() {
		return yf;
	}
	/**
	 * @param yfҪ���õ� yf
	 */
	public void setYf(String yf) {
		this.yf = yf;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param userҪ���õ� user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
