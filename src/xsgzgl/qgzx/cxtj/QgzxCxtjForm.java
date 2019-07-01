package xsgzgl.qgzx.cxtj;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.comm.form.CommForm;
/**
 * 勤工助学-查询统计-酬金统计导出
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxCxtjForm extends CommForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String pkValue;//主键
	private String zgzt;//在岗状态
	private String tjlx;//统计类型
	private String nd;//年度
	private String yf;//月份
	private String bmdm;//部门代码
	private String gwmc;//岗位名称
	public String getGwmc() {
		return gwmc;
	}
	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}
	
	private String query;//查询条件
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String[] ffyf;// 发放月份
	
	private String[] ffnd;// 发放年度
	
	private String[] njs;//项目类型
	private String[] xydms; //学院代码
	private String[] zydms;//项目类型
	private String[] bjdms; //学院代码
	private String type;
	private String xh;
	private String shzt;

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	@Override
	public String getXh() {
		return xh;
	}

	@Override
	public void setXh(String xh) {
		this.xh = xh;
	}

	@Override
	public Pages getPages() {
		return pages;
	}

	@Override
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	@Override
	public SearchModel getSearchModel() {
		return searchModel;
	}

	@Override
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getZgzt() {
		return zgzt;
	}
	public void setZgzt(String zgzt) {
		this.zgzt = zgzt;
	}
	public String getTjlx() {
		return tjlx;
	}
	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String[] getFfyf() {
		return ffyf;
	}
	public void setFfyf(String[] ffyf) {
		this.ffyf = ffyf;
	}
	public String[] getFfnd() {
		return ffnd;
	}
	public void setFfnd(String[] ffnd) {
		this.ffnd = ffnd;
	}
	
	public String[] getNjs() {
		return njs;
	}
	public void setNjs(String[] njs) {
		this.njs = njs;
	}
	public String[] getXydms() {
		return xydms;
	}
	public void setXydms(String[] xydms) {
		this.xydms = xydms;
	}
	public String[] getZydms() {
		return zydms;
	}
	public void setZydms(String[] zydms) {
		this.zydms = zydms;
	}
	public String[] getBjdms() {
		return bjdms;
	}
	public void setBjdms(String[] bjdms) {
		this.bjdms = bjdms;
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
	
	
	
}
