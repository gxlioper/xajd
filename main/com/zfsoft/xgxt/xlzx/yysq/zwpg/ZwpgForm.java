/**
 * @部门:学工产品事业部
 * @日期：2018-3-6 下午05:03:53 
 */  
package com.zfsoft.xgxt.xlzx.yysq.zwpg;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-3-6 下午05:03:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwpgForm  extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String id ;
	private String xh ;
	private String zxjy ;//有无心理咨询经验
	private String jsyw ;//曾经或现在是否服用精神药物
	private String rybs ;//我最近会容易悲伤哭泣
	private String smbh ;//我最近睡的变少或变多
	private String sqqq ;//我最近对很多事情失去情趣
	private String cdxbh ;//我最近吃的东西变多或变少
	private String zeg ;//我最近常有罪恶感
	private String stwl ;//我觉得身体无力
	private String zwpp ;//我最近容易自我批评
	private String zwjz ;//我觉得自己没有价值
	private String rypy ;//我最近变得容易疲倦
	private String zsnt ;//我有想自杀的念头
	private String zxzt ;//咨询主题
	private String qt ;//其他
	private String sj ;//时间
	
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
	 * @return the zxjy
	 */
	public String getZxjy() {
		return zxjy;
	}
	/**
	 * @param zxjy要设置的 zxjy
	 */
	public void setZxjy(String zxjy) {
		this.zxjy = zxjy;
	}
	/**
	 * @return the jsyw
	 */
	public String getJsyw() {
		return jsyw;
	}
	/**
	 * @param jsyw要设置的 jsyw
	 */
	public void setJsyw(String jsyw) {
		this.jsyw = jsyw;
	}
	/**
	 * @return the rybs
	 */
	public String getRybs() {
		return rybs;
	}
	/**
	 * @param rybs要设置的 rybs
	 */
	public void setRybs(String rybs) {
		this.rybs = rybs;
	}
	/**
	 * @return the smbh
	 */
	public String getSmbh() {
		return smbh;
	}
	/**
	 * @param smbh要设置的 smbh
	 */
	public void setSmbh(String smbh) {
		this.smbh = smbh;
	}
	/**
	 * @return the sqqq
	 */
	public String getSqqq() {
		return sqqq;
	}
	/**
	 * @param sqqq要设置的 sqqq
	 */
	public void setSqqq(String sqqq) {
		this.sqqq = sqqq;
	}
	/**
	 * @return the cdxbh
	 */
	public String getCdxbh() {
		return cdxbh;
	}
	/**
	 * @param cdxbh要设置的 cdxbh
	 */
	public void setCdxbh(String cdxbh) {
		this.cdxbh = cdxbh;
	}
	/**
	 * @return the zeg
	 */
	public String getZeg() {
		return zeg;
	}
	/**
	 * @param zeg要设置的 zeg
	 */
	public void setZeg(String zeg) {
		this.zeg = zeg;
	}
	/**
	 * @return the stwl
	 */
	public String getStwl() {
		return stwl;
	}
	/**
	 * @param stwl要设置的 stwl
	 */
	public void setStwl(String stwl) {
		this.stwl = stwl;
	}
	/**
	 * @return the zwpp
	 */
	public String getZwpp() {
		return zwpp;
	}
	/**
	 * @param zwpp要设置的 zwpp
	 */
	public void setZwpp(String zwpp) {
		this.zwpp = zwpp;
	}
	/**
	 * @return the zwjz
	 */
	public String getZwjz() {
		return zwjz;
	}
	/**
	 * @param zwjz要设置的 zwjz
	 */
	public void setZwjz(String zwjz) {
		this.zwjz = zwjz;
	}
	/**
	 * @return the rypy
	 */
	public String getRypy() {
		return rypy;
	}
	/**
	 * @param rypy要设置的 rypy
	 */
	public void setRypy(String rypy) {
		this.rypy = rypy;
	}
	/**
	 * @return the zsnt
	 */
	public String getZsnt() {
		return zsnt;
	}
	/**
	 * @param zsnt要设置的 zsnt
	 */
	public void setZsnt(String zsnt) {
		this.zsnt = zsnt;
	}
	/**
	 * @return the zxzt
	 */
	public String getZxzt() {
		return zxzt;
	}
	/**
	 * @param zxzt要设置的 zxzt
	 */
	public void setZxzt(String zxzt) {
		this.zxzt = zxzt;
	}
	/**
	 * @return the qt
	 */
	public String getQt() {
		return qt;
	}
	/**
	 * @param qt要设置的 qt
	 */
	public void setQt(String qt) {
		this.qt = qt;
	}
	
}
