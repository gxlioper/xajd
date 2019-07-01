package xsgzgl.gygl.rcjc.xswsjc;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @系统名称：学生工作管理系统
 * @模块名称：勤工助学-津贴发放
 * @类功能描述：
 * @作者：卓耐[工号:1391]
 * @时间：2017年5月11日
 * @版本：V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XswsjcForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel(); //高级查询
	private Pages pages = new Pages(); // 分页
	private ExportModel exportModel = new ExportModel(); //自定义导出
	
	private String jcrcid; //检查日程ID
	private String xn;
	private String xq;
	private String xh;
	private String fs; //分数
	private String djbz;
	
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModel要设置的 searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
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
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @return the jcrcid
	 */
	public String getJcrcid() {
		return jcrcid;
	}
	/**
	 * @param jcrcid要设置的 jcrcid
	 */
	public void setJcrcid(String jcrcid) {
		this.jcrcid = jcrcid;
	}
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh要设置的 xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the fs
	 */
	public String getFs() {
		return fs;
	}
	/**
	 * @param fs要设置的 fs
	 */
	public void setFs(String fs) {
		this.fs = fs;
	}
	/**
	 * @return the djbz
	 */
	public String getDjbz() {
		return djbz;
	}
	/**
	 * @param djbz要设置的 djbz
	 */
	public void setDjbz(String djbz) {
		this.djbz = djbz;
	}
	
	
	
}
