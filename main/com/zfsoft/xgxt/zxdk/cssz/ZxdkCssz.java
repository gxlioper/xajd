/**
 * @部门:学工产品事业部
 * @日期：2014-9-24 上午09:47:46 
 */  
package com.zfsoft.xgxt.zxdk.cssz;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 助学贷款-参数设置
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-9-24 上午09:47:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxdkCssz extends ActionForm{

	private static final long serialVersionUID = -4739399939634987614L;

	private String id;
	private String xydkg;
	private String xydkssj;
	private String xydjssj;
	private String xydshlc;
	private String xdkg;
	private String xdkssj;
	private String xdjssj;
	
	
	private String sydkg;
	private String sydkssj;
	private String sydjssj;
	private String sydkshlc;
	private String sydkxn;
	
	
	
	private String xydKg;
	private String sydKg;
	private String xdKg;
	private String dkzesx;//贷款总额上限
	private String sfwcjtdc;//是否完成家庭情况调查
	
	private String tqhkKg;
	private String tqhkkg;
	private String tqhkkssj;
	private String tqhkjssj;
	private String tqhksplc;
	/*
	 * 新增字段：续贷审核流程*/
	private String xydxdshlc;
	
	
	/**
	 * @return the xydxdshlc
	 */
	public String getXydxdshlc() {
		return xydxdshlc;
	}
	/**
	 * @param xydxdshlc要设置的 xydxdshlc
	 */
	public void setXydxdshlc(String xydxdshlc) {
		this.xydxdshlc = xydxdshlc;
	}
	/**
	 * @return the tqhkKg
	 */
	public String getTqhkKg() {
		return tqhkKg;
	}
	/**
	 * @param tqhkKg要设置的 tqhkKg
	 */
	public void setTqhkKg(String tqhkKg) {
		this.tqhkKg = tqhkKg;
	}
	/**
	 * @return the tqhksplc
	 */
	public String getTqhksplc() {
		return tqhksplc;
	}
	/**
	 * @param tqhksplc要设置的 tqhksplc
	 */
	public void setTqhksplc(String tqhksplc) {
		this.tqhksplc = tqhksplc;
	}
	/**
	 * @return the tqhkkg
	 */
	public String getTqhkkg() {
		return tqhkkg;
	}
	/**
	 * @param tqhkkg要设置的 tqhkkg
	 */
	public void setTqhkkg(String tqhkkg) {
		this.tqhkkg = tqhkkg;
	}
	/**
	 * @return the tqhkkssj
	 */
	public String getTqhkkssj() {
		return tqhkkssj;
	}
	/**
	 * @param tqhkkssj要设置的 tqhkkssj
	 */
	public void setTqhkkssj(String tqhkkssj) {
		this.tqhkkssj = tqhkkssj;
	}
	/**
	 * @return the tqhkjssj
	 */
	public String getTqhkjssj() {
		return tqhkjssj;
	}
	/**
	 * @param tqhkjssj要设置的 tqhkjssj
	 */
	public void setTqhkjssj(String tqhkjssj) {
		this.tqhkjssj = tqhkjssj;
	}
	/**
	 * @return the xydKg
	 */
	public String getXydKg() {
		return xydKg;
	}
	/**
	 * @param xydKg要设置的 xydKg
	 */
	public void setXydKg(String xydKg) {
		this.xydKg = xydKg;
	}
	/**
	 * @return the sydKg
	 */
	public String getSydKg() {
		return sydKg;
	}
	/**
	 * @param sydKg要设置的 sydKg
	 */
	public void setSydKg(String sydKg) {
		this.sydKg = sydKg;
	}
	/**
	 * @return the xdKg
	 */
	public String getXdKg() {
		return xdKg;
	}
	/**
	 * @param xdKg要设置的 xdKg
	 */
	public void setXdKg(String xdKg) {
		this.xdKg = xdKg;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the xydkg
	 */
	public String getXydkg() {
		return xydkg;
	}
	/**
	 * @param xydkg要设置的 xydkg
	 */
	public void setXydkg(String xydkg) {
		this.xydkg = xydkg;
	}
	/**
	 * @return the xydkssj
	 */
	public String getXydkssj() {
		return xydkssj;
	}
	/**
	 * @param xydkssj要设置的 xydkssj
	 */
	public void setXydkssj(String xydkssj) {
		this.xydkssj = xydkssj;
	}
	/**
	 * @return the xydjssj
	 */
	public String getXydjssj() {
		return xydjssj;
	}
	/**
	 * @param xydjssj要设置的 xydjssj
	 */
	public void setXydjssj(String xydjssj) {
		this.xydjssj = xydjssj;
	}
	/**
	 * @return the xydshlc
	 */
	public String getXydshlc() {
		return xydshlc;
	}
	/**
	 * @param xydshlc要设置的 xydshlc
	 */
	public void setXydshlc(String xydshlc) {
		this.xydshlc = xydshlc;
	}
	/**
	 * @return the xdkg
	 */
	public String getXdkg() {
		return xdkg;
	}
	/**
	 * @param xdkg要设置的 xdkg
	 */
	public void setXdkg(String xdkg) {
		this.xdkg = xdkg;
	}
	/**
	 * @return the xdkssj
	 */
	public String getXdkssj() {
		return xdkssj;
	}
	/**
	 * @param xdkssj要设置的 xdkssj
	 */
	public void setXdkssj(String xdkssj) {
		this.xdkssj = xdkssj;
	}
	/**
	 * @return the xdjssj
	 */
	public String getXdjssj() {
		return xdjssj;
	}
	/**
	 * @param xdjssj要设置的 xdjssj
	 */
	public void setXdjssj(String xdjssj) {
		this.xdjssj = xdjssj;
	}
	/**
	 * @return the sydkg
	 */
	public String getSydkg() {
		return sydkg;
	}
	/**
	 * @param sydkg要设置的 sydkg
	 */
	public void setSydkg(String sydkg) {
		this.sydkg = sydkg;
	}
	/**
	 * @return the sydkssj
	 */
	public String getSydkssj() {
		return sydkssj;
	}
	/**
	 * @param sydkssj要设置的 sydkssj
	 */
	public void setSydkssj(String sydkssj) {
		this.sydkssj = sydkssj;
	}
	/**
	 * @return the sydjssj
	 */
	public String getSydjssj() {
		return sydjssj;
	}
	/**
	 * @param sydjssj要设置的 sydjssj
	 */
	public void setSydjssj(String sydjssj) {
		this.sydjssj = sydjssj;
	}
	/**
	 * @return the dkzesx
	 */
	public String getDkzesx() {
		return dkzesx;
	}
	/**
	 * @param dkzesx要设置的 dkzesx
	 */
	public void setDkzesx(String dkzesx) {
		this.dkzesx = dkzesx;
	}
	/**
	 * @return the sfwcjtdc
	 */
	public String getSfwcjtdc() {
		return sfwcjtdc;
	}
	/**
	 * @param sfwcjtdc要设置的 sfwcjtdc
	 */
	public void setSfwcjtdc(String sfwcjtdc) {
		this.sfwcjtdc = sfwcjtdc;
	}
	public String getSydkshlc() {
		return sydkshlc;
	}
	public void setSydkshlc(String sydkshlc) {
		this.sydkshlc = sydkshlc;
	}
	public String getSydkxn() {
		return sydkxn;
	}
	public void setSydkxn(String sydkxn) {
		this.sydkxn = sydkxn;
	}
	
	
	
}
