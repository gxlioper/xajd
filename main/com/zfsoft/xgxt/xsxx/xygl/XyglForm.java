/**
 * @部门:学工产品事业部
 * @日期：2015-9-8 上午09:31:48 
 */  
package com.zfsoft.xgxt.xsxx.xygl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息new
 * @类功能描述: 校友管理Form 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2015-9-8 上午09:31:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyglForm extends CommForm {
	
	private static final long serialVersionUID = 3342672568022096329L;
	private ExportModel exportModel = new ExportModel();
	private String type;
	private User user;
	
	private String xh; // 学号
	private String xm; // 姓名
	private String xb; // 性别
	private String xszp; // 照片
	private String nj; // 年级
	private String xy;// 学院名称
	private String xymc; // 学院名称
	private String zymc; // 专业名称
	private String bjmc; // 班级名称
	private String jg; // 籍贯
	private String jgs; // 籍贯省
	private String jgshi; // 籍贯市
	private String jgx; // 籍贯县
	private String zw; // 在校担任职务
	private String qqhm; // QQ号码
	private String pycc; // 培养层次
	private String shgxgx2; // 现工作单位
	private String shgxgzdw2; // 单位地址
	private String shgxzw2; // 职务
	private String rxqdw; // 捐赠信息
	private String gzbx; // 动态信息
	private String sjhm; // 联系方式
	private String sfzx;// 是否在校
	
	public XyglForm() {
		super();
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getXszp() {
		return xszp;
	}

	public void setXszp(String xszp) {
		this.xszp = xszp;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getJgs() {
		return jgs;
	}

	public void setJgs(String jgs) {
		this.jgs = jgs;
	}

	public String getJgshi() {
		return jgshi;
	}

	public void setJgshi(String jgshi) {
		this.jgshi = jgshi;
	}

	public String getJgx() {
		return jgx;
	}

	public void setJgx(String jgx) {
		this.jgx = jgx;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public String getQqhm() {
		return qqhm;
	}

	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}

	public String getPycc() {
		return pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	public String getShgxgx2() {
		return shgxgx2;
	}

	public void setShgxgx2(String shgxgx2) {
		this.shgxgx2 = shgxgx2;
	}

	public String getShgxgzdw2() {
		return shgxgzdw2;
	}

	public void setShgxgzdw2(String shgxgzdw2) {
		this.shgxgzdw2 = shgxgzdw2;
	}

	public String getShgxzw2() {
		return shgxzw2;
	}

	public void setShgxzw2(String shgxzw2) {
		this.shgxzw2 = shgxzw2;
	}

	public String getRxqdw() {
		return rxqdw;
	}

	public void setRxqdw(String rxqdw) {
		this.rxqdw = rxqdw;
	}

	public String getGzbx() {
		return gzbx;
	}

	public void setGzbx(String gzbx) {
		this.gzbx = gzbx;
	}	

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getSfzx() {
		return sfzx;
	}

	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}
	
}
