/**
 * @部门:学工产品事业部
 * @日期：2014-4-29 下午03:33:55 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.yyzxfk;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询 -预约咨询反馈
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-29 下午03:33:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YyzxfkForm extends ActionForm{


	private static final long serialVersionUID = 6049055260101630099L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	
	private String sqid;
	
	private String zxid;
	
	private String xh;
	
	private String yyzt;
	
	private String zzaprq;
	
	private String zxsdkssj;
	
	private String zxsdjssj;
	
	private String zxs;
	
	private String zxslxdh;
	
	private String zxdz;
	
	private String bz;
	
	private String yysbyy;
	
	private String yyzxfs;

	public String getYyzxfs() {
		return yyzxfs;
	}

	public void setYyzxfs(String yyzxfs) {
		this.yyzxfs = yyzxfs;
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
	 * @return 申请ID
	 */
	public String getSqid() {
		return sqid;
	}

	/**
	 * @param 申请ID
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	/**
	 * @return 咨询ID
	 */
	public String getZxid() {
		return zxid;
	}

	/**
	 * @param 咨询ID
	 */
	public void setZxid(String zxid) {
		this.zxid = zxid;
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
	 * @return 预约状态-1预约中2预约成功3预约中(学生取消)4预约成功(学生取消)5预约失败
	 */
	public String getYyzt() {
		return yyzt;
	}

	/**
	 * @param 预约状态-1预约中2预约成功3预约中(学生取消)4预约成功(学生取消)5预约失败
	 */
	public void setYyzt(String yyzt) {
		this.yyzt = yyzt;
	}

	/**
	 * @return 咨询安排日期
	 */
	public String getZzaprq() {
		return zzaprq;
	}

	/**
	 * @param 咨询安排日期
	 */
	public void setZzaprq(String zzaprq) {
		this.zzaprq = zzaprq;
	}

	/**
	 * @return 咨询时段开始时间
	 */
	public String getZxsdkssj() {
		return zxsdkssj;
	}

	/**
	 * @param 咨询时段开始时间
	 */
	public void setZxsdkssj(String zxsdkssj) {
		this.zxsdkssj = zxsdkssj;
	}

	/**
	 * @return 咨询时段结束时间
	 */
	public String getZxsdjssj() {
		return zxsdjssj;
	}

	/**
	 * @param 咨询时段结束时间
	 */
	public void setZxsdjssj(String zxsdjssj) {
		this.zxsdjssj = zxsdjssj;
	}

	/**
	 * @return 安排咨询师
	 */
	public String getZxs() {
		return zxs;
	}

	/**
	 * @param 安排咨询师
	 */
	public void setZxs(String zxs) {
		this.zxs = zxs;
	}

	/**
	 * @return 咨询师联系电话
	 */
	public String getZxslxdh() {
		return zxslxdh;
	}

	/**
	 * @param 咨询师联系电话
	 */
	public void setZxslxdh(String zxslxdh) {
		this.zxslxdh = zxslxdh;
	}

	/**
	 * @return 咨询地址
	 */
	public String getZxdz() {
		return zxdz;
	}

	/**
	 * @param 咨询地址
	 */
	public void setZxdz(String zxdz) {
		this.zxdz = zxdz;
	}

	/**
	 * @return 备注
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param 备注
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return 预约失败原因
	 */
	public String getYysbyy() {
		return yysbyy;
	}

	/**
	 * @param 预约失败原因
	 */
	public void setYysbyy(String yysbyy) {
		this.yysbyy = yysbyy;
	}
	
}
