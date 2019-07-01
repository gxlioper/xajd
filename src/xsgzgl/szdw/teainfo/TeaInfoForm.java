package xsgzgl.szdw.teainfo;

import org.apache.struts.upload.FormFile;

import xgxt.comm.CommForm;

public class TeaInfoForm extends CommForm {

	private static final long serialVersionUID = 1L;
	
	private String xb; 
	private String xl; 
	private String mz; 
	private String dzyx; 
	private String byyx; 
	private String sxzy; 
	private String jsrzsj; 
	private String djsj; 
	private String szgzsj; 
	private String ssbyzy; 
	private String cjgzrq; 
	private String zdzy; 
	private String gdxlzl; 
	private String gzjl; 
	private String jg; 
	private String kyjl; 
	private String sg; 
	private String bksxzy; 
	private String sfzh; 
	private String zgh; 
	private String xw; 
	private String csfdysj; 
	private String bgdh; 
	private String cz; 
	private String tc; 
	private String bsbyyx; 
	private String sfbl; 
	private String csgz; 
	private String zyyjjg; 
	private String lxdh; 
	private String pxqk; 
	private String bmdm; 
	private String zw; 
	private String csrq; 
	private String lxgzsj; 
	private String gzfg; 
	private String zjz; 
	private String zwrzsj; 
	private String txdz; 
	private String jrgz; 
	private String jb; 
	private String ssbyyx; 
	private String bsbyzy; 
	private String zhxwssxk; 
	private String bgdd; 
	private String xm; 
	private String zzmm; 
	private String grhjqk; 
	private String bz; 
	private String zc; 
	private String zdxl; 
	private String qtfgnj; 
	private String fglxdm; 
	private String dwlbdm; 
	private String zyzz; 
	private String xsgzyjfx; 
	private String yddh; 
	private String yzbm; 
	private String rwsj; 
	private String tz; 
	private String bkbyyx; 
	private String fgnj; 
	private String jtzz; 
	private String fblw; 
	private String sjdw; 
	private String jgxs; 
	private String fdyz;
	private String kzzd1;
	private String kzzd2;
	private String kzzd3;
	private String kzzd4;
	private String kzzd5;
	private String[] colList;
	private String doType;
	private String zdm;
	private String dwdm;
	private String sfjryx;
	private String kzzd6;     //À©Õ¹×Ö¶Î6
	private String kzzd7;     //À©Õ¹×Ö¶Î7
	private String kzzd10;     //À©Õ¹×Ö¶Î10
	private String kzzd11;     //À©Õ¹×Ö¶Î11
	private String kzzd13;     //À©Õ¹×Ö¶Î13
	private String kzzd14;     //À©Õ¹×Ö¶Î14
	private String kzzd8;     //À©Õ¹×Ö¶Î8
	private String kzzd12;     //À©Õ¹×Ö¶Î12
	private String kzzd20;     //À©Õ¹×Ö¶Î20
	private String kzzd19;     //À©Õ¹×Ö¶Î19
	private String kzzd9;     //À©Õ¹×Ö¶Î9
	private String kzzd16;     //À©Õ¹×Ö¶Î16
	private String kzzd18;     //À©Õ¹×Ö¶Î18
	private String kzzd17;     //À©Õ¹×Ö¶Î17
	private String kzzd15;     //À©Õ¹×Ö¶Î15
	
	private FormFile teaPic;
	
