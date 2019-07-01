package xsgzgl.gygl.ldgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class LdglForm extends ActionForm {

	
	private static final long serialVersionUID = 1L;

	// 分页
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();
	
	private String[] checkVal;

	private String lddm;//楼栋代码
	private String ldmc;//楼栋名称
	private String ldxb;//楼栋性别
	private String ldcs;//楼栋层数
	private String qsch;//起始层号
	private String sfhlc;//是否含0层
	private String xqdm;//校区代码
	private String yqdm;//园区代码
	private String bz;//备注
	
	private String yhm;		//用户名
	private String xm;		//姓名
	private String xb;		//性别
	private String bmdm;	//部门代码
	private String zwdm;	//职务代码
	
	private String type;//类型，用于查询判断
	private String sffp;//是否分配
	private String[] yhms;

	private String originalLdcs;	//原始楼栋层数
	private String sfkzjlc;	//是否可增加楼层，1：是
	
	/**
	 * @return the yhms
	 */
	public String[] getYhms() {
		return yhms;
	}
	/**
	 * @param yhms要设置的 yhms
	 */
	public void setYhms(String[] yhms) {
		this.yhms = yhms;
	}
	/**
	 * @return pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the sffp
	 */
	public String getSffp() {
		return sffp;
	}
	/**
	 * @param sffp要设置的 sffp
	 */
	public void setSffp(String sffp) {
		this.sffp = sffp;
	}
	/**
	 * @param pages 要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModel 要设置的 searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddm 要设置的 lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @return ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmc 要设置的 ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	/**
	 * @return ldxb
	 */
	public String getLdxb() {
		return ldxb;
	}
	/**
	 * @param ldxb 要设置的 ldxb
	 */
	public void setLdxb(String ldxb) {
		this.ldxb = ldxb;
	}
	/**
	 * @return ldcs
	 */
	public String getLdcs() {
		return ldcs;
	}
	/**
	 * @param ldcs 要设置的 ldcs
	 */
	public void setLdcs(String ldcs) {
		this.ldcs = ldcs;
	}
	/**
	 * @return qsch
	 */
	public String getQsch() {
		return qsch;
	}
	/**
	 * @param qsch 要设置的 qsch
	 */
	public void setQsch(String qsch) {
		this.qsch = qsch;
	}
	/**
	 * @return sfhlc
	 */
	public String getSfhlc() {
		return sfhlc;
	}
	/**
	 * @param sfhlc 要设置的 sfhlc
	 */
	public void setSfhlc(String sfhlc) {
		this.sfhlc = sfhlc;
	}
	/**
	 * @return xqdm
	 */
	public String getXqdm() {
		return xqdm;
	}
	/**
	 * @param xqdm 要设置的 xqdm
	 */
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	/**
	 * @return yqdm
	 */
	public String getYqdm() {
		return yqdm;
	}
	/**
	 * @param yqdm 要设置的 yqdm
	 */
	public void setYqdm(String yqdm) {
		this.yqdm = yqdm;
	}
	/**
	 * @return bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz 要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return checkVal
	 */
	public String[] getCheckVal() {
		return checkVal;
	}
	/**
	 * @param checkVal 要设置的 checkVal
	 */
	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getZwdm() {
		return zwdm;
	}
	public void setZwdm(String zwdm) {
		this.zwdm = zwdm;
	}

	public String getOriginalLdcs() {
		return originalLdcs;
	}

	public void setOriginalLdcs(String originalLdcs) {
		this.originalLdcs = originalLdcs;
	}

	public String getSfkzjlc() {
		return sfkzjlc;
	}

	public void setSfkzjlc(String sfkzjlc) {
		this.sfkzjlc = sfkzjlc;
	}
}
