/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 下午04:38:53 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg;

import com.zfsoft.xgxt.rcsw.rcxwwh.comm.RcxwwhForm;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： dlq[工号：995]
 * @时间： 2013-8-7 下午04:38:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RcxwjgForm extends RcxwwhForm{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 4231203486782621475L;
	
	private String id;
	private String rcxwxxid;
	private String xb;
	private String xm;
	private String bjmc;
	private String xn;
	private String xq;
	private String rcxwjlsj;
	private String bz;
	private String type;
	private String xh;
	private String rcxwlbdldm;
	private String rcxwlbdlmc;
	private String rcxwlbmc;
	private String rcxwlbdm;
	private String rcxwlbfz;
	private String shztmc;
	private String shyj;
	
	private String sjly; //数据来源
	
	private String[] xwlbdmArr;//批量增加行为维护行为类别代码
	private String[] fzArray;//批量增加行为维护行为分值
	private String[] xwdldmArr;//批量增加行为维护行为大类代码
	private String[] gflyArr;//批量增加给分理由
	private String fz;//最终得分
	private String rcxwlbzdfz;//行为类别最低分值
	private String rcxwlbzgfz;//行为类别最高分值
	private String fssj;//发生时间
	private String jlr;//记录人
	private String[] fssjArr;//批量增加行为维护行为发生时间
	private String rcxwlbbzsj;//评分说明缩减为10字显示字段
	private String fzqj;//分值区间
	private String gfly;//给分理由
	private String fjlj;//附件路径
	private String fjmc;
	
	
	
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
	 * @return the xwdldmArr
	 */
	public String[] getXwdldmArr() {
		return xwdldmArr;
	}
	/**
	 * @param xwdldmArr要设置的 xwdldmArr
	 */
	public void setXwdldmArr(String[] xwdldmArr) {
		this.xwdldmArr = xwdldmArr;
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
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}
	/**
	 * @param xb要设置的 xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmc要设置的 bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
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
	 * @return the rcxwxxid
	 */
	public String getRcxwxxid() {
		return rcxwxxid;
	}
	/**
	 * @param rcxwxxid要设置的 rcxwxxid
	 */
	public void setRcxwxxid(String rcxwxxid) {
		this.rcxwxxid = rcxwxxid;
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
	public void setFz(String fz) {
		this.fz = fz;
	}
	public String getFz() {
		return fz;
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
