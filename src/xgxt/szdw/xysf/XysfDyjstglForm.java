package xgxt.szdw.xysf;

import xgxt.dtjs.gdby.tygl.BasicExtendForm;
import xgxt.utils.Pages;

public class XysfDyjstglForm extends BasicExtendForm {
	private Pages pages = new Pages();

	private String[] pxsj;
	private String[] pxdd;
	private String[] pxnr;
	private String[] pxmc;

	private String[] sksj; // 授课时间
	private String[] skdd; // 授课地点
	private String[] bz; // 备注
	private String[] hdgm; // 活动规模
	private String zgh; // 职工号
	private String[] zt; // 主题

	private String save_wzmc; // 文章名称
	private String save_fbqk; // 发表期刊
	private String save_qkjb; // 期刊级别
	private String save_qkqs; // 期刊期数

	private String save_pxpc;
	private String save_kscj;
	private String save_xxpj;
	
	private String save_pxmc;
	private String save_pxdd;
	private String save_bz;
	private String save_pxnr;
	private String save_pxsj;

	private String querylike_wzmc;

	
	public String getSave_pxmc() {
		return save_pxmc;
	}

	public void setSave_pxmc(String savePxmc) {
		save_pxmc = savePxmc;
	}

	public String getSave_pxdd() {
		return save_pxdd;
	}

	public void setSave_pxdd(String savePxdd) {
		save_pxdd = savePxdd;
	}

	public String getSave_bz() {
		return save_bz;
	}

	public void setSave_bz(String saveBz) {
		save_bz = saveBz;
	}

	public String getSave_pxnr() {
		return save_pxnr;
	}

	public void setSave_pxnr(String savePxnr) {
		save_pxnr = savePxnr;
	}

	public String getSave_pxsj() {
		return save_pxsj;
	}

	public void setSave_pxsj(String savePxsj) {
		save_pxsj = savePxsj;
	}

	public String getSave_pxpc() {
		return save_pxpc;
	}

	public String[] getPxsj() {
		return pxsj;
	}

	public void setPxsj(String[] pxsj) {
		this.pxsj = pxsj;
	}

	public String[] getPxdd() {
		return pxdd;
	}

	public void setPxdd(String[] pxdd) {
		this.pxdd = pxdd;
	}

	public String[] getPxnr() {
		return pxnr;
	}

	public void setPxnr(String[] pxnr) {
		this.pxnr = pxnr;
	}

	public String[] getPxmc() {
		return pxmc;
	}

	public void setPxmc(String[] pxmc) {
		this.pxmc = pxmc;
	}

	public void setSave_pxpc(String savePxpc) {
		save_pxpc = savePxpc;
	}

	public String getSave_kscj() {
		return save_kscj;
	}

	public void setSave_kscj(String saveKscj) {
		save_kscj = saveKscj;
	}

	public String getSave_xxpj() {
		return save_xxpj;
	}

	public void setSave_xxpj(String saveXxpj) {
		save_xxpj = saveXxpj;
	}

	public String getQuerylike_wzmc() {
		return querylike_wzmc;
	}

	public void setQuerylike_wzmc(String querylikeWzmc) {
		querylike_wzmc = querylikeWzmc;
	}

	public String getSave_wzmc() {
		return save_wzmc;
	}

	public void setSave_wzmc(String saveWzmc) {
		save_wzmc = saveWzmc;
	}

	public String getSave_fbqk() {
		return save_fbqk;
	}

	public void setSave_fbqk(String saveFbqk) {
		save_fbqk = saveFbqk;
	}

	public String getSave_qkjb() {
		return save_qkjb;
	}

	public void setSave_qkjb(String saveQkjb) {
		save_qkjb = saveQkjb;
	}

	public String getSave_qkqs() {
		return save_qkqs;
	}

	public void setSave_qkqs(String saveQkqs) {
		save_qkqs = saveQkqs;
	}

	public String[] getSksj() {
		return sksj;
	}

	public void setSksj(String[] sksj) {
		this.sksj = sksj;
	}

	public String[] getSkdd() {
		return skdd;
	}

	public void setSkdd(String[] skdd) {
		this.skdd = skdd;
	}

	public String[] getBz() {
		return bz;
	}

	public void setBz(String[] bz) {
		this.bz = bz;
	}

	public String[] getHdgm() {
		return hdgm;
	}

	public void setHdgm(String[] hdgm) {
		this.hdgm = hdgm;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String[] getZt() {
		return zt;
	}

	public void setZt(String[] zt) {
		this.zt = zt;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
