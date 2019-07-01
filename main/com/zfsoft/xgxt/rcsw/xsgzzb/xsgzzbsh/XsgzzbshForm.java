package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsh;
import com.zfsoft.xgxt.rcsw.xsgzzb.comm.XsgzzbForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 周报审核
 */
public class XsgzzbshForm extends XsgzzbForm {

	private static final long serialVersionUID = 1L;
	private String sqid;
	private String xn;
	private String xq;
	private String bmdm;
	private String bmdmmc;
	private String zc;
	private String zcmc;
	private String zcksjsrq;
	private String yzzygz;
	private String xzzygz;
	private String yj;
	private String lrsj;
	private String lrr;
	private String lrrxm;
	private String shzt;
	private String splc;
	private String type;
	private String xqmc;
	private String qtgz;
	
	private String gzzblx ;//工作周报类型（学院xy、班级bj）
	// =========== 班级周报 < ============
	private String bjdm;
	private String ydrs;
	private String sdrs;
	private String qjrs;
	private String wdrs;
	private String zynr;
	private String zywt;
	private String jjdc;
	private String filepath;
	private String nj;
	private String xymc;
	private String zymc;
	private String bjmc;
	// =========== 班级周报 > ============
	
	private String shlx;
	private String ywid;
	private String shsj;
	private String shr;
	private String shyj;
	private String shlc;
	private String gwid;
	private String shztmc;
	private String shid;
	private String thgw;//岗位退回
	private String shjg;
	
	private String[] id;
	private String[] splcs;
	private String[] gwids;
	private String[] lrrs;
	
	//四川信息职业技术学院附件上传的文件
	private String filegid;//附件组id
	/**
	 * @return the filegid
	 */
	public String getFilegid() {
		return filegid;
	}
	/**
	 * @param filegid要设置的 filegid
	 */
	public void setFilegid(String filegid) {
		this.filegid = filegid;
	}
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
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
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getBmdmmc() {
		return bmdmmc;
	}
	public void setBmdmmc(String bmdmmc) {
		this.bmdmmc = bmdmmc;
	}
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
	}
	public String getZcmc() {
		return zcmc;
	}
	public void setZcmc(String zcmc) {
		this.zcmc = zcmc;
	}
	public String getZcksjsrq() {
		return zcksjsrq;
	}
	public void setZcksjsrq(String zcksjsrq) {
		this.zcksjsrq = zcksjsrq;
	}
	public String getYzzygz() {
		return yzzygz;
	}
	public void setYzzygz(String yzzygz) {
		this.yzzygz = yzzygz;
	}
	public String getXzzygz() {
		return xzzygz;
	}
	public void setXzzygz(String xzzygz) {
		this.xzzygz = xzzygz;
	}
	public String getYj() {
		return yj;
	}
	public void setYj(String yj) {
		this.yj = yj;
	}
	public String getLrsj() {
		return lrsj;
	}
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public String getLrrxm() {
		return lrrxm;
	}
	public void setLrrxm(String lrrxm) {
		this.lrrxm = lrrxm;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public String getQtgz() {
		return qtgz;
	}
	public void setQtgz(String qtgz) {
		this.qtgz = qtgz;
	}
	public String getShlx() {
		return shlx;
	}
	public void setShlx(String shlx) {
		this.shlx = shlx;
	}
	public String getYwid() {
		return ywid;
	}
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	public String getShsj() {
		return shsj;
	}
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getShlc() {
		return shlc;
	}
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	public String getGwid() {
		return gwid;
	}
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	public String getShztmc() {
		return shztmc;
	}
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	public String getShid() {
		return shid;
	}
	public void setShid(String shid) {
		this.shid = shid;
	}
	public String getThgw() {
		return thgw;
	}
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	public String getShjg() {
		return shjg;
	}
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}
	public String[] getSplcs() {
		return splcs;
	}
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	public String[] getGwids() {
		return gwids;
	}
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	public String[] getLrrs() {
		return lrrs;
	}
	public void setLrrs(String[] lrrs) {
		this.lrrs = lrrs;
	}
	public String getGzzblx() {
		return gzzblx;
	}
	public void setGzzblx(String gzzblx) {
		this.gzzblx = gzzblx;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getYdrs() {
		return ydrs;
	}
	public void setYdrs(String ydrs) {
		this.ydrs = ydrs;
	}
	public String getSdrs() {
		return sdrs;
	}
	public void setSdrs(String sdrs) {
		this.sdrs = sdrs;
	}
	public String getQjrs() {
		return qjrs;
	}
	public void setQjrs(String qjrs) {
		this.qjrs = qjrs;
	}
	public String getWdrs() {
		return wdrs;
	}
	public void setWdrs(String wdrs) {
		this.wdrs = wdrs;
	}
	public String getZynr() {
		return zynr;
	}
	public void setZynr(String zynr) {
		this.zynr = zynr;
	}
	public String getZywt() {
		return zywt;
	}
	public void setZywt(String zywt) {
		this.zywt = zywt;
	}
	public String getJjdc() {
		return jjdc;
	}
	public void setJjdc(String jjdc) {
		this.jjdc = jjdc;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
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
	
}
