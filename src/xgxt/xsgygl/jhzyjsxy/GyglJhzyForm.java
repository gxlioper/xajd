/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-10-8 下午01:31:30</p>
 */
package xgxt.xsgygl.jhzyjsxy;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class GyglJhzyForm extends ActionForm {

	private static final long serialVersionUID = 2184797162218352606L;
	
	private String xydm;
	private String zydm;
	private String bjdm;
	private String nj;
	private String xn;
	private String xq;
	private String bz;
	private String[] whfss;
	private String[] yhfss;
	private String yhm;
	private String zmc;
	private String bmmc;
	private String dwmc;
	private String gzs;
	private String dzyx;
	private String lxdh;
	private String lddm;
	private String lcdm;
	private String ssbh;
	private String xqdm;
    private String yesNo;
    private String szlcqk;
    private String zz;
    private String ldqk;
    private String gyfdy;
	private String hflx;    
	private String dqxn;
	private String dqxq;
	private String zgh;
	private String guid;
	private String cs;
	private String xb;
	private String mzmc;
	private String zzmmmc;
	private String xz;
	private String sfzh;
	private String xymc;
	private String zymc;
	private String bjmc;
	private String ldmc;
	private String khdx;
	private String rdkcsj;
	private String ssgyzb;
	private String zbfzr;
	private String cjhdsj;
	private String hdzt;
	private String hdnr;
	private String zw;
	private String rzrq;
	private String id;
	private String lzm;
  	private String qss;
  	private String xss;
  	private String wshgl;
  	private String wsyxl;
  	private String xjqsl;
  	private String gywmgs;
  	private String fdyyj;
  	private String xyyj;
  	private String xxyj;
  	private String xxsh;
  	private String xxshsj;	
  	private String dj;
  	private String yf;
  	private String[] lxids;
  	private String checkedValue;
  	private String[] wsdj;
  	private String jcsj;
  	private String bmdm;
  	private String lzrq;
  	private String sfzz;
  	private String[] wsdjxz;
  	private String qsz;
	private String xdqk;
	private String jcrq;
	private String fgfdy;
	private String[] ssbhT;
	private String[] xdqkT;
	private String[] yhfssbf;
	private String tjlx;
	private String tjdw;
	//公寓辅导员考核
	private String  mywsjc;
	private String  xxbsjs;
	private String  thjl;
	private String  gyhdqk;
	private String  sfzxsqsws;
	private String  xsqsdgldq;
	private String  xshd;
	private String  jb;
	private String  fdyjzf;
	private String  zpf;
	private String  sjcljsx;
	private String  ascjhy;
	private String  bzgxgz;
	private String  ayqrzgy;
	private String  mzzkyclh;
	private String[] qswsbz;
	
	Pages pages = new Pages();
	public String getTjdw() {
		return tjdw;
	}
	public void setTjdw(String tjdw) {
		this.tjdw = tjdw;
	}
	public String getTjlx() {
		return tjlx;
	}
	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
	}
	public String[] getXdqkT() {
		return xdqkT;
	}
	public void setXdqkT(String[] xdqkT) {
		this.xdqkT = xdqkT;
	}
	public String getFgfdy() {
		return fgfdy;
	}
	public void setFgfdy(String fgfdy) {
		this.fgfdy = fgfdy;
	}
	public String getJcrq() {
		return jcrq;
	}
	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
	}
	public String[] getSsbhT() {
		return ssbhT;
	}
	public void setSsbhT(String[] ssbhT) {
		this.ssbhT = ssbhT;
	}
	public String getXdqk() {
		return xdqk;
	}
	public void setXdqk(String xdqk) {
		this.xdqk = xdqk;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
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
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String[] getWhfss() {
		return whfss;
	}
	public void setWhfss(String[] whfss) {
		this.whfss = whfss;
	}
	public String[] getYhfss() {
		return yhfss;
	}
	public void setYhfss(String[] yhfss) {
		this.yhfss = yhfss;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getZmc() {
		return zmc;
	}
	public void setZmc(String zmc) {
		this.zmc = zmc;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public String getDwmc() {
		return dwmc;
	}
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	public String getGzs() {
		return gzs;
	}
	public void setGzs(String gzs) {
		this.gzs = gzs;
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}


	public String getCs() {
		return cs;
	}
	public void setCs(String cs) {
		this.cs = cs;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getCjhdsj() {
		return cjhdsj;
	}
	public void setCjhdsj(String cjhdsj) {
		this.cjhdsj = cjhdsj;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getHdnr() {
		return hdnr;
	}
	public void setHdnr(String hdnr) {
		this.hdnr = hdnr;
	}
	public String getHdzt() {
		return hdzt;
	}
	public void setHdzt(String hdzt) {
		this.hdzt = hdzt;
	}
	public String getKhdx() {
		return khdx;
	}
	public void setKhdx(String khdx) {
		this.khdx = khdx;
	}
	public String getLdmc() {
		return ldmc;
	}
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	public String getMzmc() {
		return mzmc;
	}
	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}
	public String getRdkcsj() {
		return rdkcsj;
	}
	public void setRdkcsj(String rdkcsj) {
		this.rdkcsj = rdkcsj;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getSsgyzb() {
		return ssgyzb;
	}
	public void setSsgyzb(String ssgyzb) {
		this.ssgyzb = ssgyzb;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getZbfzr() {
		return zbfzr;
	}
	public void setZbfzr(String zbfzr) {
		this.zbfzr = zbfzr;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getZzmmmc() {
		return zzmmmc;
	}
	public void setZzmmmc(String zzmmmc) {
		this.zzmmmc = zzmmmc;
	}
	public String getLcdm() {
		return lcdm;
	}
	public void setLcdm(String lcdm) {
		this.lcdm = lcdm;
	}
	public String getXqdm() {
		return xqdm;
	}
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
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
	
	
	private String gnmk;	
	private String xh;
	private String xm;
	private String qsh;
	private String qsjsqk;
	private String lc;
	public String getLc() {
		return lc;
	}
	public void setLc(String lc) {
		this.lc = lc;
	}
	public String getGnmk() {
		return gnmk;
	}
	public void setGnmk(String gnmk) {
		this.gnmk = gnmk;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getQsjsqk() {
		return qsjsqk;
	}
	public void setQsjsqk(String qsjsqk) {
		this.qsjsqk = qsjsqk;
	}
	public String getSsbh() {
		return ssbh;
	}
	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getGyfdy() {
		return gyfdy;
	}
	public void setGyfdy(String gyfdy) {
		this.gyfdy = gyfdy;
	}
	public String getHflx() {
		return hflx;
	}
	public void setHflx(String hflx) {
		this.hflx = hflx;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}
	public String getSzlcqk() {
		return szlcqk;
	}
	public void setSzlcqk(String szlcqk) {
		this.szlcqk = szlcqk;
	}
	public String getZz() {
		return zz;
	}
	public void setZz(String zz) {
		this.zz = zz;
	}
	public String getLdqk() {
		return ldqk;
	}
	public void setLdqk(String ldqk) {
		this.ldqk = ldqk;
	}
	public String getDqxn() {
		return dqxn;
	}
	public void setDqxn(String dqxn) {
		this.dqxn = dqxn;
	}
	public String getDqxq() {
		return dqxq;
	}
	public void setDqxq(String dqxq) {
		this.dqxq = dqxq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLzm() {
		return lzm;
	}
	public void setLzm(String lzm) {
		this.lzm = lzm;
	}
	public String getQss() {
		return qss;
	}
	public void setQss(String qss) {
		this.qss = qss;
	}
	public String getXss() {
		return xss;
	}
	public void setXss(String xss) {
		this.xss = xss;
	}
	public String getWshgl() {
		return wshgl;
	}
	public void setWshgl(String wshgl) {
		this.wshgl = wshgl;
	}
	public String getWsyxl() {
		return wsyxl;
	}
	public void setWsyxl(String wsyxl) {
		this.wsyxl = wsyxl;
	}
	public String getXjqsl() {
		return xjqsl;
	}
	public void setXjqsl(String xjqsl) {
		this.xjqsl = xjqsl;
	}
	public String getGywmgs() {
		return gywmgs;
	}
	public void setGywmgs(String gywmgs) {
		this.gywmgs = gywmgs;
	}
	public String getFdyyj() {
		return fdyyj;
	}
	public void setFdyyj(String fdyyj) {
		this.fdyyj = fdyyj;
	}
	public String getXyyj() {
		return xyyj;
	}
	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}
	public String getXxyj() {
		return xxyj;
	}
	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXxshsj() {
		return xxshsj;
	}
	public void setXxshsj(String xxshsj) {
		this.xxshsj = xxshsj;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public String getRzrq() {
		return rzrq;
	}
	public void setRzrq(String rzrq) {
		this.rzrq = rzrq;
	}
	public String getDj() {
		return dj;
	}
	public void setDj(String dj) {
		this.dj = dj;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	public String[] getLxids() {
		return lxids;
	}
	public void setLxids(String[] lxids) {
		this.lxids = lxids;
	}
	public String getCheckedValue() {
		return checkedValue;
	}
	public void setCheckedValue(String checkedValue) {
		this.checkedValue = checkedValue;
	}
	public String[] getWsdj() {
		return wsdj;
	}
	public void setWsdj(String[] wsdj) {
		this.wsdj = wsdj;
	}
	public String getJcsj() {
		return jcsj;
	}
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	public String getLzrq() {
		return lzrq;
	}
	public void setLzrq(String lzrq) {
		this.lzrq = lzrq;
	}
	public String getSfzz() {
		return sfzz;
	}
	public void setSfzz(String sfzz) {
		this.sfzz = sfzz;
	}
	public String getQsz() {
		return qsz;
	}
	public void setQsz(String qsz) {
		this.qsz = qsz;
	}
	public String[] getYhfssbf() {
		return yhfssbf;
	}
	public void setYhfssbf(String[] yhfssbf) {
		this.yhfssbf = yhfssbf;
	}
	public String getAscjhy() {
		return ascjhy;
	}
	public void setAscjhy(String ascjhy) {
		this.ascjhy = ascjhy;
	}
	public String getAyqrzgy() {
		return ayqrzgy;
	}
	public void setAyqrzgy(String ayqrzgy) {
		this.ayqrzgy = ayqrzgy;
	}
	public String getBzgxgz() {
		return bzgxgz;
	}
	public void setBzgxgz(String bzgxgz) {
		this.bzgxgz = bzgxgz;
	}
	public String getFdyjzf() {
		return fdyjzf;
	}
	public void setFdyjzf(String fdyjzf) {
		this.fdyjzf = fdyjzf;
	}
	public String getGyhdqk() {
		return gyhdqk;
	}
	public void setGyhdqk(String gyhdqk) {
		this.gyhdqk = gyhdqk;
	}
	public String getJb() {
		return jb;
	}
	public void setJb(String jb) {
		this.jb = jb;
	}
	public String getMywsjc() {
		return mywsjc;
	}
	public void setMywsjc(String mywsjc) {
		this.mywsjc = mywsjc;
	}
	public String getMzzkyclh() {
		return mzzkyclh;
	}
	public void setMzzkyclh(String mzzkyclh) {
		this.mzzkyclh = mzzkyclh;
	}
	public String getSfzxsqsws() {
		return sfzxsqsws;
	}
	public void setSfzxsqsws(String sfzxsqsws) {
		this.sfzxsqsws = sfzxsqsws;
	}
	public String getSjcljsx() {
		return sjcljsx;
	}
	public void setSjcljsx(String sjcljsx) {
		this.sjcljsx = sjcljsx;
	}
	public String getThjl() {
		return thjl;
	}
	public void setThjl(String thjl) {
		this.thjl = thjl;
	}
	public String getXshd() {
		return xshd;
	}
	public void setXshd(String xshd) {
		this.xshd = xshd;
	}
	public String getXsqsdgldq() {
		return xsqsdgldq;
	}
	public void setXsqsdgldq(String xsqsdgldq) {
		this.xsqsdgldq = xsqsdgldq;
	}
	public String getXxbsjs() {
		return xxbsjs;
	}
	public void setXxbsjs(String xxbsjs) {
		this.xxbsjs = xxbsjs;
	}
	public String getZpf() {
		return zpf;
	}
	public void setZpf(String zpf) {
		this.zpf = zpf;
	}
		public String[] getWsdjxz() {
		return wsdjxz;
	}
	public void setWsdjxz(String[] wsdjxz) {
		this.wsdjxz = wsdjxz;
	}
	public String[] getQswsbz() {
		return qswsbz;
	}
	public void setQswsbz(String[] qswsbz) {
		this.qswsbz = qswsbz;
	}
	
}
