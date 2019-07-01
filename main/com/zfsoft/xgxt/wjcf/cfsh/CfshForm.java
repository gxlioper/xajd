/**
 * @部门:学工产品事业部
 * @日期：2013-10-28 上午10:54:09 
 */  
package com.zfsoft.xgxt.wjcf.cfsh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.wjcf.cfsb.CfsbglForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分上报审核) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-28 上午10:51:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfshForm extends CfsbglForm {


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
	private String sbjg ;
	private String cfwh;
	private String cfsj;
	private String cfdqsj;//处分到期时间
	private String cfid;
	private String sjly;
	private String cflsh;
	private String cflbdm;//处分类别代码
	private String kzzd1;//最后一次改变前处分类别
	private String thgw;//岗位退回
	
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcids;
	
	private int cffwqxPd; //处分发文权限判断
	private String kzzd2; //处分文号
	private String kzzd3; //处分时间
	private String kzzd5; //处分到期时间
	
	/**
	 * @return the cfid
	 */
	public String getCfid() {
		return cfid;
	}

	/**
	 * @param cfid要设置的 cfid
	 */
	public void setCfid(String cfid) {
		this.cfid = cfid;
	}

	/**
	 * @return the cfwh
	 */
	public String getCfwh() {
		return cfwh;
	}

	/**
	 * @param cfwh要设置的 cfwh
	 */
	public void setCfwh(String cfwh) {
		this.cfwh = cfwh;
	}

	/**
	 * @return the cfsj
	 */
	public String getCfsj() {
		return cfsj;
	}

	/**
	 * @param cfsj要设置的 cfsj
	 */
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}

	/**
	 * @return the sbjg
	 */
	public String getSbjg() {
		return sbjg;
	}

	/**
	 * @param sbjg要设置的 sbjg
	 */
	public void setSbjg(String sbjg) {
		this.sbjg = sbjg;
	}

	private ExportModel exportModel = new ExportModel();

	/**
	 * @return the shlx
	 */
	public String getShlx() {
		return shlx;
	}

	/**
	 * @param shlx要设置的 shlx
	 */
	public void setShlx(String shlx) {
		this.shlx = shlx;
	}

	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * @param guid要设置的 guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
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

	public String[] getSplcids() {
		return splcids;
	}

	public void setSplcids(String[] splcids) {
		this.splcids = splcids;
	}

	/**
	 * @return the cflsh
	 */
	public String getCflsh() {
		return cflsh;
	}

	/**
	 * @param cflsh要设置的 cflsh
	 */
	public void setCflsh(String cflsh) {
		this.cflsh = cflsh;
	}

	/**
	 * @return the cflbdm
	 */
	public String getCflbdm() {
		return cflbdm;
	}

	/**
	 * @param cflbdm要设置的 cflbdm
	 */
	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}

	/**
	 * @return the kzzd1
	 */
	public String getKzzd1() {
		return kzzd1;
	}

	/**
	 * @param kzzd1要设置的 kzzd1
	 */
	public void setKzzd1(String kzzd1) {
		this.kzzd1 = kzzd1;
	}

	/**
	 * @return the cffwqxPd
	 */
	public int getCffwqxPd() {
		return cffwqxPd;
	}

	/**
	 * @param cffwqxPd要设置的 cffwqxPd
	 */
	public void setCffwqxPd(int cffwqxPd) {
		this.cffwqxPd = cffwqxPd;
	}

	/**
	 * @return the kzzd2
	 */
	public String getKzzd2() {
		return kzzd2;
	}

	/**
	 * @param kzzd2要设置的 kzzd2
	 */
	public void setKzzd2(String kzzd2) {
		this.kzzd2 = kzzd2;
	}

	/**
	 * @return the kzzd3
	 */
	public String getKzzd3() {
		return kzzd3;
	}

	/**
	 * @param kzzd3要设置的 kzzd3
	 */
	public void setKzzd3(String kzzd3) {
		this.kzzd3 = kzzd3;
	}

	/**
	 * @return the kzzd5
	 */
	public String getKzzd5() {
		return kzzd5;
	}

	/**
	 * @param kzzd4要设置的 kzzd5
	 */
	public void setKzzd5(String kzzd5) {
		this.kzzd5 = kzzd5;
	}

	/**
	 * @return the cfdqsj
	 */
	public String getCfdqsj() {
		return cfdqsj;
	}

	/**
	 * @param cfdqsj要设置的 cfdqsj
	 */
	public void setCfdqsj(String cfdqsj) {
		this.cfdqsj = cfdqsj;
	}
	
	
	
	

}
