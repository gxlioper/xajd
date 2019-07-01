/**
 * @部门:学工产品事业部
 * @日期：2013-11-22 上午10:31:30 
 */
package xsgzgl.xtwh.general.news;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.base.util.SearchUtil;

import xgxt.comm.search.SearchModel;
import xgxt.utils.String.StringUtils;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 新闻权限范围
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-11-22 上午10:31:30
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class NewQxfwForm extends ActionForm {
	private String qxfwid;
	private String newsid;
	private String mhcx_lx;
	private String searchTj;
	private String searchTjz;
	private String searchLx;
	
	private Map<String,List<String>> seletTj;
	public void setNewQxfwFormForRequest(HttpServletRequest request) {
		this.setMhcx_lx(request.getParameter("mhcx_lx"));
		this.setSearchTj(request.getParameter("searchTj"));
		this.setSearchTjz(request.getParameter("searchTjz"));
		this.setSearchLx(request.getParameter("searchLx"));
	}
	/**
	 * 
	 * @描述:获取对应的SearchModel
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-23 下午06:25:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * SearchModel 返回类型 
	 */
	public SearchModel getSearchModel(){
		if(StringUtils.isNull(this.getNewsid())||StringUtils.isNull(this.getQxfwid())){
			return null;
		}
		SearchModel sm=SearchUtil.getSearchModel(null, this.mhcx_lx, this.searchLx, this.searchTj, this.searchTjz);
		sm.setPath("newselect.do");
		return sm;
	}
	/**
	 * @return the qxfwid
	 */
	public String getQxfwid() {
		return qxfwid;
	}

	/**
	 * @param qxfwid要设置的
	 *            qxfwid
	 */
	public void setQxfwid(String qxfwid) {
		this.qxfwid = qxfwid;
	}

	/**
	 * @return the newsid
	 */
	public String getNewsid() {
		return newsid;
	}

	/**
	 * @param newsid要设置的
	 *            newsid
	 */
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}

	/**
	 * @return the mhcx_lx
	 */
	public String getMhcx_lx() {
		return mhcx_lx;
	}

	/**
	 * @param mhcxLx要设置的
	 *            mhcx_lx
	 */
	public void setMhcx_lx(String mhcxLx) {
		mhcx_lx = mhcxLx;
	}

	/**
	 * @return the searchTj
	 */
	public String getSearchTj() {
		return searchTj;
	}

	/**
	 * @param searchTj要设置的
	 *            searchTj
	 */
	public void setSearchTj(String searchTj) {
		this.searchTj = searchTj;
	}

	/**
	 * @return the searchTjz
	 */
	public String getSearchTjz() {
		return searchTjz;
	}

	/**
	 * @param searchTjz要设置的
	 *            searchTjz
	 */
	public void setSearchTjz(String searchTjz) {
		this.searchTjz = searchTjz;
	}

	/**
	 * @return the searchLx
	 */
	public String getSearchLx() {
		return searchLx;
	}

	/**
	 * @param searchLx要设置的
	 *            searchLx
	 */
	public void setSearchLx(String searchLx) {
		this.searchLx = searchLx;
	}
	/**
	 * @return the seletTj
	 */
	public Map<String, List<String>> getSeletTj() {
		return seletTj;
	}
	/**
	 * @param seletTj要设置的 seletTj
	 */
	public void setSeletTj(Map<String,List<String>> seletTj) {
		this.seletTj = seletTj;
	}
	public boolean isNull(){
		if(StringUtils.isNull(this.getNewsid())||StringUtils.isNull(this.getQxfwid())){
			return true;
		}
		return false;
	}
}
