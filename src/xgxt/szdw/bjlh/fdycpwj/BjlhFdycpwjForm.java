package xgxt.szdw.bjlh.fdycpwj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class BjlhFdycpwjForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String type;
	// 分页
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();
	
	//问卷管理部分
	private String xn;//学年
	private String wjid;//问卷id
	private String wjmc;//问卷名称
	private String sfqy;//是否启用
	private String fbrq;//发布日期
	private String fbr;//发布人
	
	//问卷试题维护
//	private String wjid;//问卷id
	private String stid;//试题id
	private String stmc;//试题名称
	private String stlx;//试题类型
	private String xssx;//显示顺序
	private String dhxxgs;//单行选项个数
	private String xxgs;//选项个数

	//试题选项维护
//	private String stid;//试题id
	private String xxid;//选项id
	private String xxnr;//选项内容
//	private String xssx;//显示顺序
	
	private String[] xxids;//选项id数组
	private String[] xxnrs;//选项内容数组
	
	//问卷答案
	private String xh;
//	private String wjid;//问卷id
//	private String stid;//试题id
//	private String xxid;//选项id
	private String wbda;//文本答案
//	private String djsj;//答卷时间 获取数据库默认时间
	private String fdyid;//辅导员id
	private String khbid;
	

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
	public String getWjid() {
		return wjid;
	}
	public void setWjid(String wjid) {
		this.wjid = wjid;
	}
	public String getWjmc() {
		return wjmc;
	}
	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	public String getFbrq() {
		return fbrq;
	}
	public void setFbrq(String fbrq) {
		this.fbrq = fbrq;
	}
	public String getFbr() {
		return fbr;
	}
	public void setFbr(String fbr) {
		this.fbr = fbr;
	}
	public String getStid() {
		return stid;
	}
	public void setStid(String stid) {
		this.stid = stid;
	}
	public String getStmc() {
		return stmc;
	}
	public void setStmc(String stmc) {
		this.stmc = stmc;
	}
	public String getStlx() {
		return stlx;
	}
	public void setStlx(String stlx) {
		this.stlx = stlx;
	}
	public String getXssx() {
		return xssx;
	}
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	public String getXxid() {
		return xxid;
	}
	public void setXxid(String xxid) {
		this.xxid = xxid;
	}
	public String getXxnr() {
		return xxnr;
	}
	public void setXxnr(String xxnr) {
		this.xxnr = xxnr;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getWbda() {
		return wbda;
	}
	public void setWbda(String wbda) {
		this.wbda = wbda;
	}
	public String[] getXxids() {
		return xxids;
	}
	public void setXxids(String[] xxids) {
		this.xxids = xxids;
	}
	public String[] getXxnrs() {
		return xxnrs;
	}
	public void setXxnrs(String[] xxnrs) {
		this.xxnrs = xxnrs;
	}
	public String getDhxxgs() {
		return dhxxgs;
	}
	public void setDhxxgs(String dhxxgs) {
		this.dhxxgs = dhxxgs;
	}
	public String getXxgs() {
		return xxgs;
	}
	public void setXxgs(String xxgs) {
		this.xxgs = xxgs;
	}
	public String getFdyid() {
		return fdyid;
	}
	public void setFdyid(String fdyid) {
		this.fdyid = fdyid;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getKhbid() {
		return khbid;
	}

	public void setKhbid(String khbid) {
		this.khbid = khbid;
	}

}
