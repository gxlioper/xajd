package xgxt.pjpy.guizhdx;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class GuizhdxForm extends ActionForm {
	
	private static final long serialVersionUID = -5188106256207942345L;

	Pages pages = new Pages();
	
	private String nj;//年级
	
	private String xydm;//学院
	
	private String zydm;//专业
	
	private String bjdm;//班级
	
	private String bzrxm;//班主任姓名
	
	private String tyrs;//团员人数
	
	private String bjrs;//班级人数
	
	private String dyrs;//党员人数
	
	private String gbrs;//干部人数
	
	private String zzqk;//资助情况
	
	private String cfqk;//处分情况
	
	private String bkqk;//补考率、补考人次
	
	private String wmqs;//文明寝室
	
	private String jfqk;//欠费人数及金额
	
	private String rdqk;//入党和入党积极分子的人数、比例
	
	private String dyqk;//体育锻炼及达标情况
	
	private String jlqk;//本学年受各种奖励情况（名称及人数）
	
	private String zysj;//主要事迹
	
	private String bzryj;//班主任意见
	
	private String fdysh;//辅导员审核
	
	private String fdyyj;//辅导员意见
	
	private String xysh;//学院审核
	
	private String xyyj;//学院意见
	
	private String xxsh;//学校审核
	
	private String xxyj;//学校意见
	
	private String shjb;//审核级别
	
	private String sqr;//申请人
	
	private String save_bjdm;
	
	private String save_xn;
	
	private String save_nd;

	private String save_bzrxm;//班主任姓名
	
	private String save_tyrs;//团员人数
	
	private String save_bjrs;//班级人数
	
	private String save_dyrs;//党员人数
	
	private String save_gbrs;//干部人数
	
	private String save_zzqk;//资助情况
	
	private String save_wmqs;//文明寝室
	
	private String save_jfqk;//欠费人数及金额
	
	private String save_dyqk;//体育锻炼及达标情况
	
	private String save_zysj;//主要事迹
	
	private String save_bzryj;//班主任意见
	
	private String save_fdysh;//辅导员审核
	
	private String save_fdyyj;//辅导员意见
	
	private String save_xysh;//学院审核
	
	private String save_xyyj;//学院意见
	
	private String save_xxsh;//学校审核
	
	private String save_xxyj;//学校意见
	
	private String save_sqr;//申请人
	
	private String save_jxjdm;//奖学金代码
	
	private String save_xq;//学期
	
	private String save_fjsh;//分级审核
	
	private String save_sqly;//申请理由
	
	private String save_yhkh;//银行卡号
	
	private String save_sfqf;//是否欠费
	
	private String save_sqsj;//申请时间
	
	private String save_shyj;//审核意见 
	
	private String save_shjb;//审核级别
	
	private String save_rychdm;//荣誉称号代码
	
	private String save_xjfcj;//学绩分成绩
	
	private String save_xjfpm;//学绩分名次
	
	private String save_pxdj;//品行等级
	
	private String save_jkdj;//健康等级
	
	private String save_jthk;//家庭户口
	
	private String save_rkzs;//家庭人口总数
	
	private String save_jtyzsr;//家庭月总收入
	
	private String save_rjsr;//人均收入
	
	private String save_srly;//收入来源
	
	private String save_jtxxdz;//家庭详细住址
	
	private String save_yzbm;//邮政编码
	
	private String save_hjqk;//曾获何种奖励
	
	private String save_sqlb;//申请类别
	
	private String save_hjdj;//获奖等级
	
	private String save_zbdw;//主办单位
	
	private String save_bfdw;//颁发单位
	
	private String save_kwmckh;//刊物及刊号
	
	private String save_zzpm;//作者排名
	
	private String save_fbsj;//发表时间
	
	private String save_cg;//成果
	
	private String save_cgjb;//成果级别
	
	
	private String save_xxshyj;
	
	private String save_xyshyj;
	
	private String querylike_xh;
	
	private String querylike_xm;
	
	private String queryequals_jxjdm;
	
	private String queryequals_nd;
	
	private String queryequals_xq;
	
	private String queryequals_fjsh;
	
	private String queryequals_xn;

	private String queryequals_nj;
	
	private String queryequals_xydm;
	
	private String queryequals_zydm;
	
	private String queryequals_bjdm;
	
	private String queryequals_fdysh;
	
	private String queryequals_xysh;
	
	private String queryequals_xxsh;
	
	private String queryequals_shjb;
	
	private String queryequals_rychdm;
	
	private String queryequals_shzt;
	
	private String xn;
	private String nd;
	private String xq;
	private String jxjdm;
	private String jxjje;
	
	public String getQueryequals_shzt() {
		return queryequals_shzt;
	}

	public void setQueryequals_shzt(String queryequals_shzt) {
		this.queryequals_shzt = queryequals_shzt;
	}

	public String getQueryequals_rychdm() {
		return queryequals_rychdm;
	}

	public void setQueryequals_rychdm(String queryequals_rychdm) {
		this.queryequals_rychdm = queryequals_rychdm;
	}

	public String getQueryequals_shjb() {
		return queryequals_shjb;
	}

	public void setQueryequals_shjb(String queryequals_shjb) {
		this.queryequals_shjb = queryequals_shjb;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getBjrs() {
		return bjrs;
	}

	public void setBjrs(String bjrs) {
		this.bjrs = bjrs;
	}

	public String getBkqk() {
		return bkqk;
	}

	public void setBkqk(String bkqk) {
		this.bkqk = bkqk;
	}

	public String getBzrxm() {
		return bzrxm;
	}

	public void setBzrxm(String bzrxm) {
		this.bzrxm = bzrxm;
	}

	public String getBzryj() {
		return bzryj;
	}

	public void setBzryj(String bzryj) {
		this.bzryj = bzryj;
	}

	public String getCfqk() {
		return cfqk;
	}

	public void setCfqk(String cfqk) {
		this.cfqk = cfqk;
	}

	public String getDyqk() {
		return dyqk;
	}

	public void setDyqk(String dyqk) {
		this.dyqk = dyqk;
	}

	public String getDyrs() {
		return dyrs;
	}

	public void setDyrs(String dyrs) {
		this.dyrs = dyrs;
	}

	public String getFdysh() {
		return fdysh;
	}

	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}

	public String getFdyyj() {
		return fdyyj;
	}

	public void setFdyyj(String fdyyj) {
		this.fdyyj = fdyyj;
	}

	public String getGbrs() {
		return gbrs;
	}

	public void setGbrs(String gbrs) {
		this.gbrs = gbrs;
	}

	public String getJfqk() {
		return jfqk;
	}

	public void setJfqk(String jfqk) {
		this.jfqk = jfqk;
	}

	public String getJlqk() {
		return jlqk;
	}

	public void setJlqk(String jlqk) {
		this.jlqk = jlqk;
	}

	public String getRdqk() {
		return rdqk;
	}

	public void setRdqk(String rdqk) {
		this.rdqk = rdqk;
	}

	public String getTyrs() {
		return tyrs;
	}

	public void setTyrs(String tyrs) {
		this.tyrs = tyrs;
	}

	public String getWmqs() {
		return wmqs;
	}

	public void setWmqs(String wmqs) {
		this.wmqs = wmqs;
	}

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getXxyj() {
		return xxyj;
	}

	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getXyyj() {
		return xyyj;
	}

	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}

	public String getZysj() {
		return zysj;
	}

	public void setZysj(String zysj) {
		this.zysj = zysj;
	}

	public String getZzqk() {
		return zzqk;
	}

	public void setZzqk(String zzqk) {
		this.zzqk = zzqk;
	}

	public String getSave_bjrs() {
		return save_bjrs;
	}

	public void setSave_bjrs(String save_bjrs) {
		this.save_bjrs = save_bjrs;
	}

	public String getSave_bzrxm() {
		return save_bzrxm;
	}

	public void setSave_bzrxm(String save_bzrxm) {
		this.save_bzrxm = save_bzrxm;
	}

	public String getSave_bzryj() {
		return save_bzryj;
	}

	public void setSave_bzryj(String save_bzryj) {
		this.save_bzryj = save_bzryj;
	}

	public String getSave_dyqk() {
		return save_dyqk;
	}

	public void setSave_dyqk(String save_dyqk) {
		this.save_dyqk = save_dyqk;
	}

	public String getSave_dyrs() {
		return save_dyrs;
	}

	public void setSave_dyrs(String save_dyrs) {
		this.save_dyrs = save_dyrs;
	}

	public String getSave_fdysh() {
		return save_fdysh;
	}

	public void setSave_fdysh(String save_fdysh) {
		this.save_fdysh = save_fdysh;
	}

	public String getSave_fdyyj() {
		return save_fdyyj;
	}

	public void setSave_fdyyj(String save_fdyyj) {
		this.save_fdyyj = save_fdyyj;
	}

	public String getSave_gbrs() {
		return save_gbrs;
	}

	public void setSave_gbrs(String save_gbrs) {
		this.save_gbrs = save_gbrs;
	}

	public String getSave_jfqk() {
		return save_jfqk;
	}

	public void setSave_jfqk(String save_jfqk) {
		this.save_jfqk = save_jfqk;
	}

	public String getSave_tyrs() {
		return save_tyrs;
	}

	public void setSave_tyrs(String save_tyrs) {
		this.save_tyrs = save_tyrs;
	}

	public String getSave_wmqs() {
		return save_wmqs;
	}

	public void setSave_wmqs(String save_wmqs) {
		this.save_wmqs = save_wmqs;
	}

	public String getSave_xxsh() {
		return save_xxsh;
	}

	public void setSave_xxsh(String save_xxsh) {
		this.save_xxsh = save_xxsh;
	}

	public String getSave_xxyj() {
		return save_xxyj;
	}

	public void setSave_xxyj(String save_xxyj) {
		this.save_xxyj = save_xxyj;
	}

	public String getSave_xysh() {
		return save_xysh;
	}

	public void setSave_xysh(String save_xysh) {
		this.save_xysh = save_xysh;
	}

	public String getSave_xyyj() {
		return save_xyyj;
	}

	public void setSave_xyyj(String save_xyyj) {
		this.save_xyyj = save_xyyj;
	}

	public String getSave_zysj() {
		return save_zysj;
	}

	public void setSave_zysj(String save_zysj) {
		this.save_zysj = save_zysj;
	}

	public String getSave_zzqk() {
		return save_zzqk;
	}

	public void setSave_zzqk(String save_zzqk) {
		this.save_zzqk = save_zzqk;
	}

	public String getSave_bjdm() {
		return save_bjdm;
	}

	public void setSave_bjdm(String save_bjdm) {
		this.save_bjdm = save_bjdm;
	}

	public String getSave_nd() {
		return save_nd;
	}

	public void setSave_nd(String save_nd) {
		this.save_nd = save_nd;
	}

	public String getSave_xn() {
		return save_xn;
	}

	public void setSave_xn(String save_xn) {
		this.save_xn = save_xn;
	}

	public String getSave_sqr() {
		return save_sqr;
	}

	public void setSave_sqr(String save_sqr) {
		this.save_sqr = save_sqr;
	}

	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}

	public String getQueryequals_nj() {
		return queryequals_nj;
	}

	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}

	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}

	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}

	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}

	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}

	public String getQueryequals_fdysh() {
		return queryequals_fdysh;
	}

	public void setQueryequals_fdysh(String queryequals_fdysh) {
		this.queryequals_fdysh = queryequals_fdysh;
	}

	public String getQueryequals_xxsh() {
		return queryequals_xxsh;
	}

	public void setQueryequals_xxsh(String queryequals_xxsh) {
		this.queryequals_xxsh = queryequals_xxsh;
	}

	public String getQueryequals_xysh() {
		return queryequals_xysh;
	}

	public void setQueryequals_xysh(String queryequals_xysh) {
		this.queryequals_xysh = queryequals_xysh;
	}

	public String getQueryequals_xn() {
		return queryequals_xn;
	}

	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}

	public String getSave_jxjdm() {
		return save_jxjdm;
	}

	public void setSave_jxjdm(String save_jxjdm) {
		this.save_jxjdm = save_jxjdm;
	}

	public String getSave_xq() {
		return save_xq;
	}

	public void setSave_xq(String save_xq) {
		this.save_xq = save_xq;
	}

	public String getSave_fjsh() {
		return save_fjsh;
	}

	public void setSave_fjsh(String save_fjsh) {
		this.save_fjsh = save_fjsh;
	}

	public String getQueryequals_fjsh() {
		return queryequals_fjsh;
	}

	public void setQueryequals_fjsh(String queryequals_fjsh) {
		this.queryequals_fjsh = queryequals_fjsh;
	}

	public String getQueryequals_jxjdm() {
		return queryequals_jxjdm;
	}

	public void setQueryequals_jxjdm(String queryequals_jxjdm) {
		this.queryequals_jxjdm = queryequals_jxjdm;
	}

	public String getQueryequals_nd() {
		return queryequals_nd;
	}

	public void setQueryequals_nd(String queryequals_nd) {
		this.queryequals_nd = queryequals_nd;
	}

	public String getQueryequals_xq() {
		return queryequals_xq;
	}

	public void setQueryequals_xq(String queryequals_xq) {
		this.queryequals_xq = queryequals_xq;
	}

	public String getSave_sfqf() {
		return save_sfqf;
	}

	public void setSave_sfqf(String save_sfqf) {
		this.save_sfqf = save_sfqf;
	}

	public String getSave_sqly() {
		return save_sqly;
	}

	public void setSave_sqly(String save_sqly) {
		this.save_sqly = save_sqly;
	}

	public String getSave_sqsj() {
		return save_sqsj;
	}

	public void setSave_sqsj(String save_sqsj) {
		this.save_sqsj = save_sqsj;
	}

	public String getSave_yhkh() {
		return save_yhkh;
	}

	public void setSave_yhkh(String save_yhkh) {
		this.save_yhkh = save_yhkh;
	}

	public String getQuerylike_xh() {
		return querylike_xh;
	}

	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}

	public String getQuerylike_xm() {
		return querylike_xm;
	}

	public void setQuerylike_xm(String querylike_xm) {
		this.querylike_xm = querylike_xm;
	}

	public String getSave_shyj() {
		return save_shyj;
	}

	public void setSave_shyj(String save_shyj) {
		this.save_shyj = save_shyj;
	}

	public String getSave_shjb() {
		return save_shjb;
	}

	public void setSave_shjb(String save_shjb) {
		this.save_shjb = save_shjb;
	}

	public String getSave_rychdm() {
		return save_rychdm;
	}

	public void setSave_rychdm(String save_rychdm) {
		this.save_rychdm = save_rychdm;
	}

	public String getSave_xxshyj() {
		return save_xxshyj;
	}

	public void setSave_xxshyj(String save_xxshyj) {
		this.save_xxshyj = save_xxshyj;
	}

	public String getSave_xyshyj() {
		return save_xyshyj;
	}

	public void setSave_xyshyj(String save_xyshyj) {
		this.save_xyshyj = save_xyshyj;
	}

	public String getShjb() {
		return shjb;
	}

	public void setShjb(String shjb) {
		this.shjb = shjb;
	}

	public String getSave_xjfcj() {
		return save_xjfcj;
	}

	public void setSave_xjfcj(String save_xjfcj) {
		this.save_xjfcj = save_xjfcj;
	}

	public String getSave_xjfpm() {
		return save_xjfpm;
	}

	public void setSave_xjfpm(String save_xjfpm) {
		this.save_xjfpm = save_xjfpm;
	}

	public String getSave_jkdj() {
		return save_jkdj;
	}

	public void setSave_jkdj(String save_jkdj) {
		this.save_jkdj = save_jkdj;
	}

	public String getSave_pxdj() {
		return save_pxdj;
	}

	public void setSave_pxdj(String save_pxdj) {
		this.save_pxdj = save_pxdj;
	}

	public String getSave_hjqk() {
		return save_hjqk;
	}

	public void setSave_hjqk(String save_hjqk) {
		this.save_hjqk = save_hjqk;
	}

	public String getSave_jthk() {
		return save_jthk;
	}

	public void setSave_jthk(String save_jthk) {
		this.save_jthk = save_jthk;
	}

	public String getSave_jtxxdz() {
		return save_jtxxdz;
	}

	public void setSave_jtxxdz(String save_jtxxdz) {
		this.save_jtxxdz = save_jtxxdz;
	}

	public String getSave_jtyzsr() {
		return save_jtyzsr;
	}

	public void setSave_jtyzsr(String save_jtyzsr) {
		this.save_jtyzsr = save_jtyzsr;
	}

	public String getSave_rjsr() {
		return save_rjsr;
	}

	public void setSave_rjsr(String save_rjsr) {
		this.save_rjsr = save_rjsr;
	}

	public String getSave_rkzs() {
		return save_rkzs;
	}

	public void setSave_rkzs(String save_rkzs) {
		this.save_rkzs = save_rkzs;
	}

	public String getSave_srly() {
		return save_srly;
	}

	public void setSave_srly(String save_srly) {
		this.save_srly = save_srly;
	}

	public String getSave_yzbm() {
		return save_yzbm;
	}

	public void setSave_yzbm(String save_yzbm) {
		this.save_yzbm = save_yzbm;
	}

	public String getSave_bfdw() {
		return save_bfdw;
	}

	public void setSave_bfdw(String save_bfdw) {
		this.save_bfdw = save_bfdw;
	}

	public String getSave_cg() {
		return save_cg;
	}

	public void setSave_cg(String save_cg) {
		this.save_cg = save_cg;
	}

	public String getSave_cgjb() {
		return save_cgjb;
	}

	public void setSave_cgjb(String save_cgjb) {
		this.save_cgjb = save_cgjb;
	}

	public String getSave_fbsj() {
		return save_fbsj;
	}

	public void setSave_fbsj(String save_fbsj) {
		this.save_fbsj = save_fbsj;
	}

	public String getSave_hjdj() {
		return save_hjdj;
	}

	public void setSave_hjdj(String save_hjdj) {
		this.save_hjdj = save_hjdj;
	}

	public String getSave_kwmckh() {
		return save_kwmckh;
	}

	public void setSave_kwmckh(String save_kwmckh) {
		this.save_kwmckh = save_kwmckh;
	}

	public String getSave_sqlb() {
		return save_sqlb;
	}

	public void setSave_sqlb(String save_sqlb) {
		this.save_sqlb = save_sqlb;
	}

	public String getSave_zbdw() {
		return save_zbdw;
	}

	public void setSave_zbdw(String save_zbdw) {
		this.save_zbdw = save_zbdw;
	}

	public String getSave_zzpm() {
		return save_zzpm;
	}

	public void setSave_zzpm(String save_zzpm) {
		this.save_zzpm = save_zzpm;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getJxjdm() {
		return jxjdm;
	}

	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}

	public String getJxjje() {
		return jxjje;
	}

	public void setJxjje(String jxjje) {
		this.jxjje = jxjje;
	}

}
