/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-28 ����05:21:08 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.sh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-3-28 ����05:21:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxddShForm extends ActionForm {
	 private String bjid;
	 private String bjdm;
	 private String xn;
	 private String xq;
	 private String tjsj;
	 private String tjr;
	 private String shzt;
	 private String xsid;
	 private String xh;
	 private String pj;
	 private String py;
	 private String splc;
	 private SearchModel searchModel = new SearchModel();
	 private static final long serialVersionUID = 1L;
	 private Pages pages = new Pages();
	 private String type;
	 private String flag;
	// private String bjdms;
	 private String shid;
	 private String shjg;
	 private String shyj;
	 private String gwid;
	 private String thgw;
	 private String shlc;
	 
	//���������
	 private String[] id;
	 private String[] gwids;
	 private String[] bjdms;
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
	 * @return the bjid
	 */
	public String getBjid() {
		return bjid;
	}
	/**
	 * @param bjidҪ���õ� bjid
	 */
	public void setBjid(String bjid) {
		this.bjid = bjid;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdmҪ���õ� bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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
	 * @return the tjsj
	 */
	public String getTjsj() {
		return tjsj;
	}
	/**
	 * @param tjsjҪ���õ� tjsj
	 */
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}
	/**
	 * @return the tjr
	 */
	public String getTjr() {
		return tjr;
	}
	/**
	 * @param tjrҪ���õ� tjr
	 */
	public void setTjr(String tjr) {
		this.tjr = tjr;
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
	 * @return the xsid
	 */
	public String getXsid() {
		return xsid;
	}
	/**
	 * @param xsidҪ���õ� xsid
	 */
	public void setXsid(String xsid) {
		this.xsid = xsid;
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
	 * @return the pj
	 */
	public String getPj() {
		return pj;
	}
	/**
	 * @param pjҪ���õ� pj
	 */
	public void setPj(String pj) {
		this.pj = pj;
	}
	/**
	 * @return the py
	 */
	public String getPy() {
		return py;
	}
	/**
	 * @param pyҪ���õ� py
	 */
	public void setPy(String py) {
		this.py = py;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
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
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * @param flagҪ���õ� flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * @return the bjdms
	 */
	public String[] getBjdms() {
		return bjdms;
	}
	/**
	 * @param bjdmsҪ���õ� bjdms
	 */
	public void setBjdms(String[] bjdms) {
		this.bjdms = bjdms;
	}
	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shidҪ���õ� shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjgҪ���õ� shjg
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
	 * @param shyjҪ���õ� shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwidҪ���õ� gwid
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
	 * @param thgwҪ���õ� thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlcҪ���õ� shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
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
	 * @param gwidsҪ���õ� gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	
}
