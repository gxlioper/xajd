package xgxt.dtjs.zgdzdx;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class ZgdzdxDtjsForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205711105806100577L;

	private String nj;

	private String nd;

	private String xn;

	private String zydm;

	private String xydm;

	private String bjdm;

	private String xq;

	private String xh;

	private String xm;

	private String xb;

	private String fssj;

	private String hddd;

	private String hdzt;

	private String hdnr;

	private String hdxg;

	private String bz;
	
//	发表论文
	private String bmdm;
	private String zgh;
	private String lwlbdm;
	private String lwtm;
	private String zy;
	private String fbsj;
	private String fbqkmc;
	private String xzlj;
	
	//科研项目
	private String xmmc;
	private String xmjs;
	private String xmjbdm;
	private String xmly;
	private String fzr;
	private String cyr;
	private String kssj;
	private String jssj;
	private String kyjf;
	private String shsj;
	private String shyj;
	
	//辅导员著作
	private String zzmc;
	private String zzjs;
	private String cbdw;
	
	//思想政治研究资料
	private String sm;
	
	//学工信息
	private String zt;
	private String dw;
	private String tjyhm;
	private String tjsj;
	private String bt;
	private String lbdm;
	private String qj;
	private String nr;
	private String fz;
	
	//上传文件
	FormFile uploadFile;
	
	
	private String id;

	private String sydw;

	private String sqrxm;

	private String sqrbj;

	private String sysj;

	private String rs;

	private String sfsp;

	private String xxsh;

	private String sbmc;

	private String sbxh;

	private String grsj;

	private String bxsj;

	private String jg;

	private String gmjsr;

	private String sbfzr;

	private String sqkm;

	private String cbs;

	private String cbsj;

	private String kfjy;

	private String sqkmc;

	private String jyr;

	private String jyrbj;

	private String jysj;

	private String ghsj;

	private String lxdh;

	// 通用分页
	// 值班ID
	private String zbid;

	// 岗位名称
	private String gwmc;

	// 银行账号
	private String yhzh;

	// 联系电话
	private String dh;

	// 值班时间
	private String zbsj;

	// 值班情况
	private String zbqk;

	// 备注
	private String wlbz;

	// 党支部名称
	private String dzbmc;

	// 责任区
	private String zrq;

	// 党支部成员数
	private String dzbcys;

	// 党支部与教育活动举措
	private String dzbjycs;

	// 备注
	private String dzbbz;
	
	//调研计划
	private String dytm;
	private String dytg;
	private String dynr;
	private String dysj;
	private String dydd;
	
	//通用分页
	Pages pages = new Pages();

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
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

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getFssj() {
		return fssj;
	}

	public void setFssj(String fssj) {
		this.fssj = fssj;
	}

	public String getHddd() {
		return hddd;
	}

	public void setHddd(String hddd) {
		this.hddd = hddd;
	}

	public String getHdnr() {
		return hdnr;
	}

	public void setHdnr(String hdnr) {
		this.hdnr = hdnr;
	}

	public String getHdxg() {
		return hdxg;
	}

	public void setHdxg(String hdxg) {
		this.hdxg = hdxg;
	}

	public String getHdzt() {
		return hdzt;
	}

	public void setHdzt(String hdzt) {
		this.hdzt = hdzt;
	}
	public String getCbdw() {
		return cbdw;
	}
	public void setCbdw(String cbdw) {
		this.cbdw = cbdw;
	}
	public String getCyr() {
		return cyr;
	}
	public void setCyr(String cyr) {
		this.cyr = cyr;
	}
	public String getFbqkmc() {
		return fbqkmc;
	}
	public void setFbqkmc(String fbqkmc) {
		this.fbqkmc = fbqkmc;
	}
	public String getFbsj() {
		return fbsj;
	}
	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}
	public String getFzr() {
		return fzr;
	}
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getKyjf() {
		return kyjf;
	}
	public void setKyjf(String kyjf) {
		this.kyjf = kyjf;
	}
	public String getLwlbdm() {
		return lwlbdm;
	}
	public void setLwlbdm(String lwlbdm) {
		this.lwlbdm = lwlbdm;
	}
	public String getLwtm() {
		return lwtm;
	}
	public void setLwtm(String lwtm) {
		this.lwtm = lwtm;
	}
	public String getSm() {
		return sm;
	}
	public void setSm(String sm) {
		this.sm = sm;
	}
	public String getXmjbdm() {
		return xmjbdm;
	}
	public void setXmjbdm(String xmjbdm) {
		this.xmjbdm = xmjbdm;
	}
	public String getXmjs() {
		return xmjs;
	}
	public void setXmjs(String xmjs) {
		this.xmjs = xmjs;
	}
	public String getXmly() {
		return xmly;
	}
	public void setXmly(String xmly) {
		this.xmly = xmly;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getXzlj() {
		return xzlj;
	}
	public void setXzlj(String xzlj) {
		this.xzlj = xzlj;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getZzjs() {
		return zzjs;
	}
	public void setZzjs(String zzjs) {
		this.zzjs = zzjs;
	}
	public String getZzmc() {
		return zzmc;
	}
	public void setZzmc(String zzmc) {
		this.zzmc = zzmc;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public FormFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSydw() {
		return sydw;
	}

	public void setSydw(String sydw) {
		this.sydw = sydw;
	}

	public String getSqrxm() {
		return sqrxm;
	}

	public void setSqrxm(String sqrxm) {
		this.sqrxm = sqrxm;
	}

	public String getSqrbj() {
		return sqrbj;
	}

	public void setSqrbj(String sqrbj) {
		this.sqrbj = sqrbj;
	}

	public String getSysj() {
		return sysj;
	}

	public void setSysj(String sysj) {
		this.sysj = sysj;
	}

	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	public String getSfsp() {
		return sfsp;
	}

	public void setSfsp(String sfsp) {
		this.sfsp = sfsp;
	}

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getSbmc() {
		return sbmc;
	}

	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}

	public String getSbxh() {
		return sbxh;
	}

	public void setSbxh(String sbxh) {
		this.sbxh = sbxh;
	}

	public String getGrsj() {
		return grsj;
	}

	public void setGrsj(String grsj) {
		this.grsj = grsj;
	}

	public String getBxsj() {
		return bxsj;
	}

	public void setBxsj(String bxsj) {
		this.bxsj = bxsj;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getGmjsr() {
		return gmjsr;
	}

	public void setGmjsr(String gmjsr) {
		this.gmjsr = gmjsr;
	}

	public String getSbfzr() {
		return sbfzr;
	}

	public void setSbfzr(String sbfzr) {
		this.sbfzr = sbfzr;
	}

	public String getSqkm() {
		return sqkm;
	}

	public void setSqkm(String sqkm) {
		this.sqkm = sqkm;
	}

	public String getCbs() {
		return cbs;
	}

	public void setCbs(String cbs) {
		this.cbs = cbs;
	}

	public String getCbsj() {
		return cbsj;
	}

	public void setCbsj(String cbsj) {
		this.cbsj = cbsj;
	}

	public String getKfjy() {
		return kfjy;
	}

	public void setKfjy(String kfjy) {
		this.kfjy = kfjy;
	}

	public String getSqkmc() {
		return sqkmc;
	}

	public void setSqkmc(String sqkmc) {
		this.sqkmc = sqkmc;
	}

	public String getJyr() {
		return jyr;
	}

	public void setJyr(String jyr) {
		this.jyr = jyr;
	}

	public String getJyrbj() {
		return jyrbj;
	}

	public void setJyrbj(String jyrbj) {
		this.jyrbj = jyrbj;
	}

	public String getJysj() {
		return jysj;
	}

	public void setJysj(String jysj) {
		this.jysj = jysj;
	}

	public String getGhsj() {
		return ghsj;
	}

	public void setGhsj(String ghsj) {
		this.ghsj = ghsj;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getGwmc() {
		return gwmc;
	}

	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}

	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getYhzh() {
		return yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}

	public String getZbid() {
		return zbid;
	}

	public void setZbid(String zbid) {
		this.zbid = zbid;
	}

	public String getZbqk() {
		return zbqk;
	}

	public void setZbqk(String zbqk) {
		this.zbqk = zbqk;
	}

	public String getZbsj() {
		return zbsj;
	}

	public void setZbsj(String zbsj) {
		this.zbsj = zbsj;
	}

	public String getWlbz() {
		return wlbz;
	}

	public void setWlbz(String wlbz) {
		this.wlbz = wlbz;
	}

	public String getDzbbz() {
		return dzbbz;
	}

	public void setDzbbz(String dzbbz) {
		this.dzbbz = dzbbz;
	}

	public String getDzbcys() {
		return dzbcys;
	}

	public void setDzbcys(String dzbcys) {
		this.dzbcys = dzbcys;
	}

	public String getDzbjycs() {
		return dzbjycs;
	}

	public void setDzbjycs(String dzbjycs) {
		this.dzbjycs = dzbjycs;
	}

	public String getDzbmc() {
		return dzbmc;
	}

	public void setDzbmc(String dzbmc) {
		this.dzbmc = dzbmc;
	}

	public String getZrq() {
		return zrq;
	}

	public void setZrq(String zrq) {
		this.zrq = zrq;
	}

	public String getDydd() {
		return dydd;
	}

	public void setDydd(String dydd) {
		this.dydd = dydd;
	}

	public String getDynr() {
		return dynr;
	}

	public void setDynr(String dynr) {
		this.dynr = dynr;
	}

	public String getDysj() {
		return dysj;
	}

	public void setDysj(String dysj) {
		this.dysj = dysj;
	}

	public String getDytg() {
		return dytg;
	}

	public void setDytg(String dytg) {
		this.dytg = dytg;
	}

	public String getDytm() {
		return dytm;
	}

	public void setDytm(String dytm) {
		this.dytm = dytm;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getBt() {
		return bt;
	}

	public void setBt(String bt) {
		this.bt = bt;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public String getLbdm() {
		return lbdm;
	}

	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getQj() {
		return qj;
	}

	public void setQj(String qj) {
		this.qj = qj;
	}

	public String getTjsj() {
		return tjsj;
	}

	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}

	public String getTjyhm() {
		return tjyhm;
	}

	public void setTjyhm(String tjyhm) {
		this.tjyhm = tjyhm;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getFz() {
		return fz;
	}

	public void setFz(String fz) {
		this.fz = fz;
	}

}
