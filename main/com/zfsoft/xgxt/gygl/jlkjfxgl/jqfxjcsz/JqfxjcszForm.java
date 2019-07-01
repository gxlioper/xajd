package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz;


import org.apache.struts.action.ActionForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-11 上午10:46:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 * 
 */
public class JqfxjcszForm extends ActionForm {
	
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 3047822587143156629L;
	
	private String fxkg;
	private String fxdm;
	private String fxtxkssj;
	private String fxtxzzsj;
	private String isopen ;//当前时间是否开启
	private String tbsj;//青岛酒店管理职业学院
	
	/**
	 * @return the fxkg
	 */
	public String getFxkg() {
		return fxkg;
	}
	/**
	 * @param fxkg要设置的 fxkg
	 */
	public void setFxkg(String fxkg) {
		this.fxkg = fxkg;
	}
	/**
	 * @return the tbsj
	 */
	public String getTbsj() {
		return tbsj;
	}
	/**
	 * @param tbsj要设置的 tbsj
	 */
	public void setTbsj(String tbsj) {
		this.tbsj = tbsj;
	}
	/**
	 * @return the fxdm
	 */
	public String getFxdm() {
		return fxdm;
	}
	/**
	 * @param fxdm要设置的 fxdm
	 */
	public void setFxdm(String fxdm) {
		this.fxdm = fxdm;
	}
	/**
	 * @return the fxtxkssj
	 */
	public String getFxtxkssj() {
		return fxtxkssj;
	}
	/**
	 * @param fxtxkssj要设置的 fxtxkssj
	 */
	public void setFxtxkssj(String fxtxkssj) {
		this.fxtxkssj = fxtxkssj;
	}
	/**
	 * @return the fxtxzzsj
	 */
	public String getFxtxzzsj() {
		return fxtxzzsj;
	}
	/**
	 * @param fxtxzzsj要设置的 fxtxzzsj
	 */
	public void setFxtxzzsj(String fxtxzzsj) {
		this.fxtxzzsj = fxtxzzsj;
	}
	/**
	 * @return the isopen
	 */
	public String getIsopen() {
		return isopen;
	}
	/**
	 * @param isopen要设置的 isopen
	 */
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
			

}
