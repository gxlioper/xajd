/**
 * @部门:学工产品事业部
 * @日期：2015-1-6 下午02:42:45 
 */  
package xsgzgl.qgzx.mrgzkh.khsq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 每日工作考核管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-6 下午02:42:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzkhKhsqForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
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
	private String yxgs;
	
	private String czyh;
	private String czyhxm;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
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
	
	public String getCzyh() {
		return czyh;
	}
	
	public void setCzyh(String czyh) {
		this.czyh = czyh;
	}
	
	public String getCzyhxm() {
		return czyhxm;
	}
	
	public void setCzyhxm(String czyhxm) {
		this.czyhxm = czyhxm;
	}
	
}
