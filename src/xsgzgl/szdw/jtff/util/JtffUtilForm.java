/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-9 ����09:31:24 
 */  
package xsgzgl.szdw.jtff.util;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2016-3-9 ����09:31:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JtffUtilForm extends ActionForm {
	private SearchModel searchModel = new SearchModel();
	private String jtlb;
    private String id;
    private String zgh;
    private String gw;
    private String bz;
    private String gdffje;
    private String type;
    private Pages pages = new Pages();
	 /**
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
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
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @param searchModelҪ���õ� searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
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
	
}
