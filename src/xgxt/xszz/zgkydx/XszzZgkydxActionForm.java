
package xgxt.xszz.zgkydx;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国矿业大学ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-13</p>
 */
public class XszzZgkydxActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nd;
	private String xh;
	private String xm;
	private String xb;
	private String nj;
	private String xz;
	private String sfzh;
	private String xydm;
	private String xymc;
	private String zydm;
	private String zymc;
	private String bjdm;
	private String bjmc;
	private String ss;
	private String dh;
	private String jtszd;
	private String jtlxdhjyzbm;
	private String szjclxdh;
	private String fqdwlxdh;
	private String mqdwlxdh;
	private String jtcy1_xm;
	private String jtcy1_xb;
	private String jtcy1_gx;
	private String jtcy1_nl;
	private String jtcy1_gzdw;
	private String jtcy1_hklx;
	private String jtcy1_cyzt;
	private String jtcy2_xm;
	private String jtcy2_xb;
	private String jtcy2_gx;
	private String jtcy2_nl;
	private String jtcy2_gzdw;
	private String jtcy2_hklx;
	private String jtcy2_cyzt;
	private String jtcy3_xm;
	private String jtcy3_xb;
	private String jtcy3_gx;
	private String jtcy3_nl;
	private String jtcy3_gzdw;
	private String jtcy3_hklx;
	private String jtcy3_cyzt;
	private String jtcy4_xm;
	private String jtcy4_xb;
	private String jtcy4_gx;
	private String jtcy4_nl;
	private String jtcy4_gzdw;
	private String jtcy4_hklx;
	private String jtcy4_cyzt;
	private String jtcy5_xm;
	private String jtcy5_xb;
	private String jtcy5_gx;
	private String jtcy5_nl;
	private String jtcy5_gzdw;
	private String jtcy5_hklx;
	private String jtcy5_cyzt;
	private String jtcy6_xm;
	private String jtcy6_xb;
	private String jtcy6_gx;
	private String jtcy6_nl;
	private String jtcy6_gzdw;
	private String jtcy6_hklx;
	private String jtcy6_cyzt;
	private String jtcy7_xm;
	private String jtcy7_xb;
	private String jtcy7_gx;
	private String jtcy7_nl;
	private String jtcy7_gzdw;
	private String jtcy7_hklx;
	private String jtcy7_cyzt;
	private String sqly_lzgjjpkxdncdq;
	private String sqly_syxszdshbzdczjt;
	private String sqly_gehjjknddqjt;
	private String sqly_fmyfhsfxg;
	private String sqly_jtcywqzndl;
	private String sqly_jtcyycjhjbrssndl;
	private String sqly_jtcyyzdjbxzfdefy;
	private String sqly_jtcyzylghyscyzjsfywjy;
	private String sqly_jtcyzszrzh;
	private String sqly_qtqk;
	private String zmcl1_mc;
	private String zmcl1_jg;
	private String zmcl1_dh;
	private String zmcl2_mc;
	private String zmcl2_jg;
	private String zmcl2_dh;
	private String zmcl3_mc;
	private String zmcl3_jg;
	private String zmcl3_dh;
	private String zmcl4_mc;
	private String zmcl4_jg;
	private String zmcl4_dh;
	private String sqsj;
	private String pyrs;
	private String adrs;
	private String bdrs;
	private String cdrs;
	private String btyrs;
	private String csbyjdrs;
	private String byjdjtqk;
	private String dcfyqk;
	private String bjpyjg;
	private String xbshjg;
	private String xbshsj;
	private String xxshjg;
	private String xxshsj;
	private String knpd;//困难判定结果
	private String tsrqdm;
	private String zzxmdm;
	
	Pages pages = new Pages();

	public Pages getPages() {
		return pages;
	}
	
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	public String getTsrqdm() {
		return tsrqdm;
	}
	public void setTsrqdm(String tsrqdm) {
		this.tsrqdm = tsrqdm;
	}
	public String getKnpd() {
		return knpd;
	}
	public void setKnpd(String knpd) {
		this.knpd = knpd;
	}
	public String getAdrs() {
		return adrs;
	}
	public void setAdrs(String adrs) {
		this.adrs = adrs;
	}
	public String getBdrs() {
		return bdrs;
	}
	public void setBdrs(String bdrs) {
		this.bdrs = bdrs;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getBjpyjg() {
		return bjpyjg;
	}
	public void setBjpyjg(String bjpyjg) {
		this.bjpyjg = bjpyjg;
	}
	public String getBtyrs() {
		return btyrs;
	}
	public void setBtyrs(String btyrs) {
		this.btyrs = btyrs;
	}
	public String getByjdjtqk() {
		return byjdjtqk;
	}
	public void setByjdjtqk(String byjdjtqk) {
		this.byjdjtqk = byjdjtqk;
	}
	public String getCdrs() {
		return cdrs;
	}
	public void setCdrs(String cdrs) {
		this.cdrs = cdrs;
	}
	public String getCsbyjdrs() {
		return csbyjdrs;
	}
	public void setCsbyjdrs(String csbyjdrs) {
		this.csbyjdrs = csbyjdrs;
	}
	public String getDcfyqk() {
		return dcfyqk;
	}
	public void setDcfyqk(String dcfyqk) {
		this.dcfyqk = dcfyqk;
	}
	public String getDh() {
		return dh;
	}
	public void setDh(String dh) {
		this.dh = dh;
	}
	public String getFqdwlxdh() {
		return fqdwlxdh;
	}
	public void setFqdwlxdh(String fqdwlxdh) {
		this.fqdwlxdh = fqdwlxdh;
	}
	public String getJtcy1_cyzt() {
		return jtcy1_cyzt;
	}
	public void setJtcy1_cyzt(String jtcy1_cyzt) {
		this.jtcy1_cyzt = jtcy1_cyzt;
	}
	public String getJtcy1_gx() {
		return jtcy1_gx;
	}
	public void setJtcy1_gx(String jtcy1_gx) {
		this.jtcy1_gx = jtcy1_gx;
	}
	public String getJtcy1_gzdw() {
		return jtcy1_gzdw;
	}
	public void setJtcy1_gzdw(String jtcy1_gzdw) {
		this.jtcy1_gzdw = jtcy1_gzdw;
	}
	public String getJtcy1_hklx() {
		return jtcy1_hklx;
	}
	public void setJtcy1_hklx(String jtcy1_hklx) {
		this.jtcy1_hklx = jtcy1_hklx;
	}
	public String getJtcy1_nl() {
		return jtcy1_nl;
	}
	public void setJtcy1_nl(String jtcy1_nl) {
		this.jtcy1_nl = jtcy1_nl;
	}
	public String getJtcy1_xb() {
		return jtcy1_xb;
	}
	public void setJtcy1_xb(String jtcy1_xb) {
		this.jtcy1_xb = jtcy1_xb;
	}
	public String getJtcy1_xm() {
		return jtcy1_xm;
	}
	public void setJtcy1_xm(String jtcy1_xm) {
		this.jtcy1_xm = jtcy1_xm;
	}
	public String getJtcy2_cyzt() {
		return jtcy2_cyzt;
	}
	public void setJtcy2_cyzt(String jtcy2_cyzt) {
		this.jtcy2_cyzt = jtcy2_cyzt;
	}
	public String getJtcy2_gx() {
		return jtcy2_gx;
	}
	public void setJtcy2_gx(String jtcy2_gx) {
		this.jtcy2_gx = jtcy2_gx;
	}
	public String getJtcy2_gzdw() {
		return jtcy2_gzdw;
	}
	public void setJtcy2_gzdw(String jtcy2_gzdw) {
		this.jtcy2_gzdw = jtcy2_gzdw;
	}
	public String getJtcy2_hklx() {
		return jtcy2_hklx;
	}
	public void setJtcy2_hklx(String jtcy2_hklx) {
		this.jtcy2_hklx = jtcy2_hklx;
	}
	public String getJtcy2_nl() {
		return jtcy2_nl;
	}
	public void setJtcy2_nl(String jtcy2_nl) {
		this.jtcy2_nl = jtcy2_nl;
	}
	public String getJtcy2_xb() {
		return jtcy2_xb;
	}
	public void setJtcy2_xb(String jtcy2_xb) {
		this.jtcy2_xb = jtcy2_xb;
	}
	public String getJtcy2_xm() {
		return jtcy2_xm;
	}
	public void setJtcy2_xm(String jtcy2_xm) {
		this.jtcy2_xm = jtcy2_xm;
	}
	public String getJtcy3_cyzt() {
		return jtcy3_cyzt;
	}
	public void setJtcy3_cyzt(String jtcy3_cyzt) {
		this.jtcy3_cyzt = jtcy3_cyzt;
	}
	public String getJtcy3_gx() {
		return jtcy3_gx;
	}
	public void setJtcy3_gx(String jtcy3_gx) {
		this.jtcy3_gx = jtcy3_gx;
	}
	public String getJtcy3_gzdw() {
		return jtcy3_gzdw;
	}
	public void setJtcy3_gzdw(String jtcy3_gzdw) {
		this.jtcy3_gzdw = jtcy3_gzdw;
	}
	public String getJtcy3_hklx() {
		return jtcy3_hklx;
	}
	public void setJtcy3_hklx(String jtcy3_hklx) {
		this.jtcy3_hklx = jtcy3_hklx;
	}
	public String getJtcy3_nl() {
		return jtcy3_nl;
	}
	public void setJtcy3_nl(String jtcy3_nl) {
		this.jtcy3_nl = jtcy3_nl;
	}
	public String getJtcy3_xb() {
		return jtcy3_xb;
	}
	public void setJtcy3_xb(String jtcy3_xb) {
		this.jtcy3_xb = jtcy3_xb;
	}
	public String getJtcy3_xm() {
		return jtcy3_xm;
	}
	public void setJtcy3_xm(String jtcy3_xm) {
		this.jtcy3_xm = jtcy3_xm;
	}
	public String getJtcy4_cyzt() {
		return jtcy4_cyzt;
	}
	public void setJtcy4_cyzt(String jtcy4_cyzt) {
		this.jtcy4_cyzt = jtcy4_cyzt;
	}
	public String getJtcy4_gx() {
		return jtcy4_gx;
	}
	public void setJtcy4_gx(String jtcy4_gx) {
		this.jtcy4_gx = jtcy4_gx;
	}
	public String getJtcy4_gzdw() {
		return jtcy4_gzdw;
	}
	public void setJtcy4_gzdw(String jtcy4_gzdw) {
		this.jtcy4_gzdw = jtcy4_gzdw;
	}
	public String getJtcy4_hklx() {
		return jtcy4_hklx;
	}
	public void setJtcy4_hklx(String jtcy4_hklx) {
		this.jtcy4_hklx = jtcy4_hklx;
	}
	public String getJtcy4_nl() {
		return jtcy4_nl;
	}
	public void setJtcy4_nl(String jtcy4_nl) {
		this.jtcy4_nl = jtcy4_nl;
	}
	public String getJtcy4_xb() {
		return jtcy4_xb;
	}
	public void setJtcy4_xb(String jtcy4_xb) {
		this.jtcy4_xb = jtcy4_xb;
	}
	public String getJtcy4_xm() {
		return jtcy4_xm;
	}
	public void setJtcy4_xm(String jtcy4_xm) {
		this.jtcy4_xm = jtcy4_xm;
	}
	public String getJtcy5_cyzt() {
		return jtcy5_cyzt;
	}
	public void setJtcy5_cyzt(String jtcy5_cyzt) {
		this.jtcy5_cyzt = jtcy5_cyzt;
	}
	public String getJtcy5_gx() {
		return jtcy5_gx;
	}
	public void setJtcy5_gx(String jtcy5_gx) {
		this.jtcy5_gx = jtcy5_gx;
	}
	public String getJtcy5_gzdw() {
		return jtcy5_gzdw;
	}
	public void setJtcy5_gzdw(String jtcy5_gzdw) {
		this.jtcy5_gzdw = jtcy5_gzdw;
	}
	public String getJtcy5_hklx() {
		return jtcy5_hklx;
	}
	public void setJtcy5_hklx(String jtcy5_hklx) {
		this.jtcy5_hklx = jtcy5_hklx;
	}
	public String getJtcy5_nl() {
		return jtcy5_nl;
	}
	public void setJtcy5_nl(String jtcy5_nl) {
		this.jtcy5_nl = jtcy5_nl;
	}
	public String getJtcy5_xb() {
		return jtcy5_xb;
	}
	public void setJtcy5_xb(String jtcy5_xb) {
		this.jtcy5_xb = jtcy5_xb;
	}
	public String getJtcy5_xm() {
		return jtcy5_xm;
	}
	public void setJtcy5_xm(String jtcy5_xm) {
		this.jtcy5_xm = jtcy5_xm;
	}
	public String getJtcy6_cyzt() {
		return jtcy6_cyzt;
	}
	public void setJtcy6_cyzt(String jtcy6_cyzt) {
		this.jtcy6_cyzt = jtcy6_cyzt;
	}
	public String getJtcy6_gx() {
		return jtcy6_gx;
	}
	public void setJtcy6_gx(String jtcy6_gx) {
		this.jtcy6_gx = jtcy6_gx;
	}
	public String getJtcy6_gzdw() {
		return jtcy6_gzdw;
	}
	public void setJtcy6_gzdw(String jtcy6_gzdw) {
		this.jtcy6_gzdw = jtcy6_gzdw;
	}
	public String getJtcy6_hklx() {
		return jtcy6_hklx;
	}
	public void setJtcy6_hklx(String jtcy6_hklx) {
		this.jtcy6_hklx = jtcy6_hklx;
	}
	public String getJtcy6_nl() {
		return jtcy6_nl;
	}
	public void setJtcy6_nl(String jtcy6_nl) {
		this.jtcy6_nl = jtcy6_nl;
	}
	public String getJtcy6_xb() {
		return jtcy6_xb;
	}
	public void setJtcy6_xb(String jtcy6_xb) {
		this.jtcy6_xb = jtcy6_xb;
	}
	public String getJtcy6_xm() {
		return jtcy6_xm;
	}
	public void setJtcy6_xm(String jtcy6_xm) {
		this.jtcy6_xm = jtcy6_xm;
	}
	public String getJtcy7_cyzt() {
		return jtcy7_cyzt;
	}
	public void setJtcy7_cyzt(String jtcy7_cyzt) {
		this.jtcy7_cyzt = jtcy7_cyzt;
	}
	public String getJtcy7_gx() {
		return jtcy7_gx;
	}
	public void setJtcy7_gx(String jtcy7_gx) {
		this.jtcy7_gx = jtcy7_gx;
	}
	public String getJtcy7_gzdw() {
		return jtcy7_gzdw;
	}
	public void setJtcy7_gzdw(String jtcy7_gzdw) {
		this.jtcy7_gzdw = jtcy7_gzdw;
	}
	public String getJtcy7_hklx() {
		return jtcy7_hklx;
	}
	public void setJtcy7_hklx(String jtcy7_hklx) {
		this.jtcy7_hklx = jtcy7_hklx;
	}
	public String getJtcy7_nl() {
		return jtcy7_nl;
	}
	public void setJtcy7_nl(String jtcy7_nl) {
		this.jtcy7_nl = jtcy7_nl;
	}
	public String getJtcy7_xb() {
		return jtcy7_xb;
	}
	public void setJtcy7_xb(String jtcy7_xb) {
		this.jtcy7_xb = jtcy7_xb;
	}
	public String getJtcy7_xm() {
		return jtcy7_xm;
	}
	public void setJtcy7_xm(String jtcy7_xm) {
		this.jtcy7_xm = jtcy7_xm;
	}
	public String getJtlxdhjyzbm() {
		return jtlxdhjyzbm;
	}
	public void setJtlxdhjyzbm(String jtlxdhjyzbm) {
		this.jtlxdhjyzbm = jtlxdhjyzbm;
	}
	public String getJtszd() {
		return jtszd;
	}
	public void setJtszd(String jtszd) {
		this.jtszd = jtszd;
	}
	public String getMqdwlxdh() {
		return mqdwlxdh;
	}
	public void setMqdwlxdh(String mqdwlxdh) {
		this.mqdwlxdh = mqdwlxdh;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getPyrs() {
		return pyrs;
	}
	public void setPyrs(String pyrs) {
		this.pyrs = pyrs;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getSqly_fmyfhsfxg() {
		return sqly_fmyfhsfxg;
	}
	public void setSqly_fmyfhsfxg(String sqly_fmyfhsfxg) {
		this.sqly_fmyfhsfxg = sqly_fmyfhsfxg;
	}
	public String getSqly_gehjjknddqjt() {
		return sqly_gehjjknddqjt;
	}
	public void setSqly_gehjjknddqjt(String sqly_gehjjknddqjt) {
		this.sqly_gehjjknddqjt = sqly_gehjjknddqjt;
	}
	public String getSqly_jtcywqzndl() {
		return sqly_jtcywqzndl;
	}
	public void setSqly_jtcywqzndl(String sqly_jtcywqzndl) {
		this.sqly_jtcywqzndl = sqly_jtcywqzndl;
	}
	public String getSqly_jtcyycjhjbrssndl() {
		return sqly_jtcyycjhjbrssndl;
	}
	public void setSqly_jtcyycjhjbrssndl(String sqly_jtcyycjhjbrssndl) {
		this.sqly_jtcyycjhjbrssndl = sqly_jtcyycjhjbrssndl;
	}
	public String getSqly_jtcyyzdjbxzfdefy() {
		return sqly_jtcyyzdjbxzfdefy;
	}
	public void setSqly_jtcyyzdjbxzfdefy(String sqly_jtcyyzdjbxzfdefy) {
		this.sqly_jtcyyzdjbxzfdefy = sqly_jtcyyzdjbxzfdefy;
	}
	public String getSqly_jtcyzszrzh() {
		return sqly_jtcyzszrzh;
	}
	public void setSqly_jtcyzszrzh(String sqly_jtcyzszrzh) {
		this.sqly_jtcyzszrzh = sqly_jtcyzszrzh;
	}
	public String getSqly_jtcyzylghyscyzjsfywjy() {
		return sqly_jtcyzylghyscyzjsfywjy;
	}
	public void setSqly_jtcyzylghyscyzjsfywjy(String sqly_jtcyzylghyscyzjsfywjy) {
		this.sqly_jtcyzylghyscyzjsfywjy = sqly_jtcyzylghyscyzjsfywjy;
	}
	public String getSqly_lzgjjpkxdncdq() {
		return sqly_lzgjjpkxdncdq;
	}
	public void setSqly_lzgjjpkxdncdq(String sqly_lzgjjpkxdncdq) {
		this.sqly_lzgjjpkxdncdq = sqly_lzgjjpkxdncdq;
	}
	public String getSqly_qtqk() {
		return sqly_qtqk;
	}
	public void setSqly_qtqk(String sqly_qtqk) {
		this.sqly_qtqk = sqly_qtqk;
	}
	public String getSqly_syxszdshbzdczjt() {
		return sqly_syxszdshbzdczjt;
	}
	public void setSqly_syxszdshbzdczjt(String sqly_syxszdshbzdczjt) {
		this.sqly_syxszdshbzdczjt = sqly_syxszdshbzdczjt;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getSs() {
		return ss;
	}
	public void setSs(String ss) {
		this.ss = ss;
	}
	public String getSzjclxdh() {
		return szjclxdh;
	}
	public void setSzjclxdh(String szjclxdh) {
		this.szjclxdh = szjclxdh;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXbshjg() {
		return xbshjg;
	}
	public void setXbshjg(String xbshjg) {
		this.xbshjg = xbshjg;
	}
	public String getXbshsj() {
		return xbshsj;
	}
	public void setXbshsj(String xbshsj) {
		this.xbshsj = xbshsj;
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
	public String getXxshjg() {
		return xxshjg;
	}
	public void setXxshjg(String xxshjg) {
		this.xxshjg = xxshjg;
	}
	public String getXxshsj() {
		return xxshsj;
	}
	public void setXxshsj(String xxshsj) {
		this.xxshsj = xxshsj;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
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
	public String getZmcl1_dh() {
		return zmcl1_dh;
	}
	public void setZmcl1_dh(String zmcl1_dh) {
		this.zmcl1_dh = zmcl1_dh;
	}
	public String getZmcl1_jg() {
		return zmcl1_jg;
	}
	public void setZmcl1_jg(String zmcl1_jg) {
		this.zmcl1_jg = zmcl1_jg;
	}
	public String getZmcl1_mc() {
		return zmcl1_mc;
	}
	public void setZmcl1_mc(String zmcl1_mc) {
		this.zmcl1_mc = zmcl1_mc;
	}
	public String getZmcl2_dh() {
		return zmcl2_dh;
	}
	public void setZmcl2_dh(String zmcl2_dh) {
		this.zmcl2_dh = zmcl2_dh;
	}
	public String getZmcl2_jg() {
		return zmcl2_jg;
	}
	public void setZmcl2_jg(String zmcl2_jg) {
		this.zmcl2_jg = zmcl2_jg;
	}
	public String getZmcl2_mc() {
		return zmcl2_mc;
	}
	public void setZmcl2_mc(String zmcl2_mc) {
		this.zmcl2_mc = zmcl2_mc;
	}
	public String getZmcl3_dh() {
		return zmcl3_dh;
	}
	public void setZmcl3_dh(String zmcl3_dh) {
		this.zmcl3_dh = zmcl3_dh;
	}
	public String getZmcl3_jg() {
		return zmcl3_jg;
	}
	public void setZmcl3_jg(String zmcl3_jg) {
		this.zmcl3_jg = zmcl3_jg;
	}
	public String getZmcl3_mc() {
		return zmcl3_mc;
	}
	public void setZmcl3_mc(String zmcl3_mc) {
		this.zmcl3_mc = zmcl3_mc;
	}
	public String getZmcl4_dh() {
		return zmcl4_dh;
	}
	public void setZmcl4_dh(String zmcl4_dh) {
		this.zmcl4_dh = zmcl4_dh;
	}
	public String getZmcl4_jg() {
		return zmcl4_jg;
	}
	public void setZmcl4_jg(String zmcl4_jg) {
		this.zmcl4_jg = zmcl4_jg;
	}
	public String getZmcl4_mc() {
		return zmcl4_mc;
	}
	public void setZmcl4_mc(String zmcl4_mc) {
		this.zmcl4_mc = zmcl4_mc;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getZzxmdm() {
		return zzxmdm;
	}
	public void setZzxmdm(String zzxmdm) {
		this.zzxmdm = zzxmdm;
	}
}
