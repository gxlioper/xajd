package xsgzgl.gygl.ntzd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * 南通职大-月考核得分(班级/学院)
 * @作者： qilm
 * @时间： 2013-9-25
 * @版本： V1.0
 */
public class YdfForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	// 分页
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();
	
	private String[] checkVal;
	private String ny;		//年月
	private String bjdm;	//班级代码
	private String zydm;	//专业代码
	private String xydm;	//学院代码
	private String nj;		//年级
	private String ykhdf;	//月考核得分
	private String xypm;	//学院排名
	private String xxpm;	//学校排名
	private String bjmc;	//班级名称
	private String zymc;	//专业名称
	private String xymc;	//学院名称
	private String xybjs;	//学院班级数
	private String xxbjs;	//学校班级数
	private String xn;		//学年
	private String xq;		//学期
	private String xyxys;	//学校学院数
	
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
	public String[] getCheckVal() {
		return checkVal;
	}
	
	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}
	public String getNy() {
		return ny;
	}
	public void setNy(String ny) {
		this.ny = ny;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getYkhdf() {
		return ykhdf;
	}
	public void setYkhdf(String ykhdf) {
		this.ykhdf = ykhdf;
	}
	public String getXypm() {
		return xypm;
	}
	public void setXypm(String xypm) {
		this.xypm = xypm;
	}
	public String getXxpm() {
		return xxpm;
	}
	public void setXxpm(String xxpm) {
		this.xxpm = xxpm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getXybjs() {
		return xybjs;
	}
	public void setXybjs(String xybjs) {
		this.xybjs = xybjs;
	}
	public String getXxbjs() {
		return xxbjs;
	}
	public void setXxbjs(String xxbjs) {
		this.xxbjs = xxbjs;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXyxys() {
		return xyxys;
	}
	public void setXyxys(String xyxys) {
		this.xyxys = xyxys;
	}
}
