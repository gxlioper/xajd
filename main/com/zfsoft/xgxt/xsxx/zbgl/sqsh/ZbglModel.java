/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-12 ����09:31:56 
 */  
package com.zfsoft.xgxt.xsxx.zbgl.sqsh;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @�๦������: �������� 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-3-18 ����01:58:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZbglModel extends SuperAuditModel{

	private static final long serialVersionUID = -5951870429763694581L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String id;
	private String xn;
	private String xh;
	private String shzt;
	private String splcid;
	private String sqsj;
	
	private String dszn;
	private String hyzk;
	private String sl;
	private String sg;
	private String tz;
    private String yzd;
    private String yzly;
    private String cjyy;
    private String cylb;
    private String zgzs;
    private String bz;
    private String ylzd1;
    private String ylzd2;
    private String ylzd3;
    private String ylzd4;
    private String ylzd5;
    private String ylzd6;
    private String ylzd7;
    private String ylzd8;
    private String ylzd9;
    private String ylzd10;
    private String filepath;
    
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
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcidҪ���õ� splcid
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
	 * @param sqsjҪ���õ� sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @return the dszn
	 */
	public String getDszn() {
		return dszn;
	}
	/**
	 * @param dsznҪ���õ� dszn
	 */
	public void setDszn(String dszn) {
		this.dszn = dszn;
	}
	/**
	 * @return the hyzk
	 */
	public String getHyzk() {
		return hyzk;
	}
	/**
	 * @param hyzkҪ���õ� hyzk
	 */
	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}
	/**
	 * @return the sl
	 */
	public String getSl() {
		return sl;
	}
	/**
	 * @param slҪ���õ� sl
	 */
	public void setSl(String sl) {
		this.sl = sl;
	}
	/**
	 * @return the sg
	 */
	public String getSg() {
		return sg;
	}
	/**
	 * @param sgҪ���õ� sg
	 */
	public void setSg(String sg) {
		this.sg = sg;
	}
	/**
	 * @return the tz
	 */
	public String getTz() {
		return tz;
	}
	/**
	 * @param tzҪ���õ� tz
	 */
	public void setTz(String tz) {
		this.tz = tz;
	}
	/**
	 * @return the yzd
	 */
	public String getYzd() {
		return yzd;
	}
	/**
	 * @param yzdҪ���õ� yzd
	 */
	public void setYzd(String yzd) {
		this.yzd = yzd;
	}
	/**
	 * @return the yzly
	 */
	public String getYzly() {
		return yzly;
	}
	/**
	 * @param yzlyҪ���õ� yzly
	 */
	public void setYzly(String yzly) {
		this.yzly = yzly;
	}
	/**
	 * @return the cjyy
	 */
	public String getCjyy() {
		return cjyy;
	}
	/**
	 * @param cjyyҪ���õ� cjyy
	 */
	public void setCjyy(String cjyy) {
		this.cjyy = cjyy;
	}
	/**
	 * @return the cylb
	 */
	public String getCylb() {
		return cylb;
	}
	/**
	 * @param cylbҪ���õ� cylb
	 */
	public void setCylb(String cylb) {
		this.cylb = cylb;
	}
	/**
	 * @return the zgzs
	 */
	public String getZgzs() {
		return zgzs;
	}
	/**
	 * @param zgzsҪ���õ� zgzs
	 */
	public void setZgzs(String zgzs) {
		this.zgzs = zgzs;
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
	 * @return the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * @param ylzd1Ҫ���õ� ylzd1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * @return the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * @param ylzd2Ҫ���õ� ylzd2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * @param ylzd3Ҫ���õ� ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * @return the ylzd4
	 */
	public String getYlzd4() {
		return ylzd4;
	}
	/**
	 * @param ylzd4Ҫ���õ� ylzd4
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	/**
	 * @return the ylzd5
	 */
	public String getYlzd5() {
		return ylzd5;
	}
	/**
	 * @param ylzd5Ҫ���õ� ylzd5
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}
	/**
	 * @return the ylzd6
	 */
	public String getYlzd6() {
		return ylzd6;
	}
	/**
	 * @param ylzd6Ҫ���õ� ylzd6
	 */
	public void setYlzd6(String ylzd6) {
		this.ylzd6 = ylzd6;
	}
	/**
	 * @return the ylzd7
	 */
	public String getYlzd7() {
		return ylzd7;
	}
	/**
	 * @param ylzd7Ҫ���õ� ylzd7
	 */
	public void setYlzd7(String ylzd7) {
		this.ylzd7 = ylzd7;
	}
	/**
	 * @return the ylzd8
	 */
	public String getYlzd8() {
		return ylzd8;
	}
	/**
	 * @param ylzd8Ҫ���õ� ylzd8
	 */
	public void setYlzd8(String ylzd8) {
		this.ylzd8 = ylzd8;
	}
	/**
	 * @return the ylzd9
	 */
	public String getYlzd9() {
		return ylzd9;
	}
	/**
	 * @param ylzd9Ҫ���õ� ylzd9
	 */
	public void setYlzd9(String ylzd9) {
		this.ylzd9 = ylzd9;
	}
	/**
	 * @return the ylzd10
	 */
	public String getYlzd10() {
		return ylzd10;
	}
	/**
	 * @param ylzd10Ҫ���õ� ylzd10
	 */
	public void setYlzd10(String ylzd10) {
		this.ylzd10 = ylzd10;
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
}
