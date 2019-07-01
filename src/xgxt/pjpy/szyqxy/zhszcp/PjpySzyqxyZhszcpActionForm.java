package xgxt.pjpy.szyqxy.zhszcp;

import xgxt.pjpy.PjpyActionForm;
import xgxt.utils.Pages;

public class PjpySzyqxyZhszcpActionForm extends PjpyActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4245679924929711959L;
	
	private Pages pages = new Pages();
	
	private String nj;

	private String xydm;

	private String zydm;

	private String bjdm;

	private String xh;

	private String xm;

	private String xn;

	private String xq;

	private String jjfyy;

	private String rq;

	private String jf;

	private String kf;

	private String lb;

	private String id;

	private String[] hdzt;

	private String[] hddj;

	private String[] hdpf;

	private String[] hdrq;

	private String[] shfz;

	// 分值项目
	private String[] fzxm;

	private String[] hddd;

	private String[] hdsj;

	private String[] jjf;

	// 报表类型
	private String bblx;

	// 周次
	private String zc;

	// 月份
	private String yf;

	// 分值
	private String[] fz;

	// 基础分值
	private String[] jcfz;

	// 最高分值
	private String[] zgfz;

	// 模块代码
	private String[] mkdm;

	// 原因
	private String[] yy;
	
	// 审核
	private String[] xxsh;

	private String[] checkVal;

	// 审核状态
	private String shzt;

	private String[] shType;

	private String[] hdnr;

	private String[] jldj;

	private String[] jztm;

	private String[] jcdj;

	private String[] ccdj;

	private String[] lrsj;

	private String[] yybdnr;

	private String[] xthdrq;

	private String[] dsrq;

	private String[] dsxd;

	private String[] sfhj;

	private String[] pf;

	private String[] lrrq;

	private String[] dsmc;

	private String[] fivexh;
	
	private String[] hdxh;
	
	private String zhszzf;
	
	public String getZhszzf() {
		return zhszzf;
	}

	public void setZhszzf(String zhszzf) {
		this.zhszzf = zhszzf;
	}

	public String[] getHdxh() {
		return hdxh;
	}

	public void setHdxh(String[] hdxh) {
		this.hdxh = hdxh;
	}

	public String[] getShType() {
		return shType;
	}

	public void setShType(String[] shType) {
		this.shType = shType;
	}

	public String[] getJcfz() {
		return jcfz;
	}

	public void setJcfz(String[] jcfz) {
		this.jcfz = jcfz;
	}

	public String[] getZgfz() {
		return zgfz;
	}

	public void setZgfz(String[] zgfz) {
		this.zgfz = zgfz;
	}

	public String[] getHddd() {
		return hddd;
	}

	public void setHddd(String[] hddd) {
		this.hddd = hddd;
	}

	public String[] getHdsj() {
		return hdsj;
	}

	public void setHdsj(String[] hdsj) {
		this.hdsj = hdsj;
	}

	public String getJf() {
		return jf;
	}

	public void setJf(String jf) {
		this.jf = jf;
	}

	public String getJjfyy() {
		return jjfyy;
	}

	public void setJjfyy(String jjfyy) {
		this.jjfyy = jjfyy;
	}

	public String getKf() {
		return kf;
	}

	public void setKf(String kf) {
		this.kf = kf;
	}

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
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

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String[] getHdzt() {
		return hdzt;
	}

	public void setHdzt(String[] hdzt) {
		this.hdzt = hdzt;
	}

	public String[] getHddj() {
		return hddj;
	}

	public void setHddj(String[] hddj) {
		this.hddj = hddj;
	}

	public String[] getHdpf() {
		return hdpf;
	}

	public void setHdpf(String[] hdpf) {
		this.hdpf = hdpf;
	}

	public String[] getHdrq() {
		return hdrq;
	}

	public void setHdrq(String[] hdrq) {
		this.hdrq = hdrq;
	}

	public String[] getShfz() {
		return shfz;
	}

	public void setShfz(String[] shfz) {
		this.shfz = shfz;
	}

	public String[] getJjf() {
		return jjf;
	}

	public void setJjf(String[] jjf) {
		this.jjf = jjf;
	}

	public String[] getFz() {
		return fz;
	}

	public void setFz(String[] fz) {
		this.fz = fz;
	}

	public String[] getFzxm() {
		return fzxm;
	}

	public void setFzxm(String[] fzxm) {
		this.fzxm = fzxm;
	}

	public String[] getHdnr() {
		return hdnr;
	}

	public void setHdnr(String[] hdnr) {
		this.hdnr = hdnr;
	}

	public String[] getJldj() {
		return jldj;
	}

	public void setJldj(String[] jldj) {
		this.jldj = jldj;
	}

	public String[] getJztm() {
		return jztm;
	}

	public void setJztm(String[] jztm) {
		this.jztm = jztm;
	}

	public String[] getJcdj() {
		return jcdj;
	}

	public void setJcdj(String[] jcdj) {
		this.jcdj = jcdj;
	}

	public String[] getCcdj() {
		return ccdj;
	}

	public void setCcdj(String[] ccdj) {
		this.ccdj = ccdj;
	}

	public String[] getLrsj() {
		return lrsj;
	}

	public void setLrsj(String[] lrsj) {
		this.lrsj = lrsj;
	}

	public String[] getYybdnr() {
		return yybdnr;
	}

	public void setYybdnr(String[] yybdnr) {
		this.yybdnr = yybdnr;
	}

	public String[] getXthdrq() {
		return xthdrq;
	}

	public void setXthdrq(String[] xthdrq) {
		this.xthdrq = xthdrq;
	}

	public String[] getDsrq() {
		return dsrq;
	}

	public void setDsrq(String[] dsrq) {
		this.dsrq = dsrq;
	}

	public String[] getDsxd() {
		return dsxd;
	}

	public void setDsxd(String[] dsxd) {
		this.dsxd = dsxd;
	}

	public String[] getSfhj() {
		return sfhj;
	}

	public void setSfhj(String[] sfhj) {
		this.sfhj = sfhj;
	}

	public String[] getPf() {
		return pf;
	}

	public void setPf(String[] pf) {
		this.pf = pf;
	}

	public String[] getLrrq() {
		return lrrq;
	}

	public void setLrrq(String[] lrrq) {
		this.lrrq = lrrq;
	}

	public String[] getDsmc() {
		return dsmc;
	}

	public void setDsmc(String[] dsmc) {
		this.dsmc = dsmc;
	}

	public String[] getMkdm() {
		return mkdm;
	}

	public void setMkdm(String[] mkdm) {
		this.mkdm = mkdm;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String[] getYy() {
		return yy;
	}

	public void setYy(String[] yy) {
		this.yy = yy;
	}

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public String getZc() {
		return zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}

	public String getBblx() {
		return bblx;
	}

	public void setBblx(String bblx) {
		this.bblx = bblx;
	}

	public String[] getxxsh() {
		return xxsh;
	}

	public void setXxsh(String[] xxsh) {
		this.xxsh = xxsh;
	}

	public String[] getFivexh() {
		return fivexh;
	}

	public void setFivexh(String[] fivexh) {
		this.fivexh = fivexh;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
