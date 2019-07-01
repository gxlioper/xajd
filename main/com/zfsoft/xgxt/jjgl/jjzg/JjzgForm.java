/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-18 ����11:16:58 
 */  
package com.zfsoft.xgxt.jjgl.jjzg;

import java.io.Serializable;


import com.zfsoft.xgxt.base.model.SuperAuditModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @�๦������: �ҽ��ʸ� 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-8-18 ����11:16:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JjzgForm extends SuperAuditModel implements Serializable{

	private static final long serialVersionUID = -6134358457163622433L;
	
	private String sqid;
	private String xh;
	private String jjnjdm;
	private String jjkma;
	private String jjkmb;
	private String jjkmc;
	private String lxdh;
	private String bz;
	private String jjpxqk;
	private String rddj;
	private String shzt;
	private String sqsj;
	private String splcid;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String xkmca;
	private String xkmcb;
	private String xkmcc;
	private String jjnjmc;
	private String sckmmcs;
	/**
	 * @return the jjnjmc
	 */
	public String getJjnjmc() {
		return jjnjmc;
	}
	/**
	 * @param jjnjmcҪ���õ� jjnjmc
	 */
	public void setJjnjmc(String jjnjmc) {
		this.jjnjmc = jjnjmc;
	}
	/**
	 * @return the xkmca
	 */
	public String getXkmca() {
		return xkmca;
	}
	/**
	 * @param xkmcaҪ���õ� xkmca
	 */
	public void setXkmca(String xkmca) {
		this.xkmca = xkmca;
	}
	/**
	 * @return the xkmcb
	 */
	public String getXkmcb() {
		return xkmcb;
	}
	/**
	 * @param xkmcbҪ���õ� xkmcb
	 */
	public void setXkmcb(String xkmcb) {
		this.xkmcb = xkmcb;
	}
	/**
	 * @return the xkmcc
	 */
	public String getXkmcc() {
		return xkmcc;
	}
	/**
	 * @param xkmccҪ���õ� xkmcc
	 */
	public void setXkmcc(String xkmcc) {
		this.xkmcc = xkmcc;
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
	 * @return the jjnjdm
	 */
	public String getJjnjdm() {
		return jjnjdm;
	}
	/**
	 * @param jjnjdmҪ���õ� jjnjdm
	 */
	public void setJjnjdm(String jjnjdm) {
		this.jjnjdm = jjnjdm;
	}
	/**
	 * @return the jjkma
	 */
	public String getJjkma() {
		return jjkma;
	}
	/**
	 * @param jjkmaҪ���õ� jjkma
	 */
	public void setJjkma(String jjkma) {
		this.jjkma = jjkma;
	}
	/**
	 * @return the jjkmb
	 */
	public String getJjkmb() {
		return jjkmb;
	}
	/**
	 * @param jjkmbҪ���õ� jjkmb
	 */
	public void setJjkmb(String jjkmb) {
		this.jjkmb = jjkmb;
	}
	/**
	 * @return the jjkmc
	 */
	public String getJjkmc() {
		return jjkmc;
	}
	/**
	 * @param jjkmcҪ���õ� jjkmc
	 */
	public void setJjkmc(String jjkmc) {
		this.jjkmc = jjkmc;
	}
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdhҪ���õ� lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
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
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the jjpxqk
	 */
	public String getJjpxqk() {
		return jjpxqk;
	}
	/**
	 * @param jjpxqkҪ���õ� jjpxqk
	 */
	public void setJjpxqk(String jjpxqk) {
		this.jjpxqk = jjpxqk;
	}
	/**
	 * @return the rddj
	 */
	public String getRddj() {
		return rddj;
	}
	/**
	 * @param rddjҪ���õ� rddj
	 */
	public void setRddj(String rddj) {
		this.rddj = rddj;
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

	public String getSckmmcs() {
		return sckmmcs;
	}

	public void setSckmmcs(String sckmmcs) {
		this.sckmmcs = sckmmcs;
	}
}
