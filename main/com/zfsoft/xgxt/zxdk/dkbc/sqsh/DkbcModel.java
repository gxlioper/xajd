/**
 * @部门:学工产品事业部
 * @日期：2014-11-12 上午09:31:56 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.sqsh;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 技术等级鉴定
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-11-12 上午09:31:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DkbcModel extends SuperAuditModel{

	private static final long serialVersionUID = -5951870429763694581L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String id;
	private String xh;
	private String xn;
	private String bysj;
	private String yjxf;
	private String yjxfmc;
	private String yjnxf;
	private String yhdm;
	private String yhmc;
	private String dkhth;
	private String dkkssj;
	private String dkjssj;
	private String dclb;
	private String dclbmc;
	private String filepath;
	private String fwnx;
	private String dwmc;
	private String dwdz;
	private String dwdh;
	private String dwyb;
	private String xfje;
	private String dkbj;
	private String dcje;
	private String hyxz;//行业性质
	private String zwmc;//职务名称
	private String shzt;
	private String splcid;
	private String sqsj;
	private String zd3;
	private String zd6;
	/*华师大个性化判断*/
	private String dklx;

	public String getHyxz() {
		return hyxz;
	}

	public void setHyxz(String hyxz) {
		this.hyxz = hyxz;
	}

	public String getZwmc() {
		return zwmc;
	}

	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}

	/**
	 * @return the dklx
	 */
	public String getDklx() {
		return dklx;
	}
	/**
	 * @param dklx要设置的 dklx
	 */
	public void setDklx(String dklx) {
		this.dklx = dklx;
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
	 * @return the zd6
	 */
	public String getZd6() {
		return zd6;
	}
	/**
	 * @param zd6要设置的 zd6
	 */
	public void setZd6(String zd6) {
		this.zd6 = zd6;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the bysj
	 */
	public String getBysj() {
		return bysj;
	}
	/**
	 * @param bysj要设置的 bysj
	 */
	public void setBysj(String bysj) {
		this.bysj = bysj;
	}
	/**
	 * @return the fwnx
	 */
	public String getFwnx() {
		return fwnx;
	}
	/**
	 * @param fwnx要设置的 fwnx
	 */
	public void setFwnx(String fwnx) {
		this.fwnx = fwnx;
	}
	/**
	 * @return the dwmc
	 */
	public String getDwmc() {
		return dwmc;
	}
	/**
	 * @param dwmc要设置的 dwmc
	 */
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	/**
	 * @return the dwdz
	 */
	public String getDwdz() {
		return dwdz;
	}
	/**
	 * @param dwdz要设置的 dwdz
	 */
	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}
	/**
	 * @return the dwdh
	 */
	public String getDwdh() {
		return dwdh;
	}
	/**
	 * @param dwdh要设置的 dwdh
	 */
	public void setDwdh(String dwdh) {
		this.dwdh = dwdh;
	}
	/**
	 * @return the xfje
	 */
	public String getXfje() {
		return xfje;
	}
	/**
	 * @param xfje要设置的 xfje
	 */
	public void setXfje(String xfje) {
		this.xfje = xfje;
	}
	/**
	 * @return the dkbj
	 */
	public String getDkbj() {
		return dkbj;
	}
	/**
	 * @param dkbj要设置的 dkbj
	 */
	public void setDkbj(String dkbj) {
		this.dkbj = dkbj;
	}
	/**
	 * @return the dcje
	 */
	public String getDcje() {
		return dcje;
	}
	/**
	 * @param dcje要设置的 dcje
	 */
	public void setDcje(String dcje) {
		this.dcje = dcje;
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
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
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
	 * @return the yjxf
	 */
	public String getYjxf() {
		return yjxf;
	}
	/**
	 * @param yjxf要设置的 yjxf
	 */
	public void setYjxf(String yjxf) {
		this.yjxf = yjxf;
	}
	/**
	 * @return the yjnxf
	 */
	public String getYjnxf() {
		return yjnxf;
	}
	/**
	 * @param yjnxf要设置的 yjnxf
	 */
	public void setYjnxf(String yjnxf) {
		this.yjnxf = yjnxf;
	}
	/**
	 * @return the yhdm
	 */
	public String getYhdm() {
		return yhdm;
	}
	/**
	 * @param yhdm要设置的 yhdm
	 */
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	/**
	 * @return the yhmc
	 */
	public String getYhmc() {
		return yhmc;
	}
	/**
	 * @param yhmc要设置的 yhmc
	 */
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	/**
	 * @return the dkhth
	 */
	public String getDkhth() {
		return dkhth;
	}
	/**
	 * @param dkhth要设置的 dkhth
	 */
	public void setDkhth(String dkhth) {
		this.dkhth = dkhth;
	}
	/**
	 * @return the dkkssj
	 */
	public String getDkkssj() {
		return dkkssj;
	}
	/**
	 * @param dkkssj要设置的 dkkssj
	 */
	public void setDkkssj(String dkkssj) {
		this.dkkssj = dkkssj;
	}
	/**
	 * @return the dkjssj
	 */
	public String getDkjssj() {
		return dkjssj;
	}
	/**
	 * @param dkjssj要设置的 dkjssj
	 */
	public void setDkjssj(String dkjssj) {
		this.dkjssj = dkjssj;
	}
	/**
	 * @return the dclb
	 */
	public String getDclb() {
		return dclb;
	}
	/**
	 * @param dclb要设置的 dclb
	 */
	public void setDclb(String dclb) {
		this.dclb = dclb;
	}
	/**
	 * @return the dclbmc
	 */
	public String getDclbmc() {
		return dclbmc;
	}
	/**
	 * @param dclbmc要设置的 dclbmc
	 */
	public void setDclbmc(String dclbmc) {
		this.dclbmc = dclbmc;
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
	 * @return the dwyb
	 */
	public String getDwyb() {
		return dwyb;
	}
	/**
	 * @param dwyb要设置的 dwyb
	 */
	public void setDwyb(String dwyb) {
		this.dwyb = dwyb;
	}
	/**
	 * @return the yjxfmc
	 */
	public String getYjxfmc() {
		return yjxfmc;
	}
	/**
	 * @param yjxfmc要设置的 yjxfmc
	 */
	public void setYjxfmc(String yjxfmc) {
		this.yjxfmc = yjxfmc;
	}
	
	
}
