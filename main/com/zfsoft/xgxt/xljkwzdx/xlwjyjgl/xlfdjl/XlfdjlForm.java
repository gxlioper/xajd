/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����11:54:44 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.xlfdjl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-6-3 ����11:54:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlfdjlForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = -3427252545213781832L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;
	
	private String fdid;
	
	private String xh;
	
	private String zgh;
	
	private String zfdyxm;
	
	private String sj;
	
	private String lx;
	
	private String fdlxdm;
	
	private String thnrfdcs;

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
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}

	/**
	 * @param zghҪ���õ� zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	/**
	 * @return the sj
	 */
	public String getSj() {
		return sj;
	}

	/**
	 * @param sjҪ���õ� sj
	 */
	public void setSj(String sj) {
		this.sj = sj;
	}

	/**
	 * @return the lx
	 */
	public String getLx() {
		return lx;
	}

	/**
	 * @param lxҪ���õ� lx
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}

	/**
	 * @return the fdlxdm
	 */
	public String getFdlxdm() {
		return fdlxdm;
	}

	/**
	 * @param fdlxdmҪ���õ� fdlxdm
	 */
	public void setFdlxdm(String fdlxdm) {
		this.fdlxdm = fdlxdm;
	}

	/**
	 * @return the thnrfdcs
	 */
	public String getThnrfdcs() {
		return thnrfdcs;
	}

	/**
	 * @param thnrfdcsҪ���õ� thnrfdcs
	 */
	public void setThnrfdcs(String thnrfdcs) {
		this.thnrfdcs = thnrfdcs;
	}

	/**
	 * @return the fdid
	 */
	public String getFdid() {
		return fdid;
	}

	/**
	 * @param fdidҪ���õ� fdid
	 */
	public void setFdid(String fdid) {
		this.fdid = fdid;
	}

	/**
	 * @return the zfdyxm
	 */
	public String getZfdyxm() {
		return zfdyxm;
	}

	/**
	 * @param zfdyxmҪ���õ� zfdyxm
	 */
	public void setZfdyxm(String zfdyxm) {
		this.zfdyxm = zfdyxm;
	}
	
	
}
