/**
 * @部门:学工产品事业部
 * @日期：2013-7-25 下午4:14:57 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwsh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.szdw.xsgbgl.zwsq.ZwsqForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部管理职务审核
 * @作者： zhangjw
 * @时间： 2013-8-9 下午5:00:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZwshForm extends ZwsqForm {

	/** 
	 * @变量 serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String shlx;

	
	private String guid ;//ID
	private String shid ;//ID
	private String ywid ;//业务ID
	private String shr ;//审核人
	private String shsj ;//审核时间
	private String shzt ;//审核状态
	private String shyj ;//审核意见
	private String gwid ;//审核岗位
	private String thgw ;
	private String shlc;
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	private String[] zwids;

	private ExportModel exportModel = new ExportModel();
	
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

	public String[] getZwids() {
		return zwids;
	}

	public void setZwids(String[] zwids) {
		this.zwids = zwids;
	}
	
}
