package com.zfsoft.xgxt.gygl.ssyd.ydsh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理-宿舍异动
 * @类功能描述:宿舍异动审核
 * @作者： qilm
 * @时间： 2013-9-29
 * @版本： V1.0
 */

public class YdshForm extends YdsqForm {
	private static final long serialVersionUID = 1L;

	private String shlx;
	private String guid;// ID
	private String shid;// ID
	private String ywid;// 业务ID
	private String shr;// 审核人
	private String shsj;// 审核时间
	private String shzt;// 审核状态
	private String shyj;// 审核意见
	private String gwid;// 审核岗位
	private String shjg;// 审核结果
	private String thgw;// 退回岗位
	
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	private String[] ssydlxs;
	private String[] sqshHideCwxxs;
	
	// ========= 业务信息 begin =========
	private String rzcwxx; // 床位信息ID（入住）
	private String cwxx; // 床位信息ID（调整）
	private String ydhlddm; 
	private String ydhldmc; 
	private String ydhqsh; 
	private String ydhcwh; 
	private String ydglxh; //异动关联学生
	// ========= 业务信息 end =========
	
	private ExportModel exportModel = new ExportModel();

	public String getShlx() {
		return shlx;
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

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getThgw() {
		return thgw;
	}

	public void setThgw(String thgw) {
		this.thgw = thgw;
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

	public String[] getSsydlxs() {
		return ssydlxs;
	}

	public void setSsydlxs(String[] ssydlxs) {
		this.ssydlxs = ssydlxs;
	}
	public String[] getSqshHideCwxxs() {
		return sqshHideCwxxs;
	}

	public void setSqshHideCwxxs(String[] sqshHideCwxxs) {
		this.sqshHideCwxxs = sqshHideCwxxs;
	}

	/**
	 * 床位信息ID（入住）
	 */
	public String getRzcwxx() {
		return rzcwxx;
	}

	/**
	 * 床位信息ID（入住）
	 */
	public void setRzcwxx(String rzcwxx) {
		this.rzcwxx = rzcwxx;
	}

	/**
	 * 床位信息ID（调整）
	 */
	public String getCwxx() {
		return cwxx;
	}

	/**
	 * 床位信息ID（调整）
	 */
	public void setCwxx(String cwxx) {
		this.cwxx = cwxx;
	}

	/**
	 * @return the ydhlddm
	 */
	public String getYdhlddm() {
		return ydhlddm;
	}

	/**
	 * @param ydhlddm要设置的 ydhlddm
	 */
	public void setYdhlddm(String ydhlddm) {
		this.ydhlddm = ydhlddm;
	}

	/**
	 * @return the ydhldmc
	 */
	public String getYdhldmc() {
		return ydhldmc;
	}

	/**
	 * @param ydhldmc要设置的 ydhldmc
	 */
	public void setYdhldmc(String ydhldmc) {
		this.ydhldmc = ydhldmc;
	}

	/**
	 * @return the ydhqsh
	 */
	public String getYdhqsh() {
		return ydhqsh;
	}

	/**
	 * @param ydhqsh要设置的 ydhqsh
	 */
	public void setYdhqsh(String ydhqsh) {
		this.ydhqsh = ydhqsh;
	}

	/**
	 * @return the ydhcwh
	 */
	public String getYdhcwh() {
		return ydhcwh;
	}

	/**
	 * @param ydhcwh要设置的 ydhcwh
	 */
	public void setYdhcwh(String ydhcwh) {
		this.ydhcwh = ydhcwh;
	}

	/**
	 * @return the ydglxh
	 */
	public String getYdglxh() {
		return ydglxh;
	}

	/**
	 * @param ydglxh要设置的 ydglxh
	 */
	public void setYdglxh(String ydglxh) {
		this.ydglxh = ydglxh;
	}
	
}
