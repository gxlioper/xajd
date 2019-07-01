/**
 * @部门:学工产品事业部
 * @日期：2014-4-29 下午02:46:53 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxcl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询 -心理咨询处理
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-29 下午02:46:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlzxclForm extends ActionForm {
	
	private static final long serialVersionUID = -7936444598764937984L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	
	private ExportModel exportModel = new ExportModel();
	
	private String zxid;
	
	private String sqid;
	
	private String xh;
	
	private String zzaprq;
	
	private String zxsdkssj;
	
	private String zxsdjssj;
	
	private String zxs;
	
	private String zxslxdh;
	
	private String zxdz;
	
	private String bz;
	
	private String zxzt;
	
	private String zxrq;
	
	private String zxkssj;
	
	private String zxjssj;
	
	private String lfzzs;
	
	private String xlhd;
	
	private String zxzj;
	
	private String gswtlx;
	
	private String jscd;
	
	private String[] jscdarray;
	
	private String qtjscd;
	
	private String yzcdpg;
	
	private String[] yzcdpgarray;
	
	private String qtyzcdpg;
	
	private String sfxyzj;
	
	private String sfyyxczx;
	
	private String xczxsj;
	
	private String zxxgmydpf;
	
	private String xszxpj;
	
	private String[] wtlxarray;
	
	private String[] qtwtlxarray;
	
	private String qtwtlx;
	
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
	 * @return 学号
	 */
	public String getXh() {
		return xh;
	}

	/**
	 * @param 学号
	 */
	public void setXh(String xh) {
		this.xh = xh;
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
	 * @return 咨询状态(0：未咨询;1:已咨询)
	 */
	public String getZxzt() {
		return zxzt;
	}

	/**
	 * @param 咨询状态(0：未咨询;1:已咨询)
	 */
	public void setZxzt(String zxzt) {
		this.zxzt = zxzt;
	}

	/**
	 * @return 咨询日期
	 */
	public String getZxrq() {
		return zxrq;
	}

	/**
	 * @param 咨询日期
	 */
	public void setZxrq(String zxrq) {
		this.zxrq = zxrq;
	}

	/**
	 * @return 咨询开始时间
	 */
	public String getZxkssj() {
		return zxkssj;
	}

	/**
	 * @param 咨询开始时间
	 */
	public void setZxkssj(String zxkssj) {
		this.zxkssj = zxkssj;
	}

	/**
	 * @return 咨询结束时间
	 */
	public String getZxjssj() {
		return zxjssj;
	}

	/**
	 * @param 咨询结束时间
	 */
	public void setZxjssj(String zxjssj) {
		this.zxjssj = zxjssj;
	}

	/**
	 * @return 来访者主诉
	 */
	public String getLfzzs() {
		return lfzzs;
	}

	/**
	 * @param 来访者主诉
	 */
	public void setLfzzs(String lfzzs) {
		this.lfzzs = lfzzs;
	}

	/**
	 * @return 咨询过程及主要的心理互动
	 */
	public String getXlhd() {
		return xlhd;
	}

	/**
	 * @param 咨询过程及主要的心理互动
	 */
	public void setXlhd(String xlhd) {
		this.xlhd = xlhd;
	}

	/**
	 * @return 咨询后的总结
	 */
	public String getZxzj() {
		return zxzj;
	}

	/**
	 * @param 咨询后的总结
	 */
	public void setZxzj(String zxzj) {
		this.zxzj = zxzj;
	}

	/**
	 * @return 该生问题类型
	 */
	public String getGswtlx() {
		return gswtlx;
	}

	/**
	 * @param 该生问题类型
	 */
	public void setGswtlx(String gswtlx) {
		this.gswtlx = gswtlx;
	}

	/**
	 * @return 咨询师来访者对咨询的接受程度
	 */
	public String getJscd() {
		return jscd;
	}

	/**
	 * @param 咨询师来访者对咨询的接受程度
	 */
	public void setJscd(String jscd) {
		this.jscd = jscd;
	}

	/**
	 * @return 你对来访者心理问题严重程度的评估
	 */
	public String getYzcdpg() {
		return yzcdpg;
	}

	/**
	 * @param 你对来访者心理问题严重程度的评估
	 */
	public void setYzcdpg(String yzcdpg) {
		this.yzcdpg = yzcdpg;
	}

	/**
	 * @return 是否需要转介
	 */
	public String getSfxyzj() {
		return sfxyzj;
	}

	/**
	 * @param 是否需要转介
	 */
	public void setSfxyzj(String sfxyzj) {
		this.sfxyzj = sfxyzj;
	}

	/**
	 * @return 是否预约下次咨询
	 */
	public String getSfyyxczx() {
		return sfyyxczx;
	}

	/**
	 * @param 是否预约下次咨询
	 */
	public void setSfyyxczx(String sfyyxczx) {
		this.sfyyxczx = sfyyxczx;
	}

	/**
	 * @return 预约下次咨询时间
	 */
	public String getXczxsj() {
		return xczxsj;
	}

	/**
	 * @param 预约下次咨询时间
	 */
	public void setXczxsj(String xczxsj) {
		this.xczxsj = xczxsj;
	}

	/**
	 * @return 学生咨询评价
	 */
	public String getXszxpj() {
		return xszxpj;
	}

	/**
	 * @param 学生咨询评价
	 */
	public void setXszxpj(String xszxpj) {
		this.xszxpj = xszxpj;
	}
	
	/**
	 * @return 咨询效果满意度评分
	 */
	public String getZxxgmydpf() {
		return zxxgmydpf;
	}

	/**
	 * @param 咨询效果满意度评分
	 */
	public void setZxxgmydpf(String zxxgmydpf) {
		this.zxxgmydpf = zxxgmydpf;
	}

	/**
	 * @return 问题类型
	 */
	public String[] getWtlxarray() {
		return wtlxarray;
	}

	/**
	 * @param 问题类型
	 */
	public void setWtlxarray(String[] wtlxarray) {
		this.wtlxarray = wtlxarray;
	}

	/**
	 * @return 其它问题类型
	 */
	public String[] getQtwtlxarray() {
		return qtwtlxarray;
	}

	/**
	 * @param 其它问题类型
	 */
	public void setQtwtlxarray(String[] qtwtlxarray) {
		this.qtwtlxarray = qtwtlxarray;
	}

	/**
	 * @return 其它问题类型名称
	 */
	public String getQtwtlx() {
		return qtwtlx;
	}

	/**
	 * @param 其它问题类型名称
	 */
	public void setQtwtlx(String qtwtlx) {
		this.qtwtlx = qtwtlx;
	}

	/**
	 * @return 接受程度数组
	 */
	public String[] getJscdarray() {
		return jscdarray;
	}

	/**
	 * @param 接受程度数组
	 */
	public void setJscdarray(String[] jscdarray) {
		this.jscdarray = jscdarray;
	}

	/**
	 * @return 其它接受程度描述
	 */
	public String getQtjscd() {
		return qtjscd;
	}

	/**
	 * @param 其它接受程度描述
	 */
	public void setQtjscd(String qtjscd) {
		this.qtjscd = qtjscd;
	}

	/**
	 * @return the yzcdpgarray
	 */
	public String[] getYzcdpgarray() {
		return yzcdpgarray;
	}

	/**
	 * @param yzcdpgarray要设置的 yzcdpgarray
	 */
	public void setYzcdpgarray(String[] yzcdpgarray) {
		this.yzcdpgarray = yzcdpgarray;
	}

	/**
	 * @return the qtyzcdpg
	 */
	public String getQtyzcdpg() {
		return qtyzcdpg;
	}

	/**
	 * @param qtyzcdpg要设置的 qtyzcdpg
	 */
	public void setQtyzcdpg(String qtyzcdpg) {
		this.qtyzcdpg = qtyzcdpg;
	}

}
