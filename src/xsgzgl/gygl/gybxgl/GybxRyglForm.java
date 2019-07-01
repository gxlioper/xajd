/**
 * @部门:学工产品事业部
 * @日期：2015-7-8 下午02:52:39 
 */
package xsgzgl.gygl.gybxgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 公寓报修人员维护
 * @作者：ChenQ[工号:856]
 * @时间： 2015-7-8 下午02:52:39
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class GybxRyglForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String bxlb;
	private String bxlbmc;
	private String zgh;
	private String flag;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private String[] yhAll;
	
	public String[] getYhAll() {
		return yhAll;
	}

	public void setYhAll(String[] yhAll) {
		this.yhAll = yhAll;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBxlb() {
		return bxlb;
	}

	public void setBxlb(String bxlb) {
		this.bxlb = bxlb;
	}

	public String getBxlbmc() {
		return bxlbmc;
	}

	public void setBxlbmc(String bxlbmc) {
		this.bxlbmc = bxlbmc;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
