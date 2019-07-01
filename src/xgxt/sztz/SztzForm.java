package xgxt.sztz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class SztzForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private Pages pages = new Pages();
	
	private String xxdm;
	
	private String sbkssj;
	
	private String sbjssj;
	
	private String rssx;
	
	private String doType;
	
	private String kmdm;
	
	private String kmmc;
	
	private String[] hxnldm;
	
	private String[] hxnlmc;
	
	private String[] minfs;
	
	private String[] maxfs;
	
	private String querylike_kmdm;
	
	private String querylike_kmmc;
	
	private String querylike_nj;
	
	private String id;
	
	private String xn;
	
	private String xq;
	
	private String hxnl;
	
	private String xmlx;
	
	private String xmmc;
	
	private String shlcid;
	
	private String jcf;
	
	private String sqbm;
	
	private String jbkssj;
	
	private String jbjssj;
	
	private String zbf;
	
	private String xs;
	
	private String sbr;
	
	private String fzr;
	
	private String bz;
	
	private String[] jxdm;
	
	private String[] jxmc;
	
	private String[] jxfs;
	
	private String queryequals_xn;
	
	private String queryequals_xq;
	
	private String queryequals_kmdm;
	
	private String queryequals_hxnl;
	
	private String queryequals_xmlx;
	
	private String queryequals_nj;
	
	private String queryequals_xydm;
	
	private String querylike_xmmc;
	
	private String querygreaterequal_jbkssj;
	
	private String querylessequal_jbjssj;
	
	private String xh;
	
	private String xm;
	
	private String xmid;
	
	private String cyjs;
	
	private String sfcx;
	
	private String cgms;
	
	private String shzt;
	
	private String sftj;
	
	private String querylike_xh;
	
	private String[] primarykey_cbv;
	
	private String shyj;
	
	private String shjg;
	
	private String thjsr;
	
	private String shjx;
	
	private String sdxf;
	
	private String nj;
	
	private String zydm;
	
	private String xydm;
	
	private String bjdm;
	
	private String moreTerm;

	public String getMoreTerm() {
		return moreTerm;
	}

	public void setMoreTerm(String moreTerm) {
		this.moreTerm = moreTerm;
	}

	public String getSdxf() {
		return sdxf;
	}

	public void setSdxf(String sdxf) {
		this.sdxf = sdxf;
	}

	public String getShjx() {
		return shjx;
	}

	public void setShjx(String shjx) {
		this.shjx = shjx;
	}

	public String getThjsr() {
		return thjsr;
	}

	public void setThjsr(String thjsr) {
		this.thjsr = thjsr;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String[] getPrimarykey_cbv() {
		return primarykey_cbv;
	}

	public void setPrimarykey_cbv(String[] primarykey_cbv) {
		this.primarykey_cbv = primarykey_cbv;
	}

	public String getQuerylike_xh() {
		return querylike_xh;
	}

	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}

	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}

	public void setQueryequals_xydm(String queryequalsXydm) {
		queryequals_xydm = queryequalsXydm;
	}

	public String getQuerygreaterequal_jbkssj() {
		return querygreaterequal_jbkssj;
	}

	public void setQuerygreaterequal_jbkssj(String querygreaterequalJbkssj) {
		querygreaterequal_jbkssj = querygreaterequalJbkssj;
	}

	public String getQuerylessequal_jbjssj() {
		return querylessequal_jbjssj;
	}

	public void setQuerylessequal_jbjssj(String querylessequalJbjssj) {
		querylessequal_jbjssj = querylessequalJbjssj;
	}

	public String getSftj() {
		return sftj;
	}

	public void setSftj(String sftj) {
		this.sftj = sftj;
	}

	public String getQueryequals_hxnl() {
		return queryequals_hxnl;
	}

	public void setQueryequals_hxnl(String queryequals_hxnl) {
		this.queryequals_hxnl = queryequals_hxnl;
	}

	public String getQueryequals_kmdm() {
		return queryequals_kmdm;
	}

	public void setQueryequals_kmdm(String queryequals_kmdm) {
		this.queryequals_kmdm = queryequals_kmdm;
	}

	public String getQueryequals_nj() {
		return queryequals_nj;
	}

	public void setQueryequals_nj(String queryequalsNj) {
		queryequals_nj = queryequalsNj;
	}

	public String getQueryequals_xmlx() {
		return queryequals_xmlx;
	}

	public void setQueryequals_xmlx(String queryequals_xmlx) {
		this.queryequals_xmlx = queryequals_xmlx;
	}

	public String getQueryequals_xn() {
		return queryequals_xn;
	}

	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}

	public String getQueryequals_xq() {
		return queryequals_xq;
	}

	public void setQueryequals_xq(String queryequals_xq) {
		this.queryequals_xq = queryequals_xq;
	}

	public String getQuerylike_xmmc() {
		return querylike_xmmc;
	}

	public void setQuerylike_xmmc(String querylike_xmmc) {
		this.querylike_xmmc = querylike_xmmc;
	}

	public String[] getJxdm() {
		return jxdm;
	}

	public void setJxdm(String[] jxdm) {
		this.jxdm = jxdm;
	}

	public String[] getJxfs() {
		return jxfs;
	}

	public void setJxfs(String[] jxfs) {
		this.jxfs = jxfs;
	}

	public String[] getJxmc() {
		return jxmc;
	}

	public void setJxmc(String[] jxmc) {
		this.jxmc = jxmc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getFzr() {
		return fzr;
	}

	public void setFzr(String fzr) {
		this.fzr = fzr;
	}

	public String getHxnl() {
		return hxnl;
	}

	public void setHxnl(String hxnl) {
		this.hxnl = hxnl;
	}

	public String getJbjssj() {
		return jbjssj;
	}

	public void setJbjssj(String jbjssj) {
		this.jbjssj = jbjssj;
	}

	public String getJbkssj() {
		return jbkssj;
	}

	public void setJbkssj(String jbkssj) {
		this.jbkssj = jbkssj;
	}

	public String getJcf() {
		return jcf;
	}

	public void setJcf(String jcf) {
		this.jcf = jcf;
	}

	public String getSbr() {
		return sbr;
	}

	public void setSbr(String sbr) {
		this.sbr = sbr;
	}

	public String getShlcid() {
		return shlcid;
	}

	public void setShlcid(String shlcid) {
		this.shlcid = shlcid;
	}

	public String getSqbm() {
		return sqbm;
	}

	public void setSqbm(String sqbm) {
		this.sqbm = sqbm;
	}

	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
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

	public String getZbf() {
		return zbf;
	}

	public void setZbf(String zbf) {
		this.zbf = zbf;
	}

	public String getXs() {
		return xs;
	}

	public void setXs(String xs) {
		this.xs = xs;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getQuerylike_kmdm() {
		return querylike_kmdm;
	}

	public void setQuerylike_kmdm(String querylike_kmdm) {
		this.querylike_kmdm = querylike_kmdm;
	}

	public String getQuerylike_kmmc() {
		return querylike_kmmc;
	}

	public void setQuerylike_kmmc(String querylike_kmmc) {
		this.querylike_kmmc = querylike_kmmc;
	}

	public String getQuerylike_nj() {
		return querylike_nj;
	}

	public void setQuerylike_nj(String querylikeNj) {
		querylike_nj = querylikeNj;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getKmdm() {
		return kmdm;
	}

	public void setKmdm(String kmdm) {
		this.kmdm = kmdm;
	}

	public String getKmmc() {
		return kmmc;
	}

	public void setKmmc(String kmmc) {
		this.kmmc = kmmc;
	}

	public String[] getHxnldm() {
		return hxnldm;
	}

	public void setHxnldm(String[] hxnldm) {
		this.hxnldm = hxnldm;
	}

	public String[] getHxnlmc() {
		return hxnlmc;
	}

	public void setHxnlmc(String[] hxnlmc) {
		this.hxnlmc = hxnlmc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCgms() {
		return cgms;
	}

	public void setCgms(String cgms) {
		this.cgms = cgms;
	}

	public String getCyjs() {
		return cyjs;
	}

	public void setCyjs(String cyjs) {
		this.cyjs = cyjs;
	}

	public String getSfcx() {
		return sfcx;
	}

	public void setSfcx(String sfcx) {
		this.sfcx = sfcx;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXmid() {
		return xmid;
	}

	public void setXmid(String xmid) {
		this.xmid = xmid;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
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

	public String getRssx() {
		return rssx;
	}

	public void setRssx(String rssx) {
		this.rssx = rssx;
	}

	public String getSbjssj() {
		return sbjssj;
	}

	public void setSbjssj(String sbjssj) {
		this.sbjssj = sbjssj;
	}

	public String getSbkssj() {
		return sbkssj;
	}

	public void setSbkssj(String sbkssj) {
		this.sbkssj = sbkssj;
	}

	public String getXxdm() {
		return xxdm;
	}

	public void setXxdm(String xxdm) {
		this.xxdm = xxdm;
	}

	public String[] getMaxfs() {
		return maxfs;
	}

	public void setMaxfs(String[] maxfs) {
		this.maxfs = maxfs;
	}

	public String[] getMinfs() {
		return minfs;
	}

	public void setMinfs(String[] minfs) {
		this.minfs = minfs;
	}
}
