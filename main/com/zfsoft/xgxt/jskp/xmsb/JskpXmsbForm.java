package com.zfsoft.xgxt.jskp.xmsb;




import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 记实考评
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2017-7-5 下午04:42:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JskpXmsbForm extends ActionForm{
	private static final long serialVersionUID = -4562302399219564190L;
	private String sqid;
	private String xmid;
	private String xmmc;
    private String zdbm;
    private String xmlb;
    private String lxsj;
    private String fzr;
    private String fzrlxfs;
    private String zdls;
    private String zdlslxfs;
	private String xh;
	private String nj;
	private String xn;
	private String xq;
	private String xqmc;
	private String sbsj;
	private String hjsj;
	private String shzt;
	private String splcid;
	private String sbzt;//申报状态
	private String type;
	
	private String sbly;
	private String fjid;
	//下载相关
	private FormFile formfile;
	private String filepath;
    
    private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
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
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the sbsj
	 */
	public String getSbsj() {
		return sbsj;
	}
	/**
	 * @param sbsj要设置的 sbsj
	 */
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
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
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	
	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcid要设置的 splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
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
	 * @return the sbly
	 */
	public String getSbly() {
		return sbly;
	}
	/**
	 * @param sbly要设置的 sbly
	 */
	public void setSbly(String sbly) {
		this.sbly = sbly;
	}
	/**
	 * @return the fjid
	 */
	public String getFjid() {
		return fjid;
	}
	/**
	 * @param fjid要设置的 fjid
	 */
	public void setFjid(String fjid) {
		this.fjid = fjid;
	}
	/**
	 * @return the formfile
	 */
	public FormFile getFormfile() {
		return formfile;
	}
	/**
	 * @param formfile要设置的 formfile
	 */
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
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
	 * @return the sbzt
	 */
	public String getSbzt() {
		return sbzt;
	}
	/**
	 * @param sbzt要设置的 sbzt
	 */
	public void setSbzt(String sbzt) {
		this.sbzt = sbzt;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc要设置的 xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the zdbm
	 */
	public String getZdbm() {
		return zdbm;
	}
	/**
	 * @param zdbm要设置的 zdbm
	 */
	public void setZdbm(String zdbm) {
		this.zdbm = zdbm;
	}
	/**
	 * @return the xmlb
	 */
	public String getXmlb() {
		return xmlb;
	}
	/**
	 * @param xmlb要设置的 xmlb
	 */
	public void setXmlb(String xmlb) {
		this.xmlb = xmlb;
	}
	/**
	 * @return the lxsj
	 */
	public String getLxsj() {
		return lxsj;
	}
	/**
	 * @param lxsj要设置的 lxsj
	 */
	public void setLxsj(String lxsj) {
		this.lxsj = lxsj;
	}
	/**
	 * @return the fzr
	 */
	public String getFzr() {
		return fzr;
	}
	/**
	 * @param fzr要设置的 fzr
	 */
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	/**
	 * @return the fzrlxfs
	 */
	public String getFzrlxfs() {
		return fzrlxfs;
	}
	/**
	 * @param fzrlxfs要设置的 fzrlxfs
	 */
	public void setFzrlxfs(String fzrlxfs) {
		this.fzrlxfs = fzrlxfs;
	}
	/**
	 * @return the zdls
	 */
	public String getZdls() {
		return zdls;
	}
	/**
	 * @param zdls要设置的 zdls
	 */
	public void setZdls(String zdls) {
		this.zdls = zdls;
	}
	/**
	 * @return the zdlslxfs
	 */
	public String getZdlslxfs() {
		return zdlslxfs;
	}
	/**
	 * @param zdlslxfs要设置的 zdlslxfs
	 */
	public void setZdlslxfs(String zdlslxfs) {
		this.zdlslxfs = zdlslxfs;
	}
	
	
	

}
