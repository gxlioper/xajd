package com.zfsoft.xgxt.dekt.xfjg;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class DektxfjgForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel(); //高级查询
	private Pages pages = new Pages(); // 分页
	private ExportModel exportModel = new ExportModel(); //自定义导出
	
	private String jgid; //结果ID
	private String xh; //学号
	private String xmid; //项目ID
	private String cyfs; //参与方式 gr/tt 个人/团体
	private String cyfsmc;
	private String hjsj; //获奖时间
	private String sqsm; //申请说明
	private String filepath; //附件地址
	private String xf; //学分
	private String sjly; //数据来源
	private String sqid; //申请ID
	private String lx; //类型
	private String rdxm; //认定项目
	private String rdnrbz; //认定内容标准
	private String dj; //等级
	private String pjjg;//评奖结果
	private String pjbz;//评奖备注
	
	
	/**
	 * @return the lx
	 */
	public String getLx() {
		return lx;
	}
	/**
	 * @param lx要设置的 lx
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}
	/**
	 * @return the rdxm
	 */
	public String getRdxm() {
		return rdxm;
	}
	/**
	 * @param rdxm要设置的 rdxm
	 */
	public void setRdxm(String rdxm) {
		this.rdxm = rdxm;
	}
	/**
	 * @return the rdnrbz
	 */
	public String getRdnrbz() {
		return rdnrbz;
	}
	/**
	 * @param rdnrbz要设置的 rdnrbz
	 */
	public void setRdnrbz(String rdnrbz) {
		this.rdnrbz = rdnrbz;
	}
	/**
	 * @return the dj
	 */
	public String getDj() {
		return dj;
	}
	/**
	 * @param dj要设置的 dj
	 */
	public void setDj(String dj) {
		this.dj = dj;
	}
	/**
	 * @return the cyfsmc
	 */
	public String getCyfsmc() {
		return cyfsmc;
	}
	/**
	 * @param cyfsmc要设置的 cyfsmc
	 */
	public void setCyfsmc(String cyfsmc) {
		this.cyfsmc = cyfsmc;
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
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @param jgid要设置的 jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
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
	 * @return the xmid
	 */
	public String getXmid() {
		return xmid;
	}
	/**
	 * @param xmid要设置的 xmid
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	/**
	 * @return the cyfs
	 */
	public String getCyfs() {
		return cyfs;
	}
	/**
	 * @param cyfs要设置的 cyfs
	 */
	public void setCyfs(String cyfs) {
		this.cyfs = cyfs;
	}
	/**
	 * @return the hjsj
	 */
	public String getHjsj() {
		return hjsj;
	}
	/**
	 * @param hjsj要设置的 hjsj
	 */
	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}
	/**
	 * @return the sqsm
	 */
	public String getSqsm() {
		return sqsm;
	}
	/**
	 * @param sqsm要设置的 sqsm
	 */
	public void setSqsm(String sqsm) {
		this.sqsm = sqsm;
	}
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepath要设置的 filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the xf
	 */
	public String getXf() {
		return xf;
	}
	/**
	 * @param xf要设置的 xf
	 */
	public void setXf(String xf) {
		this.xf = xf;
	}
	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjly要设置的 sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-18 上午11:25:51 
	 * @return		: the pjjg
	 */
	public String getPjjg() {
		return pjjg;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-18 上午11:25:51 
	 * @param 		：pjjg the pjjg to set
	 */
	public void setPjjg(String pjjg) {
		this.pjjg = pjjg;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-18 上午11:25:51 
	 * @return		: the pjbz
	 */
	public String getPjbz() {
		return pjbz;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-18 上午11:25:51 
	 * @param 		：pjbz the pjbz to set
	 */
	public void setPjbz(String pjbz) {
		this.pjbz = pjbz;
	}
	
	
	
}
