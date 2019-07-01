package xsgzgl.qgzx.mrgzkh.khjg;

import org.apache.struts.action.ActionForm;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class GzkhKhjgForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String xh;
	private String xn;
	private String sqsj;
	private String yrdw;
	private String gwdm;
	private String gs;
	private String gzrq;
	private String gzkssj;
	private String gzjssj;
	private String gzdd;
	private String gznr;
	private String sjly;
	private String sqid;
	private String bz;
	private String czyh;
	private String cjsj;
	private String cjbz;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	private String bmmc;
	private String gwmc;
	private String czyhxm;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCzyh() {
		return czyh;
	}
	public void setCzyh(String czyh) {
		this.czyh = czyh;
	}
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public String getGwmc() {
		return gwmc;
	}
	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}
	public String getCzyhxm() {
		return czyhxm;
	}
	public void setCzyhxm(String czyhxm) {
		this.czyhxm = czyhxm;
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
	
	
}
