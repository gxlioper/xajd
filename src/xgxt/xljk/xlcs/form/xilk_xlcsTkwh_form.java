
package xgxt.xljk.xlcs.form;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;
import xgxt.utils.Pages;

/**
 * MyEclipse Struts Creation date: 07-17-2007
 */
public class xilk_xlcsTkwh_form extends ActionForm {

	private static final long serialVersionUID = 1L;

	DealString deal = new DealString();

	private String errMsg;

	private String[] errMsgs;

	private String pk = "";

	private String tableName = "";

	private String act = "";

	private String realTable = "";

	private String message = "";

	private String sjlsh = "";// 试卷流水号

	private String sjm = "";// 试卷名称

	private String sjxd = "";// 时间限定

	private String sjsm = "";// 试卷说明

	private String sjxsbj = "";// 试卷显示标记

	private String jrsj = "";// 试卷加入时间

	private String stlsh = "";// 试题流水号

	private String stnr = "";// 试题内容

	private String stlxdm = "";// 试题类型代码

	private String stlxmc = "";// 试题类型名称

	private String stndjbdm = "";// 试题难度级别代码

	private String stjffs = "";// 试题记分方式

	private String stfz = "";// 试题分值

	private String stda = "";// 试题答案

	private String stdajs = "";// 试题答案解释

	private String stxsbj = "";// 试题显示标记

	private String sslxdm = "";// 试题所属类别代码

	private String sslxmc = "";// 试题所属类别名称

	private String stndjbmc = "";// 试题难度级别名称

	private String xxlsh = "";// 选项流水号

	private String xxxh = "";// 选项序号

	private String xxnr = "";// 选项内容

	private String xxfz = "";// 选项分值

	private String xxxsbj = "";// 选项显示标记

	private String sjst_xnid = "";// 试卷试题虚拟主键

	private String stxh = "";// 试题序号

	private String yxstlb = "";//

	private String yxstStr = "";

	private String xn_id; // 虚拟主键
	
	private String temp; // 临时属性
	
	private String yzdm; // 因子代码
	
	private String yzmc; // 因子名称
	
	private String xlcsxmdm; // 心理测试项目代码
	
	private String xlcsxmmc; // 心理测试项目名称
	
	private Pages pages = new Pages();

	public String getYxstStr() {
		return yxstStr;
	}

	public void setYxstStr(String yxstStr) {
		this.yxstStr = yxstStr;
	}

	public String getYxstlb() {
		return yxstlb;
	}

	public void setYxstlb(String yxstlb) {
		this.yxstlb = yxstlb;
	}

	public String getStxh() {
		return stxh;
	}

	public void setStxh(String stxh) {
		this.stxh = stxh;
	}

	public String getSjst_xnid() {
		return sjst_xnid;
	}

	public void setSjst_xnid(String sjst_xnid) {
		this.sjst_xnid = sjst_xnid;
	}

	public String getXxfz() {
		return xxfz;
	}

	public void setXxfz(String xxfz) {
		this.xxfz = xxfz;
	}

	public String getXxlsh() {
		return xxlsh;
	}

	public void setXxlsh(String xxlsh) {
		this.xxlsh = xxlsh;
	}

	public String getXxnr() {
		return xxnr;
	}

	public void setXxnr(String xxnr) {
		this.xxnr = xxnr;
	}

	public String getXxxh() {
		return xxxh;
	}

	public void setXxxh(String xxxh) {
		this.xxxh = xxxh;
	}

	public String getXxxsbj() {
		return xxxsbj;
	}

	public void setXxxsbj(String xxxsbj) {
		this.xxxsbj = xxxsbj;
	}

	public String getStndjbmc() {
		return stndjbmc;
	}

	public void setStndjbmc(String stndjbmc) {
		this.stndjbmc = stndjbmc;
	}

	public String getStda() {
		return stda;
	}

	public void setStda(String stda) {
		this.stda = stda;
	}

	public String getStdajs() {
		return stdajs;
	}

	public void setStdajs(String stdajs) {
		this.stdajs = stdajs;
	}

	public String getStfz() {
		return stfz;
	}

	public void setStfz(String stfz) {
		this.stfz = stfz;
	}

	public String getStjffs() {
		return stjffs;
	}

	public void setStjffs(String stjffs) {
		this.stjffs = stjffs;
	}

	public String getStlxdm() {
		return stlxdm;
	}

	public void setStlxdm(String stlxdm) {
		this.stlxdm = stlxdm;
	}

