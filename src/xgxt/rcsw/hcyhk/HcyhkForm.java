package xgxt.rcsw.hcyhk;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class HcyhkForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private Pages pages = new Pages();
	
	private String shr;
	
	private String shzt;
	
	private String shjg;//审核结果
	
	private String shsj;
	
	private String shyj;
	
	private String shgw;
	
	private String sqsj;
	
	private String sqkssj;
	
	private String sqjssj;
	
	private String xh;
	
	private String xydm;
	
	private String nj;
	
	private String zydm;
	
	private String bjdm;
	
	private String xm;

	private String save_xh;
	
	private String save_sqsj;//申请时间
	
	private String save_sqly;//申请理由
	
	private String save_bz;//备注
	
	private String qdz;//起点站
	
	private String zdz;//终点站
	
	private String save_qdz;//起点站
	
	private String save_zdz;//终点站

	public String getSave_bz() {
		return save_bz;
	}

	public void setSave_bz(String save_bz) {
		this.save_bz = save_bz;
	}

	public String getSave_sqsj() {
		return save_sqsj;
	}

	public void setSave_sqsj(String save_sqsj) {
		this.save_sqsj = save_sqsj;
	}

	public String getSave_xh() {
		return save_xh;
	}

	public void setSave_xh(String save_xh) {
		this.save_xh = save_xh;
	}

	public String getSave_sqly() {
		return save_sqly;
	}

	public void setSave_sqly(String save_sqly) {
		this.save_sqly = save_sqly;
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

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
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

	public String getSqjssj() {
		return sqjssj;
	}

	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}

	public String getSqkssj() {
		return sqkssj;
	}

	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}

	public String getShgw() {
		return shgw;
	}

	public void setShgw(String shgw) {
		this.shgw = shgw;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
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

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getQdz() {
		return qdz;
	}

	public void setQdz(String qdz) {
		this.qdz = qdz;
	}

	public String getSave_qdz() {
		return save_qdz;
	}

	public void setSave_qdz(String save_qdz) {
		this.save_qdz = save_qdz;
	}

	public String getSave_zdz() {
		return save_zdz;
	}

	public void setSave_zdz(String save_zdz) {
		this.save_zdz = save_zdz;
	}

	public String getZdz() {
		return zdz;
	}

	public void setZdz(String zdz) {
		this.zdz = zdz;
	}
}
