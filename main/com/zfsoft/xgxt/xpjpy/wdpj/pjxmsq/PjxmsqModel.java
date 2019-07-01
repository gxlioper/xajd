/**
 * @部门:学工产品事业部
 * @日期：2013-12-9 上午11:18:20 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.pjxmsq;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import java.io.File;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-9 上午11:18:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjxmsqModel extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -2636417102028485376L;
	
	private String id;		// ID
	private String sqid;	//申请ID
	private String xn;		//学年
	private String xh; 		//学号
	private String xq;		//学期
	private String xqmc;   //学期名称
	private String sqsj; 	//申请时间
	private String zzhdjx;	//最终获得奖项
	private String shzt;	//审核状态
	private String ylzd1;	// 预留字段一
	private String ylzd2;	// 预留字段二
	private String ylzd3;	// 预留字段三
	private String ylzd4;	// 预留字段四
	private String ylzd5;	// 附件id
	private String sjly;	// 数据来源
	private String lylcywid;// 来源业务id
	private String sqly;	//申请理由
	private String sqr;		//申请人
	private String splc;
	private String tzhxmdm; //调整后项目代码
	private String dqxmdm;	//当前项目代码

	private String xmdm; 	//项目代码
	private String xzdm; 	//项性质代码
	private String xmlx; 	//项目类型
	private String xmmc;	//项目名称
	private String xmje;	//金额
	private String sqsjd;	//申请时间段
	
	private String lxdm;
	
	private String lxdmmc;
	private FormFile file;
	
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private String type;
	private String queryType; //查询类型  wsq 未申请 ，   ysq 已申请
	private String djjl;//徐州医药
	
	//中国美术学院个性化修改
	private String[] ids;
	private String sfysq;
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String getSfysq() {
		return sfysq;
	}
	public void setSfysq(String sfysq) {
		this.sfysq = sfysq;
	}
	public String getDjjl() {
		return djjl;
	}
	public void setDjjl(String djjl) {
		this.djjl = djjl;
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
	 * @return the queryType
	 */
	public String getQueryType() {
		return queryType;
	}
	/**
	 * @param queryType要设置的 queryType
	 */
	public void setQueryType(String queryType) {
		this.queryType = queryType;
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
	 * @return the zzhdjx
	 */
	public String getZzhdjx() {
		return zzhdjx;
	}
	/**
	 * @param zzhdjx要设置的 zzhdjx
	 */
	public void setZzhdjx(String zzhdjx) {
		this.zzhdjx = zzhdjx;
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
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdm要设置的 xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getXzdm() {
		return xzdm;
	}
	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}
	/**
	 * @return the xmlx
	 */
	public String getXmlx() {
		return xmlx;
	}
	/**
	 * @param xmlx要设置的 xmlx
	 */
	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc要设置的 xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the xmje
	 */
	public String getXmje() {
		return xmje;
	}
	/**
	 * @param xmje要设置的 xmje
	 */
	public void setXmje(String xmje) {
		this.xmje = xmje;
	}
	/**
	 * @return the sqsjd
	 */
	public String getSqsjd() {
		return sqsjd;
	}
	/**
	 * @param sqsjd要设置的 sqsjd
	 */
	public void setSqsjd(String sqsjd) {
		this.sqsjd = sqsjd;
	}
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
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqly要设置的 sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}
	/**
	 * @param sqr要设置的 sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
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
	 * @return the tzhxmdm
	 */
	public String getTzhxmdm() {
		return tzhxmdm;
	}
	/**
	 * @param tzhxmdm要设置的 tzhxmdm
	 */
	public void setTzhxmdm(String tzhxmdm) {
		this.tzhxmdm = tzhxmdm;
	}
	public String getDqxmdm() {
		return dqxmdm;
	}
	public void setDqxmdm(String dqxmdm) {
		this.dqxmdm = dqxmdm;
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
	 * @return the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * @param ylzd2要设置的 ylzd2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * @param ylzd3要设置的 ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * @return the ylzd4
	 */
	public String getYlzd4() {
		return ylzd4;
	}
	/**
	 * @param ylzd4要设置的 ylzd4
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	/**
	 * @return the ylzd5
	 */
	public String getYlzd5() {
		return ylzd5;
	}
	/**
	 * @param ylzd5要设置的 ylzd5
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}
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
	 * @return the lylcywid
	 */
	public String getLylcywid() {
		return lylcywid;
	}
	/**
	 * @param lylcywid要设置的 lylcywid
	 */
	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
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
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}
	/**
	 * @param lxdm要设置的 lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	/**
	 * @return the lxdmmc
	 */
	public String getLxdmmc() {
		return lxdmmc;
	}
	/**
	 * @param lxdmmc要设置的 lxdmmc
	 */
	public void setLxdmmc(String lxdmmc) {
		this.lxdmmc = lxdmmc;
	}
	
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
}
