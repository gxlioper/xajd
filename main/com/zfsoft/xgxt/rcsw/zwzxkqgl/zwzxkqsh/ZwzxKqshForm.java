/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:38:26 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-26 下午02:38:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwzxKqshForm extends ActionForm{
	
	private String sqid;
	private String xh;
	private String nj;
	private String xn;
	private String xq;
	private String xqmc;
	private String sqsj;
	private String cclxdm;  //抽查类型
	private String cclxmc;
	private String qqlxdm;
	private String qqlxmc;
	private String xydm;
	private String xymc;
	private String zydm;
	private String zymc;
	private String bjdm;
	private String bjmc;
	private String kkjs;
	private String ylzd1;
	private String ydrs;  //应到人数
	private String sdrs;  //实到人数
	private String qqrs;  //缺勤人数
	private String wjrs;  //违纪人数
	private String ccrq;  //抽查日期
	private String jlf;  //纪律分
	private String bz;
	private String jlr;  //记录人
	private String jlrxm;  //记录人
	private String shzt;
	private String splc;
	private String type;
	
	private String ywid;
	private String shsj;
	private String shr;
	private String shyj;
	private String shlc;
	private String gwid;
	private String shztmc;
	private String shid;
	private String thgw;//岗位退回
	private String shjg;
	
	private String[] id;
	private String[] gwids;
	private String[] bjdms;

 // 分页
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @return the cclxdm
	 */
	public String getCclxdm() {
		return cclxdm;
	}
	/**
	 * @param cclxdm要设置的 cclxdm
	 */
	public void setCclxdm(String cclxdm) {
		this.cclxdm = cclxdm;
	}
	/**
	 * @return the cclxmc
	 */
	public String getCclxmc() {
		return cclxmc;
	}
	/**
	 * @param cclxmc要设置的 cclxmc
	 */
	public void setCclxmc(String cclxmc) {
		this.cclxmc = cclxmc;
	}
	/**
	 * @return the qqlxdm
	 */
	public String getQqlxdm() {
		return qqlxdm;
	}
	/**
	 * @param qqlxdm要设置的 qqlxdm
	 */
	public void setQqlxdm(String qqlxdm) {
		this.qqlxdm = qqlxdm;
	}
	/**
	 * @return the qqlxmc
	 */
	public String getQqlxmc() {
		return qqlxmc;
	}
	/**
	 * @param qqlxmc要设置的 qqlxmc
	 */
	public void setQqlxmc(String qqlxmc) {
		this.qqlxmc = qqlxmc;
	}
	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @param xydm要设置的 xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymc要设置的 xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * @param zydm要设置的 zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * @param zymc要设置的 zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdm要设置的 bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmc要设置的 bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the kkjs
	 */
	public String getKkjs() {
		return kkjs;
	}
	/**
	 * @param kkjs要设置的 kkjs
	 */
	public void setKkjs(String kkjs) {
		this.kkjs = kkjs;
	}
	/**
	 * @return the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * @param ylzd1要设置的 ylzd1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * @return the ydrs
	 */
	public String getYdrs() {
		return ydrs;
	}
	/**
	 * @param ydrs要设置的 ydrs
	 */
	public void setYdrs(String ydrs) {
		this.ydrs = ydrs;
	}
	/**
	 * @return the sdrs
	 */
	public String getSdrs() {
		return sdrs;
	}
	/**
	 * @param sdrs要设置的 sdrs
	 */
	public void setSdrs(String sdrs) {
		this.sdrs = sdrs;
	}
	/**
	 * @return the qqrs
	 */
	public String getQqrs() {
		return qqrs;
	}
	/**
	 * @param qqrs要设置的 qqrs
	 */
	public void setQqrs(String qqrs) {
		this.qqrs = qqrs;
	}
	/**
	 * @return the wjrs
	 */
	public String getWjrs() {
		return wjrs;
	}
	/**
	 * @param wjrs要设置的 wjrs
	 */
	public void setWjrs(String wjrs) {
		this.wjrs = wjrs;
	}
	/**
	 * @return the ccrq
	 */
	public String getCcrq() {
		return ccrq;
	}
	/**
	 * @param ccrq要设置的 ccrq
	 */
	public void setCcrq(String ccrq) {
		this.ccrq = ccrq;
	}
	/**
	 * @return the jlf
	 */
	public String getJlf() {
		return jlf;
	}
	/**
	 * @param jlf要设置的 jlf
	 */
	public void setJlf(String jlf) {
		this.jlf = jlf;
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
	 * @return the jlr
	 */
	public String getJlr() {
		return jlr;
	}
	/**
	 * @param jlr要设置的 jlr
	 */
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}
	/**
	 * @return the jlrxm
	 */
	public String getJlrxm() {
		return jlrxm;
	}
	/**
	 * @param jlrxm要设置的 jlrxm
	 */
	public void setJlrxm(String jlrxm) {
		this.jlrxm = jlrxm;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
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
	 * @return the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @param ywid要设置的 ywid
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	/**
	 * @return the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @param shsj要设置的 shsj
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	/**
	 * @return the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @param shr要设置的 shr
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyj要设置的 shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlc要设置的 shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwid要设置的 gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @return the shztmc
	 */
	public String getShztmc() {
		return shztmc;
	}
	/**
	 * @param shztmc要设置的 shztmc
	 */
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shid要设置的 shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgw要设置的 thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjg要设置的 shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
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
	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param nj要设置的 nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String[] id) {
		this.id = id;
	}
	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @param gwids要设置的 gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	/**
	 * @return the bjdms
	 */
	public String[] getBjdms() {
		return bjdms;
	}
	/**
	 * @param bjdms要设置的 bjdms
	 */
	public void setBjdms(String[] bjdms) {
		this.bjdms = bjdms;
	}
    
    

}
