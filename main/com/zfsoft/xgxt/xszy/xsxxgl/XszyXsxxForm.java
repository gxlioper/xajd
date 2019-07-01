package com.zfsoft.xgxt.xszy.xsxxgl;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 新生之友
 * @类功能描述: 新生信息管理
 */
public class XszyXsxxForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	// 分页
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();

	FormFile uploadFile;// 上传文件

	private String yclx;//异常数据类型
	
	private String[] primarykey_checkVal;// CheckBox
	
	private String type; //类型
	
	private String xh;
	private String jg;
	private String mzdm;
	private String lxdh;
	private String dh;
	private String bzrxm;
	private String bzrlxdh;
	private String fdyxm;
	private String fdylxdh;
	private String bzryx;
	private String fdyyx;
	private String bz;
	private String zd1;
	private String zd2;
	private String zd3;
	private String dzyx;
	
	private String lddm;
	private String qsh;
	private String xydm;
	private String ssyxdm;
	private String ssyxmc;
	private String ldmc;
	private String xymc;
	private String xm;
	private String dldm;
	private String xb;
	private String dl;
	private String bjmc;
	private String bjdm;
	private String nj;
	private String jgmc;
	private String mzdmmc;
	
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pages要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModel要设置的 searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @return the uploadFile
	 */
	public FormFile getUploadFile() {
		return uploadFile;
	}
	/**
	 * @param uploadFile要设置的 uploadFile
	 */
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	/**
	 * @return the yclx
	 */
	public String getYclx() {
		return yclx;
	}
	/**
	 * @param yclx要设置的 yclx
	 */
	public void setYclx(String yclx) {
		this.yclx = yclx;
	}
	/**
	 * @return the primarykey_checkVal
	 */
	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}
	/**
	 * @param primarykeyCheckVal要设置的 primarykey_checkVal
	 */
	public void setPrimarykey_checkVal(String[] primarykeyCheckVal) {
		primarykey_checkVal = primarykeyCheckVal;
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
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh要设置的 xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the jg
	 */
	public String getJg() {
		return jg;
	}
	/**
	 * @param jg要设置的 jg
	 */
	public void setJg(String jg) {
		this.jg = jg;
	}
	/**
	 * @return the mzdm
	 */
	public String getMzdm() {
		return mzdm;
	}
	/**
	 * @param mzdm要设置的 mzdm
	 */
	public void setMzdm(String mzdm) {
		this.mzdm = mzdm;
	}
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdh要设置的 lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return the dh
	 */
	public String getDh() {
		return dh;
	}
	/**
	 * @param dh要设置的 dh
	 */
	public void setDh(String dh) {
		this.dh = dh;
	}
	/**
	 * @return the bzrxm
	 */
	public String getBzrxm() {
		return bzrxm;
	}
	/**
	 * @param bzrxm要设置的 bzrxm
	 */
	public void setBzrxm(String bzrxm) {
		this.bzrxm = bzrxm;
	}
	/**
	 * @return the bzrlxdh
	 */
	public String getBzrlxdh() {
		return bzrlxdh;
	}
	/**
	 * @param bzrlxdh要设置的 bzrlxdh
	 */
	public void setBzrlxdh(String bzrlxdh) {
		this.bzrlxdh = bzrlxdh;
	}
	/**
	 * @return the fdyxm
	 */
	public String getFdyxm() {
		return fdyxm;
	}
	/**
	 * @param fdyxm要设置的 fdyxm
	 */
	public void setFdyxm(String fdyxm) {
		this.fdyxm = fdyxm;
	}
	/**
	 * @return the fdylxdh
	 */
	public String getFdylxdh() {
		return fdylxdh;
	}
	/**
	 * @param fdylxdh要设置的 fdylxdh
	 */
	public void setFdylxdh(String fdylxdh) {
		this.fdylxdh = fdylxdh;
	}
	/**
	 * @return the bzryx
	 */
	public String getBzryx() {
		return bzryx;
	}
	/**
	 * @param bzryx要设置的 bzryx
	 */
	public void setBzryx(String bzryx) {
		this.bzryx = bzryx;
	}
	/**
	 * @return the fdyyx
	 */
	public String getFdyyx() {
		return fdyyx;
	}
	/**
	 * @param fdyyx要设置的 fdyyx
	 */
	public void setFdyyx(String fdyyx) {
		this.fdyyx = fdyyx;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}
	/**
	 * @param zd1要设置的 zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	/**
	 * @return the zd2
	 */
	public String getZd2() {
		return zd2;
	}
	/**
	 * @param zd2要设置的 zd2
	 */
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}
	/**
	 * @return the zd3
	 */
	public String getZd3() {
		return zd3;
	}
	/**
	 * @param zd3要设置的 zd3
	 */
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}
	/**
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddm要设置的 lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @param qsh要设置的 qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @param xydm要设置的 xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	/**
	 * @return the ssyxdm
	 */
	public String getSsyxdm() {
		return ssyxdm;
	}
	/**
	 * @param ssyxdm要设置的 ssyxdm
	 */
	public void setSsyxdm(String ssyxdm) {
		this.ssyxdm = ssyxdm;
	}
	/**
	 * @return the ssyxmc
	 */
	public String getSsyxmc() {
		return ssyxmc;
	}
	/**
	 * @param ssyxmc要设置的 ssyxmc
	 */
	public void setSsyxmc(String ssyxmc) {
		this.ssyxmc = ssyxmc;
	}
	/**
	 * @return the ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmc要设置的 ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymc要设置的 xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the dldm
	 */
	public String getDldm() {
		return dldm;
	}
	/**
	 * @param dldm要设置的 dldm
	 */
	public void setDldm(String dldm) {
		this.dldm = dldm;
	}
	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}
	/**
	 * @param xb要设置的 xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}
	/**
	 * @return the dl
	 */
	public String getDl() {
		return dl;
	}
	/**
	 * @param dl要设置的 dl
	 */
	public void setDl(String dl) {
		this.dl = dl;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmc要设置的 bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdm要设置的 bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param nj要设置的 nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @return the jgmc
	 */
	public String getJgmc() {
		return jgmc;
	}
	/**
	 * @param jgmc要设置的 jgmc
	 */
	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}
	/**
	 * @return the mzdmmc
	 */
	public String getMzdmmc() {
		return mzdmmc;
	}
	/**
	 * @param mzdmmc要设置的 mzdmmc
	 */
	public void setMzdmmc(String mzdmmc) {
		this.mzdmmc = mzdmmc;
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	
	
}
