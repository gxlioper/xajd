
package xgxt.pjpy.ynys.zhszcp;

import xgxt.pjpy.PjpyActionForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 云南艺术评奖评优综合素质测评ActionForm
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-31</p>
 */
public class PjpyYnysZhszcpActionForm extends PjpyActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8504061635937744353L;

	private String szlx;//素质类型
	private String[] jcxm;//奖惩项目
	private String fs[];//分数
	private String lr[];//奖惩内容
	private String zt;//状态
	private String slr;
	private String sfs;
	private String szt;
	private String[] kcmc;
	private String[] cj;
	private String[] kclx;
	private String cxfs;
	private String[] dfxm;
	private String[] df;
	private String sdfxm;
	private String sdf;
	private String[] sfbxk;
	private String shzt;
	private String[] xid;
	private String delIds;
	
	public String getDelIds() {
		return delIds;
	}
	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}
	public String getSdf() {
		return sdf;
	}
	public void setSdf(String sdf) {
		this.sdf = sdf;
	}
	public String getSdfxm() {
		return sdfxm;
	}
	public void setSdfxm(String sdfxm) {
		this.sdfxm = sdfxm;
	}
	public String[] getDf() {
		return df;
	}
	public void setDf(String[] df) {
		this.df = df;
	}
	public String[] getDfxm() {
		return dfxm;
	}
	public void setDfxm(String[] dfxm) {
		this.dfxm = dfxm;
	}
	public String getCxfs() {
		return cxfs;
	}
	public void setCxfs(String cxfs) {
		this.cxfs = cxfs;
	}
	public String[] getCj() {
		return cj;
	}
	public void setCj(String[] cj) {
		this.cj = cj;
	}
	public String[] getKclx() {
		return kclx;
	}
	public void setKclx(String[] kclx) {
		this.kclx = kclx;
	}
	public String[] getKcmc() {
		return kcmc;
	}
	public void setKcmc(String[] kcmc) {
		this.kcmc = kcmc;
	}
	public String getSfs() {
		return sfs;
	}
	public void setSfs(String sfs) {
		this.sfs = sfs;
	}
	public String getSlr() {
		return slr;
	}
	public void setSlr(String slr) {
		this.slr = slr;
	}
	public String getSzt() {
		return szt;
	}
	public void setSzt(String szt) {
		this.szt = szt;
	}
	public String[] getFs() {
		return fs;
	}
	public void setFs(String[] fs) {
		this.fs = fs;
	}
	public String[] getJcxm() {
		return jcxm;
	}
	public void setJcxm(String[] jcxm) {
		this.jcxm = jcxm;
	}
	public String[] getLr() {
		return lr;
	}
	public void setLr(String[] lr) {
		this.lr = lr;
	}
	public String getSzlx() {
		return szlx;
	}
	public void setSzlx(String szlx) {
		this.szlx = szlx;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String[] getSfbxk() {
		return sfbxk;
	}
	public void setSfbxk(String[] sfbxk) {
		this.sfbxk = sfbxk;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String[] getXid() {
		return xid;
	}
	public void setXid(String[] xid) {
		this.xid = xid;
	}
}