	public String getStndjbdm() {
		return stndjbdm;
	}

	public void setStndjbdm(String stndjbdm) {
		this.stndjbdm = stndjbdm;
	}

	public String getStnr() {
		return stnr;
	}

	public void setStnr(String stnr) {
		this.stnr = stnr;
	}

	public String getStxsbj() {
		return stxsbj;
	}

	public void setStxsbj(String stxsbj) {
		this.stxsbj = stxsbj;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJrsj() {
		return jrsj;
	}

	public void setJrsj(String jrsj) {
		this.jrsj = jrsj;
	}

	public String getSjxsbj() {
		return sjxsbj;
	}

	public void setSjxsbj(String sjxsbj) {
		this.sjxsbj = sjxsbj;
	}

	public String getSjm() {
		return sjm;
	}

	public void setSjm(String sjm) {
		this.sjm = sjm;
	}

	public String getSjsm() {
		return sjsm;
	}

	public void setSjsm(String sjsm) {
		this.sjsm = sjsm;
	}

	public String getSjxd() {
		return sjxd;
	}

	public void setSjxd(String sjxd) {
		this.sjxd = sjxd;
	}

	public String getSjlsh() {
		return sjlsh;
	}

	public void setSjlsh(String sjlsh) {
		this.sjlsh = sjlsh;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getRealTable() {
		return realTable;
	}

	public void setRealTable(String realTable) {
		this.realTable = realTable;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getStlsh() {
		return stlsh;
	}

	public void setStlsh(String stlsh) {
		this.stlsh = stlsh;
	}

	public void deal_gbk() {
		this.sjlsh = DealString.toGBK(sjlsh);
		this.sjm = DealString.toGBK(sjm);
		this.sjxd = DealString.toGBK(sjxd);
		this.sjsm = DealString.toGBK(sjsm);
		this.sjxsbj = DealString.toGBK(sjxsbj);
		this.jrsj = DealString.toGBK(jrsj);

		this.stlsh = DealString.toGBK(stlsh);
		this.stnr = DealString.toGBK(stnr);
		this.stlxdm = DealString.toGBK(stlxdm);
		this.stndjbdm = DealString.toGBK(stndjbdm);
		this.stjffs = DealString.toGBK(stjffs);
		this.stfz = DealString.toGBK(stfz);
		this.stda = DealString.toGBK(stda);
		this.stdajs = DealString.toGBK(stdajs);
		this.stxsbj = DealString.toGBK(stxsbj);
		this.sslxdm = DealString.toGBK(sslxdm);
		this.sslxmc = DealString.toGBK(sslxmc);
		this.stndjbmc = DealString.toGBK(stndjbmc);
		this.stlxmc = DealString.toGBK(stlxmc);

		this.xxlsh = DealString.toGBK(xxlsh);
		this.xxxh = DealString.toGBK(xxxh);
		this.xxnr = DealString.toGBK(xxnr);
		this.xxfz = DealString.toGBK(xxfz);
		this.xxxsbj = DealString.toGBK(xxxsbj);

		this.yxstlb = DealString.toGBK(yxstlb);
		this.yxstStr = DealString.toGBK(yxstStr);
	}

	public String getSslxdm() {
		return sslxdm;
	}

	public void setSslxdm(String sslxdm) {
		this.sslxdm = sslxdm;
	}

	public String getSslxmc() {
		return sslxmc;
	}

	public void setSslxmc(String sslxmc) {
		this.sslxmc = sslxmc;
	}

	public String getStlxmc() {
		return stlxmc;
	}

	public void setStlxmc(String stlxmc) {
		this.stlxmc = stlxmc;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String[] getErrMsgs() {
		return errMsgs;
	}

	public void setErrMsgs(String[] errMsgs) {
		this.errMsgs = errMsgs;
	}

	public String getXn_id() {
		return xn_id;
	}

	public void setXn_id(String xn_id) {
		this.xn_id = xn_id;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getYzdm() {
		return yzdm;
	}

	public void setYzdm(String yzdm) {
		this.yzdm = yzdm;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public String getXlcsxmdm() {
		return xlcsxmdm;
	}

	public void setXlcsxmdm(String xlcsxmdm) {
		this.xlcsxmdm = xlcsxmdm;
	}

	public String getXlcsxmmc() {
		return xlcsxmmc;
	}

	public void setXlcsxmmc(String xlcsxmmc) {
		this.xlcsxmmc = xlcsxmmc;
	}

}