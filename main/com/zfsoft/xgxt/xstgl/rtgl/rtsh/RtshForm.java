/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-14 ����08:37:52 
 */
package com.zfsoft.xgxt.xstgl.rtgl.rtsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-8-14 ����08:37:52
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class RtshForm extends ActionForm {
	private String rtid;
	private String xh;
	private String stid;
	private String rtxn;
	private String rtxq;
	private String sqly;
	private String tc;// �س�
	private String shzt;
	private String stxmmc;
	private String xmlbdm;
	private String sqsj;
	private String splc;
	private String shlc;
	private String shid;
	private String shyj;
	private String shjg;
	private String thgw;
	private String gwid;
	private String rylbdm;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;

	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	private String[] stids;

	/**
	 * @return the rylbdm
	 */
	public String getRylbdm() {
		return rylbdm;
	}

	/**
	 * @param rylbdmҪ���õ� rylbdm
	 */
	public void setRylbdm(String rylbdm) {
		this.rylbdm = rylbdm;
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
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}

	/**
	 * @param gwidҪ���õ�
	 *            gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}

	/**
	 * @param thgwҪ���õ�
	 *            thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}

	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}

	/**
	 * @param shjgҪ���õ�
	 *            shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}

	/**
	 * @param shyjҪ���õ�
	 *            shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}

	/**
	 * @param shidҪ���õ�
	 *            shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}

	/**
	 * @return the rtid
	 */
	public String getRtid() {
		return rtid;
	}

	/**
	 * @param rtidҪ���õ�
	 *            rtid
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
	 * @param xhҪ���õ�
	 *            xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/**
	 * @return the stid
	 */
	public String getStid() {
		return stid;
	}

	/**
	 * @param stidҪ���õ�
	 *            stid
	 */
	public void setStid(String stid) {
		this.stid = stid;
	}

	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}

	/**
	 * @param sqlyҪ���õ�
	 *            sqly
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
	 * @param tcҪ���õ�
	 *            tc
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
	 * @param shztҪ���õ�
	 *            shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	/**
	 * @return the stxmmc
	 */
	public String getStxmmc() {
		return stxmmc;
	}

	/**
	 * @param stxmmcҪ���õ�
	 *            stxmmc
	 */
	public void setStxmmc(String stxmmc) {
		this.stxmmc = stxmmc;
	}

	/**
	 * @return the xmlbdm
	 */
	public String getXmlbdm() {
		return xmlbdm;
	}

	/**
	 * @param xmlbdmҪ���õ�
	 *            xmlbdm
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
	 * @param sqsjҪ���õ�
	 *            sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}

	/**
	 * @param splcҪ���õ�
	 *            splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}

	/**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}

	/**
	 * @param shlcҪ���õ�
	 *            shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}

	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * @param pagesҪ���õ�
	 *            pages
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
	 * @param searchModelҪ���õ�
	 *            searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param typeҪ���õ�
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}

	/**
	 * @param idҪ���õ�
	 *            id
	 */
	public void setId(String[] id) {
		this.id = id;
	}

	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}

	/**
	 * @param gwidsҪ���õ�
	 *            gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}

	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}

	/**
	 * @param xhsҪ���õ�
	 *            xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	/**
	 * @return the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}

	/**
	 * @param splcsҪ���õ�
	 *            splcs
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}

	/**
	 * @return the stids
	 */
	public String[] getStids() {
		return stids;
	}

	/**
	 * @param stidsҪ���õ�
	 *            stids
	 */
	public void setStids(String[] stids) {
		this.stids = stids;
	}
}
