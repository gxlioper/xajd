package xsgzgl.xtwh.general.news;

import xgxt.comm.CommForm;

public class NewsManageForm extends CommForm {
	private static final long serialVersionUID = 1L;
	private String bt;
	private String kssj;
	private String jssj;
	private String sfzd;
	private String sffb;
	private String type;
	private String newsid;
	private String tablename;
	private String typeid;
	private String[] checkVal;
	private String isSaveQx;
	private String tableArr;
	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getBt() {
		return bt;
	}

	public void setBt(String bt) {
		this.bt = bt;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getSfzd() {
		return sfzd;
	}

	public void setSfzd(String sfzd) {
		this.sfzd = sfzd;
	}

	public String getSffb() {
		return sffb;
	}

	public void setSffb(String sffb) {
		this.sffb = sffb;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the typeid
	 */
	public String getTypeid() {
		return typeid;
	}

	/**
	 * @param typeid要设置的
	 *            typeid
	 */
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	/**
	 * @return the isSaveQx
	 */
	public String getIsSaveQx() {
		return isSaveQx;
	}

	/**
	 * @param isSaveQx要设置的 isSaveQx
	 */
	public void setIsSaveQx(String isSaveQx) {
		this.isSaveQx = isSaveQx;
	}

	public String getNewsid() {
		return newsid;
	}

	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getTableArr() {
		return tableArr;
	}

	public void setTableArr(String tableArr) {
		this.tableArr = tableArr;
	}
	
	
	

}
