/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����02:27:06 
 */  
package xsgzgl.szdw.jtff.jtmdwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2016-3-8 ����02:27:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JtmdwhForm extends ActionForm {
	 private String id;
	 private String zgh;
	 private String gw;
	 private String jtlb;
	 private String bz;
	 private String gdffje;
	 private static final long serialVersionUID = 1L;
	 private SearchModel searchModel = new SearchModel();
	 private String type;
	//����
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
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
	 * @param idҪ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the jtlb
	 */
	public String getJtlb() {
		return jtlb;
	}
	/**
	 * @param jtlbҪ���õ� jtlb
	 */
	public void setJtlb(String jtlb) {
		this.jtlb = jtlb;
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
	 * @return the gdffje
	 */
	public String getGdffje() {
		return gdffje;
	}
	/**
	 * @param gdffjeҪ���õ� gdffje
	 */
	public void setGdffje(String gdffje) {
		this.gdffje = gdffje;
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
}
