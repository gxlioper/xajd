/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 上午10:30:23 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwdmwh;

import com.zfsoft.xgxt.rcsw.rcxwwh.comm.RcxwwhForm;







/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： dlq [工号：995]
 * @时间： 2013-7-30 上午10:30:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RcxwdmwhForm extends RcxwwhForm{

	private static final long serialVersionUID = -7463590518719457773L;
	
	private String type;
	private String rcxwlbdldm;//行为大类代码
	private String rcxwlbdlmc;//行为大类名称
	private String rcxwlbdm;//行为类别代码
	private String rcxwlbmc;//行为类别名称
	private String rcxwlbfz;//行为类别分值
	private String splc;//审批流程
	private String lcxx;//审批流程信息
	private String rcxwfzlx;//行为类别分值
	
	private String rcxwlbzdfz;//行为类别最低分值
	private String rcxwlbzgfz;//行为类别最高分值
	private String rcxwlbbz;//行为类别备注
	private String sfqy;
	private String sqkssj;
	private String sqjssj;
	private String sqkg;
	private String rcxwdlfssx;
	private String rcxwdljcf;
	
	
	/**
	 * @return the rcxwlbzdfz
	 */
	public String getRcxwlbzdfz() {
		return rcxwlbzdfz;
	}
	/**
	 * @param rcxwlbzdfz要设置的 rcxwlbzdfz
	 */
	public void setRcxwlbzdfz(String rcxwlbzdfz) {
		this.rcxwlbzdfz = rcxwlbzdfz;
	}
	/**
	 * @return the rcxwlbzgfz
	 */
	public String getRcxwlbzgfz() {
		return rcxwlbzgfz;
	}
	/**
	 * @param rcxwlbzgfz要设置的 rcxwlbzgfz
	 */
	public void setRcxwlbzgfz(String rcxwlbzgfz) {
		this.rcxwlbzgfz = rcxwlbzgfz;
	}
	/**
	 * @return the rcxwlbbz
	 */
	public String getRcxwlbbz() {
		return rcxwlbbz;
	}
	/**
	 * @param rcxwlbbz要设置的 rcxwlbbz
	 */
	public void setRcxwlbbz(String rcxwlbbz) {
		this.rcxwlbbz = rcxwlbbz;
	}
	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssj要设置的 sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssj要设置的 sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkg要设置的 sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the sfqy
	 */
	public String getSfqy() {
		return sfqy;
	}
	/**
	 * @param sfqy要设置的 sfqy
	 */
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	/**
	 * @return the rcxwlbdldm
	 */
	public String getRcxwlbdldm() {
		return rcxwlbdldm;
	}
	/**
	 * @param rcxwlbdldm要设置的 rcxwlbdldm
	 */
	public void setRcxwlbdldm(String rcxwlbdldm) {
		this.rcxwlbdldm = rcxwlbdldm;
	}
	/**
	 * @return the rcxwlbdlmc
	 */
	public String getRcxwlbdlmc() {
		return rcxwlbdlmc;
	}
	/**
	 * @param rcxwlbdlmc要设置的 rcxwlbdlmc
	 */
	public void setRcxwlbdlmc(String rcxwlbdlmc) {
		this.rcxwlbdlmc = rcxwlbdlmc;
	}
	/**
	 * @return the rcxwlbdm
	 */
	public String getRcxwlbdm() {
		return rcxwlbdm;
	}
	/**
	 * @param rcxwlbdm要设置的 rcxwlbdm
	 */
	public void setRcxwlbdm(String rcxwlbdm) {
		this.rcxwlbdm = rcxwlbdm;
	}
	/**
	 * @return the rcxwlbmc
	 */
	public String getRcxwlbmc() {
		return rcxwlbmc;
	}
	/**
	 * @param rcxwlbmc要设置的 rcxwlbmc
	 */
	public void setRcxwlbmc(String rcxwlbmc) {
		this.rcxwlbmc = rcxwlbmc;
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
	 * @return the lcxx
	 */
	public String getLcxx() {
		return lcxx;
	}
	/**
	 * @param lcxx要设置的 lcxx
	 */
	public void setLcxx(String lcxx) {
		this.lcxx = lcxx;
	}
	/**
	 * @return the rcxwlbfz
	 */
	public String getRcxwlbfz() {
		return rcxwlbfz;
	}
	/**
	 * @param rcxwlbfz要设置的 rcxwlbfz
	 */
	public void setRcxwlbfz(String rcxwlbfz) {
		this.rcxwlbfz = rcxwlbfz;
	}
	/**
	 * @return the rcxwfzlx
	 */
	public String getRcxwfzlx() {
		return rcxwfzlx;
	}
	/**
	 * @param rcxwfzlx要设置的 rcxwfzlx
	 */
	public void setRcxwfzlx(String rcxwfzlx) {
		this.rcxwfzlx = rcxwfzlx;
	}
	/**
	 * @return the rcxwdlfssx
	 */
	public String getRcxwdlfssx() {
		return rcxwdlfssx;
	}
	/**
	 * @param rcxwdlfssx要设置的 rcxwdlfssx
	 */
	public void setRcxwdlfssx(String rcxwdlfssx) {
		this.rcxwdlfssx = rcxwdlfssx;
	}
	/**
	 * @return the rcxwdljcf
	 */
	public String getRcxwdljcf() {
		return rcxwdljcf;
	}
	/**
	 * @param rcxwdljcf要设置的 rcxwdljcf
	 */
	public void setRcxwdljcf(String rcxwdljcf) {
		this.rcxwdljcf = rcxwdljcf;
	}
	
}
