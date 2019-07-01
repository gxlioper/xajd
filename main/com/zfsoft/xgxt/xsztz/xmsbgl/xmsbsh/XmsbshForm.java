/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:38:26 
 */  
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsbsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-26 下午02:38:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmsbshForm extends ActionForm{
	private String xmdm;
	private String xn;
	private String xq;
	private String xmmc;
	private String xmjbdm;
	private String sbbmdm;
	private String sskmdm;//所属科目代码
	private String kcyrs;//可参与人数
	private String xmkssj;
	private String xmsbsj;
	private String jcxf;
	private String sbr;
	private String lxdh;
	private String sfsljx;//是否设立奖项：0|否，1|是
	private String xmms;//项目描述
	private String dkfyj;//得/扣分依据
	private String cyyq;//参与要求
	private String czr;//操作人
	private String splc;
	private String shzt;
	private String type;
	
	private String ywid;
	private String shsj;
	private String shr;
	private String shyj;
	private String shlc;
	private String gwid;
	private String shztmc;
	private String shid;
	private String thgw;//岗位退回
	private String shjg;
	
	private String[] id;
	private String[] gwids;

 // 分页
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdm要设置的 xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
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
	 * @return the xmjbdm
	 */
	public String getXmjbdm() {
		return xmjbdm;
	}
	/**
	 * @param xmjbdm要设置的 xmjbdm
	 */
	public void setXmjbdm(String xmjbdm) {
		this.xmjbdm = xmjbdm;
	}
	/**
	 * @return the sbbmdm
	 */
	public String getSbbmdm() {
		return sbbmdm;
	}
	/**
	 * @param sbbmdm要设置的 sbbmdm
	 */
	public void setSbbmdm(String sbbmdm) {
		this.sbbmdm = sbbmdm;
	}
	/**
	 * @return the sskmdm
	 */
	public String getSskmdm() {
		return sskmdm;
	}
	/**
	 * @param sskmdm要设置的 sskmdm
	 */
	public void setSskmdm(String sskmdm) {
		this.sskmdm = sskmdm;
	}
	/**
	 * @return the kcyrs
	 */
	public String getKcyrs() {
		return kcyrs;
	}
	/**
	 * @param kcyrs要设置的 kcyrs
	 */
	public void setKcyrs(String kcyrs) {
		this.kcyrs = kcyrs;
	}
	/**
	 * @return the xmkssj
	 */
	public String getXmkssj() {
		return xmkssj;
	}
	/**
	 * @param xmkssj要设置的 xmkssj
	 */
	public void setXmkssj(String xmkssj) {
		this.xmkssj = xmkssj;
	}
	/**
	 * @return the xmsbsj
	 */
	public String getXmsbsj() {
		return xmsbsj;
	}
	/**
	 * @param xmsbsj要设置的 xmsbsj
	 */
	public void setXmsbsj(String xmsbsj) {
		this.xmsbsj = xmsbsj;
	}
	/**
	 * @return the jcxf
	 */
	public String getJcxf() {
		return jcxf;
	}
	/**
	 * @param jcxf要设置的 jcxf
	 */
	public void setJcxf(String jcxf) {
		this.jcxf = jcxf;
	}
	/**
	 * @return the sbr
	 */
	public String getSbr() {
		return sbr;
	}
	/**
	 * @param sbr要设置的 sbr
	 */
	public void setSbr(String sbr) {
		this.sbr = sbr;
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
	 * @return the sfsljx
	 */
	public String getSfsljx() {
		return sfsljx;
	}
	/**
	 * @param sfsljx要设置的 sfsljx
	 */
	public void setSfsljx(String sfsljx) {
		this.sfsljx = sfsljx;
	}
	/**
	 * @return the xmms
	 */
	public String getXmms() {
		return xmms;
	}
	/**
	 * @param xmms要设置的 xmms
	 */
	public void setXmms(String xmms) {
		this.xmms = xmms;
	}
	/**
	 * @return the dkfyj
	 */
	public String getDkfyj() {
		return dkfyj;
	}
	/**
	 * @param dkfyj要设置的 dkfyj
	 */
	public void setDkfyj(String dkfyj) {
		this.dkfyj = dkfyj;
	}
	/**
	 * @return the cyyq
	 */
	public String getCyyq() {
		return cyyq;
	}
	/**
	 * @param cyyq要设置的 cyyq
	 */
	public void setCyyq(String cyyq) {
		this.cyyq = cyyq;
	}
	/**
	 * @return the czr
	 */
	public String getCzr() {
		return czr;
	}
	/**
	 * @param czr要设置的 czr
	 */
	public void setCzr(String czr) {
		this.czr = czr;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
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
	 * @return the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @param ywid要设置的 ywid
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	/**
	 * @return the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @param shsj要设置的 shsj
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	/**
	 * @return the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @param shr要设置的 shr
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyj要设置的 shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlc要设置的 shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwid要设置的 gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @return the shztmc
	 */
	public String getShztmc() {
		return shztmc;
	}
	/**
	 * @param shztmc要设置的 shztmc
	 */
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shid要设置的 shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgw要设置的 thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjg要设置的 shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String[] id) {
		this.id = id;
	}
	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @param gwids要设置的 gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
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
	
    
    

}
