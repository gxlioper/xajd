/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-7-18 ����05:59:35 
 */  
package com.zfsoft.xgxt.dekt.qnzyry;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ����־Ը��Աform(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-7-18 ����05:59:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QnzyryForm extends ActionForm{
	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 29958884530608231L;
	private String id;
	private String xh;
	private String hdid;
	private String bmzt;
	private String gs;
	private String gsshzt;
	private String[] bmzts;
	private String[] ids;
	private String bmsj;
	private String mhcx;
	private String sftj;
	private String type;
	private String shzt;
	private String gsshyj;
	private String fwjg;
	private String jbfwgs;
	private String fwjgs[];
	private String pjjg;
	private String pjbz;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private Pages pages = new Pages();
	
	private String accept;
	
	private String maxsize;
	
	private String maxcount;
	
	

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
	 * @return the hdid
	 */
	public String getHdid() {
		return hdid;
	}

	/**
	 * @param hdidҪ���õ� hdid
	 */
	public void setHdid(String hdid) {
		this.hdid = hdid;
	}

	/**
	 * @return the bmzt
	 */
	public String getBmzt() {
		return bmzt;
	}

	/**
	 * @param bmztҪ���õ� bmzt
	 */
	public void setBmzt(String bmzt) {
		this.bmzt = bmzt;
	}

	/**
	 * @return the gs
	 */
	public String getGs() {
		return gs;
	}

	/**
	 * @param gsҪ���õ� gs
	 */
	public void setGs(String gs) {
		this.gs = gs;
	}

	/**
	 * @return the gsshzt
	 */
	public String getGsshzt() {
		return gsshzt;
	}

	/**
	 * @param gsshztҪ���õ� gsshzt
	 */
	public void setGsshzt(String gsshzt) {
		this.gsshzt = gsshzt;
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
	 * @return the accept
	 */
	public String getAccept() {
		return accept;
	}

	/**
	 * @param acceptҪ���õ� accept
	 */
	public void setAccept(String accept) {
		this.accept = accept;
	}

	/**
	 * @return the maxsize
	 */
	public String getMaxsize() {
		return maxsize;
	}

	/**
	 * @param maxsizeҪ���õ� maxsize
	 */
	public void setMaxsize(String maxsize) {
		this.maxsize = maxsize;
	}

	/**
	 * @return the maxcount
	 */
	public String getMaxcount() {
		return maxcount;
	}

	/**
	 * @param maxcountҪ���õ� maxcount
	 */
	public void setMaxcount(String maxcount) {
		this.maxcount = maxcount;
	}

	/**
	 * @return the bmzts
	 */
	public String[] getBmzts() {
		return bmzts;
	}

	/**
	 * @param bmztsҪ���õ� bmzts
	 */
	public void setBmzts(String[] bmzts) {
		this.bmzts = bmzts;
	}

	/**
	 * @return the bmsj
	 */
	public String getBmsj() {
		return bmsj;
	}

	/**
	 * @param bmsjҪ���õ� bmsj
	 */
	public void setBmsj(String bmsj) {
		this.bmsj = bmsj;
	}

	/**
	 * @return the mhcx
	 */
	public String getMhcx() {
		return mhcx;
	}

	/**
	 * @param mhcxҪ���õ� mhcx
	 */
	public void setMhcx(String mhcx) {
		this.mhcx = mhcx;
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
	 * @return the sftj
	 */
	public String getSftj() {
		return sftj;
	}

	/**
	 * @param sftjҪ���õ� sftj
	 */
	public void setSftj(String sftj) {
		this.sftj = sftj;
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
	 * @return the gsshyj
	 */
	public String getGsshyj() {
		return gsshyj;
	}

	/**
	 * @param gsshyjҪ���õ� gsshyj
	 */
	public void setGsshyj(String gsshyj) {
		this.gsshyj = gsshyj;
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
	 * @return the fwjg
	 */
	public String getFwjg() {
		return fwjg;
	}

	/**
	 * @param fwjgҪ���õ� fwjg
	 */
	public void setFwjg(String fwjg) {
		this.fwjg = fwjg;
	}

	/**
	 * @return the jbfwgs
	 */
	public String getJbfwgs() {
		return jbfwgs;
	}

	/**
	 * @param jbfwgsҪ���õ� jbfwgs
	 */
	public void setJbfwgs(String jbfwgs) {
		this.jbfwgs = jbfwgs;
	}

	/**
	 * @return the fwjgs
	 */
	public String[] getFwjgs() {
		return fwjgs;
	}

	/**
	 * @param fwjgsҪ���õ� fwjgs
	 */
	public void setFwjgs(String[] fwjgs) {
		this.fwjgs = fwjgs;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-15 ����05:51:23 
	 * @return		: the pjjg
	 */
	public String getPjjg() {
		return pjjg;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-15 ����05:51:23 
	 * @param 		��pjjg the pjjg to set
	 */
	public void setPjjg(String pjjg) {
		this.pjjg = pjjg;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-15 ����05:51:23 
	 * @return		: the pjbz
	 */
	public String getPjbz() {
		return pjbz;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-15 ����05:51:23 
	 * @param 		��pjbz the pjbz to set
	 */
	public void setPjbz(String pjbz) {
		this.pjbz = pjbz;
	}
	
	
}
