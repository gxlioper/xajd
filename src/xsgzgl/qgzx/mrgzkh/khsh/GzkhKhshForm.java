/**
 * @部门:学工产品事业部
 * @日期：2015-1-7 下午04:10:46 
 */  
package xsgzgl.qgzx.mrgzkh.khsh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 每日工作考核管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-7 下午04:10:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzkhKhshForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	// 分页
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	
	private String[] primarykey_checkVal;// CheckBox
	
	
	private String sqid;
	private String xn;
	private String xh;
	private String sqsj;
	private String yrdw;
	private String gwdm;
	private String gs;
	private String gzrq;
	private String gzkssj;
	private String gzjssj;
	private String gzdd;
	private String gznr;
	private String shzt;
	private String splc;
	private String bz;
	private String gwmc;
	private String bmmc;
	private String qxfw;
	private String cjbz;
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
	private String yxgs;
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] yxgss;
	private String[] gss;
	private String czyh;

	public String getCzyh() {
		return czyh;
	}

	public void setCzyh(String czyh) {
		this.czyh = czyh;
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
	 * @return the yrdw
	 */
	public String getYrdw() {
		return yrdw;
	}
	/**
	 * @param yrdw要设置的 yrdw
	 */
	public void setYrdw(String yrdw) {
		this.yrdw = yrdw;
	}
	/**
	 * @return the gwdm
	 */
	public String getGwdm() {
		return gwdm;
	}
	/**
	 * @param gwdm要设置的 gwdm
	 */
	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}
	/**
	 * @return the gs
	 */
	public String getGs() {
		return gs;
	}
	/**
	 * @param gs要设置的 gs
	 */
	public void setGs(String gs) {
		this.gs = gs;
	}
	/**
	 * @return the gzrq
	 */
	public String getGzrq() {
		return gzrq;
	}
	/**
	 * @param gzrq要设置的 gzrq
	 */
	public void setGzrq(String gzrq) {
		this.gzrq = gzrq;
	}
	/**
	 * @return the gzkssj
	 */
	public String getGzkssj() {
		return gzkssj;
	}
	/**
	 * @param gzkssj要设置的 gzkssj
	 */
	public void setGzkssj(String gzkssj) {
		this.gzkssj = gzkssj;
	}
	/**
	 * @return the gzjssj
	 */
	public String getGzjssj() {
		return gzjssj;
	}
	/**
	 * @param gzjssj要设置的 gzjssj
	 */
	public void setGzjssj(String gzjssj) {
		this.gzjssj = gzjssj;
	}
	/**
	 * @return the gzdd
	 */
	public String getGzdd() {
		return gzdd;
	}
	/**
	 * @param gzdd要设置的 gzdd
	 */
	public void setGzdd(String gzdd) {
		this.gzdd = gzdd;
	}
	/**
	 * @return the gznr
	 */
	public String getGznr() {
		return gznr;
	}
	/**
	 * @param gznr要设置的 gznr
	 */
	public void setGznr(String gznr) {
		this.gznr = gznr;
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
	 * @return the gwmc
	 */
	public String getGwmc() {
		return gwmc;
	}
	/**
	 * @param gwmc要设置的 gwmc
	 */
	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}
	/**
	 * @return the bmmc
	 */
	public String getBmmc() {
		return bmmc;
	}
	/**
	 * @param bmmc要设置的 bmmc
	 */
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
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
	 * @return the yxgs
	 */
	public String getYxgs() {
		return yxgs;
	}
	/**
	 * @param yxgs要设置的 yxgs
	 */
	public void setYxgs(String yxgs) {
		this.yxgs = yxgs;
	}
	/**
	 * @return the yxgss
	 */
	public String[] getYxgss() {
		return yxgss;
	}
	/**
	 * @param yxgss要设置的 yxgss
	 */
	public void setYxgss(String[] yxgss) {
		this.yxgss = yxgss;
	}
	/**
	 * @return the qxfw
	 */
	public String getQxfw() {
		return qxfw;
	}
	/**
	 * @param qxfw要设置的 qxfw
	 */
	public void setQxfw(String qxfw) {
		this.qxfw = qxfw;
	}
	/**
	 * @return the cjbz
	 */
	public String getCjbz() {
		return cjbz;
	}
	/**
	 * @param cjbz要设置的 cjbz
	 */
	public void setCjbz(String cjbz) {
		this.cjbz = cjbz;
	}
	/**
	 * @return the gss
	 */
	public String[] getGss() {
		return gss;
	}
	/**
	 * @param gss要设置的 gss
	 */
	public void setGss(String[] gss) {
		this.gss = gss;
	}
	
	
	
	
	
	

}
