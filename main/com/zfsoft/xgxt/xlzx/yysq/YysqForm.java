/**
 * @部门:学工产品事业部
 * @日期：2013-8-22 上午08:53:35 
 */  
package com.zfsoft.xgxt.xlzx.yysq;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.xlzx.zxzxjl.ZxzxjlxxModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 预约申请
 * @作者：wanghj [工号：1004]
 * @时间： 2013-8-22 上午08:53:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
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
	private String xxxq;//校区
	private String sjddm;//时间段代码
	
	private String sfdszn;
	private String jtszd;
	private String jtjjzk;
	private String fqwhcd;
	private String mqwhcd;
	private String fmhyzk;
	private String jtjsbs;
	private String jtxhcd;
	private String sfzl;
	private String djrq;
	private String[] yzxwts;
	private String yzxwt;
	private String wtbc;
	private String zxqw;
	private List<ZxzxjlxxModel> xxList;
	private String type;
	private String txr;
	
	private String zwpgid;//湖南城市学院自我评估id

	private String qxztzt;//一周情绪状况总体:hh 很好,jh 较好,yb 一般,jc 较差,hc 很差
	private String qxztjl;//一周情绪状况焦虑:yz 严重,jz 较重,y 有,qw 轻微,w 无
	private String qxztyy;//一周情绪状况抑郁:yz 严重,jz 较重,y 有,qw 轻微,w 无
	private String sczxhgb;//上次咨询后的改变:hmx 很明显,jmx 较明显,yb 一般,jbmx 较不明显,bmx 不明显
	private String zjzt;//对自己最近的状态:hmy 很满意,jmy 较满意,yb 一般,jbmc 较不满意,bmy 不满意
	private String bczxwt;//本次咨询的问题
	private String zxhzt;//上次咨询后的生理、心理状态和社会功能

	private String yyfs;//预约方式:ws 网上预约,dh 电话预约,xc 现场预约,jz 家长预约

	public String getYyfs() {
		return yyfs;
	}

	public void setYyfs(String yyfs) {
		this.yyfs = yyfs;
	}

	public String getQxztzt() {
		return qxztzt;
	}

	public void setQxztzt(String qxztzt) {
		this.qxztzt = qxztzt;
	}

	public String getQxztjl() {
		return qxztjl;
	}

	public void setQxztjl(String qxztjl) {
		this.qxztjl = qxztjl;
	}

	public String getQxztyy() {
		return qxztyy;
	}

	public void setQxztyy(String qxztyy) {
		this.qxztyy = qxztyy;
	}

	public String getSczxhgb() {
		return sczxhgb;
	}

	public void setSczxhgb(String sczxhgb) {
		this.sczxhgb = sczxhgb;
	}

	public String getZjzt() {
		return zjzt;
	}

	public void setZjzt(String zjzt) {
		this.zjzt = zjzt;
	}

	public String getBczxwt() {
		return bczxwt;
	}

	public void setBczxwt(String bczxwt) {
		this.bczxwt = bczxwt;
	}

	public String getZxhzt() {
		return zxhzt;
	}

	public void setZxhzt(String zxhzt) {
		this.zxhzt = zxhzt;
	}

	/**
	 * @return the zwpgid
	 */
	public String getZwpgid() {
		return zwpgid;
	}
	/**
	 * @param zwpgid要设置的 zwpgid
	 */
	public void setZwpgid(String zwpgid) {
		this.zwpgid = zwpgid;
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
	
	public String getSjddm() {
		return sjddm;
	}
	public void setSjddm(String sjddm) {
		this.sjddm = sjddm;
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
	public String getXxxq() {
		return xxxq;
	}
	public void setXxxq(String xxxq) {
		this.xxxq = xxxq;
	}
	/**
	 * @return the sfdszn
	 */
	public String getSfdszn() {
		return sfdszn;
	}
	/**
	 * @param sfdszn要设置的 sfdszn
	 */
	public void setSfdszn(String sfdszn) {
		this.sfdszn = sfdszn;
	}
	/**
	 * @return the jtszd
	 */
	public String getJtszd() {
		return jtszd;
	}
	/**
	 * @param jtszd要设置的 jtszd
	 */
	public void setJtszd(String jtszd) {
		this.jtszd = jtszd;
	}
	/**
	 * @return the jtjjzk
	 */
	public String getJtjjzk() {
		return jtjjzk;
	}
	/**
	 * @param jtjjzk要设置的 jtjjzk
	 */
	public void setJtjjzk(String jtjjzk) {
		this.jtjjzk = jtjjzk;
	}
	/**
	 * @return the fqwhcd
	 */
	public String getFqwhcd() {
		return fqwhcd;
	}
	/**
	 * @param fqwhcd要设置的 fqwhcd
	 */
	public void setFqwhcd(String fqwhcd) {
		this.fqwhcd = fqwhcd;
	}
	/**
	 * @return the mqwhcd
	 */
	public String getMqwhcd() {
		return mqwhcd;
	}
	/**
	 * @param mqwhcd要设置的 mqwhcd
	 */
	public void setMqwhcd(String mqwhcd) {
		this.mqwhcd = mqwhcd;
	}
	/**
	 * @return the fmhyzk
	 */
	public String getFmhyzk() {
		return fmhyzk;
	}
	/**
	 * @param fmhyzk要设置的 fmhyzk
	 */
	public void setFmhyzk(String fmhyzk) {
		this.fmhyzk = fmhyzk;
	}
	/**
	 * @return the jtjsbs
	 */
	public String getJtjsbs() {
		return jtjsbs;
	}
	/**
	 * @param jtjsbs要设置的 jtjsbs
	 */
	public void setJtjsbs(String jtjsbs) {
		this.jtjsbs = jtjsbs;
	}
	/**
	 * @return the jtxhcd
	 */
	public String getJtxhcd() {
		return jtxhcd;
	}
	/**
	 * @param jtxhcd要设置的 jtxhcd
	 */
	public void setJtxhcd(String jtxhcd) {
		this.jtxhcd = jtxhcd;
	}
	/**
	 * @return the sfzl
	 */
	public String getSfzl() {
		return sfzl;
	}
	/**
	 * @param sfzl要设置的 sfzl
	 */
	public void setSfzl(String sfzl) {
		this.sfzl = sfzl;
	}
	/**
	 * @return the djrq
	 */
	public String getDjrq() {
		return djrq;
	}
	/**
	 * @param djrq要设置的 djrq
	 */
	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}
	/**
	 * @return the yzxwts
	 */
	public String[] getYzxwts() {
		return yzxwts;
	}
	/**
	 * @param yzxwts要设置的 yzxwts
	 */
	public void setYzxwts(String[] yzxwts) {
		this.yzxwts = yzxwts;
	}
	/**
	 * @return the yzxwt
	 */
	public String getYzxwt() {
		return yzxwt;
	}
	/**
	 * @param yzxwt要设置的 yzxwt
	 */
	public void setYzxwt(String yzxwt) {
		this.yzxwt = yzxwt;
	}
	/**
	 * @return the wtbc
	 */
	public String getWtbc() {
		return wtbc;
	}
	/**
	 * @param wtbc要设置的 wtbc
	 */
	public void setWtbc(String wtbc) {
		this.wtbc = wtbc;
	}
	/**
	 * @return the zxqw
	 */
	public String getZxqw() {
		return zxqw;
	}
	/**
	 * @param zxqw要设置的 zxqw
	 */
	public void setZxqw(String zxqw) {
		this.zxqw = zxqw;
	}
	/**
	 * @return the xxList
	 */
	public List<ZxzxjlxxModel> getXxList() {
		return xxList;
	}
	/**
	 * @param xxList要设置的 xxList
	 */
	public void setXxList(List<ZxzxjlxxModel> xxList) {
		this.xxList = xxList;
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
	 * @return the txr
	 */
	public String getTxr() {
		return txr;
	}
	/**
	 * @param txr要设置的 txr
	 */
	public void setTxr(String txr) {
		this.txr = txr;
	}
	
	
}
