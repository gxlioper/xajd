/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-5 ����09:58:45 
 */  
package xsgzgl.rcsw.wsbz.yy;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-5-5 ����09:58:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsbzYyForm extends ActionForm {
	  private String sqid;
	  private String xh;
	  private String fddm;
	  private String yyrq;
	  private String sqsj;
	  private String sqly;
	  private String yyrqday;
	  private String yyrqzc;
	  private String yyzc;
	  private SearchModel searchModel = new SearchModel();
	  private ExportModel exportModel = new ExportModel();
	  /**
	 * @return the yyzc
	 */
	public String getYyzc() {
		return yyzc;
	}
	/**
	 * @param yyzcҪ���õ� yyzc
	 */
	public void setYyzc(String yyzc) {
		this.yyzc = yyzc;
	}
	
	  /**
	 * @return the yyrqday
	 */
	public String getYyrqday() {
		return yyrqday;
	}
	/**
	 * @param yyrqdayҪ���õ� yyrqday
	 */
	public void setYyrqday(String yyrqday) {
		this.yyrqday = yyrqday;
	}
	/**
	 * @return the yyrqzc
	 */
	public String getYyrqzc() {
		return yyrqzc;
	}
	/**
	 * @param yyrqzcҪ���õ� yyrqzc
	 */
	public void setYyrqzc(String yyrqzc) {
		this.yyrqzc = yyrqzc;
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
	private String type;
	  private Pages pages = new Pages();
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
	 * @return the fddm
	 */
	public String getFddm() {
		return fddm;
	}
	/**
	 * @param fddmҪ���õ� fddm
	 */
	public void setFddm(String fddm) {
		this.fddm = fddm;
	}
	/**
	 * @return the yyrq
	 */
	public String getYyrq() {
		return yyrq;
	}
	/**
	 * @param yyrqҪ���õ� yyrq
	 */
	public void setYyrq(String yyrq) {
		this.yyrq = yyrq;
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
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqlyҪ���õ� sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
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
