/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-27 ����06:55:22 
 */  
package com.zfsoft.xgxt.znxgl.wdznx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-8-27 ����06:55:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WdznxForm extends ActionForm {
	private String xjbh;
	private String jsrbh;
	private String fprph;
	private String jsrydbj;
	private String jsrscbj;
	private String fsrbh;
	private String fssj;
	private String fsnr;
	private String ztlb;
	private String xjzt;
	private String fsrydbj;
	private String fsrscbj;
	private String[] xharr;
	private String[] teaarr;
	private String[] jsrbhs;
	private String[] xjbhs;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	/**
	 * @return the xjbhs
	 */
	public String[] getXjbhs() {
		return xjbhs;
	}
	/**
	 * @param xjbhsҪ���õ� xjbhs
	 */
	public void setXjbhs(String[] xjbhs) {
		this.xjbhs = xjbhs;
	}

	/**
	 * @return the xharr
	 */
	public String[] getXharr() {
		return xharr;
	}
	/**
	 * @param xharrҪ���õ� xharr
	 */
	public void setXharr(String[] xharr) {
		this.xharr = xharr;
	}
	/**
	 * @return the teaarr
	 */
	public String[] getTeaarr() {
		return teaarr;
	}
	/**
	 * @param teaarrҪ���õ� teaarr
	 */
	public void setTeaarr(String[] teaarr) {
		this.teaarr = teaarr;
	}

	/**
	 * @return the jsrbhs
	 */
	public String[] getJsrbhs() {
		return jsrbhs;
	}
	/**
	 * @param jsrbhsҪ���õ� jsrbhs
	 */
	public void setJsrbhs(String[] jsrbhs) {
		this.jsrbhs = jsrbhs;
	}

	/**
	 * @return the xjbh
	 */
	public String getXjbh() {
		return xjbh;
	}
	/**
	 * @param xjbhҪ���õ� xjbh
	 */
	public void setXjbh(String xjbh) {
		this.xjbh = xjbh;
	}
	/**
	 * @return the jsrbh
	 */
	public String getJsrbh() {
		return jsrbh;
	}
	/**
	 * @param jsrbhҪ���õ� jsrbh
	 */
	public void setJsrbh(String jsrbh) {
		this.jsrbh = jsrbh;
	}
	/**
	 * @return the fprph
	 */
	public String getFprph() {
		return fprph;
	}
	/**
	 * @param fprphҪ���õ� fprph
	 */
	public void setFprph(String fprph) {
		this.fprph = fprph;
	}
	/**
	 * @return the jsrydbj
	 */
	public String getJsrydbj() {
		return jsrydbj;
	}
	/**
	 * @param jsrydbjҪ���õ� jsrydbj
	 */
	public void setJsrydbj(String jsrydbj) {
		this.jsrydbj = jsrydbj;
	}
	/**
	 * @return the jsrscbj
	 */
	public String getJsrscbj() {
		return jsrscbj;
	}
	/**
	 * @param jsrscbjҪ���õ� jsrscbj
	 */
	public void setJsrscbj(String jsrscbj) {
		this.jsrscbj = jsrscbj;
	}
	/**
	 * @return the fsrbh
	 */
	public String getFsrbh() {
		return fsrbh;
	}
	/**
	 * @param fsrbhҪ���õ� fsrbh
	 */
	public void setFsrbh(String fsrbh) {
		this.fsrbh = fsrbh;
	}
	/**
	 * @return the fssj
	 */
	public String getFssj() {
		return fssj;
	}
	/**
	 * @param fssjҪ���õ� fssj
	 */
	public void setFssj(String fssj) {
		this.fssj = fssj;
	}
	/**
	 * @return the fsnr
	 */
	public String getFsnr() {
		return fsnr;
	}
	/**
	 * @param fsnrҪ���õ� fsnr
	 */
	public void setFsnr(String fsnr) {
		this.fsnr = fsnr;
	}
	/**
	 * @return the ztlb
	 */
	public String getZtlb() {
		return ztlb;
	}
	/**
	 * @param ztlbҪ���õ� ztlb
	 */
	public void setZtlb(String ztlb) {
		this.ztlb = ztlb;
	}
	/**
	 * @return the xjzt
	 */
	public String getXjzt() {
		return xjzt;
	}
	/**
	 * @param xjztҪ���õ� xjzt
	 */
	public void setXjzt(String xjzt) {
		this.xjzt = xjzt;
	}
	/**
	 * @return the fsrydbj
	 */
	public String getFsrydbj() {
		return fsrydbj;
	}
	/**
	 * @param fsrydbjҪ���õ� fsrydbj
	 */
	public void setFsrydbj(String fsrydbj) {
		this.fsrydbj = fsrydbj;
	}
	/**
	 * @return the fsrscbj
	 */
	public String getFsrscbj() {
		return fsrscbj;
	}
	/**
	 * @param fsrscbjҪ���õ� fsrscbj
	 */
	public void setFsrscbj(String fsrscbj) {
		this.fsrscbj = fsrscbj;
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

}
