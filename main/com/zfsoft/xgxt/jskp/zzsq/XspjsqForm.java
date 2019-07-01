/**
 * @部门:学工产品(1)部
 * @日期：2018-4-11 上午09:09:59 
 */  
package com.zfsoft.xgxt.jskp.zzsq;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学习评价管理模块
 * @类功能描述: 自主申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-11 上午09:09:20 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XspjsqForm extends ActionForm{
	private static final long serialVersionUID = -4562302399219564190L;
	private Pages pages = new Pages();//分页
	private SearchModel searchModel = new SearchModel();//高级查询
	private ExportModel exportModel = new ExportModel();//自定义导出
	private String sqid;//申请ID
	private String xh;//学号
	private String xn;//学年
	private String fz;//分值
	private String dxqdm;//短学期代码
	private String xmmc;//项目名称
	private String zdbmdm;//指导部门代码
	private String xmlbdm;//项目类别代码
	private String cysj;//参与时间
	private String fzrxm;//负责人姓名
	private String fzrlxfs;//负责人联系方式
	private String zdlsxm;//指导老师姓名
	private String zdlslxfs;//指导老师联系方式
	private String fjid;//附件id
	private String sqly;//申请理由
	private String sjlrr;//数据录入人
	private String sjlrsj;//数据录入时间
	private String shzt;//审核状态
	private String splcid;//审批流程ID
	private String type;//类型
	private String saveFlag;//保存类型(保存时的参数)
	private FormFile drmb;/*导入模板*/
	private String exclePath;/*导入模板路径*/
	private String filepath;/*文件 路径*/
	
	/**
	 * @return the drmb
	 */
	public FormFile getDrmb() {
		return drmb;
	}
	/**
	 * @param drmb要设置的 drmb
	 */
	public void setDrmb(FormFile drmb) {
		this.drmb = drmb;
	}
	/**
	 * @return the exclePath
	 */
	public String getExclePath() {
		return exclePath;
	}
	/**
	 * @param exclePath要设置的 exclePath
	 */
	public void setExclePath(String exclePath) {
		this.exclePath = exclePath;
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
	 * @return the sjlrsj
	 */
	public String getSjlrsj() {
		return sjlrsj;
	}
	/**
	 * @param sjlrsj要设置的 sjlrsj
	 */
	public void setSjlrsj(String sjlrsj) {
		this.sjlrsj = sjlrsj;
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
	 * @return the saveFlag
	 */
	public String getSaveFlag() {
		return saveFlag;
	}
	/**
	 * @param saveFlag要设置的 saveFlag
	 */
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
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
	 * @return the fz
	 */
	public String getFz() {
		return fz;
	}
	/**
	 * @param fz要设置的 fz
	 */
	public void setFz(String fz) {
		this.fz = fz;
	}
	/**
	 * @return the dxqdm
	 */
	public String getDxqdm() {
		return dxqdm;
	}
	/**
	 * @param dxqdm要设置的 dxqdm
	 */
	public void setDxqdm(String dxqdm) {
		this.dxqdm = dxqdm;
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
	 * @return the zdbmdm
	 */
	public String getZdbmdm() {
		return zdbmdm;
	}
	/**
	 * @param zdbmdm要设置的 zdbmdm
	 */
	public void setZdbmdm(String zdbmdm) {
		this.zdbmdm = zdbmdm;
	}
	/**
	 * @return the xmlbdm
	 */
	public String getXmlbdm() {
		return xmlbdm;
	}
	/**
	 * @param xmlbdm要设置的 xmlbdm
	 */
	public void setXmlbdm(String xmlbdm) {
		this.xmlbdm = xmlbdm;
	}
	/**
	 * @return the cysj
	 */
	public String getCysj() {
		return cysj;
	}
	/**
	 * @param cysj要设置的 cysj
	 */
	public void setCysj(String cysj) {
		this.cysj = cysj;
	}
	/**
	 * @return the fzrxm
	 */
	public String getFzrxm() {
		return fzrxm;
	}
	/**
	 * @param fzrxm要设置的 fzrxm
	 */
	public void setFzrxm(String fzrxm) {
		this.fzrxm = fzrxm;
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
	 * @return the zdlsxm
	 */
	public String getZdlsxm() {
		return zdlsxm;
	}
	/**
	 * @param zdlsxm要设置的 zdlsxm
	 */
	public void setZdlsxm(String zdlsxm) {
		this.zdlsxm = zdlsxm;
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
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqly要设置的 sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @return the sjlrr
	 */
	public String getSjlrr() {
		return sjlrr;
	}
	/**
	 * @param sjlrr要设置的 sjlrr
	 */
	public void setSjlrr(String sjlrr) {
		this.sjlrr = sjlrr;
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
}
