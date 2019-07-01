package xgxt.pjpy.tyb.zhszcp.model;

import xgxt.pjpy.tablesmodel.PjpyModel;
import xgxt.utils.String.StringUtils;

public class PjpyZhszcpModel extends PjpyModel{

	private String[] dm;    
	private String[] mc;
	private String[] bl;    
	private String[] xzf;   
	private String[] lb;    
	private String[] bm;    
	private String[] zd;    
	private String[] fdm;   
	private String[] tj;    
	private String[] sfplzj;
	private String[] shjb;
	private String[] dmjb;
	private String[] mrf;
	private String[] zj;
	private String[] zjz;
	private String[] sfwh;
	private String yjdm;
	private String zczq;
	private String pjzq;
	private String zcfdm;
	private String zcdmjb;

	public String[] getDmjb() {
		return dmjb;
	}
	public void setDmjb(String[] dmjb) {
		this.dmjb = dmjb;
	}
	public String[] getBl() {
		return bl;
	}
	public void setBl(String[] bl) {
		this.bl = bl;
	}
	public String[] getBm() {
		return bm;
	}
	public void setBm(String[] bm) {
		this.bm = bm;
	}
	public String[] getDm() {
		return dm;
	}
	public void setDm(String[] dm) {
		this.dm = dm;
	}
	public String[] getFdm() {
		return fdm;
	}
	public void setFdm(String[] fdm) {
		this.fdm = fdm;
	}
	public String[] getLb() {
		return lb;
	}
	public void setLb(String[] lb) {
		this.lb = lb;
	}
	public String[] getMc() {
		return mc;
	}
	public void setMc(String[] mc) {
		this.mc = mc;
	}
	public String[] getSfplzj() {
		return sfplzj;
	}
	public void setSfplzj(String[] sfplzj) {
		this.sfplzj = sfplzj;
	}
	public String[] getShjb() {
		return shjb;
	}
	public void setShjb(String[] shjb) {
		this.shjb = shjb;
	}
	public String[] getTj() {
		return tj;
	}
	public void setTj(String[] tj) {
		this.tj = tj;
	}
	public String[] getXzf() {
		return xzf;
	}
	public void setXzf(String[] xzf) {
		this.xzf = xzf;
	}
	public String[] getZd() {
		return zd;
	}
	public void setZd(String[] zd) {
		this.zd = zd;
	}
	public String[] getMrf() {
		return mrf;
	}
	public void setMrf(String[] mrf) {
		this.mrf = mrf;
	}
	public String[] getZj() {
		return zj;
	}
	public void setZj(String[] zj) {
		this.zj = zj;
	}
	public String[] getZjz() {
		return zjz;
	}
	public void setZjz(String[] zjz) {
		this.zjz = zjz;
	}
	public String getYjdm() {
		return yjdm;
	}
	public void setYjdm(String yjdm) {
		this.yjdm = yjdm;
	}
	public String[] getSfwh() {
		return sfwh;
	}
	public void setSfwh(String[] sfwh) {
		this.sfwh = sfwh;
	}
	public String getPjzq() {
		return pjzq;
	}
	public void setPjzq(String pjzq) {
		this.pjzq = pjzq;
	}
	public String getZczq() {
		return zczq;
	}
	public void setZczq(String zczq) {
		this.zczq = zczq;
	}
	public String getZcdmjb() {
		return zcdmjb;
	}
	public void setZcdmjb(String zcdmjb) {
		this.zcdmjb = zcdmjb;
	}
	public String getZcfdm() {
		return zcfdm;
	}
	public void setZcfdm(String zcfdm) {
		this.zcfdm = zcfdm;
	}
	
	public String getXn(){
		return StringUtils.isNull(xn) ? "нч" : xn;
	}
	
	public String getXq(){
		return StringUtils.isNull(xq) ? "нч" : xq;
	}
	
	public String getNd(){
		return StringUtils.isNull(nd) ? "нч" : nd;
	}
}
