package xgxt.dtjs.jhzy.yydx;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class JhzyYydxForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = -768973083362107629L;
	private String kssj;//开始时间
	private String jssj;//结束时间
	private String xydm;
	private String dkjs;//党课届数
	private String rczy;//日程摘要
	private String xn;
	private String xq;
	private String kczy;
	private String jtap;
	private String bz;
	private String kssjH;//开始时间小时
	private String kssjM;//开始时间分
	private String kssjS;//开始时间秒
	private String jssjH;//结束时间小时
	private String jssjM;//结束时间分
	private String jssjS;//结束时间秒
	private String xh;
	private String lrrq;
	private String sqly;
	private String xysh;
	private String xxsh;
	private String qj;
	private String nj;
	private String bjdm;
	private String zydm;
	private String cj;
	private String xm;
	private String yesNo;
	private String [] pks;
	private String [] cjs;
	//上传文件
	FormFile uploadFile;
	private String scdz;//上传地址
	
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getDkjs() {
		return dkjs;
	}
	public void setDkjs(String dkjs) {
		this.dkjs = dkjs;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getJtap() {
		return jtap;
	}
	public void setJtap(String jtap) {
		this.jtap = jtap;
	}
	public String getKczy() {
		return kczy;
	}
	public void setKczy(String kczy) {
		this.kczy = kczy;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getRczy() {
		return rczy;
	}
	public void setRczy(String rczy) {
		this.rczy = rczy;
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
	public String getCj() {
		return cj;
	}
	public void setCj(String cj) {
		this.cj = cj;
	}
	public String getLrrq() {
		return lrrq;
	}
	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}
	public String getQj() {
		return qj;
	}
	public void setQj(String qj) {
		this.qj = qj;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
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
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
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
	public String[] getCjs() {
		return cjs;
	}
	public void setCjs(String[] cjs) {
		this.cjs = cjs;
	}
	public String[] getPks() {
		return pks;
	}
	public void setPks(String[] pks) {
		this.pks = pks;
	}
	public String getJssjH() {
		return jssjH;
	}
	public void setJssjH(String jssjH) {
		this.jssjH = jssjH;
	}
	public String getJssjM() {
		return jssjM;
	}
	public void setJssjM(String jssjM) {
		this.jssjM = jssjM;
	}
	public String getJssjS() {
		return jssjS;
	}
	public void setJssjS(String jssjS) {
		this.jssjS = jssjS;
	}
	public String getKssjH() {
		return kssjH;
	}
	public void setKssjH(String kssjH) {
		this.kssjH = kssjH;
	}
	public String getKssjM() {
		return kssjM;
	}
	public void setKssjM(String kssjM) {
		this.kssjM = kssjM;
	}
	public String getKssjS() {
		return kssjS;
	}
	public void setKssjS(String kssjS) {
		this.kssjS = kssjS;
	}
	public String getScdz() {
		return scdz;
	}
	public void setScdz(String scdz) {
		this.scdz = scdz;
	}
	public FormFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}
}
