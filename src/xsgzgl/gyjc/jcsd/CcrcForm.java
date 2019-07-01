/**
 * @部门:学工产品事业部
 * @日期：2018年5月22日 下午5:53:04 
 */  
package xsgzgl.gyjc.jcsd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018年5月22日 下午5:53:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CcrcForm  extends ActionForm{
	private String ccid;
	private String jcrq;
	private String xn;
	private String xqdm;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String lddm;
	private String qshs;
	private String lddms;
	private String xb;
	private String ldmc;
	private String bmmc;
	private String ch;
	private String qsh;
	private String zgh;
	private String xm;
	private String sffp;
	private String xh;
	private String qsnum;
	private String xgzgh;
	private String[] lds;
	private String[] qss;
	/**
	 * @return the jcxy
	 */
	public String getJcxy() {
		return jcxy;
	}
	/**
	 * @param jcxy要设置的 jcxy
	 */
	public void setJcxy(String jcxy) {
		this.jcxy = jcxy;
	}
	/**
	 * @return the wtjs
	 */
	public String getWtjs() {
		return wtjs;
	}
	/**
	 * @param wtjs要设置的 wtjs
	 */
	public void setWtjs(String wtjs) {
		this.wtjs = wtjs;
	}
	/**
	 * @return the ytjs
	 */
	public String getYtjs() {
		return ytjs;
	}
	/**
	 * @param ytjs要设置的 ytjs
	 */
	public void setYtjs(String ytjs) {
		this.ytjs = ytjs;
	}
	private String[] dels;
	private String jcxy;
	private String wtjs;
	private String ytjs;
	
	/**
	 * @return the dels
	 */
	public String[] getDels() {
		return dels;
	}
	/**
	 * @param dels要设置的 dels
	 */
	public void setDels(String[] dels) {
		this.dels = dels;
	}
	/**
	 * @return the lds
	 */
	public String[] getLds() {
		return lds;
	}
	/**
	 * @param lds要设置的 lds
	 */
	public void setLds(String[] lds) {
		this.lds = lds;
	}
	/**
	 * @return the qss
	 */
	public String[] getQss() {
		return qss;
	}
	/**
	 * @param qss要设置的 qss
	 */
	public void setQss(String[] qss) {
		this.qss = qss;
	}
	/**
	 * @return the xgzgh
	 */
	public String getXgzgh() {
		return xgzgh;
	}
	/**
	 * @param xgzgh要设置的 xgzgh
	 */
	public void setXgzgh(String xgzgh) {
		this.xgzgh = xgzgh;
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
	 * @return the qsnum
	 */
	public String getQsnum() {
		return qsnum;
	}
	/**
	 * @param qsnum要设置的 qsnum
	 */
	public void setQsnum(String qsnum) {
		this.qsnum = qsnum;
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
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}
	/**
	 * @param xb要设置的 xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}

	/**
	 * @return the lddms
	 */
	public String getLddms() {
		return lddms;
	}
	/**
	 * @param lddms要设置的 lddms
	 */
	public void setLddms(String lddms) {
		this.lddms = lddms;
	}
	/**
	 * @return the qshs
	 */
	public String getQshs() {
		return qshs;
	}
	/**
	 * @param qshs要设置的 qshs
	 */
	public void setQshs(String qshs) {
		this.qshs = qshs;
	}
	/**
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddm要设置的 lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	/**
	 * @return the ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmc要设置的 ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	/**
	 * @return the bmmc
	 */
	public String getBmmc() {
		return bmmc;
	}
	/**
	 * @param bmmc要设置的 bmmc
	 */
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	/**
	 * @return the ch
	 */
	public String getCh() {
		return ch;
	}
	/**
	 * @param ch要设置的 ch
	 */
	public void setCh(String ch) {
		this.ch = ch;
	}
	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @param qsh要设置的 qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
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
	 * @return the ccid
	 */
	public String getCcid() {
		return ccid;
	}
	/**
	 * @param ccid要设置的 ccid
	 */
	public void setCcid(String ccid) {
		this.ccid = ccid;
	}
	/**
	 * @return the jcrq
	 */
	public String getJcrq() {
		return jcrq;
	}
	/**
	 * @param jcrq要设置的 jcrq
	 */
	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
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
	 * @return the xqdm
	 */
	public String getXqdm() {
		return xqdm;
	}
	/**
	 * @param xqdm要设置的 xqdm
	 */
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
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

}
