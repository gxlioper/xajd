/**
 * @部门:学工产品事业部
 * @日期：2017年5月10日 上午8:43:24 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 志愿服务管理模块
 * @类功能描述: 志愿服务审核Form
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月10日 上午8:43:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwShForm extends ActionForm{
	
	private static final long serialVersionUID = -2717741659123386697L;
	// 分页
	Pages pages = new Pages();
	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	
	private String fwid;	//服务id
	private String xn;	//学年
	private String xq;	//学期
	private String xqmc;	//学期名称
	private String xh;	//学号
	private String fwdd;	//服务地点
	private String fwddssx;	//服务地点省市县
	private String fwkssj;	//服务开始时间
	private String fwjssj;	//服务结束时间
	private String fwnr;	//服务内容
	private String fwxss;	//服务小时数
	private String jzr;	//见证人
	private String splc;	//审批流程
	private String shzt;	//审核状态
	private String type;
	
	private String ywid;	//业务id
	private String shsj;	//审核时间
	private String shr;	//审核人
	private String shyj;	//审核意见
	private String gwid;	//岗位id
	private String shztmc;	//审核状态名称
	private String shid;	//审核id
	private String thgw;	//岗位退回
	private String shjg;	//审核结果
	private String[] fwids;	//服务id数组，用于批量审核
	private String[] gwids;	//岗位id数组，用于批量审核
	private String[] xhs;	//学号数组，用于批量审核
	
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
	/**
	 * @return the fwid
	 */
	public String getFwid() {
		return fwid;
	}
	/**
	 * @param fwid要设置的 fwid
	 */
	public void setFwid(String fwid) {
		this.fwid = fwid;
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
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
	 * @return the fwdd
	 */
	public String getFwdd() {
		return fwdd;
	}
	/**
	 * @param fwdd要设置的 fwdd
	 */
	public void setFwdd(String fwdd) {
		this.fwdd = fwdd;
	}
	/**
	 * @return the fwddssx
	 */
	public String getFwddssx() {
		return fwddssx;
	}
	/**
	 * @param fwddssx要设置的 fwddssx
	 */
	public void setFwddssx(String fwddssx) {
		this.fwddssx = fwddssx;
	}
	/**
	 * @return the fwkssj
	 */
	public String getFwkssj() {
		return fwkssj;
	}
	/**
	 * @param fwkssj要设置的 fwkssj
	 */
	public void setFwkssj(String fwkssj) {
		this.fwkssj = fwkssj;
	}
	/**
	 * @return the fwjssj
	 */
	public String getFwjssj() {
		return fwjssj;
	}
	/**
	 * @param fwjssj要设置的 fwjssj
	 */
	public void setFwjssj(String fwjssj) {
		this.fwjssj = fwjssj;
	}
	/**
	 * @return the fwnr
	 */
	public String getFwnr() {
		return fwnr;
	}
	/**
	 * @param fwnr要设置的 fwnr
	 */
	public void setFwnr(String fwnr) {
		this.fwnr = fwnr;
	}
	/**
	 * @return the fwxss
	 */
	public String getFwxss() {
		return fwxss;
	}
	/**
	 * @param fwxss要设置的 fwxss
	 */
	public void setFwxss(String fwxss) {
		this.fwxss = fwxss;
	}
	/**
	 * @return the jzr
	 */
	public String getJzr() {
		return jzr;
	}
	/**
	 * @param jzr要设置的 jzr
	 */
	public void setJzr(String jzr) {
		this.jzr = jzr;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
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
	 * @return the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @param ywid要设置的 ywid
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	/**
	 * @return the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @param shsj要设置的 shsj
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	/**
	 * @return the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @param shr要设置的 shr
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyj要设置的 shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwid要设置的 gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @return the shztmc
	 */
	public String getShztmc() {
		return shztmc;
	}
	/**
	 * @param shztmc要设置的 shztmc
	 */
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shid要设置的 shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgw要设置的 thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjg要设置的 shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @return the fwids
	 */
	public String[] getFwids() {
		return fwids;
	}
	/**
	 * @param fwids要设置的 fwids
	 */
	public void setFwids(String[] fwids) {
		this.fwids = fwids;
	}
	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @param gwids要设置的 gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @param xhs要设置的 xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	
	
}
