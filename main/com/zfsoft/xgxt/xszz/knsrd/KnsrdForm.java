package com.zfsoft.xgxt.xszz.knsrd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.xszz.jtqkdc.JtcyForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生资助2013版之困难生认定
 * @作者： Penghui.Qu
 * @时间： 2013-4-24 上午11:25:19
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KnsrdForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private JtcyForm jtcy = new JtcyForm();

	private String xh;// 学号
	private String xn;// 学年
	private String xq;// 学期
	private String xqmc;
	private String sqsj;// 申请时间
	private String sqly;// 申请理由
	private String shzt;// 审核状态
	private String ylzd1;// 申请档次
	private String ylzd2;// 预留字段2
	private String ylzd3;// 预留字段3
	private String ylzd4;// 预留字段4
	private String ylzd5;// 预留字段5
	private String ylzd6;// 预留字段6
	private String ylzd7;// 预留字段7
	private String ylzd8;// 预留字段8
	private String ylzd9;// 预留字段9
	private String ylzd10;// 预留字段10
	private String guid;// ID
	private String rddc;//认定档次
	private String shlc;
	private String shyj;
	private String xtgwid;
	private String[] id;
	private String[] gwid;
	private String[] xhs;
	private String shid;
	private String shjg;// 审核结果
	private String thgw;// 退回岗位
	private String sqdcmc;//申请档次名称
	private String yymc;
	private String sjdc;//上级认定档次
	private String sqlydm;//申请理由
	private String shrddc; //审核认定档次
	private String knpx;//困难排序
	private String jtknlxmc;
	private String gdxfplxmc;
	private String jtrjnsr;  //家庭人均年收入
	private String dcmcYdxg; //移动端从申请页面获取到的困难当此信息
	private String sqlyyy;

	public String getSqlyyy() {
		return sqlyyy;
	}

	public void setSqlyyy(String sqlyyy) {
		this.sqlyyy = sqlyyy;
	}

	public String getDcmcYdxg() {
		return dcmcYdxg;
	}

	public void setDcmcYdxg(String dcmcYdxg) {
		this.dcmcYdxg = dcmcYdxg;
	}

	public String getJtrjnsr() {
		return jtrjnsr;
	}

	public void setJtrjnsr(String jtrjnsr) {
		this.jtrjnsr = jtrjnsr;
	}

	public String getKnpx() {
		return knpx;
	}

	public void setKnpx(String knpx) {
		this.knpx = knpx;
	}

	/**
	 * @return the shrddc
	 */
	public String getShrddc() {
		return shrddc;
	}

	/**
	 * @param shrddc要设置的 shrddc
	 */
	public void setShrddc(String shrddc) {
		this.shrddc = shrddc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}

	/**
	 * @param xqmc the xqmc to set
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

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

	public JtcyForm getJtcy() {
		return jtcy;
	}

	public void setJtcy(JtcyForm jtcy) {
		this.jtcy = jtcy;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
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

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getYlzd1() {
		return ylzd1;
	}

	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}

	public String getYlzd2() {
		return ylzd2;
	}

	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}

	public String getYlzd3() {
		return ylzd3;
	}

	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}

	public String getYlzd4() {
		return ylzd4;
	}

	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}

	public String getYlzd5() {
		return ylzd5;
	}

	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}

	public String getYlzd6() {
		return ylzd6;
	}

	public void setYlzd6(String ylzd6) {
		this.ylzd6 = ylzd6;
	}

	public String getYlzd7() {
		return ylzd7;
	}

	public void setYlzd7(String ylzd7) {
		this.ylzd7 = ylzd7;
	}

	public String getYlzd8() {
		return ylzd8;
	}

	public void setYlzd8(String ylzd8) {
		this.ylzd8 = ylzd8;
	}

	public String getYlzd9() {
		return ylzd9;
	}

	public void setYlzd9(String ylzd9) {
		this.ylzd9 = ylzd9;
	}

	public String getYlzd10() {
		return ylzd10;
	}

	public void setYlzd10(String ylzd10) {
		this.ylzd10 = ylzd10;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getRddc() {
		return rddc;
	}

	public void setRddc(String rddc) {
		this.rddc = rddc;
	}

	public String getShlc() {
		return shlc;
	}

	public void setShlc(String shlc) {
		this.shlc = shlc;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getXtgwid() {
		return xtgwid;
	}

	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getGwid() {
		return gwid;
	}

	public void setGwid(String[] gwid) {
		this.gwid = gwid;
	}

	public String[] getXhs() {
		return xhs;
	}

	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getShid() {
		return shid;
	}

	public void setShid(String shid) {
		this.shid = shid;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getThgw() {
		return thgw;
	}

	public void setThgw(String thgw) {
		this.thgw = thgw;
	}

	public void setSqdcmc(String sqdcmc) {
		this.sqdcmc = sqdcmc;
	}

	public String getSqdcmc() {
		return sqdcmc;
	}

	public String getSjdc() {
		return sjdc;
	}

	public void setSjdc(String sjdc) {
		this.sjdc = sjdc;
	}

	/**
	 * @return the yymc
	 */
	public String getYymc() {
		return yymc;
	}

	/**
	 * @param yymc要设置的 yymc
	 */
	public void setYymc(String yymc) {
		this.yymc = yymc;
	}

	public String getSqlydm() {
		return sqlydm;
	}

	public void setSqlydm(String sqlydm) {
		this.sqlydm = sqlydm;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-13 下午05:31:59 
	 * @return		: the jtknlxmc
	 */
	public String getJtknlxmc() {
		return jtknlxmc;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-13 下午05:31:59 
	 * @param 		：jtknlxmc the jtknlxmc to set
	 */
	public void setJtknlxmc(String jtknlxmc) {
		this.jtknlxmc = jtknlxmc;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-13 下午05:31:59 
	 * @return		: the gdxfplxmc
	 */
	public String getGdxfplxmc() {
		return gdxfplxmc;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-13 下午05:31:59 
	 * @param 		：gdxfplxmc the gdxfplxmc to set
	 */
	public void setGdxfplxmc(String gdxfplxmc) {
		this.gdxfplxmc = gdxfplxmc;
	}
	
}
