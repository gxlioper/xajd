/**
 * @部门:学工产品事业部
 * @日期：2013-5-16 上午10:28:17 
 */  
package com.zfsoft.xgxt.szdw.sdkj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cmj 
 * @时间： 2013-5-16 上午10:28:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdlskhForm extends ActionForm {
private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String nf ;//考核年份
	private String yf ;//考核月份
	private String xb ;//系部
	private String zgh ;//职工号
	private String zdls ;//指导老师
	private String zrs ;//总人数
	private String zxrs ;//在校人数
	private String kfhj ;//扣分合计
	private String xfdf ;//校风得分
	private String jsjgjl ;//计算机过级率
	private String yygjl ;//英语过级率
	private String gycx ;//公寓秩序
	private String jyl ;//就业率
	private String xxj ;//信息机
	private String syldf ;//使用率得分
	private String tdjs ;//团队建设
	private String dkl ;//到课率
	private String dkldf ;//到课率得分
	private String zysyghhgl ;//职业生涯规划合格率
	private String zysyghdf ;//职业生涯规划得分
	private String jszdsc ;//教师指导手册
	private String xsls ;//学生流失
	private String xszl ;//学生专利
	private String aq ;//安全
	private String dshj ;//大赛获奖
	private String df ;//得分
	private String xzhdf ;//修正后得分
	
	
	/**
	 * @return the nf
	 */
	public String getNf() {
		return nf;
	}
	/**
	 * @param nf要设置的 nf
	 */
	public void setNf(String nf) {
		this.nf = nf;
	}
	/**
	 * @return the yf
	 */
	public String getYf() {
		return yf;
	}
	/**
	 * @param yf要设置的 yf
	 */
	public void setYf(String yf) {
		this.yf = yf;
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
	 * @return the zdls
	 */
	public String getZdls() {
		return zdls;
	}
	/**
	 * @param zdls要设置的 zdls
	 */
	public void setZdls(String zdls) {
		this.zdls = zdls;
	}
	/**
	 * @return the zrs
	 */
	public String getZrs() {
		return zrs;
	}
	/**
	 * @param zrs要设置的 zrs
	 */
	public void setZrs(String zrs) {
		this.zrs = zrs;
	}
	/**
	 * @return the zxrs
	 */
	public String getZxrs() {
		return zxrs;
	}
	/**
	 * @param zxrs要设置的 zxrs
	 */
	public void setZxrs(String zxrs) {
		this.zxrs = zxrs;
	}
	/**
	 * @return the kfhj
	 */
	public String getKfhj() {
		return kfhj;
	}
	/**
	 * @param kfhj要设置的 kfhj
	 */
	public void setKfhj(String kfhj) {
		this.kfhj = kfhj;
	}
	/**
	 * @return the xfdf
	 */
	public String getXfdf() {
		return xfdf;
	}
	/**
	 * @param xfdf要设置的 xfdf
	 */
	public void setXfdf(String xfdf) {
		this.xfdf = xfdf;
	}
	/**
	 * @return the jsjgjl
	 */
	public String getJsjgjl() {
		return jsjgjl;
	}
	/**
	 * @param jsjgjl要设置的 jsjgjl
	 */
	public void setJsjgjl(String jsjgjl) {
		this.jsjgjl = jsjgjl;
	}
	/**
	 * @return the yygjl
	 */
	public String getYygjl() {
		return yygjl;
	}
	/**
	 * @param yygjl要设置的 yygjl
	 */
	public void setYygjl(String yygjl) {
		this.yygjl = yygjl;
	}
	/**
	 * @return the gycx
	 */
	public String getGycx() {
		return gycx;
	}
	/**
	 * @param gycx要设置的 gycx
	 */
	public void setGycx(String gycx) {
		this.gycx = gycx;
	}
	/**
	 * @return the jyl
	 */
	public String getJyl() {
		return jyl;
	}
	/**
	 * @param jyl要设置的 jyl
	 */
	public void setJyl(String jyl) {
		this.jyl = jyl;
	}
	/**
	 * @return the xxj
	 */
	public String getXxj() {
		return xxj;
	}
	/**
	 * @param xxj要设置的 xxj
	 */
	public void setXxj(String xxj) {
		this.xxj = xxj;
	}
	/**
	 * @return the syldf
	 */
	public String getSyldf() {
		return syldf;
	}
	/**
	 * @param syldf要设置的 syldf
	 */
	public void setSyldf(String syldf) {
		this.syldf = syldf;
	}
	/**
	 * @return the tdjs
	 */
	public String getTdjs() {
		return tdjs;
	}
	/**
	 * @param tdjs要设置的 tdjs
	 */
	public void setTdjs(String tdjs) {
		this.tdjs = tdjs;
	}
	/**
	 * @return the dkl
	 */
	public String getDkl() {
		return dkl;
	}
	/**
	 * @param dkl要设置的 dkl
	 */
	public void setDkl(String dkl) {
		this.dkl = dkl;
	}
	/**
	 * @return the dkldf
	 */
	public String getDkldf() {
		return dkldf;
	}
	/**
	 * @param dkldf要设置的 dkldf
	 */
	public void setDkldf(String dkldf) {
		this.dkldf = dkldf;
	}
	/**
	 * @return the zysyghhgl
	 */
	public String getZysyghhgl() {
		return zysyghhgl;
	}
	/**
	 * @param zysyghhgl要设置的 zysyghhgl
	 */
	public void setZysyghhgl(String zysyghhgl) {
		this.zysyghhgl = zysyghhgl;
	}
	/**
	 * @return the zysyghdf
	 */
	public String getZysyghdf() {
		return zysyghdf;
	}
	/**
	 * @param zysyghdf要设置的 zysyghdf
	 */
	public void setZysyghdf(String zysyghdf) {
		this.zysyghdf = zysyghdf;
	}
	/**
	 * @return the jszdsc
	 */
	public String getJszdsc() {
		return jszdsc;
	}
	/**
	 * @param jszdsc要设置的 jszdsc
	 */
	public void setJszdsc(String jszdsc) {
		this.jszdsc = jszdsc;
	}
	/**
	 * @return the xsls
	 */
	public String getXsls() {
		return xsls;
	}
	/**
	 * @param xsls要设置的 xsls
	 */
	public void setXsls(String xsls) {
		this.xsls = xsls;
	}
	/**
	 * @return the xszl
	 */
	public String getXszl() {
		return xszl;
	}
	/**
	 * @param xszl要设置的 xszl
	 */
	public void setXszl(String xszl) {
		this.xszl = xszl;
	}
	/**
	 * @return the aq
	 */
	public String getAq() {
		return aq;
	}
	/**
	 * @param aq要设置的 aq
	 */
	public void setAq(String aq) {
		this.aq = aq;
	}
	/**
	 * @return the dshj
	 */
	public String getDshj() {
		return dshj;
	}
	/**
	 * @param dshj要设置的 dshj
	 */
	public void setDshj(String dshj) {
		this.dshj = dshj;
	}
	/**
	 * @return the df
	 */
	public String getDf() {
		return df;
	}
	/**
	 * @param df要设置的 df
	 */
	public void setDf(String df) {
		this.df = df;
	}
	/**
	 * @return the xzhdf
	 */
	public String getXzhdf() {
		return xzhdf;
	}
	/**
	 * @param xzhdf要设置的 xzhdf
	 */
	public void setXzhdf(String xzhdf) {
		this.xzhdf = xzhdf;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public Pages getPages() {
		return pages;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}

}
