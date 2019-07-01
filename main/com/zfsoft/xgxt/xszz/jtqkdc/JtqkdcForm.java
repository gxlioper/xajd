package com.zfsoft.xgxt.xszz.jtqkdc;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生资助2013版之家庭情况调查 
 * @作者： Penghui.Qu 
 * @时间： 2013-4-18 下午06:28:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class JtqkdcForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	private JtcyForm jtcy = new JtcyForm();
	
	private String xh ;//学号 
	private String dcsj ;//调查时间 
	private String sfgc ;//是否孤残 
	private String sfdq ;//是否单亲 
	private String lszn ;//是否烈士子女 
	private String sfdb ;//是否低保 
	private String jthk ;//家庭户口
	private String jtszdssxdm ;//家庭所在地省市县代码 
	private String sfpkx ;//是否贫困县 
	private String pkxjb ;//贫困县等级 
	private String sfqz ;//是否欠债 
	private String fmjk ;//父母是否有病或残疾 
	private String fmjz ;//父母健在情况 
	private String jtdh ;//家庭电话 
	private String jtdz ;//家庭地址 
	private String jtrs ;//家庭人口数 
	private String jtyb ;//家庭邮编 
	private String jtrjsr ;//家庭人均收入 
	private String jtnzsr ;//家庭年总收入 
	private String jtrjysr ;//家庭人均月收入 
	private String jtsrly ;//家庭收入来源 
	private String mzbmtxdz ;//民政部门通讯地址 
	private String mzbmyzbm ;//民政部门邮政编码 
	private String mzbmlxdh ;//民政部门联系电话 
	private String snjtsr ;//上年家庭收入 
	private String yhzzqk ;//已获资助情况 
	private String jtszqk ;//家庭受灾情况 
	private String tfsjqk ;//突发事件情况 
	private String cjnmqk ;//家庭年迈情况 
	private String jtsyqk ;//家庭失业情况 
	private String jtqzqk ;//家庭欠债情况 
	private String jtqtqk ;//家庭其他情况 
	private String ylzd1 ;//预留字段1 
	private String ylzd2 ;//预留字段2 
	private String ylzd3 ;//预留字段3 
	private String ylzd4 ;//预留字段4 
	private String ylzd5 ;//预留字段5 
	private String ylzd6 ;//预留字段6 
	private String ylzd7 ;//预留字段7 
	private String ylzd8 ;//预留字段8 
	private String ylzd9 ;//预留字段9 
	private String ylzd10 ;//预留字段10 
	private String ylzd11 ;//预留字段11
	private String ylzd12 ;//预留字段12
	private String ylzd13 ;//预留字段13
	private String ylzd14 ;//预留字段14
	private String ylzd15 ;//预留字段15
	private String ylzd16 ;//预留字段16
	private String ylzd17 ;//预留字段17
	private String ylzd18 ;//预留字段18
	private String ylzd19 ;//预留字段19
	private String ylzd20 ;//预留字段20
	private String[] jtknlx;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getDcsj() {
		return dcsj;
	}

	public void setDcsj(String dcsj) {
		this.dcsj = dcsj;
	}

	public String getSfgc() {
		return sfgc;
	}

	public void setSfgc(String sfgc) {
		this.sfgc = sfgc;
	}

	public String getSfdq() {
		return sfdq;
	}

	public void setSfdq(String sfdq) {
		this.sfdq = sfdq;
	}

	public String getLszn() {
		return lszn;
	}

	public void setLszn(String lszn) {
		this.lszn = lszn;
	}

	public String getSfdb() {
		return sfdb;
	}

	public void setSfdb(String sfdb) {
		this.sfdb = sfdb;
	}

	public String getJthk() {
		return jthk;
	}

	public void setJthk(String jthk) {
		this.jthk = jthk;
	}

	public String getJtszdssxdm() {
		return jtszdssxdm;
	}

	public void setJtszdssxdm(String jtszdssxdm) {
		this.jtszdssxdm = jtszdssxdm;
	}

	public String getSfpkx() {
		return sfpkx;
	}

	public void setSfpkx(String sfpkx) {
		this.sfpkx = sfpkx;
	}

	public String getPkxjb() {
		return pkxjb;
	}

	public void setPkxjb(String pkxjb) {
		this.pkxjb = pkxjb;
	}

	public String getSfqz() {
		return sfqz;
	}

	public void setSfqz(String sfqz) {
		this.sfqz = sfqz;
	}

	public String getFmjk() {
		return fmjk;
	}

	public void setFmjk(String fmjk) {
		this.fmjk = fmjk;
	}

	public String getFmjz() {
		return fmjz;
	}

	public void setFmjz(String fmjz) {
		this.fmjz = fmjz;
	}

	public String getJtdh() {
		return jtdh;
	}

	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}

	public String getJtdz() {
		return jtdz;
	}

	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}

	public String getJtrs() {
		return jtrs;
	}

	public void setJtrs(String jtrs) {
		this.jtrs = jtrs;
	}

	public String getJtyb() {
		return jtyb;
	}

	public void setJtyb(String jtyb) {
		this.jtyb = jtyb;
	}

	public String getJtrjsr() {
		return jtrjsr;
	}

	public void setJtrjsr(String jtrjsr) {
		this.jtrjsr = jtrjsr;
	}

	public String getJtnzsr() {
		return jtnzsr;
	}

	public void setJtnzsr(String jtnzsr) {
		this.jtnzsr = jtnzsr;
	}

	public String getJtrjysr() {
		return jtrjysr;
	}

	public void setJtrjysr(String jtrjysr) {
		this.jtrjysr = jtrjysr;
	}

	public String getJtsrly() {
		return jtsrly;
	}

	public void setJtsrly(String jtsrly) {
		this.jtsrly = jtsrly;
	}

	public String getMzbmtxdz() {
		return mzbmtxdz;
	}

	public void setMzbmtxdz(String mzbmtxdz) {
		this.mzbmtxdz = mzbmtxdz;
	}

	public String getMzbmyzbm() {
		return mzbmyzbm;
	}

	public void setMzbmyzbm(String mzbmyzbm) {
		this.mzbmyzbm = mzbmyzbm;
	}

	public String getMzbmlxdh() {
		return mzbmlxdh;
	}

	public void setMzbmlxdh(String mzbmlxdh) {
		this.mzbmlxdh = mzbmlxdh;
	}

	public String getSnjtsr() {
		return snjtsr;
	}

	public void setSnjtsr(String snjtsr) {
		this.snjtsr = snjtsr;
	}

	public String getYhzzqk() {
		return yhzzqk;
	}

	public void setYhzzqk(String yhzzqk) {
		this.yhzzqk = yhzzqk;
	}

	public String getJtszqk() {
		return jtszqk;
	}

	public void setJtszqk(String jtszqk) {
		this.jtszqk = jtszqk;
	}

	public String getTfsjqk() {
		return tfsjqk;
	}

	public void setTfsjqk(String tfsjqk) {
		this.tfsjqk = tfsjqk;
	}

	public String getCjnmqk() {
		return cjnmqk;
	}

	public void setCjnmqk(String cjnmqk) {
		this.cjnmqk = cjnmqk;
	}

	public String getJtsyqk() {
		return jtsyqk;
	}

	public void setJtsyqk(String jtsyqk) {
		this.jtsyqk = jtsyqk;
	}

	public String getJtqzqk() {
		return jtqzqk;
	}

	public void setJtqzqk(String jtqzqk) {
		this.jtqzqk = jtqzqk;
	}

	public String getJtqtqk() {
		return jtqtqk;
	}

	public void setJtqtqk(String jtqtqk) {
		this.jtqtqk = jtqtqk;
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

	public JtcyForm getJtcy() {
		return jtcy;
	}

	public void setJtcy(JtcyForm jtcy) {
		this.jtcy = jtcy;
	}

	public String getYlzd11() {
		return ylzd11;
	}

	public void setYlzd11(String ylzd11) {
		this.ylzd11 = ylzd11;
	}

	public String getYlzd12() {
		return ylzd12;
	}

	public void setYlzd12(String ylzd12) {
		this.ylzd12 = ylzd12;
	}

	public String getYlzd13() {
		return ylzd13;
	}

	public void setYlzd13(String ylzd13) {
		this.ylzd13 = ylzd13;
	}

	public String getYlzd14() {
		return ylzd14;
	}

	public void setYlzd14(String ylzd14) {
		this.ylzd14 = ylzd14;
	}

	public String getYlzd15() {
		return ylzd15;
	}

	public void setYlzd15(String ylzd15) {
		this.ylzd15 = ylzd15;
	}

	public String getYlzd16() {
		return ylzd16;
	}

	public void setYlzd16(String ylzd16) {
		this.ylzd16 = ylzd16;
	}

	public String getYlzd17() {
		return ylzd17;
	}

	public void setYlzd17(String ylzd17) {
		this.ylzd17 = ylzd17;
	}

	public String getYlzd18() {
		return ylzd18;
	}

	public void setYlzd18(String ylzd18) {
		this.ylzd18 = ylzd18;
	}

	public String getYlzd19() {
		return ylzd19;
	}

	public void setYlzd19(String ylzd19) {
		this.ylzd19 = ylzd19;
	}

	public String getYlzd20() {
		return ylzd20;
	}

	public void setYlzd20(String ylzd20) {
		this.ylzd20 = ylzd20;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-26 下午02:36:09 
	 * @return		: the jtknlx
	 */
	public String[] getJtknlx() {
		return jtknlx;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-26 下午02:36:09 
	 * @param 		：jtknlx the jtknlx to set
	 */
	public void setJtknlx(String[] jtknlx) {
		this.jtknlx = jtknlx;
	}
	
}
