/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����02:25:20 
 */  
package xsgzgl.szdw.jtff.jtff;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2016-3-8 ����02:25:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JtffForm extends ActionForm {
	  private String zgh;
	  private String ffny;
	  private String jsje;
	  private String ffje;
	  private String bz;
	  private String gw;
	  private String dbrs;
	  private String id;
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
	private static final long serialVersionUID = 1L;
	  private SearchModel searchModel = new SearchModel();
	  private String type;
	  //����
	  private ExportModel exportModel = new ExportModel();
	  private Pages pages = new Pages();
	  /**
	 * @return the gw
	 */
	public String getGw() {
		return gw;
	}
	/**
	 * @param gwҪ���õ� gw
	 */
	public void setGw(String gw) {
		this.gw = gw;
	}
	/**
	 * @return the dbrs
	 */
	public String getDbrs() {
		return dbrs;
	}
	/**
	 * @param dbrsҪ���õ� dbrs
	 */
	public void setDbrs(String dbrs) {
		this.dbrs = dbrs;
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
	 * @return the ffny
	 */
	public String getFfny() {
		return ffny;
	}
	/**
	 * @param ffnyҪ���õ� ffny
	 */
	public void setFfny(String ffny) {
		this.ffny = ffny;
	}
	/**
	 * @return the jsje
	 */
	public String getJsje() {
		return jsje;
	}
	/**
	 * @param jsjeҪ���õ� jsje
	 */
	public void setJsje(String jsje) {
		this.jsje = jsje;
	}
	/**
	 * @return the ffje
	 */
	public String getFfje() {
		return ffje;
	}
	/**
	 * @param ffjeҪ���õ� ffje
	 */
	public void setFfje(String ffje) {
		this.ffje = ffje;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
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
	
}
