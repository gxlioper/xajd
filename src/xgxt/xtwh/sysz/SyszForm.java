package xgxt.xtwh.sysz;

import org.apache.struts.upload.FormFile;

import xgxt.comm.CommForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import xgxt.xtwh.XtwhCommForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_首页设置_formBean
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SyszForm extends XtwhCommForm {

	private static final long serialVersionUID = 1L;
	
	FormFile updateFile;// 修改上传文件

	private String xmdm;// 项目代码

	private String xmmc;// 项目名称

	private String xmnr;// 项目内容

	private String xsfs;// 显示方式

	private String sfxs;// 是否显示

	private String xssx;// 显示顺序

	private String mklx;// 模块类型

	private String save_xmdm;// 项目代码

	private String save_xmmc;// 项目名称

	private String save_xmnr;// 项目内容

	private String save_xsfs;// 显示方式

	private String save_sfxs;// 是否显示

	private String save_xssx;// 显示顺序

	private String querylike_xmdm;// 项目代码

	private String querylike_xmmc;// 项目名称

	private String queryequals_xsfs;// 显示方式

	private String queryequals_sfxs;// 是否显示

	private String queryequals_xssx;// 显示顺序

	private boolean fdyQx;// 辅导员权限

	private boolean bzrQx;// 班主任权限

	private String[] sfxsArr;// 是否显示（批量保存）

	private String[] xmdmArr;// 项目代码（批量保存）

	private String fileid;// 文件编号

	private String filepath;// 文件地址
	
	private String updatePath;//修改文件路径

	private String filemc;// 文件名称

	private String filelx;// 文件类型

	private String filess;// 文件所属

	private String filesm;// 文件说明

	private String xzdx;// 下载对象
	private String towho;// 
	private String newsid;// 主键

	private String scr;// 上传人

	private String scsj;// 上传时间

	private String dcid;// '调查id';

	private String dcnr;// '调查内容';

	private String shownr;// '显示内容';

	private String sfqy;// '是否启用';

	private String dcr;// '调查人';

	private String dcsj;// '调查时间';

	private String[] xxid;// '选项id';

	private String[] xxnr;// '选项内容';

	private String bdcr;// '被调查人';

	private String dcqy;// 调查启用标志位;
	
	private String gnmklx;//功能模块类型(zz,pj)
	
	private String wjh;//文件号
	
	private String wjm;//文件名

	public String getWjh() {
		return wjh;
	}

	public void setWjh(String wjh) {
		this.wjh = wjh;
	}

	public String getWjm() {
		return wjm;
	}

	public void setWjm(String wjm) {
		this.wjm = wjm;
	}

	public String getDcqy() {
		return dcqy;
	}

	public void setDcqy(String dcqy) {
		this.dcqy = dcqy;
	}

	public boolean getBzrQx() {
		return bzrQx;
	}

	public void setBzrQx(boolean bzrQx) {
		this.bzrQx = bzrQx;
	}

	public boolean getFdyQx() {
		return fdyQx;
	}

	public void setFdyQx(boolean fdyQx) {
		this.fdyQx = fdyQx;
	}

	public String getQueryequals_sfxs() {
		return queryequals_sfxs;
	}

	public void setQueryequals_sfxs(String queryequals_sfxs) {
		this.queryequals_sfxs = queryequals_sfxs;
	}

	public String getQueryequals_xsfs() {
		return queryequals_xsfs;
	}

	public void setQueryequals_xsfs(String queryequals_xsfs) {
		this.queryequals_xsfs = queryequals_xsfs;
	}

	public String getQueryequals_xssx() {
		return queryequals_xssx;
	}

	public void setQueryequals_xssx(String queryequals_xssx) {
		this.queryequals_xssx = queryequals_xssx;
	}

	public String getQuerylike_xmdm() {
		return querylike_xmdm;
	}

	public void setQuerylike_xmdm(String querylike_xmdm) {
		this.querylike_xmdm = querylike_xmdm;
	}

	public String getQuerylike_xmmc() {
		return querylike_xmmc;
	}

	public void setQuerylike_xmmc(String querylike_xmmc) {
		this.querylike_xmmc = querylike_xmmc;
	}

	public String getSave_sfxs() {
		return save_sfxs;
	}

	public void setSave_sfxs(String save_sfxs) {
		this.save_sfxs = save_sfxs;
	}

	public String getSave_xmdm() {
		return save_xmdm;
	}

	public void setSave_xmdm(String save_xmdm) {
		this.save_xmdm = save_xmdm;
	}

	public String getSave_xmmc() {
		return save_xmmc;
	}

	public void setSave_xmmc(String save_xmmc) {
		this.save_xmmc = save_xmmc;
	}

	public String getSave_xmnr() {
		return save_xmnr;
	}

	public void setSave_xmnr(String save_xmnr) {
		this.save_xmnr = save_xmnr;
	}

	public String getSave_xsfs() {
		return save_xsfs;
	}

	public void setSave_xsfs(String save_xsfs) {
		this.save_xsfs = save_xsfs;
	}

	public String getSave_xssx() {
		return save_xssx;
	}

	public void setSave_xssx(String save_xssx) {
		this.save_xssx = save_xssx;
	}

	public String getSfxs() {
		return sfxs;
	}

	public void setSfxs(String sfxs) {
		this.sfxs = sfxs;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXmnr() {
		return xmnr;
	}

	public void setXmnr(String xmnr) {
		this.xmnr = xmnr;
	}

	public String getXsfs() {
		return xsfs;
	}

	public void setXsfs(String xsfs) {
		this.xsfs = xsfs;
	}

	public String getXssx() {
		return xssx;
	}

	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	public String getMklx() {
		return mklx;
	}

	public void setMklx(String mklx) {
		this.mklx = mklx;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public String getFilelx() {
		return filelx;
	}

	public void setFilelx(String filelx) {
		this.filelx = filelx;
	}

	public String getFilemc() {
		return filemc;
	}

	public void setFilemc(String filemc) {
		this.filemc = filemc;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFilesm() {
		return filesm;
	}

	public void setFilesm(String filesm) {
		this.filesm = filesm;
	}

	public String getScr() {
		return scr;
	}

	public void setScr(String scr) {
		this.scr = scr;
	}

	public String getScsj() {
		return scsj;
	}

	public void setScsj(String scsj) {
		this.scsj = scsj;
	}

	public String getXzdx() {
		return xzdx;
	}

	public void setXzdx(String xzdx) {
		this.xzdx = xzdx;
	}

	public String getTowho() {
		return towho;
	}

	public void setTowho(String towho) {
		this.towho = towho;
	}

	public String getNewsid() {
		return newsid;
	}

	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}

	public String getBdcr() {
		return bdcr;
	}

	public void setBdcr(String bdcr) {
		this.bdcr = bdcr;
	}

	public String getDcid() {
		return dcid;
	}

	public void setDcid(String dcid) {
		this.dcid = dcid;
	}

	public String getDcr() {
		return dcr;
	}

	public void setDcr(String dcr) {
		this.dcr = dcr;
	}

	public String getDcsj() {
		return dcsj;
	}

	public void setDcsj(String dcsj) {
		this.dcsj = dcsj;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	public String[] getXxid() {
		return xxid;
	}

	public void setXxid(String[] xxid) {
		this.xxid = xxid;
	}

	public String getDcnr() {
		return dcnr;
	}

	public void setDcnr(String dcnr) {
		this.dcnr = dcnr;
	}

	public String[] getXxnr() {
		return xxnr;
	}

	public void setXxnr(String[] xxnr) {
		this.xxnr = xxnr;
	}

	public String getShownr() {
		return shownr;
	}

	public void setShownr(String shownr) {
		this.shownr = shownr;
	}

	public String[] getSfxsArr() {
		return sfxsArr;
	}

	public void setSfxsArr(String[] sfxsArr) {
		this.sfxsArr = sfxsArr;
	}

	public String[] getXmdmArr() {
		return xmdmArr;
	}

	public void setXmdmArr(String[] xmdmArr) {
		this.xmdmArr = xmdmArr;
	}

	public String getFiless() {
		return filess;
	}

	public void setFiless(String filess) {
		this.filess = filess;
	}

	public FormFile getUpdateFile() {
		return updateFile;
	}

	public void setUpdateFile(FormFile updateFile) {
		this.updateFile = updateFile;
	}

	public String getUpdatePath() {
		return updatePath;
	}

	public void setUpdatePath(String updatePath) {
		this.updatePath = updatePath;
	}

	public String getGnmklx() {
		return gnmklx;
	}

	public void setGnmklx(String gnmklx) {
		this.gnmklx = gnmklx;
	}

}
