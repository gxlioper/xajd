/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-3 ����03:45:18 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.xsqjcx;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2016-3-3 ����03:45:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsqjcxForm extends ActionForm {
	 private String xh;
	 private String zgh;
	 private String type;
	 private String id;
	 private String actinstid;
	 private static final long serialVersionUID = 1L;
	 private SearchModel searchModel = new SearchModel();
	//����
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
	 private String flag;
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
		 * @return the serialversionuid
		 */
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		/**
		 * @return the actinstid
		 */
		public String getActinstid() {
			return actinstid;
		}
		/**
		 * @param actinstidҪ���õ� actinstid
		 */
		public void setActinstid(String actinstid) {
			this.actinstid = actinstid;
		}
	

}
