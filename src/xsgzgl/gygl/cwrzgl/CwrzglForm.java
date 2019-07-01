package xsgzgl.gygl.cwrzgl;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xsgzgl.gygl.comm.GyglNewForm;

public class CwrzglForm extends GyglNewForm {

	
	private static final long serialVersionUID = 1L;

	private String lddm;//楼栋代码
	private String ldmc;//楼栋名称
	private String ldxb;//楼栋性别
	private String ldcs;//楼栋层数
	private String qsch;//起始层号
	private String sfhlc;//是否含0层
	private String xqdm;//校区代码
	private String yqdm;//园区代码
	private String bz;//备注
	private String cwfpdx;
	
	private String nj;//年级
	private String xydm;//学院
	private String zydm;//专业
	private String bjdm;//班级
	private String xb;//性别
	private String xhxm;//学号姓名
	private String rzqk;//入住情况
	private String cwzt;//床位状态
	
	private String rzsj;//入住时间
	private String tsyy;//退宿原因
	
	private FormFile impFilePath;//导入文件
	
	private String rzyy;//入住原因

	/**
	 * 学年
	 */
	private String xn;
	
	/**
	 * 学期
	 */
	private String xq;
	
	private String yzlbdm;
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getTsyy() {
		return tsyy;
	}
	public void setTsyy(String tsyy) {
		this.tsyy = tsyy;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getLdmc() {
		return ldmc;
	}
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	public String getLdxb() {
		return ldxb;
	}
	public void setLdxb(String ldxb) {
		this.ldxb = ldxb;
	}
	public String getLdcs() {
		return ldcs;
	}
	public void setLdcs(String ldcs) {
		this.ldcs = ldcs;
	}
	public String getQsch() {
		return qsch;
	}
	public void setQsch(String qsch) {
		this.qsch = qsch;
	}
	public String getSfhlc() {
		return sfhlc;
	}
	public void setSfhlc(String sfhlc) {
		this.sfhlc = sfhlc;
	}
	public String getXqdm() {
		return xqdm;
	}
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	public String getYqdm() {
		return yqdm;
	}
	public void setYqdm(String yqdm) {
		this.yqdm = yqdm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCwfpdx() {
		return cwfpdx;
	}
	public void setCwfpdx(String cwfpdx) {
		this.cwfpdx = cwfpdx;
	}
	
	
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getXhxm() {
		return xhxm;
	}
	public void setXhxm(String xhxm) {
		this.xhxm = xhxm;
	}
	public String getRzqk() {
		return rzqk;
	}
	public void setRzqk(String rzqk) {
		this.rzqk = rzqk;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getCwzt() {
		return cwzt;
	}
	public void setCwzt(String cwzt) {
		this.cwzt = cwzt;
	}
	public FormFile getImpFilePath() {
		return impFilePath;
	}
	public void setImpFilePath(FormFile impFilePath) {
		this.impFilePath = impFilePath;
	}
	public String getRzyy() {
		return rzyy;
	}
	public void setRzyy(String rzyy) {
		this.rzyy = rzyy;
	}
	/**
	 * @return the yzlbdm
	 */
	public String getYzlbdm() {
		return yzlbdm;
	}
	/**
	 * @param yzlbdm要设置的 yzlbdm
	 */
	public void setYzlbdm(String yzlbdm) {
		this.yzlbdm = yzlbdm;
	}
	
}
