/**
 * @部门:学工产品事业部
 * @日期：2014-3-7 上午10:15:35 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述:场地申请审核form
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-7 上午10:15:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CdshForm extends ActionForm {

	
	
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -3970845499392576864L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	/**
	 *  @属性 ：type 查询类型 
	 */
	private String type;
	
	/**
	 * @属性 ：sqid 申请id
	 */
	private String sqid;
	
	/**
	 * @属性 ：cdid 场地id
	 */
	private String cdid;
	
	/**
	 * @属性 ：xh 学号
	 */
	private String xh;
	
	/**
	 * @属性 ：bmlbdm 部门类别代码
	 */
	private String bmlbdm;
	
	/**
	 * @属性 ：sqsj 申请时间
	 */
	private String sqsj;
	
	/**
	 * @属性 ：sqsjdkssj 申请时间段开始时间
	 */
	private String sqsjdkssj;
	
	/**
	 * @属性 ：sqsjdjssj 申请时间段结束时间
	 */
	private String sqsjdjssj;
	
	/**
	 * @属性 ：sqly 申请理由
	 */
	private String sqly;
	
	private String cyrs;
	private String fzrxm;
	private String fzrlxfs;
	
	private String xfgfsyxy;
	
	/**
	 * @属性 ：splcid 审批流id
	 */
	private String splcid;
	
	/**
	 * @属性 ：shzt 审批状态
	 */
	private String shzt;
	
	/**
	 * @属性 ：xtgwid 系统岗位id
	 */
	private String xtgwid;
	
	/**
	 * @属性 ：shid 审核id
	 */
	private String shid;
	
	/**
	 * @属性 ：shyj 审核意见
	 */
	private String shyj;
	
	/**
	 * @属性 ：shjg 审核结果
	 */
	private String shjg;
	
	/**
	 * @属性 ：thgw 退回岗位
	 */
	private String thgw;
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;


	/**
	 * @描述 ：无参数构造器 
	 */
	public CdshForm() {
		super();
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
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}

	/**
	 * @param splcid要设置的 splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
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
	 * @return the xtgwid
	 */
	public String getXtgwid() {
		return xtgwid;
	}

	/**
	 * @param xtgwid要设置的 xtgwid
	 */
	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
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
	 * @return the cdid
	 */
	public String getCdid() {
		return cdid;
	}

	/**
	 * @param cdid要设置的 cdid
	 */
	public void setCdid(String cdid) {
		this.cdid = cdid;
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
	 * @return the bmlbdm
	 */
	public String getBmlbdm() {
		return bmlbdm;
	}

	/**
	 * @param bmlbdm要设置的 bmlbdm
	 */
	public void setBmlbdm(String bmlbdm) {
		this.bmlbdm = bmlbdm;
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
	 * @return the sqsjdkssj
	 */
	public String getSqsjdkssj() {
		return sqsjdkssj;
	}

	/**
	 * @param sqsjdkssj要设置的 sqsjdkssj
	 */
	public void setSqsjdkssj(String sqsjdkssj) {
		this.sqsjdkssj = sqsjdkssj;
	}

	/**
	 * @return the sqsjdjssj
	 */
	public String getSqsjdjssj() {
		return sqsjdjssj;
	}

	/**
	 * @param sqsjdjssj要设置的 sqsjdjssj
	 */
	public void setSqsjdjssj(String sqsjdjssj) {
		this.sqsjdjssj = sqsjdjssj;
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
	 * @return the cyrs
	 */
	public String getCyrs() {
		return cyrs;
	}

	/**
	 * @param cyrs要设置的 cyrs
	 */
	public void setCyrs(String cyrs) {
		this.cyrs = cyrs;
	}

	/**
	 * @return the fzrxm
	 */
	public String getFzrxm() {
		return fzrxm;
	}

	/**
	 * @param fzrxm要设置的 fzrxm
	 */
	public void setFzrxm(String fzrxm) {
		this.fzrxm = fzrxm;
	}

	/**
	 * @return the fzrlxfs
	 */
	public String getFzrlxfs() {
		return fzrlxfs;
	}

	/**
	 * @param fzrlxfs要设置的 fzrlxfs
	 */
	public void setFzrlxfs(String fzrlxfs) {
		this.fzrlxfs = fzrlxfs;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getGwids() {
		return gwids;
	}
	
	public String getXfgfsyxy() {
		return xfgfsyxy;
	}

	public void setXfgfsyxy(String xfgfsyxy) {
		this.xfgfsyxy = xfgfsyxy;
	}

	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}

	public String[] getXhs() {
		return xhs;
	}

	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	public String[] getSplcs() {
		return splcs;
	}

	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}

}
