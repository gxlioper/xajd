/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-22 ����10:46:43 
 */  
package com.zfsoft.xgxt.xlzx.zxzxjl;

import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;



/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-12-22 ����10:46:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxzxjlModel extends ActionForm {	
	private static final long serialVersionUID = 1L;
	
	private String xh;
	private String[] xhs;
	private String sfdszn;
	private String jtszd;
	private String jtjjzk;
	private String fqwhcd;
	private String mqwhcd;
	private String fmhyzk;
	private String jtjsbs;
	private String jtxhcd;
	private String sfzl;
	private String djrq;
	private String[] yzxwts;
	private String yzxwt;
	private String wtbc;
	private String zxqw;
	private Pages pages = new Pages();
	private List<ZxzxjlxxModel> xxList;
	private String[] zjs;
	private String[] xgs;
	private String[] delIds;
	
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
	private String txr;
	public String getTxr() {
		return txr;
	}
	public void setTxr(String txr) {
		this.txr = txr;
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
	 * @return the sfdszn
	 */
	public String getSfdszn() {
		return sfdszn;
	}
	/**
	 * @param sfdsznҪ���õ� sfdszn
	 */
	public void setSfdszn(String sfdszn) {
		this.sfdszn = sfdszn;
	}
	/**
	 * @return the jtszd
	 */
	public String getJtszd() {
		return jtszd;
	}
	/**
	 * @param jtszdҪ���õ� jtszd
	 */
	public void setJtszd(String jtszd) {
		this.jtszd = jtszd;
	}
	/**
	 * @return the jtjjzk
	 */
	public String getJtjjzk() {
		return jtjjzk;
	}
	/**
	 * @param jtjjzkҪ���õ� jtjjzk
	 */
	public void setJtjjzk(String jtjjzk) {
		this.jtjjzk = jtjjzk;
	}
	/**
	 * @return the fqwhcd
	 */
	public String getFqwhcd() {
		return fqwhcd;
	}
	/**
	 * @param fqwhcdҪ���õ� fqwhcd
	 */
	public void setFqwhcd(String fqwhcd) {
		this.fqwhcd = fqwhcd;
	}
	/**
	 * @return the mqwhcd
	 */
	public String getMqwhcd() {
		return mqwhcd;
	}
	/**
	 * @param mqwhcdҪ���õ� mqwhcd
	 */
	public void setMqwhcd(String mqwhcd) {
		this.mqwhcd = mqwhcd;
	}
	/**
	 * @return the fmhyzk
	 */
	public String getFmhyzk() {
		return fmhyzk;
	}
	/**
	 * @param fmhyzkҪ���õ� fmhyzk
	 */
	public void setFmhyzk(String fmhyzk) {
		this.fmhyzk = fmhyzk;
	}
	/**
	 * @return the jtjsbs
	 */
	public String getJtjsbs() {
		return jtjsbs;
	}
	/**
	 * @param jtjsbsҪ���õ� jtjsbs
	 */
	public void setJtjsbs(String jtjsbs) {
		this.jtjsbs = jtjsbs;
	}
	/**
	 * @return the jtxhcd
	 */
	public String getJtxhcd() {
		return jtxhcd;
	}
	/**
	 * @param jtxhcdҪ���õ� jtxhcd
	 */
	public void setJtxhcd(String jtxhcd) {
		this.jtxhcd = jtxhcd;
	}
	/**
	 * @return the sfzl
	 */
	public String getSfzl() {
		return sfzl;
	}
	/**
	 * @param sfzlҪ���õ� sfzl
	 */
	public void setSfzl(String sfzl) {
		this.sfzl = sfzl;
	}
	/**
	 * @return the djrq
	 */
	public String getDjrq() {
		return djrq;
	}
	/**
	 * @param djrqҪ���õ� djrq
	 */
	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}
	/**
	 * @return the yzxwt
	 */
	public String getYzxwt() {
		return yzxwt;
	}
	/**
	 * @param yzxwtҪ���õ� yzxwt
	 */
	public void setYzxwt(String yzxwt) {
		this.yzxwt = yzxwt;
	}
	/**
	 * @return the wtbc
	 */
	public String getWtbc() {
		return wtbc;
	}
	/**
	 * @param wtbcҪ���õ� wtbc
	 */
	public void setWtbc(String wtbc) {
		this.wtbc = wtbc;
	}
	/**
	 * @return the zxqw
	 */
	public String getZxqw() {
		return zxqw;
	}
	/**
	 * @param zxqwҪ���õ� zxqw
	 */
	public void setZxqw(String zxqw) {
		this.zxqw = zxqw;
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
	 * @return the xxList
	 */
	public List<ZxzxjlxxModel> getXxList() {
		return xxList;
	}
	/**
	 * @param xxListҪ���õ� xxList
	 */
	public void setXxList(List<ZxzxjlxxModel> xxList) {
		this.xxList = xxList;
	}
	/**
	 * @return the yzxwts
	 */
	public String[] getYzxwts() {
		return yzxwts;
	}
	/**
	 * @param yzxwtsҪ���õ� yzxwts
	 */
	public void setYzxwts(String[] yzxwts) {
		this.yzxwts = yzxwts;
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
	 * @return the zjs
	 */
	public String[] getZjs() {
		return zjs;
	}
	/**
	 * @param zjsҪ���õ� zjs
	 */
	public void setZjs(String[] zjs) {
		this.zjs = zjs;
	}
	/**
	 * @return the xgs
	 */
	public String[] getXgs() {
		return xgs;
	}
	/**
	 * @param xgsҪ���õ� xgs
	 */
	public void setXgs(String[] xgs) {
		this.xgs = xgs;
	}
	/**
	 * @return the delIds
	 */
	public String[] getDelIds() {
		return delIds;
	}
	/**
	 * @param delIdsҪ���õ� delIds
	 */
	public void setDelIds(String[] delIds) {
		this.delIds = delIds;
	}
	
	
	  
}