	public String getBgdd() {
		return bgdd;
	}
	public void setBgdd(String bgdd) {
		this.bgdd = bgdd;
	}
	public String getBgdh() {
		return bgdh;
	}
	public void setBgdh(String bgdh) {
		this.bgdh = bgdh;
	}
	public String getBkbyyx() {
		return bkbyyx;
	}
	public void setBkbyyx(String bkbyyx) {
		this.bkbyyx = bkbyyx;
	}
	public String getBksxzy() {
		return bksxzy;
	}
	public void setBksxzy(String bksxzy) {
		this.bksxzy = bksxzy;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getBsbyyx() {
		return bsbyyx;
	}
	public void setBsbyyx(String bsbyyx) {
		this.bsbyyx = bsbyyx;
	}
	public String getBsbyzy() {
		return bsbyzy;
	}
	public void setBsbyzy(String bsbyzy) {
		this.bsbyzy = bsbyzy;
	}
	public String getByyx() {
		return byyx;
	}
	public void setByyx(String byyx) {
		this.byyx = byyx;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCjgzrq() {
		return cjgzrq;
	}
	public void setCjgzrq(String cjgzrq) {
		this.cjgzrq = cjgzrq;
	}
	public String getCsfdysj() {
		return csfdysj;
	}
	public void setCsfdysj(String csfdysj) {
		this.csfdysj = csfdysj;
	}
	public String getCsgz() {
		return csgz;
	}
	public void setCsgz(String csgz) {
		this.csgz = csgz;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getCz() {
		return cz;
	}
	public void setCz(String cz) {
		this.cz = cz;
	}
	public String getDjsj() {
		return djsj;
	}
	public void setDjsj(String djsj) {
		this.djsj = djsj;
	}
	public String getDwlbdm() {
		return dwlbdm;
	}
	public void setDwlbdm(String dwlbdm) {
		this.dwlbdm = dwlbdm;
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	public String getFblw() {
		return fblw;
	}
	public void setFblw(String fblw) {
		this.fblw = fblw;
	}
	public String getFdyz() {
		return fdyz;
	}
	public void setFdyz(String fdyz) {
		this.fdyz = fdyz;
	}
	public String getFglxdm() {
		return fglxdm;
	}
	public void setFglxdm(String fglxdm) {
		this.fglxdm = fglxdm;
	}
	public String getFgnj() {
		return fgnj;
	}
	public void setFgnj(String fgnj) {
		this.fgnj = fgnj;
	}
	public String getGdxlzl() {
		return gdxlzl;
	}
	public void setGdxlzl(String gdxlzl) {
		this.gdxlzl = gdxlzl;
	}
	public String getGrhjqk() {
		return grhjqk;
	}
	public void setGrhjqk(String grhjqk) {
		this.grhjqk = grhjqk;
	}
	public String getGzfg() {
		return gzfg;
	}
	public void setGzfg(String gzfg) {
		this.gzfg = gzfg;
	}
	public String getGzjl() {
		return gzjl;
	}
	public void setGzjl(String gzjl) {
		this.gzjl = gzjl;
	}
	public String getJb() {
		return jb;
	}
	public void setJb(String jb) {
		this.jb = jb;
	}
	public String getJg() {
		return jg;
	}
	public void setJg(String jg) {
		this.jg = jg;
	}
	public String getJgxs() {
		return jgxs;
	}
	public void setJgxs(String jgxs) {
		this.jgxs = jgxs;
	}
	public String getJrgz() {
		return jrgz;
	}
	public void setJrgz(String jrgz) {
		this.jrgz = jrgz;
	}
	public String getJsrzsj() {
		return jsrzsj;
	}
	public void setJsrzsj(String jsrzsj) {
		this.jsrzsj = jsrzsj;
	}
	public String getJtzz() {
		return jtzz;
	}
	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}
	public String getKyjl() {
		return kyjl;
	}
	public void setKyjl(String kyjl) {
		this.kyjl = kyjl;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getLxgzsj() {
		return lxgzsj;
	}
	public void setLxgzsj(String lxgzsj) {
		this.lxgzsj = lxgzsj;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getPxqk() {
		return pxqk;
	}
	public void setPxqk(String pxqk) {
		this.pxqk = pxqk;
	}
	public String getQtfgnj() {
		return qtfgnj;
	}
	public void setQtfgnj(String qtfgnj) {
		this.qtfgnj = qtfgnj;
	}
	public String getRwsj() {
		return rwsj;
	}
	public void setRwsj(String rwsj) {
		this.rwsj = rwsj;
	}
	public String getSfbl() {
		return sfbl;
	}
	public void setSfbl(String sfbl) {
		this.sfbl = sfbl;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getSg() {
		return sg;
	}
	public void setSg(String sg) {
		this.sg = sg;
	}
	public String getSjdw() {
		return sjdw;
	}
	public void setSjdw(String sjdw) {
		this.sjdw = sjdw;
	}
	public String getSsbyyx() {
		return ssbyyx;
	}
	public void setSsbyyx(String ssbyyx) {
		this.ssbyyx = ssbyyx;
	}
	public String getSsbyzy() {
		return ssbyzy;
	}
	public void setSsbyzy(String ssbyzy) {
		this.ssbyzy = ssbyzy;
	}
	public String getSxzy() {
		return sxzy;
	}
	public void setSxzy(String sxzy) {
		this.sxzy = sxzy;
	}
	public String getSzgzsj() {
		return szgzsj;
	}
	public void setSzgzsj(String szgzsj) {
		this.szgzsj = szgzsj;
	}
	public String getTc() {
		return tc;
	}
	public void setTc(String tc) {
		this.tc = tc;
	}
	public String getTxdz() {
		return txdz;
	}
	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}
	public String getTz() {
		return tz;
	}
	public void setTz(String tz) {
		this.tz = tz;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXl() {
		return xl;
	}
	public void setXl(String xl) {
		this.xl = xl;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXsgzyjfx() {
		return xsgzyjfx;
	}
	public void setXsgzyjfx(String xsgzyjfx) {
		this.xsgzyjfx = xsgzyjfx;
	}
	public String getXw() {
		return xw;
	}
	public void setXw(String xw) {
		this.xw = xw;
	}
	public String getYddh() {
		return yddh;
	}
	public void setYddh(String yddh) {
		this.yddh = yddh;
	}
	public String getYzbm() {
		return yzbm;
	}
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
	}
	public String getZdxl() {
		return zdxl;
	}
	public void setZdxl(String zdxl) {
		this.zdxl = zdxl;
	}
	public String getZdzy() {
		return zdzy;
	}
	public void setZdzy(String zdzy) {
		this.zdzy = zdzy;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getZhxwssxk() {
		return zhxwssxk;
	}
	public void setZhxwssxk(String zhxwssxk) {
		this.zhxwssxk = zhxwssxk;
	}
	public String getZjz() {
		return zjz;
	}
	public void setZjz(String zjz) {
		this.zjz = zjz;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public String getZwrzsj() {
		return zwrzsj;
	}
	public void setZwrzsj(String zwrzsj) {
		this.zwrzsj = zwrzsj;
	}
	public String getZyyjjg() {
		return zyyjjg;
	}
	public void setZyyjjg(String zyyjjg) {
		this.zyyjjg = zyyjjg;
	}
	public String getZyzz() {
		return zyzz;
	}
	public void setZyzz(String zyzz) {
		this.zyzz = zyzz;
	}
	public String getZzmm() {
		return zzmm;
	}
	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}
	public FormFile getTeaPic() {
		return teaPic;
	}
	public void setTeaPic(FormFile teaPic) {
		this.teaPic = teaPic;
	}
	public String getKzzd1() {
		return kzzd1;
	}
	public void setKzzd1(String kzzd1) {
		this.kzzd1 = kzzd1;
	}
	public String getKzzd2() {
		return kzzd2;
	}
	public void setKzzd2(String kzzd2) {
		this.kzzd2 = kzzd2;
	}
	public String getKzzd3() {
		return kzzd3;
	}
	public void setKzzd3(String kzzd3) {
		this.kzzd3 = kzzd3;
	}
	public String getKzzd4() {
		return kzzd4;
	}
	public void setKzzd4(String kzzd4) {
		this.kzzd4 = kzzd4;
	}
	public String getKzzd5() {
		return kzzd5;
	}
	public void setKzzd5(String kzzd5) {
		this.kzzd5 = kzzd5;
	}
	public String[] getColList() {
		return colList;
	}
	public void setColList(String[] colList) {
		this.colList = colList;
	}
	public String getDoType() {
		return doType;
	}
	public void setDoType(String doType) {
		this.doType = doType;
	}
	public String getDwdm() {
		return dwdm;
	}
	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}
	public String getZdm() {
		return zdm;
	}
	public void setZdm(String zdm) {
		this.zdm = zdm;
	}
	public String getSfjryx() {
		return sfjryx;
	}
	public void setSfjryx(String sfjryx) {
		this.sfjryx = sfjryx;
	}
	public String getKzzd10() {
		return kzzd10;
	}
	public void setKzzd10(String kzzd10) {
		this.kzzd10 = kzzd10;
	}
	public String getKzzd11() {
		return kzzd11;
	}
	public void setKzzd11(String kzzd11) {
		this.kzzd11 = kzzd11;
	}
	public String getKzzd12() {
		return kzzd12;
	}
	public void setKzzd12(String kzzd12) {
		this.kzzd12 = kzzd12;
	}
	public String getKzzd13() {
		return kzzd13;
	}
	public void setKzzd13(String kzzd13) {
		this.kzzd13 = kzzd13;
	}
	public String getKzzd14() {
		return kzzd14;
	}
	public void setKzzd14(String kzzd14) {
		this.kzzd14 = kzzd14;
	}
	public String getKzzd15() {
		return kzzd15;
	}
	public void setKzzd15(String kzzd15) {
		this.kzzd15 = kzzd15;
	}
	public String getKzzd16() {
		return kzzd16;
	}
	public void setKzzd16(String kzzd16) {
		this.kzzd16 = kzzd16;
	}
	public String getKzzd17() {
		return kzzd17;
	}
	public void setKzzd17(String kzzd17) {
		this.kzzd17 = kzzd17;
	}
	public String getKzzd18() {
		return kzzd18;
	}
	public void setKzzd18(String kzzd18) {
		this.kzzd18 = kzzd18;
	}
	public String getKzzd19() {
		return kzzd19;
	}
	public void setKzzd19(String kzzd19) {
		this.kzzd19 = kzzd19;
	}
	public String getKzzd20() {
		return kzzd20;
	}
	public void setKzzd20(String kzzd20) {
		this.kzzd20 = kzzd20;
	}
	public String getKzzd6() {
		return kzzd6;
	}
	public void setKzzd6(String kzzd6) {
		this.kzzd6 = kzzd6;
	}
	public String getKzzd7() {
		return kzzd7;
	}
	public void setKzzd7(String kzzd7) {
		this.kzzd7 = kzzd7;
	}
	public String getKzzd8() {
		return kzzd8;
	}
	public void setKzzd8(String kzzd8) {
		this.kzzd8 = kzzd8;
	}
	public String getKzzd9() {
		return kzzd9;
	}
	public void setKzzd9(String kzzd9) {
		this.kzzd9 = kzzd9;
	}


}
