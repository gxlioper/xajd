package xgxt.xsh;

import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class XshModel {
	
	Pages pages = new Pages();
	
	private String fzrlb;
	
	private String hdzt;//活动主题
	
	private String hdsj;//活动时间
	
	private String hddd;//活动地点
	
	private String fqr;//发起人
	
	private String hdnr;//活动内容
	
	private String xczt;//宣传主题 
	
	private String xcdd;//宣传地点
	
	private String xcsj;//宣传时间
	
	private String xckh;//宣传口号
	
	private String bz;//备注
	
	private String fjlj;//附件路径
	
	FormFile uploadFile;
	
	private String stmc;//社团名称
	
	private String stcsr;//社团创始人
	
	private String stclsj;//社团成立时间
	
	private String stzz;//社团宗旨
	
	private String hdxs;//活动形式
	
	private String zdls;//指导老师
	
	private String fzr;//负责人
	
	private String sthddd;//社团活动地点

	private String yhm;//用户名
	
	private String xm;//姓名
	
	private String zmc;//组名称
	
	private String stxz;//社团性质
	
	private String bmdm;//部门代码
	
	private String stdm;//社团代码
	
	private String sfyxst;//是否优秀社团
	
	public String getSfyxst() {
		return sfyxst;
	}

	public void setSfyxst(String sfyxst) {
		this.sfyxst = sfyxst;
	}

	public String getStdm() {
		return stdm;
	}

	public void setStdm(String stdm) {
		this.stdm = stdm;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getStxz() {
		return stxz;
	}

	public void setStxz(String stxz) {
		this.stxz = stxz;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getFjlj() {
		return fjlj;
	}

	public void setFjlj(String fjlj) {
		this.fjlj = fjlj;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getXcdd() {
		return xcdd;
	}

	public void setXcdd(String xcdd) {
		this.xcdd = xcdd;
	}

	public String getXckh() {
		return xckh;
	}

	public void setXckh(String xckh) {
		this.xckh = xckh;
	}

	public String getXcsj() {
		return xcsj;
	}

	public void setXcsj(String xcsj) {
		this.xcsj = xcsj;
	}

	public String getXczt() {
		return xczt;
	}

	public void setXczt(String xczt) {
		this.xczt = xczt;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getFqr() {
		return fqr;
	}

	public void setFqr(String fqr) {
		this.fqr = fqr;
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

	public String getHdsj() {
		return hdsj;
	}

	public void setHdsj(String hdsj) {
		this.hdsj = hdsj;
	}

	public String getHdzt() {
		return hdzt;
	}

	public void setHdzt(String hdzt) {
		this.hdzt = hdzt;
	}

	public String getFzr() {
		return fzr;
	}

	public void setFzr(String fzr) {
		this.fzr = fzr;
	}

	public String getHdxs() {
		return hdxs;
	}

	public void setHdxs(String hdxs) {
		this.hdxs = hdxs;
	}

	public String getStclsj() {
		return stclsj;
	}

	public void setStclsj(String stclsj) {
		this.stclsj = stclsj;
	}

	public String getStcsr() {
		return stcsr;
	}

	public void setStcsr(String stcsr) {
		this.stcsr = stcsr;
	}

	public String getSthddd() {
		return sthddd;
	}

	public void setSthddd(String sthddd) {
		this.sthddd = sthddd;
	}

	public String getStmc() {
		return stmc;
	}

	public void setStmc(String stmc) {
		this.stmc = stmc;
	}

	public String getStzz() {
		return stzz;
	}

	public void setStzz(String stzz) {
		this.stzz = stzz;
	}

	public String getZdls() {
		return zdls;
	}

	public void setZdls(String zdls) {
		this.zdls = zdls;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getZmc() {
		return zmc;
	}

	public void setZmc(String zmc) {
		this.zmc = zmc;
	}

	public String getFzrlb() {
		return fzrlb;
	}

	public void setFzrlb(String fzrlb) {
		this.fzrlb = fzrlb;
	}
}
