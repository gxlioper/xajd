/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-6-27 ����02:56:58 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zhpxf;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2017-6-27 ����02:56:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class zhpxfForm extends ActionForm{
	  /** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1315015409554665125L;
	private String xn;
	  private String  zcfid;
	  private String xm;
	  private String xh;
	  private String zhpxf;
	  private String drsj;
	  private String pjf;
	  private String zhszf;
	  private String zhszfpm;
	  private String type;
	  private FormFile drmb;//file
	  private User user;//user,�������ݷ�Χ����
	  private String filepath;//��Ŵ����ļ���·��
		private Pages pages = new Pages();
		  private SearchModel searchModel = new SearchModel();
		  private ExportModel exportModel = new ExportModel();
		  
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
	 * @return the zcfid
	 */
	public String getZcfid() {
		return zcfid;
	}
	/**
	 * @param zcfidҪ���õ� zcfid
	 */
	public void setZcfid(String zcfid) {
		this.zcfid = zcfid;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
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
	 * @return the drmb
	 */
	public FormFile getDrmb() {
		return drmb;
	}
	/**
	 * @param drmbҪ���õ� drmb
	 */
	public void setDrmb(FormFile drmb) {
		this.drmb = drmb;
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
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepathҪ���õ� filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the zhpxf
	 */
	public String getZhpxf() {
		return zhpxf;
	}
	/**
	 * @param zhpxfҪ���õ� zhpxf
	 */
	public void setZhpxf(String zhpxf) {
		this.zhpxf = zhpxf;
	}
	/**
	 * @return the pjf
	 */
	public String getPjf() {
		return pjf;
	}
	/**
	 * @param pjfҪ���õ� pjf
	 */
	public void setPjf(String pjf) {
		this.pjf = pjf;
	}
	/**
	 * @return the zhszf
	 */
	public String getZhszf() {
		return zhszf;
	}
	/**
	 * @param zhszfҪ���õ� zhszf
	 */
	public void setZhszf(String zhszf) {
		this.zhszf = zhszf;
	}
	/**
	 * @return the zhszfpm
	 */
	public String getZhszfpm() {
		return zhszfpm;
	}
	/**
	 * @param zhszfpmҪ���õ� zhszfpm
	 */
	public void setZhszfpm(String zhszfpm) {
		this.zhszfpm = zhszfpm;
	}
	/**
	 * @return the drsj
	 */
	public String getDrsj() {
		return drsj;
	}
	/**
	 * @param drsjҪ���õ� drsj
	 */
	public void setDrsj(String drsj) {
		this.drsj = drsj;
	}
	  
	  
	  
}
