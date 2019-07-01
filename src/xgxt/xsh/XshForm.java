package xgxt.xsh;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.upload.FormFile;

import xgxt.bdsz.BdzdForm;
import xgxt.utils.Pages;

public class XshForm extends BdzdForm {
	
	private static final long serialVersionUID = -7601891413834718664L;

	Pages pages = new Pages();
	
	
	private String stValue;//社团主键值 
	
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
	
	private String fzrlb;//负责人类别 
	
	private String yhm;//用户名
	
	private String xm;//姓名
	
	private String zmc;//组名称
	
	private String stxz;//社团性质
	
	private String bmdm;//部门代码
	
	private String zwdm;//职务代码
	
	private String sfyxst;//是否优秀社团
	
	private String sfyxstgb;//是否优秀社团干部
	
	private String stdm;//社团代码
	
	private String queryequals_nj;
	
	private String querylike_xh;
	
	private String querylike_xm;
	
	private String queryequals_xydm;
	
	private String queryequals_zydm;
	
	private String queryequals_bjdm;
	
	private String queryequals_stv;
	
	private String queryequals_zwdm;
	
	private String queryequals_sfyxstgb;
	
	private String queryequals_zcnd;
	
	private String nd;
	
	
	private HashMap<String, ArrayList<HashMap<String, String>>> opslist;


	public HashMap<String, ArrayList<HashMap<String, String>>> getOpslist() {
		return opslist;
	}

	public void setOpslist(
			HashMap<String, ArrayList<HashMap<String, String>>> opslist) {
		this.opslist = opslist;
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

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
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

	public String getFzrlb() {
		return fzrlb;
	}

	public void setFzrlb(String fzrlb) {
		this.fzrlb = fzrlb;
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

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}

	public String getQueryequals_nj() {
		return queryequals_nj;
	}

	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}

	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}

	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}

	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}

	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}

	public String getQuerylike_xh() {
		return querylike_xh;
	}

	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}

	public String getQuerylike_xm() {
		return querylike_xm;
	}

	public void setQuerylike_xm(String querylike_xm) {
		this.querylike_xm = querylike_xm;
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

	public String getZwdm() {
		return zwdm;
	}

	public void setZwdm(String zwdm) {
		this.zwdm = zwdm;
	}

	public String getQueryequals_stv() {
		return queryequals_stv;
	}

	public void setQueryequals_stv(String queryequals_stv) {
		this.queryequals_stv = queryequals_stv;
	}

	public String getQueryequals_zwdm() {
		return queryequals_zwdm;
	}

	public void setQueryequals_zwdm(String queryequals_zwdm) {
		this.queryequals_zwdm = queryequals_zwdm;
	}

	public String getStValue() {
		return stValue;
	}

	public void setStValue(String stValue) {
		this.stValue = stValue;
	}

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

	public String getSfyxstgb() {
		return sfyxstgb;
	}

	public void setSfyxstgb(String sfyxstgb) {
		this.sfyxstgb = sfyxstgb;
	}

	public String getQueryequals_sfyxstgb() {
		return queryequals_sfyxstgb;
	}

	public void setQueryequals_sfyxstgb(String queryequals_sfyxstgb) {
		this.queryequals_sfyxstgb = queryequals_sfyxstgb;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getQueryequals_zcnd() {
		return queryequals_zcnd;
	}

	public void setQueryequals_zcnd(String queryequals_zcnd) {
		this.queryequals_zcnd = queryequals_zcnd;
	}
	
	
}
