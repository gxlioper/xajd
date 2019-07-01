/**
 * @部门:学工产品事业部
 * @日期：2016-3-9 上午09:31:24 
 */  
package xsgzgl.szdw.jtff.util;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2016-3-9 上午09:31:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
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
	 * @param pages要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @param type要设置的 type
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
	 * @param id要设置的 id
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
	 * @param gw要设置的 gw
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
	 * @param bz要设置的 bz
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
	 * @param gdffje要设置的 gdffje
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
	 * @param zgh要设置的 zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	/**
	 * @param searchModel要设置的 searchModel
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
	 * @param jtlb要设置的 jtlb
	 */
	public void setJtlb(String jtlb) {
		this.jtlb = jtlb;
	}
	
}
