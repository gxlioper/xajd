/**
 * @部门:学工产品事业部
 * @日期：2013-6-17 上午09:15:55 
 */  
package com.zfsoft.xgxt.qgzx.xsgw;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学管理模块
 * @类功能描述: 学生岗位-我的岗位申请
 * @作者： zhangjw
 * @时间： 2013-6-17 上午09:09:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WdgwsqForm  extends ActionForm{
	/** 
	 * @变量 serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String sqbh;//申请编号
	
	private String xh;//学号
	
	private String xm;
	
	private String xn;//学年
	
	private String yrdwmc;//用人单位名称
	private String yrdwdm;
	
	private String gwdm;//岗位代码
	
	private String gwmc;//岗位名称
	
	private String gwxzmc;//岗位类别名称

	private String gwxzdm;//工作性质（临时，正式）

	private String gwlx;//岗位类型（临时，长期）

	private String zpkssj;

	private String zpjssj;

	private String gssx;

	private String cq;

	private String sfzd;

	private String sfxsgz;

	private String xqrs;//需求人数
	
	private String zgrs;//在岗人数
	
	private String knss;//困难生数
	
	private String sqsj;//申请时间
	
	private String shzt;//审核状态
	
	private String gwms;//岗位描述
	
	private String splc;//审批流程
	
	private String sqly;//申请理由
	
	private String bz;//备注
	
	private String gwryyq;//岗位人员要求
	
	private String gwyqryxg;//岗位人员要求
	
	private String shgw;

	private String gwcjsx;//岗位酬金上限
	
	private String mxrq; //明细日期
	
	private String sffcap; //是否服从安排
	
	private String sfzqscy; // 是否自强社成员
	private String sfsgwsqsxzmc; // 是否受岗位申请数限制
	
	/**浙江中医药个性化字段
	 */
	private String yhtc;
	private String jtqk;
	private String qgzxrs;
	private String gzdd;
	private String gzsj;
	private String gznr;
	private String xq;
	private String xqmc;
	
	/**
	 * 武昌首义个性化字段
	 */
	private String gwcjbz;
	private String jfhb;
	private String zc;
	
	/**
	 * 衢州学院个性化判断
	 */
	private String xxcj;
	private String stzk;


	private String lxr;
	private String lxdh;

	public String getCq() {
		return cq;
	}

	public void setCq(String cq) {
		this.cq = cq;
	}

	public String getSfzd() {
		return sfzd;
	}

	public void setSfzd(String sfzd) {
		this.sfzd = sfzd;
	}

	public String getSfxsgz() {
		return sfxsgz;
	}

	public void setSfxsgz(String sfxsgz) {
		this.sfxsgz = sfxsgz;
	}

	public String getGwxzdm() {
		return gwxzdm;
	}

	public void setGwxzdm(String gwxzdm) {
		this.gwxzdm = gwxzdm;
	}

	public String getGwlx() {
		return gwlx;
	}

	public void setGwlx(String gwlx) {
		this.gwlx = gwlx;
	}

	public String getZpkssj() {
		return zpkssj;
	}

	public void setZpkssj(String zpkssj) {
		this.zpkssj = zpkssj;
	}

	public String getZpjssj() {
		return zpjssj;
	}

	public void setZpjssj(String zpjssj) {
		this.zpjssj = zpjssj;
	}

	public String getGssx() {
		return gssx;
	}

	public void setGssx(String gssx) {
		this.gssx = gssx;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getXxcj() {
		return xxcj;
	}
	public void setXxcj(String xxcj) {
		this.xxcj = xxcj;
	}
	public String getStzk() {
		return stzk;
	}
	public void setStzk(String stzk) {
		this.stzk = stzk;
	}
	//温州大学个性化
	private String sqxyms;
	
	
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the gzdd
	 */
	public String getGzdd() {
		return gzdd;
	}
	/**
	 * @param gzdd要设置的 gzdd
	 */
	public void setGzdd(String gzdd) {
		this.gzdd = gzdd;
	}
	/**
	 * @return the gzsj
	 */
	public String getGzsj() {
		return gzsj;
	}
	/**
	 * @param gzsj要设置的 gzsj
	 */
	public void setGzsj(String gzsj) {
		this.gzsj = gzsj;
	}
	/**
	 * @return the gznr
	 */
	public String getGznr() {
		return gznr;
	}
	/**
	 * @param gznr要设置的 gznr
	 */
	public void setGznr(String gznr) {
		this.gznr = gznr;
	}
	/**
	 * @return the yhtc
	 */
	public String getYhtc() {
		return yhtc;
	}
	/**
	 * @param yhtc要设置的 yhtc
	 */
	public void setYhtc(String yhtc) {
		this.yhtc = yhtc;
	}
	/**
	 * @return the jtqk
	 */
	public String getJtqk() {
		return jtqk;
	}
	/**
	 * @param jtqk要设置的 jtqk
	 */
	public void setJtqk(String jtqk) {
		this.jtqk = jtqk;
	}
	/**
	 * @return the qgzxrs
	 */
	public String getQgzxrs() {
		return qgzxrs;
	}
	/**
	 * @param qgzxrs要设置的 qgzxrs
	 */
	public void setQgzxrs(String qgzxrs) {
		this.qgzxrs = qgzxrs;
	}
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
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getYrdwmc() {
		return yrdwmc;
	}
	public void setYrdwmc(String yrdwmc) {
		this.yrdwmc = yrdwmc;
	}
	public String getGwmc() {
		return gwmc;
	}
	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}
	public String getGwxzmc() {
		return gwxzmc;
	}
	public void setGwxzmc(String gwxzmc) {
		this.gwxzmc = gwxzmc;
	}
	public String getXqrs() {
		return xqrs;
	}
	public void setXqrs(String xqrs) {
		this.xqrs = xqrs;
	}
	public String getZgrs() {
		return zgrs;
	}
	public void setZgrs(String zgrs) {
		this.zgrs = zgrs;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getKnss() {
		return knss;
	}
	public void setKnss(String knss) {
		this.knss = knss;
	}
	public String getGwdm() {
		return gwdm;
	}
	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}
	public String getGwms() {
		return gwms;
	}
	public void setGwms(String gwms) {
		this.gwms = gwms;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getGwryyq() {
		return gwryyq;
	}
	public void setGwryyq(String gwryyq) {
		this.gwryyq = gwryyq;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSqbh() {
		return sqbh;
	}
	public void setSqbh(String sqbh) {
		this.sqbh = sqbh;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the shgw
	 */
	public String getShgw() {
		return shgw;
	}
	/**
	 * @param shgw要设置的 shgw
	 */
	public void setShgw(String shgw) {
		this.shgw = shgw;
	}
	/**
	 * @return the yrdwdm
	 */
	public String getYrdwdm() {
		return yrdwdm;
	}
	/**
	 * @param yrdwdm要设置的 yrdwdm
	 */
	public void setYrdwdm(String yrdwdm) {
		this.yrdwdm = yrdwdm;
	}
	/**
	 * @return the gwcjsx
	 */
	public String getGwcjsx() {
		return gwcjsx;
	}
	/**
	 * @param gwcjsx要设置的 gwcjsx
	 */
	public void setGwcjsx(String gwcjsx) {
		this.gwcjsx = gwcjsx;
	}
	/**
	 * @return the gwyqryxg
	 */
	public String getGwyqryxg() {
		return gwyqryxg;
	}
	/**
	 * @param gwyqryxg要设置的 gwyqryxg
	 */
	public void setGwyqryxg(String gwyqryxg) {
		this.gwyqryxg = gwyqryxg;
	}
	/**
	 * @return the mxrq
	 */
	public String getMxrq() {
		return mxrq;
	}
	/**
	 * @param mxrq要设置的 mxrq
	 */
	public void setMxrq(String mxrq) {
		this.mxrq = mxrq;
	}
	/**
	 * @return the sffcap
	 */
	public String getSffcap() {
		return sffcap;
	}
	/**
	 * @param sffcap要设置的 sffcap
	 */
	public void setSffcap(String sffcap) {
		this.sffcap = sffcap;
	}
	/**
	 * @return the sfzqscy
	 */
	public String getSfzqscy() {
		return sfzqscy;
	}
	/**
	 * @param sfzqscy要设置的 sfzqscy
	 */
	public void setSfzqscy(String sfzqscy) {
		this.sfzqscy = sfzqscy;
	}
	/**
	 * @return the sfsgwsqsxzmc
	 */
	public String getSfsgwsqsxzmc() {
		return sfsgwsqsxzmc;
	}
	/**
	 * @param sfsgwsqsxzmc要设置的 sfsgwsqsxzmc
	 */
	public void setSfsgwsqsxzmc(String sfsgwsqsxzmc) {
		this.sfsgwsqsxzmc = sfsgwsqsxzmc;
	}
	/**
	 * @return the gwcjbz
	 */
	public String getGwcjbz() {
		return gwcjbz;
	}
	/**
	 * @param gwcjbz要设置的 gwcjbz
	 */
	public void setGwcjbz(String gwcjbz) {
		this.gwcjbz = gwcjbz;
	}
	/**
	 * @return the jfhb
	 */
	public String getJfhb() {
		return jfhb;
	}
	/**
	 * @param jfhb要设置的 jfhb
	 */
	public void setJfhb(String jfhb) {
		this.jfhb = jfhb;
	}
	/**
	 * @return the zc
	 */
	public String getZc() {
		return zc;
	}
	/**
	 * @param zc要设置的 zc
	 */
	public void setZc(String zc) {
		this.zc = zc;
	}
	/**
	 * @return the sqxyms
	 */
	public String getSqxyms() {
		return sqxyms;
	}
	/**
	 * @param sqxyms要设置的 sqxyms
	 */
	public void setSqxyms(String sqxyms) {
		this.sqxyms = sqxyms;
	}
	
	
	
}
