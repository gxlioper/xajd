/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-7-12 ����06:39:13 
 */  
package com.zfsoft.xgxt.dekt.qnzyhd;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-7-12 ����06:39:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QnzyhdForm extends ActionForm{	
	private static final long serialVersionUID = 1L;
	
	private String hdid;
	private String hdmc;
	private String fwlx;
	private String hddd;
	private String fwdx;
	private String xdrs;
	private String hdkssj;
	private String hdjssj;
	private String hdfzr;
	private String hdfbr;
	private String hdfzrlxfs;
	private String zzbm;
	private String fjpath;
	private String hdxq;
	private String fbzt;
	private String shzt;
	private Pages pages = new Pages();
	private String mhcx;
	private String type;
	private String oldPath;
	private String lastPath;
	private String gsshzt;
	private String bmjzsj;
	private String jbfwgs;
	private String[] xydms;
	private String shyj;
	private String[] ids;
	
	private FormFile file;
	
	private String accept;
	
	private String maxsize;
	
	private String maxcount;
	
	private SearchModel searchModel = new SearchModel();
	/**
	 * @return the hdid
	 */
	public String getHdid() {
		return hdid;
	}
	/**
	 * @param hdidҪ���õ� hdid
	 */
	public void setHdid(String hdid) {
		this.hdid = hdid;
	}
	/**
	 * @return the hdmc
	 */
	public String getHdmc() {
		return hdmc;
	}
	/**
	 * @param hdmcҪ���õ� hdmc
	 */
	public void setHdmc(String hdmc) {
		this.hdmc = hdmc;
	}
	/**
	 * @return the fwlx
	 */
	public String getFwlx() {
		return fwlx;
	}
	/**
	 * @param fwlxҪ���õ� fwlx
	 */
	public void setFwlx(String fwlx) {
		this.fwlx = fwlx;
	}
	/**
	 * @return the hddd
	 */
	public String getHddd() {
		return hddd;
	}
	/**
	 * @param hdddҪ���õ� hddd
	 */
	public void setHddd(String hddd) {
		this.hddd = hddd;
	}
	/**
	 * @return the fwdx
	 */
	public String getFwdx() {
		return fwdx;
	}
	/**
	 * @param fwdxҪ���õ� fwdx
	 */
	public void setFwdx(String fwdx) {
		this.fwdx = fwdx;
	}
	/**
	 * @return the xdrs
	 */
	public String getXdrs() {
		return xdrs;
	}
	/**
	 * @param xdrsҪ���õ� xdrs
	 */
	public void setXdrs(String xdrs) {
		this.xdrs = xdrs;
	}
	/**
	 * @return the hdkssj
	 */
	public String getHdkssj() {
		return hdkssj;
	}
	/**
	 * @param hdkssjҪ���õ� hdkssj
	 */
	public void setHdkssj(String hdkssj) {
		this.hdkssj = hdkssj;
	}
	/**
	 * @return the hdjssj
	 */
	public String getHdjssj() {
		return hdjssj;
	}
	/**
	 * @param hdjssjҪ���õ� hdjssj
	 */
	public void setHdjssj(String hdjssj) {
		this.hdjssj = hdjssj;
	}
	/**
	 * @return the hdfzr
	 */
	public String getHdfzr() {
		return hdfzr;
	}
	/**
	 * @param hdfzrҪ���õ� hdfzr
	 */
	public void setHdfzr(String hdfzr) {
		this.hdfzr = hdfzr;
	}
	/**
	 * @return the hdfbr
	 */
	public String getHdfbr() {
		return hdfbr;
	}
	/**
	 * @param hdfbrҪ���õ� hdfbr
	 */
	public void setHdfbr(String hdfbr) {
		this.hdfbr = hdfbr;
	}
	/**
	 * @return the hdfzrlxfs
	 */
	public String getHdfzrlxfs() {
		return hdfzrlxfs;
	}
	/**
	 * @param hdfzrlxfsҪ���õ� hdfzrlxfs
	 */
	public void setHdfzrlxfs(String hdfzrlxfs) {
		this.hdfzrlxfs = hdfzrlxfs;
	}
	/**
	 * @return the zzbm
	 */
	public String getZzbm() {
		return zzbm;
	}
	/**
	 * @param zzbmҪ���õ� zzbm
	 */
	public void setZzbm(String zzbm) {
		this.zzbm = zzbm;
	}
	/**
	 * @return the fjpath
	 */
	public String getFjpath() {
		return fjpath;
	}
	/**
	 * @param fjpathҪ���õ� fjpath
	 */
	public void setFjpath(String fjpath) {
		this.fjpath = fjpath;
	}
	/**
	 * @return the hdxq
	 */
	public String getHdxq() {
		return hdxq;
	}
	/**
	 * @param hdxqҪ���õ� hdxq
	 */
	public void setHdxq(String hdxq) {
		this.hdxq = hdxq;
	}
	/**
	 * @return the fbzt
	 */
	public String getFbzt() {
		return fbzt;
	}
	/**
	 * @param fbztҪ���õ� fbzt
	 */
	public void setFbzt(String fbzt) {
		this.fbzt = fbzt;
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
	 * @return the mhcx
	 */
	public String getMhcx() {
		return mhcx;
	}
	/**
	 * @param mhcxҪ���õ� mhcx
	 */
	public void setMhcx(String mhcx) {
		this.mhcx = mhcx;
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
	 * @return the file
	 */
	public FormFile getFile() {
		return file;
	}
	/**
	 * @param fileҪ���õ� file
	 */
	public void setFile(FormFile file) {
		this.file = file;
	}
	/**
	 * @return the accept
	 */
	public String getAccept() {
		return accept;
	}
	/**
	 * @param acceptҪ���õ� accept
	 */
	public void setAccept(String accept) {
		this.accept = accept;
	}
	/**
	 * @return the maxsize
	 */
	public String getMaxsize() {
		return maxsize;
	}
	/**
	 * @param maxsizeҪ���õ� maxsize
	 */
	public void setMaxsize(String maxsize) {
		this.maxsize = maxsize;
	}
	/**
	 * @return the maxcount
	 */
	public String getMaxcount() {
		return maxcount;
	}
	/**
	 * @param maxcountҪ���õ� maxcount
	 */
	public void setMaxcount(String maxcount) {
		this.maxcount = maxcount;
	}
	/**
	 * @return the oldPath
	 */
	public String getOldPath() {
		return oldPath;
	}
	/**
	 * @param oldPathҪ���õ� oldPath
	 */
	public void setOldPath(String oldPath) {
		this.oldPath = oldPath;
	}
	/**
	 * @return the gsshzt
	 */
	public String getGsshzt() {
		return gsshzt;
	}
	/**
	 * @param gsshztҪ���õ� gsshzt
	 */
	public void setGsshzt(String gsshzt) {
		this.gsshzt = gsshzt;
	}
	/**
	 * @return the lastPath
	 */
	public String getLastPath() {
		return lastPath;
	}
	/**
	 * @param lastPathҪ���õ� lastPath
	 */
	public void setLastPath(String lastPath) {
		this.lastPath = lastPath;
	}
	/**
	 * @return the bmjzsj
	 */
	public String getBmjzsj() {
		return bmjzsj;
	}
	/**
	 * @param bmjzsjҪ���õ� bmjzsj
	 */
	public void setBmjzsj(String bmjzsj) {
		this.bmjzsj = bmjzsj;
	}
	/**
	 * @return the jbfwgs
	 */
	public String getJbfwgs() {
		return jbfwgs;
	}
	/**
	 * @param jbfwgsҪ���õ� jbfwgs
	 */
	public void setJbfwgs(String jbfwgs) {
		this.jbfwgs = jbfwgs;
	}
	/**
	 * @return the xydms
	 */
	public String[] getXydms() {
		return xydms;
	}
	/**
	 * @param xydmsҪ���õ� xydms
	 */
	public void setXydms(String[] xydms) {
		this.xydms = xydms;
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
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyjҪ���õ� shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}
	/**
	 * @param idsҪ���õ� ids
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	
	
	
}
