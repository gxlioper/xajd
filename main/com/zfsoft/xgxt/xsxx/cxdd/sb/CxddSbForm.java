/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-28 ����05:19:30 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.sb;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�yxy[����:1206]
 * @ʱ�䣺 2016-3-28 ����05:19:30 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxddSbForm extends ActionForm {
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
	 private String type;
	 private String flag;
	 private String bjdms;
	 private String flag1;
	
	/**
	 * @return the flag1
	 */
	public String getFlag1() {
		return flag1;
	}
	/**
	 * @param flag1Ҫ���õ� flag1
	 */
	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}
	/**
	 * @return the bjdms
	 */
	public String getBjdms() {
		return bjdms;
	}
	/**
	 * @param bjdmsҪ���õ� bjdms
	 */
	public void setBjdms(String bjdms) {
		this.bjdms = bjdms;
	}
	private Pages pages = new Pages(); 
	 /**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
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
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
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
