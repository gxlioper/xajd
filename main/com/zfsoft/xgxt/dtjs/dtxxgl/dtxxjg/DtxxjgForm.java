/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:00:24 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团信息管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-10-25 下午02:40:46
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DtxxjgForm extends ActionForm {
	private static final long serialVersionUID = -7844335000460670544L;
	private String dtxxjgid;
	private String xh;// 学号
	private String jddm;// 阶段代码
	private String jlsj;// 记录时间
	private String jdmc;// 阶段名称
	private String grxj;// 个人小结
	private String kssj;// 开始时间
	private String jssj;// 结束时间
	private String sjly;// 数据来源
	private String sqid;// 申请id
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	private String lcxx;

	private String dqjdbj;//当前阶段标记
	private String zd1;// 宁波理工党校结业结业时间
	private String zd2;// 宁波理工成绩录入
	private String zd3;// 扩展字段
	private String zd4;// 扩展字段
	private String zd5;// 扩展字段
	private String zd6;// 扩展字段
	private String zd7;// 扩展字段
	private String zd8;// 扩展字段
	private String zd9;// 扩展字段
	private String zd10;// 扩展字段

	private String jddmstr;
	private String kssjstr;
	private String grxjstr;
	private String zd5str;
	private String dtxxjgidstr;
	private String zd1str;
	private String zd2str;
	private String zd3str;
	private String zd8str;
	private String zd9str;
	private String zd10str;
	/**
	 * @return the dtxxjgid
	 */
	public String getDtxxjgid() {
		return dtxxjgid;
	}

	/**
	 * @param dtxxjgid要设置的
	 *            dtxxjgid
	 */
	public void setDtxxjgid(String dtxxjgid) {
		this.dtxxjgid = dtxxjgid;
	}

	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}

	/**
	 * @param xh要设置的
	 *            xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/**
	 * @return the jddm
	 */
	public String getJddm() {
		return jddm;
	}

	/**
	 * @param jddm要设置的
	 *            jddm
	 */
	public void setJddm(String jddm) {
		this.jddm = jddm;
	}

	/**
	 * @return the jlsj
	 */
	public String getJlsj() {
		return jlsj;
	}

	/**
	 * @param jlsj要设置的
	 *            jlsj
	 */
	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}

	/**
	 * @return the jdmc
	 */
	public String getJdmc() {
		return jdmc;
	}

	/**
	 * @param jdmc要设置的
	 *            jdmc
	 */
	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}

	/**
	 * @return the grxj
	 */
	public String getGrxj() {
		return grxj;
	}

	/**
	 * @param grxj要设置的
	 *            grxj
	 */
	public void setGrxj(String grxj) {
		this.grxj = grxj;
	}

	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}

	/**
	 * @param kssj要设置的
	 *            kssj
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}

	/**
	 * @param jssj要设置的
	 *            jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}

	/**
	 * @param sjly要设置的
	 *            sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}

	/**
	 * @param sqid要设置的
	 *            sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}

	/**
	 * @param exportModel要设置的
	 *            exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
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
	 * @return the lcxx
	 */
	public String getLcxx() {
		return lcxx;
	}

	/**
	 * @param lcxx要设置的
	 *            lcxx
	 */
	public void setLcxx(String lcxx) {
		this.lcxx = lcxx;
	}

	/**
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}

	/**
	 * @param zd1要设置的
	 *            zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}

	/**
	 * @return the zd2
	 */
	public String getZd2() {
		return zd2;
	}

	/**
	 * @param zd2要设置的
	 *            zd2
	 */
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}

	/**
	 * @return the zd3
	 */
	public String getZd3() {
		return zd3;
	}

	/**
	 * @param zd3要设置的
	 *            zd3
	 */
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}

	/**
	 * @return the zd4
	 */
	public String getZd4() {
		return zd4;
	}

	/**
	 * @param zd4要设置的
	 *            zd4
	 */
	public void setZd4(String zd4) {
		this.zd4 = zd4;
	}

	/**
	 * @return the zd5
	 */
	public String getZd5() {
		return zd5;
	}

	/**
	 * @param zd5要设置的
	 *            zd5
	 */
	public void setZd5(String zd5) {
		this.zd5 = zd5;
	}

	/**
	 * @return the zd6
	 */
	public String getZd6() {
		return zd6;
	}

	/**
	 * @param zd6要设置的
	 *            zd6
	 */
	public void setZd6(String zd6) {
		this.zd6 = zd6;
	}

	/**
	 * @return the zd7
	 */
	public String getZd7() {
		return zd7;
	}

	/**
	 * @param zd7要设置的
	 *            zd7
	 */
	public void setZd7(String zd7) {
		this.zd7 = zd7;
	}

	/**
	 * @return the zd8
	 */
	public String getZd8() {
		return zd8;
	}

	/**
	 * @param zd8要设置的
	 *            zd8
	 */
	public void setZd8(String zd8) {
		this.zd8 = zd8;
	}

	/**
	 * @return the zd9
	 */
	public String getZd9() {
		return zd9;
	}

	/**
	 * @param zd9要设置的
	 *            zd9
	 */
	public void setZd9(String zd9) {
		this.zd9 = zd9;
	}

	/**
	 * @return the zd10
	 */
	public String getZd10() {
		return zd10;
	}

	/**
	 * @param zd10要设置的
	 *            zd10
	 */
	public void setZd10(String zd10) {
		this.zd10 = zd10;
	}

	/**
	 * @return the jddmstr
	 */
	public String getJddmstr() {
		return jddmstr;
	}

	/**
	 * @param jddmstr要设置的 jddmstr
	 */
	public void setJddmstr(String jddmstr) {
		this.jddmstr = jddmstr;
	}

	/**
	 * @return the kssjstr
	 */
	public String getKssjstr() {
		return kssjstr;
	}

	/**
	 * @param kssjstr要设置的 kssjstr
	 */
	public void setKssjstr(String kssjstr) {
		this.kssjstr = kssjstr;
	}

	/**
	 * @return the grxjstr
	 */
	public String getGrxjstr() {
		return grxjstr;
	}

	/**
	 * @param grxjstr要设置的 grxjstr
	 */
	public void setGrxjstr(String grxjstr) {
		this.grxjstr = grxjstr;
	}

	/**
	 * @return the dtxxjgidstr
	 */
	public String getDtxxjgidstr() {
		return dtxxjgidstr;
	}

	/**
	 * @param dtxxjgidstr要设置的 dtxxjgidstr
	 */
	public void setDtxxjgidstr(String dtxxjgidstr) {
		this.dtxxjgidstr = dtxxjgidstr;
	}

	/**
	 * @return the dqjdbj
	 */
	public String getDqjdbj() {
		return dqjdbj;
	}

	/**
	 * @param dqjdbj要设置的 dqjdbj
	 */
	public void setDqjdbj(String dqjdbj) {
		this.dqjdbj = dqjdbj;
	}

	/**
	 * @return the zd1str
	 */
	public String getZd1str() {
		return zd1str;
	}

	/**
	 * @param zd1str要设置的 zd1str
	 */
	public void setZd1str(String zd1str) {
		this.zd1str = zd1str;
	}

	/**
	 * @return the zd2str
	 */
	public String getZd2str() {
		return zd2str;
	}

	/**
	 * @param zd2str要设置的 zd2str
	 */
	public void setZd2str(String zd2str) {
		this.zd2str = zd2str;
	}

	public String getZd8str() {
		return zd8str;
	}

	public void setZd8str(String zd8str) {
		this.zd8str = zd8str;
	}

	public String getZd9str() {
		return zd9str;
	}

	public void setZd9str(String zd9str) {
		this.zd9str = zd9str;
	}

	public String getZd10str() {
		return zd10str;
	}

	public void setZd10str(String zd10str) {
		this.zd10str = zd10str;
	}

	public String getZd5str() {
		return zd5str;
	}

	public void setZd5str(String zd5str) {
		this.zd5str = zd5str;
	}

	/**
	 * @return the zd3str
	 */
	public String getZd3str() {
		return zd3str;
	}

	/**
	 * @param zd3str要设置的 zd3str
	 */
	public void setZd3str(String zd3str) {
		this.zd3str = zd3str;
	}
	
	
	
}
