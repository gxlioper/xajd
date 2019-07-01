package xsgzgl.gygl.wsjc.fscx;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.CommForm;
import xgxt.form.User;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-28 上午11:29:22
 * </p>
 */
public class FscxForm extends CommForm {
	private static final long serialVersionUID = 9219083873367155307L;
	private User user;

	private String guid;

	private String ldmc;// 楼栋名称

	private String lddm;// 楼栋代码

	private String qsch;// 起始层号

	private String qsh;// 寝室号

	private String shnj;// 所属年级

	private String shxy;// 所属学院

	private String cws;// 床位数

	private String rzrs;// 入住人数

	private String jcrc;// 检查日程

	private String fz;// 分值

	private String dj;// 分值

	private String jcrq;// 检查日期

	private String jcbm;// 检查部门

	private String jcry;// 检查人员
	
	private String yf;
	
	private String nd;
	
	private ExportModel exportModel = new ExportModel();

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public String getJcrq() {
		return jcrq;
	}

	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
	}

	public String getJcbm() {
		return jcbm;
	}

	public void setJcbm(String jcbm) {
		this.jcbm = jcbm;
	}

	public String getJcry() {
		return jcry;
	}

	public void setJcry(String jcry) {
		this.jcry = jcry;
	}

	public String getLddm() {
		return lddm;
	}

	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getLdmc() {
		return ldmc;
	}

	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}

	public String getQsch() {
		return qsch;
	}

	public void setQsch(String qsch) {
		this.qsch = qsch;
	}

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	public String getShnj() {
		return shnj;
	}

	public void setShnj(String shnj) {
		this.shnj = shnj;
	}

	public String getShxy() {
		return shxy;
	}

	public void setShxy(String shxy) {
		this.shxy = shxy;
	}

	public String getCws() {
		return cws;
	}

	public void setCws(String cws) {
		this.cws = cws;
	}

	public String getRzrs() {
		return rzrs;
	}

	public void setRzrs(String rzrs) {
		this.rzrs = rzrs;
	}

	public String getJcrc() {
		return jcrc;
	}

	public void setJcrc(String jcrc) {
		this.jcrc = jcrc;
	}

	public String getFz() {
		return fz;
	}

	public void setFz(String fz) {
		this.fz = fz;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-17 下午02:00:23 
	 * @return		: the yf
	 */
	public String getYf() {
		return yf;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-17 下午02:00:23 
	 * @param 		：yf the yf to set
	 */
	public void setYf(String yf) {
		this.yf = yf;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-17 下午02:32:58 
	 * @return		: the nd
	 */
	public String getNd() {
		return nd;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-17 下午02:32:58 
	 * @param 		：nd the nd to set
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}
	
	
}