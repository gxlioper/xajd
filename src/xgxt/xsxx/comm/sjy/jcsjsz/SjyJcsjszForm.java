package xgxt.xsxx.comm.sjy.jcsjsz;

import xgxt.xsxx.comm.XsxxCommForm;

public class SjyJcsjszForm extends XsxxCommForm {

	private static final long serialVersionUID = 1L;

	// ================字段设置====================

	private String step;// 操作

	private String rowNum = "16";// 列表行数

	private String stepNum = "9"; // 操作步骤数

	private String[] ch_zd;// 选中字段

	private String[] ch_zdm;// 选中字段名

	private String[] zd;// '字段';

	private String[] sjy;// '数据源';

	private String[] xgwz;// '学工为准';

	private String[] zdm;// '字段名';

	private String[] xsmc;// '显示名称';

	private String[] ymxs;// '页面显示';

	private String[] lrxz;// '录入限制';

	private String[] wkxz;// '为空限制';

	private String[] lrxs;// '录入形式';

	private String[] lyb;// '数据来源表';

	private String[] lybm;// '数据来源表（名称）';

	private String[] sfqy;// '是否启用';

	private String search_zd;// '字段';

	private String search_sjy;// '数据源';

	private String search_xgwz;// '学工为准';

	private String search_zdm;// '字段名';

	private String search_xsmc;// '显示名称';

	private String search_ymxs;// '页面显示';

	private String search_lrxz;// '录入限制';

	private String search_wkxz;// '为空限制';

	private String search_lrxs;// '录入形式';

	private String search_lyb;// '数据来源表';

	private String search_lybm;// '数据来源表名称';

	private String search_sfqy;// '是否启用';

	// ================字段设置 end================

	// ================页面设置====================

	private String qyzd;// '启用字段';

	private String[] xsqdm;// '显示区代码';

	private String[] xsqmc;// '显示区名称';

	private String[] szhs;// '所占行数';

	private String[] zpxs;// '照片显示';

	private String[] zpwz;// '照片位置';

	private String[] zpszhs;// '照片所占行数';

	private String[] xssx;// '显示顺序';

	private String[] sfzk;// '是否展开';

	private String[] hbh_szxsq;// '合并行所在显示区

	private String[] hbh;// '合并行

	private String[] zd_szxsq;// '字段所在显示区

	private String[] szh;// '所在行';

	private String[] szl;// '所在列'

	private String[] sfzd;// '是否置顶';

	private String search_xsqmc;// '显示区名称';

	private String xsqsfzk;// '显示区是否展开';

	private String xsqsfqy;// '显示区是否显示';

	private String xsqsfzd;// '显示区是否是否置顶';

	// ================页面设置 end====================
	
	// =================详细页面设置======================
	private String[]xsxm_can;//可以移动显示项目
	
	private String[]xsxm_no;//不可移动显示项目
	
	private String[]sfxs_can;//可以移动TABLE显示类型保存
	
	private String[]sfxs_no;//不可移动TABLE显示类型保存
	
	private String[]xslxArr;//显示类型
	// =================详细页面设置end======================

	public String[] getXslxArr() {
		return xslxArr;
	}

	public void setXslxArr(String[] xslxArr) {
		this.xslxArr = xslxArr;
	}

	public String[] getXsxm_can() {
		return xsxm_can;
	}

	public void setXsxm_can(String[] xsxm_can) {
		this.xsxm_can = xsxm_can;
	}

	public String[] getXsxm_no() {
		return xsxm_no;
	}

	public void setXsxm_no(String[] xsxm_no) {
		this.xsxm_no = xsxm_no;
	}

	public String[] getSfzd() {
		return sfzd;
	}

	public void setSfzd(String[] sfzd) {
		this.sfzd = sfzd;
	}

	public String getXsqsfzd() {
		return xsqsfzd;
	}

	public void setXsqsfzd(String xsqsfzd) {
		this.xsqsfzd = xsqsfzd;
	}

	public String[] getHbh() {
		return hbh;
	}

	public void setHbh(String[] hbh) {
		this.hbh = hbh;
	}

	public String[] getLrxs() {
		return lrxs;
	}

	public void setLrxs(String[] lrxs) {
		this.lrxs = lrxs;
	}

	public String[] getLrxz() {
		return lrxz;
	}

	public void setLrxz(String[] lrxz) {
		this.lrxz = lrxz;
	}

	public String getQyzd() {
		return qyzd;
	}

	public void setQyzd(String qyzd) {
		this.qyzd = qyzd;
	}

	public String getSearch_lrxs() {
		return search_lrxs;
	}

	public void setSearch_lrxs(String search_lrxs) {
		this.search_lrxs = search_lrxs;
	}

	public String getSearch_lrxz() {
		return search_lrxz;
	}

	public void setSearch_lrxz(String search_lrxz) {
		this.search_lrxz = search_lrxz;
	}

	public String getSearch_sfqy() {
		return search_sfqy;
	}

	public void setSearch_sfqy(String search_sfqy) {
		this.search_sfqy = search_sfqy;
	}

	public String getSearch_sjy() {
		return search_sjy;
	}

	public void setSearch_sjy(String search_sjy) {
		this.search_sjy = search_sjy;
	}

	public String getSearch_wkxz() {
		return search_wkxz;
	}

	public void setSearch_wkxz(String search_wkxz) {
		this.search_wkxz = search_wkxz;
	}

	public String getSearch_xgwz() {
		return search_xgwz;
	}

	public void setSearch_xgwz(String search_xgwz) {
		this.search_xgwz = search_xgwz;
	}

	public String getSearch_xsmc() {
		return search_xsmc;
	}

