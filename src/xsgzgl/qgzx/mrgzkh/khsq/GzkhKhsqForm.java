/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-6 ����02:42:45 
 */  
package xsgzgl.qgzx.mrgzkh.khsq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ÿ�չ������˹���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-6 ����02:42:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
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
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqidҪ���õ� sqid
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
	 * @param xnҪ���õ� xn
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
	 * @param xhҪ���õ� xh
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
	 * @param yrdwҪ���õ� yrdw
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
	 * @param gwdmҪ���õ� gwdm
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
	 * @param gsҪ���õ� gs
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
	 * @param gzrqҪ���õ� gzrq
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
	 * @param gzkssjҪ���õ� gzkssj
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
	 * @param gzjssjҪ���õ� gzjssj
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
	 * @param gzddҪ���õ� gzdd
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
	 * @param gznrҪ���õ� gznr
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
	 * @param shztҪ���õ� shzt
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
	 * @param splcҪ���õ� splc
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
	 * @param pagesҪ���õ� pages
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
	 * @param searchModelҪ���õ� searchModel
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
	 * @param exportModelҪ���õ� exportModel
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
	 * @param typeҪ���õ� type
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
	 * @param sqsjҪ���õ� sqsj
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
	 * @param bzҪ���õ� bz
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
	 * @param gwmcҪ���õ� gwmc
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
	 * @param bmmcҪ���õ� bmmc
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
	 * @param qxfwҪ���õ� qxfw
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
	 * @param cjbzҪ���õ� cjbz
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
	 * @param yxgsҪ���õ� yxgs
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
