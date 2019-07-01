package com.zfsoft.xgxt.xsxx.xjyd.xjydsh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学籍异动
 * @类功能描述:学籍异动审核
 * @作者： qilm
 * @版本： V1.0
 */

public class XjydshForm extends XjydsqForm {
	private static final long serialVersionUID = 1L;

	private String shlx;
	private String guid ;//ID
	/**
	 * 审核ID
	 */
	private String shid ;
	/**
	 * 业务ID
	 */
	private String ywid ;
	/**
	 * 审核人
	 */
	private String shr ;
	/**
	 * 审核时间
	 */
	private String shsj ;
	/**
	 * 审核状态
	 */
	private String shzt ;
	/**
	 * 审核意见
	 */
	private String shyj ;
	/**
	 * 审核岗位
	 */
	private String gwid ;
	/**
	 * 学籍异动文号
	 */
	private String xjydwh;
	/**
	 * 学籍异动时间
	 */
	private String xjydsj;
	/**
	 * 学籍异动备注
	 */
	private String xjydbz;
	/**
	 * 退回岗位
	 */
	private String thgw;
	/**
	 * 审核结果
	 */
	private String shjg;
	
	/**
	 * 是否最后一级审核
	 */
	private String isZhgw;
	
	private ExportModel exportModel = new ExportModel();
	
	public String getIsZhgw() {
		return isZhgw;
	}

	public void setIsZhgw(String isZhgw) {
		this.isZhgw = isZhgw;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getShlx() {
		return shlx;
	}

	public String getThgw() {
		return thgw;
	}

	public void setThgw(String thgw) {
		this.thgw = thgw;
	}

	public void setShlx(String shlx) {
		this.shlx = shlx;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getYwid() {
		return ywid;
	}

	public void setYwid(String ywid) {
		this.ywid = ywid;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getGwid() {
		return gwid;
	}

	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	public String getShid() {
		return shid;
	}

	public void setShid(String shid) {
		this.shid = shid;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getXjydwh() {
		return xjydwh;
	}

	public void setXjydwh(String xjydwh) {
		this.xjydwh = xjydwh;
	}

	public String getXjydsj() {
		return xjydsj;
	}

	public void setXjydsj(String xjydsj) {
		this.xjydsj = xjydsj;
	}

	public String getXjydbz() {
		return xjydbz;
	}

	public void setXjydbz(String xjydbz) {
		this.xjydbz = xjydbz;
	}
}