	public void setSearch_xsmc(String search_xsmc) {
		this.search_xsmc = search_xsmc;
	}

	public String getSearch_ymxs() {
		return search_ymxs;
	}

	public void setSearch_ymxs(String search_ymxs) {
		this.search_ymxs = search_ymxs;
	}

	public String getSearch_zd() {
		return search_zd;
	}

	public void setSearch_zd(String search_zd) {
		this.search_zd = search_zd;
	}

	public String getSearch_zdm() {
		return search_zdm;
	}

	public void setSearch_zdm(String search_zdm) {
		this.search_zdm = search_zdm;
	}

	public String[] getSfqy() {
		return sfqy;
	}

	public void setSfqy(String[] sfqy) {
		this.sfqy = sfqy;
	}

	public String[] getSfzk() {
		return sfzk;
	}

	public void setSfzk(String[] sfzk) {
		this.sfzk = sfzk;
	}

	public String[] getSjy() {
		return sjy;
	}

	public void setSjy(String[] sjy) {
		this.sjy = sjy;
	}

	public String[] getSzh() {
		return szh;
	}

	public void setSzh(String[] szh) {
		this.szh = szh;
	}

	public String[] getSzhs() {
		return szhs;
	}

	public void setSzhs(String[] szhs) {
		this.szhs = szhs;
	}

	public String[] getSzl() {
		return szl;
	}

	public void setSzl(String[] szl) {
		this.szl = szl;
	}

	public String[] getWkxz() {
		return wkxz;
	}

	public void setWkxz(String[] wkxz) {
		this.wkxz = wkxz;
	}

	public String[] getXgwz() {
		return xgwz;
	}

	public void setXgwz(String[] xgwz) {
		this.xgwz = xgwz;
	}

	public String[] getXsmc() {
		return xsmc;
	}

	public void setXsmc(String[] xsmc) {
		this.xsmc = xsmc;
	}

	public String[] getXsqdm() {
		return xsqdm;
	}

	public void setXsqdm(String[] xsqdm) {
		this.xsqdm = xsqdm;
	}

	public String[] getXsqmc() {
		return xsqmc;
	}

	public void setXsqmc(String[] xsqmc) {
		this.xsqmc = xsqmc;
	}

	public String[] getXssx() {
		return xssx;
	}

	public void setXssx(String[] xssx) {
		this.xssx = xssx;
	}

	public String[] getYmxs() {
		return ymxs;
	}

	public void setYmxs(String[] ymxs) {
		this.ymxs = ymxs;
	}

	public String[] getZd() {
		return zd;
	}

	public void setZd(String[] zd) {
		this.zd = zd;
	}

	public String[] getZdm() {
		return zdm;
	}

	public void setZdm(String[] zdm) {
		this.zdm = zdm;
	}

	public String[] getZpszhs() {
		return zpszhs;
	}

	public void setZpszhs(String[] zpszhs) {
		this.zpszhs = zpszhs;
	}

	public String[] getZpwz() {
		return zpwz;
	}

	public void setZpwz(String[] zpwz) {
		this.zpwz = zpwz;
	}

	public String[] getZpxs() {
		return zpxs;
	}

	public void setZpxs(String[] zpxs) {
		this.zpxs = zpxs;
	}

	public String getXsqsfqy() {
		return xsqsfqy;
	}

	public void setXsqsfqy(String xsqsfqy) {
		this.xsqsfqy = xsqsfqy;
	}

	public String[] getHbh_szxsq() {
		return hbh_szxsq;
	}

	public void setHbh_szxsq(String[] hbh_szxsq) {
		this.hbh_szxsq = hbh_szxsq;
	}

	public String[] getZd_szxsq() {
		return zd_szxsq;
	}

	public void setZd_szxsq(String[] zd_szxsq) {
		this.zd_szxsq = zd_szxsq;
	}

	public String[] getLyb() {
		return lyb;
	}

	public void setLyb(String[] lyb) {
		this.lyb = lyb;
	}

	public String getSearch_lyb() {
		return search_lyb;
	}

	public void setSearch_lyb(String search_lyb) {
		this.search_lyb = search_lyb;
	}

	public String getXsqsfzk() {
		return xsqsfzk;
	}

	public void setXsqsfzk(String xsqsfzk) {
		this.xsqsfzk = xsqsfzk;
	}

	public String getSearch_xsqmc() {
		return search_xsqmc;
	}

	public void setSearch_xsqmc(String search_xsqmc) {
		this.search_xsqmc = search_xsqmc;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	public String getStepNum() {
		return stepNum;
	}

	public void setStepNum(String stepNum) {
		this.stepNum = stepNum;
	}

	public String[] getCh_zd() {
		return ch_zd;
	}

	public void setCh_zd(String[] ch_zd) {
		this.ch_zd = ch_zd;
	}

	public String[] getCh_zdm() {
		return ch_zdm;
	}

	public void setCh_zdm(String[] ch_zdm) {
		this.ch_zdm = ch_zdm;
	}

	public String[] getLybm() {
		return lybm;
	}

	public void setLybm(String[] lybm) {
		this.lybm = lybm;
	}

	public String getSearch_lybm() {
		return search_lybm;
	}

	public void setSearch_lybm(String search_lybm) {
		this.search_lybm = search_lybm;
	}

	public String[] getSfxs_can() {
		return sfxs_can;
	}

	public void setSfxs_can(String[] sfxs_can) {
		this.sfxs_can = sfxs_can;
	}

	public String[] getSfxs_no() {
		return sfxs_no;
	}

	public void setSfxs_no(String[] sfxs_no) {
		this.sfxs_no = sfxs_no;
	}

	// ================页面设置 end================

}
