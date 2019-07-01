package xgxt.pjpy.comm.pjpy;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.comm.xml.XMLReader;
import xgxt.utils.Pages;

public class PjpyCommForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	// 分页
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();

	FormFile uploadFile;// 上传文件

	// 复选框
	private String[] checkVal;

	private String jsonResult;

	// 综测周期
	private String zczq = XMLReader.getFlowControl("pjpy", "zczq");

	// 人员库
	private String ryk = XMLReader.getFlowControl("pjpy", "ryk");

	// 综测排名
	private String zcpm = XMLReader.getFlowControl("pjpy", "zcpm");
	
	// 智育分排名
	private String zypm = XMLReader.getFlowControl("pjpy", "zypm");
	
	private String nofy;// 不分页

	private String arrange;// 排序

	private String fashion;// 排序方式
	
	private String xh;// 学号

	private String xm;// 姓名

	private String xn;// 学年

	private String xq;// 学期

	private String nd;// 年度

	private String pjxn;// 评奖学年

	private String pjxq;// 评奖学期

	private String pjnd;// 评奖年度

	private String kssj;// 开始时间

	private String jssj;// 结束时间

	private String pjry_sql = " (select a.pjxn,"
			+ " a.pjxq,"
			+ " a.pjnd,"
			+ " a.xh,"
			+ " a.xm,"
			+ " a.xydm,"
			+ " a.zydm,"
			+ " a.bjdm,"
			+ " a.xb,"
			+ " b.xymc,"
			+ " c.zymc,"
			+ " d.bjmc,"
			+ " d.nj,"
			+ " a.sfysz"
			+ " from xg_pjpy_xsb a, xg_pjpy_xyb b, xg_pjpy_zyb c, xg_pjpy_bjb d"
			+ " where a.sfysz = '是'"
			+ " and a.xydm = b.xydm"
			+ " and a.zydm = c.zydm"
			+ " and a.bjdm = d.bjdm"
			+ " and a.pjxn = b.pjxn and a.pjxq = b.pjxq and a.pjnd = b.pjnd"
			+ " and a.pjxn = c.pjxn and a.pjxq = c.pjxq and a.pjnd = c.pjnd"
			+ " and a.pjxn = d.pjxn and a.pjxq = d.pjxq and a.pjnd = d.pjnd"
			+ " and a.pjxn = (select pjxn from xg_pjpy_xtszb where rownum = 1)"
			+ " and a.pjxq = (select pjxq from xg_pjpy_xtszb where rownum = 1)"
			+ " and a.pjnd = (select pjnd from xg_pjpy_xtszb where rownum = 1))";

	public String getArrange() {
		return arrange;
	}

	public void setArrange(String arrange) {
		this.arrange = arrange;
	}
	
	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getPjnd() {
		return pjnd;
	}

	public void setPjnd(String pjnd) {
		this.pjnd = pjnd;
	}

	public String getPjxn() {
		return pjxn;
	}

	public void setPjxn(String pjxn) {
		this.pjxn = pjxn;
	}

	public String getPjxq() {
		return pjxq;
	}

	public void setPjxq(String pjxq) {
		this.pjxq = pjxq;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
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

	public String getZczq() {
		return zczq;
	}

	public void setZczq(String zczq) {
		this.zczq = zczq;
	}

	public String getRyk() {
		return ryk;
	}

	public void setRyk(String ryk) {
		this.ryk = ryk;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getNofy() {
		return nofy;
	}

	public void setNofy(String nofy) {
		this.nofy = nofy;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public String getZcpm() {
		return zcpm;
	}

	public void setZcpm(String zcpm) {
		this.zcpm = zcpm;
	}

	public String getPjry_sql() {
		return pjry_sql;
	}

	public void setPjry_sql(String pjry_sql) {
		this.pjry_sql = pjry_sql;
	}
	
	public String getFashion() {
		return fashion;
	}

	public void setFashion(String fashion) {
		this.fashion = fashion;
	}

	public String getZypm() {
		return zypm;
	}

	public void setZypm(String zypm) {
		this.zypm = zypm;
	}
}
