/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-26 ����03:17:33 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��Ϣ�ϱ�
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-26 ����03:17:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XxsbForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;
	
	private String sbsqid;
	
	private String xh;
	
	private String sblx;
	
	private String sbsj;
	
	private String sbzbid;
	
	private String ztqk;
	
	private String xlxsxxqk;
	
	private String bz;
	
	private String splcid;
	
	private String shzt;
	
	private String xn;
	
	private String xq;

	/**************************/
	private String xtgwid;
	private String shid;
	private String shyj;
	private String thgw;
	private String shjg;
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	/**************************/
	
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
	 * @return the sbsqid
	 */
	public String getSbsqid() {
		return sbsqid;
	}

	/**
	 * @param sbsqidҪ���õ� sbsqid
	 */
	public void setSbsqid(String sbsqid) {
		this.sbsqid = sbsqid;
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
	 * @return the sblx
	 */
	public String getSblx() {
		return sblx;
	}

	/**
	 * @param sblxҪ���õ� sblx
	 */
	public void setSblx(String sblx) {
		this.sblx = sblx;
	}

	/**
	 * @return the sbsj
	 */
	public String getSbsj() {
		return sbsj;
	}

	/**
	 * @param sbsjҪ���õ� sbsj
	 */
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	/**
	 * @return the sbzbid
	 */
	public String getSbzbid() {
		return sbzbid;
	}

	/**
	 * @param sbzbidҪ���õ� sbzbid
	 */
	public void setSbzbid(String sbzbid) {
		this.sbzbid = sbzbid;
	}

	/**
	 * @return the ztqk
	 */
	public String getZtqk() {
		return ztqk;
	}

	/**
	 * @param ztqkҪ���õ� ztqk
	 */
	public void setZtqk(String ztqk) {
		this.ztqk = ztqk;
	}

	/**
	 * @return the xlxsxxqk
	 */
	public String getXlxsxxqk() {
		return xlxsxxqk;
	}

	/**
	 * @param xlxsxxqkҪ���õ� xlxsxxqk
	 */
	public void setXlxsxxqk(String xlxsxxqk) {
		this.xlxsxxqk = xlxsxxqk;
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
	 * @return the xtgwid
	 */
	public String getXtgwid() {
		return xtgwid;
	}

	/**
	 * @param xtgwidҪ���õ� xtgwid
	 */
	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
	}

	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}

	/**
	 * @param shidҪ���õ� shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
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
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}

	/**
	 * @param thgwҪ���õ� thgw
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
	 * @param shjgҪ���õ� shjg
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
	 * @param idҪ���õ� id
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
	 * @param gwidsҪ���õ� gwids
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
	 * @param xhsҪ���õ� xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	/**
	 * @return the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}

	/**
	 * @param splcsҪ���õ� splcs
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}
	
}
