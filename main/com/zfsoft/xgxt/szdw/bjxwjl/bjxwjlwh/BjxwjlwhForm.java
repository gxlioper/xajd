/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-14 ����02:25:41 
 */  
package com.zfsoft.xgxt.szdw.bjxwjl.bjxwjlwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @�๦������: �༶��Ϣ��¼ά��
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-14 ����02:25:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjxwjlwhForm extends ActionForm {
	//
	public static class Record{
		public String ixndsdm;
		
		public String ijlsj;
		
		public String ijlnr;

		public String ibjdm;

		/**
		 * @���� ��TODO�����µ�ǰ���췽��
		 */
		public Record() {
			super();
		}

		/**
		 * @���� ��TODO�����µ�ǰ���췽��
		 * @param xndsdm
		 * @param jlsj
		 * @param jlnr
		 * @param bjdm
		 */
		public Record(String ixndsdm, String ijlsj, String ijlnr,
				String ibjdm) {
			super();
			this.ixndsdm = ixndsdm;
			this.ijlsj = ijlsj;
			this.ijlnr = ijlnr;
			this.ibjdm = ibjdm;
		}

		/**
		 * @return the _xndsdm
		 */
		public String getIxndsdm() {
			return ixndsdm;
		}

		/**
		 * @param xndsdmҪ���õ� _xndsdm
		 */
		public void setIxndsdm(String xndsdm) {
			ixndsdm = xndsdm;
		}

		/**
		 * @return the _jlsj
		 */
		public String getIjlsj() {
			return ijlsj;
		}

		/**
		 * @param jlsjҪ���õ� _jlsj
		 */
		public void setIjlsj(String jlsj) {
			ijlsj = jlsj;
		}

		/**
		 * @return the _jlnr
		 */
		public String getIjlnr() {
			return ijlnr;
		}

		/**
		 * @param jlnrҪ���õ� _jlnr
		 */
		public void setIjlnr(String jlnr) {
			ijlnr = jlnr;
		}

		/**
		 * @return the _bjdm
		 */
		public String getIbjdm() {
			return ibjdm;
		}

		/**
		 * @param bjdmҪ���õ� _bjdm
		 */
		public void setIbjdm(String bjdm) {
			ibjdm = bjdm;
		}
		 
		 
	}
	
	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	/** 
	 * @���� pages : ��ҳģ�� 
	 */
	private Pages pages = new Pages();
	
	/** 
	 * @���� searchModel : �߼���ѯģ�� 
	 */
	private SearchModel searchModel = new SearchModel();
	
	/** 
	 * @���� exportModel : ����ģ�� 
	 */
	private ExportModel exportModel = new ExportModel();
	
	private String guid;
	
	private String xn;
	
	private String xqdm;
	
	private String xndsdm;
	
	private String jlr;
	
	private String jlsj;
	
	private String jlnr;
	
	private String bjdm;

	private String type;
	
	private String jsondata;
	
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
	 * @return the xqdm
	 */
	public String getXqdm() {
		return xqdm;
	}

	/**
	 * @param xqdmҪ���õ� xqdm
	 */
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}

	/**
	 * @return the xndsdm
	 */
	public String getXndsdm() {
		return xndsdm;
	}

	/**
	 * @param xndsdmҪ���õ� xndsdm
	 */
	public void setXndsdm(String xndsdm) {
		this.xndsdm = xndsdm;
	}

	/**
	 * @return the jlr
	 */
	public String getJlr() {
		return jlr;
	}

	/**
	 * @param jlrҪ���õ� jlr
	 */
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}

	/**
	 * @return the jlsj
	 */
	public String getJlsj() {
		return jlsj;
	}

	/**
	 * @param jlsjҪ���õ� jlsj
	 */
	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}

	/**
	 * @return the jlnr
	 */
	public String getJlnr() {
		return jlnr;
	}

	/**
	 * @param jlnrҪ���õ� jlnr
	 */
	public void setJlnr(String jlnr) {
		this.jlnr = jlnr;
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
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * @param guidҪ���õ� guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * @return the jsondata
	 */
	public String getJsondata() {
		return jsondata;
	}

	/**
	 * @param jsondataҪ���õ� jsondata
	 */
	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}
	
}
