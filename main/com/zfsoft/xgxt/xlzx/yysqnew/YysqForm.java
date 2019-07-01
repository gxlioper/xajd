package com.zfsoft.xgxt.xlzx.yysqnew;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/** 
 * 心理咨询管理模块
 */

public class YysqForm  extends ActionForm {


	private static final long serialVersionUID = 1L;

	User user = new User();

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	
	private String id; //主键
	private String xh;//学号
	private String xstell;//学生电话
	private String zgh;//职工号
	private String yyzxrq;//预约咨询时间
	private String yyzxzt;//预约咨询主题
	private String yyzxxq;//预约咨询详情
	private String status;//预约状态-1预约中2预约成功3预约中(学生取消)4预约成功(学生取消)5预约失败6已过期
	private String yysbyy;//预约失败原因
	private String createsj;//创建时间
	private String bz;//备注
	private String datazt;//数据状态-0失效1正常
	private String qssj;//预约咨询起始时间
	private String jssj;//预约咨询结束时间
	private String xn;//学年
	private String xq;//学期
	
	private String xlcsjg;
	private String ywyw;
	private String zyzlls;
	private String zxcs;
	private String sfja;
	private String sczxsj;
	private String bczxnr;
	private String bcjjwt;
	private String zxgsfs;
	private String zxfknr;


	private String qdzt;//签到状态：yqd 已签到,wqd 未签到,qj 请假,cd 迟到
	private String yyfs;//预约方式:ws 网上预约,dh 电话预约,xc 现场预约,jz 家长预约
	private String qdztbz;//签到状态备注：当状态为请假，则保存的是请假原因，当状态为请假，则保存的是迟到时间

	public String getQdztbz() {
		return qdztbz;
	}

	public void setQdztbz(String qdztbz) {
		this.qdztbz = qdztbz;
	}

	public String getYyfs() {
		return yyfs;
	}

	public void setYyfs(String yyfs) {
		this.yyfs = yyfs;
	}

	public String getQdzt() {
		return qdzt;
	}

	public void setQdzt(String qdzt) {
		this.qdzt = qdzt;
	}

	/**
	 * 导出配置
	 */
	private ExportModel exportModel = new ExportModel();
	
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user要设置的 user
	 */
	public void setUser(User user) {
		this.user = user;
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
	 * @return the xstell
	 */
	public String getXstell() {
		return xstell;
	}
	/**
	 * @param xstell要设置的 xstell
	 */
	public void setXstell(String xstell) {
		this.xstell = xstell;
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
	 * @return the yyzxrq
	 */
	public String getYyzxrq() {
		return yyzxrq;
	}
	/**
	 * @param yyzxrq要设置的 yyzxrq
	 */
	public void setYyzxrq(String yyzxrq) {
		this.yyzxrq = yyzxrq;
	}
	/**
	 * @return the yyzxzt
	 */
	public String getYyzxzt() {
		return yyzxzt;
	}
	/**
	 * @param yyzxzt要设置的 yyzxzt
	 */
	public void setYyzxzt(String yyzxzt) {
		this.yyzxzt = yyzxzt;
	}
	/**
	 * @return the yyzxxq
	 */
	public String getYyzxxq() {
		return yyzxxq;
	}
	/**
	 * @param yyzxxq要设置的 yyzxxq
	 */
	public void setYyzxxq(String yyzxxq) {
		this.yyzxxq = yyzxxq;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status要设置的 status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return the yysbyy
	 */
	public String getYysbyy() {
		return yysbyy;
	}
	/**
	 * @param yysbyy要设置的 yysbyy
	 */
	public void setYysbyy(String yysbyy) {
		this.yysbyy = yysbyy;
	}
	/**
	 * @return the createsj
	 */
	public String getCreatesj() {
		return createsj;
	}
	/**
	 * @param createsj要设置的 createsj
	 */
	public void setCreatesj(String createsj) {
		this.createsj = createsj;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	
	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the datazt
	 */
	public String getDatazt() {
		return datazt;
	}
	
	/**
	 * @param datazt要设置的 datazt
	 */
	public void setDatazt(String datazt) {
		this.datazt = datazt;
	}
	/**
	 * @return the qssj
	 */
	public String getQssj() {
		return qssj;
	}
	/**
	 * @param qssj要设置的 qssj
	 */
	public void setQssj(String qssj) {
		this.qssj = qssj;
	}
	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}
	/**
	 * @param jssj要设置的 jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
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
	 * @return the xlcsjg
	 */
	public String getXlcsjg() {
		return xlcsjg;
	}
	/**
	 * @param xlcsjg要设置的 xlcsjg
	 */
	public void setXlcsjg(String xlcsjg) {
		this.xlcsjg = xlcsjg;
	}
	/**
	 * @return the ywyw
	 */
	public String getYwyw() {
		return ywyw;
	}
	/**
	 * @param ywyw要设置的 ywyw
	 */
	public void setYwyw(String ywyw) {
		this.ywyw = ywyw;
	}
	/**
	 * @return the zyzlls
	 */
	public String getZyzlls() {
		return zyzlls;
	}
	/**
	 * @param zyzlls要设置的 zyzlls
	 */
	public void setZyzlls(String zyzlls) {
		this.zyzlls = zyzlls;
	}
	/**
	 * @return the zxcs
	 */
	public String getZxcs() {
		return zxcs;
	}
	/**
	 * @param zxcs要设置的 zxcs
	 */
	public void setZxcs(String zxcs) {
		this.zxcs = zxcs;
	}
	/**
	 * @return the sfja
	 */
	public String getSfja() {
		return sfja;
	}
	/**
	 * @param sfja要设置的 sfja
	 */
	public void setSfja(String sfja) {
		this.sfja = sfja;
	}
	/**
	 * @return the sczxsj
	 */
	public String getSczxsj() {
		return sczxsj;
	}
	/**
	 * @param sczxsj要设置的 sczxsj
	 */
	public void setSczxsj(String sczxsj) {
		this.sczxsj = sczxsj;
	}
	/**
	 * @return the bczxnr
	 */
	public String getBczxnr() {
		return bczxnr;
	}
	/**
	 * @param bczxnr要设置的 bczxnr
	 */
	public void setBczxnr(String bczxnr) {
		this.bczxnr = bczxnr;
	}
	/**
	 * @return the bcjjwt
	 */
	public String getBcjjwt() {
		return bcjjwt;
	}
	/**
	 * @param bcjjwt要设置的 bcjjwt
	 */
	public void setBcjjwt(String bcjjwt) {
		this.bcjjwt = bcjjwt;
	}
	/**
	 * @return the zxgsfs
	 */
	public String getZxgsfs() {
		return zxgsfs;
	}
	/**
	 * @param zxgsfs要设置的 zxgsfs
	 */
	public void setZxgsfs(String zxgsfs) {
		this.zxgsfs = zxgsfs;
	}
	/**
	 * @return the zxfknr
	 */
	public String getZxfknr() {
		return zxfknr;
	}
	/**
	 * @param zxfknr要设置的 zxfknr
	 */
	public void setZxfknr(String zxfknr) {
		this.zxfknr = zxfknr;
	}
	
}
