/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-26 ����09:40:36 
 */  
package xsgzgl.qgzx.zjdx.tjcx;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-12-26 ����09:40:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjcxForm extends ActionForm {
	private String id;
	private String nd;
	private String bmlb;
	private String yf;
	private String xh;
	private String xm;
	private String ffndyf;
	private String yrdwdm;
	private String yrdwmc;
	private String xqdm;
	private String gwlbdm;
	private String gwxzdm;
	private String gss;
	private String bcje;
	private String gznr;
	private String bz;
	private String sftj;
	private String lrr;
	private String lrsj;
	private String type;
	private FormFile drmb;
	private String filepath;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
	private static final long serialVersionUID = 1L;
	/**
	 * @return the yrdwmc
	 */
	public String getYrdwmc() {
		return yrdwmc;
	}
	/**
	 * @param yrdwmcҪ���õ� yrdwmc
	 */
	public void setYrdwmc(String yrdwmc) {
		this.yrdwmc = yrdwmc;
	}
	/**
	 * @return the bmlb
	 */
	public String getBmlb() {
		return bmlb;
	}
	/**
	 * @param bmlbҪ���õ� bmlb
	 */
	public void setBmlb(String bmlb) {
		this.bmlb = bmlb;
	}
	
	/**
	 * @return the nd
	 */
	public String getNd() {
		return nd;
	}
	/**
	 * @param ndҪ���õ� nd
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}
	/**
	 * @return the yf
	 */
	public String getYf() {
		return yf;
	}
	/**
	 * @param yfҪ���õ� yf
	 */
	public void setYf(String yf) {
		this.yf = yf;
	}
	
	/**
	 * @return the drmb
	 */
	public FormFile getDrmb() {
		return drmb;
	}
	/**
	 * @param drmbҪ���õ� drmb
	 */
	public void setDrmb(FormFile drmb) {
		this.drmb = drmb;
	}
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepathҪ���õ� filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
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
	 * @param xhҪ���õ� xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the ffndyf
	 */
	public String getFfndyf() {
		return ffndyf;
	}
	/**
	 * @param ffndyfҪ���õ� ffndyf
	 */
	public void setFfndyf(String ffndyf) {
		this.ffndyf = ffndyf;
	}
	/**
	 * @return the yrdwdm
	 */
	public String getYrdwdm() {
		return yrdwdm;
	}
	/**
	 * @param yrdwdmҪ���õ� yrdwdm
	 */
	public void setYrdwdm(String yrdwdm) {
		this.yrdwdm = yrdwdm;
	}
	/**
	 * @return the xqdm
	 */
	public String getXqdm() {
		return xqdm;
	}
	/**
	 * @param xqdmҪ���õ� xqdm
	 */
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	/**
	 * @return the gwlbdm
	 */
	public String getGwlbdm() {
		return gwlbdm;
	}
	/**
	 * @param gwlbdmҪ���õ� gwlbdm
	 */
	public void setGwlbdm(String gwlbdm) {
		this.gwlbdm = gwlbdm;
	}
	/**
	 * @return the gwxzdm
	 */
	public String getGwxzdm() {
		return gwxzdm;
	}
	/**
	 * @param gwxzdmҪ���õ� gwxzdm
	 */
	public void setGwxzdm(String gwxzdm) {
		this.gwxzdm = gwxzdm;
	}
	/**
	 * @return the gss
	 */
	public String getGss() {
		return gss;
	}
	/**
	 * @param gssҪ���õ� gss
	 */
	public void setGss(String gss) {
		this.gss = gss;
	}
	/**
	 * @return the bcje
	 */
	public String getBcje() {
		return bcje;
	}
	/**
	 * @param bcjeҪ���õ� bcje
	 */
	public void setBcje(String bcje) {
		this.bcje = bcje;
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
	 * @return the sftj
	 */
	public String getSftj() {
		return sftj;
	}
	/**
	 * @param sftjҪ���õ� sftj
	 */
	public void setSftj(String sftj) {
		this.sftj = sftj;
	}
	/**
	 * @return the lrr
	 */
	public String getLrr() {
		return lrr;
	}
	/**
	 * @param lrrҪ���õ� lrr
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	/**
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @param lrsjҪ���õ� lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
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

}
