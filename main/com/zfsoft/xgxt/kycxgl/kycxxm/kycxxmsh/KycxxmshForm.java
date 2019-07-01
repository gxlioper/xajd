package com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmsh;
import com.zfsoft.xgxt.kycxgl.kycxxm.comm.KycxxmForm;

/**
 * 科研创新项目审核
 */
public class KycxxmshForm extends KycxxmForm {

	private static final long serialVersionUID = 1L;
	private String sqid;
	
	private String xn;
	private String xmmc;
	private String zdls;
	private String zdlsxm;
	private String lbdm;
	private String lbmc;
	private String xmsqrxm;
	private String xmsqsj;
	private String xbjf;
	private String czr;
	private String czsj;
	
	private String shzt;
	private String splc;
	private String type;
	
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
	private String[] czrs;
	
	private String fjxx; //附件信息
	
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc要设置的 xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the zdls
	 */
	public String getZdls() {
		return zdls;
	}
	/**
	 * @param zdls要设置的 zdls
	 */
	public void setZdls(String zdls) {
		this.zdls = zdls;
	}
	/**
	 * @return the zdlsxm
	 */
	public String getZdlsxm() {
		return zdlsxm;
	}
	/**
	 * @param zdlsxm要设置的 zdlsxm
	 */
	public void setZdlsxm(String zdlsxm) {
		this.zdlsxm = zdlsxm;
	}
	/**
	 * @return the lbdm
	 */
	public String getLbdm() {
		return lbdm;
	}
	/**
	 * @param lbdm要设置的 lbdm
	 */
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}
	/**
	 * @return the lbmc
	 */
	public String getLbmc() {
		return lbmc;
	}
	/**
	 * @param lbmc要设置的 lbmc
	 */
	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}
	/**
	 * @return the xmsqrxm
	 */
	public String getXmsqrxm() {
		return xmsqrxm;
	}
	/**
	 * @param xmsqrxm要设置的 xmsqrxm
	 */
	public void setXmsqrxm(String xmsqrxm) {
		this.xmsqrxm = xmsqrxm;
	}
	/**
	 * @return the xmsqsj
	 */
	public String getXmsqsj() {
		return xmsqsj;
	}
	/**
	 * @param xmsqsj要设置的 xmsqsj
	 */
	public void setXmsqsj(String xmsqsj) {
		this.xmsqsj = xmsqsj;
	}
	/**
	 * @return the xbjf
	 */
	public String getXbjf() {
		return xbjf;
	}
	/**
	 * @param xbjf要设置的 xbjf
	 */
	public void setXbjf(String xbjf) {
		this.xbjf = xbjf;
	}
	/**
	 * @return the czr
	 */
	public String getCzr() {
		return czr;
	}
	/**
	 * @param czr要设置的 czr
	 */
	public void setCzr(String czr) {
		this.czr = czr;
	}
	/**
	 * @return the czsj
	 */
	public String getCzsj() {
		return czsj;
	}
	/**
	 * @param czsj要设置的 czsj
	 */
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the shlx
	 */
	public String getShlx() {
		return shlx;
	}
	/**
	 * @param shlx要设置的 shlx
	 */
	public void setShlx(String shlx) {
		this.shlx = shlx;
	}
	/**
	 * @return the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @param ywid要设置的 ywid
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	/**
	 * @return the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @param shsj要设置的 shsj
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	/**
	 * @return the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @param shr要设置的 shr
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyj要设置的 shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlc要设置的 shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwid要设置的 gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @return the shztmc
	 */
	public String getShztmc() {
		return shztmc;
	}
	/**
	 * @param shztmc要设置的 shztmc
	 */
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shid要设置的 shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgw要设置的 thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjg要设置的 shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String[] id) {
		this.id = id;
	}
	/**
	 * @return the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}
	/**
	 * @param splcs要设置的 splcs
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @param gwids要设置的 gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	/**
	 * @return the czrs
	 */
	public String[] getCzrs() {
		return czrs;
	}
	/**
	 * @param czrs要设置的 czrs
	 */
	public void setCzrs(String[] czrs) {
		this.czrs = czrs;
	}
	/**
	 * @return the fjxx
	 */
	public String getFjxx() {
		return fjxx;
	}
	/**
	 * @param fjxx要设置的 fjxx
	 */
	public void setFjxx(String fjxx) {
		this.fjxx = fjxx;
	}

	
}
