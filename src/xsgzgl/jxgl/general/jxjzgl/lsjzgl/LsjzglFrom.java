package xsgzgl.jxgl.general.jxjzgl.lsjzgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.form.CommanForm;
import xgxt.form.User;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_历史建制管理_Model类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 易江东
 * @version 1.0
 */

public class LsjzglFrom extends CommanForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	//军训建制等级表
	private String djdm;	//代码
	private String djmc;	//名称
	private String djdj;	//等级
	
	//军训建制名单表
	private String mdid;	//名单ID
	private String jzid;	//建制ID
	private String xh;	//学号
	
	//军训建制维护表
	private String jzmc;	//建制名称
	private String jzjb;	//建制级别
	private String sjid;	//上级ID
	private String jgmc;	//教官名称
	private String jgdh;	//教官电话
	private String jsmc;	//教师名称
	private String jsdh;	//教师电话
	
	//非业务字段
	private String jxid;	//军训ID
	private ExportModel exportModel = new ExportModel();
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDjdm() {
		return djdm;
	}
	public void setDjdm(String djdm) {
		this.djdm = djdm;
	}
	public String getDjmc() {
		return djmc;
	}
	public void setDjmc(String djmc) {
		this.djmc = djmc;
	}
	public String getDjdj() {
		return djdj;
	}
	public void setDjdj(String djdj) {
		this.djdj = djdj;
	}
	public String getMdid() {
		return mdid;
	}
	public void setMdid(String mdid) {
		this.mdid = mdid;
	}
	public String getJzid() {
		return jzid;
	}
	public void setJzid(String jzid) {
		this.jzid = jzid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getJzmc() {
		return jzmc;
	}
	public void setJzmc(String jzmc) {
		this.jzmc = jzmc;
	}
	public String getJzjb() {
		return jzjb;
	}
	public void setJzjb(String jzjb) {
		this.jzjb = jzjb;
	}
	public String getSjid() {
		return sjid;
	}
	public void setSjid(String sjid) {
		this.sjid = sjid;
	}
	public String getJgmc() {
		return jgmc;
	}
	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}
	public String getJgdh() {
		return jgdh;
	}
	public void setJgdh(String jgdh) {
		this.jgdh = jgdh;
	}
	public String getJsmc() {
		return jsmc;
	}
	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}
	public String getJsdh() {
		return jsdh;
	}
	public void setJsdh(String jsdh) {
		this.jsdh = jsdh;
	}
	public String getJxid() {
		return jxid;
	}
	public void setJxid(String jxid) {
		this.jxid = jxid;
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
	
	
}
