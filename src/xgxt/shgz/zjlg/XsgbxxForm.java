package xgxt.shgz.zjlg;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;


public class XsgbxxForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 786463825497738971L;
	Pages pages = new Pages();
	private String id;
	private String xh;
	private String xm;
	private String xb;
	private String xydm;
	private String bjdm;
	private String sszz;
	private String srzw;
	private String drxzsj;
	private String zhaop;
	private String grjl;
	private String jcqk;
	private String gzzj;
	private String hdch;
	private String zwpd;
	private String zwpdsj;
	private String sjbmpdyj;
	private String sjbmpdsj;
	private String zongp;
	private String bz;
	private String xn;
	private String xq;
	private String yf;
	private String zwpdlx;
	private String sjbmpdlx;
	private String zppdsj;
	private String zppdlx;

	private String gzzjsj ;
	private String hdcd;
	private String hdcdsj;
	private String gzzjlx;
	private String hdchlx;
	FormFile uploadFile;
	FormFile impFilePath;
	FormFile checkFilePath;
	private FormFile file;
	
	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getSszz() {
		return sszz;
	}

	public void setSszz(String sszz) {
		this.sszz = sszz;
	}

	public String getSrzw() {
		return srzw;
	}

	public void setSrzw(String srzw) {
		this.srzw = srzw;
	}

	public String getDrxzsj() {
		return drxzsj;
	}

	public void setDrxzsj(String drxzsj) {
		this.drxzsj = drxzsj;
	}

	public String getZhaop() {
		return zhaop;
	}

	public void setZhaop(String zhaop) {
		this.zhaop = zhaop;
	}

	public String getGrjl() {
		return grjl;
	}

	public void setGrjl(String grjl) {
		this.grjl = grjl;
	}

	public String getJcqk() {
		return jcqk;
	}

	public void setJcqk(String jcqk) {
		this.jcqk = jcqk;
	}

	public String getGzzj() {
		return gzzj;
	}

	public void setGzzj(String gzzj) {
		this.gzzj = gzzj;
	}

	public String getHdch() {
		return hdch;
	}

	public void setHdch(String hdch) {
		this.hdch = hdch;
	}

	public String getZwpd() {
		return zwpd;
	}

	public void setZwpd(String zwpd) {
		this.zwpd = zwpd;
	}

	public String getZwpdsj() {
		return zwpdsj;
	}

	public void setZwpdsj(String zwpdsj) {
		this.zwpdsj = zwpdsj;
	}

	public String getSjbmpdyj() {
		return sjbmpdyj;
	}

	public void setSjbmpdyj(String sjbmpdyj) {
		this.sjbmpdyj = sjbmpdyj;
	}

	public String getSjbmpdsj() {
		return sjbmpdsj;
	}

	public void setSjbmpdsj(String sjbmpdsj) {
		this.sjbmpdsj = sjbmpdsj;
	}

	public String getZongp() {
		return zongp;
	}

	public void setZongp(String zongp) {
		this.zongp = zongp;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
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

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public String getZwpdlx() {
		return zwpdlx;
	}

	public void setZwpdlx(String zwpdlx) {
		this.zwpdlx = zwpdlx;
	}

	public String getSjbmpdlx() {
		return sjbmpdlx;
	}

	public void setSjbmpdlx(String sjbmpdlx) {
		this.sjbmpdlx = sjbmpdlx;
	}

	public String getZppdsj() {
		return zppdsj;
	}

	public void setZppdsj(String zppdsj) {
		this.zppdsj = zppdsj;
	}

	public String getZppdlx() {
		return zppdlx;
	}

	public void setZppdlx(String zppdlx) {
		this.zppdlx = zppdlx;
	}

	public String getGzzjsj() {
		return gzzjsj;
	}

	public void setGzzjsj(String gzzjsj) {
		this.gzzjsj = gzzjsj;
	}

	public String getHdcd() {
		return hdcd;
	}

	public void setHdcd(String hdcd) {
		this.hdcd = hdcd;
	}

	public String getHdcdsj() {
		return hdcdsj;
	}

	public void setHdcdsj(String hdcdsj) {
		this.hdcdsj = hdcdsj;
	}

	public String getGzzjlx() {
		return gzzjlx;
	}

	public void setGzzjlx(String gzzjlx) {
		this.gzzjlx = gzzjlx;
	}

	public String getHdchlx() {
		return hdchlx;
	}

	public void setHdchlx(String hdchlx) {
		this.hdchlx = hdchlx;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public FormFile getImpFilePath() {
		return impFilePath;
	}

	public void setImpFilePath(FormFile impFilePath) {
		this.impFilePath = impFilePath;
	}

	public FormFile getCheckFilePath() {
		return checkFilePath;
	}

	public void setCheckFilePath(FormFile checkFilePath) {
		this.checkFilePath = checkFilePath;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}
	

}