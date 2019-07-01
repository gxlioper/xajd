/**
 * @部门:学工产品事业部
 * @日期：2016-5-5 上午09:55:45 
 */  
package xsgzgl.rcsw.wsbz.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-5-5 上午09:55:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsbzDmwhForm extends ActionForm {
	private String fddm;
	private String fdmc;
	private String hdpl;
	private String sj;
	private String dd;
	private String rs;
	private String gzzz;
	private String fwyq;
	private String type;
	
	
	
	private String bmcs;//报名次数
	private String jzts;//截止天数
	private String jzsj;//截止时间
	/**
	 * @return the fddm
	 */
	public String getFddm() {
		return fddm;
	}
	/**
	 * @param fddm要设置的 fddm
	 */
	public void setFddm(String fddm) {
		this.fddm = fddm;
	}
	/**
	 * @return the fdmc
	 */
	public String getFdmc() {
		return fdmc;
	}
	/**
	 * @param fdmc要设置的 fdmc
	 */
	public void setFdmc(String fdmc) {
		this.fdmc = fdmc;
	}
	/**
	 * @return the hdpl
	 */
	public String getHdpl() {
		return hdpl;
	}
	/**
	 * @param hdpl要设置的 hdpl
	 */
	public void setHdpl(String hdpl) {
		this.hdpl = hdpl;
	}
	/**
	 * @return the sj
	 */
	public String getSj() {
		return sj;
	}
	/**
	 * @param sj要设置的 sj
	 */
	public void setSj(String sj) {
		this.sj = sj;
	}
	/**
	 * @return the dd
	 */
	public String getDd() {
		return dd;
	}
	/**
	 * @param dd要设置的 dd
	 */
	public void setDd(String dd) {
		this.dd = dd;
	}
	/**
	 * @return the rs
	 */
	public String getRs() {
		return rs;
	}
	/**
	 * @param rs要设置的 rs
	 */
	public void setRs(String rs) {
		this.rs = rs;
	}
	/**
	 * @return the gzzz
	 */
	public String getGzzz() {
		return gzzz;
	}
	/**
	 * @param gzzz要设置的 gzzz
	 */
	public void setGzzz(String gzzz) {
		this.gzzz = gzzz;
	}
	/**
	 * @return the fwyq
	 */
	public String getFwyq() {
		return fwyq;
	}
	/**
	 * @param fwyq要设置的 fwyq
	 */
	public void setFwyq(String fwyq) {
		this.fwyq = fwyq;
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
	private Pages pages = new Pages();
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-14 上午10:46:22 
	 * @return		: the bmcs
	 */
	public String getBmcs() {
		return bmcs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-14 上午10:46:22 
	 * @param 		：bmcs the bmcs to set
	 */
	public void setBmcs(String bmcs) {
		this.bmcs = bmcs;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-14 上午10:46:22 
	 * @return		: the jzts
	 */
	public String getJzts() {
		return jzts;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-14 上午10:46:22 
	 * @param 		：jzts the jzts to set
	 */
	public void setJzts(String jzts) {
		this.jzts = jzts;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-14 上午10:46:22 
	 * @return		: the jzsj
	 */
	public String getJzsj() {
		return jzsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-14 上午10:46:22 
	 * @param 		：jzsj the jzsj to set
	 */
	public void setJzsj(String jzsj) {
		this.jzsj = jzsj;
	}
	
	
}
