/**
 * @部门:学工产品事业部
 * @日期：2013-8-2 上午09:23:15 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh;

import com.zfsoft.xgxt.rcsw.rcxwwh.comm.RcxwwhForm;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-8-2 上午09:23:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RcxwxxwhForm extends RcxwwhForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 2888799158521094394L;
	
	private String id;
	private String xh;
	private String xn;
	private String xq;
	private String rcxwlbdm;
	private String rcxwjlsj;
	private String bz;
	private String shzt;
	private String splc;
	private String type;
	private String rcxwlbmc;
	private String rcxwlbdlmc;
	private String rcxwlbdldm;
	private String shztmc;
	
	private String returnflag;//返回标志
	
	private String[] xwlbdmArr;//批量增加行为维护行为类别代码
	private String[] fzArray;//批量增加行为维护行为分值
	private String[] xwdldmArr;//批量增加行为维护行为大类代码
	private String[] gflyArr;//批量增加给分理由
	private String fz;//填写的分值
	private String fssj;//发生时间
	private String jlr;//记录人
	private String[] fssjArr;//批量增加行为维护行为发生时间
	private String rcxwlbbzsj;//评分说明缩减为10字显示字段
	private String fzqj;//分值区间

	private String gfly;//给分理由
	private String fjlj;//附件路径
	private String fjmc;//附件名称
	
	public String[] getGflyArr() {
		return gflyArr;
	}
	public void setGflyArr(String[] gflyArr) {
		this.gflyArr = gflyArr;
	}
	public String getGfly() {
		return gfly;
	}
	public void setGfly(String gfly) {
		this.gfly = gfly;
	}
	/**
	 * @return the xwlbdmArr
	 */
	public String[] getXwlbdmArr() {
		return xwlbdmArr;
	}
	/**
	 * @param xwlbdmArr要设置的 xwlbdmArr
	 */
	public void setXwlbdmArr(String[] xwlbdmArr) {
		this.xwlbdmArr = xwlbdmArr;
	}
	/**
	 * @return the fzArray
	 */
	public String[] getFzArray() {
		return fzArray;
	}
	/**
	 * @param fzArray要设置的 fzArray
	 */
	public void setFzArray(String[] fzArray) {
		this.fzArray = fzArray;
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
	 * @return the rcxwjlsj
	 */
	public String getRcxwjlsj() {
		return rcxwjlsj;
	}
	/**
	 * @param rcxwjlsj要设置的 rcxwjlsj
	 */
	public void setRcxwjlsj(String rcxwjlsj) {
		this.rcxwjlsj = rcxwjlsj;
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
	 * @return the returnflag
	 */
	public String getReturnflag() {
		return returnflag;
	}
	/**
	 * @param returnflag要设置的 returnflag
	 */
	public void setReturnflag(String returnflag) {
		this.returnflag = returnflag;
	}
	public void setFz(String fz) {
		this.fz = fz;
	}
	public String getFz() {
		return fz;
	}
	public void setXwdldmArr(String[] xwdldmArr) {
		this.xwdldmArr = xwdldmArr;
	}
	public String[] getXwdldmArr() {
		return xwdldmArr;
	}
	public String getFssj() {
		return fssj;
	}
	public void setFssj(String fssj) {
		this.fssj = fssj;
	}
	public String getJlr() {
		return jlr;
	}
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}
	public String[] getFssjArr() {
		return fssjArr;
	}
	public void setFssjArr(String[] fssjArr) {
		this.fssjArr = fssjArr;
	}
	public String getRcxwlbbzsj() {
		return rcxwlbbzsj;
	}
	public void setRcxwlbbzsj(String rcxwlbbzsj) {
		this.rcxwlbbzsj = rcxwlbbzsj;
	}
	public String getFzqj() {
		return fzqj;
	}
	public void setFzqj(String fzqj) {
		this.fzqj = fzqj;
	}
	/**
	 * @return the fjlj
	 */
	public String getFjlj() {
		return fjlj;
	}
	/**
	 * @param fjlj要设置的 fjlj
	 */
	public void setFjlj(String fjlj) {
		this.fjlj = fjlj;
	}
	/**
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * @param fjmc要设置的 fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

}
