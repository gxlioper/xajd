/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-17 ����02:52:23 
 */  
package com.zfsoft.xgxt.zjly.zhf.sq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �ۺϷ�(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-17 ����02:52:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfForm extends ActionForm{
	private static final long serialVersionUID = 4791318853675293550L;
	
	private String id;
	private String xh;
	private String jfxmdm;
	private String xmmkdm;
	private String sxsm;
	private String shsj;
	private String shr;
	private String cysj;
	private String shzt;
	private String lrsj;
	private String lrr;
	private String fj;
	private String fjmc;
	private String cxzd;
	private String jfxmdms[];
	private String xmmkdms[];
	private String sxsms[];
	private String fjs[];
	private String xhs[];
	private String cysjs[];
	private String jfxmmc;
	private String xmmkmc;
	private String fs;
	private String khyd;
	private String thyj;
	private String filepath;
	private String defaultimagepath;//Ĭ��ͼƬ·��
	private String[] xhArr;
	private String lb; //�������ֲ˵�����˻����޸�
	
	/**
	 * @return the xhArr
	 */
	public String[] getXhArr() {
		return xhArr;
	}
	/**
	 * @param xhArrҪ���õ� xhArr
	 */
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}
	/**
	 * @return the defaultimagepath
	 */
	public String getDefaultimagepath() {
		return defaultimagepath;
	}
	/**
	 * @param defaultimagepathҪ���õ� defaultimagepath
	 */
	public void setDefaultimagepath(String defaultimagepath) {
		this.defaultimagepath = defaultimagepath;
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
	 * @return the thyj
	 */
	public String getThyj() {
		return thyj;
	}
	/**
	 * @param thyjҪ���õ� thyj
	 */
	public void setThyj(String thyj) {
		this.thyj = thyj;
	}
	private String type;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
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
	 * @return the jfxmdm
	 */
	public String getJfxmdm() {
		return jfxmdm;
	}
	/**
	 * @param jfxmdmҪ���õ� jfxmdm
	 */
	public void setJfxmdm(String jfxmdm) {
		this.jfxmdm = jfxmdm;
	}
	/**
	 * @return the xmmkdm
	 */
	public String getXmmkdm() {
		return xmmkdm;
	}
	/**
	 * @param xmmkdmҪ���õ� xmmkdm
	 */
	public void setXmmkdm(String xmmkdm) {
		this.xmmkdm = xmmkdm;
	}
	/**
	 * @return the sxsm
	 */
	public String getSxsm() {
		return sxsm;
	}
	/**
	 * @param sxsmҪ���õ� sxsm
	 */
	public void setSxsm(String sxsm) {
		this.sxsm = sxsm;
	}
	/**
	 * @return the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @param shsjҪ���õ� shsj
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
	 * @param shrҪ���õ� shr
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @return the cysj
	 */
	public String getCysj() {
		return cysj;
	}
	/**
	 * @param cysjҪ���õ� cysj
	 */
	public void setCysj(String cysj) {
		this.cysj = cysj;
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
	 * @return the fj
	 */
	public String getFj() {
		return fj;
	}
	/**
	 * @param fjҪ���õ� fj
	 */
	public void setFj(String fj) {
		this.fj = fj;
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
	 * @return the cxzd
	 */
	public String getCxzd() {
		return cxzd;
	}
	/**
	 * @param cxzdҪ���õ� cxzd
	 */
	public void setCxzd(String cxzd) {
		this.cxzd = cxzd;
	}
	/**
	 * @return the jfxmdms
	 */
	public String[] getJfxmdms() {
		return jfxmdms;
	}
	/**
	 * @param jfxmdmsҪ���õ� jfxmdms
	 */
	public void setJfxmdms(String[] jfxmdms) {
		this.jfxmdms = jfxmdms;
	}
	/**
	 * @return the xmmkdms
	 */
	public String[] getXmmkdms() {
		return xmmkdms;
	}
	/**
	 * @param xmmkdmsҪ���õ� xmmkdms
	 */
	public void setXmmkdms(String[] xmmkdms) {
		this.xmmkdms = xmmkdms;
	}
	
	/**
	 * @return the fjs
	 */
	public String[] getFjs() {
		return fjs;
	}
	/**
	 * @param fjsҪ���õ� fjs
	 */
	public void setFjs(String[] fjs) {
		this.fjs = fjs;
	}
	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @param xhsҪ���õ� xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	/**
	 * @return the sxsms
	 */
	public String[] getSxsms() {
		return sxsms;
	}
	/**
	 * @param sxsmsҪ���õ� sxsms
	 */
	public void setSxsms(String[] sxsms) {
		this.sxsms = sxsms;
	}
	/**
	 * @return the cysjs
	 */
	public String[] getCysjs() {
		return cysjs;
	}
	/**
	 * @param cysjsҪ���õ� cysjs
	 */
	public void setCysjs(String[] cysjs) {
		this.cysjs = cysjs;
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
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * @param fjmcҪ���õ� fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	/**
	 * @return the jfxmmc
	 */
	public String getJfxmmc() {
		return jfxmmc;
	}
	/**
	 * @param jfxmmcҪ���õ� jfxmmc
	 */
	public void setJfxmmc(String jfxmmc) {
		this.jfxmmc = jfxmmc;
	}
	/**
	 * @return the xmmkmc
	 */
	public String getXmmkmc() {
		return xmmkmc;
	}
	/**
	 * @param xmmkmcҪ���õ� xmmkmc
	 */
	public void setXmmkmc(String xmmkmc) {
		this.xmmkmc = xmmkmc;
	}
	/**
	 * @return the fs
	 */
	public String getFs() {
		return fs;
	}
	/**
	 * @param fsҪ���õ� fs
	 */
	public void setFs(String fs) {
		this.fs = fs;
	}
	/**
	 * @return the khyd
	 */
	public String getKhyd() {
		return khyd;
	}
	/**
	 * @param khydҪ���õ� khyd
	 */
	public void setKhyd(String khyd) {
		this.khyd = khyd;
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
	 * @return the lb
	 */
	public String getLb() {
		return lb;
	}
	/**
	 * @param lbҪ���õ� lb
	 */
	public void setLb(String lb) {
		this.lb = lb;
	}
	
	
}
