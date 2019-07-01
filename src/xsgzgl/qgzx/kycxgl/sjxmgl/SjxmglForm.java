/**
 * @部门:学工产品事业部
 * @日期：2015-11-30 上午11:38:01 
 */  
package  xsgzgl.qgzx.kycxgl.sjxmgl;


import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-11-30 上午11:38:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SjxmglForm extends ActionForm{
	
	private String xmid;
	private String xmbh;//项目编号
	private String xmmc;
	private String xmsxdm;//项目属性代码
	private String xh;//项目负责人学号
	private String xmssdw;//项目所属单位
	private String jhcyrs;//计划参与人数
	private String gwlb;
	private String kssj;
	private String jssj;
	private String xm;
	private String nj;
	private String xymc;
	private String sjhm;
	private String jfys;//经费预算
	private String pzjf;
	private String sqzy;
	private String lxyj;
	private String gjwt;
	private String yjfa;
	private String yjjh;
	private String cgxsyqjg;
	private String shzt;
	private String splcid;
	private String  ybxjf;    
	private String  zjjf;     
	private String  xmhjzjf ; 
	private String sbjfhj;
	private String[] xhs;
	private String[] zghs;
	
	
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

	
	private String type;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
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
	 * @return the xmbh
	 */
	public String getXmbh() {
		return xmbh;
	}
	/**
	 * @param xmbh要设置的 xmbh
	 */
	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
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
	 * @return the xmsxdm
	 */
	public String getXmsxdm() {
		return xmsxdm;
	}
	/**
	 * @param xmsxdm要设置的 xmsxdm
	 */
	public void setXmsxdm(String xmsxdm) {
		this.xmsxdm = xmsxdm;
	}
	/**
	 * @return the xmfzrxh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xmfzrxh要设置的 xmfzrxh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the xmssdw
	 */
	public String getXmssdw() {
		return xmssdw;
	}
	/**
	 * @param xmssdw要设置的 xmssdw
	 */
	public void setXmssdw(String xmssdw) {
		this.xmssdw = xmssdw;
	}
	
	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}
	/**
	 * @param kssj要设置的 kssj
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}
	/**
	 * @param jssj要设置的 jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
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
	 * @return the pzjf
	 */
	public String getPzjf() {
		return pzjf;
	}
	/**
	 * @param pzjf要设置的 pzjf
	 */
	public void setPzjf(String pzjf) {
		this.pzjf = pzjf;
	}
	/**
	 * @return the sqzy
	 */
	public String getSqzy() {
		return sqzy;
	}
	/**
	 * @param sqzy要设置的 sqzy
	 */
	public void setSqzy(String sqzy) {
		this.sqzy = sqzy;
	}
	/**
	 * @return the lxyj
	 */
	public String getLxyj() {
		return lxyj;
	}
	/**
	 * @param lxyj要设置的 lxyj
	 */
	public void setLxyj(String lxyj) {
		this.lxyj = lxyj;
	}
	/**
	 * @return the gjwt
	 */
	public String getGjwt() {
		return gjwt;
	}
	/**
	 * @param gjwt要设置的 gjwt
	 */
	public void setGjwt(String gjwt) {
		this.gjwt = gjwt;
	}
	/**
	 * @return the yjfa
	 */
	public String getYjfa() {
		return yjfa;
	}
	/**
	 * @param yjfa要设置的 yjfa
	 */
	public void setYjfa(String yjfa) {
		this.yjfa = yjfa;
	}
	/**
	 * @return the yjjh
	 */
	public String getYjjh() {
		return yjjh;
	}
	/**
	 * @param yjjh要设置的 yjjh
	 */
	public void setYjjh(String yjjh) {
		this.yjjh = yjjh;
	}
	/**
	 * @return the cgxsjyqjg
	 */
	public String getCgxsyqjg() {
		return cgxsyqjg;
	}
	/**
	 * @param cgxsjyqjg要设置的 cgxsjyqjg
	 */
	public void setCgxsyqjg(String cgxsyqjg) {
		this.cgxsyqjg = cgxsyqjg;
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
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @param xhs要设置的 xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	/**
	 * @return the zghs
	 */
	public String[] getZghs() {
		return zghs;
	}
	/**
	 * @param zghs要设置的 zghs
	 */
	public void setZghs(String[] zghs) {
		this.zghs = zghs;
	}
	/**
	 * @return the ybxjf
	 */
	public String getYbxjf() {
		return ybxjf;
	}
	/**
	 * @param ybxjf要设置的 ybxjf
	 */
	public void setYbxjf(String ybxjf) {
		this.ybxjf = ybxjf;
	}
	/**
	 * @return the zjjf
	 */
	public String getZjjf() {
		return zjjf;
	}
	/**
	 * @param zjjf要设置的 zjjf
	 */
	public void setZjjf(String zjjf) {
		this.zjjf = zjjf;
	}
	/**
	 * @return the xmhjzjf
	 */
	public String getXmhjzjf() {
		return xmhjzjf;
	}
	/**
	 * @param xmhjzjf要设置的 xmhjzjf
	 */
	public void setXmhjzjf(String xmhjzjf) {
		this.xmhjzjf = xmhjzjf;
	}
	/**
	 * @return the sbjfhj
	 */
	public String getSbjfhj() {
		return sbjfhj;
	}
	/**
	 * @param sbjfhj要设置的 sbjfhj
	 */
	public void setSbjfhj(String sbjfhj) {
		this.sbjfhj = sbjfhj;
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
	 * @return the jhcyrs
	 */
	public String getJhcyrs() {
		return jhcyrs;
	}
	/**
	 * @param jhcyrs要设置的 jhcyrs
	 */
	public void setJhcyrs(String jhcyrs) {
		this.jhcyrs = jhcyrs;
	}
	/**
	 * @return the gwlb
	 */
	public String getGwlb() {
		return gwlb;
	}
	/**
	 * @param gwlb要设置的 gwlb
	 */
	public void setGwlb(String gwlb) {
		this.gwlb = gwlb;
	}
	/**
	 * @return the jfys
	 */
	public String getJfys() {
		return jfys;
	}
	/**
	 * @param jfys要设置的 jfys
	 */
	public void setJfys(String jfys) {
		this.jfys = jfys;
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
	 * @return the sjhm
	 */
	public String getSjhm() {
		return sjhm;
	}
	/**
	 * @param sjhm要设置的 sjhm
	 */
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	
	
	

}
