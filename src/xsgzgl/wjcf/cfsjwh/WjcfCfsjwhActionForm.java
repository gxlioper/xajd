package xsgzgl.wjcf.cfsjwh;

import org.apache.struts.upload.FormFile;

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
 * Author: ltt
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-17 下午03:45:48
 * </p>
 */
public class WjcfCfsjwhActionForm extends CommForm  {

	private String cfid  ;
	private String cflbmc;
	private String cfyymc;
	private String cflbdm;
	private String cfyydm;
	private String cfsj  ;
	private String cfwh  ;
	private String wjsj  ;
	private String sbb   ;
	private String sbsj  ;
	private String wjssjg;
	private String bz    ;
	private String sfsc  ; 
	private String sssj  ;
	private String sswh  ;
	private String ssjg  ;
	private String cfggw ;
	private String ssyj  ;
	private String jcwh  ;
	private String jcsj  ;
	private String jcyj  ;
	private String fjmc  ;
	private String jcjg  ;
	private String zzwh  ;
	private String zzsj  ;
	private String zzyj  ;
	private User user;
	private FormFile fj;
	private ExportModel exportModel = new ExportModel();
	
	//玉林师范学院处分决定书下载和拟处分告知书下载需要用到的字段
	private String xh;
	private String xm;
	private String xb;
	private String xymc;
	private String bjmc;
	private String zzmmmc;
	private String dysj;
	private String csrq;
	private String zymc;
	private String cfyj;
	
	public String getCfyj() {
		return cfyj;
	}
	public void setCfyj(String cfyj) {
		this.cfyj = cfyj;
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
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getZzmmmc() {
		return zzmmmc;
	}
	public void setZzmmmc(String zzmmmc) {
		this.zzmmmc = zzmmmc;
	}
	public String getDysj() {
		return dysj;
	}
	public void setDysj(String dysj) {
		this.dysj = dysj;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCfggw() {
		return cfggw;
	}
	public void setCfggw(String cfggw) {
		this.cfggw = cfggw;
	}
	public String getCfid() {
		return cfid;
	}
	public void setCfid(String cfid) {
		this.cfid = cfid;
	}
	public String getCflbmc() {
		return cflbmc;
	}
	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}
	public String getCfsj() {
		return cfsj;
	}
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}
	public String getCfwh() {
		return cfwh;
	}
	public void setCfwh(String cfwh) {
		this.cfwh = cfwh;
	}
	public String getCfyymc() {
		return cfyymc;
	}
	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}
	public String getFjmc() {
		return fjmc;
	}
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	public String getJcjg() {
		return jcjg;
	}
	public void setJcjg(String jcjg) {
		this.jcjg = jcjg;
	}
	public String getJcsj() {
		return jcsj;
	}
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	public String getJcwh() {
		return jcwh;
	}
	public void setJcwh(String jcwh) {
		this.jcwh = jcwh;
	}
	public String getJcyj() {
		return jcyj;
	}
	public void setJcyj(String jcyj) {
		this.jcyj = jcyj;
	}
	public String getSbb() {
		return sbb;
	}
	public void setSbb(String sbb) {
		this.sbb = sbb;
	}
	public String getSbsj() {
		return sbsj;
	}
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}
	public String getSfsc() {
		return sfsc;
	}
	public void setSfsc(String sfsc) {
		this.sfsc = sfsc;
	}
	public String getSsjg() {
		return ssjg;
	}
	public void setSsjg(String ssjg) {
		this.ssjg = ssjg;
	}
	public String getSssj() {
		return sssj;
	}
	public void setSssj(String sssj) {
		this.sssj = sssj;
	}
	public String getSswh() {
		return sswh;
	}
	public void setSswh(String sswh) {
		this.sswh = sswh;
	}
	public String getSsyj() {
		return ssyj;
	}
	public void setSsyj(String ssyj) {
		this.ssyj = ssyj;
	}
	public String getWjsj() {
		return wjsj;
	}
	public void setWjsj(String wjsj) {
		this.wjsj = wjsj;
	}
	public String getWjssjg() {
		return wjssjg;
	}
	public void setWjssjg(String wjssjg) {
		this.wjssjg = wjssjg;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCflbdm() {
		return cflbdm;
	}
	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}
	public String getCfyydm() {
		return cfyydm;
	}
	public void setCfyydm(String cfyydm) {
		this.cfyydm = cfyydm;
	}
	public FormFile getFj() {
		return fj;
	}
	public void setFj(FormFile fj) {
		this.fj = fj;
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
	 * @return the zzwh
	 */
	public String getZzwh() {
		return zzwh;
	}
	/**
	 * @param zzwh要设置的 zzwh
	 */
	public void setZzwh(String zzwh) {
		this.zzwh = zzwh;
	}
	/**
	 * @return the zzsj
	 */
	public String getZzsj() {
		return zzsj;
	}
	/**
	 * @param zzsj要设置的 zzsj
	 */
	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}
	/**
	 * @return the zzyj
	 */
	public String getZzyj() {
		return zzyj;
	}
	/**
	 * @param zzyj要设置的 zzyj
	 */
	public void setZzyj(String zzyj) {
		this.zzyj = zzyj;
	}
	
	
}
