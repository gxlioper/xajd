/**
 * @部门:学工产品事业部
 * @日期：2014-5-20 下午03:24:36 
 */
package com.zfsoft.xgxt.rcsw.rwdj;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 入伍登记管理模块
 * @作者： 张昌路[工号:982]
 * @时间： 2014-5-20 下午03:24:36
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class RwdjForm extends ActionForm {
	private String rwdjid;// varchar2(32) n 征兵信息id
	private String xh;
	private String rwsj;// varchar2(20) y 入伍时间
	private String xyqk;// varchar2(20) y 学业情况
	private String ywqrwxy;// varchar2(2) y 有无签入伍协议
	private String bjgms;// varchar2(2) y 不及格门数
	private String hyzk;// varchar2(2) y 婚姻状况
	private String cylb;// varchar2(2) y 从业类别
	private String hjlb;// varchar2(2) y 户籍类别
	private String fqxm;// varchar2(20) y 父亲姓名
	private String fqsj;// varchar2(20) y 父亲手机
	private String mqxm;// varchar2(20) y 母亲姓名
	private String mqsj;// varchar2(20) y 母亲手机
	private String qtlxr;// varchar2(20) y 其他联系人
	private String qtlxrfs;// varchar2(20) y 其他联系人方式
	private String zysl;// varchar2(20) y 左眼视力
	private String yysl;// varchar2(20) y 右眼视力
	private String fybd;// varchar2(20) y 服役部队
	private String bddz;// varchar2(100) y 部队地址
	private String bdlxfs;// varchar2(20) y 部队联系方式
	private String jj;// varchar2(500) y 嘉奖
	private String yxsb;// varchar2(50) y 优秀士兵
	private String fysj;// varchar2(20) y 复原时间
	private String lg;// varchar2(1000) y 立功
	private String bysj;// varchar2(20) y 毕业时间
	private String zjbsj;// varchar2(20) y 专接本时间
	private String zjbhjdxy;// varchar2(50) y 专接本后就读学院
	private String zjbhzy;// varchar2(20) y 专接本后专业
	private String zjbhxh;// varchar2(50) y 专接本后学号
	private String zjbhbysj;// varchar2(20) y 专接本后毕业时间
	private String bjyhkh;// varchar2(50) y 北京银行卡号
	private String yhkmc;// varchar2(20) y 银行卡名称
	private String yhkdz;// varchar2(100) y 银行卡地址
	private String rwhxfbc;// varchar2(10) y 入伍后学费补偿
	private String tyhxfzz;// varchar2(10) y 退役后学费资助
	private String jyhdw;// varchar2(50) y 就业后单位
	private String gwy;// varchar2(2) y 公务员
	private String syb;// varchar2(2) y 事业编
	private String gq;// varchar2(2) y 国企
	private String fgjj;// varchar2(2) y 非公经济
	private String bz;// varchar2(20) y 备注
	private String rwtj;// 入伍途径
	private String rwtjmc;// 入伍途径
	private String sjly;//数据来源，默认为0
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	private String type;

	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}

	/**
	 * @param sjly要设置的 sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}


	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * @param pages要设置的
	 *            pages
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
	 * @param searchModel要设置的
	 *            searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the rwsj
	 */
	public String getRwsj() {
		return rwsj;
	}

	/**
	 * @param rwsj要设置的
	 *            rwsj
	 */
	public void setRwsj(String rwsj) {
		this.rwsj = rwsj;
	}

	/**
	 * @return the xyqk
	 */
	public String getXyqk() {
		return xyqk;
	}

	/**
	 * @param xyqk要设置的
	 *            xyqk
	 */
	public void setXyqk(String xyqk) {
		this.xyqk = xyqk;
	}

	/**
	 * @return the ywqrwxy
	 */
	public String getYwqrwxy() {
		return ywqrwxy;
	}

	/**
	 * @param ywqrwxy要设置的
	 *            ywqrwxy
	 */
	public void setYwqrwxy(String ywqrwxy) {
		this.ywqrwxy = ywqrwxy;
	}

	/**
	 * @return the bjgms
	 */
	public String getBjgms() {
		return bjgms;
	}

	/**
	 * @param bjgms要设置的
	 *            bjgms
	 */
	public void setBjgms(String bjgms) {
		this.bjgms = bjgms;
	}

	/**
	 * @return the hyzk
	 */
	public String getHyzk() {
		return hyzk;
	}

	/**
	 * @param hyzk要设置的
	 *            hyzk
	 */
	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}

	/**
	 * @return the cylb
	 */
	public String getCylb() {
		return cylb;
	}

	/**
	 * @param cylb要设置的
	 *            cylb
	 */
	public void setCylb(String cylb) {
		this.cylb = cylb;
	}

	/**
	 * @return the hjlb
	 */
	public String getHjlb() {
		return hjlb;
	}

	/**
	 * @param hjlb要设置的
	 *            hjlb
	 */
	public void setHjlb(String hjlb) {
		this.hjlb = hjlb;
	}

	/**
	 * @return the fqxm
	 */
	public String getFqxm() {
		return fqxm;
	}

	/**
	 * @param fqxm要设置的
	 *            fqxm
	 */
	public void setFqxm(String fqxm) {
		this.fqxm = fqxm;
	}

	/**
	 * @return the fqsj
	 */
	public String getFqsj() {
		return fqsj;
	}

	/**
	 * @param fqsj要设置的
	 *            fqsj
	 */
	public void setFqsj(String fqsj) {
		this.fqsj = fqsj;
	}

	/**
	 * @return the mqxm
	 */
	public String getMqxm() {
		return mqxm;
	}

	/**
	 * @param mqxm要设置的
	 *            mqxm
	 */
	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}

	/**
	 * @return the mqsj
	 */
	public String getMqsj() {
		return mqsj;
	}

	/**
	 * @param mqsj要设置的
	 *            mqsj
	 */
	public void setMqsj(String mqsj) {
		this.mqsj = mqsj;
	}

	/**
	 * @return the qtlxr
	 */
	public String getQtlxr() {
		return qtlxr;
	}

	/**
	 * @param qtlxr要设置的
	 *            qtlxr
	 */
	public void setQtlxr(String qtlxr) {
		this.qtlxr = qtlxr;
	}

	/**
	 * @return the qtlxrfs
	 */
	public String getQtlxrfs() {
		return qtlxrfs;
	}

	/**
	 * @param qtlxrfs要设置的
	 *            qtlxrfs
	 */
	public void setQtlxrfs(String qtlxrfs) {
		this.qtlxrfs = qtlxrfs;
	}

	/**
	 * @return the zysl
	 */
	public String getZysl() {
		return zysl;
	}

	/**
	 * @param zysl要设置的
	 *            zysl
	 */
	public void setZysl(String zysl) {
		this.zysl = zysl;
	}

	/**
	 * @return the yysl
	 */
	public String getYysl() {
		return yysl;
	}

	/**
	 * @param yysl要设置的
	 *            yysl
	 */
	public void setYysl(String yysl) {
		this.yysl = yysl;
	}

	/**
	 * @return the fybd
	 */
	public String getFybd() {
		return fybd;
	}

	/**
	 * @param fybd要设置的
	 *            fybd
	 */
	public void setFybd(String fybd) {
		this.fybd = fybd;
	}

	/**
	 * @return the bddz
	 */
	public String getBddz() {
		return bddz;
	}

	/**
	 * @param bddz要设置的
	 *            bddz
	 */
	public void setBddz(String bddz) {
		this.bddz = bddz;
	}

	/**
	 * @return the bdlxfs
	 */
	public String getBdlxfs() {
		return bdlxfs;
	}

	/**
	 * @param bdlxfs要设置的
	 *            bdlxfs
	 */
	public void setBdlxfs(String bdlxfs) {
		this.bdlxfs = bdlxfs;
	}

	/**
	 * @return the jj
	 */
	public String getJj() {
		return jj;
	}

	/**
	 * @param jj要设置的
	 *            jj
	 */
	public void setJj(String jj) {
		this.jj = jj;
	}

	/**
	 * @return the yxsb
	 */
	public String getYxsb() {
		return yxsb;
	}

	/**
	 * @param yxsb要设置的
	 *            yxsb
	 */
	public void setYxsb(String yxsb) {
		this.yxsb = yxsb;
	}

	/**
	 * @return the fysj
	 */
	public String getFysj() {
		return fysj;
	}

	/**
	 * @param fysj要设置的
	 *            fysj
	 */
	public void setFysj(String fysj) {
		this.fysj = fysj;
	}

	/**
	 * @return the lg
	 */
	public String getLg() {
		return lg;
	}

	/**
	 * @param lg要设置的
	 *            lg
	 */
	public void setLg(String lg) {
		this.lg = lg;
	}

	/**
	 * @return the bysj
	 */
	public String getBysj() {
		return bysj;
	}

	/**
	 * @param bysj要设置的
	 *            bysj
	 */
	public void setBysj(String bysj) {
		this.bysj = bysj;
	}

	/**
	 * @return the zjbsj
	 */
	public String getZjbsj() {
		return zjbsj;
	}

	/**
	 * @param zjbsj要设置的
	 *            zjbsj
	 */
	public void setZjbsj(String zjbsj) {
		this.zjbsj = zjbsj;
	}

	/**
	 * @return the zjbhzy
	 */
	public String getZjbhzy() {
		return zjbhzy;
	}

	/**
	 * @param zjbhzy要设置的
	 *            zjbhzy
	 */
	public void setZjbhzy(String zjbhzy) {
		this.zjbhzy = zjbhzy;
	}

	/**
	 * @return the zjbhxh
	 */
	public String getZjbhxh() {
		return zjbhxh;
	}

	/**
	 * @param zjbhxh要设置的
	 *            zjbhxh
	 */
	public void setZjbhxh(String zjbhxh) {
		this.zjbhxh = zjbhxh;
	}

	/**
	 * @return the zjbhbysj
	 */
	public String getZjbhbysj() {
		return zjbhbysj;
	}

	/**
	 * @param zjbhbysj要设置的
	 *            zjbhbysj
	 */
	public void setZjbhbysj(String zjbhbysj) {
		this.zjbhbysj = zjbhbysj;
	}

	/**
	 * @return the bjyhkh
	 */
	public String getBjyhkh() {
		return bjyhkh;
	}

	/**
	 * @param bjyhkh要设置的
	 *            bjyhkh
	 */
	public void setBjyhkh(String bjyhkh) {
		this.bjyhkh = bjyhkh;
	}

	/**
	 * @return the yhkmc
	 */
	public String getYhkmc() {
		return yhkmc;
	}

	/**
	 * @param yhkmc要设置的
	 *            yhkmc
	 */
	public void setYhkmc(String yhkmc) {
		this.yhkmc = yhkmc;
	}

	/**
	 * @return the yhkdz
	 */
	public String getYhkdz() {
		return yhkdz;
	}

	/**
	 * @param yhkdz要设置的
	 *            yhkdz
	 */
	public void setYhkdz(String yhkdz) {
		this.yhkdz = yhkdz;
	}

	/**
	 * @return the rwhxfbc
	 */
	public String getRwhxfbc() {
		return rwhxfbc;
	}

	/**
	 * @param rwhxfbc要设置的
	 *            rwhxfbc
	 */
	public void setRwhxfbc(String rwhxfbc) {
		this.rwhxfbc = rwhxfbc;
	}

	/**
	 * @return the tyhxfzz
	 */
	public String getTyhxfzz() {
		return tyhxfzz;
	}

	/**
	 * @param tyhxfzz要设置的
	 *            tyhxfzz
	 */
	public void setTyhxfzz(String tyhxfzz) {
		this.tyhxfzz = tyhxfzz;
	}

	/**
	 * @return the jyhdw
	 */
	public String getJyhdw() {
		return jyhdw;
	}

	/**
	 * @param jyhdw要设置的
	 *            jyhdw
	 */
	public void setJyhdw(String jyhdw) {
		this.jyhdw = jyhdw;
	}

	/**
	 * @return the gwy
	 */
	public String getGwy() {
		return gwy;
	}

	/**
	 * @param gwy要设置的
	 *            gwy
	 */
	public void setGwy(String gwy) {
		this.gwy = gwy;
	}

	/**
	 * @return the syb
	 */
	public String getSyb() {
		return syb;
	}

	/**
	 * @param syb要设置的
	 *            syb
	 */
	public void setSyb(String syb) {
		this.syb = syb;
	}

	/**
	 * @return the gq
	 */
	public String getGq() {
		return gq;
	}

	/**
	 * @param gq要设置的
	 *            gq
	 */
	public void setGq(String gq) {
		this.gq = gq;
	}

	/**
	 * @return the fgjj
	 */
	public String getFgjj() {
		return fgjj;
	}

	/**
	 * @param fgjj要设置的
	 *            fgjj
	 */
	public void setFgjj(String fgjj) {
		this.fgjj = fgjj;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz要设置的
	 *            bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return the rwtj
	 */
	public String getRwtj() {
		return rwtj;
	}

	/**
	 * @param rwtj要设置的 rwtj
	 */
	public void setRwtj(String rwtj) {
		this.rwtj = rwtj;
	}

	/**
	 * @return the rwtjmc
	 */
	public String getRwtjmc() {
		return rwtjmc;
	}

	/**
	 * @param rwtjmc要设置的 rwtjmc
	 */
	public void setRwtjmc(String rwtjmc) {
		this.rwtjmc = rwtjmc;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type要设置的
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the zjbhjdxy
	 */
	public String getZjbhjdxy() {
		return zjbhjdxy;
	}

	/**
	 * @param zjbhjdxy要设置的 zjbhjdxy
	 */
	public void setZjbhjdxy(String zjbhjdxy) {
		this.zjbhjdxy = zjbhjdxy;
	}

	/**
	 * @return the rwdjid
	 */
	public String getRwdjid() {
		return rwdjid;
	}

	/**
	 * @param rwdjid要设置的 rwdjid
	 */
	public void setRwdjid(String rwdjid) {
		this.rwdjid = rwdjid;
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
}
